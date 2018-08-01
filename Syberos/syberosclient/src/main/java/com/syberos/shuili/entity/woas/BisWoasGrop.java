package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 * 8.2.2.77	考核组（BIS_WOAS_GROP）
 *
 * 8.2.2.72	工作考核对象（BIS_WOAS_OBJ）
 */

public class BisWoasGrop extends HttpBaseResponse<BisWoasGrop> {
    String guid;
    /**
     * 考核组名称
     */
    String woasGropName;
    /**
     * 工作考核GUID
     */
    String woasGuid;
    /**
     * 考核组长单位
     */
    String leadWiun;
    String note;
    String collTime;
    String updTime;
    String recPers;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWoasGropName() {
        return woasGropName;
    }

    public void setWoasGropName(String woasGropName) {
        this.woasGropName = woasGropName;
    }

    public String getWoasGuid() {
        return woasGuid;
    }

    public void setWoasGuid(String woasGuid) {
        this.woasGuid = woasGuid;
    }

    public String getLeadWiun() {
        return leadWiun;
    }

    public void setLeadWiun(String leadWiun) {
        this.leadWiun = leadWiun;
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
}
