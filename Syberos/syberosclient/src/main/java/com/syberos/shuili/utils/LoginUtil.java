package com.syberos.shuili.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.syberos.shuili.App;
import com.syberos.shuili.entity.RoleBaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/18.
 */

public class LoginUtil {
    private static final String PREFERENCES_NAME = "app_config";
    private static final String LOGIN_ACCOUNTS = "login_accounts";
    private static final String LOGIN_PHONES = "login_phones";
    private static final String LOGIN_RECORDS_SPLIT = "_@_login_records_split_@_";
    private static final String LOGIN_LAST_USER ="login_last_user";
    private static final String LAW_ENFORCEMENT_QUERY_HISTORIES = "L_E_Q_HISTORY";
    private static final int MAX_LOGIN_RECORD_COUNT = 4;
    private static   ArrayList<RoleBaseInfo> roleList;

    private static SharedPreferences preferences;
    public static void recordLoginAccount(final String account) {
        if(preferences == null)
        preferences =  App.globalContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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
        if(preferences == null)
            preferences =  App.globalContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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
        if(preferences == null)
            preferences =  App.globalContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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
        if(preferences == null)
            preferences =  App.globalContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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
    public static void clearCache(){
        SharedPreferences preferences = App.globalContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    public static void setRoleList( ArrayList<RoleBaseInfo> list){
        roleList = list;
    }
    public static ArrayList<RoleBaseInfo> getRoleList(){
        return roleList;
    }
}
