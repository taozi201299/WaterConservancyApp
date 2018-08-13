package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/13.
 */

public class InspectionProject extends HttpBaseResponse<InspectionProject>{
    String PROJNAME;

    public String getPROJNAME() {
        return PROJNAME == null ? "" : PROJNAME;
    }

    public void setPROJNAME(String PROJNAME) {
        this.PROJNAME = PROJNAME;
    }

    public String getPROJGUID() {
        return PROJGUID == null ? "" : PROJGUID;
    }

    public void setPROJGUID(String PROJGUID) {
        this.PROJGUID = PROJGUID;
    }

    String PROJGUID;
}
