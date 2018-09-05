package com.example.testmodule.securitycloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/23.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.securitycloud.
 */
public class SecurityCloudEntry implements Serializable {
    SynthesisInfoEntry synthesisInfoEntry;
    AccidentInfoEntry accidentInfoEntry;//事故
    HiddenInfoEntry hiddenInfoEntry;//隐患
    RiskSourceEntry riskSourceEntry;//危险源
    StraightTubeManageEntry straightTubeManageEntry;//直管的 “管理”数据
    SupervisionMangeEntry supervisionMangeEntry;//监管 和 流域的 “管理”数据
    CompScoreTrend compScoreTrend;  //综合得分 趋势
    List<AreaRank> rankList;    //排名

    public SynthesisInfoEntry getSynthesisInfoEntry() {
        return synthesisInfoEntry;
    }

    public void setSynthesisInfoEntry(SynthesisInfoEntry synthesisInfoEntry) {
        this.synthesisInfoEntry = synthesisInfoEntry;
    }

    public AccidentInfoEntry getAccidentInfoEntry() {
        return accidentInfoEntry;
    }

    public void setAccidentInfoEntry(AccidentInfoEntry accidentInfoEntry) {
        this.accidentInfoEntry = accidentInfoEntry;
    }

    public HiddenInfoEntry getHiddenInfoEntry() {
        return hiddenInfoEntry;
    }

    public void setHiddenInfoEntry(HiddenInfoEntry hiddenInfoEntry) {
        this.hiddenInfoEntry = hiddenInfoEntry;
    }

    public RiskSourceEntry getRiskSourceEntry() {
        return riskSourceEntry;
    }

    public void setRiskSourceEntry(RiskSourceEntry riskSourceEntry) {
        this.riskSourceEntry = riskSourceEntry;
    }

    public StraightTubeManageEntry getStraightTubeManageEntry() {
        return straightTubeManageEntry;
    }

    public void setStraightTubeManageEntry(StraightTubeManageEntry straightTubeManageEntry) {
        this.straightTubeManageEntry = straightTubeManageEntry;
    }

    public SupervisionMangeEntry getSupervisionMangeEntry() {
        return supervisionMangeEntry;
    }

    public void setSupervisionMangeEntry(SupervisionMangeEntry supervisionMangeEntry) {
        this.supervisionMangeEntry = supervisionMangeEntry;
    }

    public CompScoreTrend getCompScoreTrend() {
        return compScoreTrend;
    }

    public void setCompScoreTrend(CompScoreTrend compScoreTrend) {
        this.compScoreTrend = compScoreTrend;
    }

    public List<AreaRank> getRankList() {
        if (rankList == null) {
            return new ArrayList<>();
        }
        return rankList;
    }

    public void setRankList(List<AreaRank> rankList) {
        this.rankList = rankList;
    }

    /**
     * 综合情况
     */
    public static class SynthesisInfoEntry implements Serializable {
        int score;//综合得分
        String chainRatio;//环比
        String sameTimeRatio;//同比

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getChainRatio() {
            return chainRatio == null ? "" : chainRatio;
        }

        public void setChainRatio(String chainRatio) {
            this.chainRatio = chainRatio;
        }

        public String getSameTimeRatio() {
            return sameTimeRatio == null ? "" : sameTimeRatio;
        }

        public void setSameTimeRatio(String sameTimeRatio) {
            this.sameTimeRatio = sameTimeRatio;
        }
    }

    public static class HiddenInfoEntry implements Serializable {
        int score;
        int noRectifyCount;//未整改隐患
        int totalHiddenCount;//隐患总数量

        int normalHiddenCount;//数量
        int normalHiddenHadRectifyCount;//已整改
        int normalHiddenNoRectifyCount;//未整改
        int normalLateNoRectifyCount;//逾期未整改

        int majorHiddenCount;//数量
        int majorHiddenHadRectifyCount;//已整改
        int majorHiddenNoRectifyCount;//未整改
        int majorLateNoRectifyCount;//逾期未整改
        int majorHadSupervisingCount;//已督办

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getNoRectifyCount() {
            return noRectifyCount;
        }

        public void setNoRectifyCount(int noRectifyCount) {
            this.noRectifyCount = noRectifyCount;
        }

        public int getTotalHiddenCount() {
            return totalHiddenCount;
        }

        public void setTotalHiddenCount(int totalHiddenCount) {
            this.totalHiddenCount = totalHiddenCount;
        }

        public int getNormalHiddenCount() {
            return normalHiddenCount;
        }

        public void setNormalHiddenCount(int normalHiddenCount) {
            this.normalHiddenCount = normalHiddenCount;
        }

        public int getNormalHiddenHadRectifyCount() {
            return normalHiddenHadRectifyCount;
        }

