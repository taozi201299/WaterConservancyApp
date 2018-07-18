package com.syberos.shuili.entity.thematicchart.securitychecks;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.List;
import java.util.Map;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */
public class SecurityCheckEntry {
    //  安全检查
    List<PointEntry> pointEntryList;
    //部门检查情况
    SecuritySituationEntry securitySituationEntry;
    //检查完成情况统计
    SecurityRankRateEntry securityRankRateEntry;


    //隐患数量排名(本部门)
    List<SecurityRanking> securityRanking;

    //安全情况 (直管工程\流域机构\行业监管)
    List<ProjectEntry> projectEntryList;

    private class SecurityRanking {
        String rangeId;//   id
        String rangeName;// 流域\监管\直管
        List<ProjectEntry> projectEntryList;
    }


}
