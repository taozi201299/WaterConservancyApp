package com.syberos.shuili.entity.map;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/28.
 */

public class MapPointBean implements Serializable {

    String lat;
    String lon;
    String radius;

    public String getLat() {
        return lat == null ? "" : lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon == null ? "" : lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getRadius() {
        return radius == null ? "" : radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
