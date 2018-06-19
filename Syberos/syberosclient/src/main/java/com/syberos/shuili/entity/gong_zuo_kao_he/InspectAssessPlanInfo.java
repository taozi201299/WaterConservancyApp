package com.syberos.shuili.entity.gong_zuo_kao_he;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

import java.util.List;

/**
 * created by：toby on 18-4-23 16:49
 * email：zhaodongshuang@syberos.com
 */
public class InspectAssessPlanInfo extends HttpHiddenBaseResponse<InspectAssessPlanInfo> {

    private String name;        // 稽察计划名称
    private String time;        // 稽察时间

    private List<InspectAssessPlanBatchInfo> inspectAssessPlanBatchInfoList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<InspectAssessPlanBatchInfo> getInspectAssessPlanBatchInfoList() {
        return inspectAssessPlanBatchInfoList;
    }

    public void setInspectAssessPlanBatchInfoList(
            List<InspectAssessPlanBatchInfo> inspectAssessPlanBatchInfoList) {
        this.inspectAssessPlanBatchInfoList = inspectAssessPlanBatchInfoList;
    }

}
