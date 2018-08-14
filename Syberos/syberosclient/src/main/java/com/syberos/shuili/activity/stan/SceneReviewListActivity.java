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
import com.syberos.shuili.entity.standardization.BisStanReviRec;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

// 现场复核 从审核记录表中获取 ifagree = 1 && REVI_TYPE = 1 IF_SITE_REVI = 1

public class SceneReviewListActivity extends TranslucentActivity implements PullRecyclerView.OnPullRefreshListener {

    public static final String SEND_BUNDLE_KEY = "ReviewItemInformation";

    @BindView(R.id.recyclerView_list)
    PullRecyclerView recyclerView;

    ListAdapter listAdapter = null;
    private BisStanReviRec bisStanReviRec = null;


    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scene_review_list;
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
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/revi/selectStanrdReview/";
        HashMap<String,String> param = new HashMap<>();
        param.put("reviType","2");
       // param.put("orgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        param.put("orgGuid","9A1223ACDF57405DB2C3D374AD1BAEEA");
        param.put("ifAgre","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeLoadingDialog();
                Gson gson = new Gson();
                bisStanReviRec = (BisStanReviRec)gson.fromJson(result,BisStanReviRec.class);
                if(bisStanReviRec != null){
                    refreshUI();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void refreshUI(){
        listAdapter.setData(bisStanReviRec.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
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
                    bundle.putSerializable(SEND_BUNDLE_KEY, information);
                    intentActivity(SceneReviewListActivity.this,
                            SceneReviewDetailActivity.class, false, bundle);
                }
            });


            // 申请单位名称
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getWiunName());
            // 申请时间
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getApplTime());
            // 申请等级
            ((TextView) (holder.getView(R.id.tv_level))).setText(information.getApplGrade());

        }
    }
}
