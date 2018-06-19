package com.syberos.shuili.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.listener.LoginEnvironmentVerifyListener;

import butterknife.BindView;

/**
 * Created by toby on 18-3-14.
 */

@SuppressLint("ValidFragment")
public class LoginEnvironmentVerifyFragment extends BaseFragment {

    @BindView(R.id.tv_login_environment_verify_content)
    TextView login_environment_verify_content;

    @BindView(R.id.tv_start_login_environment_verify)
    TextView start_login_environment_verify;

    private LoginEnvironmentVerifyListener listener = null;

    @SuppressLint("ValidFragment")
    public LoginEnvironmentVerifyFragment(LoginEnvironmentVerifyListener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_login_environment_verify;
    }

    @Override
    protected void initListener() {
        start_login_environment_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.callEnter();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    public void setLoginEnvironmentVerifyListener(LoginEnvironmentVerifyListener listener) {
        this.listener = listener;
    }
}
