package com.syberos.shuili.entity.an_quan_jian_cha;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-20 14:06
 * email：zhaodongshuang@syberos.com
 */
public class EECI_HiddenItemInfo
        extends HttpBaseResponse<EECI_HiddenItemInfo> {

    private String project;         // 所属工程
    private String target;          // 所属标段
    private String name;            // 隐患名称
    private String time;            //
    private int level;              // 隐患级别
    private String describe;        // 隐患描述

    public String getProject() {
        return project == null ? "" : project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTarget() {
        return target == null ? "" : target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescribe() {
        return describe == null ? "" : describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    // 现场情况


}
