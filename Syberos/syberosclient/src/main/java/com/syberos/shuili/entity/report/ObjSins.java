package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/9/11.
 */

public class ObjSins extends HttpBaseResponse<ObjSins> {


    private String guid;
    private String sinsDeplName;
    private String sinsStartTime;
    private String sinsCompTime;
    private String sinsRange;
    private String sinsLareId;
    private String sinsNotTime;
    private String notIssuGuid;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;
    private String ifSendDown;
    private String scheModel;
    private String pGuid;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSinsDeplName() {
        return sinsDeplName == null ? "" : sinsDeplName;
    }

    public void setSinsDeplName(String sinsDeplName) {
        this.sinsDeplName = sinsDeplName;
    }

    public String getSinsStartTime() {
        return sinsStartTime == null ? "" : sinsStartTime;
    }

    public void setSinsStartTime(String sinsStartTime) {
        this.sinsStartTime = sinsStartTime;
    }

    public String getSinsCompTime() {
        return sinsCompTime == null ? "" : sinsCompTime;
    }

    public void setSinsCompTime(String sinsCompTime) {
        this.sinsCompTime = sinsCompTime;
    }

    public String getSinsRange() {
        return sinsRange == null ? "" : sinsRange;
    }

    public void setSinsRange(String sinsRange) {
        this.sinsRange = sinsRange;
    }

    public String getSinsLareId() {
        return sinsLareId == null ? "" : sinsLareId;
    }

    public void setSinsLareId(String sinsLareId) {
        this.sinsLareId = sinsLareId;
    }

    public String getSinsNotTime() {
        return sinsNotTime == null ? "" : sinsNotTime;
    }

    public void setSinsNotTime(String sinsNotTime) {
        this.sinsNotTime = sinsNotTime;
    }

    public String getNotIssuGuid() {
        return notIssuGuid == null ? "" : notIssuGuid;
    }

    public void setNotIssuGuid(String notIssuGuid) {
        this.notIssuGuid = notIssuGuid;
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

    public String getIfSendDown() {
        return ifSendDown == null ? "" : ifSendDown;
    }

    public void setIfSendDown(String ifSendDown) {
        this.ifSendDown = ifSendDown;
    }

    public String getScheModel() {
        return scheModel == null ? "" : scheModel;
    }

    public void setScheModel(String scheModel) {
        this.scheModel = scheModel;
    }

    public String getpGuid() {
        return pGuid == null ? "" : pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }
}
