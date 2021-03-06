package com.syberos.shuili.entity.wins;

import java.io.Serializable;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/11
 * @time: 下午2:52
 * @email: ZhaoDongshuang@syberos.com
 * @description 稽察问题详情
 */
public class InspectProblemInformation implements Serializable {

    public static final int SEVERITY_NORMAL = 0; // 一般
    public static final int SEVERITY_BIG    = 1; // 严重
    public static final int SEVERITY_LARGE  = 2; // 非常严重

    private String project;     // 所属项目
    private String type;        // 问题分类
    private String department;  // 对应司局
    private int severity;       // 严重程度
    private String content;     // 问题描述
    private String advice;      // 整改建议
    private String time;        // 时间


    public static int getSeverityNormal() {
        return SEVERITY_NORMAL;
    }

    public static int getSeverityBig() {
        return SEVERITY_BIG;
    }

    public static int getSeverityLarge() {
        return SEVERITY_LARGE;
    }

    public String getProject() {
        return project == null ? "" : project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department == null ? "" : department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdvice() {
        return advice == null ? "" : advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
