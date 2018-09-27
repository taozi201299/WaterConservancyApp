package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/20.
 * 专家对象表 OBJ_EXPERT
 */

public class ObjExpert extends HttpBaseResponse<ObjExpert> {
    String guid;
    String persGuid;
    String citNum;
    /**
     * 专家姓名
     */
    String persName;
    String orgGuid;
    String sex;
    String bornDate;
    String major;
    String curMajor;
    String curMajorYear;
    String admDuty;
    String techTit;
    String poafCode;
    String addr;
    String zip;
    String offiTel;
    String moph;
    String homeTel;
    String expertGrad;
    String expertResid;
    String workResum;
    String winsJobEava;
    String applInsPost;
    String sinsExpertType;
    String sinsJobEava;
    String bankCode;
    String bankDepo;
    String bankCardNum;
    String note;
    String collTime;
    String updTime;
    String recPers;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPersGuid() {
        return persGuid == null ? "" : persGuid;
    }

    public void setPersGuid(String persGuid) {
        this.persGuid = persGuid;
    }

    public String getCitNum() {
        return citNum == null ? "" : citNum;
    }

    public void setCitNum(String citNum) {
        this.citNum = citNum;
    }

    public String getPersName() {
        return persName == null ? "" : persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBornDate() {
        return bornDate == null ? "" : bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getMajor() {
        return major == null ? "" : major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCurMajor() {
        return curMajor == null ? "" : curMajor;
    }

    public void setCurMajor(String curMajor) {
        this.curMajor = curMajor;
    }

    public String getCurMajorYear() {
        return curMajorYear == null ? "" : curMajorYear;
    }

    public void setCurMajorYear(String curMajorYear) {
        this.curMajorYear = curMajorYear;
    }

    public String getAdmDuty() {
        return admDuty == null ? "" : admDuty;
    }

    public void setAdmDuty(String admDuty) {
        this.admDuty = admDuty;
    }

    public String getTechTit() {
        return techTit == null ? "" : techTit;
    }

    public void setTechTit(String techTit) {
        this.techTit = techTit;
    }

    public String getPoafCode() {
        return poafCode == null ? "" : poafCode;
    }

    public void setPoafCode(String poafCode) {
        this.poafCode = poafCode;
    }

    public String getAddr() {
        return addr == null ? "" : addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZip() {
        return zip == null ? "" : zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOffiTel() {
        return offiTel == null ? "" : offiTel;
    }

    public void setOffiTel(String offiTel) {
        this.offiTel = offiTel;
    }

    public String getMoph() {
        return moph == null ? "" : moph;
    }

    public void setMoph(String moph) {
        this.moph = moph;
    }

    public String getHomeTel() {
        return homeTel == null ? "" : homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getExpertGrad() {
        return expertGrad == null ? "" : expertGrad;
    }

    public void setExpertGrad(String expertGrad) {
        this.expertGrad = expertGrad;
    }

    public String getExpertResid() {
        return expertResid == null ? "" : expertResid;
    }

    public void setExpertResid(String expertResid) {
        this.expertResid = expertResid;
    }

    public String getWorkResum() {
        return workResum == null ? "" : workResum;
    }

    public void setWorkResum(String workResum) {
        this.workResum = workResum;
    }

    public String getWinsJobEava() {
        return winsJobEava == null ? "" : winsJobEava;
    }

    public void setWinsJobEava(String winsJobEava) {
        this.winsJobEava = winsJobEava;
    }

    public String getApplInsPost() {
        return applInsPost == null ? "" : applInsPost;
    }

    public void setApplInsPost(String applInsPost) {
        this.applInsPost = applInsPost;
    }

    public String getSinsExpertType() {
        return sinsExpertType == null ? "" : sinsExpertType;
    }

    public void setSinsExpertType(String sinsExpertType) {
        this.sinsExpertType = sinsExpertType;
    }

    public String getSinsJobEava() {
        return sinsJobEava == null ? "" : sinsJobEava;
    }

    public void setSinsJobEava(String sinsJobEava) {
        this.sinsJobEava = sinsJobEava;
    }

    public String getBankCode() {
        return bankCode == null ? "" : bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankDepo() {
        return bankDepo == null ? "" : bankDepo;
    }

    public void setBankDepo(String bankDepo) {
        this.bankDepo = bankDepo;
    }

    public String getBankCardNum() {
        return bankCardNum == null ? "" : bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
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
