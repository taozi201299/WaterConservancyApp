package com.syberos.shuili.entity.woas;

/**
 * Created by Administrator on 2018/8/22.
 */


import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * 考核方案对象
 */
public class BisWoasProg extends HttpBaseResponse<BisWoasProg> {
    String guid;
    String progName;
    String endTime;
    String lareId;
    String startTime;
    String woasThem;
    String woasGuid;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProgName() {
        return progName == null ? "" : progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getWoasThem() {
        return woasThem == null ? "" : woasThem;
    }

    public void setWoasThem(String woasThem) {
        this.woasThem = woasThem;
    }

    public String getWoasGuid() {
        return woasGuid == null ? "" : woasGuid;
    }

    public void setWoasGuid(String woasGuid) {
        this.woasGuid = woasGuid;
    }
}
