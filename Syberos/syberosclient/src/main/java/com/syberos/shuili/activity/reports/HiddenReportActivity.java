package com.syberos.shuili.activity.reports;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.publicentry.GroupInformationEntity;
import com.syberos.shuili.entity.report.BisOrgMonRepPeriForAdmin;
import com.syberos.shuili.listener.ItemClickedAlphaChangeListener;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


import butterknife.BindView;
import butterknife.OnClick;

public class HiddenReportActivity extends BaseActivity{

    @BindView(R.id.reportRecycleView)
    RecyclerView reportRecycleView;
    @BindView(R.id.tv_current_month)
    TextView tv_current_month;


    final String Tag = AcciReportActivity.class.getSimpleName();
    final String title = "事故报表";
    String header[] = {"本单位","直管单位","监管单位"};
    GroupedReportListAdapter groupedReportListAdapter;
    ArrayList<GroupInformationEntity<BisOrgMonRepPeriForAdmin>> mGroups = new ArrayList<>();

    @OnClick(R.id.tv_current_month)
    void onCurrentMonthClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_right)
    void onActionBarRightClicked() {
        onSelectMonthClicked();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_report_header_layout;
    }

    @Override
    public void initListener() {
        tv_current_month.setOnTouchListener(new ItemClickedAlphaChangeListener());
    }

    @Override
    public void initData() {
        mGroups.clear();
        getDirectUnit();
    }

    @Override
    public void initView() {
        setInitActionBar(false);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        reportRecycleView.setLayoutManager(layoutmanager);
        groupedReportListAdapter = new GroupedReportListAdapter(mContext,mGroups);
    }

    /**
     * 获取直属单位上报情况
     */

    /**
     * 获取下一级单位上报情况
     */

    private void getSubUnitReportList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/org/mon/rep/selectSubordinateMonthlyByTimeAndGuid/";
        HashMap<String,String>params = new HashMap<>();
        params.put("pguid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("repTime",tv_current_month.getText().toString());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                BisOrgMonRepPeriForAdmin bisOrgMonRepPeriForAdmin = gson.fromJson(result,BisOrgMonRepPeriForAdmin.class);
                if(bisOrgMonRepPeriForAdmin == null || bisOrgMonRepPeriForAdmin.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(bisOrgMonRepPeriForAdmin.dataSource.size() > 0){
                    mGroups.add(new GroupInformationEntity<>(header[2], (ArrayList<BisOrgMonRepPeriForAdmin>) bisOrgMonRepPeriForAdmin.dataSource));
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

    private void getDirectUnit(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/org/mon/rep/selectMonthlyReportListByTimeAndGuid/";
        HashMap<String,String>params = new HashMap<>();
        params.put("adminWiunGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("repTime",tv_current_month.getText().toString());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                BisOrgMonRepPeriForAdmin bisOrgMonRepPeriForAdmin = gson.fromJson(result,BisOrgMonRepPeriForAdmin.class);
                if(bisOrgMonRepPeriForAdmin == null || bisOrgMonRepPeriForAdmin.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(bisOrgMonRepPeriForAdmin.dataSource.size() > 0){
                    mGroups.add(new GroupInformationEntity<>(header[1], (ArrayList<BisOrgMonRepPeriForAdmin>) bisOrgMonRepPeriForAdmin.dataSource));
                }
                getSubUnitReportList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    private void refreshUI(){
        groupedReportListAdapter.setData(mGroups);
        reportRecycleView.setAdapter(groupedReportListAdapter);
        groupedReportListAdapter.notifyDataSetChanged();
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
                String time = Strings.formatDate(date);
                String[] arrayTime = time.split("-");
                tv_current_month.setText(arrayTime[0]+"年"+arrayTime[1]+"月");
                // TODO: 2018/4/10 处理时间设置之后的逻辑
                initData();
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    private class GroupedReportListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<GroupInformationEntity<BisOrgMonRepPeriForAdmin>> mGroups;

        public GroupedReportListAdapter(
                Context context, ArrayList<GroupInformationEntity<BisOrgMonRepPeriForAdmin>> groups) {
            super(context);
            mGroups = groups;
        }

        public void setData(ArrayList<GroupInformationEntity<BisOrgMonRepPeriForAdmin>> groups) {
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<BisOrgMonRepPeriForAdmin> children = mGroups.get(groupPosition).getChildren();
            return children == null ? 0 : children.size();
        }

        @Override
        public boolean hasHeader(int groupPosition) {
            return true;
        }

        /**
         * 返回false表示没有组尾
         *
         * @param groupPosition
         * @return
         */
        @Override
        public boolean hasFooter(int groupPosition) {
            return false;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @return
         */
        @Override
        public int getFooterLayout(int viewType) {
            return 0;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @param holder
         * @param groupPosition
         */
        @Override
        public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public int getHeaderLayout(int viewType) {
            return R.layout.adapter_header;
        }

        @Override
        public int getChildLayout(int viewType) {
            return R.layout.layout_sub_unit_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            holder.setText(R.id.tv_header, mGroups.get(groupPosition).getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            TextView tv_sub_unit = holder.get(R.id.tv_sub_unit);
            TextView tv_returned = holder.get(R.id.tv_returned); // 已退回
            TextView tv_repetition = holder.get(R.id.tv_repetition); // 重报
            TextView tv_rush = holder.get(R.id.tv_rush); // 催报
            TextView tv_revoke  = holder.get(R.id.tv_revoke); // 撤销
            TextView tv_report = holder.get(R.id.tv_report); //上报
            TextView tv_reported = holder.get(R.id.tv_reported); // 已上报
            tv_returned.setVisibility(View.GONE);
            tv_repetition.setVisibility(View.GONE);
            tv_rush.setVisibility(View.GONE);
            tv_revoke.setVisibility(View.GONE);
            tv_report.setVisibility(View.GONE);
            tv_reported.setVisibility(View.GONE);

            final BisOrgMonRepPeriForAdmin reportForAdmin
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            if(reportForAdmin.getStatus().equals("2")){
                if(true){
                    // /退回
                    tv_returned.setVisibility(View.VISIBLE);
                    if(groupPosition == 0){
                        tv_repetition.setVisibility(View.VISIBLE);
                    }else {
                        tv_rush.setVisibility(View.VISIBLE);
                    }
                }else{
                    // 未上报
                    if(groupPosition == 0){
                        tv_report.setVisibility(View.VISIBLE);
                    }else {
                        tv_rush.setVisibility(View.VISIBLE);
                    }
                }
            }
            else if(reportForAdmin.getStatus().equals("1")){
                tv_reported.setVisibility(View.VISIBLE);
                //  已上报
            }else if(reportForAdmin.getStatus().equals("3")){
                if(groupPosition == 0)
                    tv_repetition.setVisibility(View.VISIBLE);
                else {
                    tv_rush.setVisibility(View.VISIBLE);
                }
                // 已撤销 重报
            }else if(reportForAdmin.getStatus().equals("4")){
                // 申请撤销中
            }else if(reportForAdmin.getStatus().equals("5")){
                //同意撤销
                if(groupPosition == 0)
                    tv_repetition.setVisibility(View.VISIBLE);
                else {
                    tv_rush.setVisibility(View.VISIBLE);
                }
            }else if(reportForAdmin.getStatus().equals("6")){
                // 不同意撤销
            }else {
                tv_rush.setVisibility(View.VISIBLE);
            }
        }
    }
}