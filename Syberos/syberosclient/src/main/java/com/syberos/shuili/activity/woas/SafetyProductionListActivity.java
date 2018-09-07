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
 * 工作考核 水利稽查
 *  1 BIS_WOAS_GROP 考核组表
 *  2 8.2.1.16	工作考核对象表（OBJ_WOAS）中获取详情
 */


public class SafetyProductionListActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "OnSiteInspectionInfo";
    private final String Title = "安全生产考核";

    private List<OnSiteInspectionInfo> infoList = null;
    private ListAdapter adapter;

    private BisWoasGrop bisWoasGrop = null;
    private ObjWoas objWoas = null;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int iSucessCount = 0;
    private int iFailedCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_list;
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(this);

    }

    @Override
    public void initData() {
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getWoasGroupList();
    }

    @Override
    public void initView() {
        showTitle(Title);
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
        BisWoasGrop item = bisWoasGrop.dataSource.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, item);
        intentActivity((Activity) mContext, SafetyProductionDetailActivity.class,
                false, bundle);
    }

    /**
     * 该接口需要返回方案Guid
     */
    private void getWoasGroupList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/grop/selectCheckGroupList/";
        HashMap<String,String>params = new HashMap<>();
        params.put("leadOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("leadOrgGuid","B694018733574C1398AF1064371BF5C6");
        // 1 水利稽查工作考核 2 安全生产工作考核
          params.put("woasType","2");
          params.put("sendStat","1");
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
                if(bisWoasGrop.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show("没有相关安全生产考核信息");
                }
                getObjWoasInfoByID();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    /**
     * 根据考核ID获取
     */
    private void getObjWoasInfoByID(){
        final ArrayList<BisWoasGrop>list = (ArrayList<BisWoasGrop>) bisWoasGrop.dataSource;
        final int size = list.size();
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/woas/selectSinglDeploymentNotificationInfor/";
        HashMap<String,String>params = new HashMap<>();
        for(int i = 0 ; i < size; i++){
            final BisWoasGrop item = list.get(i);
            if(iFailedCount > 0) break;
            params.put("guid",list.get(i).getWoasGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    objWoas = gson.fromJson(result,ObjWoas.class);
                    if(objWoas == null || objWoas.dataSource == null || objWoas.dataSource.size() == 0){
                        iFailedCount ++;
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    }else {
                        item.setStartTime(objWoas.dataSource.get(0).getWoasStartime());
                        item.setEndTime(objWoas.dataSource.get(0).getWoasDeadline());
                    }
                    if(iSucessCount == size){
                        closeDataDialog();
                        refreshUI();
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }
    private void refreshUI(){
        adapter.setData(bisWoasGrop.dataSource);
        adapter.notifyDataSetChanged();

    }
    private class ListAdapter extends CommonAdapter<BisWoasGrop> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisWoasGrop information) {

            ((TextView) (holder.getView(R.id.tv_title))).setText(information.getWoasGropName());
            ((TextView) (holder.getView(R.id.tv_name))).setText(information.getLareId());
            ((TextView) (holder.getView(R.id.tv_time))).setText(information.getStartTime() +"-"+information.getEndTime());

            TextView tv_assess = (TextView) (holder.getView(R.id.tv_assess));
            tv_assess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bisWoasGrop",information);
                    intentActivity((Activity) mContext, SafetyProductionObjectSelectActivity.class,
                            false, bundle);
                }
            });
        }
    }
}
