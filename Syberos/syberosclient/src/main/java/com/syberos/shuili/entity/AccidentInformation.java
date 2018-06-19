package com.syberos.shuili.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by toby on 18-3-26.
 * 事故信息
 */

public class AccidentInformation implements Serializable {

    public static final int TYPE_NORMAL = 0; // 一般事故
    public static final int TYPE_BIG = 1; // 较大事故
    public static final int TYPE_BIGGER = 2; // 重大事故
    public static final int TYPE_LARGE = 3; // 特大事故

    public static final int REPORT_QUICK = 0; // 快报
    public static final int REPORT_AFTER = 1; // 补报

    public static final int UNIT_SELF = 0; // 本单位
    public static final int UNIT_OTHER = 1; // 参建单位/下级单位

    private String accidentId;
    private String accidentName;
    private String accidentUnit;
    private String accidentContent;
    private int accidentType;
    private String time;
    private int seriousInjuriesCount = 0; // 重伤人数
    private int deathCount = 0; // 死亡人数
    private double directEconomicLoss = 0; // 直接经济损失
    private List<String> audioList;
    private int accidentUnitType;
    private boolean accidentIsLiability;
    private boolean accidentIsPhoneReport;

    private int reportType;

    @Override
    public String toString() {
        return "accidentId: " + accidentId + "\n" +
                "accidentName: " + accidentName + "\n" +
                "accidentUnit: " + accidentUnit + "\n" +
                "accidentContent: " + accidentContent + "\n" +
                "accidentType: " + accidentType + "\n" +
                "time: " + time + "\n" +
                "seriousInjuriesCount: " + seriousInjuriesCount + "\n" +
                "deathCount: " + deathCount + "\n" +
                "directEconomicLoss: " + directEconomicLoss + "\n" +
                "audioList: " + (audioList == null ? "" : audioList.toString()) + "\n" +
                "accidentUnitType: " + accidentUnitType + "\n" +
                "accidentIsLiability: " + accidentIsLiability + "\n" +
                "accidentIsPhoneReport: " + accidentIsPhoneReport;
    }

    public String getAccidentName() {
        return accidentName;
    }

    public void setAccidentName(String title) {
        this.accidentName = title;
    }

    public String getAccidentUnit() {
        return accidentUnit;
    }

    public void setAccidentUnit(String accidentUnit) {
        this.accidentUnit = accidentUnit;
    }

    public String getAccidentContent() {
        return accidentContent;
    }

    public void setAccidentContent(String accidentContent) {
        this.accidentContent = accidentContent;
    }

    public int getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(int accidentType) {
        this.accidentType = accidentType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(String accidentId) {
        this.accidentId = accidentId;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getSeriousInjuriesCount() {
        return seriousInjuriesCount;
    }

    public void setSeriousInjuriesCount(int seriousInjuriesCount) {
        this.seriousInjuriesCount = seriousInjuriesCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public double getDirectEconomicLoss() {
        return directEconomicLoss;
    }

    public void setDirectEconomicLoss(double directEconomicLoss) {
        this.directEconomicLoss = directEconomicLoss;
    }

    public List<String> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<String> audioList) {
        this.audioList = audioList;
    }

    public int getAccidentUnitType() {
        return accidentUnitType;
    }

    public void setAccidentUnitType(int accidentUnitType) {
        this.accidentUnitType = accidentUnitType;
    }


    public boolean isAccidentIsLiability() {
        return accidentIsLiability;
    }

    public void setAccidentIsLiability(boolean accidentIsLiability) {
        this.accidentIsLiability = accidentIsLiability;
    }


    public boolean isAccidentIsPhoneReport() {
        return accidentIsPhoneReport;
    }

    public void setAccidentIsPhoneReport(boolean accidentIsPhoneReport) {
        this.accidentIsPhoneReport = accidentIsPhoneReport;
    }

}
