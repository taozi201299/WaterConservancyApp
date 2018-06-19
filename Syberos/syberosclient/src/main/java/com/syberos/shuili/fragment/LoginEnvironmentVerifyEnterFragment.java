package com.syberos.shuili.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syberos.shuili.MainActivity;
import com.syberos.shuili.R;
import com.syberos.shuili.utils.Singleton;
import com.syberos.shuili.activity.login.SendSMSCodeCountDownTimer;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.listener.TextChangedListener;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginEnvironmentVerifyEnterFragment extends BaseFragment {
    private final static String TAG = "L_E_V_E_Fragment";

    @BindView(R.id.tv_ve_submit)
    TextView tv_ve_submit;

    @BindView(R.id.btn_ve_send_sms_code)
    Button btn_ve_send_sms_code;

    @BindView(R.id.ve_phoneNumberEdit)
    ClearableEditText ve_phoneNumberEdit;

    @BindView(R.id.ve_smsCodeEdit)
    ClearableEditText ve_smsCodeEdit;

    @OnClick(R.id.tv_ve_submit)
    void submitToVerifySmsCode() {
        String phoneNum = ve_phoneNumberEdit.getText().toString();
        if (Strings.isValidPhoneNum(phoneNum)) {
            if (Singleton.INSTANCE.smsCodeVerify(ve_smsCodeEdit.getText().toString())) {
                intentActivity(getActivity(), MainActivity.class, true, true);
                showVerifyResultToast(true);
            } else {
                showVerifyResultToast(false);
            }
        } else {
            ToastUtils.show(getResources().getText(R.string.invalid_phone_number));
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_login_environment_verify_enter;
    }

    @Override
    protected void initListener() {
        btn_ve_send_sms_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSVerifyCode();
            }
        });
        TextChangedListener mTextChangedListener = new TextChangedListener() {
            @Override
            public void onTextChanged(String text) {
                updateLoginButtonEnabledStatus();
            }
        };
        ve_phoneNumberEdit.setTextChangedListener(mTextChangedListener);
        ve_smsCodeEdit.setTextChangedListener(mTextChangedListener);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        updateLoginButtonEnabledStatus();
    }

    private void sendSMSVerifyCode() {
        String phoneNum = ve_phoneNumberEdit.getText().toString();
        if (Strings.isValidPhoneNum(phoneNum)) {
            SendSMSCodeCountDownTimer timer
                    = new SendSMSCodeCountDownTimer(btn_ve_send_sms_code, "重新获取");
            timer.start();
        } else {
            ToastUtils.show(getResources().getText(R.string.invalid_phone_number));
        }
    }

    private void updateLoginButtonEnabledStatus() {
        if (ve_phoneNumberEdit.getText().length() > 0
                && ve_smsCodeEdit.getText().length() > 0
                && ve_smsCodeEdit.getText().length() == Singleton.INSTANCE.SMS_VERIFY_CODE_LENGTH) {
            if (!tv_ve_submit.isEnabled()) {
                tv_ve_submit.setEnabled(true);
            }
        } else {
            if (tv_ve_submit.isEnabled()) {
                tv_ve_submit.setEnabled(false);
            }
        }
    }

    private void showVerifyResultToast(boolean success) {
        View layout = getLayoutInflater().inflate(R.layout.toast_sms_code_verify_result, null);

        ImageView resultIcon = layout.findViewById(R.id.toast_sms_code_verify_result_icon);
        resultIcon.setImageResource(success ?
                R.mipmap.icon_login_sms_verify_success : R.mipmap.icon_login_sms_verify_failed);

        TextView resultText = layout.findViewById(R.id.toast_sms_code_verify_result_text);
        resultText.setText(getResources().getText(success ?
                R.string.verify_success : R.string.verify_failed));

        ToastUtils.customViewShowCenter(layout);
    }
}
