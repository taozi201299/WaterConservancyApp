package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/23.
 * 工程和行政机构关系表
 */

public class RelEngOrg extends HttpBaseResponse<RelEngOrg> {
    String guid;
    String engGuid;
    String orgGuid;
    String fromDate;
    String toDate;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEngGuid() {
        return engGuid == null ? "" : engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getFromDate() {
        return fromDate == null ? "" : fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate == null ? "" : toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
