package com.syberos.shuili.entity.gong_zuo_kao_he;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.List;

/**
 * created by：toby on 18-4-23 09:54
 * email：zhaodongshuang@syberos.com
 * 工作考核：现场检查信息
 */
public class OnSiteInspectionInfo
        extends HttpBaseResponse<OnSiteInspectionInfo> {

    private String name;                // 考核组名称
    private String plan;                // 所属考核方案
    private String time;                // 考核时间
    private String content;             // 考核内容
    private String groupName;           // 组长姓名
    private String groupUnit;           // 组长单位
    private String childUnit;           // 组员单位
    private String experts;             // 专家姓名

    // 被检对象
    private List<InspectionObjectInfo> inspectionObjectInfoList;

    // 扣分记录
    private List<DeductMarksInfo> deductMarksInfoList;

    // 水利稽查信息
    private List<InspectAssessInfo> inspectAssessInfoList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupUnit() {
        return groupUnit;
    }

    public void setGroupUnit(String groupUnit) {
        this.groupUnit = groupUnit;
    }

    public String getChildUnit() {
        return childUnit;
    }

    public void setChildUnit(String childUnit) {
        this.childUnit = childUnit;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public List<InspectionObjectInfo> getInspectionObjectInfoList() {
        return inspectionObjectInfoList;
    }

    public void setInspectionObjectInfoList(List<InspectionObjectInfo> inspectionObjectInfoList) {
        this.inspectionObjectInfoList = inspectionObjectInfoList;
    }

    public List<DeductMarksInfo> getDeductMarksInfoList() {
        return deductMarksInfoList;
    }

    public void setDeductMarksInfoList(List<DeductMarksInfo> deductMarksInfoList) {
        this.deductMarksInfoList = deductMarksInfoList;
    }

    public List<InspectAssessInfo> getInspectAssessInfoList() {
        return inspectAssessInfoList;
    }

    public void setInspectAssessInfoList(List<InspectAssessInfo> inspectAssessInfoList) {
        this.inspectAssessInfoList = inspectAssessInfoList;
    }
}
