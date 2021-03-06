package com.syberos.shuili.entity.thematicchart.accident;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class AccidentStatisticEntry implements Serializable {
    int accCount;//事故数量
    int deadCount;//死亡人数
    int seriouslyInjured;//重伤人数
    int directEconomicLosses;//直接经济损失

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


}
