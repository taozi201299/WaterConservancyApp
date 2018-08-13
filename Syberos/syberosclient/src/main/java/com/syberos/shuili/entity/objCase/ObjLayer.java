package com.syberos.shuili.entity.objCase;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/23.
 */

public class ObjLayer extends HttpBaseResponse<ObjLayer> {
     String guid;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLareName() {
        return lareName == null ? "" : lareName;
    }

    public void setLareName(String lareName) {
        this.lareName = lareName;
    }

    public String getConSumm() {
        return conSumm == null ? "" : conSumm;
    }

    public void setConSumm(String conSumm) {
        this.conSumm = conSumm;
    }

    String lareName;
     String conSumm;
}
