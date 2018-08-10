package com.syberos.shuili.activity.securitycheck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepDetailActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.entity.securitycheck.ObjExpert;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupExpert;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;

import static com.syberos.shuili.config.GlobleConstants.strIP;

/**
 * 安全检查检查记录中隐患信息 被检对象详情和被检对象下的隐患信息
 */

public class SecurityCheckQueryProblemsActivity extends BaseActivity  {

    @BindView(R.id.ll_hidden_object_container)
    LinearLayout ll_hidden_object_container;
    @BindView(R.id.tv_group_unit)
    TextView tv_group_unit;
    @BindView(R.id.tv_check_person)
    TextView tv_check_person;
    @BindView(R.id.tv_check_project)
    TextView tv_check_project;
    @BindView(R.id.tv_problem_count)
    TextView tv_problem_count;


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

    /**
     * 隐患信息表  通过检查对象来判断是哪个检查组提交的隐患
     */
    private ObjHidden objHidden;
    /**
     * 传递的参数
     */
    private BisSinsScheGroup bisSinsScheGroup;

    private int hiddCount = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_security_check_query_problems;
    }

    @Override
    public void initListener() {

    }
    @Override
    public void initView() {
        setActionBarTitle("隐患信息");
        setActionBarRightVisible(View.INVISIBLE);
    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if(bisSinsScheGroup == null){
            bisSinsScheGroup = (BisSinsScheGroup) bundle.getSerializable("bisSinsScheGroup");
        }
        getExpertGuid();
    }
    /**
     * 获取专家信息 从检查小组和专家关系表中获取专家GUID，在从专家信息表中获取专家详细信息
     */
    private void getExpertGuid(){
        String url = strIP +"/sjjk/v1/rel/sins/group/expe/relSinsGroupExpes/";
        HashMap<String,String> params = new HashMap<>();
        params.put("groupGuid",bisSinsScheGroup.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                relSinsGroupExpert = (RelSinsGroupExpert)gson.fromJson(result,RelSinsGroupExpert.class);
                if(relSinsGroupExpert != null){
                    getExpertInfo();
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
                objExpert = (ObjExpert)gson.fromJson(result,ObjExpert.class);
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
        String url = strIP +"/sjjk/v1/rel/sins/group/wiun/selectCheckOnline/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisSinsScheGroup.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                relSinsGroupWiun = (RelSinsGroupWiun) gson.fromJson(result, RelSinsGroupWiun.class);
                if (relSinsGroupWiun != null && relSinsGroupWiun.dataSource != null) {
                    getAllhiddenByPlanId();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
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
            if(item.isbExist()){
                hiddCount ++;
            }
        }
    }
    boolean getEngGuid(ObjHidden objHidden){
        for(RelSinsGroupWiun item : relSinsGroupWiun.dataSource){
            if(item.getGuid().equals(objHidden.getEngGuid())){
                objHidden.setEngName(item.getEngName());
                return true;
            }
        }
        return false;
    }
    private void refreshUI(){
        tv_group_unit.setText(bisSinsScheGroup.getGroupLeaderWiun());
        if(objExpert != null || objExpert.dataSource != null) {
            String name = "";
            for (ObjExpert item : objExpert.dataSource) {
                name += item.getPersName();
                name += " ";
            }
            tv_check_person.setText(name);
        }
        if(relSinsGroupWiun != null || relSinsGroupWiun.dataSource != null){
            String checkObject= "";
            for(RelSinsGroupWiun item : relSinsGroupWiun.dataSource){
                checkObject += item.getEngName();
                checkObject +=" ";
            }
            tv_check_project.setText(checkObject);
            tv_problem_count.setText(hiddCount);
        }
        if(objHidden != null || objHidden.dataSource != null){
            for(final ObjHidden item : objHidden.dataSource){
                if(!item.isbExist()) continue;
                View view = LayoutInflater.from(mContext).inflate(R.layout.activity_investigation_task_item,null);
                TextView tv_type = (TextView)view.findViewById(R.id.tv_type);
                TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
                TextView tv_time = (TextView)view.findViewById(R.id.tv_time);
                TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
                TextView tv_content = (TextView)view.findViewById(R.id.tv_content);
                LinearLayout ll_type = (LinearLayout)view.findViewById(R.id.ll_type);
                if(item.getHiddGrad().equals("0")){
                    tv_type.setText(getResources().getString(R.string.normal));
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                }else if(item.getHiddGrad().equals("1")){
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
                        bundle.putSerializable("data",item);
                        intentActivity(SecurityCheckQueryProblemsActivity.this, InvestigationAccepDetailActivity.class,false,bundle);
                    }
                });
            }
            }
        }

}
