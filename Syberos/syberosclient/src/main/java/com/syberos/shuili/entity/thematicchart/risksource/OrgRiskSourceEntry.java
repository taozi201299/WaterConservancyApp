package com.syberos.shuili.entity.thematicchart.risksource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.sourcerist.
 */
public class OrgRiskSourceEntry implements Serializable {
    private RiskSituationEntry riskRiskSituationEntry;
    private RiskRankRateEntry riskRankRateEntry;
    private List<RiskStatisticsEntry> riskSourceEntryList;//一般风险源\重大风险源\危化品危险源


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
