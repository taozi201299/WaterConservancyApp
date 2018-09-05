package com.syberos.shuili.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.syberos.shuili.MainActivity;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.ScreenManager;
import com.syberos.shuili.utils.StatusbarUtil;

import butterknife.ButterKnife;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.syberos.shuili.base.
 */
public abstract class BActivity extends AppCompatActivity {
    public Context mContext;
    // 是否允许全屏
    private boolean allowFullScreen = true;
    protected final static String Msg_Recv =  "MsgRecv";
    protected final static String Allow_ScreenShot = "AllowScreenShot";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isAllowFullScreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        mContext = this;
        ScreenManager.getScreenManager().pushActivity(this);
        if(Boolean.valueOf(SPUtils.get(Allow_ScreenShot,false).toString())) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        setContentView(getLayoutId());
        // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButterKnife.bind(this);
        StatusbarUtil.setStatusBar(this);
        initView();
        initListener();
        initData();
    }

    public abstract int getLayoutId();

    /**
     * 设置监听事件
     */
    public abstract void initListener();

    /**
     * 记载数据
     */
    public abstract void initData();

    public abstract void initView();

    public boolean isAllowFullScreen() {
        return allowFullScreen;
    }

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.allowFullScreen = allowFullScreen;
    }

}
