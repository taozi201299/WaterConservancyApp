package com.example.testmodule.thematicchart.hidden;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class MajorHiddenEntry  {
    int majorHiddenHadRectifiedCount;   //重大隐患   已整改数
    int majorHiddenNotRectifiedCount;//重大隐患 未整改数
    String majorHiddenRectifyRate; //整改率(百分值 eg:20%)
    int lateOfMajorRectifyCount;// 重大隐患 已逾期数
    int hadSupervisingCount;//重大隐患 已经督办数

    public int getMajorHiddenHadRectifiedCount() {
        return majorHiddenHadRectifiedCount;
    }

    public void setMajorHiddenHadRectifiedCount(int majorHiddenHadRectifiedCount) {
        this.majorHiddenHadRectifiedCount = majorHiddenHadRectifiedCount;
    }

    public int getMajorHiddenNotRectifiedCount() {
        return majorHiddenNotRectifiedCount;
    }

    public void setMajorHiddenNotRectifiedCount(int majorHiddenNotRectifiedCount) {
        this.majorHiddenNotRectifiedCount = majorHiddenNotRectifiedCount;
    }

    public String getMajorHiddenRectifyRate() {
        return majorHiddenRectifyRate == null ? "" : majorHiddenRectifyRate;
    }

    public void setMajorHiddenRectifyRate(String majorHiddenRectifyRate) {
        this.majorHiddenRectifyRate = majorHiddenRectifyRate;
    }

    public int getLateOfMajorRectifyCount() {
        return lateOfMajorRectifyCount;
    }

    public void setLateOfMajorRectifyCount(int lateOfMajorRectifyCount) {
        this.lateOfMajorRectifyCount = lateOfMajorRectifyCount;
    }

    public int getHadSupervisingCount() {
        return hadSupervisingCount;
    }

    public void setHadSupervisingCount(int hadSupervisingCount) {
        this.hadSupervisingCount = hadSupervisingCount;
    }

}
