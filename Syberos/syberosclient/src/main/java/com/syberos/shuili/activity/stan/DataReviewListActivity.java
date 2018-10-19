package com.syberos.shuili.activity.stan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.App;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

// 材料审查 从标准化审核记录表中获取

public class DataReviewListActivity extends TranslucentActivity
        implements CommonAdapter.OnItemClickListener,PullRecyclerView.OnPullRefreshListener {

    @BindView(R.id.recyclerView_list)
    PullRecyclerView recyclerView;

    ListAdapter listAdapter = null;
    ArrayList<ObjStanRevis> selectedReviewItemInformationList = new ArrayList<>();
    private ObjStanRevis objStanRevis = null;
    private ArrayList<ObjStanRevis>result = new ArrayList<>();
    private int iSucessCount =0;
    private int iFailedCount = 0;

    @OnClick(R.id.tv_review)
    void onReviewClicked() {
        // use selectedReviewItemInformationList
        if(selectedReviewItemInformationList.size() == 0){
            ToastUtils.show("没有需要审核的项");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",selectedReviewItemInformationList);
        intentActivity(this, DataReviewConclusionActivity.class, false, bundle);
    }

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_review_list;
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

    //材料审核 对象表
    private void getobjStanRevisList() {
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/revi/objStanRevis/";
        HashMap<String,String> param = new HashMap<>();
        param.put("veriWiunCode", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgCode());
        param.put("veriConc","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objStanRevis = gson.fromJson(result,ObjStanRevis.class);
                if(objStanRevis != null || objStanRevis.dataSource != null
                        || objStanRevis.dataSource.size() > 0){
                   getOrgName();
                }else {
                    closeLoadingDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    public void getOrgName() {
        final int size = objStanRevis.dataSource.size();
        for(int i = 0; i< size; i++) {
            String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", objStanRevis.dataSource.get(i).getApplOrgGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    AttOrgBase attOrgBase = gson.fromJson(result, AttOrgBase.class);
                    if (attOrgBase != null && attOrgBase.dataSource != null && attOrgBase.dataSource.size() > 0) {
                        objStanRevis.dataSource.get(finalI).setApplOrgName(attOrgBase.dataSource.get(0).getOrgName());
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
        for(ObjStanRevis item: objStanRevis.dataSource){
            if(item.getVeriConc()!= null && item.getVeriConc().equals("1")){
                result.add(item);
            }
        }
        listAdapter.setData(result);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        if(result != null) result.clear();
        iSucessCount = 0;
        iFailedCount = 0;
        getobjStanRevisList();
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_data_review_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        if(result != null) result.clear();
        getobjStanRevisList();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<ObjStanRevis> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(final ViewHolder holder, final ObjStanRevis information) {

            final CheckBox checkBox = (CheckBox) holder.getView(R.id.cb_select);

            LinearLayout background = (LinearLayout) holder.getView(R.id.ll_background);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isShown()) {
                        if (isChecked) {
                            selectedReviewItemInformationList.add(information);
                        } else {
                            selectedReviewItemInformationList.remove(information);
                        }
                    }
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
