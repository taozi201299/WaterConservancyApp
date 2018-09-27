package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/12.
 */

public class HiddenInvesInfo extends HttpBaseResponse<HiddenInvesInfo> {
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

    public String getInspOrgGuid() {
        return inspOrgGuid == null ? "" : inspOrgGuid;
    }

    public void setInspOrgGuid(String inspOrgGuid) {
        this.inspOrgGuid = inspOrgGuid;
    }

    public String getInspDate() {
        return inspDate == null ? "" : inspDate;
    }

    public void setInspDate(String inspDate) {
        this.inspDate = inspDate;
    }

    public String getInspLeader() {
        return inspLeader == null ? "" : inspLeader;
    }

    public void setInspLeader(String inspLeader) {
        this.inspLeader = inspLeader;
    }

    public String getInspLeaderPost() {
        return inspLeaderPost == null ? "" : inspLeaderPost;
    }

    public void setInspLeaderPost(String inspLeaderPost) {
        this.inspLeaderPost = inspLeaderPost;
    }

    public String getInspMem() {
        return inspMem == null ? "" : inspMem;
    }

    public void setInspMem(String inspMem) {
        this.inspMem = inspMem;
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

    public String getUnitName() {
        return unitName == null ? "" : unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
