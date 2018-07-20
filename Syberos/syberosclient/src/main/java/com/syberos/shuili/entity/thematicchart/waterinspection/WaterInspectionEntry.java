package com.syberos.shuili.entity.thematicchart.waterinspection;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspection.
 */
public class WaterInspectionEntry implements Serializable{
    //    水利稽查
    List<WaterInspectionPointEntry> pointEntryList;
    //  xxxx年本部门水利稽察情况
    WISituationEntry wiSituationEntry;
    //稽察问题分类统计占比图
    WISortEntry wiClassisEntry;
    //稽察问题等级统计
    WIProblemEntry wiProblemEntry;

    public List<WaterInspectionPointEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<WaterInspectionPointEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

    public WISituationEntry getWiSituationEntry() {
        return wiSituationEntry;
    }

    public void setWiSituationEntry(WISituationEntry wiSituationEntry) {
        this.wiSituationEntry = wiSituationEntry;
    }

    public WISortEntry getWiClassisEntry() {
        return wiClassisEntry;
    }

    public void setWiClassisEntry(WISortEntry wiClassisEntry) {
        this.wiClassisEntry = wiClassisEntry;
    }

    public WIProblemEntry getWiProblemEntry() {
        return wiProblemEntry;
    }

    public void setWiProblemEntry(WIProblemEntry wiProblemEntry) {
        this.wiProblemEntry = wiProblemEntry;
    }
}
