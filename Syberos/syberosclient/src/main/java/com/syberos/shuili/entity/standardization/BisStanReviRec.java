package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/24.
 */

public class BisStanReviRec extends HttpBaseResponse<BisStanReviRec> {
    String applGrade;
    String applTime;
    String guid;
    String wiunName;


    public String getApplGrade() {
        return applGrade == null ? "" : applGrade;
    }

    public void setApplGrade(String applGrade) {
        this.applGrade = applGrade;
    }

    public String getApplTime() {
        return applTime == null ? "" : applTime;
    }

    public void setApplTime(String applTime) {
        this.applTime = applTime;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWiunName() {
        return wiunName == null ? "" : wiunName;
    }

    public void setWiunName(String wiunName) {
        this.wiunName = wiunName;
    }
}
