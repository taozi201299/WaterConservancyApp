package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/27.
 */

public class ObjSe extends HttpBaseResponse<ObjSe> {

    String guid;
    String seName;
    String seCode;
    String seType;
    String collTime;
    String updTime;
    String recPers;
    String pGuid;
    Boolean isGroup;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSeName() {
        return seName == null ? "" : seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public String getSeCode() {
        return seCode == null ? "" : seCode;
    }

    public void setSeCode(String seCode) {
        this.seCode = seCode;
    }

    public String getSeType() {
        return seType == null ? "" : seType;
    }

    public void setSeType(String seType) {
        this.seType = seType;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime == null ? "" : updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers == null ? "" : recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getpGuid() {
        return pGuid == null ? "" : pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }

    public Boolean getGroup() {
        return isGroup;
    }

    public void setGroup(Boolean group) {
        isGroup = group;
    }
}
