package com.example.testmodule.thematicchart.securitychecks;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */

import java.io.Serializable;

/**
 * 部门检查情况
 */
public class SecuritySituationEntry implements Serializable{
    int situationCount;//检查次数
    int checkProjectCount;//检查工程数量
    int programCount;//问题数量
    int hiddenCount;//隐患数量

    public int getSituationCount() {
        return situationCount;
    }

    public void setSituationCount(int situationCount) {
        this.situationCount = situationCount;
    }

    public int getCheckProjectCount() {
        return checkProjectCount;
    }

    public void setCheckProjectCount(int checkProjectCount) {
        this.checkProjectCount = checkProjectCount;
    }

    public int getProgramCount() {
        return programCount;
    }

    public void setProgramCount(int programCount) {
        this.programCount = programCount;
    }

    public int getHiddenCount() {
        return hiddenCount;
    }

    public void setHiddenCount(int hiddenCount) {
        this.hiddenCount = hiddenCount;
    }
}