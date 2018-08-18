package com.syberos.shuili;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.callback.FileCallback;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.activity.login.LoginActivity;
import com.syberos.shuili.activity.personalcenter.ChangePasswordActivity;
import com.syberos.shuili.activity.personalcenter.MapManActitity;
import com.syberos.shuili.activity.personalcenter.PersonalCenterActivity;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.AppUpdateEntity;
import com.syberos.shuili.fragment.AddressListFragment;
import com.syberos.shuili.fragment.GateWayFragment;
import com.syberos.shuili.fragment.HematicMapFragment;
import com.syberos.shuili.fragment.SecurityCloudFragment;
import com.syberos.shuili.fragment.WorkFragment;
import com.syberos.shuili.listener.OpenDrawerListener;
import com.syberos.shuili.service.update.UpdateManager;
import com.syberos.shuili.utils.LoginUtil;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.ScreenManager;
import com.syberos.shuili.utils.Singleton;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jidan on 18-3-10.
 */

public class MainActivity extends TranslucentActivity
        implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.v4_drawerlayout)
    DrawerLayout v4_drawerlayout;

    @BindView(R.id.rg_tab)
    RadioGroup rgTabs;
    @BindView(R.id.setting_menu)
    LinearLayout setting_menu;
    RelativeLayout rl_me_password;
    CheckBox cb_me_switcher_ring;
    CheckBox cb_screenshot_ring;

    RelativeLayout rl_me_update;
    RelativeLayout rl_me_clear;
    RelativeLayout rl_me_logout;
    RelativeLayout rl_map_down;
    RelativeLayout rl_me_message_ring;
    RelativeLayout rl_allow_screenshot;
    ConstraintLayout cl_me_myself;
    ImageView iv_me_red_pot;
    TextView tv_person_name;
    TextView tv_person_address;

    @BindView(R.id.btn_addressListFragment_enterprises)
    RadioButton btn_addressListFragment_enterprises;
    @BindView(R.id.btn_gateWayFragment_enterprises)
    RadioButton btn_gateWayFragment_enterprises;
    @BindView(R.id.btn_workFragment_enterprises)
    RadioButton btn_workFragment_enterprises;

    private final static String TAG = MainActivity.class.getSimpleName();

    private int checkId = R.id.btn_workFragment;

    private BaseFragment workFragment,
            securityCloudFragment, hematicMapFragment,
            addressListFragment,
            gateWayFragment;



    private OpenDrawerListener openDrawerListener = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        rgTabs.setOnCheckedChangeListener(this);
        rl_me_password.setOnClickListener(this);
        cb_me_switcher_ring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.put(Msg_Recv, true);
                } else {
                    SPUtils.put(Msg_Recv, false);
                }
                Singleton.INSTANCE.messageReceiveRemindSwitch(isChecked);
            }
        });
        cb_screenshot_ring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtils.put(Allow_ScreenShot,isChecked);
                if(isChecked) {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
                }else {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                }
            }

        });
        rl_me_update.setOnClickListener(this);
        rl_me_clear.setOnClickListener(this);
        rl_me_logout.setOnClickListener(this);
        rl_map_down.setOnClickListener(this);
        rl_me_message_ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_me_switcher_ring.setChecked(!cb_me_switcher_ring.isChecked());
            }
        });
        rl_allow_screenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_screenshot_ring.setChecked(!cb_screenshot_ring.isChecked());
            }
        });

        cl_me_myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentActivity(MainActivity.this, PersonalCenterActivity.class,
                        false, true);
                v4_drawerlayout.closeDrawer(Gravity.LEFT, false);
            }
        });
        v4_drawerlayout.addDrawerListener(mDrawerListener);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
