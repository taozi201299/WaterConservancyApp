package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * Created by jidan on 18-3-19.
 */

public class AppUpdateEntity  extends BaseResponse<AppUpdateEntity> {
    String appinfo;

    public void setAppinfo(String appinfo) {
        this.appinfo = appinfo;
    }

    public String getAppinfo() {
        return appinfo;
    }
}
