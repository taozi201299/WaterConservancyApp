package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/5/2.
 */

public class AttOrgBase extends HttpHiddenBaseResponse<AttOrgBase> {
    String guid;
    String pguid;
    String orgCode;
    String orgName;
    String orgType;
    String orgClientType;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPguid() {
        return pguid;
    }

    public void setPguid(String pguid) {
        this.pguid = pguid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgClientType() {
        return orgClientType;
    }

    public void setOrgClientType(String orgClientType) {
        this.orgClientType = orgClientType;
    }
}
