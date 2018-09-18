package com.syberos.shuili.entity.test;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/17.
 */

public class EngineInfoBean implements Serializable {
    /**
     * meta : {}
     * data : {}
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
        /**
         * recordsFiltered : 16
         * data : []
         * recordsTotal : 16
         */

        private int recordsFiltered;
        private int recordsTotal;
        private List<EngineBean> data;

        public int getRecordsFiltered() {
            return recordsFiltered;
        }

        public void setRecordsFiltered(int recordsFiltered) {
            this.recordsFiltered = recordsFiltered;
        }

        public int getRecordsTotal() {
            return recordsTotal;
        }

        public void setRecordsTotal(int recordsTotal) {
            this.recordsTotal = recordsTotal;
        }

        public List<EngineBean> getData() {
            return data;
        }

        public void setData(List<EngineBean> data) {
            this.data = data;
        }
    }
    public static class EngineBean implements Serializable{

        /**
         * AD_CODE : 440183
         * STAT : 1
         * GUID : 1881D5EFC4AE43D98FB7CE4274F7FB00
         * OFLP_CODE : 65EB30A41FE24D8D98F9FC5366E1D7EC
         * IFSPILLWAY : 1
         * IFCHECK : 1
         * RES_TYPE : 土坝
         * TOT_CAP : 900
         * Y : 23.21924351
         * DAM_SIZE_HIG : 0
         * X : 113.6061966
         * RES_NAME : 余家庄水库
         * COUNTRY : 增城市
         * DEAD_LEV : 0
         * ENG_STAT : 2
         * DES_FL_STAG : 34.12
         * RES_PART : 永宁街公安村
         * CHECKOPIN : 哈哈哈,老子是爹
         * START_DATE : null
         * COMP_DATE : null
         * RES_GRAD : 小(1)型
         * DEAD_CAP : 0
         * PROVINCE : 广东省
         * IFSPILLWAYNAME : 疑似无
         * ROW_ID : 1
         * BAAD_CODE : 050000
         * DES_FL_STAND : null
         * CITY : 广州市
         * BAAD_NAME : 珠江水利委员会片区
         */

        private String AD_CODE;
        private String STAT;
        private String GUID;
        private String OFLP_CODE;
        private String IFSPILLWAY;
        private String IFCHECK;
        private String RES_TYPE;
        private String TOT_CAP;
        private String Y;
        private String DAM_SIZE_HIG;
        private String X;
        private String RES_NAME;
        private String COUNTRY;
        private String DEAD_LEV;
        private String ENG_STAT;
        private String DES_FL_STAG;
        private String RES_PART;
        private String CHECKOPIN;
        private String START_DATE;
        private String COMP_DATE;
        private String RES_GRAD;
        private String DEAD_CAP;
        private String PROVINCE;
        private String IFSPILLWAYNAME;
        private String ROW_ID;
        private String BAAD_CODE;
        private String DES_FL_STAND;
        private String CITY;
        private String BAAD_NAME;


        public String getAD_CODE() {
            return AD_CODE == null ? "" : AD_CODE;
        }

        public void setAD_CODE(String AD_CODE) {
            this.AD_CODE = AD_CODE;
        }

        public String getSTAT() {
            return STAT == null ? "" : STAT;
        }

        public void setSTAT(String STAT) {
            this.STAT = STAT;
        }

        public String getGUID() {
            return GUID == null ? "" : GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getOFLP_CODE() {
            return OFLP_CODE == null ? "" : OFLP_CODE;
        }

        public void setOFLP_CODE(String OFLP_CODE) {
            this.OFLP_CODE = OFLP_CODE;
        }

        public String getIFSPILLWAY() {
            return IFSPILLWAY == null ? "" : IFSPILLWAY;
        }

        public void setIFSPILLWAY(String IFSPILLWAY) {
            this.IFSPILLWAY = IFSPILLWAY;
        }

        public String getIFCHECK() {
            return IFCHECK == null ? "" : IFCHECK;
        }

        public void setIFCHECK(String IFCHECK) {
            this.IFCHECK = IFCHECK;
        }

        public String getRES_TYPE() {
            return RES_TYPE == null ? "" : RES_TYPE;
        }

        public void setRES_TYPE(String RES_TYPE) {
            this.RES_TYPE = RES_TYPE;
        }

        public String getTOT_CAP() {
            return TOT_CAP == null ? "" : TOT_CAP;
        }

        public void setTOT_CAP(String TOT_CAP) {
            this.TOT_CAP = TOT_CAP;
        }

        public String getY() {
            return Y == null ? "" : Y;
        }

        public void setY(String y) {
            Y = y;
        }

        public String getDAM_SIZE_HIG() {
            return DAM_SIZE_HIG == null ? "" : DAM_SIZE_HIG;
        }

        public void setDAM_SIZE_HIG(String DAM_SIZE_HIG) {
            this.DAM_SIZE_HIG = DAM_SIZE_HIG;
        }

        public String getX() {
            return X == null ? "" : X;
        }

        public void setX(String x) {
            X = x;
        }

        public String getRES_NAME() {
            return RES_NAME == null ? "" : RES_NAME;
        }

        public void setRES_NAME(String RES_NAME) {
            this.RES_NAME = RES_NAME;
        }

        public String getCOUNTRY() {
            return COUNTRY == null ? "" : COUNTRY;
        }

        public void setCOUNTRY(String COUNTRY) {
            this.COUNTRY = COUNTRY;
        }

        public String getDEAD_LEV() {
            return DEAD_LEV == null ? "" : DEAD_LEV;
        }

        public void setDEAD_LEV(String DEAD_LEV) {
            this.DEAD_LEV = DEAD_LEV;
        }

        public String getENG_STAT() {
            return ENG_STAT == null ? "" : ENG_STAT;
        }

        public void setENG_STAT(String ENG_STAT) {
            this.ENG_STAT = ENG_STAT;
        }

        public String getDES_FL_STAG() {
            return DES_FL_STAG == null ? "" : DES_FL_STAG;
        }

        public void setDES_FL_STAG(String DES_FL_STAG) {
            this.DES_FL_STAG = DES_FL_STAG;
        }

        public String getRES_PART() {
            return RES_PART == null ? "" : RES_PART;
        }

        public void setRES_PART(String RES_PART) {
            this.RES_PART = RES_PART;
        }

        public String getCHECKOPIN() {
            return CHECKOPIN == null ? "" : CHECKOPIN;
        }

        public void setCHECKOPIN(String CHECKOPIN) {
            this.CHECKOPIN = CHECKOPIN;
        }

        public String getSTART_DATE() {
            return START_DATE == null ? "" : START_DATE;
        }

        public void setSTART_DATE(String START_DATE) {
            this.START_DATE = START_DATE;
        }

        public String getCOMP_DATE() {
            return COMP_DATE == null ? "" : COMP_DATE;
        }

        public void setCOMP_DATE(String COMP_DATE) {
            this.COMP_DATE = COMP_DATE;
        }

        public String getRES_GRAD() {
            return RES_GRAD == null ? "" : RES_GRAD;
        }

        public void setRES_GRAD(String RES_GRAD) {
            this.RES_GRAD = RES_GRAD;
        }

        public String getDEAD_CAP() {
            return DEAD_CAP == null ? "" : DEAD_CAP;
        }

        public void setDEAD_CAP(String DEAD_CAP) {
            this.DEAD_CAP = DEAD_CAP;
        }

        public String getPROVINCE() {
            return PROVINCE == null ? "" : PROVINCE;
        }

        public void setPROVINCE(String PROVINCE) {
            this.PROVINCE = PROVINCE;
        }

        public String getIFSPILLWAYNAME() {
            return IFSPILLWAYNAME == null ? "" : IFSPILLWAYNAME;
        }

        public void setIFSPILLWAYNAME(String IFSPILLWAYNAME) {
            this.IFSPILLWAYNAME = IFSPILLWAYNAME;
        }

        public String getROW_ID() {
            return ROW_ID == null ? "" : ROW_ID;
        }

        public void setROW_ID(String ROW_ID) {
            this.ROW_ID = ROW_ID;
        }

        public String getBAAD_CODE() {
            return BAAD_CODE == null ? "" : BAAD_CODE;
        }

        public void setBAAD_CODE(String BAAD_CODE) {
            this.BAAD_CODE = BAAD_CODE;
        }

        public String getDES_FL_STAND() {
            return DES_FL_STAND == null ? "" : DES_FL_STAND;
        }

        public void setDES_FL_STAND(String DES_FL_STAND) {
            this.DES_FL_STAND = DES_FL_STAND;
        }

        public String getCITY() {
            return CITY == null ? "" : CITY;
        }

        public void setCITY(String CITY) {
            this.CITY = CITY;
        }

        public String getBAAD_NAME() {
            return BAAD_NAME == null ? "" : BAAD_NAME;
        }

        public void setBAAD_NAME(String BAAD_NAME) {
            this.BAAD_NAME = BAAD_NAME;
        }
    }
}
