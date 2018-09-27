package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/26.
 */

public class RelSinsGroupPers extends HttpBaseResponse<RelSinsGroupPers> {
    String guid;
    String groupGuid;
    String persGuid;
    String fromDate;
    String toDate;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGroupGuid() {
        return groupGuid == null ? "" : groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    public String getPersGuid() {
        return persGuid == null ? "" : persGuid;
    }

    public void setPersGuid(String persGuid) {
        this.persGuid = persGuid;
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
