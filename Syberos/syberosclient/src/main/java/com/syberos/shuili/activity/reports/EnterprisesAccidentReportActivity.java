package com.syberos.shuili.activity.reports;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.syberos.shuili.entity.bao_biao_guan_li.BisHiddRecRep;
import com.syberos.shuili.entity.bao_biao_guan_li.BisOrgMonRepPeri;
import com.syberos.shuili.entity.bao_biao_guan_li.HiddenDangerReport;
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
import okhttp3.Call;

// 企事业端：隐患报表
/**
 * BIS_ORG_MON_REP_PERI 月报表上报期间表
 * 按照时间查找报表列表
 */
public class EnterprisesAccidentReportActivity extends TranslucentActivity {

    private ListAdapter listAdapter = null;
    private Dialog reasonDialog;
    private Dialog confirmDialog;
    private TextView tv_reasonDialog_title;
    private TextView tv_reasonDialog_message;
    private TextView tv_confirmDialog_title;
    private ScrollView sv_reasonDialog_message_view;
    private String refundedReason = ""; // 被退回原因
    private String returnedReason = ""; // 退回原因

    private BisOrgMonRepPeri bisOrgMonRepPeri = null;
    private BisHiddRecRep bisHiddRecRep = null;

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;

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
        return R.layout.activity_enterprises_hidden_danger_report;
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

    /**
     * 根据上报单位获取本单位上报列表
     */
    private void getReortList(){
        String url= "http://192.168.1.8:8080/sjjk/v1/bis/org/mon/rep/hazy-bisOrgMonRepPeris/";
        HashMap<String,String>params = new HashMap<>();
        // params.put("repOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("repOrgGuid","F83199FDD35E49FF9643A6C394DBBF45");
        params.put("repTime",tv_current_month.getText().toString());
        params.put("repType","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisOrgMonRepPeri = gson.fromJson(result,BisOrgMonRepPeri.class);
                if(bisOrgMonRepPeri == null || bisOrgMonRepPeri.dataSource == null || bisOrgMonRepPeri.dataSource.size() == 0 ){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getReportItemDetail();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private  void getReportItemDetail(){
        String url = "";
        HashMap<String,String>params = new HashMap<>();
        ArrayList<BisOrgMonRepPeri> list = (ArrayList<BisOrgMonRepPeri>) bisOrgMonRepPeri.dataSource;
        final int size = list.size();
        BisOrgMonRepPeri item = null;
        for(int i = 0 ; i < size ; i++){
            item = list.get(i);
            params.put("repGuid",item.getGuid());
            final BisOrgMonRepPeri finalItem = item;
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisHiddRecRep = gson.fromJson(result,BisHiddRecRep.class);
                    if(bisHiddRecRep == null || bisHiddRecRep.dataSource == null){
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    if(bisHiddRecRep.dataSource.size() > 0) {
                        finalItem.setRepType(bisHiddRecRep.dataSource.get(0).getRepAct());
                        finalItem.setReportFinish(true);
                    }else {
                        finalItem.setReportFinish(false);
                    }
                    if(finalI == size -1){
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
        listAdapter.setData(bisOrgMonRepPeri.dataSource);
        listAdapter.notifyDataSetChanged();
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
                DividerItemDecoration.VERTICAL));

        refundedReason = "数据补全，请补充后重新上报数据补全" ;
        reasonDialog = new Dialog(this);
        View v = LayoutInflater.from(this).inflate(
                R.layout.dialog_hidden_danger_report_refunded_reason, null);
        tv_reasonDialog_title = (TextView) v.findViewById(R.id.tv_title);
        tv_reasonDialog_message = (TextView) v.findViewById(R.id.tv_content);
        sv_reasonDialog_message_view = (ScrollView) v.findViewById(R.id.message_view);
        reasonDialog.setContentView(v);
        Window dialogWindow = reasonDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        reasonDialog.setCancelable(false);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp);
        Button bt_cancel = (Button)v.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sv_reasonDialog_message_view.setFocusable(false);
                sv_reasonDialog_message_view.fullScroll(View.FOCUS_UP);
                reasonDialog.dismiss();
            }
        });
    }

