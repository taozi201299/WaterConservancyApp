package com.syberos.shuili.entity.inspect;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 稽查地区
 */

public class BisWinsRegi extends HttpHiddenBaseResponse<BisWinsRegi>{
    String guid;
    String orgGuid;
    String areaName;
    String winsProgGuid;
    String winsGroupGuid;
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

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWinsProgGuid() {
        return winsProgGuid;
    }

    public void setWinsProgGuid(String winsProgGuid) {
        this.winsProgGuid = winsProgGuid;
    }

    public String getWinsGroupGuid() {
        return winsGroupGuid;
    }

    public void setWinsGroupGuid(String winsGroupGuid) {
        this.winsGroupGuid = winsGroupGuid;
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
