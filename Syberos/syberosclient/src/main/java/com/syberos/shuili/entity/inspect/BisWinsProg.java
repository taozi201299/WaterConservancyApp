package com.syberos.shuili.entity.inspect;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 8.2.2.82	稽察方案（BIS_WINS_PROG）
 */

public class BisWinsProg extends HttpHiddenBaseResponse<BisWinsProg> {
    String guid;
    /**
     * 稽察计划GUID
     */
    String winsPlanGuid;
    /**
     * 稽察批次编号
     */
    String winsArrayCode;
    /**
     * 稽察组数
     */
    String winsGroupNum;
    /**
     * 稽察项目类型
     */
    String winsProjType;
    /**
     * 稽察开始时间
     */
    String startTime;
    /**
     * 稽察结束时间
     */
    String endTime;
    String note;
    String collTime;
    String updTime;
    String recPers;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWinsPlanGuid() {
        return winsPlanGuid;
    }

    public void setWinsPlanGuid(String winsPlanGuid) {
        this.winsPlanGuid = winsPlanGuid;
    }

    public String getWinsArrayCode() {
        return winsArrayCode;
    }

    public void setWinsArrayCode(String winsArrayCode) {
        this.winsArrayCode = winsArrayCode;
    }

    public String getWinsGroupNum() {
        return winsGroupNum;
    }

    public void setWinsGroupNum(String winsGroupNum) {
        this.winsGroupNum = winsGroupNum;
    }

    public String getWinsProjType() {
        return winsProjType;
    }

    public void setWinsProjType(String winsProjType) {
        this.winsProjType = winsProjType;
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

    public String getRecPers() {
        return recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }
}
