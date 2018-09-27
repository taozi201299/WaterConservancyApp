package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/19.
 * 	安全检查方案分组 类名对应数据库表名
 */

public class BisSinsScheGroup extends HttpBaseResponse<BisSinsScheGroup> {
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


    /**
     * 检查内容
     */
    String scheCont;

    /**
     * 检查结束时间
     */
    String scheCompTime;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getScheGuid() {
        return scheGuid == null ? "" : scheGuid;
    }

    public void setScheGuid(String scheGuid) {
        this.scheGuid = scheGuid;
    }

    public String getGroupName() {
        return groupName == null ? "" : groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLeader() {
        return groupLeader == null ? "" : groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getGroupLeaderWiun() {
        return groupLeaderWiun == null ? "" : groupLeaderWiun;
    }

    public void setGroupLeaderWiun(String groupLeaderWiun) {
        this.groupLeaderWiun = groupLeaderWiun;
    }

    public String getScheModel() {
        return scheModel == null ? "" : scheModel;
    }

    public void setScheModel(String scheModel) {
        this.scheModel = scheModel;
    }

    public String getSchePath() {
        return schePath == null ? "" : schePath;
    }

    public void setSchePath(String schePath) {
        this.schePath = schePath;
    }

    public String getSchePathDetl() {
        return schePathDetl == null ? "" : schePathDetl;
    }

    public void setSchePathDetl(String schePathDetl) {
        this.schePathDetl = schePathDetl;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime == null ? "" : updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers == null ? "" : recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getCurrentPage() {
        return currentPage == null ? "" : currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize == null ? "" : pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getScheName() {
        return scheName == null ? "" : scheName;
    }

    public void setScheName(String scheName) {
        this.scheName = scheName;
    }

    public String getScheStartTime() {
        return scheStartTime == null ? "" : scheStartTime;
    }

    public void setScheStartTime(String scheStartTime) {
        this.scheStartTime = scheStartTime;
    }

    public String getScheCont() {
        return scheCont == null ? "" : scheCont;
    }

    public void setScheCont(String scheCont) {
        this.scheCont = scheCont;
    }

    public String getScheCompTime() {
        return scheCompTime == null ? "" : scheCompTime;
    }

    public void setScheCompTime(String scheCompTime) {
        this.scheCompTime = scheCompTime;
    }
}
