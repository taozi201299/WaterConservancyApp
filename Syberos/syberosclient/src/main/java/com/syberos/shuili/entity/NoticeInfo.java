package com.syberos.shuili.entity;

/**
 * Created by Administrator on 2018/4/4.
 */

public class NoticeInfo extends HttpBaseListResponse<NoticeInfo> {

    public String guid;
    public String noticeTitle;
    public String isRead;
    public String fromDate;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
