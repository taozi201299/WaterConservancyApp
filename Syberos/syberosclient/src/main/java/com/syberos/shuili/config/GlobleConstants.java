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
    public static HashMap<Integer,String>hazGradeMap = new HashMap<Integer,String>(){

        {
            put(HAZ_HTYPE_NORMAL,"一般危险源");
            put(HAZ_TYPE_BIGER,"重大危险源");
        }
    };
}
