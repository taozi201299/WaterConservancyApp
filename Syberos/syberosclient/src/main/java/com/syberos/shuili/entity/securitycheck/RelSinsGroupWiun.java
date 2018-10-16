package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/19.
 * 对应数据库 检查小组与检查对象关系表（REL_SINS_GROUP_WIUN）
 */

public class RelSinsGroupWiun extends HttpBaseResponse<RelSinsGroupWiun> {
    private String guid;
    private String groupGuid;
    private String objGuid;
    private String fromDate;
    private String toDate;
    private String stat;
    private String sinsType;

    public String getObjName() {
        return objName == null ? "" : objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    private String objName;

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

    public String getObjGuid() {
        return objGuid == null ? "" : objGuid;
    }

    public void setObjGuid(String objGuid) {
        this.objGuid = objGuid;
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

    public String getStat() {
        return stat == null ? "" : stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getSinsType() {
        return sinsType == null ? "" : sinsType;
    }

    public void setSinsType(String sinsType) {
        this.sinsType = sinsType;
    }
}
