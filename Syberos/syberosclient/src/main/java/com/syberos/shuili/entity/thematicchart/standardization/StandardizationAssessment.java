package com.syberos.shuili.entity.thematicchart.standardization;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.standardization.
 */
//标准化考核情况
public class StandardizationAssessment  implements Serializable {
    private int assessmentOne;//标准化考核 一级
    private int assessmentTwo;//标准化考核 二级
    private int assessmentThree;//标准化考核 三级

    public int getAssessmentOne() {
        return assessmentOne;
    }

    public void setAssessmentOne(int assessmentOne) {
        this.assessmentOne = assessmentOne;
    }

    public int getAssessmentTwo() {
        return assessmentTwo;
    }

    public void setAssessmentTwo(int assessmentTwo) {
        this.assessmentTwo = assessmentTwo;
    }

    public int getAssessmentThree() {
        return assessmentThree;
    }

    public void setAssessmentThree(int assessmentThree) {
        this.assessmentThree = assessmentThree;
    }
}