package com.syberos.shuili.entity.inspect;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 稽查问题
 */

public class BisWinsProb extends HttpHiddenBaseResponse<BisWinsProb> {
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
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProbType() {
        return probType;
    }

    public void setProbType(String probType) {
        this.probType = probType;
    }

    public String getProbCate() {
        return probCate;
    }

    public void setProbCate(String probCate) {
        this.probCate = probCate;
    }

    public String getProbDep() {
        return probDep;
    }

    public void setProbDep(String probDep) {
        this.probDep = probDep;
    }

    public String getProbDesc() {
        return probDesc;
    }

    public void setProbDesc(String probDesc) {
        this.probDesc = probDesc;
    }

    public String getRectSugg() {
        return rectSugg;
    }

    public void setRectSugg(String rectSugg) {
        this.rectSugg = rectSugg;
    }

    public String getRectConc() {
        return rectConc;
    }

    public void setRectConc(String rectConc) {
        this.rectConc = rectConc;
    }

    public String getUnrecReson() {
        return unrecReson;
    }

    public void setUnrecReson(String unrecReson) {
        this.unrecReson = unrecReson;
    }

    public String getRectMeas() {
        return rectMeas;
    }

    public void setRectMeas(String rectMeas) {
        this.rectMeas = rectMeas;
    }

    public String getRectLed() {
        return rectLed;
    }

    public void setRectLed(String rectLed) {
        this.rectLed = rectLed;
    }

    public String getIsRect() {
        return isRect;
    }

    public void setIsRect(String isRect) {
        this.isRect = isRect;
    }

    public String getTrackPeop() {
        return trackPeop;
    }

    public void setTrackPeop(String trackPeop) {
        this.trackPeop = trackPeop;
    }

    public String getWinsProjGuid() {
        return winsProjGuid;
    }

    public void setWinsProjGuid(String winsProjGuid) {
        this.winsProjGuid = winsProjGuid;
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
