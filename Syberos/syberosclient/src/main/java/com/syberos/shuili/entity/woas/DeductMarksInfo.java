package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-23 10:27
 * email：zhaodongshuang@syberos.com
 * 工作考核：现场检查:扣分信息
 */
public class DeductMarksInfo extends HttpBaseResponse<DeductMarksInfo> {

    private String unit;                // 被考核单位
    private String time;                // 扣分时间
    private String score;               // 考核扣分
    private String description;         // 扣分说明

    // TODO: 18-4-23 现场情况信息


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
