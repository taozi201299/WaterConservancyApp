package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/15.
 */

public class BisWinsGroupAll extends HttpBaseResponse<BisWinsGroupAll>{

    String guid;
    String winsGroupNum;// 稽察组编号
    String speStafGuid; // 特派员GUID
    String winsProgGuid; // 稽察方案GUID
    String note;
    String collTime;
    String updTime;
    String recPers;
    String speStafAssiGuid;
    String winsPlanCode;  // 稽察计划编码
    String winsArrayCode;  // 稽察批次编号

    String specStaffName;

    public String getSpecStaffName() {
        return specStaffName == null ? "" : specStaffName;
    }

    public void setSpecStaffName(String specStaffName) {
        this.specStaffName = specStaffName;
    }

    public String getSpecStaffAssiName() {
        return specStaffAssiName == null ? "" : specStaffAssiName;
    }

    public void setSpecStaffAssiName(String specStaffAssiName) {
        this.specStaffAssiName = specStaffAssiName;
    }

    public String getSpecExportName() {
        return specExportName == null ? "" : specExportName;
    }

    public void setSpecExportName(String specExportName) {
        this.specExportName = specExportName;
    }

    String specStaffAssiName;
    String specExportName;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWinsGroupNum() {
        return winsGroupNum == null ? "" : winsGroupNum;
    }

    public void setWinsGroupNum(String winsGroupNum) {
        this.winsGroupNum = winsGroupNum;
    }

    public String getSpeStafGuid() {
        return speStafGuid == null ? "" : speStafGuid;
    }

    public void setSpeStafGuid(String speStafGuid) {
        this.speStafGuid = speStafGuid;
    }

    public String getWinsProgGuid() {
        return winsProgGuid == null ? "" : winsProgGuid;
    }

    public void setWinsProgGuid(String winsProgGuid) {
        this.winsProgGuid = winsProgGuid;
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

    public String getSpeStafAssiGuid() {
        return speStafAssiGuid == null ? "" : speStafAssiGuid;
    }

    public void setSpeStafAssiGuid(String speStafAssiGuid) {
        this.speStafAssiGuid = speStafAssiGuid;
    }

    public String getWinsPlanCode() {
        return winsPlanCode == null ? "" : winsPlanCode;
    }

    public void setWinsPlanCode(String winsPlanCode) {
        this.winsPlanCode = winsPlanCode;
    }

    public String getWinsArrayCode() {
        return winsArrayCode == null ? "" : winsArrayCode;
    }

    public void setWinsArrayCode(String winsArrayCode) {
        this.winsArrayCode = winsArrayCode;
    }
}