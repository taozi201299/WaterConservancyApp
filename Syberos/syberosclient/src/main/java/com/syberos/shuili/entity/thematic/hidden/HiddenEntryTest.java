package com.syberos.shuili.entity.thematic.hidden;

import java.io.Serializable;
import java.util.List;

public class HiddenEntryTest implements Serializable {

    /**
     * data : {"HIDDGRAD0LATEQUA":0,"HIDDGRAD0LISTQUA":0,"HIDDGRAD0RECTQUA":0,"HIDDGRAD0TOTALQUA":0,"HIDDGRAD1LATEQUA":0,"HIDDGRAD1LISTQUA":0,"HIDDGRAD1RECTQUA":0,"HIDDGRAD1TOTALQUA":1,"HIDDLATEQUA":0,"HIDDLISTQUA":0,"HIDDRECTQUA":0,"HIDDTOTALQUA":1,"ITEMDATA":[{"AD_CODE":"460000","AD_GRAD":"2","AD_LAT":19.16095687,"AD_LONG":109.62831135,"AD_NAME":"海南省","ENG_GUID":"1069ec95a5ab4394aa1578b68b20f74f","ENG_LAT":45.22365478,"ENG_LONG":125.68782151,"ENG_NAME":"56","HIDDGRAD0LATEQUA":0,"HIDDGRAD0LISTQUA":0,"HIDDGRAD0RECTQUA":0,"HIDDGRAD0TOTALQUA":0,"HIDDGRAD1LATEQUA":0,"HIDDGRAD1LISTQUA":0,"HIDDGRAD1RECTQUA":0,"HIDDGRAD1TOTALQUA":1,"HIDDLATEQUA":0,"HIDDLISTQUA":0,"HIDDRECTQUA":0,"HIDDTOTALQUA":1}]}
     * meta : {"message":"ok","success":true}
     */

    private DataBean data;
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

    public static class DataBean implements Serializable {
        /**
         * HIDDGRAD0LATEQUA : 0
         * HIDDGRAD0LISTQUA : 0
         * HIDDGRAD0RECTQUA : 0
         * HIDDGRAD0TOTALQUA : 0
         * HIDDGRAD1LATEQUA : 0
         * HIDDGRAD1LISTQUA : 0
         * HIDDGRAD1RECTQUA : 0
         * HIDDGRAD1TOTALQUA : 1
         * HIDDLATEQUA : 0
         * HIDDLISTQUA : 0
         * HIDDRECTQUA : 0
         * HIDDTOTALQUA : 1
         * ITEMDATA : [{"AD_CODE":"460000","AD_GRAD":"2","AD_LAT":19.16095687,"AD_LONG":109.62831135,"AD_NAME":"海南省","ENG_GUID":"1069ec95a5ab4394aa1578b68b20f74f","ENG_LAT":45.22365478,"ENG_LONG":125.68782151,"ENG_NAME":"56","HIDDGRAD0LATEQUA":0,"HIDDGRAD0LISTQUA":0,"HIDDGRAD0RECTQUA":0,"HIDDGRAD0TOTALQUA":0,"HIDDGRAD1LATEQUA":0,"HIDDGRAD1LISTQUA":0,"HIDDGRAD1RECTQUA":0,"HIDDGRAD1TOTALQUA":1,"HIDDLATEQUA":0,"HIDDLISTQUA":0,"HIDDRECTQUA":0,"HIDDTOTALQUA":1}]
         */

        private int HIDDGRAD0LATEQUA;
        private int HIDDGRAD0LISTQUA;
        private int HIDDGRAD0RECTQUA;
        private int HIDDGRAD0TOTALQUA;
        private int HIDDGRAD1LATEQUA;
        private int HIDDGRAD1LISTQUA;
        private int HIDDGRAD1RECTQUA;
        private int HIDDGRAD1TOTALQUA;
        private int HIDDLATEQUA;
        private int HIDDLISTQUA;
        private int HIDDRECTQUA;
        private int HIDDTOTALQUA;
        private List<ITEMDATABean> ITEMDATA;

        public int getHIDDGRAD0LATEQUA() {
            return HIDDGRAD0LATEQUA;
        }

        public void setHIDDGRAD0LATEQUA(int HIDDGRAD0LATEQUA) {
            this.HIDDGRAD0LATEQUA = HIDDGRAD0LATEQUA;
        }

        public int getHIDDGRAD0LISTQUA() {
            return HIDDGRAD0LISTQUA;
        }

        public void setHIDDGRAD0LISTQUA(int HIDDGRAD0LISTQUA) {
            this.HIDDGRAD0LISTQUA = HIDDGRAD0LISTQUA;
        }

        public int getHIDDGRAD0RECTQUA() {
            return HIDDGRAD0RECTQUA;
        }

        public void setHIDDGRAD0RECTQUA(int HIDDGRAD0RECTQUA) {
            this.HIDDGRAD0RECTQUA = HIDDGRAD0RECTQUA;
        }

