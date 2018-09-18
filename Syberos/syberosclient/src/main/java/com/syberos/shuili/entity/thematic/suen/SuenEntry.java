package com.syberos.shuili.entity.thematic.suen;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/14.
 */

public class SuenEntry implements Serializable {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"everyOrgList":[],"countOrgList":[]}
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

    public static class DataBean implements Serializable{
        private List<EveryOrgBean> everyOrgList;
        private List<CountOrgBean> countOrgList;

        public List<EveryOrgBean> getEveryOrgList() {
            return everyOrgList;
        }

        public void setEveryOrgList(List<EveryOrgBean> everyOrgList) {
            this.everyOrgList = everyOrgList;
        }

        public List<CountOrgBean> getCountOrgList() {
            return countOrgList;
        }

        public void setCountOrgList(List<CountOrgBean> countOrgList) {
            this.countOrgList = countOrgList;
        }
    }
    public static class EveryOrgBean implements Serializable{

        /**
         * COUNT : 1
         * ZGL : 100
         * ORG_NAME : 水利部机关服务中心（水利部机关服务局）
         * JURD : 000000
         * VALUE : 1
         * Y : 39.88457
         * X : 116.352597
         */

        private int COUNT;
        private int ZGL;
        private String ORG_NAME;
        private String JURD;
        private int VALUE;
        private double Y;
        private double X;

        public int getCOUNT() {
            return COUNT;
        }

        public void setCOUNT(int COUNT) {
            this.COUNT = COUNT;
        }

        public int getZGL() {
            return ZGL;
        }

        public void setZGL(int ZGL) {
            this.ZGL = ZGL;
        }

        public String getORG_NAME() {
            return ORG_NAME;
        }

        public void setORG_NAME(String ORG_NAME) {
            this.ORG_NAME = ORG_NAME;
        }

        public String getJURD() {
            return JURD;
        }

        public void setJURD(String JURD) {
            this.JURD = JURD;
        }

        public int getVALUE() {
            return VALUE;
        }

        public void setVALUE(int VALUE) {
            this.VALUE = VALUE;
        }

        public double getY() {
            return Y;
        }

        public void setY(double Y) {
            this.Y = Y;
        }

        public double getX() {
            return X;
        }

        public void setX(double X) {
            this.X = X;
        }
    }
    public static class CountOrgBean implements Serializable{

        /**
         * COUNT : 2
         * VALUE : 1
         */

        private int COUNT;
        private int VALUE;

        public int getCOUNT() {
            return COUNT;
        }

        public void setCOUNT(int COUNT) {
            this.COUNT = COUNT;
        }

        public int getVALUE() {
            return VALUE;
        }

        public void setVALUE(int VALUE) {
            this.VALUE = VALUE;
        }
    }
}
