package com.syberos.shuili.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/4.
 */

public class HttpBaseOneResponse<T> implements Serializable {
    @SerializedName("code")
    public String code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T dataSource;
}
