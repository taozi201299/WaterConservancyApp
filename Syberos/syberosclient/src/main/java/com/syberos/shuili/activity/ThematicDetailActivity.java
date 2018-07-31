package com.syberos.shuili.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BActivity;
import com.syberos.shuili.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.syberos.shuili.activity_accident_query.
 */
public class ThematicDetailActivity extends BActivity {
    @BindView(R.id.ibtn_back)
    ImageButton ibtnBack;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_thematic_detail_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        tvToolbarTitle.setText("hidden");

    }

    @OnClick(R.id.ibtn_back)
    public void backClicked() {
        finish();
    }
}
