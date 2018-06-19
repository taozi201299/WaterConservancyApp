package com.syberos.shuili.entity.inspect;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 */

public class BisWinsProj extends HttpHiddenBaseResponse<BisWinsProj> {
    String guid;
    String projGuid;
    String winsGroupGuid;
    String projName;
    String adminWiunGuid;
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

    public String getProjGuid() {
        return projGuid;
    }

    public void setProjGuid(String projGuid) {
        this.projGuid = projGuid;
    }

    public String getWinsGroupGuid() {
        return winsGroupGuid;
    }

    public void setWinsGroupGuid(String winsGroupGuid) {
        this.winsGroupGuid = winsGroupGuid;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getAdminWiunGuid() {
        return adminWiunGuid;
    }

    public void setAdminWiunGuid(String adminWiunGuid) {
        this.adminWiunGuid = adminWiunGuid;
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
