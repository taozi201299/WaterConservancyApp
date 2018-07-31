package com.syberos.shuili.entity.gong_zuo_kao_he;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.List;

/**
 * created by：toby on 18-4-23 16:45
 * email：zhaodongshuang@syberos.com
 * 工作考核：水利稽查考核:考前摸底信息
 */
public class InspectAssessInfo extends HttpBaseResponse<InspectAssessInfo> {

    private String name;                // 被考核对象名称

    // 稽察工作情况
    private List<InspectAssessPlanInfo> inspectAssessPlanInfoList;

    // 被稽察历史情况
    private List<InspectAssessHistory> inspectAssessHistoryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InspectAssessPlanInfo> getInspectAssessPlanInfoList() {
        return inspectAssessPlanInfoList;
    }

    public void setInspectAssessPlanInfoList(
            List<InspectAssessPlanInfo> inspectAssessPlanInfoList) {
        this.inspectAssessPlanInfoList = inspectAssessPlanInfoList;
    }

    public List<InspectAssessHistory> getInspectAssessHistoryList() {
        return inspectAssessHistoryList;
    }

    public void setInspectAssessHistoryList(List<InspectAssessHistory> inspectAssessHistoryList) {
        this.inspectAssessHistoryList = inspectAssessHistoryList;
    }

}
