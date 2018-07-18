package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class HiddenProjectDetailEntry implements Parcelable {
    ChartEntry chartEntry;
    RankRateEntry rankRateEntry;
    NormalHiddenEntry normalHiddenEntry;
    MajorHiddenEntry majorHiddenEntry;


    public ChartEntry getChartEntry() {
        return chartEntry;
    }

    public void setChartEntry(ChartEntry chartEntry) {
        this.chartEntry = chartEntry;
    }

    public RankRateEntry getRankRateEntry() {
        return rankRateEntry;
    }

    public void setRankRateEntry(RankRateEntry rankRateEntry) {
        this.rankRateEntry = rankRateEntry;
    }

    public NormalHiddenEntry getNormalHiddenEntry() {
        return normalHiddenEntry;
    }

    public void setNormalHiddenEntry(NormalHiddenEntry normalHiddenEntry) {
        this.normalHiddenEntry = normalHiddenEntry;
    }

    public MajorHiddenEntry getMajorHiddenEntry() {
        return majorHiddenEntry;
    }

    public void setMajorHiddenEntry(MajorHiddenEntry majorHiddenEntry) {
        this.majorHiddenEntry = majorHiddenEntry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.chartEntry, flags);
        dest.writeParcelable(this.rankRateEntry, flags);
        dest.writeParcelable(this.normalHiddenEntry, flags);
        dest.writeParcelable(this.majorHiddenEntry, flags);
    }

    public HiddenProjectDetailEntry() {
    }

    protected HiddenProjectDetailEntry(Parcel in) {
        this.chartEntry = in.readParcelable(ChartEntry.class.getClassLoader());
        this.rankRateEntry = in.readParcelable(RankRateEntry.class.getClassLoader());
        this.normalHiddenEntry = in.readParcelable(NormalHiddenEntry.class.getClassLoader());
        this.majorHiddenEntry = in.readParcelable(MajorHiddenEntry.class.getClassLoader());
    }

    public static final Parcelable.Creator<HiddenProjectDetailEntry> CREATOR = new Parcelable.Creator<HiddenProjectDetailEntry>() {
        @Override
        public HiddenProjectDetailEntry createFromParcel(Parcel source) {
            return new HiddenProjectDetailEntry(source);
        }

        @Override
        public HiddenProjectDetailEntry[] newArray(int size) {
            return new HiddenProjectDetailEntry[size];
        }
    };
}
