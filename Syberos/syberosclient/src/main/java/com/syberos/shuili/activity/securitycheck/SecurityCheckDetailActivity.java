package com.syberos.shuili.activity.securitycheck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepDetailActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.entity.securitycheck.ObjExpert;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupExpert;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomScrollView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.activity.securitycheck.SecurityCheckTaskActivity.SEND_BUNDLE_KEY;
import static com.syberos.shuili.config.GlobleConstants.strIP;

/**
 * Created by jidan on 18-4-6.
 * 检查方案 + 被检对象+  隐患
 * 被检对象为工程ID 或者单位ID
 * 根据组ID 从检查小组与检查对象关系表中找到所有的检查对象ID 根据ID从工程对象表和机构对象表中查找对应的信息
 * 交互修改：该界面不显示隐患信息 在新的界面中显示隐患
 */

public class SecurityCheckDetailActivity extends BaseActivity implements View.OnClickListener{

    private HiddenInvestigationTaskInfo information;
    /**
     * 检查组信息
     */
    private BisSinsScheGroup bisSinsScheGroup;
    /**
     * 检查组和专家关系信息
     */
    private RelSinsGroupExpert relSinsGroupExpert;
    /**
     * 专家信息
     */
    private ObjExpert objExpert;
    /**
     * 检查组和检查对象关系信息
     */
    private RelSinsGroupWiun relSinsGroupWiun;

    private BisSinsSche bisSinsSche  = null;

    /**
     * 隐患信息表
     */
    private ObjHidden objHidden;
    @BindView(R.id.tv_check_plan)
    TextView tv_check_plan;

    @BindView(R.id.tv_check_time)
    TextView tv_check_time;

    @BindView(R.id.tv_check_content)
    TextView tv_check_content;

    @BindView(R.id.tv_group_leader)
    TextView tv_group_leader;

    @BindView(R.id.tv_group_unit)
    TextView tv_group_unit;

    @BindView(R.id.tv_member_unit)
    TextView tv_member_unit;

    @BindView(R.id.tv_check_person)
    TextView tv_check_person;

    @BindView(R.id.rl_hidden_count)
    RelativeLayout rl_hidden_count;
    @BindView(R.id.ll_check_object_container)
    LinearLayout ll_check_object_container;
    @BindView(R.id.ll_hidden_object_container)
    LinearLayout ll_hidden_object_container;
    @BindView(R.id.customScrollView)
    CustomScrollView  customScrollView;
    private int iSucessCount = 0;
    private int iFailedCount = 0;

    @Override  public int getLayoutId() {
        return R.layout.activity_security_check_form_layout;
    }

    @Override
    public void initListener() {
        rl_hidden_count.setOnClickListener(this);

    }

    @Override
    public void initData() {
        iSucessCount = 0;
        iFailedCount = 0;
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if(bisSinsScheGroup == null){
            bisSinsScheGroup = (BisSinsScheGroup) bundle.getSerializable(SEND_BUNDLE_KEY);
        }
        if(bisSinsSche == null ){
            bisSinsSche = (BisSinsSche)bundle.getSerializable("bisSinsSche");
        }
        if(bisSinsScheGroup == null || bisSinsSche == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            return;
        }
        showDataLoadingDialog();
        showTitle(bisSinsScheGroup.getGroupName());
        getExpertGuid();

    }

