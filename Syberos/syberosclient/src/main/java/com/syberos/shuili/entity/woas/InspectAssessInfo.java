package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * created by：toby on 18-4-23 16:45
 * email：zhaodongshuang@syberos.com
 * 工作考核：水利稽察考核:考前摸底信息
 */
public class InspectAssessInfo extends HttpBaseResponse<InspectAssessInfo> {

    private String name;                // 被考核对象名称

    // 稽察工作情况
    private List<InspectAssessPlanInfo> inspectAssessPlanInfoList;

    // 被稽察历史情况
    private List<InspectAssessHistory> inspectAssessHistoryList;


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InspectAssessPlanInfo> getInspectAssessPlanInfoList() {
        if (inspectAssessPlanInfoList == null) {
            return new ArrayList<>();
        }
        return inspectAssessPlanInfoList;
    }

    public void setInspectAssessPlanInfoList(List<InspectAssessPlanInfo> inspectAssessPlanInfoList) {
        this.inspectAssessPlanInfoList = inspectAssessPlanInfoList;
    }

    public List<InspectAssessHistory> getInspectAssessHistoryList() {
        if (inspectAssessHistoryList == null) {
            return new ArrayList<>();
        }
        return inspectAssessHistoryList;
    }

    public void setInspectAssessHistoryList(List<InspectAssessHistory> inspectAssessHistoryList) {
        this.inspectAssessHistoryList = inspectAssessHistoryList;
    }
}
