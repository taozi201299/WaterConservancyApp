package com.syberos.shuili.entity.thematicchart.standardization;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.standardization.
 */
public class StandardizationInfoEntry implements Serializable{
    private String leval;//等级
    private String date;//达标时间 (million值)


    public String getLeval() {
        return leval == null ? "" : leval;
    }

    public void setLeval(String leval) {
        this.leval = leval;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
