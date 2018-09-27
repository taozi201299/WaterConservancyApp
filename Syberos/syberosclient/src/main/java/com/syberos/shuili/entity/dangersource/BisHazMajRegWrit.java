package com.syberos.shuili.entity.dangersource;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/20.
 */

public class BisHazMajRegWrit extends HttpBaseResponse<BisHazMajRegWrit> {

    String guid;
    String hazGuid;
    String regOrgGuid;
    String regTime;
    String regCode;
    String writeOrgGuid;
    String writeCode;
    String writeOffTime;
    String hazStat;
    String note;
    String collTime;
    String updTime;
    String recPers;
    String reviOpin;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHazGuid() {
        return hazGuid == null ? "" : hazGuid;
    }

    public void setHazGuid(String hazGuid) {
        this.hazGuid = hazGuid;
    }

    public String getRegOrgGuid() {
        return regOrgGuid == null ? "" : regOrgGuid;
    }

    public void setRegOrgGuid(String regOrgGuid) {
        this.regOrgGuid = regOrgGuid;
    }

    public String getRegTime() {
        return regTime == null ? "" : regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegCode() {
        return regCode == null ? "" : regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getWriteOrgGuid() {
        return writeOrgGuid == null ? "" : writeOrgGuid;
    }

    public void setWriteOrgGuid(String writeOrgGuid) {
        this.writeOrgGuid = writeOrgGuid;
    }

    public String getWriteCode() {
        return writeCode == null ? "" : writeCode;
    }

    public void setWriteCode(String writeCode) {
        this.writeCode = writeCode;
    }

    public String getWriteOffTime() {
        return writeOffTime == null ? "" : writeOffTime;
    }

    public void setWriteOffTime(String writeOffTime) {
        this.writeOffTime = writeOffTime;
    }

    public String getHazStat() {
        return hazStat == null ? "" : hazStat;
    }

    public void setHazStat(String hazStat) {
        this.hazStat = hazStat;
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

    public String getReviOpin() {
        return reviOpin == null ? "" : reviOpin;
    }

    public void setReviOpin(String reviOpin) {
        this.reviOpin = reviOpin;
    }
}
