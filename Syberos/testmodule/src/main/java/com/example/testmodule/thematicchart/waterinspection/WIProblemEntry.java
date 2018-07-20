package com.example.testmodule.thematicchart.waterinspection;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspection.
 */

public class WIProblemEntry implements Serializable{
    int normal;
    int major;
    int veryMajor;

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getVeryMajor() {
        return veryMajor;
    }

    public void setVeryMajor(int veryMajor) {
        this.veryMajor = veryMajor;
    }
}