package com.syberos.shuili.entity.thematicchart.standardization;

import java.util.List;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.standardization.
 */
//标准化等级占比
 class StandardizationRankRateEntry {
    List<RankRateItem> rankRateItem;

    //占比item
    public class RankRateItem {
        int itemCount;
        int itemName;
    }
}


