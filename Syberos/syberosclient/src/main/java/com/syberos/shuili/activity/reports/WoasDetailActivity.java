package com.syberos.shuili.activity.reports;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.report.BisWoasNoti;
import com.syberos.shuili.entity.report.ObjWoas;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 工作考核报表 上报和退回 没有其他操作
 * 分级显示
 */
public class WoasDetailActivity extends BaseActivity {

    private ObjWoas objWoas = null;
    private BisWoasNoti bisWoasNoti = null;

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;
    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;
    @BindView(R.id.iv_action_right)
    LinearLayout iv_action_right;
    @BindView(R.id.iv_action_bar_back)
    ImageView iv_action_bar_back;

    private ListAdapter listAdapter ;
    private int orgLevel = BusinessConfig.getOrgLevel();
    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_hidden_danger_report;
    }

    @Override
    public void initListener() {
        iv_action_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityFinish();
            }
        });

    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        if(orgLevel == 1){
            getWoasNoti();
        }else {
            getWoasNotiSelf();
        }
    }
    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_enterprises_hidden_danger_report_item);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.HORIZONTAL));
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        objWoas = (ObjWoas) bundle.getSerializable("objWoas");
        if(objWoas == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
        tv_action_bar_title.setText("考核报表");
        iv_action_right.setVisibility(View.GONE);

    }
    private void getWoasNoti(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/noti/selectIncomingDispatchesUnitList/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("woasGuid",objWoas.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWoasNoti = gson.fromJson(result, BisWoasNoti.class);
                if(bisWoasNoti == null || bisWoasNoti.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                }
                refreshUI();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getWoasNotiSelf(){
            String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/noti/selectIncomingDispatchesUnitList/";
        urlTags.add(url);
            HashMap<String,String>params = new HashMap<>();
            params.put("woasGuid",objWoas.getGuid());
            params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeDataDialog();
                    Gson gson = new Gson();
                    bisWoasNoti = gson.fromJson(result, BisWoasNoti.class);
                    if(bisWoasNoti == null || bisWoasNoti.dataSource == null){
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                    }else if(bisWoasNoti.dataSource.size() == 1){
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
    private void refreshUI(){
        closeDataDialog();
        listAdapter.setData(bisWoasNoti.dataSource);
        listAdapter.notifyDataSetChanged();

    }

    private class ListAdapter extends CommonAdapter<BisWoasNoti> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisWoasNoti bisWoasNoti) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(bisWoasNoti.getOrgName());
            TextView tv_refunded = holder.getView(R.id.tv_refunded);
            TextView tv_report = holder.getView(R.id.tv_report);
            TextView tv_recall = holder.getView(R.id.tv_recall);
            TextView tv_score = holder.getView(R.id.tv_score);
            tv_recall.setVisibility(View.GONE);
            tv_refunded.setVisibility(View.GONE);
            // 0 未上报 1 已上报
            final int linkStatus = Integer.valueOf(bisWoasNoti.getRepStat());
            if (orgLevel == 1) {
                tv_report.setVisibility(View.GONE);
                tv_score.setVisibility(View.VISIBLE);
                tv_score.setText(tv_score.getText() + bisWoasNoti.getSeasScore());
                tv_refunded.setVisibility(View.VISIBLE);
                if( 0 == linkStatus){
                    tv_refunded.setText("未上报");
                }else {
                    tv_refunded.setText("已上报");
                }

            } else {
                // 上报和撤回功能
                switch (linkStatus) {
                    case 0:
                        tv_report.setVisibility(View.VISIBLE);
                        tv_report.setText("上报");
                        break;
                    case 1:
                        tv_report.setVisibility(View.GONE);
                        tv_recall.setVisibility(View.VISIBLE);
                        tv_refunded.setVisibility(View.GONE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color, null));
                        } else {
                            tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color));
                        }
                        break;
                }
                tv_report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        report(1, bisWoasNoti);
                    }
                });
                tv_recall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        report(2, bisWoasNoti);
                    }
                });
            }
        }
    }

    private void report(int type,BisWoasNoti bisWoasNoti){
        // 0 上报 1 申请撤回
        String url = GlobleConstants.strZRIP + "/woas/mobile/bisWoasNoti/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisWoasNoti.getGuid());
        params.put("operType",String.valueOf(type));
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersId());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities,new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                ToastUtils.show("提交成功");
                initData();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());
            }
        });



    }
}
