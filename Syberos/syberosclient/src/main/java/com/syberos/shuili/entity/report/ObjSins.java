package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/9/11.
 * 安全检查报表
 */

public class ObjSins extends HttpBaseResponse<ObjSins> {


    /**
     * sinsStartTime : 2018-09-18 00:00:00
     * guid : 3d81e2357c5d45eaadf9a45705dc7aab
     * ifSendDown : 1
     * wiunName : 中华人民共和国水利部
     * orgCode : QQQQQQ460108000024
     * sinsCompTime : 2018-09-22 00:00:00
     * sinsDeplName : 0918test6
     * ROWNO : 1
     * collTime : 2018-09-18 17:55:20
     * swen : 海南省水务厅
     * checkTime : 2018-09-18至2018-09-22
     * notIssuGuid : D7862390F88443AE87FA9DD1FE45A8B6
     * sinsRange : 大型及重点中型已建工程(水库)
     */

    private String sinsStartTime;
    private String guid;
    private String ifSendDown;
    private String wiunName;
    private String orgCode;
    private String sinsCompTime;
    private String sinsDeplName;
    private int ROWNO;
    private String collTime;
    private String swen;
    private String checkTime;
    private String notIssuGuid;
    private String sinsRange;


    public String getSinsStartTime() {
        return sinsStartTime == null ? "" : sinsStartTime;
    }

    public void setSinsStartTime(String sinsStartTime) {
        this.sinsStartTime = sinsStartTime;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getIfSendDown() {
        return ifSendDown == null ? "" : ifSendDown;
    }

    public void setIfSendDown(String ifSendDown) {
        this.ifSendDown = ifSendDown;
    }

    public String getWiunName() {
        return wiunName == null ? "" : wiunName;
    }

    public void setWiunName(String wiunName) {
        this.wiunName = wiunName;
    }

    public String getOrgCode() {
        return orgCode == null ? "" : orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSinsCompTime() {
        return sinsCompTime == null ? "" : sinsCompTime;
    }

    public void setSinsCompTime(String sinsCompTime) {
        this.sinsCompTime = sinsCompTime;
    }

    public String getSinsDeplName() {
        return sinsDeplName == null ? "" : sinsDeplName;
    }

    public void setSinsDeplName(String sinsDeplName) {
        this.sinsDeplName = sinsDeplName;
    }

    public int getROWNO() {
        return ROWNO;
    }

    public void setROWNO(int ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getSwen() {
        return swen == null ? "" : swen;
    }

    public void setSwen(String swen) {
        this.swen = swen;
    }

    public String getCheckTime() {
        return checkTime == null ? "" : checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getNotIssuGuid() {
        return notIssuGuid == null ? "" : notIssuGuid;
    }

    public void setNotIssuGuid(String notIssuGuid) {
        this.notIssuGuid = notIssuGuid;
    }

    public String getSinsRange() {
        return sinsRange == null ? "" : sinsRange;
    }

    public void setSinsRange(String sinsRange) {
        this.sinsRange = sinsRange;
    }
}
