package com.syberos.shuili.entity;

/**
 * Created by Administrator on 2018/4/4.
 */

public class NoticeInfo extends HttpBaseListResponse<NoticeInfo> {

    String appCode;
    String fromDate;
    String guid;
    String isRead;
    String mtime;
    String noticeCode;
    String noticeContent;
    String noticeTitle;
    String orgGuid;
    String roleCode;
    String toDate;
    String userGuid;


    public String getAppCode() {
        return appCode == null ? "" : appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getFromDate() {
        return fromDate == null ? "" : fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getIsRead() {
        return isRead == null ? "" : isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getMtime() {
        return mtime == null ? "" : mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getNoticeCode() {
        return noticeCode == null ? "" : noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeContent() {
        return noticeContent == null ? "" : noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTitle() {
        return noticeTitle == null ? "" : noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
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

    public String getToDate() {
        return toDate == null ? "" : toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getUserGuid() {
        return userGuid == null ? "" : userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
