package com.syberos.shuili.entity.wins;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/15
 * @time: 下午9:20
 * @email: ZhaoDongshuang@syberos.com
 * 稽查方案
 */
public class InspectPlan implements Serializable {

    private String name;            // 稽察方案名称
    private String batch;           // 稽察批次
    private String time;            // 稽察时间
    private List<String> groups;    // 检查分组
    private String special;         // 特派员
    private String assistant;       // 特派员助理
    private String experts;         // 稽查专家
    private String project;         // 稽察项目
    private String problemCount;    // 问题数量


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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
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

}
