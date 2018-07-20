package com.example.testmodule.thematicchart.supervisionenforcement;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/20.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.supervisionenforcement.
 */
public class SupervisionOrgEntry implements Serializable {
    String name;//辖区
    int punishCount;//处罚数-监管\流域
    int rectifyCount;//整改单位数-监管\流域
    String rate;//整改率 -直管

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getRectifyCount() {
        return rectifyCount;
    }

    public void setRectifyCount(int rectifyCount) {
        this.rectifyCount = rectifyCount;
    }

    public String getRate() {
        return rate == null ? "" : rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getPunishCount() {
        return punishCount;
    }

    public void setPunishCount(int punishCount) {
        this.punishCount = punishCount;
    }
}
