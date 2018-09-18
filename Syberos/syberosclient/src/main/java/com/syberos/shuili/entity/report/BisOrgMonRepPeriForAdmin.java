package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/9/10.
 */

public class BisOrgMonRepPeriForAdmin extends HttpBaseResponse<BisOrgMonRepPeriForAdmin> {
    private String revoVeriResu;
    private String orgGuid;
    private String revoReas;
    private String totalserinjnum;
    private String status;
    private String totaleconloss;
    private int ROWNO;
    private String qJguid;
    private String guid;
    private String wiunName;
    private String orgCode;
    private String revoVeriOpin;
    private String totalacci;
    private String totalcasenum;
    private String returnDesc;

    public String getRevoVeriResu() {
        return revoVeriResu == null ? "" : revoVeriResu;
    }

    public void setRevoVeriResu(String revoVeriResu) {
        this.revoVeriResu = revoVeriResu;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getRevoReas() {
        return revoReas == null ? "" : revoReas;
    }

    public void setRevoReas(String revoReas) {
        this.revoReas = revoReas;
    }

    public String getTotalserinjnum() {
        return totalserinjnum == null ? "" : totalserinjnum;
    }

    public void setTotalserinjnum(String totalserinjnum) {
        this.totalserinjnum = totalserinjnum;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotaleconloss() {
        return totaleconloss == null ? "" : totaleconloss;
    }

    public void setTotaleconloss(String totaleconloss) {
        this.totaleconloss = totaleconloss;
    }

    public int getROWNO() {
        return ROWNO;
    }

    public void setROWNO(int ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getqJguid() {
        return qJguid == null ? "" : qJguid;
    }

    public void setqJguid(String qJguid) {
        this.qJguid = qJguid;
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

    public String getOrgCode() {
        return orgCode == null ? "" : orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getRevoVeriOpin() {
        return revoVeriOpin == null ? "" : revoVeriOpin;
    }

    public void setRevoVeriOpin(String revoVeriOpin) {
        this.revoVeriOpin = revoVeriOpin;
    }

    public String getTotalacci() {
        return totalacci == null ? "" : totalacci;
    }

    public void setTotalacci(String totalacci) {
        this.totalacci = totalacci;
    }

    public String getTotalcasenum() {
        return totalcasenum == null ? "" : totalcasenum;
    }

    public void setTotalcasenum(String totalcasenum) {
        this.totalcasenum = totalcasenum;
    }

    public String getReturnDesc() {
        return returnDesc == null ? "" : returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }
}
