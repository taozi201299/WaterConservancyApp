package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/20.
 * 检查小组和专家关系表
 */

public class RelSinsGroupExpert extends HttpBaseResponse<RelSinsGroupExpert> {
    String guid;
    /**
     * 检查小组GUID
     */
    String groupGuid;
    /**
     * 专家GUID
     */
    String expeGuid;
    /**
     * 关系建立时间
     */
    String fromDate;
    /**
     * 关系终止时间
     */
    String toDate;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    public String getExpeGuid() {
        return expeGuid;
    }

    public void setExpeGuid(String expeGuid) {
        this.expeGuid = expeGuid;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
