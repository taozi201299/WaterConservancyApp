package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * Created by jidan on 18-3-19.
 */

public class MessageInfo implements Serializable {

    String messageId;
    String title;
    String content;
    String publisher;
    String publishDate;
    String readStatus;
    String serverDate;
    String organizationId;


    public String getMessageId() {
        return messageId == null ? "" : messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher == null ? "" : publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate == null ? "" : publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getReadStatus() {
        return readStatus == null ? "" : readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getServerDate() {
        return serverDate == null ? "" : serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    public String getOrganizationId() {
        return organizationId == null ? "" : organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
