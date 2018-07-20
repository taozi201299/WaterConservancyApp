package com.example.testmodule.thematicchart.workassessment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.workassessment.
 */
public class WARecentlyScoreEntry implements Serializable {
    int totalScore;//满分
    List<ScoreItem> dataList;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<ScoreItem> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<ScoreItem> dataList) {
        this.dataList = dataList;
    }

    public static class ScoreItem implements Serializable {
        String date;//月份
        int score;//分数

        public String getDate() {
            return date == null ? "" : date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getScore() {

            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

}

