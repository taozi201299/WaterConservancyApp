package com.syberos.shuili.entity.thematicchart.securitychecks;

import java.util.List;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */
 class SecurityRankRateEntry {
    List<RankRateItem> rankRateItemList;
    //占比item
    class RankRateItem {
        int itemCount;
        int itemName;
    }
}

