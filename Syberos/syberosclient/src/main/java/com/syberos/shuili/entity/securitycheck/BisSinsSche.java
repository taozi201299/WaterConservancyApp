package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/20.
 * 安全检查方案（BIS_SINS_SCHE）
 */

public class BisSinsSche extends HttpBaseResponse<BisSinsSche> {
    /**
     * 检查方案GUID
     */
    String guid;
    /**
     * 检查部署GUID
     */
    String sinsGuid;
    /**
     * 检查方案名称
     */
    String scheName;
    /**
     * 检查开始时间
     */
    String scheStartTime;
    /**
     * 检查结束时间
     */
    String scheCompTime;
    /**
     * 检查内容
     */
    String scheCont;
    /**
     * 方案制定时间
     */
    String scheMakeTime;
    /**
     * 方案制定单位
     */
    String scheMakeWiun;
    /**
     * 方案是否制定完成
     */
    String scheIsComp;
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


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSinsGuid() {
        return sinsGuid == null ? "" : sinsGuid;
    }

    public void setSinsGuid(String sinsGuid) {
        this.sinsGuid = sinsGuid;
    }

    public String getScheName() {
        return scheName == null ? "" : scheName;
    }

    public void setScheName(String scheName) {
        this.scheName = scheName;
    }

    public String getScheStartTime() {
        return scheStartTime == null ? "" : scheStartTime;
    }

    public void setScheStartTime(String scheStartTime) {
        this.scheStartTime = scheStartTime;
    }

    public String getScheCompTime() {
        return scheCompTime == null ? "" : scheCompTime;
    }

    public void setScheCompTime(String scheCompTime) {
        this.scheCompTime = scheCompTime;
    }

    public String getScheCont() {
        return scheCont == null ? "" : scheCont;
    }

    public void setScheCont(String scheCont) {
        this.scheCont = scheCont;
    }

    public String getScheMakeTime() {
        return scheMakeTime == null ? "" : scheMakeTime;
    }

    public void setScheMakeTime(String scheMakeTime) {
        this.scheMakeTime = scheMakeTime;
    }

    public String getScheMakeWiun() {
        return scheMakeWiun == null ? "" : scheMakeWiun;
    }

    public void setScheMakeWiun(String scheMakeWiun) {
        this.scheMakeWiun = scheMakeWiun;
    }

    public String getScheIsComp() {
        return scheIsComp == null ? "" : scheIsComp;
    }

    public void setScheIsComp(String scheIsComp) {
        this.scheIsComp = scheIsComp;
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
}
