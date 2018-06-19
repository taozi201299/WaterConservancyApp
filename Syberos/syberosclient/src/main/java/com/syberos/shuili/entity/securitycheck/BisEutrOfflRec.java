package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/27.
 * 8.2.2.55	线下教育培训记录
 */

public class BisEutrOfflRec  extends HttpHiddenBaseResponse<BisEutrOfflRec> {
     String guid;
     String orgGuid;
    /**
     * 培训主题
     */
    String eutrThem;
    /**
     * 培训类型
      */
    String eutrType;
    /**
     * 培训人数
     */
     String eutrNum;
    /**
     * 培训开始时间
     */
    String eutrStartDate;
    /**
     * 培训结束时间
     */
     String eutrEndDate;
    /**
     * 学时
     */
    String eutrHuor;
    /**
     * 培训地点
     */
     String eutrLoc;
    /**
     * 培训内容
     */
    String eutrCont;
     String note;;
     String collTime;
     String updTime;
     String recPers;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getEutrThem() {
        return eutrThem;
    }

    public void setEutrThem(String eutrThem) {
        this.eutrThem = eutrThem;
    }

    public String getEutrType() {
        return eutrType;
    }

    public void setEutrType(String eutrType) {
        this.eutrType = eutrType;
    }

    public String getEutrNum() {
        return eutrNum;
    }

    public void setEutrNum(String eutrNum) {
        this.eutrNum = eutrNum;
    }

    public String getEutrStartDate() {
        return eutrStartDate;
    }

    public void setEutrStartDate(String eutrStartDate) {
        this.eutrStartDate = eutrStartDate;
    }

    public String getEutrEndDate() {
        return eutrEndDate;
    }

    public void setEutrEndDate(String eutrEndDate) {
        this.eutrEndDate = eutrEndDate;
    }

    public String getEutrHuor() {
        return eutrHuor;
    }

    public void setEutrHuor(String eutrHuor) {
        this.eutrHuor = eutrHuor;
    }

    public String getEutrLoc() {
        return eutrLoc;
    }

    public void setEutrLoc(String eutrLoc) {
        this.eutrLoc = eutrLoc;
    }

    public String getEutrCont() {
        return eutrCont;
    }

    public void setEutrCont(String eutrCont) {
        this.eutrCont = eutrCont;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }
}
