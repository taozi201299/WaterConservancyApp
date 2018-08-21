package com.syberos.shuili.activity.reports;

import android.annotation.SuppressLint;
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
import com.syberos.shuili.entity.report.ObjWoas;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;

/**
 * 行政端 工作考核报表 分为安全生产和水利稽查
 * 1 从8.2.1.16	工作考核通知对象表（OBJ_WOAS）中获取下发的所有考核通知
 * 2 分类 安全生产考核 和水利稽查考核
 * 3 发文单位 根通知guid为null 则无上报 为退回
 * 4 根通知guid不为null 有上报和撤回，可以查看其发送的单位的上报情况
 */

public class WoasReportActivity extends BaseActivity{

    @BindView(R.id.recyclerView_report_woas)
    RecyclerView recyclerView_report_woas;
    private final String TAG = WoasReportActivity.class.getSimpleName();
    private final String Title = "考核报表";
    private ObjWoas  objWoas = null;
    ListAdapter listAdapter ;
    @Override
    public int getLayoutId() {
        return R.layout.activity_job_rating_report;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListener() {
        listAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("objWoas",objWoas.dataSource.get(position));
                intentActivity(WoasReportActivity.this,WoasDetailActivity.class,false,bundle);
            }
        });
    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getWoasReportList();

    }

    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView_report_woas.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,R.layout.activity_history_patrol_list_item);
        recyclerView_report_woas.setAdapter(listAdapter);
    }
    private void getWoasReportList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/woas/selectDeployInformByMultiTable/";
        HashMap<String,String>params = new HashMap<>();
        params.put("orgGuid","D7862390F88443AE87FA9DD1FE45A8B6");
        params.put("sendStat","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objWoas = gson.fromJson(result,ObjWoas.class);
                if(objWoas != null && objWoas.dataSource != null && objWoas.dataSource.size() != 0 ){
                    refreshUI();
                }else {
                    ToastUtils.show("未获取到报表内容");
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show("未获取到报表内容");
            }
        });
    }
    private void refreshUI(){
        listAdapter.setData(objWoas.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    private class ListAdapter extends CommonAdapter<ObjWoas> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjWoas objWoas) {
            holder.getView(R.id.arrhpli_tv_title).setVisibility(View.GONE);
            ((TextView) holder.getView(R.id.arrhpli_tv_time)).setText(objWoas.getWoasThem());
            ((TextView)holder.getView(R.id.arrhpli_tv_person_label)).setText("考核时间:");
            ((TextView)holder.getView(R.id.arrhpli_tv_person)).setText(objWoas.getWOASSTARTIME() +"-"+objWoas.getWOASDEADLINE());
        }
}

}
