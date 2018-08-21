package com.syberos.shuili.activity.reports;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import com.syberos.shuili.entity.report.BisWoasNoti;
import com.syberos.shuili.entity.report.ObjWoas;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

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

    private ListAdapter listAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_hidden_danger_report;
    }

    @Override
    public void initListener() {

    }
    @Override
    public void initData() {
    }
    @Override
    public void initView() {
        showDataLoadingDialog();
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
        getWoasNoti();

    }
    private void getWoasNoti(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/noti/selectIncomingDispatchesUnitList/";
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
                getWoasNotiSelf();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getWoasNotiSelf(){
        if("0".equals(objWoas.getIsForw())){
            refreshUI();
        }else {
            String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/noti/selectIncomingDispatchesUnitList/";
            HashMap<String,String>params = new HashMap<>();
            params.put("woasGuid",objWoas.getGuid());
            params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeDataDialog();
                    Gson gson = new Gson();
                    BisWoasNoti item = gson.fromJson(result, BisWoasNoti.class);
                    if(item == null || item.dataSource == null){
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                    }else if(item.dataSource.size() == 1){
                        bisWoasNoti.dataSource.add(0,item.dataSource.get(0));
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
            TextView tv_refunded =  holder.getView(R.id.tv_refunded);
            TextView tv_report =  holder.getView(R.id.tv_report);
            TextView tv_recall =  holder.getView(R.id.tv_recall);
            tv_recall.setVisibility(View.GONE);
            tv_refunded.setVisibility(View.GONE);
            // 0 未上报 1 已上报
            final int linkStatus = Integer.valueOf(bisWoasNoti.getRepStat());
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
                    report(0);
                }
            });
            tv_recall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    report(1);
                }
            });
        }
    }

    private void report(int type){
        // 0 上报 1 申请撤回

    }
}
