package com.syberos.shuili.utils;

import com.syberos.shuili.service.update.UpdateManager;

public enum Singleton {
    INSTANCE;

    public final int SMS_VERIFY_CODE_LENGTH = 6;
    public final int MAX_ATTACHMENT_NUMBER = 5;

    public boolean isShowPassword = false;
    public boolean isAccountLogin = true;

    public boolean isLogin = false;

    public long sendSMSVerifyCodeLeftTime = 0;

    public void messageReceiveRemindSwitch(boolean isRemind) {
        // TODO: 18-3-17 真实实现
    }

    public boolean passwordVerify(final String password) {
        // TODO: 18-3-17 真实实现
        if (!password.isEmpty()) {

        }
        return false;
    }

    public void changePassword(final String password) {
        // TODO: 18-3-17 真实实现
    }

    public boolean haveUpdate() {
           return !UpdateManager.appUrl.equals("");
    }

    public boolean smsCodeVerify(final String smsCode) {
        // TODO: 18-3-17 真实实现
        if (smsCode.isEmpty()){
            return false;
        }
        return true;
    }

}