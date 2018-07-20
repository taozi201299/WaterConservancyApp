package com.syberos.shuili.entity.thematicchart.standardization;

import com.syberos.shuili.entity.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.standardization.
 */
//点击 点的 详情
public class StandardizationPointEntry extends PointEntry {
    //流域机构\监管的 显示改数据
    StandardizationAssessment standardizationAssessment;
    //    直管的 显示该数据
    StandardizationInfoEntry standardizationInfoEntry;

    public StandardizationAssessment getStandardizationAssessment() {
        return standardizationAssessment;
    }

    public void setStandardizationAssessment(StandardizationAssessment standardizationAssessment) {
        this.standardizationAssessment = standardizationAssessment;
    }

    public StandardizationInfoEntry getStandardizationInfoEntry() {
        return standardizationInfoEntry;
    }

    public void setStandardizationInfoEntry(StandardizationInfoEntry standardizationInfoEntry) {
        this.standardizationInfoEntry = standardizationInfoEntry;
    }
}
