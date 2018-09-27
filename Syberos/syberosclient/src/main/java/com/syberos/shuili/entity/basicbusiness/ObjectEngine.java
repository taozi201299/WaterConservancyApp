package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/18.
 */

public class ObjectEngine extends HttpBaseResponse<ObjectEngine> {
    String username;
    String timestamp;
    String nonce;
    String appkey;
    String appsecret;
    String sign;
    String guid;
    /**
     * 工程代码
     */
    String engCode;
    /**
     * 工程名称
     */
    String engName;
    /**
     * 工程类型
     */
    String engType;
    /**
     * 对象建立时间
     */
    String fromDate;
    /**
     * 对象终止时间
     */
    String toDate;


    String engStat;


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

    public String getEngCode() {
        return engCode == null ? "" : engCode;
    }

    public void setEngCode(String engCode) {
        this.engCode = engCode;
    }

    public String getEngName() {
        return engName == null ? "" : engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEngType() {
        return engType == null ? "" : engType;
    }

    public void setEngType(String engType) {
        this.engType = engType;
    }

    public String getFromDate() {
        return fromDate == null ? "" : fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate == null ? "" : toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getEngStat() {
        return engStat == null ? "" : engStat;
    }

    public void setEngStat(String engStat) {
        this.engStat = engStat;
    }
}
