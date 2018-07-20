package com.example.testmodule.thematicchart;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/16.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.hidden.
 */
public class PointEntry implements Serializable {
    String pointId;//地图点的 id
    String pointLongitude; //点的 jinngdu 坐标
    String pointLatitude;//点的 纬度坐标
    String pointValue; //点的 值

    public String getPointId() {
        return pointId == null ? "" : pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointLongitude() {
        return pointLongitude == null ? "" : pointLongitude;
    }

    public void setPointLongitude(String pointLongitude) {
        this.pointLongitude = pointLongitude;
    }

    public String getPointLatitude() {
        return pointLatitude == null ? "" : pointLatitude;
    }

    public void setPointLatitude(String pointLatitude) {
        this.pointLatitude = pointLatitude;
    }

    public String getPointValue() {
        return pointValue == null ? "" : pointValue;
    }

    public void setPointValue(String pointValue) {
        this.pointValue = pointValue;
    }

}
