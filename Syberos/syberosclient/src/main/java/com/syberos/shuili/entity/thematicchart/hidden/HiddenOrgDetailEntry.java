package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class HiddenOrgDetailEntry implements Serializable{
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

}
