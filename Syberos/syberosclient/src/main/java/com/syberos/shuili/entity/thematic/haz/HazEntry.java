package com.syberos.shuili.entity.thematic.haz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */

public class HazEntry implements Serializable {

    /**
     * meta : {}
     * data : {"countEngList":[],"everyEngList":[]}
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

    public static class MetaBean implements Serializable{
    }

    public static class DataBean implements Serializable {
        private List<CountBean> countEngList;
        private List<EveryEngBean> everyEngList;

        public List<CountBean> getCountEngList() {
            return countEngList;
        }

        public void setCountEngList(List<CountBean> countEngList) {
            this.countEngList = countEngList;
        }

        public List<EveryEngBean> getEveryEngList() {
            return everyEngList;
        }

        public void setEveryEngList(List<EveryEngBean> everyEngList) {
            this.everyEngList = everyEngList;
        }
    }

    public class CountBean implements Serializable{

        private int GENERALHAVECONTROL;
        private String GENERALCONTROLRATE;
        private int WGK;
        private int GENERALCONTROLCOUNT;
        private int YGK;
        private int GENERALREGCOUNT;
        private int MAJORHAVEREG;
        private int MAJORHAVECONTROL;
        private int GENERALNOTCONTROL;
        private String MAJORCONTROLRATE;
        private int GENERALHAVEREG;
        private int GENERALNOTREG;
        private int MAJORNOTCONTROL;
        private int MAJORCONTROLCOUNT;
        private String MAJORREGRATE;
        private String GENERALREGRATE;
        private int MAJORREGCOUNT;


        public int getGENERALHAVECONTROL() {
            return GENERALHAVECONTROL;
        }

        public void setGENERALHAVECONTROL(int GENERALHAVECONTROL) {
            this.GENERALHAVECONTROL = GENERALHAVECONTROL;
        }

        public String getGENERALCONTROLRATE() {
            return GENERALCONTROLRATE == null ? "" : GENERALCONTROLRATE;
        }

        public void setGENERALCONTROLRATE(String GENERALCONTROLRATE) {
            this.GENERALCONTROLRATE = GENERALCONTROLRATE;
        }

        public int getWGK() {
            return WGK;
        }

        public void setWGK(int WGK) {
            this.WGK = WGK;
        }

        public int getGENERALCONTROLCOUNT() {
            return GENERALCONTROLCOUNT;
        }

        public void setGENERALCONTROLCOUNT(int GENERALCONTROLCOUNT) {
            this.GENERALCONTROLCOUNT = GENERALCONTROLCOUNT;
        }

        public int getYGK() {
            return YGK;
        }

        public void setYGK(int YGK) {
            this.YGK = YGK;
        }

        public int getGENERALREGCOUNT() {
            return GENERALREGCOUNT;
        }

        public void setGENERALREGCOUNT(int GENERALREGCOUNT) {
            this.GENERALREGCOUNT = GENERALREGCOUNT;
        }

        public int getMAJORHAVEREG() {
            return MAJORHAVEREG;
        }

        public void setMAJORHAVEREG(int MAJORHAVEREG) {
            this.MAJORHAVEREG = MAJORHAVEREG;
        }

        public int getMAJORHAVECONTROL() {
            return MAJORHAVECONTROL;
        }

        public void setMAJORHAVECONTROL(int MAJORHAVECONTROL) {
            this.MAJORHAVECONTROL = MAJORHAVECONTROL;
        }

        public int getGENERALNOTCONTROL() {
            return GENERALNOTCONTROL;
        }

        public void setGENERALNOTCONTROL(int GENERALNOTCONTROL) {
            this.GENERALNOTCONTROL = GENERALNOTCONTROL;
        }

        public String getMAJORCONTROLRATE() {
            return MAJORCONTROLRATE == null ? "" : MAJORCONTROLRATE;
        }

        public void setMAJORCONTROLRATE(String MAJORCONTROLRATE) {
            this.MAJORCONTROLRATE = MAJORCONTROLRATE;
        }

        public int getGENERALHAVEREG() {
            return GENERALHAVEREG;
        }

        public void setGENERALHAVEREG(int GENERALHAVEREG) {
            this.GENERALHAVEREG = GENERALHAVEREG;
        }

        public int getGENERALNOTREG() {
            return GENERALNOTREG;
        }

        public void setGENERALNOTREG(int GENERALNOTREG) {
            this.GENERALNOTREG = GENERALNOTREG;
        }

        public int getMAJORNOTCONTROL() {
            return MAJORNOTCONTROL;
        }

        public void setMAJORNOTCONTROL(int MAJORNOTCONTROL) {
            this.MAJORNOTCONTROL = MAJORNOTCONTROL;
        }

        public int getMAJORCONTROLCOUNT() {
            return MAJORCONTROLCOUNT;
        }

        public void setMAJORCONTROLCOUNT(int MAJORCONTROLCOUNT) {
            this.MAJORCONTROLCOUNT = MAJORCONTROLCOUNT;
        }

        public String getMAJORREGRATE() {
            return MAJORREGRATE == null ? "" : MAJORREGRATE;
        }

        public void setMAJORREGRATE(String MAJORREGRATE) {
            this.MAJORREGRATE = MAJORREGRATE;
        }

        public String getGENERALREGRATE() {
            return GENERALREGRATE == null ? "" : GENERALREGRATE;
        }

        public void setGENERALREGRATE(String GENERALREGRATE) {
            this.GENERALREGRATE = GENERALREGRATE;
        }

        public int getMAJORREGCOUNT() {
            return MAJORREGCOUNT;
        }

        public void setMAJORREGCOUNT(int MAJORREGCOUNT) {
            this.MAJORREGCOUNT = MAJORREGCOUNT;
        }
    }
    public class EveryEngBean implements Serializable{

        /**
         * GENERALHAVECONTROL : 121
         * GENERALCONTROLRATE : 33.7%
         * ORGCODE : 610000
         * WGK : 316
         * GENERALCONTROLCOUNT : 359
         * YGK : 433
         * MAJORNOTREG : 17
         * GENERALREGCOUNT : 359
         * MAJORHAVECONTROL : 312
         * MAJORHAVEREG : 373
         * GENERALNOTCONTROL : 238
         * MAJORCONTROLRATE : 80%
         * GENERALHAVEREG : 0
         * AD_LAT : 35.64603571
         * GENERALNOTREG : 359
         * MAJORNOTCONTROL : 78
         * MAJORCONTROLCOUNT : 390
         * MAJORREGRATE : 95.64%
         * GENERALREGRATE : 0%
         * MAJORREGCOUNT : 390
         * X : 109.56550263
         * ORGNAME : 陕西省
         */

