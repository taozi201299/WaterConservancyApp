package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 * 8.2.1.16	工作考核对象表（OBJ_WOAS）
 */

public class ObjWoas extends HttpBaseResponse<ObjWoas> {
    String guid;
    /**
     * 考核主题
     */
    String woasThem;
    /**
     * 发文号
     */
    String learId;
    /**
     * 文件标题
     */
    String fileTitle;
    /**
     * 考核类型
     */
    String woasType;
    /**
     * 考核要求
     */
    String woasRequ;
    /**
     * 考核组GUID
     */
    String woasGroupGuid;
    /**
     * 考核截止时间
     */
    String woasDeadLine;
    /**
     * 发送状态
     */
    String sendStat;
    String note;
    String collTime;
    String updTime;
    String recPers;
    String groupName;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWoasThem() {
        return woasThem;
    }

    public void setWoasThem(String woasThem) {
        this.woasThem = woasThem;
    }

    public String getLearId() {
        return learId;
    }

    public void setLearId(String learId) {
        this.learId = learId;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getWoasType() {
        return woasType;
    }

    public void setWoasType(String woasType) {
        this.woasType = woasType;
    }

    public String getWoasRequ() {
        return woasRequ;
    }

    public void setWoasRequ(String woasRequ) {
        this.woasRequ = woasRequ;
    }

    public String getWoasGroupGuid() {
        return woasGroupGuid;
    }

    public void setWoasGroupGuid(String woasGroupGuid) {
        this.woasGroupGuid = woasGroupGuid;
    }

    public String getWoasDeadLine() {
        return woasDeadLine;
    }

    public void setWoasDeadLine(String woasDeadLine) {
        this.woasDeadLine = woasDeadLine;
    }

    public String getSendStat() {
        return sendStat;
    }

    public void setSendStat(String sendStat) {
        this.sendStat = sendStat;
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
