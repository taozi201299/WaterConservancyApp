package com.example.testmodule.thematicchart.waterinspection;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspection.
 */

public class WISituationEntry implements Serializable{
    int inspectionCount;//稽察次数
    int projectCount;//项目数量
    int programCount;//问题数量

    public int getInspectionCount() {
        return inspectionCount;
    }

    public void setInspectionCount(int inspectionCount) {
        this.inspectionCount = inspectionCount;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public int getProgramCount() {
        return programCount;
    }

    public void setProgramCount(int programCount) {
        this.programCount = programCount;
    }
}