package com.syberos.shuili.activity.stan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.standardization.BisStanReviRec;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

// 现场复核 从审核记录表中获取 ifagree = 1 && REVI_TYPE = 1 IF_SITE_REVI = 1

// 从标准化评审记录表中获取

public class ReviewAndApprovalListActivity extends TranslucentActivity implements PullRecyclerView.OnPullRefreshListener {

    public static final String SEND_BUNDLE_KEY = "ReviewItemInformation";

    @BindView(R.id.recyclerView_list)
    PullRecyclerView recyclerView;

    ListAdapter listAdapter = null;
    private BisStanReviRec bisStanReviRec = null;
    private int iSucessCount = 0;
    private int iFailedCount = 0;


    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_review_and_approval_list;
    }

    @Override
    public void initListener() {
        recyclerView.setOnPullRefreshListener(this);
        recyclerView.setHasMore(false);

    }
    private void closeLoadingDialog(){
        closeDataDialog();
        recyclerView.refreshOrLoadComplete();
    }
    private void getobjStanRevisList() {
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/revi/bisStanReviRecs/";
        HashMap<String,String> param = new HashMap<>();
        param.put("reviType","3");
        param.put("ifAgre","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisStanReviRec = gson.fromJson(result,BisStanReviRec.class);
                if(bisStanReviRec != null && bisStanReviRec.dataSource != null &&
                        bisStanReviRec.dataSource.size() > 0){
                    getApplOrgId();
                }else {
                    closeLoadingDialog();
                    ToastUtils.show(SyberosManagerImpl.ErrorCode.valueOf(-7).getMessage());
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getApplOrgId(){
        final int size = bisStanReviRec.dataSource.size();
        for(int i = 0 ; i< size;i++) {
            final BisStanReviRec item = bisStanReviRec.dataSource.get(i);
            String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/revi/objStanRevis/";
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", item.getStanReviGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    ObjStanRevis objStanRevis =  gson.fromJson(result,ObjStanRevis.class);
                    if(objStanRevis != null  && objStanRevis.dataSource != null &&
                            objStanRevis.dataSource.size() > 0){
                        item.setApplOrgId(objStanRevis.dataSource.get(0).getApplOrgGuid());
                        item.setApplGrade(objStanRevis.dataSource.get(0).getApplGrade());
                        item.setApplTime(objStanRevis.dataSource.get(0).getApplTime());
                    }
                    if(iSucessCount + iFailedCount == size){
                        getOrgName();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount + iFailedCount == size){
                        getOrgName();
                    }

                }
            });
        }
    }
    public void getOrgName() {
        iSucessCount = 0;
        iFailedCount = 0;
        final int size = bisStanReviRec.dataSource.size();
        for(int i = 0; i< size; i++) {
            String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", bisStanReviRec.dataSource.get(i).getApplOrgId());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    AttOrgBase attOrgBase = gson.fromJson(result, AttOrgBase.class);
                    if (attOrgBase != null && attOrgBase.dataSource != null && attOrgBase.dataSource.size() > 0) {
                        bisStanReviRec.dataSource.get(finalI).setApplOrgName(attOrgBase.dataSource.get(0).getOrgName());
                    }
                    if(iSucessCount +iFailedCount == size) {
                        closeLoadingDialog();
                        refreshUI();
                    }

                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == size) {
                        closeLoadingDialog();
                        refreshUI();
                    }
                }
            });
        }

    }
    private void refreshUI(){
        listAdapter.setData(bisStanReviRec.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
         iSucessCount = 0;
         iFailedCount = 0;
        showDataLoadingDialog();
        getobjStanRevisList();
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_scene_review_list_item);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onRefresh() {
        getobjStanRevisList();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<BisStanReviRec> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(final ViewHolder holder, final BisStanReviRec information) {
            LinearLayout background = (LinearLayout) holder.getView(R.id.ll_background);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", information);
                    intentActivity(ReviewAndApprovalListActivity.this,
                            ReviewAndApprovalDetailActivity.class, false, bundle);
                }
            });

            // 申请单位名称
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getApplOrgName());
            // 申请时间
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getApplTime());
            // 申请等级
            ((TextView) (holder.getView(R.id.tv_level))).setText(information.getApplGrade());

        }
    }
}
