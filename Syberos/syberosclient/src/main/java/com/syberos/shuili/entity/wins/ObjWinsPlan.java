package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/15.
 * 水利稽察计划对象表
 */



public class ObjWinsPlan extends HttpBaseResponse<ObjWinsPlan>{


    private String adminWiunName;
    private String bwpGuid;
    private String winsPlanName;
    private int winsArrayCode;
    private int rowno;
    private String projName;
    private int bwpWinsGroupNum;
    private String owpGuid;
    private int winsArrayNum;
    private String feedStat;
    private String downStat;
    private int stat;


    public String getAdminWiunName() {
        return adminWiunName == null ? "" : adminWiunName;
    }

    public void setAdminWiunName(String adminWiunName) {
        this.adminWiunName = adminWiunName;
    }

    public String getBwpGuid() {
        return bwpGuid == null ? "" : bwpGuid;
    }

    public void setBwpGuid(String bwpGuid) {
        this.bwpGuid = bwpGuid;
    }

    public String getWinsPlanName() {
        return winsPlanName == null ? "" : winsPlanName;
    }

    public void setWinsPlanName(String winsPlanName) {
        this.winsPlanName = winsPlanName;
    }

    public int getWinsArrayCode() {
        return winsArrayCode;
    }

    public void setWinsArrayCode(int winsArrayCode) {
        this.winsArrayCode = winsArrayCode;
    }

    public int getRowno() {
        return rowno;
    }

    public void setRowno(int rowno) {
        this.rowno = rowno;
    }

    public String getProjName() {
        return projName == null ? "" : projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public int getBwpWinsGroupNum() {
        return bwpWinsGroupNum;
    }

    public void setBwpWinsGroupNum(int bwpWinsGroupNum) {
        this.bwpWinsGroupNum = bwpWinsGroupNum;
    }

    public String getOwpGuid() {
        return owpGuid == null ? "" : owpGuid;
    }

    public void setOwpGuid(String owpGuid) {
        this.owpGuid = owpGuid;
    }

    public int getWinsArrayNum() {
        return winsArrayNum;
    }

    public void setWinsArrayNum(int winsArrayNum) {
        this.winsArrayNum = winsArrayNum;
    }

    public String getFeedStat() {
        return feedStat == null ? "" : feedStat;
    }

    public void setFeedStat(String feedStat) {
        this.feedStat = feedStat;
    }

    public String getDownStat() {
        return downStat == null ? "" : downStat;
    }

    public void setDownStat(String downStat) {
        this.downStat = downStat;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
