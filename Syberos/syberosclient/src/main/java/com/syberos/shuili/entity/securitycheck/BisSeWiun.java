package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/27.
 */

public class BisSeWiun extends HttpBaseResponse<BisSeWiun> {
    String guid ;
    String pGuid;
    String seGuid;
    String seCode;
    String seStat;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getpGuid() {
        return pGuid == null ? "" : pGuid;
    }

    public void setpGuid(String pGuid) {
        this.pGuid = pGuid;
    }

    public String getSeGuid() {
        return seGuid == null ? "" : seGuid;
    }

    public void setSeGuid(String seGuid) {
        this.seGuid = seGuid;
    }

    public String getSeCode() {
        return seCode == null ? "" : seCode;
    }

    public void setSeCode(String seCode) {
        this.seCode = seCode;
    }

    public String getSeStat() {
        return seStat == null ? "" : seStat;
    }

    public void setSeStat(String seStat) {
        this.seStat = seStat;
    }
}
