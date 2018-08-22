package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/22.
 */

public class BisWinsProgAll extends HttpBaseResponse<BisWinsProgAll> {
    String guid;
    String winsPlanGuid;
    String note;
    String collTime;
    String updTime;
    String recPers;
    String startTime;
    String endTime;
    String winsArrayCode;
    String winsGroupNum;
    String winsProjType;
    String winsType;
    String reviBatc;
    String downStat;
    String feedStat;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWinsPlanGuid() {
        return winsPlanGuid == null ? "" : winsPlanGuid;
    }

    public void setWinsPlanGuid(String winsPlanGuid) {
        this.winsPlanGuid = winsPlanGuid;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime == null ? "" : updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers == null ? "" : recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWinsArrayCode() {
        return winsArrayCode == null ? "" : winsArrayCode;
    }

    public void setWinsArrayCode(String winsArrayCode) {
        this.winsArrayCode = winsArrayCode;
    }

    public String getWinsGroupNum() {
        return winsGroupNum == null ? "" : winsGroupNum;
    }

    public void setWinsGroupNum(String winsGroupNum) {
        this.winsGroupNum = winsGroupNum;
    }

    public String getWinsProjType() {
        return winsProjType == null ? "" : winsProjType;
    }

    public void setWinsProjType(String winsProjType) {
        this.winsProjType = winsProjType;
    }

    public String getWinsType() {
        return winsType == null ? "" : winsType;
    }

    public void setWinsType(String winsType) {
        this.winsType = winsType;
    }

    public String getReviBatc() {
        return reviBatc == null ? "" : reviBatc;
    }

    public void setReviBatc(String reviBatc) {
        this.reviBatc = reviBatc;
    }

    public String getDownStat() {
        return downStat == null ? "" : downStat;
    }

    public void setDownStat(String downStat) {
        this.downStat = downStat;
    }

    public String getFeedStat() {
        return feedStat == null ? "" : feedStat;
    }

    public void setFeedStat(String feedStat) {
        this.feedStat = feedStat;
    }
}
