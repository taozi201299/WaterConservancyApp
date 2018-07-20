package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

import com.syberos.shuili.entity.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/18.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class HiddenPointEntry extends PointEntry {
    HiddenPointInfoEntry hiddenPointInfoEntry;

    public HiddenPointInfoEntry getHiddenPointInfoEntry() {
        return hiddenPointInfoEntry;
    }

    public void setHiddenPointInfoEntry(HiddenPointInfoEntry hiddenPointInfoEntry) {
        this.hiddenPointInfoEntry = hiddenPointInfoEntry;
    }
}
