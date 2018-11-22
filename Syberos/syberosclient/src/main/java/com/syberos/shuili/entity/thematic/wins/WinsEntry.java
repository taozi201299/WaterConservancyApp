package com.syberos.shuili.entity.thematic.wins;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/30.
 * 水利稽察
 */

public class WinsEntry implements Serializable {
    private  DataBean data;
    private MetaBean meta;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public static class MetaBean implements Serializable {
        /**
         * message : ok
         * success : true
         */

        private String message;
        private boolean success;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

    public static class DataBean implements Serializable {
       private ArrayList<WinsProbCateDataBean> WINSPROBCATEDATA;
       private String WINSPROBQUA;
       private String WINSQUA;
       private String WINSPROBRECTQUA;
       private String WINSPROJQUA;
       private ArrayList<WinsProbTypeDataBean> WINSPROBTYPEDATA;
       private ArrayList<SubWinsDataBean> SUBWINSDATA;

        public ArrayList<WinsProbCateDataBean> getWINSPROBCATEDATA() {
            if (WINSPROBCATEDATA == null) {
                return new ArrayList<>();
            }
            return WINSPROBCATEDATA;
        }

        public void setWINSPROBCATEDATA(ArrayList<WinsProbCateDataBean> WINSPROBCATEDATA) {
            this.WINSPROBCATEDATA = WINSPROBCATEDATA;
        }

        public String getWINSPROBQUA() {
            return WINSPROBQUA == null ? "" : WINSPROBQUA;
        }

        public void setWINSPROBQUA(String WINSPROBQUA) {
            this.WINSPROBQUA = WINSPROBQUA;
        }

        public String getWINSQUA() {
            return WINSQUA == null ? "" : WINSQUA;
        }

        public void setWINSQUA(String WINSQUA) {
            this.WINSQUA = WINSQUA;
        }

        public String getWINSPROBRECTQUA() {
            return WINSPROBRECTQUA == null ? "" : WINSPROBRECTQUA;
        }

        public void setWINSPROBRECTQUA(String WINSPROBRECTQUA) {
            this.WINSPROBRECTQUA = WINSPROBRECTQUA;
        }

        public String getWINSPROJQUA() {
            return WINSPROJQUA == null ? "" : WINSPROJQUA;
        }

        public void setWINSPROJQUA(String WINSPROJQUA) {
            this.WINSPROJQUA = WINSPROJQUA;
        }

        public ArrayList<WinsProbTypeDataBean> getWINSPROBTYPEDATA() {
            if (WINSPROBTYPEDATA == null) {
                return new ArrayList<>();
            }
            return WINSPROBTYPEDATA;
        }

        public void setWINSPROBTYPEDATA(ArrayList<WinsProbTypeDataBean> WINSPROBTYPEDATA) {
            this.WINSPROBTYPEDATA = WINSPROBTYPEDATA;
        }

        public ArrayList<SubWinsDataBean> getSUBWINSDATA() {
            if (SUBWINSDATA == null) {
                return new ArrayList<>();
            }
            return SUBWINSDATA;
        }

        public void setSUBWINSDATA(ArrayList<SubWinsDataBean> SUBWINSDATA) {
            this.SUBWINSDATA = SUBWINSDATA;
        }
    }
    public static class WinsProbCateDataBean implements Serializable{
        private String WINSPROBQUA;
        private String PROBCLASS;
        private String CLASSTYPE;
        private String PROBCLASSNAME;

        public String getWINSPROBQUA() {
            return WINSPROBQUA == null ? "" : WINSPROBQUA;
        }

        public void setWINSPROBQUA(String WINSPROBQUA) {
            this.WINSPROBQUA = WINSPROBQUA;
        }

        public String getPROBCLASS() {
            return PROBCLASS == null ? "" : PROBCLASS;
        }

        public void setPROBCLASS(String PROBCLASS) {
            this.PROBCLASS = PROBCLASS;
        }

        public String getCLASSTYPE() {
            return CLASSTYPE == null ? "" : CLASSTYPE;
        }

        public void setCLASSTYPE(String CLASSTYPE) {
            this.CLASSTYPE = CLASSTYPE;
        }

        public String getPROBCLASSNAME() {
            return PROBCLASSNAME == null ? "" : PROBCLASSNAME;
        }

        public void setPROBCLASSNAME(String PROBCLASSNAME) {
            this.PROBCLASSNAME = PROBCLASSNAME;
        }
    }
    public static class WinsProbTypeDataBean implements  Serializable{
        private String WINSPROBQUA;
        private String PROBCLASS;
        private String CLASSTYPE;
        private String PROBCLASSNAME;

        public String getWINSPROBQUA() {
            return WINSPROBQUA == null ? "" : WINSPROBQUA;
        }

        public void setWINSPROBQUA(String WINSPROBQUA) {
            this.WINSPROBQUA = WINSPROBQUA;
        }

        public String getPROBCLASS() {
            return PROBCLASS == null ? "" : PROBCLASS;
        }

        public void setPROBCLASS(String PROBCLASS) {
            this.PROBCLASS = PROBCLASS;
        }

        public String getCLASSTYPE() {
            return CLASSTYPE == null ? "" : CLASSTYPE;
        }

        public void setCLASSTYPE(String CLASSTYPE) {
            this.CLASSTYPE = CLASSTYPE;
        }

        public String getPROBCLASSNAME() {
            return PROBCLASSNAME == null ? "" : PROBCLASSNAME;
        }

        public void setPROBCLASSNAME(String PROBCLASSNAME) {
            this.PROBCLASSNAME = PROBCLASSNAME;
        }
    }
    public static class  SubWinsDataBean implements Serializable{
        private String WINSQUA;;
        private String WINSPROBQUA;
        private String OBJNAME;
        private String WINSPROJQUA;
        private String WINSPROBRECTQUA;
        private String OBJGUID;
        private String OBJLAT;
        private String OBJLONG;

        public String getWINSQUA() {
            return WINSQUA == null ? "" : WINSQUA;
        }

        public void setWINSQUA(String WINSQUA) {
            this.WINSQUA = WINSQUA;
        }

        public String getWINSPROBQUA() {
            return WINSPROBQUA == null ? "" : WINSPROBQUA;
        }

        public void setWINSPROBQUA(String WINSPROBQUA) {
            this.WINSPROBQUA = WINSPROBQUA;
        }

        public String getWINSPROJQUA() {
            return WINSPROJQUA == null ? "" : WINSPROJQUA;
        }

        public void setWINSPROJQUA(String WINSPROJQUA) {
            this.WINSPROJQUA = WINSPROJQUA;
        }

        public String getWINSPROBRECTQUA() {
            return WINSPROBRECTQUA == null ? "" : WINSPROBRECTQUA;
        }

        public void setWINSPROBRECTQUA(String WINSPROBRECTQUA) {
            this.WINSPROBRECTQUA = WINSPROBRECTQUA;
        }

        public String getOBJNAME() {
            return OBJNAME == null ? "" : OBJNAME;
        }

        public void setOBJNAME(String OBJNAME) {
            this.OBJNAME = OBJNAME;
        }

        public String getOBJGUID() {
            return OBJGUID == null ? "" : OBJGUID;
        }

        public void setOBJGUID(String OBJGUID) {
            this.OBJGUID = OBJGUID;
        }

        public String getOBJLAT() {
            return OBJLAT == null ? "" : OBJLAT;
        }

        public void setOBJLAT(String OBJLAT) {
            this.OBJLAT = OBJLAT;
        }

        public String getOBJLONG() {
            return OBJLONG == null ? "" : OBJLONG;
        }

        public void setOBJLONG(String OBJLONG) {
            this.OBJLONG = OBJLONG;
        }
    }

}
