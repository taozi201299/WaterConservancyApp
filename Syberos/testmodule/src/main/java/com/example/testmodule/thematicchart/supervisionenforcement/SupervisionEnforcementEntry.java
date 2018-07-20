package com.example.testmodule.thematicchart.supervisionenforcement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.supervisionenforcement.
 */
public class SupervisionEnforcementEntry implements Serializable {
    //    监督执法
    private List<SupervisionOrgEntry> pointEntryList;
    // 被处罚的单位
    private int punishPartCount;
    // 已整改单位
    private int hadRectifyPartCount;

    //  总执法次数
    private int totalEnforcementCount;
    // 总执法人数
    private int getTotalEnforcementPersonCount;

    public List<SupervisionOrgEntry> getPointEntryList() {
        if (pointEntryList == null) {
            return new ArrayList<>();
        }
        return pointEntryList;
    }

    public void setPointEntryList(List<SupervisionOrgEntry> pointEntryList) {
        this.pointEntryList = pointEntryList;
    }

    public int getPunishPartCount() {
        return punishPartCount;
    }

    public void setPunishPartCount(int punishPartCount) {
        this.punishPartCount = punishPartCount;
    }

    public int getHadRectifyPartCount() {
        return hadRectifyPartCount;
    }

    public void setHadRectifyPartCount(int hadRectifyPartCount) {
        this.hadRectifyPartCount = hadRectifyPartCount;
    }

    public int getTotalEnforcementCount() {
        return totalEnforcementCount;
    }

    public void setTotalEnforcementCount(int totalEnforcementCount) {
        this.totalEnforcementCount = totalEnforcementCount;
    }

    public int getGetTotalEnforcementPersonCount() {
        return getTotalEnforcementPersonCount;
    }

    public void setGetTotalEnforcementPersonCount(int getTotalEnforcementPersonCount) {
        this.getTotalEnforcementPersonCount = getTotalEnforcementPersonCount;
    }
}
