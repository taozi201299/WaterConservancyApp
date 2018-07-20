package com.example.testmodule.thematicchart.supervisionenforcement;


import com.example.testmodule.thematicchart.PointEntry;

/**
 * Created by BZB on 2018/7/20.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.supervisionenforcement.
 */
public class SupervisionPointEntry extends PointEntry {
    SupervisionOrgEntry supervisionOrgEntry;

    public SupervisionOrgEntry getSupervisionOrgEntry() {
        return supervisionOrgEntry;
    }

    public void setSupervisionOrgEntry(SupervisionOrgEntry supervisionOrgEntry) {
        this.supervisionOrgEntry = supervisionOrgEntry;
    }
}
