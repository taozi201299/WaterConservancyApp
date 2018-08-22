package com.syberos.shuili.entity.woas;

/**
 * Created by Administrator on 2018/8/22.
 */


import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * 考核方案对象
 */
public class BisWoasProg extends HttpBaseResponse<BisWoasProg> {
    String GUID;
    String PROGNAME;
    String WOASDEADLINE;
    String LAREID;
    String WOASSTARTIME;
    String WOASTHEM;
    String WOASGUID;

    public String getGUID() {
        return GUID == null ? "" : GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getPROGNAME() {
        return PROGNAME == null ? "" : PROGNAME;
    }

    public void setPROGNAME(String PROGNAME) {
        this.PROGNAME = PROGNAME;
    }

    public String getWOASDEADLINE() {
        return WOASDEADLINE == null ? "" : WOASDEADLINE;
    }

    public void setWOASDEADLINE(String WOASDEADLINE) {
        this.WOASDEADLINE = WOASDEADLINE;
    }

    public String getLAREID() {
        return LAREID == null ? "" : LAREID;
    }

    public void setLAREID(String LAREID) {
        this.LAREID = LAREID;
    }

    public String getWOASSTARTIME() {
        return WOASSTARTIME == null ? "" : WOASSTARTIME;
    }

    public void setWOASSTARTIME(String WOASSTARTIME) {
        this.WOASSTARTIME = WOASSTARTIME;
    }

    public String getWOASTHEM() {
        return WOASTHEM == null ? "" : WOASTHEM;
    }

    public void setWOASTHEM(String WOASTHEM) {
        this.WOASTHEM = WOASTHEM;
    }

    public String getWOASGUID() {
        return WOASGUID == null ? "" : WOASGUID;
    }

    public void setWOASGUID(String WOASGUID) {
        this.WOASGUID = WOASGUID;
    }
}
