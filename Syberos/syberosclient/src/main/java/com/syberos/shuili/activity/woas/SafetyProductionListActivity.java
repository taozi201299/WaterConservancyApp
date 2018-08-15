package com.syberos.shuili.activity.woas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.woas.BisWoasGrop;
import com.syberos.shuili.entity.woas.ObjWoas;
import com.syberos.shuili.entity.woas.OnSiteInspectionInfo;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 工作考核 安全生产考核
 * 1 从BIS_WOAS_GROP中根据LEAD_ORG_GUID 获取所有考核组信息 woasType 水利稽查 1 安全生产 2
 *
 */

public class SafetyProductionListActivity extends  BaseActivity implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "SafetyProductionListActivity";

    private List<OnSiteInspectionInfo> infoList = null;
    private ListAdapter adapter;

    private BisWoasGrop bisWoasGrop = null;
    private ObjWoas objWoas = null;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle("安全生产考核");
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(this,
                R.layout.activity_safety_production_list_item);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        OnSiteInspectionInfo item = infoList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, item);
        intentActivity((Activity) mContext, SafetyProductionDetailActivity.class,
                false, bundle);
    }

    private void getWoasGroupList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/grop/selectCheckGroupList/";
        HashMap<String,String>params = new HashMap<>();
        params.put("leadOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        // 1 水利稽查工作考核 2 安全生产工作考核
        params.put("woasType","2");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWoasGrop = gson.fromJson(result,BisWoasGrop.class);
                if(bisWoasGrop == null || bisWoasGrop.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getWoasGroupInfo();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getWoasGroupInfo(){
        final ArrayList<BisWoasGrop>list = (ArrayList<BisWoasGrop>) bisWoasGrop.dataSource;
        final int size = list.size();
        String url = "";
        HashMap<String,String>params = new HashMap<>();
        for(int i = 0 ; i < size; i++){
            params.put("","");
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    objWoas = gson.fromJson(result,ObjWoas.class);
                    if(objWoas == null || objWoas.dataSource == null || objWoas.dataSource.size() == 0){
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    objWoas.setGroupName(list.get(finalI).getWoasGropName());
                    if(finalI == size -1){
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
    private void refreshUI(){
        adapter.setData(objWoas.dataSource);
        adapter.notifyDataSetChanged();

    }
    private class ListAdapter extends CommonAdapter<ObjWoas> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjWoas information) {

            ((TextView) (holder.getView(R.id.tv_title))).setText(information.getGroupName());
            ((TextView) (holder.getView(R.id.tv_name))).setText(information.getWoasRequ());
            ((TextView) (holder.getView(R.id.tv_time))).setText(information.getWoasDeadLine());

            TextView tv_assess = (TextView) (holder.getView(R.id.tv_assess));
            tv_assess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentActivity((Activity) mContext, InspectAssessObjectSelectActivity.class,
                            false, true);
                }
            });
        }
    }
}
