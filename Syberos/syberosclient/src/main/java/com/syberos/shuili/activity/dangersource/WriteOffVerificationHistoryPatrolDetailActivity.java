package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.HistoryPatrolInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
/**
 * 该文件暂不使用
 */
public class WriteOffVerificationHistoryPatrolDetailActivity extends BaseActivity {

    private final String TAG = WriteOffVerificationHistoryPatrolDetailActivity.class.getSimpleName();

    private final String Title = "问题详情";

    @BindView(R.id.tv_maybe)
    TextView tv_maybe;
    @BindView(R.id.tv_controls)
    TextView tv_controls;
    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_reporter)
    TextView tv_reporter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_write_off_verification_history_patrol_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle(Title);

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        HistoryPatrolInformation information
                = (HistoryPatrolInformation)bundle.getSerializable(
                RecordReviewHistoryPatrolListActivity.SEND_BUNDLE_KEY);

        if (null != information) {
            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);


            tv_maybe.setText(information.getContent());
            tv_controls.setText(information.getControls());
            tv_time.setText(information.getTime());
            tv_reporter.setText(information.getReporter());
        }
    }
}
