package com.syberos.shuili.entity.thematicchart.accident;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class ProjectDetailEntry {
    String projectName;
    List<AccidentEntry> dataList;

    private class AccidentEntry {
        String entryTitle;
        AccidentStatisticEntry accidentStatisticEntry;
        String accReson;
        String accDescription;
    }
}
