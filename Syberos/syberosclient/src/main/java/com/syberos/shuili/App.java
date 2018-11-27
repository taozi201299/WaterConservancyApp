package com.syberos.shuili;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lzy.okhttputils.OkHttpUtils;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.common.AppModule;
import com.syberos.shuili.service.dao.DBHelperFactory;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.CrashHandler;
import com.syberos.shuili.utils.SPUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;


public class  App extends Application {

    private static final String TAG = App.class.getSimpleName();


    public static App app;
    private static Context context;

    /**
     *    0表示非水利机构  1表示水利机构  无此用户时为3
     */
    private static int  isWaterIndustry = 0;
    /**
     * 1 大中型已建工程运行管理单位 (工程对象表 )2大中型在建工程项目法人 （项目对象表）3 小型工程管理单位和技术服务单位

     */
    private static int orgType = 1;

    /** appGuid
     * 1 大中型已建工程运行管理单位 CJYJ
     * 2 大中型在建工程项目法人 CJFR
     * 3 小型工程管理单位和技术服务单位CJFW
     * 4 大中型在建工程施工单位  CJSG
     * 5 大中型在建工程监理单位 CJJL
     */
    public static String sCode = "";
    public static AppModule appModule = null;
    /**
     * appCode
     * 002 系统管理员
     * 005 一般用户
     */
    public static String roleCode;
    public static ArrayList<String>sCodes = new ArrayList<>();
    public static String orgJurd = "";

    /**
     * 行政区划 流域区域
     * 1行政区划 2水资源分区 3流域分区 4流域片区 5	单位 9其他
     */
    public static String jurdAreaType = "";

    public static String userType = "-1";
    /**
     * 该用户的模块权限
     */
    public static ArrayList<String>moduleName = new ArrayList<>();

    /**
     * Log
     */
    public static void initLog() {

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);


    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();
        initLog();
        OkHttpUtils.init(this);
        String oldVersionCode = SPUtils.get("versionCode","1").toString();
        String newVersionCode = String.valueOf(CommonUtils.getVersionCode());
//        if(Float.valueOf(newVersionCode) - Float.valueOf(oldVersionCode) > 0){
//            context.deleteDatabase("/sdcard/" + DBHelperFactory.DB_NAME);
//        }
    //    CrashReport.initCrashReport(getApplicationContext(), "362b783ee8", true);

//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init();


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SPUtils.put(GlobleConstants.Login,"-1");
    }

    public static Context globalContext() {
        return context;
    }


    public static void  setUserType(int isWaterIndustry){
       app.isWaterIndustry = isWaterIndustry;
    }
    public static int getUserType(){
        return  isWaterIndustry;
    }
}
