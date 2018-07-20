package com.syberos.shuili.entity.thematicchart.standardization;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.standardization.
 */
//标准化情况
public class StandardizationSituationEntry implements Serializable {
    private int hadStandardCount;
    private int startStandardCount;

    public int getHadStandardCount() {
        return hadStandardCount;
    }

    public void setHadStandardCount(int hadStandardCount) {
        this.hadStandardCount = hadStandardCount;
    }

    public int getStartStandardCount() {
        return startStandardCount;
    }

    public void setStartStandardCount(int startStandardCount) {
        this.startStandardCount = startStandardCount;
    }
}
