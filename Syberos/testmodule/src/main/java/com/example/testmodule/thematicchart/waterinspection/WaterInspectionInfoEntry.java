package com.example.testmodule.thematicchart.waterinspection;


import com.example.testmodule.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspe ction.
 */
public class WaterInspectionInfoEntry extends PointEntry {
    int inspectionCount;//稽查次数
    int projectCount;//项目数
    int problemCount;//问题数

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

    public int getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(int problemCount) {
        this.problemCount = problemCount;
    }
}
