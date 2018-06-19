package com.syberos.shuili.entity.an_quan_jian_cha;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * created by：toby on 18-4-20 14:06
 * email：zhaodongshuang@syberos.com
 */
public class EECI_HiddenItemInfo
        extends HttpHiddenBaseResponse<EECI_HiddenItemInfo> {

    private String project;         // 所属工程
    private String target;          // 所属标段
    private String name;            // 隐患名称
    private String time;            //
    private int level;              // 隐患级别
    private String describe;        // 隐患描述
    // 现场情况

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
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
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
