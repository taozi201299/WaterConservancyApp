package com.syberos.shuili.entity.thematicchart.accident;

import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
class RankRateEntry {
    List<ItemEntry> dataList;

    private class ItemEntry {
        int itemCount;
        int itemName;
    }
}
