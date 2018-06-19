package com.syberos.shuili.activity.jian_du_zhi_fa;

import android.os.Bundle;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.LawQueryInformation;
import com.syberos.shuili.utils.Strings;

public class LawEnforcementQueryDetailActivity extends BaseActivity {

    private LawQueryInformation lawQueryInformation = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_law_enforcement_query_detail;
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
        lawQueryInformation = (LawQueryInformation) bundle.getSerializable(
                OnSiteLawEnforcementDetailActivity.SEND_BUNDLE_KEY);
    }
}
