package com.syberos.shuili.entity.map;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */

public class NearbyEngInfoBean implements Serializable{
    @SerializedName("message")
    public String message;
    @SerializedName("pageIndex")
    public String pageIndex;
    @SerializedName("responseSts")
    public String responseSts;
    @SerializedName("resultInfoList")
    public List<EngineInfo> resultInfoList;
    @SerializedName("totalCount")
    public String totalCount;
    @SerializedName("totalPage")
    public String totalPage;

    public class EngineInfo implements Serializable{
        public String geo;
        public String geoType;
        public String guid;
        public String name;
    }

}
