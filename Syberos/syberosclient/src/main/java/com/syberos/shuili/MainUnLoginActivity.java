package com.syberos.shuili;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.fragment.GateWayFragment;
import com.syberos.shuili.utils.SPUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/17.
 */

public class MainUnLoginActivity extends TranslucentActivity {
    @BindView(R.id.rg_tab)
    RadioGroup rg_tab;

    private BaseFragment gateWayFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        if(Boolean.valueOf(SPUtils.get(Allow_ScreenShot,false).toString())) {
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        rg_tab.setVisibility(View.GONE);
        switchFragment();
        if(gateWayFragment != null)
        {
            ((GateWayFragment)gateWayFragment).setView();
        }
    }
    public void switchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (gateWayFragment == null) {
            gateWayFragment = new GateWayFragment();
            transaction.add(R.id.container, gateWayFragment);
        } else {
            transaction.show(gateWayFragment);
        }
        transaction.commit();//提交事务
    }
}
