package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/21.
 */

public class BisWoasNoti extends HttpBaseResponse<BisWoasNoti> {

    String guid;
    String orgGuid;
    String seasScore;
    String woasGuid;
    String repStat;
    String SEASTIME;
    String woasThem;;
    String orgName;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getSeasScore() {
        return seasScore == null ? "" : seasScore;
    }

    public void setSeasScore(String seasScore) {
        this.seasScore = seasScore;
    }

    public String getWoasGuid() {
        return woasGuid == null ? "" : woasGuid;
    }

    public void setWoasGuid(String woasGuid) {
        this.woasGuid = woasGuid;
    }

    public String getRepStat() {
        return repStat == null ? "" : repStat;
    }

    public void setRepStat(String repStat) {
        this.repStat = repStat;
    }

    public String getSEASTIME() {
        return SEASTIME == null ? "" : SEASTIME;
    }

    public void setSEASTIME(String SEASTIME) {
        this.SEASTIME = SEASTIME;
    }

    public String getWoasThem() {
        return woasThem == null ? "" : woasThem;
    }

    public void setWoasThem(String woasThem) {
        this.woasThem = woasThem;
    }

    public String getOrgName() {
        return orgName == null ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
