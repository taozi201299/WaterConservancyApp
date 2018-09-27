package com.syberos.shuili.entity.dangersource;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/20.
 * 危险源备案
 */


public class BisHazReg extends HttpBaseResponse<BisHazReg> {
    public String hazName;
    public String guid;;
    public String regTime;
    public String wiunName;
    public String ROWNO;
    public String collTime;
    public String hazStat;
    public String engName;
    public String writeOffTime;

    public String getHazName() {
        return hazName == null ? "" : hazName;
    }

    public void setHazName(String hazName) {
        this.hazName = hazName;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRegTime() {
        return regTime == null ? "" : regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getWiunName() {
        return wiunName == null ? "" : wiunName;
    }

    public void setWiunName(String wiunName) {
        this.wiunName = wiunName;
    }

    public String getROWNO() {
        return ROWNO == null ? "" : ROWNO;
    }

    public void setROWNO(String ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getHazStat() {
        return hazStat == null ? "" : hazStat;
    }

    public void setHazStat(String hazStat) {
        this.hazStat = hazStat;
    }

    public String getEngName() {
        return engName == null ? "" : engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getWriteOffTime() {
        return writeOffTime == null ? "" : writeOffTime;
    }

    public void setWriteOffTime(String writeOffTime) {
        this.writeOffTime = writeOffTime;
    }
}