        private int GENERALHAVECONTROL;
        private String GENERALCONTROLRATE;
        private String ORGCODE;
        private int WGK;
        private int GENERALCONTROLCOUNT;
        private int YGK;
        private int MAJORNOTREG;
        private int GENERALREGCOUNT;
        private int MAJORHAVECONTROL;
        private int MAJORHAVEREG;
        private int GENERALNOTCONTROL;
        private String MAJORCONTROLRATE;
        private int GENERALHAVEREG;
        private double Y;
        private int GENERALNOTREG;
        private int MAJORNOTCONTROL;
        private int MAJORCONTROLCOUNT;
        private String MAJORREGRATE;
        private String GENERALREGRATE;
        private int MAJORREGCOUNT;
        private double X;
        private String ORGNAME;

        public int getGENERALHAVECONTROL() {
            return GENERALHAVECONTROL;
        }

        public void setGENERALHAVECONTROL(int GENERALHAVECONTROL) {
            this.GENERALHAVECONTROL = GENERALHAVECONTROL;
        }

        public String getGENERALCONTROLRATE() {
            return GENERALCONTROLRATE == null ? "" : GENERALCONTROLRATE;
        }

        public void setGENERALCONTROLRATE(String GENERALCONTROLRATE) {
            this.GENERALCONTROLRATE = GENERALCONTROLRATE;
        }

        public String getORGCODE() {
            return ORGCODE == null ? "" : ORGCODE;
        }

        public void setORGCODE(String ORGCODE) {
            this.ORGCODE = ORGCODE;
        }

        public int getWGK() {
            return WGK;
        }

