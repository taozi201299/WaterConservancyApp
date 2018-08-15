package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/15.
 */

public class BisWinsProjAll extends HttpBaseResponse<BisWinsProjAll> {
    String guid;
    String projGuid;
    String winsGroupGuid;
    String note;
    String collTime;
    String updTime;
    String recPers;
    String projName;
    String adminWiunGuid;
    String adminWiunName;
    String winsProjType;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProjGuid() {
        return projGuid == null ? "" : projGuid;
    }

    public void setProjGuid(String projGuid) {
        this.projGuid = projGuid;
    }

    public String getWinsGroupGuid() {
        return winsGroupGuid == null ? "" : winsGroupGuid;
    }

    public void setWinsGroupGuid(String winsGroupGuid) {
        this.winsGroupGuid = winsGroupGuid;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getProjName() {
        return projName == null ? "" : projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getAdminWiunGuid() {
        return adminWiunGuid == null ? "" : adminWiunGuid;
    }

    public void setAdminWiunGuid(String adminWiunGuid) {
        this.adminWiunGuid = adminWiunGuid;
    }

    public String getAdminWiunName() {
        return adminWiunName == null ? "" : adminWiunName;
    }

    public void setAdminWiunName(String adminWiunName) {
        this.adminWiunName = adminWiunName;
    }

    public String getWinsProjType() {
        return winsProjType == null ? "" : winsProjType;
    }

    public void setWinsProjType(String winsProjType) {
        this.winsProjType = winsProjType;
    }
}
