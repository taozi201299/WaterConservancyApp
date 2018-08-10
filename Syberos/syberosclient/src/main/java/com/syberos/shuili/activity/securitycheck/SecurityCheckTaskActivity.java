package com.syberos.shuili.activity.securitycheck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupPers;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.strIP;

/**
 * Created by Administrator on 2018/4/3.
 * 安全检查行政版 现场检查
 */

public class SecurityCheckTaskActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "item";

    private List<HiddenInvestigationTaskInfo> investigationInfos = null;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SecurityCheckTaskAdapter adapter;
    private RelSinsGroupPers relSinsGroupPers; // 检查小组和组员关系表
    private BisSinsScheGroup bisSinsScheGroup; // 安全检查分组信息表
    private ArrayList<BisSinsScheGroup>bisSinsScheGroups  = new ArrayList<>(); //安全检查分组列表
    private ArrayList<BisSinsSche> bisSinsSches = new ArrayList<>();  // 安全检查方案信息表
    private RelSinsGroupWiun relSinsGroupWiun;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recyclerview_laout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getScheGroupIdByUserId();
    }

    @Override
    public void initView() {
        showDataLoadingDialog();
        showTitle(getResources().getString(R.string.module_child_anquan_xianchangjiancha));
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        adapter = new SecurityCheckTaskAdapter(this, R.layout.activity_security_check_task_item_layout);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        BisSinsScheGroup item = bisSinsScheGroup.dataSource.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, item);
        intentActivity((Activity) mContext, SecurityCheckFormActivity.class, false, bundle);
    }

    /**
     *  1 根据用户id从8.2.3.14	检查小组与组员关系表（REL_SINS_GROUP_PERS）中获取检查小组信息
     */
    private void getScheGroupIdByUserId(){
        String userId = SyberosManagerImpl.getInstance().getCurrentUserId();
        String url = strIP +"/sjjk/v1/rel/sins/group/pers/relSinsGroupPerss/";
        HashMap<String,String>params = new HashMap<>();
        //params.put("persGuid",userId);
        params.put("persGuid","dfdfgfdfA9FBCffgfgBF285D6Affgrtr");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                relSinsGroupPers = gson.fromJson(result,RelSinsGroupPers.class);
                if(relSinsGroupPers == null || relSinsGroupPers.dataSource == null){
                    closeDataDialog();
                    String errMsg  = ErrorInfo.ErrorCode.valueOf(-5).getMessage();
                    ToastUtils.show(errMsg);
                }
                getScheGroupList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getScheGroupList(){
/**
 *   2 根据检查分组GUID 获取安全检查方案分组信息  （现场检查任务列表）
 */
       for(final RelSinsGroupPers item :relSinsGroupPers.dataSource) {
           String url = strIP +"/sjjk/v1/bis/sins/sche/grop/bisSinsScheGrops/";
           HashMap<String, String> params = new HashMap<>();
           params.put("guid",item.getGroupGuid());
           SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
               @Override
               public void onResponse(String result) {
                   Gson gson = new Gson();
                   bisSinsScheGroup = gson.fromJson(result, BisSinsScheGroup.class);
                   if (bisSinsScheGroup != null && bisSinsScheGroup.dataSource != null) {
                       bisSinsScheGroups.add(bisSinsScheGroup.dataSource.get(0));
                   } else {
                       closeDataDialog();
                       String errMsg = ErrorInfo.ErrorCode.valueOf(-2).getMessage();
                       ToastUtils.show(errMsg);
                       return;
                   }
                   if(relSinsGroupPers.dataSource.indexOf(item) == relSinsGroupPers.dataSource.size() - 1){
                       getPlanInfo();
                   }
               }

               @Override
               public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                   closeDataDialog();
                   ToastUtils.show(errorInfo.getMessage());
               }
           });
       }
    }
    /**
     * 3 检查方案GUID获取该检查方案的信息 从安全检查方案（BIS_SINS_SCHE）表中获取
     * todo 该接口sql报错 暂未处理
     */
    private void getPlanInfo(){
        String url = strIP +"/sjjk/v1/bis/sins/sche/bisSinsSches/";
        HashMap<String,String>params = new HashMap<>();
        for(BisSinsScheGroup item : bisSinsScheGroups){
            params.put("guid",item.getScheGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeDataDialog();
                    Gson gson = new Gson();
                    BisSinsSche bisSinsSche = gson.fromJson(result,BisSinsSche.class);
                    if(bisSinsSche == null || bisSinsSche.dataSource == null){
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        //   return;
                    }
                    bisSinsSches.add(bisSinsSche);
                    if(bisSinsSches.size() == bisSinsScheGroups.size()){
                        merageData();
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
    }
    private void merageData(){
        int size = bisSinsSches.size();
        BisSinsSche item ;
        for(int i = 0 ; i < size ; i++){
            BisSinsSche bisSinsSche = bisSinsSches.get(i);
            if(bisSinsSche.dataSource == null || bisSinsSche.dataSource.size() == 0){
                continue;
            }
            item = bisSinsSches.get(i).dataSource.get(0);
            for(BisSinsScheGroup group : bisSinsScheGroup.dataSource){
                if(item.getGuid().equals(group.getScheGuid())){
                    group.setScheName(item.getScheName());
                    group.setScheStartTime(item.getScheStartTime());
                    group.setScheCompTime(item.getScheCompTime());
                    group.setScheCont(item.getScheCont());
                }

            }
        }
    }
    private void refreshUI(){
        adapter.setData(bisSinsScheGroup.dataSource);
        adapter.notifyDataSetChanged();
    }
    private class SecurityCheckTaskAdapter extends CommonAdapter<BisSinsScheGroup> {
        public SecurityCheckTaskAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisSinsScheGroup investigationInfo) {
            LinearLayout ll_type = null;
            TextView addNewProblem = (TextView) (holder.getView(R.id.tv_ok));
            addNewProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY, investigationInfo);
                    intentActivity(SecurityCheckTaskActivity.this,
                            SecurityCheckProjectSelectActivity.class, false, bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_title))).setText(investigationInfo.getGroupName());
            ((TextView)(holder.getView(R.id.tv_name))).setText(investigationInfo.getScheName());
            ((TextView) (holder.getView(R.id.tv_content))).setText(investigationInfo.getScheStartTime() +" --" +investigationInfo.getScheCompTime());


        }
    }
}
