package com.syberos.shuili.entity.publicentry;

public class LocationEntry {
    public LocationEntry(String lon, String lat, String value) {
        this.lon = lon;
        this.lat = lat;
        this.value = value;
    }

    private String lon;
    private String lat;
    private String value;

    public String getLon() {
        return lon == null ? "" : lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat == null ? "" : lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
