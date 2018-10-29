package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/10/29.
 */

public class ObjPuno extends HttpBaseResponse<ObjPuno> {
    private String guid;
    private String titl;
    private String cont;
    private String punoType;
    private String releTime;
    private String endTime;
    private String relePers;
    private String releOrgGuid;
    private String valiTime;
    private String stat;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;
    private String stanReviGuid;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitl() {
        return titl == null ? "" : titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public String getCont() {
        return cont == null ? "" : cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getPunoType() {
        return punoType == null ? "" : punoType;
    }

    public void setPunoType(String punoType) {
        this.punoType = punoType;
    }

    public String getReleTime() {
        return releTime == null ? "" : releTime;
    }

    public void setReleTime(String releTime) {
        this.releTime = releTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRelePers() {
        return relePers == null ? "" : relePers;
    }

    public void setRelePers(String relePers) {
        this.relePers = relePers;
    }

    public String getReleOrgGuid() {
        return releOrgGuid == null ? "" : releOrgGuid;
    }

    public void setReleOrgGuid(String releOrgGuid) {
        this.releOrgGuid = releOrgGuid;
    }

    public String getValiTime() {
        return valiTime == null ? "" : valiTime;
    }

    public void setValiTime(String valiTime) {
        this.valiTime = valiTime;
    }

    public String getStat() {
        return stat == null ? "" : stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
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

    public String getStanReviGuid() {
        return stanReviGuid == null ? "" : stanReviGuid;
    }

    public void setStanReviGuid(String stanReviGuid) {
        this.stanReviGuid = stanReviGuid;
    }
}
