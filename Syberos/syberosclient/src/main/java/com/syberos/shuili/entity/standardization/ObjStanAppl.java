package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public class ObjStanAppl extends HttpBaseResponse<ObjStanAppl> {
    private String guid;
    private String stanApplCode;
    private String applOrgGuid;

    public String getApplOrgName() {
        return applOrgName == null ? "" : applOrgName;
    }

    public void setApplOrgName(String applOrgName) {
        this.applOrgName = applOrgName;
    }

    private String applOrgName;
    private String array;
    private String applProp;
    private String applGrade;
    private String befoStanSitu;
    private String selfEvaConc;
    private String applTime;
    private String stat;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;
    /**
     * 资料审查
     */
    private String startTime;
    private String compTime;
    private String confLoc;

    public String getObjPunoGuid() {
        return objPunoGuid == null ? "" : objPunoGuid;
    }

    public void setObjPunoGuid(String objPunoGuid) {
        this.objPunoGuid = objPunoGuid;
    }

    private String objPunoGuid;

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

    private String partPers;

    public String getBisScheReviGuid() {
        return bisScheReviGuid == null ? "" : bisScheReviGuid;
    }

    public void setBisScheReviGuid(String bisScheReviGuid) {
        this.bisScheReviGuid = bisScheReviGuid;
    }

    private String bisScheReviGuid;

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    private boolean isVerify = false; // 是否已经审核

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStanApplCode() {
        return stanApplCode == null ? "" : stanApplCode;
    }

    public void setStanApplCode(String stanApplCode) {
        this.stanApplCode = stanApplCode;
    }

    public String getApplOrgGuid() {
        return applOrgGuid == null ? "" : applOrgGuid;
    }

    public void setApplOrgGuid(String applOrgGuid) {
        this.applOrgGuid = applOrgGuid;
    }

    public String getArray() {
        return array == null ? "" : array;
    }

    public void setArray(String array) {
        this.array = array;
    }

    public String getApplProp() {
        return applProp == null ? "" : applProp;
    }

    public void setApplProp(String applProp) {
        this.applProp = applProp;
    }

    public String getApplGrade() {
        return applGrade == null ? "" : applGrade;
    }

    public void setApplGrade(String applGrade) {
        this.applGrade = applGrade;
    }

    public String getBefoStanSitu() {
        return befoStanSitu == null ? "" : befoStanSitu;
    }

    public void setBefoStanSitu(String befoStanSitu) {
        this.befoStanSitu = befoStanSitu;
    }

    public String getSelfEvaConc() {
        return selfEvaConc == null ? "" : selfEvaConc;
    }

    public void setSelfEvaConc(String selfEvaConc) {
        this.selfEvaConc = selfEvaConc;
    }

    public String getApplTime() {
        return applTime == null ? "" : applTime;
    }

    public void setApplTime(String applTime) {
        this.applTime = applTime;
    }

    public String getStat() {
        return stat == null ? "" : stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
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
