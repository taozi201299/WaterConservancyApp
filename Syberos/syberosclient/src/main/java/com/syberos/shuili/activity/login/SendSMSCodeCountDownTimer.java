package com.syberos.shuili.activity.login;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.syberos.shuili.utils.Singleton;

public class SendSMSCodeCountDownTimer extends CountDownTimer {

    public static final int TIME_COUNT = 60200;
    private TextView btn;
    private String endStrRid;
    private int normalColor, timingColor;//未计时的文字颜色，计时期间的文字颜色

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public SendSMSCodeCountDownTimer(long millisInFuture, long countDownInterval,
                                     TextView btn, String endStrRid) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    /**
     * 参数上面有注释
     */
    public SendSMSCodeCountDownTimer(TextView btn, String endStrRid) {
        super(Singleton.INSTANCE.sendSMSVerifyCodeLeftTime > 0 ?
                Singleton.INSTANCE.sendSMSVerifyCodeLeftTime : TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    public SendSMSCodeCountDownTimer(TextView tv_verify, String endStrRid,
                                     int normalColor, int timingColor) {
        this(tv_verify, endStrRid);
        this.normalColor = normalColor;
        this.timingColor = timingColor;
    }

    // 计时完毕时触发
    @Override
    public void onFinish() {
        if (normalColor > 0) {
            btn.setTextColor(normalColor);
        }
        Singleton.INSTANCE.sendSMSVerifyCodeLeftTime = 0;
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        if (timingColor > 0) {
            btn.setTextColor(timingColor);
        }
        btn.setEnabled(false);
        btn.setText(String.format("%ss", String.valueOf(millisUntilFinished / 1000)));
        Singleton.INSTANCE.sendSMSVerifyCodeLeftTime
                = millisUntilFinished < 1000 ? 0 : millisUntilFinished;
    }
}