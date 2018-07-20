package com.example.testmodule.thematicchart.accident;


import com.example.testmodule.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/18.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class AccidentPointEntry extends PointEntry {
    AccidentPointInfoEntry accidentPointInfoEntry;

    public AccidentPointInfoEntry getAccidentPointInfoEntry() {
        return accidentPointInfoEntry;
    }

    public void setAccidentPointInfoEntry(AccidentPointInfoEntry accidentPointInfoEntry) {
        this.accidentPointInfoEntry = accidentPointInfoEntry;
    }
}
