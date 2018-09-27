package com.syberos.shuili.entity.dangersource;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/13.
 */

public class InspectionPartolInfo extends HttpBaseResponse<InspectionPartolInfo> {
    String username;
    String timestam;
    String nonce;
    String appkey;
    String appsecret;
    String sign;
    String guid;
    /**
     * 危险源GUID
     */
    String hazGuid;
    /**
     * 巡视时间
     */
    String patTime;
    /**
     * 巡视人员
     */
    String patPers;
    /**
     * 发现问题
     */
    String probFound;
    /**
     * 处理措施
     */
    String treaMeas;
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

    public String getTimestam() {
        return timestam == null ? "" : timestam;
    }

    public void setTimestam(String timestam) {
        this.timestam = timestam;
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

    public String getHazGuid() {
        return hazGuid == null ? "" : hazGuid;
    }

    public void setHazGuid(String hazGuid) {
        this.hazGuid = hazGuid;
    }

    public String getPatTime() {
        return patTime == null ? "" : patTime;
    }

    public void setPatTime(String patTime) {
        this.patTime = patTime;
    }

    public String getPatPers() {
        return patPers == null ? "" : patPers;
    }

    public void setPatPers(String patPers) {
        this.patPers = patPers;
    }

    public String getProbFound() {
        return probFound == null ? "" : probFound;
    }

    public void setProbFound(String probFound) {
        this.probFound = probFound;
    }

    public String getTreaMeas() {
        return treaMeas == null ? "" : treaMeas;
    }

    public void setTreaMeas(String treaMeas) {
        this.treaMeas = treaMeas;
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
