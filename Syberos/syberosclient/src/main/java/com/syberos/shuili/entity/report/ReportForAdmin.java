package com.syberos.shuili.entity.report;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/5/3.
 */

public class ReportForAdmin extends HttpBaseResponse<ReportForAdmin> {
    String orgGuid;
    /**
     * 单位名称
     */
    String wiunName;
    /**
     * 单位类型（客户端）
     */
    String orgClientType;
    /**
     * 法定代表人
     */
    String legRep;
    /**
     * 标准化等级
     */
    String stanGrad;
    /**
     * 是否直管单位
     */
    String IfMiniDire;
    String reportStatus;
    boolean reportDone;


    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getWiunName() {
        return wiunName == null ? "" : wiunName;
    }

    public void setWiunName(String wiunName) {
        this.wiunName = wiunName;
    }

    public String getOrgClientType() {
        return orgClientType == null ? "" : orgClientType;
    }

    public void setOrgClientType(String orgClientType) {
        this.orgClientType = orgClientType;
    }

    public String getLegRep() {
        return legRep == null ? "" : legRep;
    }

    public void setLegRep(String legRep) {
        this.legRep = legRep;
    }

    public String getStanGrad() {
        return stanGrad == null ? "" : stanGrad;
    }

    public void setStanGrad(String stanGrad) {
        this.stanGrad = stanGrad;
    }

    public String getIfMiniDire() {
        return IfMiniDire == null ? "" : IfMiniDire;
    }

    public void setIfMiniDire(String ifMiniDire) {
        IfMiniDire = ifMiniDire;
    }

    public String getReportStatus() {
        return reportStatus == null ? "" : reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public boolean isReportDone() {
        return reportDone;
    }

    public void setReportDone(boolean reportDone) {
        this.reportDone = reportDone;
    }
}
