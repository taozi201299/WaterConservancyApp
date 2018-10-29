package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 * 会议审定对象
 */

public class BisConfExamappr extends HttpBaseResponse<BisConfExamappr> {
    private String guid;
    private String confExamapprCode;
    private String applGuid;
    private String examapprTime;
    private String examapprExpert;
    private String examapprOpin;
    private String veriOpin;
    private String apprOpin;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;
    private String apprStat;
    private String veriStat;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getConfExamapprCode() {
        return confExamapprCode == null ? "" : confExamapprCode;
    }

    public void setConfExamapprCode(String confExamapprCode) {
        this.confExamapprCode = confExamapprCode;
    }

    public String getApplGuid() {
        return applGuid == null ? "" : applGuid;
    }

    public void setApplGuid(String applGuid) {
        this.applGuid = applGuid;
    }

    public String getExamapprTime() {
        return examapprTime == null ? "" : examapprTime;
    }

    public void setExamapprTime(String examapprTime) {
        this.examapprTime = examapprTime;
    }

    public String getExamapprExpert() {
        return examapprExpert == null ? "" : examapprExpert;
    }

    public void setExamapprExpert(String examapprExpert) {
        this.examapprExpert = examapprExpert;
    }

    public String getExamapprOpin() {
        return examapprOpin == null ? "" : examapprOpin;
    }

    public void setExamapprOpin(String examapprOpin) {
        this.examapprOpin = examapprOpin;
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

    public String getApprStat() {
        return apprStat == null ? "" : apprStat;
    }

    public void setApprStat(String apprStat) {
        this.apprStat = apprStat;
    }

    public String getVeriStat() {
        return veriStat == null ? "" : veriStat;
    }

    public void setVeriStat(String veriStat) {
        this.veriStat = veriStat;
    }
}
