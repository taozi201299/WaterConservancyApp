package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.List;

/**
 * Created by jidan on 18-3-20.
 */

public class HiddenInvestigationTaskInfo extends HttpBaseResponse<HiddenInvestigationTaskInfo> {
    /**
     * 隐患状态
     */
    String hiddStat;
    /**
     * 0 未知 1 是 2 否
     */
    String isList;
    String hiddClas;
    String inspOrgGuid;
    String engGuid;
    String hiddGuid;
    String hiddName;
    String inspDate;
    String engName;
    String requCompDate;
    String hiddGrad;

    private String noticeName;      // 部署通知名称
    private String groupName;       // 检查组名称
    private String planName;        // 检查方案名称
    private String time;            // 检查时间
    private String content;         // 检查内容
    private String groupLeader;     // 组长名称
    private String groupUnit;       // 组长单位
    private String memberUnit;      // 组员单位
    private String experts;         // 专家姓名
    private String area;            // 检查区域
    private String project;         // 检查对象
    private String problemCount;    // 隐患数量

    private List<String> projects = null;      // 检查工程
    private List<HiddenProjectInfo> hiddenProblems = null; // 隐患
    private List<String> childGroups = null;    // 检查分组


    public String getHiddStat() {
        return hiddStat;
    }

    public void setHiddStat(String hiddStat) {
        this.hiddStat = (hiddStat != null) ? hiddStat : "";
    }

    public String getIsList() {
        return isList;
    }

    public void setIsList(String isList) {
        this.isList = (isList != null) ? isList : "";
    }

    public String getHiddClas() {
        return hiddClas;
    }

    public void setHiddClas(String hiddClas) {
        this.hiddClas = (hiddClas != null) ? hiddClas : "";
    }

    public String getInspOrgGuid() {
        return inspOrgGuid;
    }

    public void setInspOrgGuid(String inspOrgGuid) {
        this.inspOrgGuid = (inspOrgGuid != null)?inspOrgGuid:"";
    }

    public String getEngGuid() {
        return engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = (engGuid != null) ?engGuid :"";
    }

    public String getHiddGuid() {
        return hiddGuid;
    }

    public void setHiddGuid(String hiddGuid) {
        this.hiddGuid = (hiddGuid != null) ? hiddGuid : "";
    }

    public String getHiddName() {
        return hiddName;
    }

    public void setHiddName(String hiddName) {
        this.hiddName = (hiddName != null) ? hiddName: "";
    }

    public String getInspDate() {
        return inspDate;
    }

    public void setInspDate(String inspDate) {
        this.inspDate = (inspDate != null) ? inspDate :"";
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = (engName != null) ? engName :"";
    }

    public String getRequCompDate() {
        return requCompDate;
    }

    public void setRequCompDate(String requCompDate) {
        this.requCompDate = (requCompDate != null) ?requCompDate :"";
    }

    public String getHiddGrad() {
        return hiddGrad;
    }

    public void setHiddGrad(String hiddGrad) {
        this.hiddGrad = (hiddGrad != null) ?hiddGrad :"";
    }


    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<HiddenProjectInfo> getHiddenProblems() {
        return hiddenProblems;
    }

    public void setHiddenProblems(List<HiddenProjectInfo> hiddenProblems) {
        this.hiddenProblems = hiddenProblems;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getGroupUnit() {
        return groupUnit;
    }

    public void setGroupUnit(String groupUnit) {
        this.groupUnit = groupUnit;
    }

    public String getMemberUnit() {
        return memberUnit;
    }

    public void setMemberUnit(String memberUnit) {
        this.memberUnit = memberUnit;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public List<String> getChildGroups() {
        return childGroups;
    }

    public void setChildGroups(List<String> childGroups) {
        this.childGroups = childGroups;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(String problemCount) {
        this.problemCount = problemCount;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

}
