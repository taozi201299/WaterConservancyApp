package com.example.testmodule.thematicchart.risksource;

import com.example.testmodule.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.example.testmodule.thematicchart.risksource.
 */
public class RiskPointEntry extends PointEntry {
    RiskSourceInfoEntry riskSourceInfoEntry;

    public RiskSourceInfoEntry getRiskSourceInfoEntry() {
        return riskSourceInfoEntry;
    }

    public void setRiskSourceInfoEntry(RiskSourceInfoEntry riskSourceInfoEntry) {
        this.riskSourceInfoEntry = riskSourceInfoEntry;
    }
}
