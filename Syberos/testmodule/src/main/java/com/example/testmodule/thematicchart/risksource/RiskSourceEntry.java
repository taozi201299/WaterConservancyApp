package com.example.testmodule.thematicchart.risksource;


import com.example.testmodule.thematicchart.PointEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.sourcerist.
 */
public class RiskSourceEntry implements Serializable {
    private List<RiskPointEntry> pointEntryList;
    private RiskSituationEntry riskRiskSituationEntry;
    private RiskRankRateEntry riskRankRateEntry;
    private List<RiskStatisticsEntry> riskSourceEntryList;

    public List<RiskPointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<RiskPointEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

    public RiskSituationEntry getRiskRiskSituationEntry() {
        return riskRiskSituationEntry;
    }

    public void setRiskRiskSituationEntry(RiskSituationEntry riskRiskSituationEntry) {
        this.riskRiskSituationEntry = riskRiskSituationEntry;
    }

    public RiskRankRateEntry getRiskRankRateEntry() {
        return riskRankRateEntry;
    }

    public void setRiskRankRateEntry(RiskRankRateEntry riskRankRateEntry) {
        this.riskRankRateEntry = riskRankRateEntry;
    }

    public List<RiskStatisticsEntry> getRiskSourceEntryList() {
        if (riskSourceEntryList == null) {
            return new ArrayList<>();
        }
        return riskSourceEntryList;
    }

    public void setRiskSourceEntryList(List<RiskStatisticsEntry> riskSourceEntryList) {
        this.riskSourceEntryList = riskSourceEntryList;
    }
}
