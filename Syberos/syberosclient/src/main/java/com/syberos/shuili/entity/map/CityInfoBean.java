package com.syberos.shuili.entity.map;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/7/12.
 */

public class CityInfoBean implements Serializable {
    @SerializedName("message")
    public String message;
    @SerializedName("pageIndex")
    public String pageIndex;
    @SerializedName("responseSts")
    public int responseSts;
    @SerializedName("result")
    public List<CityInfoBean> result;
    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("totalPage")
    public String totalPage;


    public String guid;
    public String level;
    public String name;
}
