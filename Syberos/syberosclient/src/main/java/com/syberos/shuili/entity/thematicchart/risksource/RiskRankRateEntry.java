package com.syberos.shuili.entity.thematicchart.risksource;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.sourcerist.
 */
//危险源等级占比
public class RiskRankRateEntry implements Serializable {
    //    一般危险源 7个
//
//重大危险源 3个
    private int normalRiskSource;//一般危险源数
    private int MajorRiskSource;//重大 危险源数据

    public int getNormalRiskSource() {
        return normalRiskSource;
    }

    public void setNormalRiskSource(int normalRiskSource) {
        this.normalRiskSource = normalRiskSource;
    }

    public int getMajorRiskSource() {
        return MajorRiskSource;
    }

    public void setMajorRiskSource(int majorRiskSource) {
        MajorRiskSource = majorRiskSource;
    }
}
