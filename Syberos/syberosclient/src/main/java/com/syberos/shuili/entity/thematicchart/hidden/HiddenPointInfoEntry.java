package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class HiddenPointInfoEntry implements Parcelable {
    String hiddenPointName; //隐患info的标题
    String hiddenRectifyRate;// 隐患整改率(百分值)
    int hiddenCount; //隐患数量
    int rectifyCount;//整改数量

    public String getHiddenPointName() {
        return hiddenPointName == null ? "" : hiddenPointName;
    }

    public void setHiddenPointName(String hiddenPointName) {
        this.hiddenPointName = hiddenPointName;
    }

    public String getHiddenRectifyRate() {
        return hiddenRectifyRate == null ? "" : hiddenRectifyRate;
    }

    public void setHiddenRectifyRate(String hiddenRectifyRate) {
        this.hiddenRectifyRate = hiddenRectifyRate;
    }

    public int getHiddenCount() {
        return hiddenCount;
    }

    public void setHiddenCount(int hiddenCount) {
        this.hiddenCount = hiddenCount;
    }

    public int getRectifyCount() {
        return rectifyCount;
    }

    public void setRectifyCount(int rectifyCount) {
        this.rectifyCount = rectifyCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hiddenPointName);
        dest.writeString(this.hiddenRectifyRate);
        dest.writeInt(this.hiddenCount);
        dest.writeInt(this.rectifyCount);
    }

    public HiddenPointInfoEntry() {
    }

    protected HiddenPointInfoEntry(Parcel in) {
        this.hiddenPointName = in.readString();
        this.hiddenRectifyRate = in.readString();
        this.hiddenCount = in.readInt();
        this.rectifyCount = in.readInt();
    }

    public static final Parcelable.Creator<HiddenPointInfoEntry> CREATOR = new Parcelable.Creator<HiddenPointInfoEntry>() {
        @Override
        public HiddenPointInfoEntry createFromParcel(Parcel source) {
            return new HiddenPointInfoEntry(source);
        }

        @Override
        public HiddenPointInfoEntry[] newArray(int size) {
            return new HiddenPointInfoEntry[size];
        }
    };
}
