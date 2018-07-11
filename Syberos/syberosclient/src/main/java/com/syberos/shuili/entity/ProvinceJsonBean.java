package com.syberos.shuili.entity;


import com.contrarywind.interfaces.IPickerViewData;

/**
 * TODO<json数据源>
 */

public class ProvinceJsonBean implements IPickerViewData {


    /**
     * UP_AD_GUID : 06FB77D973114934A947567C579AC308
     * AD_CODE : 511500
     * GUID : 09086FE7AB6E4DA3BA5BC583ACD757AF
     * PATHNAME : 四川省
     * LEVELS : 2
     * PATH : 510000
     * AD_NAME : 宜宾市
     * AD_GRAD : 3
     */
//    UP_AD_GUID 上级 guid
//    AD_CODE 行政编码
//    AD_NAME 行政名称
//    AD_GRAD  行政级别
//    GUID 本级的guid
//    PATHNAME 省名称
//    LEVELS  行政级别（以省为1级）
//    PATH 最高省级的节点

    private String UP_AD_GUID;
    private String AD_CODE;
    private String GUID;
    private String PATHNAME;
    private int LEVELS;
    private String PATH;
    private String AD_NAME;
    private String AD_GRAD;

    @Override
    public String getPickerViewText() {
        return AD_NAME;
    }

    public String getUP_AD_GUID() {
        return UP_AD_GUID;
    }

    public void setUP_AD_GUID(String UP_AD_GUID) {
        this.UP_AD_GUID = UP_AD_GUID;
    }

    public String getAD_CODE() {
        return AD_CODE;
    }

    public void setAD_CODE(String AD_CODE) {
        this.AD_CODE = AD_CODE;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getPATHNAME() {
        return PATHNAME;
    }

    public void setPATHNAME(String PATHNAME) {
        this.PATHNAME = PATHNAME;
    }

    public int getLEVELS() {
        return LEVELS;
    }

    public void setLEVELS(int LEVELS) {
        this.LEVELS = LEVELS;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public String getAD_NAME() {
        return AD_NAME;
    }

    public void setAD_NAME(String AD_NAME) {
        this.AD_NAME = AD_NAME;
    }

    public String getAD_GRAD() {
        return AD_GRAD;
    }

    public void setAD_GRAD(String AD_GRAD) {
        this.AD_GRAD = AD_GRAD;
    }

    @Override
    public String toString() {
        return "ProvinceJsonBean{" + "UP_AD_GUID='" + UP_AD_GUID + '\'' + ", AD_CODE='" + AD_CODE + '\'' + ", GUID='" + GUID + '\'' + ", PATHNAME='" + PATHNAME + '\'' + ", LEVELS=" + LEVELS + ", PATH='" + PATH + '\'' + ", AD_NAME='" + AD_NAME + '\'' + ", AD_GRAD='" + AD_GRAD + '\'' + '}';
    }

}
