package com.syberos.shuili.entity.accident;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/20.
 */

public class AttOrgBaseForAcci extends HttpBaseResponse<AttOrgBaseForAcci>{
    String guid;
    String pguid;
    String orgCode;
    String orgName;
    String orgLevel;
    String orgGrad;
    String orgType;;
    String ordernum;
    String adCode;
    String status;
    String modifier;
    String fromDate;
    String toDate;
    String note;
    String jurd;
    String areaType;
    String nodeCode;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPguid() {
        return pguid == null ? "" : pguid;
    }

    public void setPguid(String pguid) {
        this.pguid = pguid;
    }

    public String getOrgCode() {
        return orgCode == null ? "" : orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName == null ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLevel() {
        return orgLevel == null ? "" : orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgGrad() {
        return orgGrad == null ? "" : orgGrad;
    }

    public void setOrgGrad(String orgGrad) {
        this.orgGrad = orgGrad;
    }

    public String getOrgType() {
        return orgType == null ? "" : orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrdernum() {
        return ordernum == null ? "" : ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getAdCode() {
        return adCode == null ? "" : adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModifier() {
        return modifier == null ? "" : modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getFromDate() {
        return fromDate == null ? "" : fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate == null ? "" : toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getJurd() {
        return jurd == null ? "" : jurd;
    }

    public void setJurd(String jurd) {
        this.jurd = jurd;
    }

    public String getAreaType() {
        return areaType == null ? "" : areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getNodeCode() {
        return nodeCode == null ? "" : nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
}