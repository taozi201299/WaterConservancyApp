package com.example.testmodule.thematicchart.accident;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class SituationEntry implements Serializable {
    private String situationTitle; //工程事故情况(xxxx年直管工程事故情况)
    private String accCount;//事故数量
    private String accDeath;//死亡人数

    public String getSituationTitle() {
        return situationTitle == null ? "" : situationTitle;
    }

    public void setSituationTitle(String situationTitle) {
        this.situationTitle = situationTitle;
    }

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