        public void setNormalHiddenHadRectifyCount(int normalHiddenHadRectifyCount) {
            this.normalHiddenHadRectifyCount = normalHiddenHadRectifyCount;
        }

        public int getNormalHiddenNoRectifyCount() {
            return normalHiddenNoRectifyCount;
        }

        public void setNormalHiddenNoRectifyCount(int normalHiddenNoRectifyCount) {
            this.normalHiddenNoRectifyCount = normalHiddenNoRectifyCount;
        }

        public int getNormalLateNoRectifyCount() {
            return normalLateNoRectifyCount;
        }

        public void setNormalLateNoRectifyCount(int normalLateNoRectifyCount) {
            this.normalLateNoRectifyCount = normalLateNoRectifyCount;
        }

        public int getMajorHiddenCount() {
            return majorHiddenCount;
        }

        public void setMajorHiddenCount(int majorHiddenCount) {
            this.majorHiddenCount = majorHiddenCount;
        }

        public int getMajorHiddenHadRectifyCount() {
            return majorHiddenHadRectifyCount;
        }

        public void setMajorHiddenHadRectifyCount(int majorHiddenHadRectifyCount) {
            this.majorHiddenHadRectifyCount = majorHiddenHadRectifyCount;
        }

        public int getMajorHiddenNoRectifyCount() {
            return majorHiddenNoRectifyCount;
        }

        public void setMajorHiddenNoRectifyCount(int majorHiddenNoRectifyCount) {
            this.majorHiddenNoRectifyCount = majorHiddenNoRectifyCount;
        }

        public int getMajorLateNoRectifyCount() {
            return majorLateNoRectifyCount;
        }

        public void setMajorLateNoRectifyCount(int majorLateNoRectifyCount) {
            this.majorLateNoRectifyCount = majorLateNoRectifyCount;
        }

        public int getMajorHadSupervisingCount() {
            return majorHadSupervisingCount;
        }

        public void setMajorHadSupervisingCount(int majorHadSupervisingCount) {
            this.majorHadSupervisingCount = majorHadSupervisingCount;
        }
    }

    public static class AccidentInfoEntry implements Serializable {

        int score;
        int totalCount;
        int deathCount;

        int accLevelOneCount; //一般事故
        int accLevelTwoCount; //较大事故
        int accLevelThreeCount;//重大事故
        int accLevelFourCount;// 特大事故
        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getDeathCount() {
            return deathCount;
        }

        public void setDeathCount(int deathCount) {
            this.deathCount = deathCount;
        }

        public int getAccLevelOneCount() {
            return accLevelOneCount;
        }

        public void setAccLevelOneCount(int accLevelOneCount) {
            this.accLevelOneCount = accLevelOneCount;
        }

        public int getAccLevelTwoCount() {
            return accLevelTwoCount;
        }

        public void setAccLevelTwoCount(int accLevelTwoCount) {
            this.accLevelTwoCount = accLevelTwoCount;
        }

        public int getAccLevelThreeCount() {
            return accLevelThreeCount;
        }

        public void setAccLevelThreeCount(int accLevelThreeCount) {
            this.accLevelThreeCount = accLevelThreeCount;
        }

        public int getAccLevelFourCount() {
            return accLevelFourCount;
        }

        public void setAccLevelFourCount(int accLevelFourCount) {
            this.accLevelFourCount = accLevelFourCount;
        }


    }

    public static class RiskSourceEntry implements Serializable {
        int score;//得分
        int hadControl; //已管控
        int noControl;//未管控
        int hadRecord;//已备案
        int noRecord;//未备案

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getHadControl() {
            return hadControl;
        }

        public void setHadControl(int hadControl) {
            this.hadControl = hadControl;
        }

        public int getNoControl() {
            return noControl;
        }

        public void setNoControl(int noControl) {
            this.noControl = noControl;
        }

        public int getHadRecord() {
            return hadRecord;
        }

        public void setHadRecord(int hadRecord) {
            this.hadRecord = hadRecord;
        }

        public int getNoRecord() {
            return noRecord;
        }

        public void setNoRecord(int noRecord) {
            this.noRecord = noRecord;
        }
    }


    public static class CompScoreTrend implements Serializable {
        int qualifiedScore;//合格分
        List<LineChartEntry> dataList;

        public int getQualifiedScore() {
            return qualifiedScore;
        }

        public void setQualifiedScore(int qualifiedScore) {
            this.qualifiedScore = qualifiedScore;
        }

        public List<LineChartEntry> getDataList() {
            if (dataList == null) {
                return new ArrayList<>();
            }
            return dataList;
        }

        public void setDataList(List<LineChartEntry> dataList) {
            this.dataList = dataList;
        }
    }

    public static class LineChartEntry implements Serializable {
        String date;
        int score;

        public String getDate() {
            return date == null ? "" : date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    public static class AreaRank implements Serializable {
        String id;
        String name;
        int score;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
