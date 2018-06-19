package com.syberos.shuili.entity;

/**
 * Created by Administrator on 2018/4/4.
 */

public class TodoWorkInfo extends HttpBaseListResponse<TodoWorkInfo> {
    String appField;
    String appGuid;
    String busiCode;
    String busiName;
    String busiUrl;
    String fromDate;
    String guid;
    String roleGuid;
    String toDate;
    String userGuid;

    public String getAppField() {
        return appField;
    }

    public void setAppField(String appField) {
        this.appField = appField;
    }

    public String getAppGuid() {
        return appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public String getBusiUrl() {
        return busiUrl;
    }

    public void setBusiUrl(String busiUrl) {
        this.busiUrl = busiUrl;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
