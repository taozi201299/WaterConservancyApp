package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 * 8.2.2.77	考核组（BIS_WOAS_GROP）
 *
 * 8.2.2.72	工作考核对象（BIS_WOAS_OBJ）
 * 需要返回考核方案GUID
 */
public class BisWoasGrop extends HttpBaseResponse<BisWoasGrop> {
    String guid;;
    String woasGuid;;
    String leadWiun;
    String woasThem;
    String woasGropName;
    String lareId;
    String startTime;
    String endTime;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWoasGuid() {
        return woasGuid == null ? "" : woasGuid;
    }

    public void setWoasGuid(String woasGuid) {
        this.woasGuid = woasGuid;
    }

    public String getLeadWiun() {
        return leadWiun == null ? "" : leadWiun;
    }

    public void setLeadWiun(String leadWiun) {
        this.leadWiun = leadWiun;
    }

    public String getWoasThem() {
        return woasThem == null ? "" : woasThem;
    }

    public void setWoasThem(String woasThem) {
        this.woasThem = woasThem;
    }

    public String getWoasGropName() {
        return woasGropName == null ? "" : woasGropName;
    }

    public void setWoasGropName(String woasGropName) {
        this.woasGropName = woasGropName;
    }

    public String getLareId() {
        return lareId == null ? "" : lareId;
    }

    public void setLareId(String lareId) {
        this.lareId = lareId;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
