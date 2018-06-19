package com.syberos.shuili.activity.searchproject;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.utils.Strings;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectDetailsActivity extends BaseActivity {

    public static final String RESULT_UNIT                 = "RESULT_UNIT";
    public static final String RESULT_PROJECT_NAME         = "RESULT_PROJECT_NAME";
    public static final String RESULT_PROJECT_CODE         = "RESULT_PROJECT_CODE";
    public static final String RESULT_PARENT_UNIT          = "RESULT_PARENT_UNIT";
    public static final String RESULT_PROJECT_STATUS       = "RESULT_PROJECT_STATUS";
    public static final String RESULT_PROJECT_START        = "RESULT_PROJECT_START";
    public static final String RESULT_PROJECT_END          = "RESULT_PROJECT_END";
    public static final String RESULT_PROJECT_PRICE        = "RESULT_PROJECT_PRICE";
    public static final String RESULT_PROJECT_IMPORTANCE   = "RESULT_PROJECT_IMPORTANCE";

    @BindView(R.id.tv_unit)
    TextView tv_unit;

    @BindView(R.id.tv_project_name)
    TextView tv_project_name;

    @BindView(R.id.tv_project_code)
    TextView tv_project_code;

    @BindView(R.id.tv_parent_unit)
    TextView tv_parent_unit;

    @BindView(R.id.tv_project_status)
    TextView tv_project_status;

    @BindView(R.id.tv_project_start)
    TextView tv_project_start;

    @BindView(R.id.tv_project_end)
    TextView tv_project_end;

    @BindView(R.id.tv_project_price)
    TextView tv_project_price;

    @BindView(R.id.tv_project_importance)
    TextView tv_project_importance;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    @OnClick(R.id.iv_action_bar_back)
    void go2back() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_qrcode_result_project_details;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setInitActionBar(false);

        tv_action_bar_title.setText("工程详情");
        tv_action_bar_title.setGravity(Gravity.LEFT);

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);

        if (null != bundle) {
            tv_unit.setText(bundle.getString(RESULT_UNIT));
            tv_project_name.setText(bundle.getString(RESULT_PROJECT_NAME));
            tv_project_code.setText(bundle.getString(RESULT_PROJECT_CODE));
            tv_parent_unit.setText(bundle.getString(RESULT_PARENT_UNIT));
            tv_project_status.setText(bundle.getString(RESULT_PROJECT_STATUS));
            tv_project_start.setText(bundle.getString(RESULT_PROJECT_START));
            tv_project_end.setText(bundle.getString(RESULT_PROJECT_END));
            tv_project_price.setText(bundle.getString(RESULT_PROJECT_PRICE));
            tv_project_importance.setText(bundle.getString(RESULT_PROJECT_IMPORTANCE));
        }
    }
}
