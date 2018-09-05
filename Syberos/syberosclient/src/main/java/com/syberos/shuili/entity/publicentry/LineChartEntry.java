package com.syberos.shuili.entity.publicentry;

import java.io.Serializable;

public class LineChartEntry implements Serializable {
    String date;
    float score;

    public LineChartEntry() {
    }

    public LineChartEntry(String date, float score) {
        this.date = date;
        this.score = score;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
