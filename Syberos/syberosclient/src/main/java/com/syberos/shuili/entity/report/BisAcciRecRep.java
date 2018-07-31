package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 * 8.2.2.22	事故记录上报（BIS_ACCI_REC_REP）
 */

public class BisAcciRecRep extends HttpBaseResponse<BisAcciRecRep> {
    String guid;
    String acciGuid;
    String repGuid;
    String repOrgGuid;
    String repAct;
    String returnDesc;
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

    public String getAcciGuid() {
        return acciGuid;
    }

    public void setAcciGuid(String acciGuid) {
        this.acciGuid = acciGuid;
    }

    public String getRepGuid() {
        return repGuid;
    }

    public void setRepGuid(String repGuid) {
        this.repGuid = repGuid;
    }

    public String getRepOrgGuid() {
        return repOrgGuid;
    }

    public void setRepOrgGuid(String repOrgGuid) {
        this.repOrgGuid = repOrgGuid;
    }

    public String getRepAct() {
        return repAct;
    }

    public void setRepAct(String repAct) {
        this.repAct = repAct;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
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
