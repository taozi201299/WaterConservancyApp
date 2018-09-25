package com.syberos.shuili.activity.woas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.woas.BisWoasGrop;
import com.syberos.shuili.entity.woas.BisWoasObj;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 考核组下所有考核对象的扣分记录 分组显示
 */
public class SafetyProductionDetailDeductMarksActivity extends TranslucentActivity {

    private DeductMarksInfo info = null;
    private String objName ;


    @BindView(R.id.tv_unit)
    TextView tv_unit;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_score)
    TextView tv_score;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_safety_production_detail_deduct_marks_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        info = (DeductMarksInfo) bundle.getSerializable("deductMarksInfo");
        objName = bundle.getString("objName");

        if (null != info) {
            tv_unit.setText(objName);
            tv_time.setText(info.getCollTime());
            tv_score.setText(String.valueOf(info.getFianDeuc()));

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setEditText(String.valueOf(info.getDeucNote()));

            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        }else {
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
    }
}
