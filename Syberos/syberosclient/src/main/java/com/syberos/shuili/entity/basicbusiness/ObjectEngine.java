package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class ObjectEngine extends HttpHiddenBaseResponse<ObjectEngine> {
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

    public String getEngStat() {
        return engStat;
    }

    public void setEngStat(String engStat) {
        this.engStat = engStat;
    }

    String engStat;

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

    public String getEngCode() {
        return engCode;
    }

    public void setEngCode(String engCode) {
        this.engCode = engCode;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEngType() {
        return engType;
    }

    public void setEngType(String engType) {
        this.engType = engType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