    /**
     * 获取专家信息 从检查小组和专家关系表中获取专家GUID，在从专家信息表中获取专家详细信息
     */
    private void getExpertGuid(){
        String url = strIP +"/sjjk/v1/rel/sins/group/expe/relSinsGroupExpes/";
        HashMap<String,String>params = new HashMap<>();
        params.put("groupGuid",bisSinsScheGroup.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                relSinsGroupExpert = (RelSinsGroupExpert)gson.fromJson(result,RelSinsGroupExpert.class);
                if(relSinsGroupExpert != null && relSinsGroupExpert.dataSource.size() > 0){
                    getExpertInfo();
                }else {
                    getCheckObject();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getExpertInfo(){
        String url = strIP +"/sjjk/v1/obj/expert/objExperts/";
        HashMap<String,String> params = new HashMap<>();
        params.put("persGuid",relSinsGroupExpert.dataSource.get(0).getExpeGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objExpert = gson.fromJson(result,ObjExpert.class);
                if(objExpert != null && objExpert.dataSource != null){
                    getCheckObject();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    /**
     * 获取被检对象
     */
    private void getCheckObject(){
        String url = strIP +"/sjjk/v1/rel/sins/group/wiun/relSinsGroupWiuns/";
        HashMap<String,String>params = new HashMap<>();
        params.put("groupGuid",bisSinsScheGroup.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                relSinsGroupWiun = gson.fromJson(result,RelSinsGroupWiun.class);
                if(relSinsGroupWiun != null && relSinsGroupWiun.dataSource != null){
                    getEntName();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getEntName(){
        final int size = relSinsGroupWiun.dataSource.size();
        for(final RelSinsGroupWiun item : relSinsGroupWiun.dataSource){
            String url = GlobleConstants.strIP + "/sjjk/v1/jck/obj/objEngs/";
            HashMap<String,String>params = new HashMap<>();
            params.put("guid",item.getObjGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    ObjectEngine objectEngine  = null;
                    objectEngine = gson.fromJson(result,ObjectEngine.class);
                    if(objectEngine == null || objectEngine.dataSource == null ||
                            objectEngine.dataSource.size() == 0){

                    }
                    item.setObjName(objectEngine.dataSource.get(0).getEngName());
                    if(iSucessCount +iFailedCount == size) {
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == size) {
                        refreshUI();
                    }
                }
            });
        }
    }
    private void getCheckObjectInfo(){
        getHiddenInfo();
    }
    private void getHiddenInfo(){
        // 1 根据方案ID 获取该检查方案下的所有隐患，再根据engguid获取对应的检查组，从而获取该检查组下的所有检查方案

        getAllhiddenByPlanId();

    }
    private void getAllhiddenByPlanId(){
        String url = strIP +"/sjjk/v1/bis/obj/selectCheckPlansHiddInfo/";
        HashMap<String,String>params = new HashMap<>();
        params.put("sinsGuid",bisSinsScheGroup.getScheGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objHidden = gson.fromJson(result,ObjHidden.class);
                if(objHidden == null || objHidden.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-2).getMessage());
                }else {
                    parseHiddenList();
                    refreshUI();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void parseHiddenList(){
        for(ObjHidden item : objHidden.dataSource){
            item.setbExist(getEngGuid(item));
        }


    }
    boolean getEngGuid(ObjHidden objHidden){
        for(RelSinsGroupWiun item : relSinsGroupWiun.dataSource){
            if(item.getGuid().equals(objHidden.getEngGuid())){
                objHidden.setEngName(item.getObjName());
                return true;
            }
        }
        return false;
    }
    private void refreshUI(){
        customScrollView.setVisibility(View.VISIBLE);
        // 检查方案名称
        tv_check_plan.setText(bisSinsSche.getScheName());
        // 检查时间
        tv_check_time.setText(bisSinsSche.getScheStartTime() +"-- " +bisSinsSche.getScheCompTime());
        // 检查内容
        tv_check_content.setText(bisSinsSche.getScheCont());
        // 组长名称
        tv_group_leader.setText(bisSinsScheGroup.getGroupLeader());
        // 组长单位
        tv_group_unit.setText(bisSinsScheGroup.getGroupLeaderWiun());
        // 专家姓名
        if(objExpert!= null && objExpert.dataSource != null &&objExpert.dataSource.size() > 0) {
            tv_check_person.setText(objExpert.dataSource.get(0).getPersName());
        }
        // 被检对象
        ll_check_object_container.removeAllViews();
        final ArrayList<RelSinsGroupWiun>infos = (ArrayList<RelSinsGroupWiun>) relSinsGroupWiun.dataSource;
        final int size = infos.size();
        for (int i = 0; i < size; i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_with_right_arrow, null);
            TextView tv_item_name = (TextView)view.findViewById(R.id.tv_item_name);
            tv_item_name.setText(infos.get(i).getObjName());
            ll_check_object_container.addView(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("checkItem",infos.get(finalI));
//                    intentActivity(SecurityCheckDetailActivity.this, SecurityCheckItemFormActivity.class,
//                            false, bundle);
                }
            });
            return;
        }

        // 隐患类别
        if(objHidden != null) {
            for (final ObjHidden item : objHidden.dataSource) {
                if (!item.isbExist()) continue;
                View view = LayoutInflater.from(mContext).inflate(R.layout.activity_investigation_task_item, null);
                TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
                TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
                LinearLayout ll_type = (LinearLayout) view.findViewById(R.id.ll_type);
                if (item.getHiddGrad().equals("0")) {
                    tv_type.setText(getResources().getString(R.string.normal));
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                } else if (item.getHiddGrad().equals("1")) {
                    tv_type.setText(getResources().getString(R.string.danger));
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                }
                tv_title.setText(item.getHiddName());
                tv_time.setText(item.getCollTime());
                tv_name.setText(item.getEngName());
                tv_content.setText(item.getHiddDesc());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", item);
                        intentActivity(SecurityCheckDetailActivity.this, InvestigationAccepDetailActivity.class, false, bundle);
                    }
                });
            }
        }

    }
    @Override
    public void initView() {
        customScrollView.setVisibility(View.GONE);
      setActionBarRightVisible(View.INVISIBLE);
      setInitActionBar(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_hidden_count:
                go2CheckHiddenActivity();
                break;
        }
    }
    private void go2CheckHiddenActivity(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("checkObject",relSinsGroupWiun);
        bundle.putSerializable("checkGroup",bisSinsScheGroup);
        intentActivity(SecurityCheckDetailActivity.this,SecurityCheckHiddenActivity.class,false,bundle);

    }
}
