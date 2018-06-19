package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/20.
 */

public class OrgInfo extends HttpHiddenBaseResponse<OrgInfo> {
    String guid ;
    String orgName ;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
