package com.syberos.shuili.entity.thematicchart.accident;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
class SituationEntry implements Serializable{
    String accCount;//事故数量
    String accDeath;//死亡人数

    public String getAccCount() {
        return accCount == null ? "" : accCount;
    }

    public void setAccCount(String accCount) {
        this.accCount = accCount;
    }

    public String getAccDeath() {
        return accDeath == null ? "" : accDeath;
    }

    public void setAccDeath(String accDeath) {
        this.accDeath = accDeath;
    }

}
