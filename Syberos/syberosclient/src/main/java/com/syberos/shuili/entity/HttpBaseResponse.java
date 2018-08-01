package com.syberos.shuili.entity;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HttpBaseResponse<T> implements Serializable {
    @SerializedName("code")
    public String code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("data")
    public List<T>dataSource;
}
