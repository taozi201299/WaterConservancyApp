package com.syberos.shuili.entity.thematicchart.standardization;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.standardization.
 */

//标准化证书情况
public class StandardizationCertificateEntry implements Serializable {
    private int normalCount;//正常
    private int revokeCount;//撤销
    private int lateCount;//逾期

    public int getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(int normalCount) {
        this.normalCount = normalCount;
    }

    public int getRevokeCount() {
        return revokeCount;
    }

    public void setRevokeCount(int revokeCount) {
        this.revokeCount = revokeCount;
    }

    public int getLateCount() {
        return lateCount;
    }

    public void setLateCount(int lateCount) {
        this.lateCount = lateCount;
    }
}