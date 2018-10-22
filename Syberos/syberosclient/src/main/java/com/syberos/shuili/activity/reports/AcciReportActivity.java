package com.syberos.shuili.activity.reports;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
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
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.report.BisAcciRecRep;
import com.syberos.shuili.entity.report.BisOrgMonRepPeri;
import com.syberos.shuili.entity.report.HiddenDangerReport;
import com.syberos.shuili.listener.ItemClickedAlphaChangeListener;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

// 行政端：隐患报表
/**
 * BIS_ORG_MON_REP_PERI 月报表上报期间表
 * 按照时间查找报表列表
 */
public class AcciReportActivity extends TranslucentActivity  {

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
    private BisAcciRecRep bisAcciRecRep = null;

    BisOrgMonRepPeri item = null;

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;

    @BindView(R.id.tv_current_month)
    TextView tv_current_month;

    @BindView(R.id.iv_action_right)
    LinearLayout iv_action_right;
    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

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

    private int iSucessCount = 0;
    private int iFailedCount = 0;

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
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getReortList();
    }

    /**
     * 根据上报单位获取本单位上报列表
     */
    private void getReortList(){
        String url= GlobleConstants.strIP + "/sjjk/v1/bis/org/mon/rep/hazy-bisOrgMonRepPeris/";
        HashMap<String,String>params = new HashMap<>();
        params.put("repOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("repTime",tv_current_month.getText().toString());
        params.put("repType","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisOrgMonRepPeri = gson.fromJson(result,BisOrgMonRepPeri.class);
                if(bisOrgMonRepPeri == null || bisOrgMonRepPeri.dataSource == null ){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                else if(bisOrgMonRepPeri.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show("没有上报内容");
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
    private  void getReportItemDetail(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/acci/rec/rep/bisAcciRecReps/";
        HashMap<String,String>params = new HashMap<>();
        ArrayList<BisOrgMonRepPeri> list = (ArrayList<BisOrgMonRepPeri>) bisOrgMonRepPeri.dataSource;
        final int size = list.size();
        BisOrgMonRepPeri item = null;
        for(int i = 0 ; i < size ; i++){
            if(iFailedCount > 0)break;
            item = list.get(i);
            params.put("repGuid",item.getGuid());
            final BisOrgMonRepPeri finalItem = item;
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {

                    Gson gson = new Gson();
                    bisAcciRecRep = gson.fromJson(result,BisAcciRecRep.class);
                    if(bisAcciRecRep == null || bisAcciRecRep.dataSource == null){
                        iFailedCount++;
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    iSucessCount ++ ;
                    if(bisAcciRecRep.dataSource.size() > 0) {
                        finalItem.setRepType(bisAcciRecRep.dataSource.get(0).getRepAct());
                        finalItem.setReportFinish(true);
                    }else {
                        finalItem.setReportFinish(false);
                    }
                    if(iSucessCount == size ){
                        refreshUI();
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }
    private void refreshUI(){
        closeDataDialog();
        listAdapter.setData(bisOrgMonRepPeri.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initView() {
        tv_action_bar_title.setText("事故报表");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_enterprises_hidden_danger_report_item);
        recyclerView.setAdapter(listAdapter);

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
        String currentTime = CommonUtils.getCurrentDate();
        String[]arrTime = currentTime.split("-");
        tv_current_month.setText(arrTime[0] +"年" +arrTime[1] +"月");
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
            tv_recall.setVisibility(View.GONE);
            tv_report.setVisibility(View.GONE);
            tv_recall.setVisibility(View.GONE);
            final int linkStatus = Integer.valueOf(hiddenDangerReport.getRepAct());

            // 2  未上报 本单位可以退回  1 已上报 2 被退回 3 已撤销
            switch (linkStatus) {
                case HiddenDangerReport.LINK_YES:
                    tv_refunded.setVisibility(View.GONE);
                    tv_report.setVisibility(View.GONE);
                    tv_refunded.setText("已上报");
                    tv_refunded.setVisibility(View.VISIBLE);
                    tv_recall.setVisibility(View.VISIBLE);
                    break;
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
                    if(hiddenDangerReport.isReportFinish()) {
                        tv_refunded.setVisibility(View.VISIBLE);
                        tv_refunded.setText("已退回");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color, null));
                        } else {
                            tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color));
                        }
                        tv_report.setVisibility(View.VISIBLE);
                        tv_report.setText("重报");
                    }else {
                        tv_recall.setVisibility(View.GONE);
                        tv_refunded.setVisibility(View.GONE);
                        tv_report.setVisibility(View.VISIBLE);
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
                        // 撤销和退回
                        case HiddenDangerReport.LINK_RETURNED:
                        case HiddenDangerReport.LINK_REFUNDED:
                            confirmDialog = new Dialog(AcciReportActivity.this);
                            View v1 = LayoutInflater.from(AcciReportActivity.this).inflate(
                                    R.layout.dialog_hidden_danger_report_confirm, null);
                            tv_confirmDialog_title = v1.findViewById(R.id.tv_title);
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
                            Button bt_cancel = v1.findViewById(R.id.btn_cancel);
                            bt_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                }
                            });
                            Button btn_confirm = v1.findViewById(R.id.btn_confirm);
                            btn_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    report(hiddenDangerReport);
                                    confirmDialog.dismiss();
                                }
                            });
                            confirmDialog.show();
                            break;
                    }
                }
            });
            tv_recall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (linkStatus) {
                        case HiddenDangerReport.LINK_YES:
                            item = hiddenDangerReport;
                            showCancleDialog(R.layout.dialog_accident_report_refunded_reason);
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
                String time = Strings.formatDate(date);
                String[] arrayTime = time.split("-");
                tv_current_month.setText(arrayTime[0]+"年"+arrayTime[1]+"月");
                // TODO: 2018/4/10 处理时间设置之后的逻辑
                getReortList();
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void report(BisOrgMonRepPeri bisOrgMonRepPeri){
        showDataLoadingDialog();
        String url = GlobleConstants.strZJIP + "/acci/acciDeal/save";
        HashMap<String,String>params = new HashMap<>();
        params.put("year",getYear());
        params.put("month",getMonth());
        params.put("orgName",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName());
        params.put("orgCode",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgCode());
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("requestFrom","0");
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
                closeDataDialog();
                ToastUtils.show("提交成功");
                initData();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private  void showCancleDialog(int resId){
        final Dialog shareDialog = new Dialog(AcciReportActivity.this);
        View v = LayoutInflater.from(AcciReportActivity.this).inflate(resId, null);
        shareDialog.setContentView(v);
        Window dialogWindow = shareDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        shareDialog.setCancelable(false);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp);
        Button bt_cancel = (Button)v.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button)v.findViewById(R.id.btn_confirm);
        final AudioEditView ae_describe_audio = v.findViewById(R.id.ae_describe_audio);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();

            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                cancelReport(item,ae_describe_audio.getEditText().toString());

            }
        });
        shareDialog.show();
    }
    private void cancelReport(BisOrgMonRepPeri bisOrgMonRepPeri,String content){
        showDataLoadingDialog();
        String url = GlobleConstants.strCJIP + "acci/acciMont/cancel";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisOrgMonRepPeri.getGuid());
        params.put("revocDesc",content);
        params.put("orgName",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName());
        params.put("orgCode",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgCode());
        params.put("requestFrom","0");

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
                closeDataDialog();
                ToastUtils.show("提交成功");
                initData();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private String getYear(){
        String time = tv_current_month.getText().toString();
        String[] arrayTime = time.split("年");
        if(arrayTime.length > 0){
            return  arrayTime[0];
        }
        return "";
    }
    private String getMonth(){
        String time = tv_current_month.getText().toString();
        time = time.replace("月","年");
        String[]arrayTime = time.split("年");
        if(arrayTime.length == 2){
            String month = arrayTime[1];
            if(month.startsWith("0")){
                month = month.replace("0","");
            }
            return  month;
        }
        return "";

    }
}
