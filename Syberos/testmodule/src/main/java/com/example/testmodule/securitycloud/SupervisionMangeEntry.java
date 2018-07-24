package com.example.testmodule.securitycloud;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/23.
 * Project: Syberos.
 * Package：com.example.testmodule.securitycloud.
 */
public class SupervisionMangeEntry implements Serializable{
    int score; //评分
    int standardLevelOneCount;//标准化 一级 数
    int standardLevelTwoCount;//标准化 二级 数
    int standardLevelThreeCount;//标准化 三级 数
    int workAssessmentScore;//工作考核平均分

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

    public int getWorkAssessmentScore() {
        return workAssessmentScore;
    }

    public void setWorkAssessmentScore(int workAssessmentScore) {
        this.workAssessmentScore = workAssessmentScore;
    }
}
