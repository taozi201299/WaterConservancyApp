package com.syberos.shuili.entity.thematic.hidden;

import java.io.Serializable;
import java.util.List;

public class HiddenEntry implements Serializable{

    /**
     * data : {"HIDDGRAD1LATEQUA":0,"HIDDGRAD1LISTQUA":1,"HIDDGRAD1RECTQUA":0,"HIDDGRAD1TOTALQUA":380,"HIDDGRAD2LATEQUA":0,"HIDDGRAD2LISTQUA":0,"HIDDGRAD2RECTQUA":0,"HIDDGRAD2TOTALQUA":5,"HIDDLATEQUA":0,"HIDDLISTQUA":1,"HIDDRECTQUA":0,"HIDDTOTALQUA":385,"ITEMDATA":[{"HIDDGRAD1LATEQUA":0,"HIDDGRAD1LISTQUA":0,"HIDDGRAD1RECTQUA":0,"HIDDGRAD1TOTALQUA":0,"HIDDGRAD2LATEQUA":0,"HIDDGRAD2LISTQUA":0,"HIDDGRAD2RECTQUA":0,"HIDDGRAD2TOTALQUA":1,"HIDDLATEQUA":0,"HIDDLISTQUA":0,"HIDDRECTQUA":0,"HIDDTOTALQUA":1,"OBJGUID":"c98c6d7aa8784adb8684947a50101314","OBJLAT":37.916475,"OBJLONG":107.074607,"OBJNAME":"大家好，我是中国第一美女陈晓洁！"}]}
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

    public static class DataBean implements Serializable{
        /**
         * HIDDGRAD1LATEQUA : 0
         * HIDDGRAD1LISTQUA : 1
         * HIDDGRAD1RECTQUA : 0
         * HIDDGRAD1TOTALQUA : 380
         * HIDDGRAD2LATEQUA : 0
         * HIDDGRAD2LISTQUA : 0
         * HIDDGRAD2RECTQUA : 0
         * HIDDGRAD2TOTALQUA : 5
         * HIDDLATEQUA : 0
         * HIDDLISTQUA : 1
         * HIDDRECTQUA : 0
         * HIDDTOTALQUA : 385
         * ITEMDATA : [{"HIDDGRAD1LATEQUA":0,"HIDDGRAD1LISTQUA":0,"HIDDGRAD1RECTQUA":0,"HIDDGRAD1TOTALQUA":0,"HIDDGRAD2LATEQUA":0,"HIDDGRAD2LISTQUA":0,"HIDDGRAD2RECTQUA":0,"HIDDGRAD2TOTALQUA":1,"HIDDLATEQUA":0,"HIDDLISTQUA":0,"HIDDRECTQUA":0,"HIDDTOTALQUA":1,"OBJGUID":"c98c6d7aa8784adb8684947a50101314","OBJLAT":37.916475,"OBJLONG":107.074607,"OBJNAME":"大家好，我是中国第一美女陈晓洁！"}]
         */

        private int HIDDGRAD1LATEQUA;
        private int HIDDGRAD1LISTQUA;
        private int HIDDGRAD1RECTQUA;
        private int HIDDGRAD1TOTALQUA;
        private int HIDDGRAD2LATEQUA;
        private int HIDDGRAD2LISTQUA;
        private int HIDDGRAD2RECTQUA;
        private int HIDDGRAD2TOTALQUA;
        private int HIDDLATEQUA;
        private int HIDDLISTQUA;
        private int HIDDRECTQUA;
        private int HIDDTOTALQUA;
        private List<ITEMDATABean> ITEMDATA;

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

        public int getHIDDGRAD2LATEQUA() {
            return HIDDGRAD2LATEQUA;
        }

        public void setHIDDGRAD2LATEQUA(int HIDDGRAD2LATEQUA) {
            this.HIDDGRAD2LATEQUA = HIDDGRAD2LATEQUA;
        }

        public int getHIDDGRAD2LISTQUA() {
            return HIDDGRAD2LISTQUA;
        }

        public void setHIDDGRAD2LISTQUA(int HIDDGRAD2LISTQUA) {
            this.HIDDGRAD2LISTQUA = HIDDGRAD2LISTQUA;
        }

