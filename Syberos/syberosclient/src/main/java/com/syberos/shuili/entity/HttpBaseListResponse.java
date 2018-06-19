package com.syberos.shuili.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class HttpBaseListResponse<T> implements Serializable {
    @SerializedName("code")
    public String code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public DataSourceBean<T> dataSource;

    public static class DataSourceBean<D> implements Serializable {
        public String hasMore;
        public List<D>list;
    }
}
