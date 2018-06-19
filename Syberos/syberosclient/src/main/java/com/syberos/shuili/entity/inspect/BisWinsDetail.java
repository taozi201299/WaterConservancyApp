package com.syberos.shuili.entity.inspect;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/30.
 */

public class BisWinsDetail implements Serializable {
    /**
     * 稽察计划名称
     */
    String winsPlanName;
    /**
     * 稽察批次数量
     */
    String winsArrayNum;
    /**
     * 特派员GUID
     */
    String speStafGuid;
    /**
     * 特派员助理GUID
     */
    String speStafAssiGuid;
    String areaName;
    /**
     * 专家姓名
     */
    String persExpertName;
    /**
     * 专家领域
     */
    String persExpertPost;
    /**
     * 稽察开始时间
     */
    String startTime;
    /**
     * 稽察结束时间
     */
    String endTime;

    public String getWinsPlanName() {
        return winsPlanName;
    }

    public void setWinsPlanName(String winsPlanName) {
        this.winsPlanName = winsPlanName;
    }

    public String getWinsArrayNum() {
        return winsArrayNum;
    }

    public void setWinsArrayNum(String winsArrayNum) {
        this.winsArrayNum = winsArrayNum;
    }

    public String getSpeStafGuid() {
        return speStafGuid;
    }

    public void setSpeStafGuid(String speStafGuid) {
        this.speStafGuid = speStafGuid;
    }

    public String getSpeStafAssiGuid() {
        return speStafAssiGuid;
    }

    public void setSpeStafAssiGuid(String speStafAssiGuid) {
        this.speStafAssiGuid = speStafAssiGuid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPersExpertName() {
        return persExpertName;
    }

    public void setPersExpertName(String persExpertName) {
        this.persExpertName = persExpertName;
    }

    public String getPersExpertPost() {
        return persExpertPost;
    }

    public void setPersExpertPost(String persExpertPost) {
        this.persExpertPost = persExpertPost;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
