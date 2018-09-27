package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * created by：toby on 18-4-23 16:49
 * email：zhaodongshuang@syberos.com
 */
public class InspectAssessPlanInfo extends HttpBaseResponse<InspectAssessPlanInfo> {

    private String name;        // 稽察计划名称
    private String time;        // 稽察时间

    private List<InspectAssessPlanBatchInfo> inspectAssessPlanBatchInfoList;


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<InspectAssessPlanBatchInfo> getInspectAssessPlanBatchInfoList() {
        if (inspectAssessPlanBatchInfoList == null) {
            return new ArrayList<>();
        }
        return inspectAssessPlanBatchInfoList;
    }

    public void setInspectAssessPlanBatchInfoList(List<InspectAssessPlanBatchInfo> inspectAssessPlanBatchInfoList) {
        this.inspectAssessPlanBatchInfoList = inspectAssessPlanBatchInfoList;
    }
}
