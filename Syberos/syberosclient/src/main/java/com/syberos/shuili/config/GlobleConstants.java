package com.syberos.shuili.config;

import java.util.HashMap;

/**
 * Created by BZB on 2018/7/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.config.
 */
public class GlobleConstants {
    public static final String ALL_CHINA = "001111";
    public static final String ALL_PROVINCE = "001110";
    public static final String ALL_COUNTER = "001100";
    public static final String DENSITY = "density";
    public static final int HAZ_HTYPE_NORMAL= 1;
    public static final int HAZ_TYPE_BIGER = 2;

    // 地图服务器地址
    public static final String mapServer = "http://192.168.1.11:8088";
    public static String strIP ="http://192.168.1.8:8080";
    public static String strCJIP = "http://192.168.1.11:9080";


    /**
     * 1 大中型已建工程运行管理单位 CJYJ
     * 2 大中型在建工程项目法人 CJFR
     * 3 小型工程管理单位和技术服务单位CJFW
     * 4 大中型在建工程施工单位  CJSG
     * 5 大中型在建工程监理单位 CJJL
     * 企事业用户
     */
    public static final String CJYJ = "CJYJ";
    public static final String CJFR ="CJFR";
    public static final String CJFW = "CJFW";
    public static final String CJSG = "CJSG";
    public static final String CJJL = "CJJL";
    /**
     * 行政用户角色
     */
    public static final String acci ="acci"; // 安监-事故管理
    public static final String sins = "sins"; // 安监-安全检查
    public static final String stan = "stan"; // 安监-标准化评审管理
    public static final String maha = "maha"; // 安监-风险源监管
    public static final String woas = "woas"; // 安监-工作考核
    public static final String suen = "suen"; // 安监-监督执法
    public static final String wins = "wins"; // 安监-水利稽察
    public static final String hidd = "hidd" ; // 安监-隐患监管
    public static HashMap<Integer,String>hazGradeMap = new HashMap<Integer,String>(){

        {
            put(HAZ_HTYPE_NORMAL,"一般危险源");
            put(HAZ_TYPE_BIGER,"重大危险源");
        }
    };
}
