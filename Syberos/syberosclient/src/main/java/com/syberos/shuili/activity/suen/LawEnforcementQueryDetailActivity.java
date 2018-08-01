package com.syberos.shuili.activity.suen;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.objCase.ObjLayer;
import com.syberos.shuili.utils.Strings;

import butterknife.BindView;

public class LawEnforcementQueryDetailActivity extends BaseActivity {

    private ObjLayer lawQueryInformation = null;
    @BindView(R.id.tv_html_text)
    TextView tv_html_text;

    private final String title = "法规详情";
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
        setActionBarRightVisible(View.INVISIBLE);
        showTitle(title);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        lawQueryInformation = (ObjLayer) bundle.getSerializable(
                LawEnforcementQueryActivity.SEND_BUNDLE_KEY);
        tv_html_text.setText(Html.fromHtml(lawQueryInformation.conSumm));
    }
}
