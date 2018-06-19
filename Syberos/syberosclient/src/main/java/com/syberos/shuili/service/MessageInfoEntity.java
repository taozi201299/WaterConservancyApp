package com.syberos.shuili.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jidan on 18-3-19.
 */

public class MessageInfoEntity implements Parcelable {

    public String messageId;
    public String title;
    public String content;
    public String publisher;
    public String publishDate;
    public String readStatus;
    public String serverDate;
    public String organizationId;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(messageId);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(publisher);
        dest.writeString(publishDate);
        dest.writeString(readStatus);
        dest.writeString(serverDate);
        dest.writeString(organizationId);

    }

    public MessageInfoEntity(String messageId,String title,String content,String publisher,
                             String publishDate,String readStatus,String serverDate,String organizationId){
        this.messageId = messageId;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.readStatus = readStatus;
        this.serverDate = serverDate;
        this.organizationId = organizationId;
    }
    protected MessageInfoEntity(Parcel in){
        messageId = in.readString();
        title = in.readString();
        content = in.readString();
        publisher = in.readString();
        publishDate = in.readString();
        readStatus = in.readString();
        serverDate = in.readString();
        organizationId = in.readString();
    }
    public static final Creator<MessageInfoEntity>CREATOR = new Creator<MessageInfoEntity>() {
        @Override
        public MessageInfoEntity createFromParcel(Parcel source) {
            return new MessageInfoEntity(source);
        }

        @Override
        public MessageInfoEntity[] newArray(int size) {
            return new MessageInfoEntity[size];
        }
    };
}
