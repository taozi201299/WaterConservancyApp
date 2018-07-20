package com.example.testmodule.thematicchart.accident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class AccidentDetailEntry implements Serializable {
    List<AccidentPointEntry> accidentPointEntries;
    SituationEntry situationEntry;
    RankRateEntry rankRateEntry;
    AccidentStatisticEntry normalAccidentDataEntry;
    AccidentStatisticEntry majorAccidentDataEntry;


    public List<AccidentPointEntry> getAccidentPointEntries() {
        if (accidentPointEntries == null) {
            return new ArrayList<>();
        }
        return accidentPointEntries;
    }

    public void setAccidentPointEntries(List<AccidentPointEntry> accidentPointEntries) {
        this.accidentPointEntries = accidentPointEntries;
    }

    public SituationEntry getSituationEntry() {
        return situationEntry;
    }

    public void setSituationEntry(SituationEntry situationEntry) {
        this.situationEntry = situationEntry;
    }

    public RankRateEntry getRankRateEntry() {
        return rankRateEntry;
    }

    public void setRankRateEntry(RankRateEntry rankRateEntry) {
        this.rankRateEntry = rankRateEntry;
    }

    public AccidentStatisticEntry getNormalAccidentDataEntry() {
        return normalAccidentDataEntry;
    }

    public void setNormalAccidentDataEntry(AccidentStatisticEntry normalAccidentDataEntry) {
        this.normalAccidentDataEntry = normalAccidentDataEntry;
    }

    public AccidentStatisticEntry getMajorAccidentDataEntry() {
        return majorAccidentDataEntry;
    }

    public void setMajorAccidentDataEntry(AccidentStatisticEntry majorAccidentDataEntry) {
        this.majorAccidentDataEntry = majorAccidentDataEntry;
    }
}
