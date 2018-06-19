package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/12.
 */

public class HiddenInvesInfo extends HttpHiddenBaseResponse<HiddenInvesInfo> {
    String username;
    String timestamp;
    String nonce;
    String sign;
    String guid;
    /**
     * 隐患GUID
     */
    String hiddGuid;
    /**
     * 排查单位GUID
     */
    String inspOrgGuid;
    /**
     * 排查日期
     */
    String inspDate;
    /**
     * 排查组组长
     */
    String inspLeader;
    /**
     * 排查组长职务
     */
    String inspLeaderPost;
    /**
     * 排查组成员
     */
    String inspMem;
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
    String unitName;

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

    public String getHiddGuid() {
        return hiddGuid;
    }

    public void setHiddGuid(String hiddGuid) {
        this.hiddGuid = hiddGuid;
    }

    public String getInspOrgGuid() {
        return inspOrgGuid;
    }

    public void setInspOrgGuid(String inspOrgGuid) {
        this.inspOrgGuid = inspOrgGuid;
    }

    public String getInspDate() {
        return inspDate;
    }

    public void setInspDate(String inspDate) {
        this.inspDate = inspDate;
    }

    public String getInspLeader() {
        return inspLeader;
    }

    public void setInspLeader(String inspLeader) {
        this.inspLeader = inspLeader;
    }

    public String getInspLeaderPost() {
        return inspLeaderPost;
    }

    public void setInspLeaderPost(String inspLeaderPost) {
        this.inspLeaderPost = inspLeaderPost;
    }

    public String getInspMem() {
        return inspMem;
    }

    public void setInspMem(String inspMem) {
        this.inspMem = inspMem;
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

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }
}
