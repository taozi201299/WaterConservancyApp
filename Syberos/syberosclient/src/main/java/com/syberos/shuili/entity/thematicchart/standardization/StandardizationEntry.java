package com.syberos.shuili.entity.thematicchart.standardization;

import com.syberos.shuili.entity.thematicchart.ProjectRankEntry;
import com.syberos.shuili.entity.thematicchart.RankRateItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.
 */
public class StandardizationEntry implements Serializable {
    //  标准化检查
    private List<StandardizationPointEntry> pointEntryList;
    //  xxxx年直管工程标准化情况
    private StandardizationSituationEntry standardizationRiskSituationEntry;//xxxx年直管工程标准化情况
    //标准化等级占比
    private List<RankRateItem> standardizationRankRateList;//标准化等级占比
    //   标准化证书情况
    private StandardizationCertificateEntry standardizationCertificateEntry;//标准化证书情况
    //    标准化考核情况
    private StandardizationAssessment standardizationAssessment;// 标准化考核

    private List<ProjectRankEntry> projectRankEntries;

    public List<ProjectRankEntry> getProjectRankEntries() {
        return projectRankEntries;
    }

    public void setProjectRankEntries(List<ProjectRankEntry> projectRankEntries) {
        this.projectRankEntries = projectRankEntries;
    }

    public List<StandardizationPointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<StandardizationPointEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

    public StandardizationSituationEntry getStandardizationRiskSituationEntry() {
        return standardizationRiskSituationEntry;
    }

    public void setStandardizationRiskSituationEntry(StandardizationSituationEntry standardizationRiskSituationEntry) {
        this.standardizationRiskSituationEntry = standardizationRiskSituationEntry;
    }

    public StandardizationCertificateEntry getStandardizationCertificateEntry() {
        return standardizationCertificateEntry;
    }

    public void setStandardizationCertificateEntry(StandardizationCertificateEntry standardizationCertificateEntry) {
        this.standardizationCertificateEntry = standardizationCertificateEntry;
    }

    public StandardizationAssessment getStandardizationAssessment() {
        return standardizationAssessment;
    }

    public List<RankRateItem> getStandardizationRankRateList() {
        if (standardizationRankRateList == null) {
            return new ArrayList<>();
        }
        return standardizationRankRateList;
    }

    public void setStandardizationRankRateList(List<RankRateItem> standardizationRankRateList) {
        this.standardizationRankRateList = standardizationRankRateList;
    }

    public void setStandardizationAssessment(StandardizationAssessment standardizationAssessment) {
        this.standardizationAssessment = standardizationAssessment;
    }
}
