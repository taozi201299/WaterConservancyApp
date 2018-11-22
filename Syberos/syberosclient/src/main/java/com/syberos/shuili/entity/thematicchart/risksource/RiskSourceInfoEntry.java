package com.syberos.shuili.entity.thematicchart.risksource;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.risksource.
 */
public class RiskSourceInfoEntry implements Serializable {
    private String title;//标题
    private int riskManageRate;//危险管控率
    private int riskCount;//危险源数量
    private String recordRate;//备案率(10%)

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRiskManageRate() {
        return riskManageRate;
    }

    public void setRiskManageRate(int riskManageRate) {
        this.riskManageRate = riskManageRate;
    }

    public int getRiskCount() {
        return riskCount;
    }

    public void setRiskCount(int riskCount) {
        this.riskCount = riskCount;
    }

    public String getRecordRate() {
        return recordRate == null ? "" : recordRate;
    }

    public void setRecordRate(String recordRate) {
        this.recordRate = recordRate;
    }
}
