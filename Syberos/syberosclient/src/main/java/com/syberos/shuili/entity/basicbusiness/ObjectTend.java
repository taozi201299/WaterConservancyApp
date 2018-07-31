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
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTendName() {
        return tendName;
    }

    public void setTendName(String tendName) {
        this.tendName = tendName;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getOrgProjDeptGuid() {
        return orgProjDeptGuid;
    }

    public void setOrgProjDeptGuid(String orgProjDeptGuid) {
        this.orgProjDeptGuid = orgProjDeptGuid;
    }

    public String getEngGuid() {
        return engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getLegTel() {
        return legTel;
    }

    public void setLegTel(String legTel) {
        this.legTel = legTel;
    }

    public String getConAmout() {
        return conAmout;
    }

    public void setConAmout(String conAmout) {
        this.conAmout = conAmout;
    }

    public String getTotalSpc() {
        return totalSpc;
    }

    public void setTotalSpc(String totalSpc) {
        this.totalSpc = totalSpc;
    }

    public String getConsCont() {
        return consCont;
    }

    public void setConsCont(String consCont) {
        this.consCont = consCont;
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
