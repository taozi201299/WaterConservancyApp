package com.syberos.shuili.entity.bao_biao_guan_li;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 *8.2.2.18	隐患记录上报（BIS_HIDD_REC_REP）
 */

public class BisHiddRecRep extends HttpBaseResponse<BisHiddRecRep> {

    String guid;
    /**
     * 隐患GUID
     */
    String hiddGuid;
    /**
     * 期间报表GUID
     */
    String repGuid;
    /**
     * 上报单位
     */
    String repOrgGuid;
    /**
     * 上报状态
     */
    String repAct;
    /**
     * 退回说明
     */
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

    public String getHiddGuid() {
        return hiddGuid;
    }

    public void setHiddGuid(String hiddGuid) {
        this.hiddGuid = hiddGuid;
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
