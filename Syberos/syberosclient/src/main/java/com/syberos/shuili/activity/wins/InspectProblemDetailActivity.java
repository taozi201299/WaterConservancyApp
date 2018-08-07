package com.syberos.shuili.activity.wins;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.inspect.InspectProblemInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 稽查问题详情
 */
public class InspectProblemDetailActivity extends TranslucentActivity {

    private InspectProblemInformation problem;

    @BindView(R.id.tv_project)
    TextView tv_project;

    @BindView(R.id.tv_area)
    TextView tv_area;

    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.tv_department)
    TextView tv_department;

    @BindView(R.id.tv_severity)
    TextView tv_severity;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.ae_controls_audio)
    AudioEditView ae_controls_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_problem_detail;
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
        problem = (InspectProblemInformation)bundle.getSerializable(
                InspectDetailActivity.SEND_BUNDLE_KEY);

        if (null != problem) {
            tv_project.setText(problem.getProject());

            tv_type.setText(problem.getType());

            tv_department.setText(problem.getDepartment());

            switch (problem.getSeverity()) {
                case InspectProblemInformation.SEVERITY_NORMAL:
                    tv_severity.setText(R.string.severity_normal);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_normal, null));
                    } else {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_normal));
                    }
                    break;
                case InspectProblemInformation.SEVERITY_BIG:
                    tv_severity.setText(R.string.severity_big);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_big, null));
                    } else {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_big));
                    }
                    break;
                case InspectProblemInformation.SEVERITY_LARGE:
                    tv_severity.setText(R.string.severity_large);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_large, null));
                    } else {
                        tv_severity.setTextColor(getResources().getColor(R.color.color_inspect_type_large));
                    }
                    break;
            }


            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("问题描述");
            ae_describe_audio.setEditText("划拨维修经费5万元，对堤坝进行修整，" +
                    "确保水库安全运行，对损坏的堤坝进行修复，加固。");

            ae_controls_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_controls_audio.setLabelText("整改建议");
            ae_controls_audio.setEditText("划拨维修经费5万元，对堤坝进行修整，" +
                    "确保水库安全运行，对损坏的堤坝进行修复，加固。");

            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        }
    }
}
