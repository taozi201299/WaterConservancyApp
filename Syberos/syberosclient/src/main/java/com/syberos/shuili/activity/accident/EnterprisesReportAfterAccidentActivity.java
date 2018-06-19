package com.syberos.shuili.activity.accident;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;
import com.syberos.shuili.view.CustomDialog;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 补报
 * 文件暂不使用
 */

public class EnterprisesReportAfterAccidentActivity extends BaseActivity {

    private ObjAcci accidentInformation = null;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.ce_serious_injuries_count)
    ClearableEditText ce_serious_injuries_count;

    @BindView(R.id.ce_death_count)
    ClearableEditText ce_death_count;

    @BindView(R.id.ce_direct_economic_loss)
    ClearableEditText ce_direct_economic_loss;

    @BindView(R.id.aev_accident_description)
    AudioEditView aev_accident_description;

    @OnClick(R.id.rl_time)
    void onSelectTimeClicked() {
        //时间选择器
        boolean[] type = {true, true, true, true, true, true};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选时间不应大于系统当前时间");
                    return;
                }
                tv_time.setText(Strings.formatDatetime(date));
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @OnClick(R.id.tv_accident_report_after_commit)
    void onAccidentReportAfterCommitClicked() {
        if (tv_time.getText().toString().isEmpty()) {
            ToastUtils.show("提示：请设置补报时间");
            return;
        }

        final CustomDialog customDialog = new CustomDialog(
                EnterprisesReportAfterAccidentActivity.this);
        customDialog.setDialogMessage(null, null,
                null);
        customDialog.setMessage("确定补报事故？");
        customDialog.setOnConfirmClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (null != accidentInformation) {
                    accidentInformation.setOccuTime(tv_time.getText().toString());

                    int number = Integer.valueOf(
                            ce_serious_injuries_count.getText().toString().isEmpty() ?
                                    "0" : ce_serious_injuries_count.getText().toString());
                    accidentInformation.setSerInjNum(String.valueOf(number));

                    number = Integer.valueOf(
                            ce_death_count.getText().toString().isEmpty() ?
                                    "0" : ce_death_count.getText().toString());
                    accidentInformation.setCasNum(String.valueOf(number));

                    double price = Double.valueOf(
                            ce_direct_economic_loss.getText().toString().isEmpty() ?
                                    "0" : ce_direct_economic_loss.getText().toString());
                    accidentInformation.setEconLoss(String.valueOf(price));

                    accidentInformation.setAcciSitu(aev_accident_description.getEditText());

                    // TODO: 18-3-29 音频

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(EnterprisesExpressAccidentListActivity.SEND_BUNDLE_KEY,
                            accidentInformation);
                    Intent i = new Intent();
                    i.putExtras(bundle);
                    setResult(Activity.RESULT_OK, i);
                    activityFinish();

                    ToastUtils.show("TODO 事故补报上传");
                }

                customDialog.dismiss();
            }
        });
        customDialog.show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_report_after_accident;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle("事故补报");
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        ObjAcci accidentInformation = (ObjAcci)bundle.getSerializable(
                EnterprisesExpressAccidentListActivity.SEND_BUNDLE_KEY);
        if (null != accidentInformation) {
            this.accidentInformation = accidentInformation;

            ce_serious_injuries_count.setText(
                    String.valueOf(accidentInformation.getSerInjNum()));

            ce_death_count.setText(String.valueOf(accidentInformation.getCasNum()));

            ce_direct_economic_loss.setText(
                    String.valueOf(accidentInformation.getEconLoss()));

            aev_accident_description.setEditText(accidentInformation.getAcciSitu());

            // TODO: 18-3-29 添加音频附件
        }
    }
}
