package com.syberos.shuili.entity.map;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */




public class MapBoundBean implements Serializable {
    @SerializedName("message")
    public String message;
    @SerializedName("pageIndex")
    public String pageIndex;
    @SerializedName("responseSts")
    public int responseSts;
    @SerializedName("result")
    public List<MapBoundBean> result;
    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("totalPage")
    public String totalPage;


    public String bounds;
    public String guid;
    public String name;
    public String centerXY;



}
