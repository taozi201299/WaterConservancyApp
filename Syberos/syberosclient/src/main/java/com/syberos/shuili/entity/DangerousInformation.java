package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * Created by toby on 18-3-27.
 * 事故信息
 */

public class DangerousInformation implements Serializable {
    public static final int TYPE_LOW= 1;
    public static final int TYPE_NORMAL = 2;
    public static final int TYPE_BIGER = 3;
    public static final int TYPE_BIGGEST = 4;

    private String dangerousId;
    private String dangerousName;
    private String dangerousUnit;
    private String dangerousContent;
    private int dangerousType;
    private String time;
    private String dangerousHazard;     // 危害
    private String dangerousMeasures;   // 措施
    private String dangerousEvaluation; // 评价


    public String getDangerousName() {
        return dangerousName;
    }

    public void setDangerousName(String dangerousName) {
        this.dangerousName = dangerousName;
    }

    public String getDangerousUnit() {
        return dangerousUnit;
    }

    public void setDangerousUnit(String dangerousUnit) {
        this.dangerousUnit = dangerousUnit;
    }

    public String getDangerousContent() {
        return dangerousContent;
    }

    public void setDangerousContent(String dangerousContent) {
        this.dangerousContent = dangerousContent;
    }

    public int getDangerousType() {
        return dangerousType;
    }

    public void setDangerousType(int dangerousType) {
        this.dangerousType = dangerousType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDangerousId() {
        return dangerousId;
    }

    public void setDangerousId(String dangerousId) {
        this.dangerousId = dangerousId;
    }

    public String getDangerousHazard() {
        return dangerousHazard;
    }

    public void setDangerousHazard(String dangerousHazard) {
        this.dangerousHazard = dangerousHazard;
    }

    public String getDangerousMeasures() {
        return dangerousMeasures;
    }

    public void setDangerousMeasures(String dangerousMeasures) {
        this.dangerousMeasures = dangerousMeasures;
    }

    public String getDangerousEvaluation() {
        return dangerousEvaluation;
    }

    public void setDangerousEvaluation(String dangerousEvaluation) {
        this.dangerousEvaluation = dangerousEvaluation;
    }
}
