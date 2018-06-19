package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 该文件暂不使用
 */
public class RecordedDetailActivity extends TranslucentActivity {

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.ae_identify_describe_audio)
    AudioEditView ae_identify_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    private DangerousInformation information = null;

    @OnClick(R.id.rl_write_off)
    void onWriteOffClicked() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(WriteOffVerificationListActivity.SEND_BUNDLE_KEY, information);

        intentActivity(this, RecordedRecordProcessActivity.class,false, bundle);
    }

    @OnClick(R.id.rl_record)
    void onRecordReviewProcessClicked() {
        intentActivity(this, RecordedWriteOffProcessActivity.class,
                false, true);
    }

    @OnClick(R.id.rl_patrol)
    void onRecordReviewPatrolClicked() {
        intentActivity(this, RecordedHistoryPatrolListActivity.class,
                false, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recorded_detail;
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
        information = (DangerousInformation) bundle.getSerializable(
                RecordReviewListActivity.SEND_BUNDLE_KEY);

        if (null != information) {
            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("危险源描述");
            ae_describe_audio.setEditText("核实描述核实描述核实描述核实描述" +
                    "核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述");
            ae_describe_audio.setAudio("111", 3);
            ae_describe_audio.setAudio("222", 4);

            ae_identify_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_identify_describe_audio.setLabelText("辨识评价描述");
            ae_identify_describe_audio.setEditText("核实描述核实描述核实描述核实描述" +
                    "核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述");
            ae_identify_describe_audio.setAudio("111", 3);
            ae_identify_describe_audio.setAudio("222", 4);
        }
    }
}
