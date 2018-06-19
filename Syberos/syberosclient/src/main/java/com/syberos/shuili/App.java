package com.syberos.shuili;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.lzy.okhttputils.OkHttpUtils;
import com.syberos.shuili.utils.Arrays2;
import com.syberos.shuili.utils.CrashHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class  App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private static final String PREFERENCES_NAME = "app_config";

    private static final String LOGIN_ACCOUNTS = "login_accounts";
    private static final String LOGIN_PHONES = "login_phones";
    private static final String LOGIN_RECORDS_SPLIT = "_@_login_records_split_@_";
    private static final String LOGIN_LAST_USER ="login_last_user";
    private static final String LAW_ENFORCEMENT_QUERY_HISTORIES = "L_E_Q_HISTORY";
    private static final int MAX_LOGIN_RECORD_COUNT = 4;

    public static App app;
    private String mVersionName;
    private int mVersionCode;
    private static Context context;
    private static SharedPreferences preferences;
    /**
     *    0表示非水利机构  1表示水利机构  无此用户时为3
     */
    private static int  isWaterIndustry = 0;
    /**
     * 1 大中型已建工程运行管理单位 (工程对象表 )2大中型在建工程项目法人 （项目对象表）3 小型工程管理单位和技术服务单位

     */
    private static int orgType = 1;

//    public static String strIP ="7.199.44.211:8080";
    public static String strIP ="192.168.1.8:8080";
    /**
     * 1 大中型已建工程运行管理单位 CJYJ
     * 2 大中型在建工程项目法人 CJFR
     * 3 小型工程管理单位和技术服务单位CJFW
     * 4 大中型在建工程施工单位  CJSG
     * 5 大中型在建工程监理单位 CJJL
     */

    public static String sCode = "";

    /**
     * Log
     */
    public static void initLog() {

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
//
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        initLog();
        OkHttpUtils.init(this);
    }

    public static Context globalContext() {
        return context;
    }

    public static void recordLoginAccount(final String account) {
        String login_accounts = "";
        login_accounts = preferences.getString(LOGIN_ACCOUNTS, login_accounts);
        if (!login_accounts.contains(account)) {
            String[] login_accounts_array = login_accounts.split(LOGIN_RECORDS_SPLIT);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(account);
            for (int i=0; i < login_accounts_array.length && i < MAX_LOGIN_RECORD_COUNT; ++i) {
                stringBuilder.append(LOGIN_RECORDS_SPLIT);
                stringBuilder.append(login_accounts_array[i]);
            }
            preferences.edit().putString(LOGIN_ACCOUNTS, stringBuilder.toString()).apply();
        }
    }
    public static String getLastUserAccount(){
        return preferences.getString(LOGIN_LAST_USER,"");
    }
    public static void setLastUserAccount(final  String account){
        preferences.edit().putString(LOGIN_LAST_USER,account).apply();
    }

    @Nullable
    public static List<String> getLoginAccounts() {
        String login_accounts = "";
        login_accounts = preferences.getString(LOGIN_ACCOUNTS, login_accounts);
        if (!login_accounts.isEmpty()) {
            String[] login_accounts_array = login_accounts.split(LOGIN_RECORDS_SPLIT);
            if (login_accounts_array.length > 0) {
                return Arrays2.stringArrayToListString(login_accounts_array);
            }
        }
        return null;
    }

    public static void recordLoginPhone(final String phone) {
        String login_phones = "";
        login_phones = preferences.getString(LOGIN_PHONES, login_phones);
        if (!login_phones.contains(phone)) {
            String[] login_phones_array = login_phones.split(LOGIN_RECORDS_SPLIT);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(phone);
            for (int i=0; i < login_phones_array.length && i < MAX_LOGIN_RECORD_COUNT; ++i) {
                stringBuilder.append(LOGIN_RECORDS_SPLIT);
                stringBuilder.append(login_phones_array[i]);
            }
            preferences.edit().putString(LOGIN_PHONES, stringBuilder.toString()).apply();
        }
    }

    @Nullable
    public static List<String> getLoginPhones() {
        String login_phones = "";
        login_phones = preferences.getString(LOGIN_PHONES, login_phones);
        if (!login_phones.isEmpty()) {
            String[] login_phones_array = login_phones.split(LOGIN_RECORDS_SPLIT);
            if (login_phones_array.length > 0) {
                return Arrays2.stringArrayToListString(login_phones_array);
            }
        }
        return null;
    }
    public static void  setUserType(int isWaterIndustry){
       app.isWaterIndustry = isWaterIndustry;
    }
    public static int getUsertype(){
        return app.isWaterIndustry;
    }
    public static void setOrgType(int orgType){
        app.orgType = orgType;
    }
    public static int getOrgType(){
        return app.orgType;
    }

    public static void saveLawEnforcementQueryHistories(List<String> histories) {
        Set<String> set = new HashSet<>(histories);
        preferences.edit().putStringSet(LAW_ENFORCEMENT_QUERY_HISTORIES, set).apply();
    }

    public static List<String> lawEnforcementQueryHistories() {
        return new ArrayList<>(preferences.getStringSet(LAW_ENFORCEMENT_QUERY_HISTORIES,
                new HashSet<String>()));
    }
}
