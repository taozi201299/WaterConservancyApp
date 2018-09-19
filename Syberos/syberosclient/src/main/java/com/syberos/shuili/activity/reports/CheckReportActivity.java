package com.syberos.shuili.activity.reports;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.report.ObjSins;
import com.syberos.shuili.entity.securitycheck.SecurityCheckInformation;
import com.syberos.shuili.listener.ItemClickedAlphaChangeListener;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 安全检查报表
 */
public class CheckReportActivity extends TranslucentActivity implements CommonAdapter.OnItemClickListener{

    private ListAdapter adapter;
    List<SecurityCheckInformation> infos = new ArrayList<>();
    public static final String SEND_BUNDLE_KEY = "SecurityCheckInformation";

    @BindView(R.id.pullRecylerView)
    RecyclerView pullRecyclerView;

    private ObjSins objSins ;

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
        return R.layout.activity_security_check_report;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListener() {
        tv_current_month.setOnTouchListener(new ItemClickedAlphaChangeListener());
        iv_action_right.setOnTouchListener(new ItemClickedAlphaChangeListener());
    }

    @Override
    public void initData() {
       showDataLoadingDialog();
        getObjSinsList();
    }

    private void getObjSinsList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/rel/sins/org/selectCheckDeploymentList/";
        HashMap<String,String>params = new HashMap<>();
        params.put("notIssuGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objSins = gson.fromJson(result,ObjSins.class);
                if(objSins == null ||objSins.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }else if(objSins.dataSource.size() == 0){
                    ToastUtils.show("未获取到报表任务");
                }else {
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
    @Override
    public void initView() {
        adapter = new ListAdapter(mContext, R.layout.layout_security_check_report_item,new ArrayList<ObjSins>());
        pullRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        pullRecyclerView.setAdapter(adapter);
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
        bundle.putSerializable("objSins", objSins.dataSource.get(position));
        intentActivity(this, CheckDetailActivity.class, false, bundle);
    }

    private void refreshUI(){
        adapter.setData(objSins.dataSource);
        adapter.notifyDataSetChanged();
    }
    private class ListAdapter extends CommonAdapter<ObjSins> {
        public ListAdapter(Context context, int layoutId, List<ObjSins> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, ObjSins objSins) {
            ((TextView)(holder.getView(R.id.tv_todo_work_time))).setText(objSins.getCheckTime());
            ((TextView)(holder.getView(R.id.tv_todo_work_title))).setText(objSins.getSinsDeplName());
            ((TextView)holder.getView(R.id.tv_check_value)).setText(objSins.getSinsRange());
        }
    }
}
