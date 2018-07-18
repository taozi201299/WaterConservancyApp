package com.syberos.shuili.entity.thematicchart.waterinspection;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspection.
 */
public class WaterInspectionEntry {
    //    水利稽查
    List<PointEntry> pointEntryList;
    //  xxxx年本部门水利稽察情况
    WISituationEntry wiSituationEntry;
    //稽察问题分类统计占比图
    WISortEntry wiClassisEntry;
    //稽察问题等级统计
    WIProblemEntry wiProblemEntry;


    //稽察问题排名(本部门)
    List<InspectionRanking> securityRanking;

    //安全情况 (直管工程\流域机构\行业监管)
    List<ProjectEntry> projectEntryList;


    private class InspectionRanking {
        String rangeId;//   id
        String rangeName;// 流域\直管
        List<ProjectEntry> projectEntryList;
    }



}
