package com.syberos.shuili.entity.thematicchart.accident;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class AccidentDetailEntry {
    List<PointEntry> pointEntryList;
    SituationEntry situationEntry;
    RankRateEntry rankRateEntry;
    List<AccidentStatisticEntry> statisticDataLis;
    List<ProjectEntry> projectEntryList;
}
