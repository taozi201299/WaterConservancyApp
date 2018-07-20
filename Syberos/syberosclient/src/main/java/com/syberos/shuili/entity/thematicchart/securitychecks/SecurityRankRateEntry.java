package com.syberos.shuili.entity.thematicchart.securitychecks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */
public class SecurityRankRateEntry implements Serializable {
    int hadCheckCount;
    int noCheckCount;

    public int getHadCheckCount() {
        return hadCheckCount;
    }

    public void setHadCheckCount(int hadCheckCount) {
        this.hadCheckCount = hadCheckCount;
    }

    public int getNoCheckCount() {
        return noCheckCount;
    }

    public void setNoCheckCount(int noCheckCount) {
        this.noCheckCount = noCheckCount;
    }
}