        public void setWGK(int WGK) {
            this.WGK = WGK;
        }

        public int getGENERALCONTROLCOUNT() {
            return GENERALCONTROLCOUNT;
        }

        public void setGENERALCONTROLCOUNT(int GENERALCONTROLCOUNT) {
            this.GENERALCONTROLCOUNT = GENERALCONTROLCOUNT;
        }

        public int getYGK() {
            return YGK;
        }

        public void setYGK(int YGK) {
            this.YGK = YGK;
        }

        public int getMAJORNOTREG() {
            return MAJORNOTREG;
        }

        public void setMAJORNOTREG(int MAJORNOTREG) {
            this.MAJORNOTREG = MAJORNOTREG;
        }

        public int getGENERALREGCOUNT() {
            return GENERALREGCOUNT;
        }

        public void setGENERALREGCOUNT(int GENERALREGCOUNT) {
            this.GENERALREGCOUNT = GENERALREGCOUNT;
        }

        public int getMAJORHAVECONTROL() {
            return MAJORHAVECONTROL;
        }

        public void setMAJORHAVECONTROL(int MAJORHAVECONTROL) {
            this.MAJORHAVECONTROL = MAJORHAVECONTROL;
        }

        public int getMAJORHAVEREG() {
            return MAJORHAVEREG;
        }

        public void setMAJORHAVEREG(int MAJORHAVEREG) {
            this.MAJORHAVEREG = MAJORHAVEREG;
        }

        public int getGENERALNOTCONTROL() {
            return GENERALNOTCONTROL;
        }

        public void setGENERALNOTCONTROL(int GENERALNOTCONTROL) {
            this.GENERALNOTCONTROL = GENERALNOTCONTROL;
        }

        public String getMAJORCONTROLRATE() {
            return MAJORCONTROLRATE == null ? "" : MAJORCONTROLRATE;
        }

        public void setMAJORCONTROLRATE(String MAJORCONTROLRATE) {
            this.MAJORCONTROLRATE = MAJORCONTROLRATE;
        }

        public int getGENERALHAVEREG() {
            return GENERALHAVEREG;
        }

        public void setGENERALHAVEREG(int GENERALHAVEREG) {
            this.GENERALHAVEREG = GENERALHAVEREG;
        }

        public void setY(double y) {
            Y = y;
        }

        public double getY() {
            return Y;
        }

        public int getGENERALNOTREG() {
            return GENERALNOTREG;
        }

        public void setGENERALNOTREG(int GENERALNOTREG) {
            this.GENERALNOTREG = GENERALNOTREG;
        }

        public int getMAJORNOTCONTROL() {
            return MAJORNOTCONTROL;
        }

        public void setMAJORNOTCONTROL(int MAJORNOTCONTROL) {
            this.MAJORNOTCONTROL = MAJORNOTCONTROL;
        }

        public int getMAJORCONTROLCOUNT() {
            return MAJORCONTROLCOUNT;
        }

        public void setMAJORCONTROLCOUNT(int MAJORCONTROLCOUNT) {
            this.MAJORCONTROLCOUNT = MAJORCONTROLCOUNT;
        }

        public String getMAJORREGRATE() {
            return MAJORREGRATE == null ? "" : MAJORREGRATE;
        }

        public void setMAJORREGRATE(String MAJORREGRATE) {
            this.MAJORREGRATE = MAJORREGRATE;
        }

        public String getGENERALREGRATE() {
            return GENERALREGRATE == null ? "" : GENERALREGRATE;
        }

        public void setGENERALREGRATE(String GENERALREGRATE) {
            this.GENERALREGRATE = GENERALREGRATE;
        }

        public int getMAJORREGCOUNT() {
            return MAJORREGCOUNT;
        }

        public void setMAJORREGCOUNT(int MAJORREGCOUNT) {
            this.MAJORREGCOUNT = MAJORREGCOUNT;
        }

        public double getX() {
            return X;
        }

        public void setX(double x) {
            X = x;
        }

        public String getORGNAME() {
            return ORGNAME == null ? "" : ORGNAME;
        }

        public void setORGNAME(String ORGNAME) {
            this.ORGNAME = ORGNAME;
        }
    }

}
