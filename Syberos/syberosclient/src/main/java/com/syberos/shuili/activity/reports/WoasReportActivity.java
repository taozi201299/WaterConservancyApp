package com.syberos.shuili.activity.reports;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.securitycheck.SecurityCheckInformation;
import com.syberos.shuili.listener.ItemClickedAlphaChangeListener;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WoasReportActivity extends TranslucentActivity
        implements PullRecyclerView.OnPullRefreshListener, CommonAdapter.OnItemClickListener{
    private int pageIndex = 1;
    private ListAdapter adapter;
    List<SecurityCheckInformation> infos = new ArrayList<>();
    public static final String SEND_BUNDLE_KEY = "SecurityCheckInformation";

    @BindView(R.id.pullRecylerView)
    PullRecyclerView pullRecyclerView;

    @BindView(R.id.tv_current_month)
    TextView tv_current_month;

    @BindView(R.id.iv_action_right)
    LinearLayout iv_action_right;

    @OnClick(R.id.tv_current_month)
    void onCurrentMonthClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_right)
    void onActionBarRightClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_job_rating_report;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListener() {
        tv_current_month.setOnTouchListener(new ItemClickedAlphaChangeListener());
        iv_action_right.setOnTouchListener(new ItemClickedAlphaChangeListener());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        infos.clear();

        for (int i = 0; i < 10; ++i) {
            SecurityCheckInformation information = new SecurityCheckInformation();
            information.setTitle("北京市水利部署通知" + (i+1));
            information.setDate("2017-12-1" + i);
            infos.add(information);
        }

        adapter = new ListAdapter(mContext, R.layout.layout_security_check_report_item, infos);
        pullRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        pullRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        pullRecyclerView.setAdapter(adapter);
        pullRecyclerView.setOnPullRefreshListener(this);
        adapter.setOnItemClickListener(this);
    }

    private void onSelectMonthClicked() {
        //时间选择器
        boolean[] type = {true, true, false, false, false, false};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选月份不应大于系统当前月份");
                    return;
                }
                tv_current_month.setText(Strings.formatYearMonth(date));
                // TODO: 2018/4/10 处理时间设置之后的逻辑
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }


    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        SecurityCheckInformation information = infos.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);

        intentActivity(this, WoasDetailActivity.class, false, bundle);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<SecurityCheckInformation> {
        public ListAdapter(Context context, int layoutId, List<SecurityCheckInformation> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, SecurityCheckInformation information) {
            ((TextView)(holder.getView(R.id.tv_todo_work_time))).setText(information.getDate());
            ((TextView)(holder.getView(R.id.tv_todo_work_title))).setText(information.getTitle());
        }
    }
}
