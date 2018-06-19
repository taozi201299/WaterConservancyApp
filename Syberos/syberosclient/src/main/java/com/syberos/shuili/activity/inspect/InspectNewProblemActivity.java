package com.syberos.shuili.activity.inspect;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.InspectProblemInformation;
import com.syberos.shuili.utils.Arrays2;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建稽查问题
 */
public class InspectNewProblemActivity extends TranslucentActivity {

    public static final String RESULT_KEY = "InspectProblemInformation";

    InspectProblemInformation information = null;

    private OptionsPickerView typePicker = null;
    private List<String> types;

    private OptionsPickerView departmentPicker = null;
    private List<String> departments;

    private OptionsPickerView severityPicker = null;
    private List<String> severities;

    @BindView(R.id.tv_project)
    TextView tv_project;

    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.tv_department)
    TextView tv_department;

    @BindView(R.id.tv_severity)
    TextView tv_severity;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @OnClick(R.id.ll_type)
    void onSelectTypeClicked() {
        typePicker.show();
    }

    @OnClick(R.id.ll_department)
    void onSelectDepartmentClicked() {
        departmentPicker.show();
    }

    @OnClick(R.id.ll_severity)
    void onSelectSeverityClicked() {
        severityPicker.show();
    }

    @OnClick(R.id.tv_save)
    void onSaveClicked(){
        Intent intent = new Intent();
        intent.putExtra(RESULT_KEY, information);
        setResult(RESULT_OK, intent);
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_new_problem;
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
        information = (InspectProblemInformation) bundle.getSerializable(
                InspectProjectSelectActivity.SEND_BUNDLE_KEY);

        if (null != information) {

            tv_project.setText(information.getProject());

            typePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    tv_type.setText(types.get(options1));
                    information.setType(types.get(options1));
                }
            })
                    .setTitleText("问题分类")
                    .isDialog(true)
                    .setOutSideCancelable(false)
                    .build();

            types = Arrays2.stringArrayToListString(getResources().getStringArray(
                    R.array.inspect_information_types));
            typePicker.setPicker(types);

            departmentPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    tv_department.setText(departments.get(options1));
                    information.setDepartment(departments.get(options1));
                }
            })
                    .setTitleText("对应司局")
                    .isDialog(true)
                    .setOutSideCancelable(false)
                    .build();

            departments = Arrays2.stringArrayToListString(getResources().getStringArray(
                    R.array.inspect_information_departments));
            departmentPicker.setPicker(departments);

            severityPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    information.setSeverity(options1);
                    switch (options1) {
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
                }
            })
                    .setTitleText("严重程度")
                    .isDialog(true)
                    .setOutSideCancelable(false)
                    .build();

            severities = Arrays2.stringArrayToListString(getResources().getStringArray(
                    R.array.inspect_information_severities));
            severityPicker.setPicker(severities);
        }
    }
}
