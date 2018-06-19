package com.syberos.shuili.entity.standardization;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/24.
 */

public class BisStanReviRec extends HttpHiddenBaseResponse<BisStanReviRec> {
    String applGrade;
    String applTime;
    String guid;
    String wiunName;

    public String getApplGrade() {
        return applGrade;
    }

    public void setApplGrade(String applGrade) {
        this.applGrade = applGrade;
    }

    public String getApplTime() {
        return applTime;
    }

    public void setApplTime(String applTime) {
        this.applTime = applTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWiunName() {
        return wiunName;
    }

    public void setWiunName(String wiunName) {
        this.wiunName = wiunName;
    }
}