        public int getHIDDGRAD0TOTALQUA() {
            return HIDDGRAD0TOTALQUA;
        }

        public void setHIDDGRAD0TOTALQUA(int HIDDGRAD0TOTALQUA) {
            this.HIDDGRAD0TOTALQUA = HIDDGRAD0TOTALQUA;
        }

        public int getHIDDGRAD1LATEQUA() {
            return HIDDGRAD1LATEQUA;
        }

        public void setHIDDGRAD1LATEQUA(int HIDDGRAD1LATEQUA) {
            this.HIDDGRAD1LATEQUA = HIDDGRAD1LATEQUA;
        }

        public int getHIDDGRAD1LISTQUA() {
            return HIDDGRAD1LISTQUA;
        }

        public void setHIDDGRAD1LISTQUA(int HIDDGRAD1LISTQUA) {
            this.HIDDGRAD1LISTQUA = HIDDGRAD1LISTQUA;
        }

        public int getHIDDGRAD1RECTQUA() {
            return HIDDGRAD1RECTQUA;
        }

        public void setHIDDGRAD1RECTQUA(int HIDDGRAD1RECTQUA) {
            this.HIDDGRAD1RECTQUA = HIDDGRAD1RECTQUA;
        }

        public int getHIDDGRAD1TOTALQUA() {
            return HIDDGRAD1TOTALQUA;
        }

        public void setHIDDGRAD1TOTALQUA(int HIDDGRAD1TOTALQUA) {
            this.HIDDGRAD1TOTALQUA = HIDDGRAD1TOTALQUA;
        }

        public int getHIDDLATEQUA() {
            return HIDDLATEQUA;
        }

        public void setHIDDLATEQUA(int HIDDLATEQUA) {
            this.HIDDLATEQUA = HIDDLATEQUA;
        }

        public int getHIDDLISTQUA() {
            return HIDDLISTQUA;
        }

        public void setHIDDLISTQUA(int HIDDLISTQUA) {
            this.HIDDLISTQUA = HIDDLISTQUA;
        }

        public int getHIDDRECTQUA() {
            return HIDDRECTQUA;
        }

        public void setHIDDRECTQUA(int HIDDRECTQUA) {
            this.HIDDRECTQUA = HIDDRECTQUA;
        }

        public int getHIDDTOTALQUA() {
            return HIDDTOTALQUA;
        }

        public void setHIDDTOTALQUA(int HIDDTOTALQUA) {
            this.HIDDTOTALQUA = HIDDTOTALQUA;
        }

        public List<ITEMDATABean> getITEMDATA() {
            return ITEMDATA;
        }

        public void setITEMDATA(List<ITEMDATABean> ITEMDATA) {
            this.ITEMDATA = ITEMDATA;
        }

        public static class ITEMDATABean implements Serializable {
            /**
             * AD_CODE : 460000
             * AD_GRAD : 2
             * AD_LAT : 19.16095687
             * AD_LONG : 109.62831135
             * AD_NAME : 海南省
             * ENG_GUID : 1069ec95a5ab4394aa1578b68b20f74f
             * ENG_LAT : 45.22365478
             * ENG_LONG : 125.68782151
             * ENG_NAME : 56
             * HIDDGRAD0LATEQUA : 0
             * HIDDGRAD0LISTQUA : 0
             * HIDDGRAD0RECTQUA : 0
             * HIDDGRAD0TOTALQUA : 0
             * HIDDGRAD1LATEQUA : 0
             * HIDDGRAD1LISTQUA : 0
             * HIDDGRAD1RECTQUA : 0
             * HIDDGRAD1TOTALQUA : 1
             * HIDDLATEQUA : 0
             * HIDDLISTQUA : 0
             * HIDDRECTQUA : 0
             * HIDDTOTALQUA : 1
             */

            private String AD_CODE;
            private String AD_GRAD;
            private double AD_LAT;
            private double AD_LONG;
            private String AD_NAME;
            private String ENG_GUID;
            private double ENG_LAT;
            private double ENG_LONG;
            private String ENG_NAME;
            private int HIDDGRAD0LATEQUA;
            private int HIDDGRAD0LISTQUA;
            private int HIDDGRAD0RECTQUA;
            private int HIDDGRAD0TOTALQUA;
            private int HIDDGRAD1LATEQUA;
            private int HIDDGRAD1LISTQUA;
            private int HIDDGRAD1RECTQUA;
            private int HIDDGRAD1TOTALQUA;
            private int HIDDLATEQUA;
            private int HIDDLISTQUA;
            private int HIDDRECTQUA;
            private int HIDDTOTALQUA;

            public String getAD_CODE() {
                return AD_CODE;
            }

            public void setAD_CODE(String AD_CODE) {
                this.AD_CODE = AD_CODE;
            }

