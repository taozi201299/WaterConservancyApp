package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/12.
 * 整改方案信息
 */

public class HiddenRectifyPlanInfo extends HttpHiddenBaseResponse<HiddenRectifyPlanInfo> {
    String guid;
    /**
     * 隐患GUID
     */
    String hiddGuid;
    /**
     * 治理责任单位GUID
     */
    String goverRespWiunGuid;
    String goverRespWiunName;
    /**
     * 整改责任人
     */
    String rectLegPers;
    /**
     * 责任人电话
     */
    String rectPersOffiTel;
    /**
     * 要求完成日期
     */
    String requCompDate;
    /**
     * 累计落实治理资金
     */
    String acimpGoverFund;
    /**
     * 治理目标任务
     */
    String goveObjeTasks;
    /**
     * 安全措施应急预案
     */
    String emerPlanSame;
    /**
     * 整改措施概述
     */
    String corrMeas;
    /**
     * 备注
     */
    String note;
    /**
     *  采集时间
     */
    String collTime;
    /**
     * 更新时间
     */
    String updTime;
    /**
     * 记录人员
     */
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

    public String getGoverRespWiunGuid() {
        return goverRespWiunGuid;
    }

    public void setGoverRespWiunGuid(String goverRespWiunGuid) {
        this.goverRespWiunGuid = goverRespWiunGuid;
    }

    public String getGoverRespWiunName() {
        return goverRespWiunName;
    }

    public void setGoverRespWiunName(String goverRespWiunName) {
        this.goverRespWiunName = goverRespWiunName;
    }

    public String getRectLegPers() {
        return rectLegPers;
    }

    public void setRectLegPers(String rectLegPers) {
        this.rectLegPers = rectLegPers;
    }

    public String getRectPersOffiTel() {
        return rectPersOffiTel;
    }

    public void setRectPersOffiTel(String rectPersOffiTel) {
        this.rectPersOffiTel = rectPersOffiTel;
    }

    public String getRequCompDate() {
        return requCompDate;
    }

    public void setRequCompDate(String requCompDate) {
        this.requCompDate = requCompDate;
    }

    public String getAcimpGoverFund() {
        return acimpGoverFund;
    }

    public void setAcimpGoverFund(String acimpGoverFund) {
        this.acimpGoverFund = acimpGoverFund;
    }

    public String getGoveObjeTasks() {
        return goveObjeTasks;
    }

    public void setGoveObjeTasks(String goveObjeTasks) {
        this.goveObjeTasks = goveObjeTasks;
    }

    public String getEmerPlanSame() {
        return emerPlanSame;
    }

    public void setEmerPlanSame(String emerPlanSame) {
        this.emerPlanSame = emerPlanSame;
    }

    public String getCorrMeas() {
        return corrMeas;
    }

    public void setCorrMeas(String corrMeas) {
        this.corrMeas = corrMeas;
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
