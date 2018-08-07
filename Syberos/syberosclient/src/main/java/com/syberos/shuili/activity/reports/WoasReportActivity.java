package com.syberos.shuili.activity.reports;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.report.ReportBaseEntity;
import com.syberos.shuili.entity.securitycheck.SecurityCheckInformation;
import com.syberos.shuili.listener.ItemClickedAlphaChangeListener;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    private final String Title = "工作考核报表";
    HashMap<String,ArrayList<String>> groupMap = new HashMap<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_job_rating_report;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
    private class GroupedListAdapter extends GroupedRecyclerViewAdapter{

        private ArrayList<ReportBaseEntity<String>>mGroups;
        public GroupedListAdapter(
                Context context, ArrayList<ReportBaseEntity<String>> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<ReportBaseEntity<String>> groups){
            mGroups = groups;

        }
        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<String> children = mGroups.get(groupPosition).getChildren();
            return children == null ? 0 : children.size();
        }

        @Override
        public boolean hasHeader(int groupPosition) {
            return true;
        }

        @Override
        public boolean hasFooter(int groupPosition) {
            return false;
        }

        @Override
        public int getHeaderLayout(int viewType) {
            return 0;
        }

        @Override
        public int getFooterLayout(int viewType) {
            return 0;
        }

        @Override
        public int getChildLayout(int viewType) {
            return 0;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

        }
    }
}
