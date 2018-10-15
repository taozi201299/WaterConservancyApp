package com.syberos.shuili.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/4/14.
 */

public class AttachMentInfoEntity implements Parcelable {
    public String url;
    public  String medName;
    public String medType;
    public String medPath;
    public String collTime;
    public String bisTableName;
    public String bisGuid;
    public String fromDate;
    public String localStatus;
    public String seriesKey;
    public String medTitl;
    public String medExt;
    public String recPers;

    public long getMedSize() {
        return medSize;
    }

    public void setMedSize(long medSize) {
        this.medSize = medSize;
    }

    public long medSize;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(url);
        dest.writeString(medName);
        dest.writeString(medType);
        dest.writeString(medPath);
        dest.writeString(collTime);
        dest.writeString(bisTableName);
        dest.writeString(bisGuid);
        dest.writeString(fromDate);
        dest.writeString(localStatus);
        dest.writeString(seriesKey);
        dest.writeString(medTitl);
        dest.writeString(medExt);
        dest.writeString(recPers);
    }
    public AttachMentInfoEntity(){

    }
    public AttachMentInfoEntity(String url, String medName, String medType, String medPath,
                                String collTime, String bisTableName,
                                String bisGuid, String fromDate, String localStatus,String seriesKey,String medTitl,
                                String medExt,String recPers ){
        this.url = url;
        this.medName = medName;
        this.medType = medType;
        this.medPath = medPath;
        this.collTime = collTime;
        this.bisTableName = bisTableName;
        this.bisGuid = bisGuid;
        this.fromDate = fromDate;
        this.localStatus = localStatus;
        this.seriesKey = seriesKey;
        this.medTitl = medTitl;
        this.medExt = medExt;
        this.recPers = recPers;

    }
    protected AttachMentInfoEntity(Parcel in){
        this.url = in.readString();
        this.medName = in.readString();
        this.medType = in.readString();
        this.medPath = in.readString();
        this.collTime = in.readString();
        this.bisTableName = in.readString();
        this.bisGuid = in.readString();
        this.fromDate = in.readString();
        this.localStatus = in.readString();
        this.seriesKey = in.readString();
        this.medTitl = in.readString();
        this.medExt = in.readString();
        this.recPers = in.readString();
    }
    public static final Creator<AttachMentInfoEntity>CREATOR = new Creator<AttachMentInfoEntity>() {
        @Override
        public AttachMentInfoEntity createFromParcel(Parcel source) {
            return new AttachMentInfoEntity(source);
        }

        @Override
        public AttachMentInfoEntity[] newArray(int size) {
            return new AttachMentInfoEntity[size];
        }
    };
}
