package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * Created by Administrator on 2018/4/19.
 * 对应数据库 检查小组与检查对象关系表（REL_SINS_GROUP_WIUN）
 */

public class RelSinsGroupWiun extends HttpHiddenBaseResponse<RelSinsGroupWiun>{
   String guid;
   String engName;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }
}
