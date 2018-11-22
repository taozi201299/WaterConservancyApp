package com.syberos.shuili.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jidan on 18-3-14.
 */

public class UserInformationEntity implements Parcelable {
    public String admDutyLevel;
    public String depCode;
    public String depId;
    public String depName;
    public String id ;
    public String modifier;
    public String note;
    public String orgCode;
    public String orgId;
    public String orgName;
    public String password;
    public String persId;
    public String persName;
    public String persType;
    public String phone;
    public String status;
    public String ts;
    public String userCode;
    public String userName;
    public String userType;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(admDutyLevel);
        dest.writeString(depCode);
        dest.writeString(depId);
        dest.writeString(depName);
        dest.writeString(id);
        dest.writeString(modifier);
        dest.writeString(note);
        dest.writeString(orgCode);
        dest.writeString(orgId);
        dest.writeString(orgName);
        dest.writeString(password);
        dest.writeString(persId);
        dest.writeString(persName);
        dest.writeString(persType);
        dest.writeString(phone);
        dest.writeString(status);
        dest.writeString(ts);
        dest.writeString(userCode);
        dest.writeString(userName);
        dest.writeString(userType);
    }
    public UserInformationEntity(String admDutyLevel,String depCode,String depId,String depName,String id,String modifier,
                                 String note,String orgCode,String orgId,String orgName,String password,
                                 String persId,String persName,String persType,String phone,String status,String ts,
                                 String userCode,String userName,String userType) {
         this.admDutyLevel = admDutyLevel == null ? "":admDutyLevel;
         this.depCode = depCode == null ? "":depCode;
         this.depId = depId == null ?"":depId;
         this.depName = depName == null ?"":depName;
         this.id = id == null ?"" :id;
         this.modifier = modifier == null ? "":modifier;
         this.note = note == null ? "":note;
         this.orgCode = orgCode == null ?"":orgCode;
         this.orgId = orgId  == null  ?"":orgId;
         this.orgName = orgName == null ?"":orgName;
         this.password = password == null ?"" :password;
         this.persId = persId == null ? "":persId;
         this.persName = persName == null ? "":persName;
         this.persType = persType == null ?"":persType;
         this.phone = phone == null ? "":phone;
         this.status = status == null ? "":status;
         this.ts = ts == null ? "":ts;
         this.userCode = userCode == null ? "":userCode;
         this.userName = userName == null ? "": userName;
         this.userType = userType == null ? "":userType;

    }
    protected UserInformationEntity(Parcel in){
        this.admDutyLevel = in.readString();
        this.depCode = in.readString();
        this.depId = in.readString();
        this.depName = in.readString();
        this.id = in.readString();
        this.modifier = in.readString();
        this.note = in.readString();
        this.orgCode = in.readString();
        this.orgId = in.readString();
        this.orgName = in.readString();
        this.password = in.readString();
        this.persId = in.readString();
        this.persName = in.readString();
        this.persType = in.readString();
        this.phone = in.readString();
        this.status = in.readString();
        this.ts = in.readString();
        this.userCode = in.readString();
        this.userName = in.readString();
        this.userType = in.readString();


    }

    public static  final Creator<UserInformationEntity>CREATOR = new Creator<UserInformationEntity>() {
        @Override
        public UserInformationEntity createFromParcel(Parcel source) {
            return new UserInformationEntity(source);
        }

        @Override
        public UserInformationEntity[] newArray(int size) {
            return new UserInformationEntity[size];
        }
    };

}
