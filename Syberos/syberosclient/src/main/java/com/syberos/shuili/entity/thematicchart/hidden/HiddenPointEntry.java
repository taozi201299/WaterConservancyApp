package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

import com.syberos.shuili.entity.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/18.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class HiddenPointEntry extends PointEntry implements Parcelable {
    HiddenPointInfoEntry hiddenPointInfoEntry;

    public HiddenPointInfoEntry getHiddenPointInfoEntry() {
        return hiddenPointInfoEntry;
    }

    public void setHiddenPointInfoEntry(HiddenPointInfoEntry hiddenPointInfoEntry) {
        this.hiddenPointInfoEntry = hiddenPointInfoEntry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.hiddenPointInfoEntry, flags);
    }

    public HiddenPointEntry() {
    }

    protected HiddenPointEntry(Parcel in) {
        super(in);
        this.hiddenPointInfoEntry = in.readParcelable(HiddenPointInfoEntry.class.getClassLoader());
    }

    public static final Creator<HiddenPointEntry> CREATOR = new Creator<HiddenPointEntry>() {
        @Override
        public HiddenPointEntry createFromParcel(Parcel source) {
            return new HiddenPointEntry(source);
        }

        @Override
        public HiddenPointEntry[] newArray(int size) {
            return new HiddenPointEntry[size];
        }
    };
}
