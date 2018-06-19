package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/5/2.
 * 8.1.2.15	水利机构扩展信息表
 */

public class AttOrgExt extends HttpHiddenBaseResponse<AttOrgExt> {
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

    public void setIfMiniDire(String ifMiniDire) {
        IfMiniDire = ifMiniDire;
    }

    public String getIfMiniDire() {
        return IfMiniDire;
    }
}
