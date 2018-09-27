package com.syberos.shuili.entity;

/**
 * Created by Administrator on 2018/4/4.
 */

public class NoticeInfo extends HttpBaseListResponse<NoticeInfo> {

    public String guid;
    public String noticeTitle;
    public String isRead;
    public String fromDate;

    public String getNoticeContent() {
        return noticeContent == null ? "" : noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String noticeContent;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNoticeTitle() {
        return noticeTitle == null ? "" : noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getIsRead() {
        return isRead == null ? "" : isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getFromDate() {
        return fromDate == null ? "" : fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
