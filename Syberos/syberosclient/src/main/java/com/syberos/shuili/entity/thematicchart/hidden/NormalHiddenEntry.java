package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class NormalHiddenEntry implements Parcelable {
    int normalHiddenHadRectifiedCout;   //一般隐患已整改数
    int normalHiddenNotRectifiedCount;  //一般隐患 未整改数据
    String normalHiddenRectifieRate; //整改率(百分值 eg:20%)
    int lateOfNormalRetifiedCount;  //一般隐患 逾期数

    public int getNormalHiddenHadRectifiedCout() {
        return normalHiddenHadRectifiedCout;
    }

    public void setNormalHiddenHadRectifiedCout(int normalHiddenHadRectifiedCout) {
        this.normalHiddenHadRectifiedCout = normalHiddenHadRectifiedCout;
    }

    public int getNormalHiddenNotRectifiedCount() {
        return normalHiddenNotRectifiedCount;
    }

    public void setNormalHiddenNotRectifiedCount(int normalHiddenNotRectifiedCount) {
        this.normalHiddenNotRectifiedCount = normalHiddenNotRectifiedCount;
    }

    public String getNormalHiddenRectifieRate() {
        return normalHiddenRectifieRate == null ? "" : normalHiddenRectifieRate;
    }

    public void setNormalHiddenRectifieRate(String normalHiddenRectifieRate) {
        this.normalHiddenRectifieRate = normalHiddenRectifieRate;
    }

    public int getLateOfNormalRetifiedCount() {
        return lateOfNormalRetifiedCount;
    }

    public void setLateOfNormalRetifiedCount(int lateOfNormalRetifiedCount) {
        this.lateOfNormalRetifiedCount = lateOfNormalRetifiedCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.normalHiddenHadRectifiedCout);
        dest.writeInt(this.normalHiddenNotRectifiedCount);
        dest.writeString(this.normalHiddenRectifieRate);
        dest.writeInt(this.lateOfNormalRetifiedCount);
    }

    public NormalHiddenEntry() {
    }

    protected NormalHiddenEntry(Parcel in) {
        this.normalHiddenHadRectifiedCout = in.readInt();
        this.normalHiddenNotRectifiedCount = in.readInt();
        this.normalHiddenRectifieRate = in.readString();
        this.lateOfNormalRetifiedCount = in.readInt();
    }

    public static final Parcelable.Creator<NormalHiddenEntry> CREATOR = new Parcelable.Creator<NormalHiddenEntry>() {
        @Override
        public NormalHiddenEntry createFromParcel(Parcel source) {
            return new NormalHiddenEntry(source);
        }

        @Override
        public NormalHiddenEntry[] newArray(int size) {
            return new NormalHiddenEntry[size];
        }
    };
}