        public int getHIDDGRAD2RECTQUA() {
            return HIDDGRAD2RECTQUA;
        }

        public void setHIDDGRAD2RECTQUA(int HIDDGRAD2RECTQUA) {
            this.HIDDGRAD2RECTQUA = HIDDGRAD2RECTQUA;
        }

        public int getHIDDGRAD2TOTALQUA() {
            return HIDDGRAD2TOTALQUA;
        }

        public void setHIDDGRAD2TOTALQUA(int HIDDGRAD2TOTALQUA) {
            this.HIDDGRAD2TOTALQUA = HIDDGRAD2TOTALQUA;
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

        public static class ITEMDATABean implements Serializable{
            /**
             * HIDDGRAD1LATEQUA : 0
             * HIDDGRAD1LISTQUA : 0
             * HIDDGRAD1RECTQUA : 0
             * HIDDGRAD1TOTALQUA : 0
             * HIDDGRAD2LATEQUA : 0
             * HIDDGRAD2LISTQUA : 0
             * HIDDGRAD2RECTQUA : 0
             * HIDDGRAD2TOTALQUA : 1
             * HIDDLATEQUA : 0
             * HIDDLISTQUA : 0
             * HIDDRECTQUA : 0
             * HIDDTOTALQUA : 1
             * OBJGUID : c98c6d7aa8784adb8684947a50101314
             * OBJLAT : 37.916475
             * OBJLONG : 107.074607
             * OBJNAME : 大家好，我是中国第一美女陈晓洁！
             */

            private int HIDDGRAD1LATEQUA;
            private int HIDDGRAD1LISTQUA;
            private int HIDDGRAD1RECTQUA;
            private int HIDDGRAD1TOTALQUA;
            private int HIDDGRAD2LATEQUA;
            private int HIDDGRAD2LISTQUA;
            private int HIDDGRAD2RECTQUA;
            private int HIDDGRAD2TOTALQUA;
            private int HIDDLATEQUA;
            private int HIDDLISTQUA;
            private int HIDDRECTQUA;
            private int HIDDTOTALQUA;
            private String OBJGUID;
            private double OBJLAT;
            private double OBJLONG;
            private String OBJNAME;

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

            public int getHIDDGRAD2LATEQUA() {
                return HIDDGRAD2LATEQUA;
            }

            public void setHIDDGRAD2LATEQUA(int HIDDGRAD2LATEQUA) {
                this.HIDDGRAD2LATEQUA = HIDDGRAD2LATEQUA;
            }

            public int getHIDDGRAD2LISTQUA() {
                return HIDDGRAD2LISTQUA;
            }

            public void setHIDDGRAD2LISTQUA(int HIDDGRAD2LISTQUA) {
                this.HIDDGRAD2LISTQUA = HIDDGRAD2LISTQUA;
            }

            public int getHIDDGRAD2RECTQUA() {
                return HIDDGRAD2RECTQUA;
            }

            public void setHIDDGRAD2RECTQUA(int HIDDGRAD2RECTQUA) {
                this.HIDDGRAD2RECTQUA = HIDDGRAD2RECTQUA;
            }

            public int getHIDDGRAD2TOTALQUA() {
                return HIDDGRAD2TOTALQUA;
            }

            public void setHIDDGRAD2TOTALQUA(int HIDDGRAD2TOTALQUA) {
                this.HIDDGRAD2TOTALQUA = HIDDGRAD2TOTALQUA;
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

            public String getOBJGUID() {
                return OBJGUID == null ? "" : OBJGUID;
            }

            public void setOBJGUID(String OBJGUID) {
                this.OBJGUID = OBJGUID;
            }

            public double getOBJLAT() {
                return OBJLAT;
            }

            public void setOBJLAT(double OBJLAT) {
                this.OBJLAT = OBJLAT;
            }

            public double getOBJLONG() {
                return OBJLONG;
            }

            public void setOBJLONG(double OBJLONG) {
                this.OBJLONG = OBJLONG;
            }

            public String getOBJNAME() {
                return OBJNAME == null ? "" : OBJNAME;
            }

            public void setOBJNAME(String OBJNAME) {
                this.OBJNAME = OBJNAME;
            }
        }
    }

    public static class MetaBean implements Serializable{
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