            public String getAD_GRAD() {
                return AD_GRAD;
            }

            public void setAD_GRAD(String AD_GRAD) {
                this.AD_GRAD = AD_GRAD;
            }

            public double getAD_LAT() {
                return AD_LAT;
            }

            public void setAD_LAT(double AD_LAT) {
                this.AD_LAT = AD_LAT;
            }

            public double getAD_LONG() {
                return AD_LONG;
            }

            public void setAD_LONG(double AD_LONG) {
                this.AD_LONG = AD_LONG;
            }

            public String getAD_NAME() {
                return AD_NAME;
            }

            public void setAD_NAME(String AD_NAME) {
                this.AD_NAME = AD_NAME;
            }

            public String getENG_GUID() {
                return ENG_GUID;
            }

            public void setENG_GUID(String ENG_GUID) {
                this.ENG_GUID = ENG_GUID;
            }

            public double getENG_LAT() {
                return ENG_LAT;
            }

            public void setENG_LAT(double ENG_LAT) {
                this.ENG_LAT = ENG_LAT;
            }

            public double getENG_LONG() {
                return ENG_LONG;
            }

            public void setENG_LONG(double ENG_LONG) {
                this.ENG_LONG = ENG_LONG;
            }

            public String getENG_NAME() {
                return ENG_NAME;
            }

            public void setENG_NAME(String ENG_NAME) {
                this.ENG_NAME = ENG_NAME;
            }

            public int getHIDDGRAD0LATEQUA() {
                return HIDDGRAD0LATEQUA;
            }

            public void setHIDDGRAD0LATEQUA(int HIDDGRAD0LATEQUA) {
                this.HIDDGRAD0LATEQUA = HIDDGRAD0LATEQUA;
            }

            public int getHIDDGRAD0LISTQUA() {
                return HIDDGRAD0LISTQUA;
            }

            public void setHIDDGRAD0LISTQUA(int HIDDGRAD0LISTQUA) {
                this.HIDDGRAD0LISTQUA = HIDDGRAD0LISTQUA;
            }

            public int getHIDDGRAD0RECTQUA() {
                return HIDDGRAD0RECTQUA;
            }

            public void setHIDDGRAD0RECTQUA(int HIDDGRAD0RECTQUA) {
                this.HIDDGRAD0RECTQUA = HIDDGRAD0RECTQUA;
            }

            public int getHIDDGRAD0TOTALQUA() {
                return HIDDGRAD0TOTALQUA;
            }

            public void setHIDDGRAD0TOTALQUA(int HIDDGRAD0TOTALQUA) {
                this.HIDDGRAD0TOTALQUA = HIDDGRAD0TOTALQUA;
            }

            public int getHIDDGRAD1LATEQUA() {
                return HIDDGRAD1LATEQUA;
            }

            public void setHIDDGRAD1LATEQUA(int HIDDGRAD1LATEQUA) {
                this.HIDDGRAD1LATEQUA = HIDDGRAD1LATEQUA;
            }

            public int getHIDDGRAD1LISTQUA() {
                return HIDDGRAD1LISTQUA;
            }

            public void setHIDDGRAD1LISTQUA(int HIDDGRAD1LISTQUA) {
                this.HIDDGRAD1LISTQUA = HIDDGRAD1LISTQUA;
            }

            public int getHIDDGRAD1RECTQUA() {
                return HIDDGRAD1RECTQUA;
            }

            public void setHIDDGRAD1RECTQUA(int HIDDGRAD1RECTQUA) {
                this.HIDDGRAD1RECTQUA = HIDDGRAD1RECTQUA;
            }

            public int getHIDDGRAD1TOTALQUA() {
                return HIDDGRAD1TOTALQUA;
            }

            public void setHIDDGRAD1TOTALQUA(int HIDDGRAD1TOTALQUA) {
                this.HIDDGRAD1TOTALQUA = HIDDGRAD1TOTALQUA;
            }

            public int getHIDDLATEQUA() {
                return HIDDLATEQUA;
            }

            public void setHIDDLATEQUA(int HIDDLATEQUA) {
                this.HIDDLATEQUA = HIDDLATEQUA;
            }

            public int getHIDDLISTQUA() {
                return HIDDLISTQUA;
            }

            public void setHIDDLISTQUA(int HIDDLISTQUA) {
                this.HIDDLISTQUA = HIDDLISTQUA;
            }

            public int getHIDDRECTQUA() {
                return HIDDRECTQUA;
            }

            public void setHIDDRECTQUA(int HIDDRECTQUA) {
                this.HIDDRECTQUA = HIDDRECTQUA;
            }

            public int getHIDDTOTALQUA() {
                return HIDDTOTALQUA;
            }

            public void setHIDDTOTALQUA(int HIDDTOTALQUA) {
                this.HIDDTOTALQUA = HIDDTOTALQUA;
            }
        }
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
}
