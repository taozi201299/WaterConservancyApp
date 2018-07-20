package com.syberos.shuili.entity.thematicchart.waterinspection;

import java.io.Serializable;

/**
 * Created by BZB on 2018/7/17.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.waterinspection.
 */
//稽察问题分类统计占比图
public class WISortEntry implements Serializable{
    int preWork;//前期工作
    int planeWork;//计划方面
    int constructManager;//建设管理
    int financialManger;//财务管理
    int qualityManger;//质量管理

    public int getPreWork() {
        return preWork;
    }

    public void setPreWork(int preWork) {
        this.preWork = preWork;
    }

    public int getPlaneWork() {
        return planeWork;
    }

    public void setPlaneWork(int planeWork) {
        this.planeWork = planeWork;
    }

    public int getConstructManager() {
        return constructManager;
    }

    public void setConstructManager(int constructManager) {
        this.constructManager = constructManager;
    }

    public int getFinancialManger() {
        return financialManger;
    }

    public void setFinancialManger(int financialManger) {
        this.financialManger = financialManger;
    }

    public int getQualityManger() {
        return qualityManger;
    }

    public void setQualityManger(int qualityManger) {
        this.qualityManger = qualityManger;
    }
}