package com.syberos.shuili.activity.woas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.woas.InspectionObjectInfo;
import com.syberos.shuili.entity.woas.TrainingRecordInfo;
import com.syberos.shuili.utils.Strings;

import java.util.List;

import butterknife.BindView;

// 考前摸底

public class SafetyProductionDetailRealSituationActivity extends BaseActivity {

    private InspectionObjectInfo info = null;

    @BindView(R.id.tv_hidden_count)
    TextView tv_hidden_count;

    @BindView(R.id.tv_not_corrected)
    TextView tv_not_corrected;

    @BindView(R.id.tv_overdue)
    TextView tv_overdue;

    @BindView(R.id.tv_changing)
    TextView tv_changing;

    @BindView(R.id.tv_supervision)
    TextView tv_supervision;

    @BindView(R.id.tv_accident_normal)
    TextView tv_accident_normal;

    @BindView(R.id.tv_accident_big)
    TextView tv_accident_big;

    @BindView(R.id.tv_danger_source_count)
    TextView tv_danger_source_count;

    @BindView(R.id.tv_danger_source_record)
    TextView tv_danger_source_record;

    @BindView(R.id.ll_container_0)
    LinearLayout ll_container_0;

    @BindView(R.id.tv_score)
    TextView tv_score;


    @Override
    public int getLayoutId() {
        return R.layout.activity_safety_production_detail_real_situation;
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
        info = (InspectionObjectInfo) bundle.getSerializable(
                SafetyProductionDetailActivity.SEND_BUNDLE_KEY_0);

        if (null != info) {
            setActionBarTitle(info.getName());
            setActionBarRightVisible(View.INVISIBLE);

            // 重大隐患情况:数量
            tv_hidden_count.setText(String.valueOf(info.getHiddenCount()));
            // 重大隐患情况:未整改
            tv_not_corrected.setText(String.valueOf(info.getHiddenNotCorrected()));
            // 重大隐患情况:逾期未整改
            tv_overdue.setText(String.valueOf(info.getHiddenOverdue()));
            // 重大隐患情况:整改中
            tv_changing.setText(String.valueOf(info.getHiddenChanging()));
            // 重大隐患情况:隐患督办
            tv_supervision.setText(String.valueOf(info.getHiddenSupervision()));

            // 事故情况:一般事故
            tv_accident_normal.setText(String.valueOf(info.getAccidentNormal()));
            // 事故情况:特别重大事故
            tv_accident_big.setText(String.valueOf(info.getAccidentBig()));

            // 重大危险源情况:数量
            tv_danger_source_count.setText(String.valueOf(info.getDangerSourceCount()));
            // 重大危险源情况:备案数量
            tv_danger_source_record.setText(String.valueOf(info.getDangerSourceRecord()));

            // 培训记录
            List<TrainingRecordInfo> trainingRecordInfoList = info.getTrainingRecordInfoList();
            for (final TrainingRecordInfo recordInfo : trainingRecordInfoList) {
                View view = LayoutInflater.from(mContext).inflate(
                        R.layout.view_expandable_item, null);

                ((TextView) view.findViewById(R.id.tv_expand_name)).setText(recordInfo.getName());
                ((TextView) view.findViewById(R.id.tv_expand_time)).setText(recordInfo.getTime());

                ((TextView) view.findViewById(R.id.tv_child_0)).setText(recordInfo.getName());
                ((TextView) view.findViewById(R.id.tv_child_1)).setText(recordInfo.getCount());
                ((TextView) view.findViewById(R.id.tv_child_2)).setText(recordInfo.getType());
                ((TextView) view.findViewById(R.id.tv_child_3)).setText(recordInfo.getTime());
                ((TextView) view.findViewById(R.id.tv_child_4)).setText(recordInfo.getContent());

                ll_container_0.addView(view);
            }

            tv_score.setText(String.format("%s分", String.valueOf(info.getScore())));
        }
    }
}
