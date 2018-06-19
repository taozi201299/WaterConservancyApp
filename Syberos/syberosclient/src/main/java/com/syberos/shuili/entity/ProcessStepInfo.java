package com.syberos.shuili.entity;

import com.syberos.shuili.R;

import java.io.Serializable;

public class ProcessStepInfo implements Serializable {
    private boolean isStepCompleted = false;
    private String stepNumber = "";
    private boolean isCurrent = false;

    private String stepTime = "2017-12-01";
    private String stepTitle = "备案申请";
    private int stepTitleColor = R.color.black;
    private String stepContent = "原因:危害较大、影响范围较广，危害较大、影响范围较广，危害较大、影响范围较广";

    public boolean isStepCompleted() {
        return isStepCompleted;
    }

    public void setStepCompleted(boolean stepCompleted) {
        isStepCompleted = stepCompleted;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }


    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getStepTime() {
        return stepTime;
    }

    public void setStepTime(String stepTime) {
        this.stepTime = stepTime;
    }


    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public int getStepTitleColor() {
        return stepTitleColor;
    }

    public void setStepTitleColor(int stepTitleColor) {
        this.stepTitleColor = stepTitleColor;
    }

    public String getStepContent() {
        return stepContent;
    }

    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }
}
