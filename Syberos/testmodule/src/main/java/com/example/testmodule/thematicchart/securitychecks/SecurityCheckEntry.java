package com.example.testmodule.thematicchart.securitychecks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */
public class SecurityCheckEntry implements Serializable{
    //  安全检查
    List<SecurityCheckPointEntry> pointEntryList;
    //部门检查情况
    SecuritySituationEntry securitySituationEntry;
    //检查完成情况统计
    SecurityRankRateEntry securityRankRateEntry;

    public List<SecurityCheckPointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<SecurityCheckPointEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

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
