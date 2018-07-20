package com.example.testmodule.thematicchart.accident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.accident.
 */
public class RankRateEntry implements Serializable {
    List<ItemEntry> dataList;

    public List<ItemEntry> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<ItemEntry> dataList) {
        this.dataList = dataList;
    }

    public static class ItemEntry implements Serializable {
        int itemCount;
        int itemName;

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public int getItemName() {
            return itemName;
        }

        public void setItemName(int itemName) {
            this.itemName = itemName;
        }
    }
}
