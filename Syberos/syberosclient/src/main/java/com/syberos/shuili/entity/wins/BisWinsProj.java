package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/13.
 */

public class BisWinsProj extends HttpBaseResponse<BisWinsProj>{

    public String getProjGuid() {
        return projGuid == null ? "" : projGuid;
    }

    public void setProjGuid(String projGuid) {
        this.projGuid = projGuid;
    }

    public String getProjName() {
        return projName == null ? "" : projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    private String projGuid;
    private String projName;
}
