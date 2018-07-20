package com.syberos.shuili.entity.thematicchart.hidden;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class HiddenPointInfoEntry implements Serializable{
    String hiddenPointName; //隐患info的标题
    String hiddenRectifyRate;// 隐患整改率(百分值)
    int hiddenCount; //隐患数量
    int rectifyCount;//整改数量

    public String getHiddenPointName() {
        return hiddenPointName == null ? "" : hiddenPointName;
    }

    public void setHiddenPointName(String hiddenPointName) {
        this.hiddenPointName = hiddenPointName;
    }

    public String getHiddenRectifyRate() {
        return hiddenRectifyRate == null ? "" : hiddenRectifyRate;
    }

    public void setHiddenRectifyRate(String hiddenRectifyRate) {
        this.hiddenRectifyRate = hiddenRectifyRate;
    }

    public int getHiddenCount() {
        return hiddenCount;
    }

    public void setHiddenCount(int hiddenCount) {
        this.hiddenCount = hiddenCount;
    }

    public int getRectifyCount() {
        return rectifyCount;
    }

    public void setRectifyCount(int rectifyCount) {
        this.rectifyCount = rectifyCount;
    }


}
