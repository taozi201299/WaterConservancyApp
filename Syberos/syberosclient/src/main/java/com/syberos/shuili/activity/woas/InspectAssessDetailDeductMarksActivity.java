package com.syberos.shuili.activity.woas;

import android.os.Bundle;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 考核组下所有考核对象的扣分记录 分组显示
 */
public class InspectAssessDetailDeductMarksActivity extends TranslucentActivity {

    private DeductMarksInfo info = null;

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
        return R.layout.activity_inspect_assess_detail_deduct_marks_detail;
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
                InspectAssessDetailActivity.SEND_BUNDLE_KEY_1);

        if (null != info) {
            tv_unit.setText(info.getWoasWiunGuid());
            tv_time.setText(info.getCollTime());
            tv_score.setText(String.valueOf(info.getFianDeuc()));

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setEditText(info.getDeucNote());

            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        }
    }
}
