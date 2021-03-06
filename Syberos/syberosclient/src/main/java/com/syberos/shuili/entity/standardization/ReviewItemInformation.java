package com.syberos.shuili.entity.standardization;

import java.io.Serializable;

/**
 * created by：toby on 18-4-17 14:42
 * email：zhaodongshuang@syberos.com
 * 标准化 ——> 评审项目信息
 */
public class ReviewItemInformation implements Serializable {

    public final static int LEVEL_0 = 0;    // 一级
    public final static int LEVEL_1 = 1;    // 二级
    public final static int LEVEL_2 = 2;    // 三级
    public final static int LEVEL_3 = 3;    // 不达标

    private String name;            // 标题
    private int level;              // 评审等级
    private String higher;          // 上级主管部门
    private String time;            // 申请时间


    public static int getLevel0() {
        return LEVEL_0;
    }

    public static int getLevel1() {
        return LEVEL_1;
    }

    public static int getLevel2() {
        return LEVEL_2;
    }

    public static int getLevel3() {
        return LEVEL_3;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getHigher() {
        return higher == null ? "" : higher;
    }

    public void setHigher(String higher) {
        this.higher = higher;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