    private class ListAdapter extends CommonAdapter<BisOrgMonRepPeri> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisOrgMonRepPeri hiddenDangerReport) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(hiddenDangerReport.getRepName());
            TextView tv_refunded = (TextView) holder.getView(R.id.tv_refunded);
            TextView tv_report = (TextView) holder.getView(R.id.tv_report);
            TextView tv_recall = (TextView) holder.getView(R.id.tv_recall);
            tv_report.setVisibility(View.GONE);
            tv_recall.setVisibility(View.GONE);
            final int linkStatus = Integer.valueOf(hiddenDangerReport.getRepAct());

            // 2  未上报 本单位可以退回  1 已上报 2 被退回 3 已撤销
            switch (linkStatus) {
                case HiddenDangerReport.LINK_YES:
                    tv_refunded.setVisibility(View.GONE);
                    tv_report.setVisibility(View.VISIBLE);
                    tv_report.setText("已上报");
                case HiddenDangerReport.LINK_RETURNED:
                    tv_report.setEnabled(false);
                    tv_refunded.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color, null));
                    } else {
                        tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color));
                    }
                    tv_refunded.setText("已撤销");
                    tv_report.setVisibility(View.VISIBLE);
                    tv_report.setText("重报");

                    break;
                case HiddenDangerReport.LINK_REFUNDED:
                    if(hiddenDangerReport.isReportFinish()){
                        tv_refunded.setVisibility(View.VISIBLE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv_refunded.setTextColor(getResources().getColor(R.color.refunded_link_text_color, null));
                        } else {
                            tv_refunded.setTextColor(getResources().getColor(R.color.refunded_link_text_color));
                        }
                        tv_refunded.setText("已退回");
                    }else {
                        tv_refunded.setVisibility(View.GONE);
                        tv_report.setText("上报");

                    }

                    break;
            }

            tv_refunded.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int linkStatus = Integer.valueOf(hiddenDangerReport.getRepAct());
                    switch (linkStatus) {
                        case HiddenDangerReport.LINK_RETURNED:
                            tv_reasonDialog_title.setText("退回原因");
                            tv_reasonDialog_message.setText(returnedReason);
                            reasonDialog.show();
                            break;
                        case HiddenDangerReport.LINK_REFUNDED:
                            if(hiddenDangerReport.isReportFinish()) {
                                tv_reasonDialog_title.setText("被退回原因");
                                tv_reasonDialog_message.setText(refundedReason);
                                reasonDialog.show();
                                break;
                            }
                    }
                }
            });
            tv_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (linkStatus) {
                        case HiddenDangerReport.LINK_RETURNED:
                            if(hiddenDangerReport.isReportFinish()) {
                                confirmDialog = new Dialog(EnterprisesAccidentReportActivity.this);
                                View v1 = LayoutInflater.from(EnterprisesAccidentReportActivity.this).inflate(
                                        R.layout.dialog_hidden_danger_report_confirm, null);
                                tv_confirmDialog_title = (TextView) v1.findViewById(R.id.tv_title);
                                tv_confirmDialog_title.setText("确认上报");
                                confirmDialog.setContentView(v1);
                                Window dialogWindow = confirmDialog.getWindow();
                                WindowManager.LayoutParams lp1 = dialogWindow.getAttributes();

                                lp1.width = WindowManager.LayoutParams.WRAP_CONTENT;
                                lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
                                lp1.gravity = Gravity.CENTER;
                                confirmDialog.setCancelable(false);
                                dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
                                dialogWindow.setAttributes(lp1);
                                Button bt_cancel = (Button) v1.findViewById(R.id.btn_cancel);
                                bt_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        confirmDialog.dismiss();
                                    }
                                });
                                Button btn_confirm = (Button) v1.findViewById(R.id.btn_confirm);
                                btn_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ToastUtils.show("TODO: 确认上报，后续处理逻辑");
                                        confirmDialog.dismiss();
                                    }
                                });

                                confirmDialog.show();
                            }
                            break;
                    }
                }
            });
        }
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
}
