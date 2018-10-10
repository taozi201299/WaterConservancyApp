package com.syberos.shuili.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.MainActivity;
import com.syberos.shuili.MainEnterpriseActivity;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.RoleBaseInfo;
import com.syberos.shuili.entity.userinfo.ModuleBean;
import com.syberos.shuili.utils.LoginUtil;
import com.syberos.shuili.utils.SPUtils;
import com.syberos.shuili.utils.Singleton;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.App;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.fragment.GateWayFragment;
import com.syberos.shuili.fragment.LoginEnvironmentVerifyEnterFragment;
import com.syberos.shuili.fragment.LoginEnvironmentVerifyFragment;
import com.syberos.shuili.listener.Back2LoginActivityListener;
import com.syberos.shuili.listener.LoginEnvironmentVerifyListener;
import com.syberos.shuili.listener.TextChangedListener;
import com.syberos.shuili.service.SyberosAidlClient;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;
import com.syberos.shuili.view.RelativePopupWindow.RelativePopupWindow;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.syberos.shuili.utils.CommonUtils.encrypt;

/**
 * 登录处理流程
 * 1 用户名 密码登录
 * 2 获取单位性质
 * 3 同步通讯录
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.tv_loginBtn)
    TextView tv_loginButton;

    @BindView(R.id.iv_passwordShow)
    CheckBox iv_passwordShow;

    @BindView(R.id.passwordEdit)
    ClearableEditText passwordEdit;

    @BindView(R.id.login_container)
    RelativeLayout login_container;

    @BindView(R.id.accountEdit)
    ClearableEditText accountEdit;

    @BindView(R.id.smsCodeEditLayout)
    RelativeLayout smsCodeLayout;

    @BindView(R.id.passwordEditLayout)
    RelativeLayout passwordEditLayout;

    @BindView(R.id.btn_send_sms_code)
    Button btn_send_sms_code;

    @BindView(R.id.tv_switch_login_method)
    TextView tv_switch_login_method;

    @BindView(R.id.phoneNumberEdit)
    ClearableEditText phoneNumberEdit;

    @BindView(R.id.smsCodeEdit)
    ClearableEditText smsCodeEdit;

    @BindView(R.id.iv_accountMore)
    ImageView iv_accountMore;

    @BindView(R.id.accountEditLayout)
    RelativeLayout accountEditLayout;

    @BindView(R.id.iv_phoneNumberMore)
    ImageView iv_phoneNumberMore;

    @BindView(R.id.phoneNumberEditLayout)
    RelativeLayout phoneNumberEditLayout;

    private GateWayFragment gateWayFragment = null;
    private Fragment loginEnvironmentVerifyFragment = null;
    private Fragment loginEnvironmentVerifyEnterFragment = null;
    private LoginRecordSelectPopup popup = null;
    private static final int POPUP_VER_POS = RelativePopupWindow.VerticalPosition.BELOW;
    private static final int POPUP_HOR_POS = RelativePopupWindow.HorizontalPosition.ALIGN_RIGHT;

    private BaseAdapter loginRecordInfoBaseAdapter = null;
    private List<String> loginRecordsList;

    private Handler mHandler = new Handler();

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setInitActionBar(false);
        super.onCreate(savedInstanceState);
        SyberosManagerImpl.init(this);
        SyberosAidlClient.init(this);
        if(!SPUtils.get(GlobleConstants.Pwd,"").toString().isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                accountEdit.setText(LoginUtil.getLastUserAccount());
                                passwordEdit.setText(SPUtils.get(GlobleConstants.Pwd,"").toString());
                                login(true);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initListener() {
        TextChangedListener mTextChangedListener = new TextChangedListener() {
            @Override
            public void onTextChanged(String text) {
                updateLoginButtonEnabledStatus();
            }
        };
        accountEdit.setTextChangedListener(mTextChangedListener);
        passwordEdit.setTextChangedListener(mTextChangedListener);
        phoneNumberEdit.setTextChangedListener(mTextChangedListener);
        smsCodeEdit.setTextChangedListener(mTextChangedListener);
        iv_passwordShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updatePasswordUI(!isChecked);
            }
        });
    }

    @Override
    public void initData() {
//        accountEdit.setText("235407195106112745");
//        passwordEdit.setText("112745");
//        accountEdit.setText("ceshi321");
//        passwordEdit.setText("123456");
        updateLoginButtonEnabledStatus();
    }

    @Override
    public void initView() {
        setStatusBar(R.color.white);

        // 初始化密码显示设置
        updatePasswordUI(Singleton.INSTANCE.isShowPassword);
        switchSMSAndAccountLogin(Singleton.INSTANCE.isAccountLogin);

        popup = new LoginRecordSelectPopup(LoginActivity.this);

        loginRecordInfoBaseAdapter = new LoginRecordInfoAdapter();

    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.tv_gate_way:
                showGateWayFragment();
                break;
            case R.id.iv_face_login:
                ToastUtils.show("请求使用人脸识别登录功能");
                break;
            case R.id.iv_accountMore:
                popup.setAdapter(loginRecordInfoBaseAdapter);
                popup.setWidth(accountEditLayout.getWidth());
                popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popup.showOnAnchor(accountEditLayout, POPUP_VER_POS, POPUP_HOR_POS, true);
                break;
            case R.id.iv_phoneNumberMore:
                popup.setAdapter(loginRecordInfoBaseAdapter);
                popup.setWidth(phoneNumberEditLayout.getWidth());
                popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popup.showOnAnchor(phoneNumberEditLayout, POPUP_VER_POS, POPUP_HOR_POS, true);
                break;
            case R.id.iv_passwordShow: // 显示隐藏密码内容
                updatePasswordUI(!Singleton.INSTANCE.isShowPassword);
                break;
            case R.id.tv_forget_password:
                showLoginEnvironmentVerifyFragment();
                break;
            case R.id.tv_loginBtn:
                if (verifyLoginInfo()) {
                    login(false);
                }
                break;
            case R.id.tv_switch_login_method:
                switchSMSAndAccountLogin(!Singleton.INSTANCE.isAccountLogin);
                break;
            case R.id.iv_btn_help:
                new AlertDialog.Builder(LoginActivity.this).setTitle(R.string.tip).setMessage("账号通过水行政单位配置获得，请向水行政部门申请账号。").setNegativeButton(getText(R.string.OK), null).setCancelable(true).show();
                break;
            case R.id.btn_send_sms_code:
                sendSMSVerifyCode();
                break;
        }
    }

    /**
     * 1 登录用户如何判断是行政用户还是企事业用户
     * 2 用户单位类型
     * 大中型已建工程运行管理单位1CJYJ  大中型在建工程项目法人	2 小型工程管理单位和技术服务单位	3
     * 3 是否有角色的区分
     */
    private void login(boolean bAuto) {
        showLoadingDialog("正在登录中...");
        final String methodName = "isUamsValidPhoneUserByPhoneOrCodeOrName";
        final HashMap<String, Object> params = new HashMap<>();
        params.put("arg0", accountEdit.getText().toString());
        if(bAuto){
            params.put("arg1",passwordEdit.getText().toString());
        }else {
            params.put("arg1", encrypt(passwordEdit.getText().toString()));
        }
        SyberosManagerImpl.getInstance().login(params, methodName, new RequestCallback<Object>() {
            @Override
            public void onResponse(Object result) {
                UserExtendInformation userExtendInformation = null;
                try {
                    userExtendInformation = parseLoginResult(result);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (userExtendInformation == null) {
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-3).getMessage());
                    return;
                }
                SyberosManagerImpl.getInstance().setCurrentUserInfo(userExtendInformation);
                if (checkUserPermission(userExtendInformation)) {
                    SPUtils.put(GlobleConstants.Pwd, userExtendInformation.getPassword());
                    LoginUtil.setLastUserAccount(accountEdit.getText().toString());
                    // TODO: 2018/8/1 企事业用户需要获取功能模块权限
                    /**
                     * 行政用户直接进行登录
                     */
                    if (GlobleConstants.CJFR.equalsIgnoreCase(App.sCode) || GlobleConstants.CJFW.equalsIgnoreCase(App.sCode) || GlobleConstants.CJJL.equalsIgnoreCase(App.sCode) || GlobleConstants.CJSG.equalsIgnoreCase(App.sCode) || GlobleConstants.CJYJ.equalsIgnoreCase(App.sCode)) {
                        getSysCode(SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersId());
                    }else {
                        go2Activity();
                        syncAddressList();
                    }

                } else {
                    ToastUtils.show("该用户无权限使用本系统");
                    closeDataDialog();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private boolean checkUserPermission(UserExtendInformation info) {
        boolean bRet = false;
        ArrayList<RoleBaseInfo> roleList = info.getRoleExtInfoList();
        if (roleList == null || roleList.size() == 0) {
            return bRet;
        }
        /**
         * 1 大中型已建工程运行管理单位 CJYJ
         * 2 大中型在建工程项目法人 CJFR
         * 3 小型工程管理单位和技术服务单位CJFW
         * 4 大中型在建工程施工单位  CJSG
         * 5 大中型在建工程监理单位 CJJL
         */
        for (RoleBaseInfo roleInfo : roleList) {
            if (GlobleConstants.CJYJ.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.CJFR.equalsIgnoreCase(roleInfo.getScode())
                    || GlobleConstants.CJFW.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.CJSG.equalsIgnoreCase(roleInfo.getScode())
                    || GlobleConstants.CJJL.equalsIgnoreCase(roleInfo.getScode())) {
                if(App.sCode.isEmpty()) {
                    App.sCode = roleInfo.getScode();
                    bRet = true;
                }
            } else if (GlobleConstants.acci.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.sins.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.stan.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.maha.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.woas.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.suen.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.wins.equalsIgnoreCase(roleInfo.getScode()) || GlobleConstants.hidd.equalsIgnoreCase(roleInfo.getScode())) {
                App.sCodes.add(roleInfo.getScode());
                bRet = true;
            }
            if(roleInfo.getRoleCode()!= null && !roleInfo.getRoleCode().isEmpty()){
                String roleCode = roleInfo.getRoleCode();
                if(GlobleConstants.CJFR002.equalsIgnoreCase(roleCode) || GlobleConstants.CJFR005.equalsIgnoreCase(roleCode)
                        || GlobleConstants.CJYJ002.equalsIgnoreCase(roleCode) ||GlobleConstants.CJJL005.equalsIgnoreCase(roleCode)
                        || GlobleConstants.CJFW002.equalsIgnoreCase(roleCode) ||GlobleConstants.CJFW005.equalsIgnoreCase(roleCode)
                        || GlobleConstants.CJSG002.equalsIgnoreCase(roleCode) || GlobleConstants.CJSG005.equalsIgnoreCase(roleCode)
                        || GlobleConstants.CJJL002.equalsIgnoreCase(roleCode) || GlobleConstants.CJJL.equalsIgnoreCase(roleCode)){
                    App.roleCode = roleCode;
                }
            }
        }
        return bRet;
    }

    private UserExtendInformation parseLoginResult(Object result) throws ParseException {
        HashMap<String, String> info = new HashMap<>();
        List<HashMap<String, String>> infoList = new ArrayList<>();
        String reponse = result.toString();
        reponse = reponse.replace(" ", "");
        reponse = reponse.replace("\n", "");
        if (!reponse.contains("userPassword")) return null;
        String[] array = reponse.split("roleInfoList=anyType");
        String[] childArray = {"0"};
        int size = array.length;
        for (int i = 0; i < size; i++) {
            if (array[i].contains("}")) {
                childArray = array[i].split("\\}");
            } else childArray[0] = array[i];
            if (childArray.length == 1) {
                info.putAll(setUserInfo(childArray[0].replace("anyType{", "")));
            } else if (childArray.length == 2) {
                HashMap<String, String> map = setUserInfo(childArray[0].replace("{", ""));
                infoList.add(map);
                if (childArray[1].length() > 1) {
                    info.putAll(setUserInfo(childArray[1]));
                }
            }
        }
        ArrayList<RoleBaseInfo> roleList = setRoleList(infoList);
        UserExtendInformation userExtendInformation = setUserExtendInfo(info);
        userExtendInformation.setRoleExtInfoList(roleList);
        App.setUserType(Integer.valueOf(info.get("isWaterIndustry")));
        LoginUtil.setRoleList(roleList);
        return userExtendInformation;

    }

    private UserExtendInformation setUserExtendInfo(HashMap<String, String> info) {
        String depId = info.get("depId") == null ? "" : info.get("depId").toString();
        String depName = info.get("depName") == null ? "" : info.get("depName").toString();
        String id = info.get("id") == null ? "" : info.get("id").toString();
        String orgCode = info.get("orgCode") == null ? "" : info.get("orgCode").toString();
        String orgId = info.get("orgId") == null ? "" : info.get("orgId").toString();
        String orgName = info.get("orgName") == null ? "" : info.get("orgName").toString();
        String userPassword = info.get("userPassword") == null ? "" : info.get("userPassword");
        String persId = info.get("persId") == null ? "" : info.get("persId");
        String persName = info.get("persName") == null ? "" : info.get("persName");
        String mobilenumb = info.get("mobilenumb") == null ? "" : info.get("mobilenumb");
        String status = info.get("status") == null ? "" : info.get("status");
        String userCode = info.get("userCode") == null ? "" : info.get("userCode");
        String userName = info.get("userName") == null ? "" : info.get("userName");
        App.jurdAreaType = info.get("jurdAreaType") == null ? "" : info.get("jurdAreaType");
        App.orgJurd = info.get("orgJurd") == null ? "" : info.get("orgJurd");

        UserExtendInformation userExtendInformation = new UserExtendInformation("", "", depId, depName, id, "", "", orgCode, orgId, orgName, userPassword, persId, persName, "", mobilenumb, null, status, "", userCode, userName, "");
        return userExtendInformation;
    }

    private ArrayList setRoleList(List<HashMap<String, String>> infoList) {
        ArrayList<RoleBaseInfo> roleList = new ArrayList<>();
        for (HashMap map : infoList) {
            RoleBaseInfo roleBaseInfo = new RoleBaseInfo();
            String roleId = map.get("roleId") == null ? "" : map.get("roleId").toString();
            String roleCode = map.get("roleCode") == null ? "" : map.get("roleCode").toString();
            String orgCode = map.get("orgCode") == null ? "" : map.get("orgCode").toString();
            String orgName = map.get("orgName") == null ? "" : map.get("orgName").toString();
            String roleName = map.get("roleName") == null ? "" : map.get("roleName").toString();
            String roleDesc = map.get("roleDesc") == null ? "" : map.get("roleDesc").toString();
            String roleType = map.get("roleType") == null ? "" : map.get("roleType").toString();
            String Scode = map.get("scode") == null ? "" : map.get("scode").toString();
            String sname = map.get("sname") == null ? "" : map.get("sname").toString();
            String ts = map.get("ts") == null ? "" : map.get("ts").toString();
            String note = map.get("Note") == null ? "" : map.get("Note").toString();
            String status = map.get("status") == null ? "" : map.get("status").toString();
            String modifier = map.get("modifier") == null ? "" : map.get("modifier").toString();
            String orgJurd = map.get("orgJurd") == null ? "" : map.get("orgJurd").toString();
            String jurdAreaType = map.get("jurdAreaType") == null ? "" : map.get("jurdAreaType").toString();
            RoleBaseInfo roleExtInfo = new RoleBaseInfo(roleId, roleCode, orgCode, orgName, roleName, roleDesc, roleType, Scode, sname, ts, note, status, modifier, orgJurd, jurdAreaType);
            roleList.add(roleExtInfo);
        }
        return roleList;
    }

    private HashMap<String, String> setUserInfo(String result) {
        HashMap<String, String> info = new HashMap<>();
        if (result == null) return null;
        String[] array = result.split(";");
        for (int i = 0; i < array.length; i++) {
            String[] childArray = array[i].split("=");
            if (childArray != null && childArray.length >= 2) {
                info.put(childArray[0], childArray[1]);
            }
        }
        return info;

    }

    private void syncAddressList() {
        String lastUser = LoginUtil.getLastUserAccount();
        String userId = SyberosManagerImpl.getInstance().getCurrentUserId();
        UserExtendInformation info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        if (!lastUser.equals(userId)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("arg0", info.getOrgId());
            SyberosManagerImpl.getInstance().syncAddressList(map, new SyberosManagerImpl.ResultCallback<List<UserExtendInformation>>() {
                @Override
                public void onSuccess(List<UserExtendInformation> var1) {
                    Singleton.INSTANCE.isLogin = true;
                }

                @Override
                public void onError(SyberosManagerImpl.ErrorCode var1) {
                    ToastUtils.show(var1.getMessage());
                }
            });
        }

    }

    /**
     * 获取orgClienType 单位类型
     */
    private void getSysCode(String perID) {
        String url = GlobleConstants.strCJIP +"/cjapi/cj/yuanXin/collresobase/getCollByPersIdAndSysCode";
        HashMap<String,String>params = new HashMap<>();
        params.put("personID",perID);
        params.put("app_guid",App.sCode);
        params.put("appCode",App.roleCode);
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                GlobleConstants.moduleBean = gson.fromJson(result, ModuleBean.class);
                go2Activity();
                syncAddressList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                go2Activity();
                syncAddressList();
            }
        });
    }

    private void go2Activity() {
        closeDialog();
        if (GlobleConstants.CJFR.equalsIgnoreCase(App.sCode) || GlobleConstants.CJFW.equalsIgnoreCase(App.sCode) || GlobleConstants.CJJL.equalsIgnoreCase(App.sCode) || GlobleConstants.CJSG.equalsIgnoreCase(App.sCode) || GlobleConstants.CJYJ.equalsIgnoreCase(App.sCode)) {
            intentActivity(LoginActivity.this, MainEnterpriseActivity.class, false, false);
            App.userType = "0";
        } else if (App.sCodes.contains(GlobleConstants.acci) || App.sCodes.contains(GlobleConstants.sins) || App.sCodes.contains(GlobleConstants.stan) || App.sCodes.contains(GlobleConstants.maha) || App.sCodes.contains(GlobleConstants.woas) || App.sCodes.contains(GlobleConstants.suen) || App.sCodes.contains(GlobleConstants.wins) || App.sCodes.contains(GlobleConstants.hidd)) {
            intentActivity(LoginActivity.this, MainActivity.class, false, true);
            App.userType = "1";
        }
        finish();
    }

    private void showGateWayFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        login_container.setVisibility(View.GONE);
        if (null == gateWayFragment) {
            gateWayFragment = new GateWayFragment();
            gateWayFragment.setBack2LoginActivityListener(new Back2LoginActivityListener() {
                @Override
                public void back() {
                    login_container.setVisibility(View.VISIBLE);
                    FrameLayout fl = findViewById(R.id.frame_container);
                    if (null != fl) {
                        fl.setVisibility(View.GONE);
                    }
                }
            });
            transaction.add(R.id.frame_container, gateWayFragment);
        } else {
            transaction.show(gateWayFragment);
        }
        FrameLayout fl = findViewById(R.id.frame_container);
        if (null != fl) {
            fl.setVisibility(View.VISIBLE);
        }
        transaction.commit();//提交事务
    }

    private void showLoginEnvironmentVerifyFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        login_container.setVisibility(View.GONE);
        if (null == loginEnvironmentVerifyFragment) {
            loginEnvironmentVerifyFragment = new LoginEnvironmentVerifyFragment(new LoginEnvironmentVerifyListener() {
                @Override
                public void callEnter() {
                    showLoginEnvironmentVerifyEnterFragment();
                }
            });
            transaction.add(R.id.frame_container, loginEnvironmentVerifyFragment);
        } else {
            transaction.show(loginEnvironmentVerifyFragment);
        }
        FrameLayout fl = findViewById(R.id.frame_container);
        if (null != fl) {
            fl.setVisibility(View.VISIBLE);
        }
        transaction.commit();//提交事务
    }

    private void showLoginEnvironmentVerifyEnterFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        login_container.setVisibility(View.GONE);
        if (null == loginEnvironmentVerifyEnterFragment) {
            loginEnvironmentVerifyEnterFragment = new LoginEnvironmentVerifyEnterFragment();
            transaction.add(R.id.frame_container, loginEnvironmentVerifyEnterFragment);
        } else {
            transaction.show(loginEnvironmentVerifyEnterFragment);
        }
        FrameLayout fl = findViewById(R.id.frame_container);
        if (null != fl) {
            fl.setVisibility(View.VISIBLE);
        }
        transaction.commit();//提交事务
    }

    /*
     * 隐藏所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (null != gateWayFragment) {
            transaction.hide(gateWayFragment);
        }
        if (null != loginEnvironmentVerifyFragment) {
            transaction.hide(loginEnvironmentVerifyFragment);
        }
        if (null != loginEnvironmentVerifyEnterFragment) {
            transaction.hide(loginEnvironmentVerifyEnterFragment);
        }
    }

    private void updatePasswordUI(final boolean showPassword) {
        int currentPosition = passwordEdit.getSelectionStart();
        if (showPassword) {
            passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        Singleton.INSTANCE.isShowPassword = showPassword;
        passwordEdit.setSelection(currentPosition);
    }

    private void switchSMSAndAccountLogin(final boolean accountLogin) {
        if (accountLogin) {
            smsCodeLayout.setVisibility(View.GONE);
            phoneNumberEditLayout.setVisibility(View.GONE);
            passwordEditLayout.setVisibility(View.VISIBLE);
            accountEditLayout.setVisibility(View.VISIBLE);
            tv_switch_login_method.setText(R.string.sms_verification_code_login);
        } else {
            accountEditLayout.setVisibility(View.GONE);
            passwordEditLayout.setVisibility(View.GONE);
            smsCodeLayout.setVisibility(View.VISIBLE);
            phoneNumberEditLayout.setVisibility(View.VISIBLE);
            tv_switch_login_method.setText(R.string.username_password_login);
        }
        Singleton.INSTANCE.isAccountLogin = accountLogin;

        // 加载账号信息
        if (accountLogin) {
            loginRecordsList = LoginUtil.getLoginAccounts();
            if (null != loginRecordsList) {
                if (loginRecordsList.size() > 1) {
                    iv_accountMore.setVisibility(View.VISIBLE);
                    accountEdit.setText(loginRecordsList.get(0));
                    passwordEdit.requestFocus();
                } else if (loginRecordsList.size() == 1) {
                    iv_accountMore.setVisibility(View.GONE);
                    accountEdit.setText(loginRecordsList.get(0));
                    passwordEdit.requestFocus();
                } else {
                    iv_accountMore.setVisibility(View.GONE);
                    accountEdit.requestFocus();
                }
            }
        } else {
            loginRecordsList = LoginUtil.getLoginPhones();
            if (null != loginRecordsList) {
                if (loginRecordsList.size() > 1) {
                    iv_phoneNumberMore.setVisibility(View.VISIBLE);
                    phoneNumberEdit.setText(loginRecordsList.get(0));
                    smsCodeEdit.requestFocus();
                } else if (loginRecordsList.size() == 1) {
                    iv_phoneNumberMore.setVisibility(View.GONE);
                    phoneNumberEdit.setText(loginRecordsList.get(0));
                    smsCodeEdit.requestFocus();
                } else {
                    iv_phoneNumberMore.setVisibility(View.GONE);
                    phoneNumberEdit.requestFocus();
                }
            }
        }

        updateLoginButtonEnabledStatus();
    }

    private void sendSMSVerifyCode() {
        String phoneNum = phoneNumberEdit.getText().toString();
        if (Strings.isValidPhoneNum(phoneNum)) {
            SendSMSCodeCountDownTimer timer = new SendSMSCodeCountDownTimer(btn_send_sms_code, "重新获取");
            timer.start();
        } else {
            ToastUtils.show(getResources().getText(R.string.invalid_phone_number));
        }
    }

    private void updateLoginButtonEnabledStatus() {
        if (Singleton.INSTANCE.isAccountLogin) {
            if (accountEdit.getText().length() > 0 && passwordEdit.getText().length() > 0) {
                if (!tv_loginButton.isEnabled()) {
                    tv_loginButton.setEnabled(true);
                }
            } else {
                if (tv_loginButton.isEnabled()) {
                    tv_loginButton.setEnabled(false);
                }
            }
        } else {
            if (phoneNumberEdit.getText().length() > 0 && smsCodeEdit.getText().length() > 0) {
                if (!tv_loginButton.isEnabled()) {
                    tv_loginButton.setEnabled(true);
                }
            } else {
                if (tv_loginButton.isEnabled()) {
                    tv_loginButton.setEnabled(false);
                }
            }
        }
    }

    private boolean verifyLoginInfo() {
        if (Singleton.INSTANCE.isAccountLogin) {
            LoginUtil.recordLoginAccount(accountEdit.getText().toString());
        } else {
            String phoneNum = phoneNumberEdit.getText().toString();
            if (Strings.isValidPhoneNum(phoneNum)) {
                LoginUtil.recordLoginPhone(phoneNum);
            } else {
                ToastUtils.show(getResources().getText(R.string.invalid_phone_number));
                return false;
            }
        }
        return true;
    }

    private static class ViewHolder {
        TextView loginRecordMenuItemText;
    }

    private class LoginRecordInfoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return null == loginRecordsList ? 0 : loginRecordsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null == loginRecordsList ? null : loginRecordsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == loginRecordsList || loginRecordsList.size() <= 0) {
                return convertView;
            }

            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.login_record_menu_item, null);
                holder = new ViewHolder();
                holder.loginRecordMenuItemText = convertView.findViewById(R.id.login_record_menu_item_text);
                convertView.setTag(holder);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewHolder holder = (ViewHolder) v.getTag();
                        if (Singleton.INSTANCE.isAccountLogin) {
                            accountEdit.setText(holder.loginRecordMenuItemText.getText());
                            passwordEdit.setText("");
                        } else {
                            phoneNumberEdit.setText(holder.loginRecordMenuItemText.getText());
                        }
                        if (null != popup && popup.isShowing()) {
                            popup.dismiss();
                        }
                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.loginRecordMenuItemText.setText(loginRecordsList.get(position));
            return convertView;
        }
    }
}
