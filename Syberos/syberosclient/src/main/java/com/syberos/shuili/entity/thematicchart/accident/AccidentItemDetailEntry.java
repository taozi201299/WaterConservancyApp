package com.syberos.shuili.entity.thematicchart.accident;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class AccidentItemDetailEntry implements Serializable {
    String accTitle;//事故标题
    int accCount;//事故数量
    int deadCount;//死亡人数
    int seriouslyInjured;//重伤人数
    int directEconomicLosses;//直接经济损失
    String accReason;    //事故原因
    String accDescription;     //事故描述

    public AccidentItemDetailEntry(String accTitle, int accCount, int deadCount, int seriouslyInjured, int directEconomicLosses, String accReason, String accDescription) {
        this.accTitle = accTitle;
        this.accCount = accCount;
        this.deadCount = deadCount;
        this.seriouslyInjured = seriouslyInjured;
        this.directEconomicLosses = directEconomicLosses;
        this.accReason = accReason;
        this.accDescription = accDescription;
    }

    public String getAccTitle() {
        return accTitle == null ? "" : accTitle;
    }

    public void setAccTitle(String accTitle) {
        this.accTitle = accTitle;
    }

    public int getAccCount() {
        return accCount;
    }

    public void setAccCount(int accCount) {
        this.accCount = accCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public int getSeriouslyInjured() {
        return seriouslyInjured;
    }

    public void setSeriouslyInjured(int seriouslyInjured) {
        this.seriouslyInjured = seriouslyInjured;
    }

    public int getDirectEconomicLosses() {
        return directEconomicLosses;
    }

    public void setDirectEconomicLosses(int directEconomicLosses) {
        this.directEconomicLosses = directEconomicLosses;
    }

    public String getAccReason() {
        return accReason == null ? "" : accReason;
    }

    public void setAccReason(String accReason) {
        this.accReason = accReason;
    }

    public String getAccDescription() {
        return accDescription == null ? "" : accDescription;
    }

    public void setAccDescription(String accDescription) {
        this.accDescription = accDescription;
    }
}
