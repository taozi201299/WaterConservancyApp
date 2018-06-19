package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/10
 * @time: 下午7:28
 * @email: ZhaoDongshuang@syberos.com
 */
public class SecurityCheckInformation implements Serializable {

    private String id = "";
    private String title = "";
    private String date = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
