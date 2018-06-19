package com.syberos.shuili.entity.bao_biao_guan_li;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;
import com.syberos.shuili.entity.basicbusiness.AttOrgExt;

/**
 * Created by Administrator on 2018/5/3.
 */

public class ReportForAdmin extends HttpHiddenBaseResponse<ReportForAdmin> {
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

    public String getReportStatus() {
        return reportStatus;
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

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getWiunName() {
        return wiunName;
    }

    public void setWiunName(String wiunName) {
        this.wiunName = wiunName;
    }

    public String getOrgClientType() {
        return orgClientType;
    }

    public void setOrgClientType(String orgClientType) {
        this.orgClientType = orgClientType;
    }

    public String getLegRep() {
        return legRep;
    }

    public void setLegRep(String legRep) {
        this.legRep = legRep;
    }

    public String getStanGrad() {
        return stanGrad;
    }

    public void setStanGrad(String stanGrad) {
        this.stanGrad = stanGrad;
    }

    public String getIfMiniDire() {
        return IfMiniDire;
    }

    public void setIfMiniDire(String ifMiniDire) {
        IfMiniDire = ifMiniDire;
    }
}
