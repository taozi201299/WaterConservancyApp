package com.syberos.shuili.entity.securitycheck;

import java.io.Serializable;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/10
 * @time: 下午7:28
 * @email: ZhaoDongshuang@syberos.com
 */
public class SecurityCheckInformation implements Serializable {

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String id = "";
    private String title = "";
    private String date = "";



}
