package com.syberos.shuili.entity.bao_biao_guan_li;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 * 8.2.2.20	月报表上报期间表 事故、隐患报表列表
 */

public class BisOrgMonRepPeri extends HttpHiddenBaseResponse<BisOrgMonRepPeri> {
    String guid;
    /**
     * 报表名称
     */
    String repName;
    /**
     * 上报状态
     */
    String repAct;
    /**
     * 页面地址
     */
    String url;
    /**
     * 上报类型
     */
    String repType;
    /**
     * 报表时间
     */
    String repTime;
    /**
     * 开始时间
     */
    String startDate;
    String endDate;
    /**
     * 上报单位
     */
    String repOrgGuid;
    String note;
    String collTime;
    String updTime;
    /**
     * 记录人员
     */
    String recPers;
    /**
     * 是否曾经上报过
     */
    boolean isReportFinish;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getRepAct() {
        return repAct;
    }

    public void setRepAct(String repAct) {
        this.repAct = repAct;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getRepTime() {
        return repTime;
    }

    public void setRepTime(String repTime) {
        this.repTime = repTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRepOrgGuid() {
        return repOrgGuid;
    }

    public void setRepOrgGuid(String repOrgGuid) {
        this.repOrgGuid = repOrgGuid;
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

    public void setReportFinish(boolean reportFinish) {
        isReportFinish = reportFinish;
    }

    public boolean isReportFinish() {
        return isReportFinish;
    }
}
