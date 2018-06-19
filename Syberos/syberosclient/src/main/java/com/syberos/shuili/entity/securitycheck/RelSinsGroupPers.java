package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/26.
 */

public class RelSinsGroupPers extends HttpHiddenBaseResponse<RelSinsGroupPers> {
    String guid;
    String groupGuid;
    String persGuid;
    String fromDate;
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

    public String getPersGuid() {
        return persGuid;
    }

    public void setPersGuid(String persGuid) {
        this.persGuid = persGuid;
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
