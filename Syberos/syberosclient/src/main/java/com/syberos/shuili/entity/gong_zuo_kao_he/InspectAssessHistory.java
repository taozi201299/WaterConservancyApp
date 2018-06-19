package com.syberos.shuili.entity.gong_zuo_kao_he;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * created by：toby on 18-4-23 17:07
 * email：zhaodongshuang@syberos.com
 */
public class InspectAssessHistory extends HttpHiddenBaseResponse<InspectAssessHistory> {

    private String time;            // 时间
    private String score;           // 分数


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
