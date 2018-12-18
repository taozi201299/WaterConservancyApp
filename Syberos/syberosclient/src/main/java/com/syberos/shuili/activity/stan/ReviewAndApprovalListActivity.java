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
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.standardization.BisConfExamappr;
import com.syberos.shuili.entity.standardization.BisScheRevi;
import com.syberos.shuili.entity.standardization.BisStanReviRec;
import com.syberos.shuili.entity.standardization.ObjStanAppl;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

//  从达标申请表中查找状态为4的记录，并从会议审定表中查到该记录的审核情况
//0:补充材料 1:形式初审 2:资料审查3.现场复核4.会议审定 5.公示 6.发证公告 7.拒绝达标 8.已发证

public class ReviewAndApprovalListActivity extends TranslucentActivity implements PullRecyclerView.OnPullRefreshListener {

    public static final String SEND_BUNDLE_KEY = "ReviewItemInformation";

    @BindView(R.id.recyclerView_list)
    PullRecyclerView recyclerView;

    ListAdapter listAdapter = null;

    private ObjStanAppl objStanAppl = null;
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
    private void getObjStanAppls(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppls/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("stat","4");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objStanAppl = gson.fromJson(result,ObjStanAppl.class);
                if(objStanAppl == null || objStanAppl.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
                else if(objStanAppl.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                    refreshUI();
                }
             getBisConfExamappr();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());

            }
        });
    }

    /**
     * 从会议审定表中获取数据
     */
    private void getBisConfExamappr(){
        final int size = objStanAppl.dataSource.size();
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/conf/examappr/bisConfExamapprs/";
        urlTags.add(url);
        HashMap<String,String> params = new HashMap<>();
        ObjStanAppl item = null;
        for(int i = 0 ; i < size ; i++){
            item = objStanAppl.dataSource.get(i);
            params.put("applGuid",item.getGuid());
            final ObjStanAppl finalItem = item;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    BisConfExamappr bisConfExamappr = gson.fromJson(result,BisConfExamappr.class);
                    if(bisConfExamappr!= null || bisConfExamappr.dataSource != null){
                        if(bisConfExamappr.dataSource.size() == 0){
                            finalItem.setVerify(false);
                        }else {
                            BisConfExamappr obj = bisConfExamappr.dataSource.get(0);
                            if((obj.getExamapprExpert() != null && !obj.getExamapprExpert().isEmpty())
                                    || obj.getExamapprOpin() != null && !obj.getExamapprOpin().isEmpty()
                                    || obj.getVeriOpin() != null && !obj.getVeriOpin().isEmpty()){
                                finalItem.setVerify(true);
                            }
                        }
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
        final int size = objStanAppl.dataSource.size();
        for(int i = 0; i< size; i++) {
            String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
            urlTags.add(url);
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", objStanAppl.dataSource.get(i).getApplOrgGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    AttOrgBase attOrgBase = gson.fromJson(result, AttOrgBase.class);
                    if (attOrgBase != null && attOrgBase.dataSource != null && attOrgBase.dataSource.size() > 0) {
                        objStanAppl.dataSource.get(finalI).setApplOrgName(attOrgBase.dataSource.get(0).getOrgName());
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
        ArrayList<ObjStanAppl>datas = new ArrayList<>();
        for(ObjStanAppl item : objStanAppl.dataSource){
            if(item.isVerify()){
                datas.add(item);
            }
        }
        listAdapter.setData(datas);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        if(!"1".equals(String.valueOf(BusinessConfig.getOrgLevel()))){
            return;
        }
         iSucessCount = 0;
         iFailedCount = 0;
        showDataLoadingDialog();
        getObjStanAppls();
    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Stan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_scene_review_list_item);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onRefresh() {
        getObjStanAppls();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<ObjStanAppl> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(final ViewHolder holder, final ObjStanAppl information) {
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
            String grade = GlobleConstants.stanGradeMap.get(information.getApplGrade());
            ((TextView) (holder.getView(R.id.tv_level))).setText(grade == null ?"":grade);

        }
    }
}
