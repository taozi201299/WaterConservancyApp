package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/18.
 */

/**
 * 形式初审返回信息结构 类名对应数据库名
 */
public class ObjStanRevis extends HttpBaseResponse<ObjStanRevis> {
    String guid;
    /**
     * 申请单位GUID
     */
    String applOrgGuid;

    public String getApplOrgName() {
        return applOrgName == null ? "" : applOrgName;
    }

    public void setApplOrgName(String applOrgName) {
        this.applOrgName = applOrgName;
    }

    /**
     * 申请单位名称
     */
    String applOrgName;
    /**
     * 申请性质
     */
    String applProp;
    /**
     * 申请等级
     */
    String applGrade;
    /**
     * 申请前本专业曾经取得的标准化等级情况
     */
    String befoStanSitu;
    /**
     * 自主评定结论
     */
    String selfEvaConc;
    /**
     * 申请时间
     */
    String applTime;
    /**
     * 审核单位
     */
    String veriWiunCode;
    /**
     * 审核时间
     */
    String veriTime;
    /**
     * 审核意见
     */
    String veriOpin;
    /**
     * 审查结论
     */
    String veriConc;
    /**
     * 备注
     */
    String note;
    /**
     * 采集时间
     */
    String collTime;
    /**
     * 更新时间
     */
    String updTime;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getApplOrgGuid() {
        return applOrgGuid == null ? "" : applOrgGuid;
    }

    public void setApplOrgGuid(String applOrgGuid) {
        this.applOrgGuid = applOrgGuid;
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

    public String getVeriWiunCode() {
        return veriWiunCode == null ? "" : veriWiunCode;
    }

    public void setVeriWiunCode(String veriWiunCode) {
        this.veriWiunCode = veriWiunCode;
    }

    public String getVeriTime() {
        return veriTime == null ? "" : veriTime;
    }

    public void setVeriTime(String veriTime) {
        this.veriTime = veriTime;
    }

    public String getVeriOpin() {
        return veriOpin == null ? "" : veriOpin;
    }

    public void setVeriOpin(String veriOpin) {
        this.veriOpin = veriOpin;
    }

    public String getVeriConc() {
        return veriConc == null ? "" : veriConc;
    }

    public void setVeriConc(String veriConc) {
        this.veriConc = veriConc;
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
}
