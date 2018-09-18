package com.syberos.shuili.config;

import java.util.HashMap;

/**
 * Created by BZB on 2018/7/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.config.
 */
public class GlobleConstants {


    public static int iMinistry = 1;
    public static int iProvince = 2;
    public static int iCity = 3;
    public static int iCounty = 4;

    /**
     * for login
     */
    public static final String Login = "login";
    public static final String Pwd = "pwd";
    public static final String ALL_CHINA = "001111";
    public static final String ALL_PROVINCE = "001110";
    public static final String ALL_COUNTER = "001100";
    public static final String DENSITY = "density";
    public static final int HAZ_HTYPE_NORMAL = 1;
    public static final int HAZ_TYPE_BIGER = 2;
    public static final int HIDD_TYPE_NORMAL = 1;
    public static final int HIDD_TYPE_BIGER = 2;

    // 地图服务器地址
    public static final String mapServer = "http://192.168.1.11:8088";
    public static String strIP = "http://192.168.1.8:8080";
    public static String strCJIP = "http://192.168.1.11:7080";
    public static String strZJIP = "http://192.168.1.11:9080";
    public static String strZRIP = " http://192.168.1.11:6080";

    //专题图 - 地址
    public static final String BASE_URL = "http://192.168.1.11:7080/";


    /**
     * 1 大中型已建工程运行管理单位 CJYJ
     * 2 大中型在建工程项目法人 CJFR
     * 3 小型工程管理单位和技术服务单位CJFW
     * 4 大中型在建工程施工单位  CJSG
     * 5 大中型在建工程监理单位 CJJL
     * 企事业用户
     */
    public static final String CJYJ = "CJYJ";
    public static final String CJFR = "CJFR";
    public static final String CJFW = "CJFW";
    public static final String CJSG = "CJSG";
    public static final String CJJL = "CJJL";
    /**
     * 行政用户角色
     */
    public static final String acci = "acci"; // 安监-事故管理
    public static final String sins = "sins"; // 安监-安全检查
    public static final String stan = "stan"; // 安监-标准化评审管理
    public static final String maha = "maha"; // 安监-风险源监管
    public static final String woas = "woas"; // 安监-工作考核
    public static final String suen = "suen"; // 安监-监督执法
    public static final String wins = "wins"; // 安监-水利稽察
    public static final String hidd = "hidd"; // 安监-隐患监管
    /**
     * 事故快报状态
     */
    public static final int NEW_ACCI = -1; // 新建事故
    public static final int reportAcci_0 = 0; // 未快报
    public static final int reportAcci_1 = 1;  // 已快报，要月报
    public static final int reportAcci_2 = 2; //已快报，不月报
    public static final int reportAcci_3 = 3; // 非快报事故
    /**
     * 事故级别
     */

    public static final int TYPE_NORMAL = 1; // 一般事故
    public static final int TYPE_BIG = 2; // 较大事故
    public static final int TYPE_BIGGER = 3; // 重大事故
    public static final int TYPE_LARGE = 4; // 特大事故

    /**
     * If_Pho_Rep IF_RESP_ACCI
     */

    public static final String YES = "1";
    public static final String NO = "2";

    /**
     * 模块名称 for 任务代办
     */

    public static final String Module_Name_Hidd = "hidd";
    public static final String Module_Name_Acci = "Acci";
    public static final String Module_Name_Haz = "Haz";
    public static final String Module_Name_Sins = "Sins";
    public static final String Module_Name_Stan = "Stan";
    public static final String Module_Name_Report_Hidd = "hiddMonRepPeri";
    public static final String Module_Name_Report_Acci = "acciMonRepPeri";
    public static final String Module_Name_Report_Check = "sinsMonRepPeri";


    public static HashMap<Integer, String> hazGradeMap = new HashMap<Integer, String>() {

        {
            put(HAZ_HTYPE_NORMAL, "一般危险源");
            put(HAZ_TYPE_BIGER, "重大危险源");
        }
    };
    public static HashMap<Integer, String> winsProbMap = new HashMap<Integer, String>() {

        {
            put(1, "前期设计");
            put(2, "建设管理");
            put(3, "计划管理");
            put(4, "财务管理");
            put(5, "质量管理");

        }
    };
    public static HashMap<Integer, String> winsProbTypeMap = new HashMap<Integer, String>() {
        {
            put(1, "一般");
            put(2, "严重");
            put(3, "非常严重");
        }
    };
    public static HashMap<String, String> winsProjType = new HashMap<String, String>() {
        {
            put("01", "基建项目");
            put("02", "预算项目");
            put("03", "国科项目");
            put("04", "许可项目");
            put("05", "生产项目");
            put("06", "其他项目");
            put("07", "水库工程");
            put("08", "水闸工程");
            put("09", "泵站工程");
            put("10", "水电站工程");
            put("11", "堤防工程");
            put("12", "罐区工程");
            put("13", "引水调工程");
            put("14", "淤地坝工程");
            put("15", "农村供水工程");
            put("16", "其他工程");
        }
    };
    public static HashMap<String, String> winsTypeMap = new HashMap<String, String>() {
        {
            put("1", "工作稽查");
            put("2", "工程稽查");
        }
    };
}
