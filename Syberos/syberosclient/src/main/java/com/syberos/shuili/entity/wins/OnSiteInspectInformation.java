package com.syberos.shuili.entity.wins;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/11
 * @time: 下午2:39
 * @email: ZhaoDongshuang@syberos.com
 * @description 现场稽查信息
 */
public class OnSiteInspectInformation implements Serializable {

    private String group;       // 稽查组名
    private String batch;       // 稽查批次
    private String time;        // 稽查时间
    private String area;        // 稽查地区
    private String special;     // 特派员
    private String assistant;   // 特派员助理
    private String experts;     // 稽查专家

    private List<String> projects = null; // 稽查项目
    private List<InspectProblemInformation> inspectProblems = null; // 稽察问题

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<InspectProblemInformation> getInspectProblems() {
        return inspectProblems;
    }

    public void setInspectProblems(List<InspectProblemInformation> inspectProblems) {
        this.inspectProblems = inspectProblems;
    }

}
