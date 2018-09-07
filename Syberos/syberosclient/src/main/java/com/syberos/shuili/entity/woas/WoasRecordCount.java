package com.syberos.shuili.entity.woas;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/7.
 */

public class WoasRecordCount implements Serializable{
    /**
     * code : 0
     * msg : 请求正常返回
     * totalCount : 1
     * data : ["2"]
     */

    private int code;
    private String msg;
    private int totalCount;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
