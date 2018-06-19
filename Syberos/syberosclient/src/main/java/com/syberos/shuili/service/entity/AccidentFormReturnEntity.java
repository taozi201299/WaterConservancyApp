package com.syberos.shuili.service.entity;

import com.syberos.shuili.entity.HttpBaseOneResponse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/17.
 */

public class AccidentFormReturnEntity extends HttpBaseOneResponse<AccidentFormReturnEntity> {
    String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
