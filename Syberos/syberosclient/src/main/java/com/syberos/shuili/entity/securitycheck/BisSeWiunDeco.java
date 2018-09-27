package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/27.
 */

public class BisSeWiunDeco extends HttpBaseResponse<BisSeWiunDeco> {
    String guid;
    String seWiunGuid;
    String legPersGuid;
    String depPersGuid;
    String charPersGuid;
    String note;
    String collDate;
    String updDate;
    String recPers;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSeWiunGuid() {
        return seWiunGuid == null ? "" : seWiunGuid;
    }

    public void setSeWiunGuid(String seWiunGuid) {
        this.seWiunGuid = seWiunGuid;
    }

    public String getLegPersGuid() {
        return legPersGuid == null ? "" : legPersGuid;
    }

    public void setLegPersGuid(String legPersGuid) {
        this.legPersGuid = legPersGuid;
    }

    public String getDepPersGuid() {
        return depPersGuid == null ? "" : depPersGuid;
    }

    public void setDepPersGuid(String depPersGuid) {
        this.depPersGuid = depPersGuid;
    }

    public String getCharPersGuid() {
        return charPersGuid == null ? "" : charPersGuid;
    }

    public void setCharPersGuid(String charPersGuid) {
        this.charPersGuid = charPersGuid;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollDate() {
        return collDate == null ? "" : collDate;
    }

    public void setCollDate(String collDate) {
        this.collDate = collDate;
    }

    public String getUpdDate() {
        return updDate == null ? "" : updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    public String getRecPers() {
        return recPers == null ? "" : recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }
}
