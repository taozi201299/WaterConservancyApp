package com.syberos.shuili.entity;

/**
 * Created by jidan on 18-1-24.
 */

public class BitmapEntity {
    public float x;
    public float y;
    public float width;
    public float height;
    static int devide = 1;
    int index = -1;

    @Override
    public String toString() {
        return "BitmapEntity [x=" + x + ", y=" + y + ", width=" + width
                + ", height=" + height + ", devide=" + devide + ", index="
                + index + "]";
    }
}
