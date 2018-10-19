package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;
/**
 * Created by Administrator on 2018/4/24.
 */

public class BisStanReviRec extends HttpBaseResponse<BisStanReviRec> {
        private String guid;
        private String stanReviGuid;
        private String reviWiunCode;


    public String getReviWiunName() {
        return reviWiunName == null ? "" : reviWiunName;
    }

    public void setReviWiunName(String reviWiunName) {
        this.reviWiunName = reviWiunName;
    }

    public String getApplOrgId() {
        return applOrgId == null ? "" : applOrgId;
    }

    public void setApplOrgId(String applOrgId) {
        this.applOrgId = applOrgId;
    }

    public String getApplOrgName() {
        return applOrgName == null ? "" : applOrgName;
    }

    public void setApplOrgName(String applOrgName) {
        this.applOrgName = applOrgName;
    }

    private String reviWiunName;
        private String applOrgId;
        private String applOrgName;
        private String reviType;
        private String confTime;
        private String confLoc;
        private String partPers;
        private String ifSiteRevi;
        private String startTime;
        private String endTime;
        private String reviChief;
        private String reviViceChief;
        private String reviGroupMem;
        private String ifAgree;
        private String reviOpin;
        private String recomLevel;
        private String veriTime;
        private String persExpertGuid;
        private String siteReviNote;
        private String reviNote;
        private String note;
        private String collTime;
        private String updTime;
        private String recPers;
        private String reviGrade;

    public String getApplGrade() {
        return applGrade == null ? "" : applGrade;
    }

    public void setApplGrade(String applGrade) {
        this.applGrade = applGrade;
    }

    private String applGrade;

    public String getApplTime() {
        return applTime == null ? "" : applTime;
    }

    public void setApplTime(String applTime) {
        this.applTime = applTime;
    }

    private String applTime;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStanReviGuid() {
        return stanReviGuid == null ? "" : stanReviGuid;
    }

    public void setStanReviGuid(String stanReviGuid) {
        this.stanReviGuid = stanReviGuid;
    }

    public String getReviWiunCode() {
        return reviWiunCode == null ? "" : reviWiunCode;
    }

    public void setReviWiunCode(String reviWiunCode) {
        this.reviWiunCode = reviWiunCode;
    }

    public String getReviType() {
        return reviType == null ? "" : reviType;
    }

    public void setReviType(String reviType) {
        this.reviType = reviType;
    }

    public String getConfTime() {
        return confTime == null ? "" : confTime;
    }

    public void setConfTime(String confTime) {
        this.confTime = confTime;
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

    public String getIfSiteRevi() {
        return ifSiteRevi == null ? "" : ifSiteRevi;
    }

    public void setIfSiteRevi(String ifSiteRevi) {
        this.ifSiteRevi = ifSiteRevi;
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

    public String getReviChief() {
        return reviChief == null ? "" : reviChief;
    }

    public void setReviChief(String reviChief) {
        this.reviChief = reviChief;
    }

    public String getReviViceChief() {
        return reviViceChief == null ? "" : reviViceChief;
    }

    public void setReviViceChief(String reviViceChief) {
        this.reviViceChief = reviViceChief;
    }

    public String getReviGroupMem() {
        return reviGroupMem == null ? "" : reviGroupMem;
    }

    public void setReviGroupMem(String reviGroupMem) {
        this.reviGroupMem = reviGroupMem;
    }

    public String getIfAgree() {
        return ifAgree == null ? "" : ifAgree;
    }

    public void setIfAgree(String ifAgree) {
        this.ifAgree = ifAgree;
    }

    public String getReviOpin() {
        return reviOpin == null ? "" : reviOpin;
    }

    public void setReviOpin(String reviOpin) {
        this.reviOpin = reviOpin;
    }

    public String getRecomLevel() {
        return recomLevel == null ? "" : recomLevel;
    }

    public void setRecomLevel(String recomLevel) {
        this.recomLevel = recomLevel;
    }

    public String getVeriTime() {
        return veriTime == null ? "" : veriTime;
    }

    public void setVeriTime(String veriTime) {
        this.veriTime = veriTime;
    }

    public String getPersExpertGuid() {
        return persExpertGuid == null ? "" : persExpertGuid;
    }

    public void setPersExpertGuid(String persExpertGuid) {
        this.persExpertGuid = persExpertGuid;
    }

    public String getSiteReviNote() {
        return siteReviNote == null ? "" : siteReviNote;
    }

    public void setSiteReviNote(String siteReviNote) {
        this.siteReviNote = siteReviNote;
    }

    public String getReviNote() {
        return reviNote == null ? "" : reviNote;
    }

    public void setReviNote(String reviNote) {
        this.reviNote = reviNote;
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

    public String getReviGrade() {
        return reviGrade == null ? "" : reviGrade;
    }

    public void setReviGrade(String reviGrade) {
        this.reviGrade = reviGrade;
    }
}
