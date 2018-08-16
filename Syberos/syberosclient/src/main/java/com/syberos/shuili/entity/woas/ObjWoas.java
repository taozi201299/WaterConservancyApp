package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/29.
 * 8.2.1.16	工作考核对象表（OBJ_WOAS）
 */

public class ObjWoas extends HttpBaseResponse<ObjWoas> {;
    String ORGGUID;
    String guid;
    String woasStartime;
    String otheFileFiel;
    String woasFileFiel;
    String woasDeadline;
    String woasThem;
    String lareId;
    String orgName;

    public String getORGGUID() {
        return ORGGUID == null ? "" : ORGGUID;
    }

    public void setORGGUID(String ORGGUID) {
        this.ORGGUID = ORGGUID;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getWoasStartime() {
        return woasStartime == null ? "" : woasStartime;
    }

    public void setWoasStartime(String woasStartime) {
        this.woasStartime = woasStartime;
    }

    public String getOtheFileFiel() {
        return otheFileFiel == null ? "" : otheFileFiel;
    }

    public void setOtheFileFiel(String otheFileFiel) {
        this.otheFileFiel = otheFileFiel;
    }

    public String getWoasFileFiel() {
        return woasFileFiel == null ? "" : woasFileFiel;
    }

    public void setWoasFileFiel(String woasFileFiel) {
        this.woasFileFiel = woasFileFiel;
    }

    public String getWoasDeadline() {
        return woasDeadline == null ? "" : woasDeadline;
    }

    public void setWoasDeadline(String woasDeadline) {
        this.woasDeadline = woasDeadline;
    }

    public String getWoasThem() {
        return woasThem == null ? "" : woasThem;
    }

    public void setWoasThem(String woasThem) {
        this.woasThem = woasThem;
    }

    public String getLareId() {
        return lareId == null ? "" : lareId;
    }

    public void setLareId(String lareId) {
        this.lareId = lareId;
    }

    public String getOrgName() {
        return orgName == null ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
