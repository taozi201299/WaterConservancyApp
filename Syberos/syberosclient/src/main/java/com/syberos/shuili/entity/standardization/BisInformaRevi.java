package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/10/29.
 * 资料审核对象
 */

public class BisInformaRevi extends HttpBaseResponse<BisInformaRevi> {
    private String guid;
    private String informaReviCode;
    private String applGuid;
    private String startTime;
    private String compTime;
    private String confLoc;
    private String partPers;
    private String informaReviOpin;
    private String informaReviResu;
    private String apprOpin;
    private String apprStat;
    private String scenReviOrg;
    private String scenReviSttime;
    private String scenReviEngtime;
    private String scenReviLeader;
    private String scenReviLeaderGuid;
    private String scenReviDepul;
    private String scenReviDepulGuid;
    private String scenReviMem;
    private String scenReviMemGuid;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getInformaReviCode() {
        return informaReviCode == null ? "" : informaReviCode;
    }

    public void setInformaReviCode(String informaReviCode) {
        this.informaReviCode = informaReviCode;
    }

    public String getApplGuid() {
        return applGuid == null ? "" : applGuid;
    }

    public void setApplGuid(String applGuid) {
        this.applGuid = applGuid;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCompTime() {
        return compTime == null ? "" : compTime;
    }

    public void setCompTime(String compTime) {
        this.compTime = compTime;
    }

    public String getConfLoc() {
        return confLoc == null ? "" : confLoc;
    }

    public void setConfLoc(String confLoc) {
        this.confLoc = confLoc;
    }

    public String getPartPers() {
        return partPers == null ? "" : partPers;
    }

    public void setPartPers(String partPers) {
        this.partPers = partPers;
    }

    public String getInformaReviOpin() {
        return informaReviOpin == null ? "" : informaReviOpin;
    }

    public void setInformaReviOpin(String informaReviOpin) {
        this.informaReviOpin = informaReviOpin;
    }

    public String getInformaReviResu() {
        return informaReviResu == null ? "" : informaReviResu;
    }

    public void setInformaReviResu(String informaReviResu) {
        this.informaReviResu = informaReviResu;
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

    public String getScenReviOrg() {
        return scenReviOrg == null ? "" : scenReviOrg;
    }

    public void setScenReviOrg(String scenReviOrg) {
        this.scenReviOrg = scenReviOrg;
    }

    public String getScenReviSttime() {
        return scenReviSttime == null ? "" : scenReviSttime;
    }

    public void setScenReviSttime(String scenReviSttime) {
        this.scenReviSttime = scenReviSttime;
    }

    public String getScenReviEngtime() {
        return scenReviEngtime == null ? "" : scenReviEngtime;
    }

    public void setScenReviEngtime(String scenReviEngtime) {
        this.scenReviEngtime = scenReviEngtime;
    }

    public String getScenReviLeader() {
        return scenReviLeader == null ? "" : scenReviLeader;
    }

    public void setScenReviLeader(String scenReviLeader) {
        this.scenReviLeader = scenReviLeader;
    }

    public String getScenReviLeaderGuid() {
        return scenReviLeaderGuid == null ? "" : scenReviLeaderGuid;
    }

    public void setScenReviLeaderGuid(String scenReviLeaderGuid) {
        this.scenReviLeaderGuid = scenReviLeaderGuid;
    }

    public String getScenReviDepul() {
        return scenReviDepul == null ? "" : scenReviDepul;
    }

    public void setScenReviDepul(String scenReviDepul) {
        this.scenReviDepul = scenReviDepul;
    }

    public String getScenReviDepulGuid() {
        return scenReviDepulGuid == null ? "" : scenReviDepulGuid;
    }

    public void setScenReviDepulGuid(String scenReviDepulGuid) {
        this.scenReviDepulGuid = scenReviDepulGuid;
    }

    public String getScenReviMem() {
        return scenReviMem == null ? "" : scenReviMem;
    }

    public void setScenReviMem(String scenReviMem) {
        this.scenReviMem = scenReviMem;
    }

    public String getScenReviMemGuid() {
        return scenReviMemGuid == null ? "" : scenReviMemGuid;
    }

    public void setScenReviMemGuid(String scenReviMemGuid) {
        this.scenReviMemGuid = scenReviMemGuid;
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
}
