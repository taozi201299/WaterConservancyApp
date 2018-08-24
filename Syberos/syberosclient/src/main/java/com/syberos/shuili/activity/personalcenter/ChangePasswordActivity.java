package com.syberos.shuili.activity.personalcenter;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.listener.TextChangedListener;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.et_input_original_password)
    ClearableEditText et_input_original_password;

    @BindView(R.id.et_input_new_password)
    ClearableEditText et_input_new_password;

    @BindView(R.id.et_repeat_new_password)
    ClearableEditText et_repeat_new_password;

    @BindView(R.id.tv_change_password_submit)
    TextView tv_change_password_submit;

    @BindView(R.id.cb_input_original_password)
    CheckBox cb_input_original_password;

    @BindView(R.id.cb_input_new_password)
    CheckBox cb_input_new_password;

    @BindView(R.id.cb_repeat_new_password)
    CheckBox cb_repeat_new_password;

    private UserExtendInformation userExtendInformation;

    @OnClick(R.id.tv_change_password_submit)
    void changePasswordSubmit() {
        if(checkPwd()) {
           changePassword();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    public void initListener() {
        TextChangedListener mTextChangedListener = new TextChangedListener() {
            @Override
            public void onTextChanged(String text) {
                updateSubmitEnabledStatus();
            }
        };

        et_input_original_password.setTextChangedListener(mTextChangedListener);
        et_input_new_password.setTextChangedListener(mTextChangedListener);
        et_repeat_new_password.setTextChangedListener(mTextChangedListener);

        cb_input_original_password.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        updatePasswordUI(et_input_original_password, !isChecked);
                    }
                });

        cb_input_new_password.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        updatePasswordUI(et_input_new_password, !isChecked);
                    }
                });

        cb_repeat_new_password.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        updatePasswordUI(et_repeat_new_password, !isChecked);
                    }
                });
    }

    @Override
    public void initData() {
        userExtendInformation = SyberosManagerImpl.getInstance().getCurrentUserInfo();

    }

    @Override
    public void initView() {
        setActionBarRightVisible(View.INVISIBLE);
        showTitle("修改密码");
        updateSubmitEnabledStatus();
    }

    private void updateSubmitEnabledStatus() {
        if (et_input_original_password.getText().length() > 0
                && et_input_new_password.getText().length() > 0
                && et_repeat_new_password.getText().length() > 0) {
            if (!tv_change_password_submit.isEnabled()) {
                tv_change_password_submit.setEnabled(true);
            }
        } else {
            if (tv_change_password_submit.isEnabled()) {
                tv_change_password_submit.setEnabled(false);
            }
        }
    }

    private void updatePasswordUI(final ClearableEditText passwordEdit,
                                  final boolean showPassword) {
        int currentPosition = passwordEdit.getSelectionStart();
        if (showPassword) {
            passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordEdit.setSelection(currentPosition);
    }

    private boolean checkPwd(){
        boolean bRet = false;
        String originalPwd = et_input_original_password.getText().toString();
        String newPwd = et_input_new_password.getText().toString();
        String repeatPwd = et_repeat_new_password.getText().toString();
        if(originalPwd.isEmpty()){
            ToastUtils.show("原密码不能为空");
            return bRet;
        }
        if(newPwd.isEmpty()){
            ToastUtils.show("新密码不能为空");
            return bRet;
        }
//        if(originalPwd.equals(newPwd)){
//            ToastUtils.show("原密码和新密码一致");
//            return bRet;
//        }
        if(repeatPwd.toString().isEmpty()){
            ToastUtils.show("确认密码不能为空");
            return bRet;
        }
        if(!newPwd.equals(repeatPwd)){
            ToastUtils.show("新密码和确认密码不一致");
            return bRet;
        }
        String strPwd = userExtendInformation.getPassword();
        if(!strPwd.equals(CommonUtils.encrypt(originalPwd))){
            ToastUtils.show("原密码错误");
            return bRet;
        }
        bRet = true;
        return bRet;
    }
    private void changePassword(){
        String methodName = "changePassword";
        HashMap<String,Object>params = new HashMap<>();

        params.put("arg0",SyberosManagerImpl.getInstance().getCurrentUserInfo().getUserCode());
        params.put("arg1",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPhone());
        params.put("arg2",CommonUtils.encrypt(et_input_new_password.getText().toString()));
        SyberosManagerImpl.getInstance().changePwd(params, methodName, new RequestCallback<Object>() {
            @Override
            public void onResponse(Object result) {
                ToastUtils.show("密码修改成功");

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
}
