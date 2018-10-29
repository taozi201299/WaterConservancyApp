package com.syberos.shuili.entity.dangersource;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/20.
 * 危险源备案
 */


public class BisHazReg extends HttpBaseResponse<BisHazReg> {
    private String hazName;
    private String guid;
    private String orgGuid;
    private String requestTime;
    private String regTime;
    private String orderCode;
    private String wiunName;
    private String orgCode;
    private int ROWNO;
    private String collTime;
    private String hazStat;
    private String adminWiunGuid;
    private String engName;
    private String writeOffTime;

    public String getHazName() {
        return hazName == null ? "" : hazName;
    }

    public void setHazName(String hazName) {
        this.hazName = hazName;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getRequestTime() {
        return requestTime == null ? "" : requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRegTime() {
        return regTime == null ? "" : regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getOrderCode() {
        return orderCode == null ? "" : orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getHazStat() {
        return hazStat == null ? "" : hazStat;
    }

    public void setHazStat(String hazStat) {
        this.hazStat = hazStat;
    }

    public String getAdminWiunGuid() {
        return adminWiunGuid == null ? "" : adminWiunGuid;
    }

    public void setAdminWiunGuid(String adminWiunGuid) {
        this.adminWiunGuid = adminWiunGuid;
    }

    public String getEngName() {
        return engName == null ? "" : engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getWriteOffTime() {
        return writeOffTime == null ? "" : writeOffTime;
    }

    public void setWriteOffTime(String writeOffTime) {
        this.writeOffTime = writeOffTime;
    }
}
