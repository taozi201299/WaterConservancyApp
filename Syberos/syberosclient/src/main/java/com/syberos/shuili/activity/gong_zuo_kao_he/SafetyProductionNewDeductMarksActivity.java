package com.syberos.shuili.activity.gong_zuo_kao_he;

import android.os.Bundle;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.gong_zuo_kao_he.DeductMarksInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

public class SafetyProductionNewDeductMarksActivity extends TranslucentActivity {

    private DeductMarksInfo info = null;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    @BindView(R.id.ce_score)
    TextView ce_score;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;


    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @OnClick(R.id.tv_save)
    void onSubmitClicked() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_safety_production_new_deduct_marks;
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
        info = (DeductMarksInfo) bundle.getSerializable(
                SafetyProductionObjectSelectActivity.SEND_BUNDLE_KEY);

        if (null != info) {
            tv_action_bar_title.setText(info.getUnit());


        }
    }
}
