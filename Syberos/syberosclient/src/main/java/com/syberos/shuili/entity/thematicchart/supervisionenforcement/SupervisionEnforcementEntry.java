package com.syberos.shuili.entity.thematicchart.supervisionenforcement;

import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.supervisionenforcement.
 */
public class SupervisionEnforcementEntry {
    //    监督执法
    List<PointEntry> pointEntryList;
    // 被处罚的单位
    int punishPartCount;
    // 已整改单位
    int hadRectifyPartCount;

    //  总执法次数
    int totalEnforcementCount;
    // 总执法人数
    int getTotalEnforcementPercentCount;
    
    List<ProjectEntry> projectEntryList;

}
