package com.example.testmodule.thematicchart.risksource;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.sourcerist.
 */
public class RiskSituationEntry implements Serializable {
    //    xxxx年直管工程风险源情况
    private String riskSituationTitle; //标题
    private int hasRegulatoryControlCount;//已管控数量
    private int noRegulatoryControlCount;// 未管控 数量

    public String getRiskSituationTitle() {
        return riskSituationTitle == null ? "" : riskSituationTitle;
    }

    public void setRiskSituationTitle(String riskSituationTitle) {
        this.riskSituationTitle = riskSituationTitle;
    }

    public int getHasRegulatoryControlCount() {
        return hasRegulatoryControlCount;
    }

    public void setHasRegulatoryControlCount(int hasRegulatoryControlCount) {
        this.hasRegulatoryControlCount = hasRegulatoryControlCount;
    }

    public int getNoRegulatoryControlCount() {
        return noRegulatoryControlCount;
    }

    public void setNoRegulatoryControlCount(int noRegulatoryControlCount) {
        this.noRegulatoryControlCount = noRegulatoryControlCount;
    }
}
