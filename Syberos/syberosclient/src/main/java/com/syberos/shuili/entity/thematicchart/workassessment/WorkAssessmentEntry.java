package com.syberos.shuili.entity.thematicchart.workassessment;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.workassessment.
 */
public class WorkAssessmentEntry {
    //    工作考核
    List<PointEntry> pointEntryList;
    //    xxxx年直管工程工作考核情况
    WASituationEntry waSituationEntry;
    //近5年个工作考核得分情况
    WARecentlyScoreEntry waRecentlyScoreEntry;
    //    工作考核得分排名
    List<ProjectEntry> projectEntryList;

    public class WASituationEntry {
        //        平均分
        int score;
    }

    private class WARecentlyScoreEntry {
        int totalScore;//满分
        List<ScoreItem> dataList;

    }

    private class ScoreItem {
        String date;//月份
        int score;//分数
    }
}
