package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/12.
 * 整改验收信息
 */

public class HiddenAcceptInfo extends HttpBaseResponse<HiddenAcceptInfo> {
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
     * 治理完成日期
     */
    String requCompDate;
    /**
     * 验收责任人
     */
    String accepLegPers;
    /**
     * 验收人
     */
    String accepPers;
    /**
     * 验收时间
     */
    String accepDate;
    /**
     * 验收意见
     */
    String accepOpin;
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

    public String getCancelState() {
        return cancelState == null ? "" : cancelState;
    }

    public void setCancelState(String cancelState) {
        this.cancelState = cancelState;
    }

    String cancelState;


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

    public String getRequCompDate() {
        return requCompDate == null ? "" : requCompDate;
    }

    public void setRequCompDate(String requCompDate) {
        this.requCompDate = requCompDate;
    }

    public String getAccepLegPers() {
        return accepLegPers == null ? "" : accepLegPers;
    }

    public void setAccepLegPers(String accepLegPers) {
        this.accepLegPers = accepLegPers;
    }

    public String getAccepPers() {
        return accepPers == null ? "" : accepPers;
    }

    public void setAccepPers(String accepPers) {
        this.accepPers = accepPers;
    }

    public String getAccepDate() {
        return accepDate == null ? "" : accepDate;
    }

    public void setAccepDate(String accepDate) {
        this.accepDate = accepDate;
    }

    public String getAccepOpin() {
        return accepOpin == null ? "" : accepOpin;
    }

    public void setAccepOpin(String accepOpin) {
        this.accepOpin = accepOpin;
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
