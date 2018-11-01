package com.syberos.shuili.entity.thematic.suen;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/14.
 */

public class SuenEntry implements Serializable {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"everyOrgList":[{"COUNT":131,"ZGL":51.91,"ORG_NAME":"黄河委","JURD":"020000","VALUE":68,"Y":39.891864,"X":116.364891},{"COUNT":29,"ZGL":51.72,"ORG_NAME":"淮河委","JURD":"030000","VALUE":15,"Y":32.920309,"X":117.404226},{"COUNT":8,"ZGL":50,"ORG_NAME":"珠江委","JURD":"050000","VALUE":4,"Y":23.150216,"X":113.339551},{"COUNT":11,"ZGL":45.45,"ORG_NAME":"松辽委","JURD":"060000","VALUE":5,"Y":43.883655,"X":125.304507},{"COUNT":55,"ZGL":43.64,"ORG_NAME":"海河委","JURD":"040000","VALUE":24,"Y":39.116233,"X":117.265547},{"COUNT":5,"ZGL":40,"ORG_NAME":"太湖局","JURD":"070000","VALUE":2,"Y":31.302162,"X":121.49262},{"COUNT":56,"ZGL":37.5,"ORG_NAME":"长江委","JURD":"010000","VALUE":21,"Y":30.602972,"X":114.304916}],"countOrgList":[{"COUNT":295,"VALUE":139}]}
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
        private List<EveryOrgBean> everyOrgList;
        private List<CountOrgListBean> countOrgList;

        public List<EveryOrgBean> getEveryOrgList() {
            return everyOrgList;
        }

        public void setEveryOrgList(List<EveryOrgBean> everyOrgList) {
            this.everyOrgList = everyOrgList;
        }

        public List<CountOrgListBean> getCountOrgList() {
            return countOrgList;
        }

        public void setCountOrgList(List<CountOrgListBean> countOrgList) {
            this.countOrgList = countOrgList;
        }

        public static class EveryOrgBean implements Serializable {
            /**
             * COUNT : 131
             * ZGL : 51.91
             * ORG_NAME : 黄河委
             * JURD : 020000
             * VALUE : 68
             * Y : 39.891864
             * X : 116.364891
             */

            private int COUNT;
            private double ZGL;
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

            public double getZGL() {
                return ZGL;
            }

            public void setZGL(double ZGL) {
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

        public static class CountOrgListBean implements Serializable {
            /**
             * COUNT : 295
             * VALUE : 139
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
}
