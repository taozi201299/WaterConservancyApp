package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/12
 * 隐患督办信息
 */

public class HiddenSupserviceInfo extends HttpBaseResponse<HiddenSupserviceInfo> {
    String username;
    String timestamp;
    String nonce;
    String appkey;
    String appsecret;
    String sign;
    String guid;
    /**
     * 隐患GUID
     */
    String hiddGuid;
    /**
     * 是否挂牌督办
     */
    String isList;
    /**
     * 整改期限
     */
    String rectPeri;
    /**
     * 督办文号
     */
    String supLareId;
    /**
     * 督办日期
     */
    String supDate;
    /**
     * 督办单位
     */
    String supWiunCode;
    /**
     * 督办负责人
     */
    String supLegPers;
    /**
     * 督办意见
     */
    String supOpin;
    /**
     * 备注
     */
    String note;
    /**
     * 采集时间
     */
    String collTime;
    /**
     *  更新时间
     */
    String updTime;
    /**
     * 记录人员
     */
    String recPers;


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

    public String getHiddGuid() {
        return hiddGuid == null ? "" : hiddGuid;
    }

    public void setHiddGuid(String hiddGuid) {
        this.hiddGuid = hiddGuid;
    }

    public String getIsList() {
        return isList == null ? "" : isList;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public String getRectPeri() {
        return rectPeri == null ? "" : rectPeri;
    }

    public void setRectPeri(String rectPeri) {
        this.rectPeri = rectPeri;
    }

    public String getSupLareId() {
        return supLareId == null ? "" : supLareId;
    }

    public void setSupLareId(String supLareId) {
        this.supLareId = supLareId;
    }

    public String getSupDate() {
        return supDate == null ? "" : supDate;
    }

    public void setSupDate(String supDate) {
        this.supDate = supDate;
    }

    public String getSupWiunCode() {
        return supWiunCode == null ? "" : supWiunCode;
    }

    public void setSupWiunCode(String supWiunCode) {
        this.supWiunCode = supWiunCode;
    }

    public String getSupLegPers() {
        return supLegPers == null ? "" : supLegPers;
    }

    public void setSupLegPers(String supLegPers) {
        this.supLegPers = supLegPers;
    }

    public String getSupOpin() {
        return supOpin == null ? "" : supOpin;
    }

    public void setSupOpin(String supOpin) {
        this.supOpin = supOpin;
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
