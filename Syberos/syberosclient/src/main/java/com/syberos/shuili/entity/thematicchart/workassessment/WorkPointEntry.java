package com.syberos.shuili.entity.thematicchart.workassessment;

import com.syberos.shuili.entity.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.workassessment.
 */
public class WorkPointEntry extends PointEntry {
    WorkAssessmentInfoEntry workAssessmentInfoEntry;

    public WorkAssessmentInfoEntry getWorkAssessmentInfoEntry() {
        return workAssessmentInfoEntry;
    }

    public void setWorkAssessmentInfoEntry(WorkAssessmentInfoEntry workAssessmentInfoEntry) {
        this.workAssessmentInfoEntry = workAssessmentInfoEntry;
    }
}
