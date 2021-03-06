package com.syberos.shuili.entity.wins;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/11
 * @time: 下午2:39
 * @email: ZhaoDongshuang@syberos.com
 * @description 现场稽察信息
 */
public class OnSiteInspectInformation implements Serializable {

    private String group;       // 稽察组名
    private String batch;       // 稽察批次
    private String time;        // 稽察时间
    private String area;        // 稽察地区
    private String special;     // 特派员
    private String assistant;   // 特派员助理
    private String experts;     // 稽察专家

    private List<String> projects = null; // 稽察项目
    private List<InspectProblemInformation> inspectProblems = null; // 稽察问题


    public String getGroup() {
        return group == null ? "" : group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

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

    public String getArea() {
        return area == null ? "" : area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSpecial() {
        return special == null ? "" : special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getAssistant() {
        return assistant == null ? "" : assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getExperts() {
        return experts == null ? "" : experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public List<String> getProjects() {
        if (projects == null) {
            return new ArrayList<>();
        }
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<InspectProblemInformation> getInspectProblems() {
        if (inspectProblems == null) {
            return new ArrayList<>();
        }
        return inspectProblems;
    }

    public void setInspectProblems(List<InspectProblemInformation> inspectProblems) {
        this.inspectProblems = inspectProblems;
    }
}
