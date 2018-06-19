package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/27.
 */

public class BisSeWiun extends HttpHiddenBaseResponse<BisSeWiun> {
    String guid ;
    String pGuid;
    String seGuid;
    String seCode;
    String seStat;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getpGuid() {
        return pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }

    public String getSeGuid() {
        return seGuid;
    }

    public void setSeGuid(String seGuid) {
        this.seGuid = seGuid;
    }

    public String getSeCode() {
        return seCode;
    }

    public void setSeCode(String seCode) {
        this.seCode = seCode;
    }

    public String getSeStat() {
        return seStat;
    }

    public void setSeStat(String seStat) {
        this.seStat = seStat;
    }
}
