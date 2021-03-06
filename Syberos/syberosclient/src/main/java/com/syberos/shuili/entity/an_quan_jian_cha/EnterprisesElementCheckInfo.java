package com.syberos.shuili.entity.an_quan_jian_cha;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * created by：toby on 18-4-20 10:06
 * email：zhaodongshuang@syberos.com
 * 企事业端：安全检查：元素检查信息
 */
public class EnterprisesElementCheckInfo
        extends HttpBaseResponse<EnterprisesElementCheckInfo> {

    public final static int LEVEL_NORMAL    = 3;    // 正常
    public final static int LEVEL_COMMON    = 1;    // 一般隐患
    public final static int LEVEL_NOT_SURE  = 4;    // 待查
    public final static int LEVEL_LARGE     = 2;    // 重大隐患
    public final static int LEVEL_OTHER     = 5;    // 重大隐患

    private int level;          // 隐患级别
    private String name;        // 元素名称
    private List<EECI_CheckItemInfo> checkItemInfos;    // 检查项
    private List<EECI_HiddenItemInfo> hiddenItemInfos;  // 可能存在隐患


    public static int getLevelNormal() {
        return LEVEL_NORMAL;
    }

    public static int getLevelCommon() {
        return LEVEL_COMMON;
    }

    public static int getLevelNotSure() {
        return LEVEL_NOT_SURE;
    }

    public static int getLevelLarge() {
        return LEVEL_LARGE;
    }

    public static int getLevelOther() {
        return LEVEL_OTHER;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EECI_CheckItemInfo> getCheckItemInfos() {
        if (checkItemInfos == null) {
            return new ArrayList<>();
        }
        return checkItemInfos;
    }

    public void setCheckItemInfos(List<EECI_CheckItemInfo> checkItemInfos) {
        this.checkItemInfos = checkItemInfos;
    }

    public List<EECI_HiddenItemInfo> getHiddenItemInfos() {
        if (hiddenItemInfos == null) {
            return new ArrayList<>();
        }
        return hiddenItemInfos;
    }

    public void setHiddenItemInfos(List<EECI_HiddenItemInfo> hiddenItemInfos) {
        this.hiddenItemInfos = hiddenItemInfos;
    }
}
