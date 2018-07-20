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
public class HiddenDetailEntry implements Serializable {

    List<HiddenPointEntry> pointEntryList;
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

    public List<HiddenPointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<HiddenPointEntry> pointEntryList) {
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

}
