package com.syberos.shuili.activity.reports;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.securitycheck.SecurityCheckInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomEdit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 工作考核报表
 */
public class WoasDetailActivity extends TranslucentActivity {

    private String refundedReason = ""; // 被退回原因
    private String returnedReason = ""; // 退回原因
    private Dialog reasonDialog;
    private Dialog confirmDialog;
    private TextView tv_reasonDialog_title;
    private TextView tv_reasonDialog_message;
    private TextView tv_confirmDialog_title;
    private ScrollView sv_reasonDialog_message_view;
    private SecurityCheckInformation information = null;

    @BindView(R.id.tv_self_unit)
    TextView tv_self_unit;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    @BindView(R.id.ll_directly_under_units)
    LinearLayout ll_directly_under_units;

    @BindView(R.id.ll_other_under_units)
    LinearLayout ll_other_under_units;

    @OnClick(R.id.tv_refunded)
    void onRefundedClicked() {
        tv_reasonDialog_title.setText("被退回原因");
        tv_reasonDialog_message.setText(refundedReason);
        reasonDialog.show();
    }

    @OnClick(R.id.tv_report)
    void onReportClicked() {
        confirmDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
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
        Button bt_cancel = (Button)v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("TODO: 确认上报，后续处理逻辑");
                confirmDialog.dismiss();
            }
        });

        confirmDialog.show();
    }

    @OnClick(R.id.tv_recall)
    void onRecallClicked() {
        confirmDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
                R.layout.dialog_hidden_danger_report_confirm, null);
        tv_confirmDialog_title = (TextView) v1.findViewById(R.id.tv_title);
        tv_confirmDialog_title.setText("确认撤回");
        confirmDialog.setContentView(v1);
        Window dialogWindow = confirmDialog.getWindow();
        WindowManager.LayoutParams lp1 = dialogWindow.getAttributes();

        lp1.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.gravity = Gravity.CENTER;
        confirmDialog.setCancelable(false);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp1);
        Button bt_cancel = (Button)v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("TODO: 确认撤回，后续处理逻辑");
                confirmDialog.dismiss();
            }
        });

        confirmDialog.show();
    }

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_job_rating_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (SecurityCheckInformation)bundle.getSerializable(
                CheckReportActivity.SEND_BUNDLE_KEY);

        if (null != information) {
            tv_action_bar_title.setText(String.format("安全检查报表|%s", information.getTitle()));
        }

        refundedReason = "0 数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报";

        returnedReason = "1 数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报" +
                "上报数据补全，请补充后重新上报数据补全，请补充后重新上报数据补全，请补充后重新上报";

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

        // TODO: 2018/4/10 可能需要一个用于唯一标识各个单位的标志量，用于标记分辨点击事件

        ll_directly_under_units.removeAllViews();
        addDirectlyUnderUnit("北京水利发展有限公司","001", "2017-12-12");
        addDirectlyUnderUnit("北京水利四方有限公司","002","2017-12-13");
        addDirectlyUnderUnit("北京水利方源科技有限公司","003","2017-12-14");

        ll_other_under_units.removeAllViews();
        addOtherUnderUnit("北京水利发展有限公司","004", "2017-12-12");
        addOtherUnderUnit("北京水利四方有限公司","005","2017-12-13");
        addOtherUnderUnit("北京水利方源科技有限公司","006","2017-12-14");
        addOtherUnderUnit("北京水利发展有限公司","007", "2017-12-12");
        addOtherUnderUnit("北京水利四方有限公司","008","2017-12-13");
        addOtherUnderUnit("北京水利方源科技有限公司","009","2017-12-14");
    }

    private void onUnitReturnedClicked(final String unitCode) {
        // TODO: 2018/4/10 获取退回原因
        tv_reasonDialog_title.setText("退回原因");
        tv_reasonDialog_message.setText(returnedReason);
        reasonDialog.show();
    }

    private void onUnitRepetitionClicked(final String unitCode) {
        final Dialog repetitionDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
                R.layout.dialog_hidden_danger_report_input_confirm, null);
        final CustomEdit customEdit = (CustomEdit) v1.findViewById(R.id.et_content);
        repetitionDialog.setContentView(v1);
        Window dialogWindow = repetitionDialog.getWindow();
        WindowManager.LayoutParams lp1 = dialogWindow.getAttributes();

        lp1.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.gravity = Gravity.CENTER;
        repetitionDialog.setCancelable(false);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp1);
        Button bt_cancel = (Button)v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repetitionDialog.dismiss();
            }
        });
        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("TODO: 退回重报，后续处理逻辑: " + customEdit.getText().toString());
                repetitionDialog.dismiss();
            }
        });

        repetitionDialog.show();
    }

    private void onUnitRushClicked(final String unitCode) {
        ToastUtils.show("单位编码： " + unitCode + "的催报选项被点击");
    }

    ////////////////////////////////////// addDirectlyUnderUnit ///////////////////////////////

    private void addDirectlyUnderUnit(final String subUnit, final String unitCode,
                                      final String date) {
        addDirectlyUnderUnit(subUnit, unitCode, date,true);
    }

    private void addDirectlyUnderUnit(final String subUnit, final String unitCode,
                                      final String date, final boolean isReturned) {
        addDirectlyUnderUnit(subUnit, unitCode, date, isReturned,true);
    }

    private void addDirectlyUnderUnit(final String subUnit, final String unitCode,
                                      final String date, final boolean isReturned,
                                      final boolean isRepetition) {
        addDirectlyUnderUnit(subUnit, unitCode, date, isReturned, isRepetition, true);
    }

    private void addDirectlyUnderUnit(final String subUnit, final String unitCode,
                                      final String date, final boolean isReturned,
                                      final boolean isRepetition, final boolean isRush) {

        View layout = LayoutInflater.from(this).inflate(R.layout.layout_sub_unit_item,
                ll_directly_under_units, false);

        TextView textView = (TextView) layout.findViewById(R.id.tv_sub_unit);
        textView.setText(subUnit);

        textView = (TextView) layout.findViewById(R.id.tv_date);
        textView.setText(date);

        textView = (TextView) layout.findViewById(R.id.tv_returned);
        textView.setVisibility(isReturned ? View.VISIBLE : View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnitReturnedClicked(unitCode);
            }
        });

        textView = (TextView) layout.findViewById(R.id.tv_repetition);
        textView.setVisibility(isRepetition ? View.VISIBLE : View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnitRepetitionClicked(unitCode);
            }
        });

        textView = (TextView) layout.findViewById(R.id.tv_rush);
        textView.setVisibility(isRush ? View.VISIBLE : View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnitRushClicked(unitCode);
            }
        });

        ll_directly_under_units.addView(layout);
    }

    ////////////////////////////////////// addDirectlyUnderUnit ///////////////////////////////

    ////////////////////////////////////// addOtherUnderUnit ///////////////////////////////

    private void addOtherUnderUnit(final String subUnit, final String unitCode,
                                   final String date) {
        addOtherUnderUnit(subUnit, unitCode, date,true);
    }

    private void addOtherUnderUnit(final String subUnit, final String unitCode,
                                   final String date, final boolean isReturned) {
        addOtherUnderUnit(subUnit, unitCode, date, isReturned,true);
    }

    private void addOtherUnderUnit(final String subUnit, final String unitCode,
                                   final String date, final boolean isReturned,
                                   final boolean isRepetition) {
        addOtherUnderUnit(subUnit, unitCode, date, isReturned, isRepetition, true);
    }

    private void addOtherUnderUnit(final String subUnit, final String unitCode,
                                   final String date, final boolean isReturned,
                                   final boolean isRepetition, final boolean isRush) {

        View layout = LayoutInflater.from(this).inflate(R.layout.layout_sub_unit_item,
                ll_other_under_units, false);

        TextView textView = (TextView) layout.findViewById(R.id.tv_sub_unit);
        textView.setText(subUnit);

        textView = (TextView) layout.findViewById(R.id.tv_date);
        textView.setText(date);

        textView = (TextView) layout.findViewById(R.id.tv_returned);
        textView.setVisibility(isReturned ? View.VISIBLE : View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnitReturnedClicked(unitCode);
            }
        });

        textView = (TextView) layout.findViewById(R.id.tv_repetition);
        textView.setVisibility(isRepetition ? View.VISIBLE : View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnitRepetitionClicked(unitCode);
            }
        });

        textView = (TextView) layout.findViewById(R.id.tv_rush);
        textView.setVisibility(isRush ? View.VISIBLE : View.GONE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUnitRushClicked(unitCode);
            }
        });

        ll_other_under_units.addView(layout);
    }

    ////////////////////////////////////// addOtherUnderUnit ///////////////////////////////

}
