package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-23 17:03
 * email：zhaodongshuang@syberos.com
 */
public class InspectAssessPlanBatchInfo extends HttpBaseResponse<InspectAssessPlanBatchInfo> {

    private String batch;               // 稽察批次
    private String time;                // 稽察时间
    private String projects;            // 稽察项目


    public String getBatch() {
        return batch == null ? "" : batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProjects() {
        return projects == null ? "" : projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }
}
