package com.syberos.shuili.entity.securitycloud;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/23.
 * Project: Syberos.
 * Package：com.example.testmodule.securitycloud.
 */
public class SupervisionMangeEntry implements Serializable {
    private int score; //评分
    private int standardLevelOneCount;//标准化 一级 数
    private int standardLevelTwoCount;//标准化 二级 数
    private int standardLevelThreeCount;//标准化 三级 数
    private double workAssessmentScore;//工作考核平均分

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStandardLevelOneCount() {
        return standardLevelOneCount;
    }

    public void setStandardLevelOneCount(int standardLevelOneCount) {
        this.standardLevelOneCount = standardLevelOneCount;
    }

    public int getStandardLevelTwoCount() {
        return standardLevelTwoCount;
    }

    public void setStandardLevelTwoCount(int standardLevelTwoCount) {
        this.standardLevelTwoCount = standardLevelTwoCount;
    }

    public int getStandardLevelThreeCount() {
        return standardLevelThreeCount;
    }

    public void setStandardLevelThreeCount(int standardLevelThreeCount) {
        this.standardLevelThreeCount = standardLevelThreeCount;
    }

    public double getWorkAssessmentScore() {
        return workAssessmentScore;
    }

    public void setWorkAssessmentScore(double workAssessmentScore) {
        this.workAssessmentScore = workAssessmentScore;
    }
}
