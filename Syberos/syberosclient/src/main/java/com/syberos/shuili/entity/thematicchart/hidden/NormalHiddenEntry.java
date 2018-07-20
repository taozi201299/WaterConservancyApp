package com.syberos.shuili.entity.thematicchart.hidden;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class NormalHiddenEntry implements Serializable {
    private int normalHiddenHadRectifiedCout;   //一般隐患已整改数
    private int normalHiddenNotRectifiedCount;  //一般隐患 未整改数据
    private String normalHiddenRectifiedRate; //整改率(百分值 eg:20%)
    private int lateOfNormalRectifiedCount;  //一般隐患 逾期数

    public int getNormalHiddenHadRectifiedCout() {
        return normalHiddenHadRectifiedCout;
    }

    public void setNormalHiddenHadRectifiedCout(int normalHiddenHadRectifiedCout) {
        this.normalHiddenHadRectifiedCout = normalHiddenHadRectifiedCout;
    }

    public int getNormalHiddenNotRectifiedCount() {
        return normalHiddenNotRectifiedCount;
    }

    public void setNormalHiddenNotRectifiedCount(int normalHiddenNotRectifiedCount) {
        this.normalHiddenNotRectifiedCount = normalHiddenNotRectifiedCount;
    }

    public String getNormalHiddenRectifiedRate() {
        return normalHiddenRectifiedRate == null ? "" : normalHiddenRectifiedRate;
    }

    public void setNormalHiddenRectifiedRate(String normalHiddenRectifiedRate) {
        this.normalHiddenRectifiedRate = normalHiddenRectifiedRate;
    }

    public int getLateOfNormalRectifiedCount() {
        return lateOfNormalRectifiedCount;
    }

    public void setLateOfNormalRectifiedCount(int lateOfNormalRectifiedCount) {
        this.lateOfNormalRectifiedCount = lateOfNormalRectifiedCount;
    }

}
