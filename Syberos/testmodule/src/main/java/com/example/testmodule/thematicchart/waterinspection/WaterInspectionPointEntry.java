package com.example.testmodule.thematicchart.waterinspection;

import java.util.Map;

/**
 * Created by BZB on 2018/7/20.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspection.
 */
public class WaterInspectionPointEntry {
    //  (水利稽察|安全检查：流域（“1”）、监管(“2”),分本部数据("0"))
    Map<String, WaterInspectionInfoEntry> waterInspectionInfoEntryMap;

    public Map<String, WaterInspectionInfoEntry> getWaterInspectionInfoEntryMap() {
        return waterInspectionInfoEntryMap;
    }

    public void setWaterInspectionInfoEntryMap(Map<String, WaterInspectionInfoEntry> waterInspectionInfoEntryMap) {
        this.waterInspectionInfoEntryMap = waterInspectionInfoEntryMap;
    }
}
