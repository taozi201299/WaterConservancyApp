package com.syberos.shuili.fragment;

import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-10.
 */

public class SecurityCloudFragment extends BaseFragment {
    @BindView(R.id.textView)
    TextView tv_content;
    private final String TAG = SecurityCloudFragment.class.getSimpleName();
    @Override
    protected int getLayoutID() {
        return R.layout.simple_list_row;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tv_content.setText(TAG);

    }
}
