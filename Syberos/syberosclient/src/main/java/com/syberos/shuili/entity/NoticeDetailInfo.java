package com.syberos.shuili.entity;

/**
 * Created by Administrator on 2018/4/4.
 */

public class NoticeDetailInfo extends HttpBaseOneResponse<NoticeDetailInfo> {
    public String appGuid;
    public String fromDate;
    public String guid;
    public String isRead;
    public String noticeCode;
    public String noticeContent;
    public String noticeTitle;
    public String toDate;
    public String userGuid;


    public String getAppGuid() {
        return appGuid == null ? "" : appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
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
