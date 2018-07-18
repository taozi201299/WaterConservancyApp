package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class MajorHiddenEntry implements Parcelable {
    int majorHiddenHadRectifiedCout;   //重大隐患   已整改数
    int majorHiddenNotRectifiedCount;//重大隐患 未整改数
    String majorHiddenRectifieRate; //整改率(百分值 eg:20%)
    int lateOfMajorRetifiedCount;// 重大隐患 已逾期数
    int hadSupervisingCount;//重大隐患 已经督办数

    public int getMajorHiddenHadRectifiedCout() {
        return majorHiddenHadRectifiedCout;
    }

    public void setMajorHiddenHadRectifiedCout(int majorHiddenHadRectifiedCout) {
        this.majorHiddenHadRectifiedCout = majorHiddenHadRectifiedCout;
    }

    public int getMajorHiddenNotRectifiedCount() {
        return majorHiddenNotRectifiedCount;
    }

    public void setMajorHiddenNotRectifiedCount(int majorHiddenNotRectifiedCount) {
        this.majorHiddenNotRectifiedCount = majorHiddenNotRectifiedCount;
    }

    public String getMajorHiddenRectifieRate() {
        return majorHiddenRectifieRate == null ? "" : majorHiddenRectifieRate;
    }

    public void setMajorHiddenRectifieRate(String majorHiddenRectifieRate) {
        this.majorHiddenRectifieRate = majorHiddenRectifieRate;
    }

    public int getLateOfMajorRetifiedCount() {
        return lateOfMajorRetifiedCount;
    }

    public void setLateOfMajorRetifiedCount(int lateOfMajorRetifiedCount) {
        this.lateOfMajorRetifiedCount = lateOfMajorRetifiedCount;
    }

    public int getHadSupervisingCount() {
        return hadSupervisingCount;
    }

    public void setHadSupervisingCount(int hadSupervisingCount) {
        this.hadSupervisingCount = hadSupervisingCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.majorHiddenHadRectifiedCout);
        dest.writeInt(this.majorHiddenNotRectifiedCount);
        dest.writeString(this.majorHiddenRectifieRate);
        dest.writeInt(this.lateOfMajorRetifiedCount);
        dest.writeInt(this.hadSupervisingCount);
    }

    public MajorHiddenEntry() {
    }

    protected MajorHiddenEntry(Parcel in) {
        this.majorHiddenHadRectifiedCout = in.readInt();
        this.majorHiddenNotRectifiedCount = in.readInt();
        this.majorHiddenRectifieRate = in.readString();
        this.lateOfMajorRetifiedCount = in.readInt();
        this.hadSupervisingCount = in.readInt();
    }

    public static final Parcelable.Creator<MajorHiddenEntry> CREATOR = new Parcelable.Creator<MajorHiddenEntry>() {
        @Override
        public MajorHiddenEntry createFromParcel(Parcel source) {
            return new MajorHiddenEntry(source);
        }

        @Override
        public MajorHiddenEntry[] newArray(int size) {
            return new MajorHiddenEntry[size];
        }
    };
}
