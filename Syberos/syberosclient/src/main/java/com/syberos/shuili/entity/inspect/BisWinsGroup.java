package com.syberos.shuili.entity.inspect;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 水利稽查组 BIS_WINS_GROUP
 */

public class BisWinsGroup extends HttpBaseResponse<BisWinsGroup> {
    String guid;
    /**
     * 组编号
     */
    String winsGroupNum;
    /**
     * 稽察方案GUID
     */
    String winsProgGuid;
    /**
     * 特派员GUID
     */
    String speStafGuid;
    /**
     * 特派员助理GUID
     */
    String speStafAssiGuid;
    String note;
    String collTime;
    String updTime;
    String recPers;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWinsGroupNum() {
        return winsGroupNum;
    }

    public void setWinsGroupNum(String winsGroupNum) {
        this.winsGroupNum = winsGroupNum;
    }

    public String getWinsProgGuid() {
        return winsProgGuid;
    }

    public void setWinsProgGuid(String winsProgGuid) {
        this.winsProgGuid = winsProgGuid;
    }

    public String getSpeStafGuid() {
        return speStafGuid;
    }

    public void setSpeStafGuid(String speStafGuid) {
        this.speStafGuid = speStafGuid;
    }

    public String getSpeStafAssiGuid() {
        return speStafAssiGuid;
    }

    public void setSpeStafAssiGuid(String speStafAssiGuid) {
        this.speStafAssiGuid = speStafAssiGuid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }
}
