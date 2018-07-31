package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/20.
 * 对应8.2.1.8	安全检查对象表
 *
 */

public class ObjSins extends HttpBaseResponse<ObjSins> {

      String guid;
    /**
     * 检查部署通知名称
     */
    String sinsDeplName;
    /**
     * 检查开始时间
     */
      String sinsStartTime;
    /**
     * 检查结束时间
     */
    String sinsCompTime;
    /**
     * 检查范围
     */
      String sinsRange;
    /**
     * 安全检查部署通知文号
     */
    String sinsLareId;
    /**
     * 下发通知时间
     */
      String sinsNotTime;
    /**
     * 通知下发单位
     */
    String notIssuWiun;
    /**
     * 备注
     */
      String note;
    /**
     * 采集时间
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
    /**
     * 是否下发
     */
      String ifSendDown;
      String pGuid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSinsDeplName() {
        return sinsDeplName;
    }

    public void setSinsDeplName(String sinsDeplName) {
        this.sinsDeplName = sinsDeplName;
    }

    public String getSinsStartTime() {
        return sinsStartTime;
    }

    public void setSinsStartTime(String sinsStartTime) {
        this.sinsStartTime = sinsStartTime;
    }

    public String getSinsCompTime() {
        return sinsCompTime;
    }

    public void setSinsCompTime(String sinsCompTime) {
        this.sinsCompTime = sinsCompTime;
    }

    public String getSinsRange() {
        return sinsRange;
    }

    public void setSinsRange(String sinsRange) {
        this.sinsRange = sinsRange;
    }

    public String getSinsLareId() {
        return sinsLareId;
    }

    public void setSinsLareId(String sinsLareId) {
        this.sinsLareId = sinsLareId;
    }

    public String getSinsNotTime() {
        return sinsNotTime;
    }

    public void setSinsNotTime(String sinsNotTime) {
        this.sinsNotTime = sinsNotTime;
    }

    public String getNotIssuWiun() {
        return notIssuWiun;
    }

    public void setNotIssuWiun(String notIssuWiun) {
        this.notIssuWiun = notIssuWiun;
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

    public String getIfSendDown() {
        return ifSendDown;
    }

    public void setIfSendDown(String ifSendDown) {
        this.ifSendDown = ifSendDown;
    }

    public String getpGuid() {
        return pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }
}
