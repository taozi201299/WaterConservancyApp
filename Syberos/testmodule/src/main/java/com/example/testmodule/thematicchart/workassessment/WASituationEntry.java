package com.example.testmodule.thematicchart.workassessment;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.workassessment.
 */
public class WASituationEntry implements Serializable{

    int totalScore;//满分
    int score; //平均分

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}