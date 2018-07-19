package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.
 */
public class HiddenDetailEntry implements Parcelable {

    List<PointEntry> pointEntryList;
    //   隐患排查单位占比图
    ChartEntry chartEntry;
    //   隐患等级占比
    RankRateEntry rankRateEntry;
    //    一般隐患数据
    NormalHiddenEntry normalHiddenEntry;
    //    重大隐患数据
    MajorHiddenEntry majorHinddenEntry;
    //    隐患 统计
    List<ProjectEntry> dataList;
    public List<PointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<PointEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

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

    public MajorHiddenEntry getMajorHinddenEntry() {
        return majorHinddenEntry;
    }

    public void setMajorHinddenEntry(MajorHiddenEntry majorHinddenEntry) {
        this.majorHinddenEntry = majorHinddenEntry;
    }

    public List<ProjectEntry> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<ProjectEntry> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.pointEntryList);
        dest.writeParcelable(this.chartEntry, flags);
        dest.writeParcelable(this.rankRateEntry, flags);
        dest.writeParcelable(this.normalHiddenEntry, flags);
        dest.writeParcelable(this.majorHinddenEntry, flags);
        dest.writeList(this.dataList);
    }

    public HiddenDetailEntry() {
    }

    protected HiddenDetailEntry(Parcel in) {
      //  this.pointEntryList = in.createTypedArrayList(PointEntry.CREATOR);
        this.chartEntry = in.readParcelable(ChartEntry.class.getClassLoader());
        this.rankRateEntry = in.readParcelable(RankRateEntry.class.getClassLoader());
        this.normalHiddenEntry = in.readParcelable(NormalHiddenEntry.class.getClassLoader());
        this.majorHinddenEntry = in.readParcelable(MajorHiddenEntry.class.getClassLoader());
        this.dataList = new ArrayList<ProjectEntry>();
        in.readList(this.dataList, ProjectEntry.class.getClassLoader());
    }

    public static final Parcelable.Creator<HiddenDetailEntry> CREATOR = new Parcelable.Creator<HiddenDetailEntry>() {
        @Override
        public HiddenDetailEntry createFromParcel(Parcel source) {
            return new HiddenDetailEntry(source);
        }

        @Override
        public HiddenDetailEntry[] newArray(int size) {
            return new HiddenDetailEntry[size];
        }
    };
}
