package com.syberos.shuili.entity.thematicchart;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class ProjectEntry implements Serializable {
    public ProjectEntry(String proId, String proName, String proTroubleCount) {
        this.proId = proId;
        this.proName = proName;
        this.proTroubleCount = proTroubleCount;
    }

    //    工程id
    String proId;
    //    工程名字
    String proName;
    //    工程中隐患数
    String proTroubleCount;

    public String getProId() {
        return proId == null ? "" : proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName == null ? "" : proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }


    public String getProTroubleCount() {
        return proTroubleCount == null ? "" : proTroubleCount;
    }

    public void setProTroubleCount(String proTroubleCount) {
        this.proTroubleCount = proTroubleCount;
    }
}
