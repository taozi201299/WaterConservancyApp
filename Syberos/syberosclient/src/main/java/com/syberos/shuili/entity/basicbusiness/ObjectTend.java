package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ObjectTend extends HttpBaseResponse<ObjectTend> {

     String username;
     String timestamp;
     String nonce;
     String appkey;
     String appsecret;
     String sign;
     String guid;
     String tendName;
     String orgGuid;
     String orgProjDeptGuid;
     String engGuid;
     String legTel;
     String conAmout;
     String totalSpc;
     String consCont;
     String note;
     String collTime;
     String updTime;
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

    public String getTendName() {
        return tendName == null ? "" : tendName;
    }

    public void setTendName(String tendName) {
        this.tendName = tendName;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getOrgProjDeptGuid() {
        return orgProjDeptGuid == null ? "" : orgProjDeptGuid;
    }

    public void setOrgProjDeptGuid(String orgProjDeptGuid) {
        this.orgProjDeptGuid = orgProjDeptGuid;
    }

    public String getEngGuid() {
        return engGuid == null ? "" : engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getLegTel() {
        return legTel == null ? "" : legTel;
    }

    public void setLegTel(String legTel) {
        this.legTel = legTel;
    }

    public String getConAmout() {
        return conAmout == null ? "" : conAmout;
    }

    public void setConAmout(String conAmout) {
        this.conAmout = conAmout;
    }

    public String getTotalSpc() {
        return totalSpc == null ? "" : totalSpc;
    }

    public void setTotalSpc(String totalSpc) {
        this.totalSpc = totalSpc;
    }

    public String getConsCont() {
        return consCont == null ? "" : consCont;
    }

    public void setConsCont(String consCont) {
        this.consCont = consCont;
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
