package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/9/5.
 * 根据单位GUID查询隐患督办列表-直属单位
 * 隐患督办对象
 */

public class HiddenSupervice  extends HttpBaseResponse<HiddenSupervice>{
    private String supOrgName;
    private String hiddSour;
    private String proPart;
    private String recPers;
    private String partLong;
    private int ROWNO;
    private String supWiunCode;
    private String bisHiddInveID;
    private String seGuid;
    private String hiddStat;
    private String seCheckItemGuid;
    private String orgCode;
    private String bisMajHiddSupID;
    private String veriResu;
    private String inspDate;
    private String note;
    private String orgGuid;
    private String ifFound;
    private String inspRecGuid;
    private String bisHiddRectAcceID;
    private String hiddDesc;
    private String collTime;
    private String orgName;
    private String requCompDate;
    private String hiddGrad;
    private String tendGuid;
    private String guid;
    private String partLat;
    private String updTime;
    private String bisHiddMajVeriID;
    private String hiddClas;
    private String engGuid;
    private String hiddName;
    private String engName;


    public String getSupOrgName() {
        return supOrgName == null ? "" : supOrgName;
    }

    public void setSupOrgName(String supOrgName) {
        this.supOrgName = supOrgName;
    }

    public String getHiddSour() {
        return hiddSour == null ? "" : hiddSour;
    }

    public void setHiddSour(String hiddSour) {
        this.hiddSour = hiddSour;
    }

    public String getProPart() {
        return proPart == null ? "" : proPart;
    }

    public void setProPart(String proPart) {
        this.proPart = proPart;
    }

    public String getRecPers() {
        return recPers == null ? "" : recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getPartLong() {
        return partLong == null ? "" : partLong;
    }

    public void setPartLong(String partLong) {
        this.partLong = partLong;
    }

    public int getROWNO() {
        return ROWNO;
    }

    public void setROWNO(int ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getSupWiunCode() {
        return supWiunCode == null ? "" : supWiunCode;
    }

    public void setSupWiunCode(String supWiunCode) {
        this.supWiunCode = supWiunCode;
    }

    public String getBisHiddInveID() {
        return bisHiddInveID == null ? "" : bisHiddInveID;
    }

    public void setBisHiddInveID(String bisHiddInveID) {
        this.bisHiddInveID = bisHiddInveID;
    }

    public String getSeGuid() {
        return seGuid == null ? "" : seGuid;
    }

    public void setSeGuid(String seGuid) {
        this.seGuid = seGuid;
    }

    public String getHiddStat() {
        return hiddStat == null ? "" : hiddStat;
    }

    public void setHiddStat(String hiddStat) {
        this.hiddStat = hiddStat;
    }

    public String getSeCheckItemGuid() {
        return seCheckItemGuid == null ? "" : seCheckItemGuid;
    }

    public void setSeCheckItemGuid(String seCheckItemGuid) {
        this.seCheckItemGuid = seCheckItemGuid;
    }

    public String getOrgCode() {
        return orgCode == null ? "" : orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getBisMajHiddSupID() {
        return bisMajHiddSupID == null ? "" : bisMajHiddSupID;
    }

    public void setBisMajHiddSupID(String bisMajHiddSupID) {
        this.bisMajHiddSupID = bisMajHiddSupID;
    }

    public String getVeriResu() {
        return veriResu == null ? "" : veriResu;
    }

    public void setVeriResu(String veriResu) {
        this.veriResu = veriResu;
    }

    public String getInspDate() {
        return inspDate == null ? "" : inspDate;
    }

    public void setInspDate(String inspDate) {
        this.inspDate = inspDate;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getIfFound() {
        return ifFound == null ? "" : ifFound;
    }

    public void setIfFound(String ifFound) {
        this.ifFound = ifFound;
    }

    public String getInspRecGuid() {
        return inspRecGuid == null ? "" : inspRecGuid;
    }

    public void setInspRecGuid(String inspRecGuid) {
        this.inspRecGuid = inspRecGuid;
    }

    public String getBisHiddRectAcceID() {
        return bisHiddRectAcceID == null ? "" : bisHiddRectAcceID;
    }

    public void setBisHiddRectAcceID(String bisHiddRectAcceID) {
        this.bisHiddRectAcceID = bisHiddRectAcceID;
    }

    public String getHiddDesc() {
        return hiddDesc == null ? "" : hiddDesc;
    }

    public void setHiddDesc(String hiddDesc) {
        this.hiddDesc = hiddDesc;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getOrgName() {
        return orgName == null ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRequCompDate() {
        return requCompDate == null ? "" : requCompDate;
    }

    public void setRequCompDate(String requCompDate) {
        this.requCompDate = requCompDate;
    }

    public String getHiddGrad() {
        return hiddGrad == null ? "" : hiddGrad;
    }

    public void setHiddGrad(String hiddGrad) {
        this.hiddGrad = hiddGrad;
    }

    public String getTendGuid() {
        return tendGuid == null ? "" : tendGuid;
    }

    public void setTendGuid(String tendGuid) {
        this.tendGuid = tendGuid;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPartLat() {
        return partLat == null ? "" : partLat;
    }

    public void setPartLat(String partLat) {
        this.partLat = partLat;
    }

    public String getUpdTime() {
        return updTime == null ? "" : updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getBisHiddMajVeriID() {
        return bisHiddMajVeriID == null ? "" : bisHiddMajVeriID;
    }

    public void setBisHiddMajVeriID(String bisHiddMajVeriID) {
        this.bisHiddMajVeriID = bisHiddMajVeriID;
    }

    public String getHiddClas() {
        return hiddClas == null ? "" : hiddClas;
    }

    public void setHiddClas(String hiddClas) {
        this.hiddClas = hiddClas;
    }

    public String getEngGuid() {
        return engGuid == null ? "" : engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getHiddName() {
        return hiddName == null ? "" : hiddName;
    }

    public void setHiddName(String hiddName) {
        this.hiddName = hiddName;
    }

    public String getEngName() {
        return engName == null ? "" : engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }
}
