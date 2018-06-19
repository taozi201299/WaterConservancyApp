package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/19.
 * 	安全检查方案分组 类名对应数据库表名
 */

public class BisSinsScheGroup extends HttpHiddenBaseResponse<BisSinsScheGroup> {
    String guid;
    String scheGuid;
    String groupName;
    String groupLeader;
    String groupLeaderWiun;
    String scheModel;
    String schePath;
    String schePathDetl;
    String note;
    String collTime;
    String updTime;
    String recPers;
    String currentPage;
    String pageSize;



    /**
     * 补充字段 方案信息
     */
    /**
     * 检查方案名称
     */
    String scheName;
    /**
     * 检查开始时间
     */
    String scheStartTime;

    public String getScheCont() {
        return scheCont;
    }

    public void setScheCont(String scheCont) {
        this.scheCont = scheCont;
    }

    /**
     * 检查内容
     */
    String scheCont;

    public String getScheName() {
        return scheName;
    }

    public void setScheName(String scheName) {
        this.scheName = scheName;
    }

    public String getScheStartTime() {
        return scheStartTime;
    }

    public void setScheStartTime(String scheStartTime) {
        this.scheStartTime = scheStartTime;
    }

    public String getScheCompTime() {
        return scheCompTime;
    }

    public void setScheCompTime(String scheCompTime) {
        this.scheCompTime = scheCompTime;
    }

    /**
     * 检查结束时间
     */
    String scheCompTime;



    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getScheGuid() {
        return scheGuid;
    }

    public void setScheGuid(String scheGuid) {
        this.scheGuid = scheGuid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getGroupLeaderWiun() {
        return groupLeaderWiun;
    }

    public void setGroupLeaderWiun(String groupLeaderWiun) {
        this.groupLeaderWiun = groupLeaderWiun;
    }

    public String getScheModel() {
        return scheModel;
    }

    public void setScheModel(String scheModel) {
        this.scheModel = scheModel;
    }

    public String getSchePath() {
        return schePath;
    }

    public void setSchePath(String schePath) {
        this.schePath = schePath;
    }

    public String getSchePathDetl() {
        return schePathDetl;
    }

    public void setSchePathDetl(String schePathDetl) {
        this.schePathDetl = schePathDetl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
