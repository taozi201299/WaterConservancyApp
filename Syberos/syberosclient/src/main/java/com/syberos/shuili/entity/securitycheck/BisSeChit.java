package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/27.
 */

public class BisSeChit extends HttpBaseResponse<BisSeChit> {

    String guid;
    /**
     * 检查项
     */
    String seChitName;
    /**
     * 安全元素GUID
     */
    String seGuid;
    /**
     * 是否 不达标项
     */
    String ifUnStan;
    /**
     * 扣分分值
     */
    String deduScore;
    /**
     * 扣分分值
     */
    String reviItemCode;
    /**
     * 扣分规则
     */
    String deduRegu;
    /**
     * 是否 /无有  1为是/否，2为无/有，3为否/是，4为有/无。
     */
    String ifWhit;
    String note;
    String collDate;
    String updDate;
    String recPers;
    String ifReasLack;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSeChitName() {
        return seChitName;
    }

    public void setSeChitName(String seChitName) {
        this.seChitName = seChitName;
    }

    public String getSeGuid() {
        return seGuid;
    }

    public void setSeGuid(String seGuid) {
        this.seGuid = seGuid;
    }

    public String getIfUnStan() {
        return ifUnStan;
    }

    public void setIfUnStan(String ifUnStan) {
        this.ifUnStan = ifUnStan;
    }

    public String getDeduScore() {
        return deduScore;
    }

    public void setDeduScore(String deduScore) {
        this.deduScore = deduScore;
    }

    public String getReviItemCode() {
        return reviItemCode;
    }

    public void setReviItemCode(String reviItemCode) {
        this.reviItemCode = reviItemCode;
    }

    public String getDeduRegu() {
        return deduRegu;
    }

    public void setDeduRegu(String deduRegu) {
        this.deduRegu = deduRegu;
    }

    public String getIfWhit() {
        return ifWhit;
    }

    public void setIfWhit(String ifWhit) {
        this.ifWhit = ifWhit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollDate() {
        return collDate;
    }

    public void setCollDate(String collDate) {
        this.collDate = collDate;
    }

    public String getUpdDate() {
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    public String getRecPers() {
        return recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getIfReasLack() {
        return ifReasLack;
    }

    public void setIfReasLack(String ifReasLack) {
        this.ifReasLack = ifReasLack;
    }
}
