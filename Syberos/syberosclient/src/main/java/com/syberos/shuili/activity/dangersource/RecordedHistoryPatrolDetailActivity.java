package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.entity.dangersource.InspectionPartolInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;

/**
 * 巡查记录详情
 */
public class RecordedHistoryPatrolDetailActivity extends BaseActivity {

    private final String TAG = RecordedHistoryPatrolDetailActivity.class.getSimpleName();

    private final String Title = "巡视记录详情";

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
        return R.layout.activity_recorded_history_patrol_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv_multimedia.cancel();
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        InspectionPartolInfo information
                = (InspectionPartolInfo)bundle.getSerializable("RecordedHistoryPatrolListActivity");

        if (null != information) {
            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
            tv_maybe.setText(information.getProbFound());
            tv_controls.setText(information.getTreaMeas());
            tv_time.setText(information.getCollTime());
            tv_reporter.setText(information.getPatPers());
        }
        BusinessConfig.getAttachMents(information.getHazGuid(),"BIS_HAZ_PAT_REC",mv_multimedia);
    }

    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
    }
}
