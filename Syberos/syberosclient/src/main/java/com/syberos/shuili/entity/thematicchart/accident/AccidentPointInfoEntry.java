package com.syberos.shuili.entity.thematicchart.accident;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class AccidentPointInfoEntry  implements Serializable {
    String accidentPointName;//事故标题
    int accidentCount;//事故数量
    int deadCount;//死亡人数
    int EconomicLosses;//经济损失


    public String getAccidentPointName() {
        return accidentPointName == null ? "" : accidentPointName;
    }

    public void setAccidentPointName(String accidentPointName) {
        this.accidentPointName = accidentPointName;
    }

    public int getAccidentCount() {
        return accidentCount;
    }

    public void setAccidentCount(int accidentCount) {
        this.accidentCount = accidentCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public int getEconomicLosses() {
        return EconomicLosses;
    }

    public void setEconomicLosses(int economicLosses) {
        EconomicLosses = economicLosses;
    }
}