//        securityCloudFragment = new SecurityCloudFragment();
        if(Boolean.valueOf(SPUtils.get(Allow_ScreenShot,false).toString())) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        btn_addressListFragment_enterprises.setVisibility(View.GONE);
        btn_gateWayFragment_enterprises.setVisibility(View.GONE);
        btn_workFragment_enterprises.setVisibility(View.GONE);
        setFinishOnBackKeyDown(false);

        // must before the switchFragment(checkId);
        openDrawerListener = new OpenDrawerListener() {
            @Override
            public void open() {
                v4_drawerlayout.openDrawer(Gravity.LEFT);
            }
        };
        switchFragment(checkId);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_setting_layout, null);
        rl_me_password = (RelativeLayout) view.findViewById(R.id.rl_me_password);
        cb_me_switcher_ring = (CheckBox) view.findViewById(R.id.cb_me_switcher_ring);
        cb_screenshot_ring = (CheckBox)view.findViewById(R.id.cb_screenshot_ring);
        rl_me_update = (RelativeLayout) view.findViewById(R.id.rl_me_update);
        rl_me_clear = (RelativeLayout) view.findViewById(R.id.rl_me_clear);
        rl_me_logout = (RelativeLayout) view.findViewById(R.id.rl_me_logout);
        rl_map_down = (RelativeLayout)view.findViewById(R.id.rl_map_down);
        rl_me_message_ring = (RelativeLayout) view.findViewById(R.id.rl_me_message_ring);
        rl_allow_screenshot = (RelativeLayout)view.findViewById(R.id.rl_allow_screenshot);
        cl_me_myself = (ConstraintLayout) view.findViewById(R.id.cl_me_myself);
        iv_me_red_pot = (ImageView)view.findViewById(R.id.iv_me_red_pot);
        tv_person_name = view.findViewById(R.id.tv_person_name);
        tv_person_name.setText(SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
        tv_person_address = view.findViewById(R.id.tv_person_address);
        tv_person_address.setText(SyberosManagerImpl.getInstance().getCurrentUserInfo().getPhone());
        if (Boolean.valueOf(SPUtils.get(Msg_Recv, true).toString())) {
            cb_me_switcher_ring.setChecked(true);
        } else {
            cb_me_switcher_ring.setChecked(false);
        }
        cb_screenshot_ring.setChecked(Boolean.valueOf(SPUtils.get(Allow_ScreenShot,false).toString()));
        setting_menu.addView(view);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switchFragment(group.getCheckedRadioButtonId());
    }

    public void switchFragment(int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        rgTabs.check(id);
        checkId = id;

        switch (id) {
            case R.id.btn_workFragment:
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                    workFragment.setOpenDrawerListener(openDrawerListener);
                    transaction.add(R.id.container, workFragment);
                } else {
                    transaction.show(workFragment);
                }
                break;
            case R.id.btn_securityCloudFragment:
                if (securityCloudFragment == null) {
                    long time1,time2;

                    time1=System.currentTimeMillis();
                    securityCloudFragment = new SecurityCloudFragment();
                    transaction.add(R.id.container, securityCloudFragment);
                    time2=System.currentTimeMillis();
                    Log.e(TAG, "switchFragment: time"+(time2-time1));
                } else {
                    transaction.show(securityCloudFragment);
                }
                break;
            case R.id.btn_hematicMapFragment:
                if (hematicMapFragment == null) {
                    hematicMapFragment = new HematicMapFragment();
                    transaction.add(R.id.container, hematicMapFragment);
                } else {
                    transaction.show(hematicMapFragment);
                }
                break;
            case R.id.btn_addressListFragment:
                if (addressListFragment == null) {
                    addressListFragment = new AddressListFragment();
                    transaction.add(R.id.container, addressListFragment);
                } else {
                    transaction.show(addressListFragment);
                }
                break;
            case R.id.btn_gateWayFragment:
                if (gateWayFragment == null) {
                    gateWayFragment = new GateWayFragment();
                    gateWayFragment.setOpenDrawerListener(openDrawerListener);
                    transaction.add(R.id.container, gateWayFragment);
                } else {
                    transaction.show(gateWayFragment);
                }
                break;

            default:
                break;
        }
        transaction.commit();//提交事务
    }

    /*
      * 隐藏所有的Fragment
      * */
    private void hideFragment(FragmentTransaction transaction) {
        if (workFragment != null) {
            transaction.hide(workFragment);
        }
        if (securityCloudFragment != null) {
            transaction.hide(securityCloudFragment);
        }
        if (hematicMapFragment != null) {
            transaction.hide(hematicMapFragment);
        }
        if (addressListFragment != null) {
            transaction.hide(addressListFragment);
        }
        if (gateWayFragment != null) {
            transaction.hide(gateWayFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_me_password:
                updatePwd();
                break;
            case R.id.rl_me_update:
                appUpdate();
                break;
            case R.id.rl_me_clear:
                clearCache();
                break;
            case R.id.rl_me_logout:
                logout(true);
                break;
            case R.id.rl_map_down:
                mapDownLoad();
                break;
        }
        v4_drawerlayout.closeDrawer(Gravity.LEFT, false);

    }

    private void mapDownLoad(){
        intentActivity(MainActivity.this,MapManActitity.class,false,true);
    }
    private void updatePwd(){
        intentActivity(MainActivity.this, ChangePasswordActivity.class,
                false, true);
    }
    private void appUpdate(){
        if(iv_me_red_pot.getVisibility() != View.VISIBLE){
            ToastUtils.show("当前为最新版本");
            return;
        }
        if(UpdateManager.isServiceRunning(this)){
            ToastUtils.show("正在下载新版本");
        }
        UpdateManager.showDialog(this,iv_me_red_pot);
    }
    private void clearCache(){
        final CustomDialog customDialog = new CustomDialog(this);
        customDialog.setDialogMessage("缓存管理", null, null);
        customDialog.setMessage("确认清空缓存数据？");
        customDialog.setOnConfirmClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SyberosManagerImpl.getInstance().clearCache();
                App.userType = "-1";
                SPUtils.put(GlobleConstants.Pwd,"");
                LoginUtil.setLastUserAccount("");
                LoginUtil.clearCache();
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }
    private void logout(final boolean bExist){
        final CustomDialog customDialog = new CustomDialog(this);
        customDialog.setDialogMessage("登录管理", null, null);
        if(bExist) {
            customDialog.setMessage("确认要退出登录吗？");
        }else {
            customDialog.setMessage("确认要退出应用吗？");
        }
        customDialog.setOnConfirmClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityFinish();
                ScreenManager.getScreenManager().popAll();
                if(bExist){
                    App.userType = "-1";
                    SPUtils.put(GlobleConstants.Pwd,"");
                    intentActivity(MainActivity.this, LoginActivity.class,
                            true, true);
                }
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }
    private DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {
            UpdateManager.initMenuData(new RequestCallback<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    AppUpdateEntity updateEntity = gson.fromJson(response, AppUpdateEntity.class);
                    if(updateEntity.isSuccess()){
                        ArrayList<AppUpdateEntity> datas =  (ArrayList<AppUpdateEntity>)updateEntity.dataSource.Tables.get(0).Datas;
                        String appinfo = datas.get(0).getAppinfo();
                        if(appinfo.equals("1")){
                            iv_me_red_pot.setVisibility(View.GONE);
                            UpdateManager.bUpdate = false;

                        }else {
                            iv_me_red_pot.setVisibility(View.VISIBLE);
                            UpdateManager.appUrl = appinfo;
                            UpdateManager.bUpdate = true;
                        }
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                   // ToastUtils.show(errorInfo.getMessage());

                }
            });

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            logout(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
