package com.example.testmodule.thematicchart.risksource;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.sourcerist.
 */
//  重大危险源数据
//  已管控2个，未管控5个 管控率21%
//  已备案1个    备案率15%
public class RiskStatisticsEntry implements Serializable {
    private int hadRegulatoryControlCount;//已管控
    private int noRegulatoryControlCount;//未管控
    private String regulatoryControlRate;//管控率
    private int RecordCount;//已备案
    private String recordRate;//备案率

    public int getHadRegulatoryControlCount() {
        return hadRegulatoryControlCount;
    }

    public void setHadRegulatoryControlCount(int hadRegulatoryControlCount) {
        this.hadRegulatoryControlCount = hadRegulatoryControlCount;
    }

    public int getNoRegulatoryControlCount() {
        return noRegulatoryControlCount;
    }

    public void setNoRegulatoryControlCount(int noRegulatoryControlCount) {
        this.noRegulatoryControlCount = noRegulatoryControlCount;
    }

    public String getRegulatoryControlRate() {
        return regulatoryControlRate == null ? "" : regulatoryControlRate;
    }

    public void setRegulatoryControlRate(String regulatoryControlRate) {
        this.regulatoryControlRate = regulatoryControlRate;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int recordCount) {
        RecordCount = recordCount;
    }

    public String getRecordRate() {
        return recordRate == null ? "" : recordRate;
    }

    public void setRecordRate(String recordRate) {
        this.recordRate = recordRate;
    }
}
