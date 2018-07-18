package com.syberos.shuili.entity.thematicchart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class PointEntry implements Parcelable {
    String pointId;//地图点的 id
    String pointLongitude; //点的 jinngdu 坐标
    String pointLatitude;//点的 纬度坐标
    String pointValue; //点的 值

    public String getPointId() {
        return pointId == null ? "" : pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointLongitude() {
        return pointLongitude == null ? "" : pointLongitude;
    }

    public void setPointLongitude(String pointLongitude) {
        this.pointLongitude = pointLongitude;
    }

    public String getPointLatitude() {
        return pointLatitude == null ? "" : pointLatitude;
    }

    public void setPointLatitude(String pointLatitude) {
        this.pointLatitude = pointLatitude;
    }

    public String getPointValue() {
        return pointValue == null ? "" : pointValue;
    }

    public void setPointValue(String pointValue) {
        this.pointValue = pointValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pointId);
        dest.writeString(this.pointLongitude);
        dest.writeString(this.pointLatitude);
        dest.writeString(this.pointValue);
    }

    public PointEntry() {
    }

    protected PointEntry(Parcel in) {
        this.pointId = in.readString();
        this.pointLongitude = in.readString();
        this.pointLatitude = in.readString();
        this.pointValue = in.readString();
    }

}
