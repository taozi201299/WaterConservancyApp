package com.syberos.shuili.entity.thematicchart.workassessment;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.workassessment.
 */
public class WorkAssessmentEntry implements Serializable {
    //    工作考核
    List<WorkPointEntry> pointEntryList;
    //    xxxx年直管工程工作考核情况
    WASituationEntry waSituationEntry;
    //近5年个工作考核得分情况
    WARecentlyScoreEntry waRecentlyScoreEntry;


    public List<WorkPointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<WorkPointEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

    public WASituationEntry getWaSituationEntry() {
        return waSituationEntry;
    }

    public void setWaSituationEntry(WASituationEntry waSituationEntry) {
        this.waSituationEntry = waSituationEntry;
    }

    public WARecentlyScoreEntry getWaRecentlyScoreEntry() {
        return waRecentlyScoreEntry;
    }

    public void setWaRecentlyScoreEntry(WARecentlyScoreEntry waRecentlyScoreEntry) {
        this.waRecentlyScoreEntry = waRecentlyScoreEntry;
    }
}
