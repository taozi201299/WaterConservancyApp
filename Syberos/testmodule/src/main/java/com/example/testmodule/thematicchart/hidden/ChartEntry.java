package com.example.testmodule.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class ChartEntry implements Parcelable {
    String chartTitle; //(xxxx年直管工程隐患情况)
    int notRectifiedCount;//未整改数量
    int totalHiddenCount;//隐患总数量

    public String getChartTitle() {
        return chartTitle == null ? "" : chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public int getNotRectifiedCount() {
        return notRectifiedCount;
    }

    public void setNotRectifiedCount(int notRectifiedCount) {
        this.notRectifiedCount = notRectifiedCount;
    }

    public int getTotalHiddenCount() {
        return totalHiddenCount;
    }

    public void setTotalHiddenCount(int totalHiddenCount) {
        this.totalHiddenCount = totalHiddenCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.chartTitle);
        dest.writeInt(this.notRectifiedCount);
        dest.writeInt(this.totalHiddenCount);
    }

    public ChartEntry() {
    }

    protected ChartEntry(Parcel in) {
        this.chartTitle = in.readString();
        this.notRectifiedCount = in.readInt();
        this.totalHiddenCount = in.readInt();
    }

    public static final Creator<ChartEntry> CREATOR = new Creator<ChartEntry>() {
        @Override
        public ChartEntry createFromParcel(Parcel source) {
            return new ChartEntry(source);
        }

        @Override
        public ChartEntry[] newArray(int size) {
            return new ChartEntry[size];
        }
    };
}
