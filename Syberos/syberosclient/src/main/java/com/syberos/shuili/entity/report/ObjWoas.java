package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/20.
 */

public class ObjWoas extends HttpBaseResponse<ObjWoas> {
    String guid;
    String WOASDEADLINE;
    String isForw; // 是否为转发
    String woasThem;
    String lareId;
    String WOASSTARTIME;
    String sendStat;
    String orgName;
    String WOASGUID;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWOASDEADLINE() {
        return WOASDEADLINE == null ? "" : WOASDEADLINE;
    }

    public void setWOASDEADLINE(String WOASDEADLINE) {
        this.WOASDEADLINE = WOASDEADLINE;
    }

    public String getIsForw() {
        return isForw == null ? "" : isForw;
    }

    public void setIsForw(String isForw) {
        this.isForw = isForw;
    }

    public String getWoasThem() {
        return woasThem == null ? "" : woasThem;
    }

    public void setWoasThem(String woasThem) {
        this.woasThem = woasThem;
    }

    public String getLareId() {
        return lareId == null ? "" : lareId;
    }

    public void setLareId(String lareId) {
        this.lareId = lareId;
    }

    public String getWOASSTARTIME() {
        return WOASSTARTIME == null ? "" : WOASSTARTIME;
    }

    public void setWOASSTARTIME(String WOASSTARTIME) {
        this.WOASSTARTIME = WOASSTARTIME;
    }

    public String getSendStat() {
        return sendStat == null ? "" : sendStat;
    }

    public void setSendStat(String sendStat) {
        this.sendStat = sendStat;
    }

    public String getOrgName() {
        return orgName == null ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWOASGUID() {
        return WOASGUID == null ? "" : WOASGUID;
    }

    public void setWOASGUID(String WOASGUID) {
        this.WOASGUID = WOASGUID;
    }
}
