package com.syberos.shuili.entity.thematicchart.standardization;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.
 */
public class StandardizationEntry {
    //  标准化检查
    List<PointEntry> pointEntryList;
    //  xxxx年直管工程标准化情况
    StandardizationSituationEntry standardizationRiskSituationEntry;//xxxx年直管工程标准化情况
    //标准化等级占比
    StandardizationRankRateEntry standardizationRankRateEntry;//标准化等级占比
    //   标准化证书情况
    StandardizationCertificateEntry standardizationCertificateEntry;//标准化证书情况
    //    标准化考核情况
    StandardizationAssessment standardizationAssessment;// 标准化考核
    //    标准化情况
    List<ProjectEntry> projectEntryList;







}
