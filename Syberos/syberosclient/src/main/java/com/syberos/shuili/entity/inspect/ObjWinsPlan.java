package com.syberos.shuili.entity.inspect;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 8.2.1.17	稽察计划对象表
 */

public class ObjWinsPlan extends HttpHiddenBaseResponse<ObjWinsPlan> {
    String guid;
    /**
     * 稽察计划名称
     */
    String winsPlanName;
    /**
     * 稽察批次数量
     */
    String winsArrayNum;
    /**
     * 稽察范围
     */
    String winsScop;
    /**
     * 计划主管机构GUID
     */
    String planOrgGuid;
    /**
     * 发送状态
     */
    String downStat;
    /**
     * 反馈状态
     */
    String feedStat;
    String note;
    String collTime;
    String updTime;
    String repPers;
    /**
     * 稽察开始时间
     */
    String startTime;
    /**
     * 稽察结束时间
     */
    String endTime;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

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

    public String getWinsScop() {
        return winsScop;
    }

    public void setWinsScop(String winsScop) {
        this.winsScop = winsScop;
    }

    public String getPlanOrgGuid() {
        return planOrgGuid;
    }

    public void setPlanOrgGuid(String planOrgGuid) {
        this.planOrgGuid = planOrgGuid;
    }

    public String getDownStat() {
        return downStat;
    }

    public void setDownStat(String downStat) {
        this.downStat = downStat;
    }

    public String getFeedStat() {
        return feedStat;
    }

    public void setFeedStat(String feedStat) {
        this.feedStat = feedStat;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRepPers() {
        return repPers;
    }

    public void setRepPers(String repPers) {
        this.repPers = repPers;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
