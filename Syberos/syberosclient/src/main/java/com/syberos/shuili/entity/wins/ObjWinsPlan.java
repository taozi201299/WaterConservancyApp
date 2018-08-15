package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/15.
 * 水利稽查计划对象表
 */



public class ObjWinsPlan extends HttpBaseResponse<ObjWinsPlan>{
    String ORGGUID;  // orgGuid
    String FEEDSTAT; // feedStat
    String DOWNSTAT;  // downStat
    String STAT;;  // stat
    String OWPGUID; // owpGuid
    String WINSARRAYNUM;
    String COLLTIME;
    String ROWNO;
    String WINSPLANNAME;

    public String getORGGUID() {
        return ORGGUID == null ? "" : ORGGUID;
    }

    public void setORGGUID(String ORGGUID) {
        this.ORGGUID = ORGGUID;
    }

    public String getFEEDSTAT() {
        return FEEDSTAT == null ? "" : FEEDSTAT;
    }

    public void setFEEDSTAT(String FEEDSTAT) {
        this.FEEDSTAT = FEEDSTAT;
    }

    public String getDOWNSTAT() {
        return DOWNSTAT == null ? "" : DOWNSTAT;
    }

    public void setDOWNSTAT(String DOWNSTAT) {
        this.DOWNSTAT = DOWNSTAT;
    }

    public String getSTAT() {
        return STAT == null ? "" : STAT;
    }

    public void setSTAT(String STAT) {
        this.STAT = STAT;
    }

    public String getOWPGUID() {
        return OWPGUID == null ? "" : OWPGUID;
    }

    public void setOWPGUID(String OWPGUID) {
        this.OWPGUID = OWPGUID;
    }

    public String getWINSARRAYNUM() {
        return WINSARRAYNUM == null ? "" : WINSARRAYNUM;
    }

    public void setWINSARRAYNUM(String WINSARRAYNUM) {
        this.WINSARRAYNUM = WINSARRAYNUM;
    }

    public String getCOLLTIME() {
        return COLLTIME == null ? "" : COLLTIME;
    }

    public void setCOLLTIME(String COLLTIME) {
        this.COLLTIME = COLLTIME;
    }

    public String getROWNO() {
        return ROWNO == null ? "" : ROWNO;
    }

    public void setROWNO(String ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getWINSPLANNAME() {
        return WINSPLANNAME == null ? "" : WINSPLANNAME;
    }

    public void setWINSPLANNAME(String WINSPLANNAME) {
        this.WINSPLANNAME = WINSPLANNAME;
    }
}
