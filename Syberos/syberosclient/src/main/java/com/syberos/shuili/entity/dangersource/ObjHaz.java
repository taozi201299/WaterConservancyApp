package com.syberos.shuili.entity.dangersource;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/13.
 */

public class ObjHaz extends HttpBaseResponse<ObjHaz> {

    String username;
    String timestamp;
    String nonce;
    String appkey;
    String appsecret;
    String sign;
    String guid;
    String hazCode;
    /**
     * 危险源名称
     */
    String hazName;
    String orgGuid;
    /**
     * 所属工程
     */
    String engGuid;
    String tendGuid;
    /**
     * 危险源等级
     */
    String hiddGrad;
    String hiddGradName;
    String proPart;
    String partLong;
    String partLat;
    String harmRisk;
    String moniPrec;
    String offiTel;
    String patPers;
    /**
     * 监管责任人
     */
    String supPers;
    /**
     * 是否已经立牌公告
     */
    String ifLiceNoti;
    String note;
    String collTime;
    String updTime;
    String recPers;

    String orgName;

    String engineName;

    public String getHazStat() {
        return hazStat == null ? "" : hazStat;
    }

    public void setHazStat(String hazStat) {
        this.hazStat = hazStat;
    }

    String hazStat;

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp == null ? "" : timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce == null ? "" : nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getAppkey() {
        return appkey == null ? "" : appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret == null ? "" : appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getSign() {
        return sign == null ? "" : sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHazCode() {
        return hazCode == null ? "" : hazCode;
    }

    public void setHazCode(String hazCode) {
        this.hazCode = hazCode;
    }

    public String getHazName() {
        return hazName == null ? "" : hazName;
    }

    public void setHazName(String hazName) {
        this.hazName = hazName;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getEngGuid() {
        return engGuid == null ? "" : engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getTendGuid() {
        return tendGuid == null ? "" : tendGuid;
    }

    public void setTendGuid(String tendGuid) {
        this.tendGuid = tendGuid;
    }

    public String getHiddGrad() {
        return hiddGrad == null ? "" : hiddGrad;
    }

    public void setHiddGrad(String hiddGrad) {
        this.hiddGrad = hiddGrad;
    }

    public String getHiddGradName() {
        return hiddGradName == null ? "" : hiddGradName;
    }

    public void setHiddGradName(String hiddGradName) {
        this.hiddGradName = hiddGradName;
    }

    public String getProPart() {
        return proPart == null ? "" : proPart;
    }

    public void setProPart(String proPart) {
        this.proPart = proPart;
    }

    public String getPartLong() {
        return partLong == null ? "" : partLong;
    }

    public void setPartLong(String partLong) {
        this.partLong = partLong;
    }

    public String getPartLat() {
        return partLat == null ? "" : partLat;
    }

    public void setPartLat(String partLat) {
        this.partLat = partLat;
    }

    public String getHarmRisk() {
        return harmRisk == null ? "" : harmRisk;
    }

    public void setHarmRisk(String harmRisk) {
        this.harmRisk = harmRisk;
    }

    public String getMoniPrec() {
        return moniPrec == null ? "" : moniPrec;
    }

    public void setMoniPrec(String moniPrec) {
        this.moniPrec = moniPrec;
    }

    public String getOffiTel() {
        return offiTel == null ? "" : offiTel;
    }

    public void setOffiTel(String offiTel) {
        this.offiTel = offiTel;
    }

    public String getPatPers() {
        return patPers == null ? "" : patPers;
    }

    public void setPatPers(String patPers) {
        this.patPers = patPers;
    }

    public String getSupPers() {
        return supPers == null ? "" : supPers;
    }

    public void setSupPers(String supPers) {
        this.supPers = supPers;
    }

    public String getIfLiceNoti() {
        return ifLiceNoti == null ? "" : ifLiceNoti;
    }

    public void setIfLiceNoti(String ifLiceNoti) {
        this.ifLiceNoti = ifLiceNoti;
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

    public String getOrgName() {
        return orgName == null ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEngineName() {
        return engineName == null ? "" : engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }
}
