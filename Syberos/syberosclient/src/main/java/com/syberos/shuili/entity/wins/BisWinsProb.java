package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 稽查问题
 */

public class BisWinsProb extends HttpBaseResponse<BisWinsProb> {
    String guid;
    // 问题分类
    String probType;
    //严重程度
    String probCate;
    // 对应司局
    String probDep;
    // 问题详情描述
    String probDesc;
    // 整改建议
    String rectSugg;
    // 整改结论
    String rectConc;
    // 未整改原因
    String unrecReson;
    // 整改措施
    String rectMeas;
    // 整改负责人
    String rectLed;
    // 是否整改
    String isRect;
    // 整改人
    String trackPeop;
    // 稽察项目GUID
    String winsProjGuid;
    String note;
    String collTime;
    String updTime;
    String recPers;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProbType() {
        return probType == null ? "" : probType;
    }

    public void setProbType(String probType) {
        this.probType = probType;
    }

    public String getProbCate() {
        return probCate == null ? "" : probCate;
    }

    public void setProbCate(String probCate) {
        this.probCate = probCate;
    }

    public String getProbDep() {
        return probDep == null ? "" : probDep;
    }

    public void setProbDep(String probDep) {
        this.probDep = probDep;
    }

    public String getProbDesc() {
        return probDesc == null ? "" : probDesc;
    }

    public void setProbDesc(String probDesc) {
        this.probDesc = probDesc;
    }

    public String getRectSugg() {
        return rectSugg == null ? "" : rectSugg;
    }

    public void setRectSugg(String rectSugg) {
        this.rectSugg = rectSugg;
    }

    public String getRectConc() {
        return rectConc == null ? "" : rectConc;
    }

    public void setRectConc(String rectConc) {
        this.rectConc = rectConc;
    }

    public String getUnrecReson() {
        return unrecReson == null ? "" : unrecReson;
    }

    public void setUnrecReson(String unrecReson) {
        this.unrecReson = unrecReson;
    }

    public String getRectMeas() {
        return rectMeas == null ? "" : rectMeas;
    }

    public void setRectMeas(String rectMeas) {
        this.rectMeas = rectMeas;
    }

    public String getRectLed() {
        return rectLed == null ? "" : rectLed;
    }

    public void setRectLed(String rectLed) {
        this.rectLed = rectLed;
    }

    public String getIsRect() {
        return isRect == null ? "" : isRect;
    }

    public void setIsRect(String isRect) {
        this.isRect = isRect;
    }

    public String getTrackPeop() {
        return trackPeop == null ? "" : trackPeop;
    }

    public void setTrackPeop(String trackPeop) {
        this.trackPeop = trackPeop;
    }

    public String getWinsProjGuid() {
        return winsProjGuid == null ? "" : winsProjGuid;
    }

    public void setWinsProjGuid(String winsProjGuid) {
        this.winsProjGuid = winsProjGuid;
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
