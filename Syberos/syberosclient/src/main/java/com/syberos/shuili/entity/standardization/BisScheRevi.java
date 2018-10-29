package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/29.
 * 现场审核对象
 */

public class BisScheRevi extends HttpBaseResponse<BisScheRevi> {
    private String guid;
    private String scenReviCode;
    private String applGuid;
    private String scenReviOpin;
    private String veriOpin;
    private String apprOpin;
    private String apprStat;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;
    private String veriStat;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getScenReviCode() {
        return scenReviCode == null ? "" : scenReviCode;
    }

    public void setScenReviCode(String scenReviCode) {
        this.scenReviCode = scenReviCode;
    }

    public String getApplGuid() {
        return applGuid == null ? "" : applGuid;
    }

    public void setApplGuid(String applGuid) {
        this.applGuid = applGuid;
    }

    public String getScenReviOpin() {
        return scenReviOpin == null ? "" : scenReviOpin;
    }

    public void setScenReviOpin(String scenReviOpin) {
        this.scenReviOpin = scenReviOpin;
    }

    public String getVeriOpin() {
        return veriOpin == null ? "" : veriOpin;
    }

    public void setVeriOpin(String veriOpin) {
        this.veriOpin = veriOpin;
    }

    public String getApprOpin() {
        return apprOpin == null ? "" : apprOpin;
    }

    public void setApprOpin(String apprOpin) {
        this.apprOpin = apprOpin;
    }

    public String getApprStat() {
        return apprStat == null ? "" : apprStat;
    }

    public void setApprStat(String apprStat) {
        this.apprStat = apprStat;
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

    public String getVeriStat() {
        return veriStat == null ? "" : veriStat;
    }

    public void setVeriStat(String veriStat) {
        this.veriStat = veriStat;
    }
}
