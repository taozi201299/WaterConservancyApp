package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class RankRateEntry implements Serializable {
    private int normalHiddenCount;//一般隐患数量
    private int majorHiddenCount;//重大隐含数量

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

}
