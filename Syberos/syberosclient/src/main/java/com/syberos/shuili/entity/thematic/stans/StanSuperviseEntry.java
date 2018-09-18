package com.syberos.shuili.entity.thematic.stans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 * 监管 和 流域对应的实体类
 */

public class StanSuperviseEntry implements Serializable {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"everyOrgList":[],"bookOrgList":[],"countOrgList":[]}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean implements Serializable {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean implements Serializable {
        private List<EveryOrgBean> everyOrgList;  // 省份列表
        private List<BookOrg> bookOrgList;
        private List<CountOrg> countOrgList;

        public List<EveryOrgBean> getEveryOrgList() {
            return everyOrgList;
        }

        public void setEveryOrgList(List<EveryOrgBean> everyOrgList) {
            this.everyOrgList = everyOrgList;
        }

        public List<BookOrg> getBookOrgList() {
            return bookOrgList;
        }

        public void setBookOrgList(List<BookOrg> bookOrgList) {
            this.bookOrgList = bookOrgList;
        }

        public List<CountOrg> getCountOrgList() {
            return countOrgList;
        }

        public void setCountOrgList(List<CountOrg> countOrgList) {
            this.countOrgList = countOrgList;
        }
    }

    public static class EveryOrgBean implements Serializable{

        /**
         * AD_CODE : 510000
         * NAME : 四川省
         * COUNT : 4372
         * SJ : 1062
         * YJ : 1070
         * Y : 30.18147777
         * X : 102.26163186
         * EJ : 2240
         */

        private String AD_CODE;
        private String NAME;
        private String COUNT;
        private String SJ;
        private String YJ;
        private String Y;
        private String X;
        private String EJ;

        public String getAD_CODE() {
            return AD_CODE == null ? "" : AD_CODE;
        }

        public void setAD_CODE(String AD_CODE) {
            this.AD_CODE = AD_CODE;
        }

        public String getNAME() {
            return NAME == null ? "" : NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getCOUNT() {
            return COUNT == null ? "" : COUNT;
        }

        public void setCOUNT(String COUNT) {
            this.COUNT = COUNT;
        }

        public String getSJ() {
            return SJ == null ? "" : SJ;
        }

        public void setSJ(String SJ) {
            this.SJ = SJ;
        }

        public String getYJ() {
            return YJ == null ? "" : YJ;
        }

        public void setYJ(String YJ) {
            this.YJ = YJ;
        }

        public String getY() {
            return Y == null ? "" : Y;
        }

        public void setY(String y) {
            Y = y;
        }

        public String getX() {
            return X == null ? "" : X;
        }

        public void setX(String x) {
            X = x;
        }

        public String getEJ() {
            return EJ == null ? "" : EJ;
        }

        public void setEJ(String EJ) {
            this.EJ = EJ;
        }
    }

    public static class BookOrg implements Serializable{

        /**
         * CX : 15989
         * CQ : 15915
         * ZC : 32011
         */

        private String CX;
        private String CQ;
        private String ZC;

        public String getCX() {
            return CX == null ? "" : CX;
        }

        public void setCX(String CX) {
            this.CX = CX;
        }

        public String getCQ() {
            return CQ == null ? "" : CQ;
        }

        public void setCQ(String CQ) {
            this.CQ = CQ;
        }

        public String getZC() {
            return ZC == null ? "" : ZC;
        }

        public void setZC(String ZC) {
            this.ZC = ZC;
        }
    }
    public static class CountOrg implements Serializable{

        /**
         * SJ : 15722
         * ER : 31594
         * YJ : 15996
         */

        private String SJ;
        private String ER;
        private String YJ;

        public String getSJ() {
            return SJ == null ? "" : SJ;
        }

        public void setSJ(String SJ) {
            this.SJ = SJ;
        }

        public String getER() {
            return ER == null ? "" : ER;
        }

        public void setER(String ER) {
            this.ER = ER;
        }

        public String getYJ() {
            return YJ == null ? "" : YJ;
        }

        public void setYJ(String YJ) {
            this.YJ = YJ;
        }
    }
}
