package com.syberos.shuili.entity.thematicchart.risksource;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.sourcerist.
 */
//  重大风险源数据
//  已管控2个，未管控5个 管控率21%
//  已备案1个    备案率15%
class RiskStatisticsEntry {
    String RiskSourceName;//标题 重大风险源数据
    int hadRegulatoryControlCount;//已管控
    int noRegulatoryControlCount;//未管控
    String regulatoryControlRate;//管控率
    int RecordCount;//已备案
    String RecordRate;//备案率
}
