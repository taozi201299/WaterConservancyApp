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
    public static final int HAZ_HTYPE_NORMAL= 1;
    public static final int HAZ_TYPE_BIGER = 2;


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
    public static HashMap<Integer,String>hazGradeMap = new HashMap<Integer,String>(){

        {
            put(HAZ_HTYPE_NORMAL,"一般危险源");
            put(HAZ_TYPE_BIGER,"重大危险源");
        }
    };
}
