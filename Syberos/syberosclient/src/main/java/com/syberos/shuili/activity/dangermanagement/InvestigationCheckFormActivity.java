package com.syberos.shuili.activity.dangermanagement;

import android.content.Intent;
import android.view.View;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-22.
 */

public class InvestigationCheckFormActivity extends BaseActivity {
    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_check_form;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle("隐患核实");
        setActionBarRightVisible(View.INVISIBLE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }
}
