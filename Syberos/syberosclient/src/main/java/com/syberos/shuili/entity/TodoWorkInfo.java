package com.syberos.shuili.entity;

/**
 * Created by Administrator on 2018/4/4.
 */

public class TodoWorkInfo extends HttpBaseListResponse<TodoWorkInfo> {
    String appCode;
    String busiCode;
    String busiName;
    String busiUrl;
    String collTime;
    String guid;
    String isread;
    String modName;
    String mtime;
    String nextStep;
    String orgGuid;
    String roleCode;
    String tableName;
    String userGuid;
    public String getBusiName() {
        return busiName == null ? "" : busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }
    public String getAppCode() {
        return appCode == null ? "" : appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getBusiCode() {
        return busiCode == null ? "" : busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getBusiUrl() {
        return busiUrl == null ? "" : busiUrl;
    }

    public void setBusiUrl(String busiUrl) {
        this.busiUrl = busiUrl;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getIsread() {
        return isread == null ? "" : isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getModName() {
        return modName == null ? "" : modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getMtime() {
        return mtime == null ? "" : mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getNextStep() {
        return nextStep == null ? "" : nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getRoleCode() {
        return roleCode == null ? "" : roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getTableName() {
        return tableName == null ? "" : tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUserGuid() {
        return userGuid == null ? "" : userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
