package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class RankRateEntry implements Parcelable {
    int normalHiddenCount;//一般隐患数量
    int majorHiddenCount;//重大隐含数量

    public int getNormalHiddenCount() {
        return normalHiddenCount;
    }

    public void setNormalHiddenCount(int normalHiddenCount) {
        this.normalHiddenCount = normalHiddenCount;
    }

    public int getMajorHiddenCount() {
        return majorHiddenCount;
    }

    public void setMajorHiddenCount(int majorHiddenCount) {
        this.majorHiddenCount = majorHiddenCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.normalHiddenCount);
        dest.writeInt(this.majorHiddenCount);
    }

    public RankRateEntry() {
    }

    protected RankRateEntry(Parcel in) {
        this.normalHiddenCount = in.readInt();
        this.majorHiddenCount = in.readInt();
    }

    public static final Parcelable.Creator<RankRateEntry> CREATOR = new Parcelable.Creator<RankRateEntry>() {
        @Override
        public RankRateEntry createFromParcel(Parcel source) {
            return new RankRateEntry(source);
        }

        @Override
        public RankRateEntry[] newArray(int size) {
            return new RankRateEntry[size];
        }
    };
}
