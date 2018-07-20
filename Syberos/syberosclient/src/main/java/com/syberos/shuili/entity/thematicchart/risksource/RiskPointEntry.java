package com.syberos.shuili.entity.thematicchart.risksource;

import com.syberos.shuili.entity.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.risksource.
 */
public class RiskPointEntry extends PointEntry {
    OrgRiskSourceEntry orgRiskSourceEntry;

    public OrgRiskSourceEntry getOrgRiskSourceEntry() {
        return orgRiskSourceEntry;
    }

    public void setOrgRiskSourceEntry(OrgRiskSourceEntry orgRiskSourceEntry) {
        this.orgRiskSourceEntry = orgRiskSourceEntry;
    }
}
