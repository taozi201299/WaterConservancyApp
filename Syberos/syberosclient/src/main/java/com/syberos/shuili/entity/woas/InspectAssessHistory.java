package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-23 17:07
 * email：zhaodongshuang@syberos.com
 */
public class InspectAssessHistory extends HttpBaseResponse<InspectAssessHistory> {

    private String time;            // 时间
    private String score;           // 分数


    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score == null ? "" : score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
