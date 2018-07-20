package com.example.testmodule.thematicchart.securitychecks;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */
public class OrgSecurityCheckEntry implements Serializable{
    //部门检查情况
    SecuritySituationEntry securitySituationEntry;
    //检查完成情况统计
    SecurityRankRateEntry securityRankRateEntry;

    public SecuritySituationEntry getSecuritySituationEntry() {
        return securitySituationEntry;
    }

    public void setSecuritySituationEntry(SecuritySituationEntry securitySituationEntry) {
        this.securitySituationEntry = securitySituationEntry;
    }

    public SecurityRankRateEntry getSecurityRankRateEntry() {
        return securityRankRateEntry;
    }

    public void setSecurityRankRateEntry(SecurityRankRateEntry securityRankRateEntry) {
        this.securityRankRateEntry = securityRankRateEntry;
    }
}
