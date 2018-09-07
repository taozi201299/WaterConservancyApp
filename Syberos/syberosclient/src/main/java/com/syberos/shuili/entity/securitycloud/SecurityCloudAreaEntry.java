package com.syberos.shuili.entity.securitycloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecurityCloudAreaEntry implements Serializable{

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"yhxx":{"YHXX":[{"GPDB":0,"YZG":0,"HIDDGRAD":"1","YHZS":6798,"YQWG":0,"HIDDGRADNAME":"一般隐患","WZG":6798},{"GPDB":0,"YZG":0,"HIDDGRAD":"2","YHZS":24,"YQWG":0,"HIDDGRADNAME":"重大隐患","WZG":24}],"YHZS":6822,"WZG":6822},"aqpgScore":{"JYPX":null,"SG":24.42,"WXY":6.88,"YH":10.44,"AQPG":45.01,"COUNTDATE":"2018-03","XXSB":null,"KH":-0.06,"BZH":3.34,"GLZB2":null,"GLZB1":3.28},"gzkh":[{"AVGSCORE":45.14}],"fxyxx":[{"WBA":629,"YBA":0,"WGK":8,"YGK":7}],"bzh":[{"COUNT":0,"SJ":0,"YJ":0,"EJ":0}],"aqpgPm":[{"ORG_NAME":"水利部长江水利委员会","SAFA_SCORE":7.93},{"ORG_NAME":"水利部淮河水利委员会","SAFA_SCORE":39.33},{"ORG_NAME":"水利部珠江水利委员会","SAFA_SCORE":41.13},{"ORG_NAME":"水利部海河水利委员会","SAFA_SCORE":46.18},{"ORG_NAME":"水利部太湖流域管理局","SAFA_SCORE":52.98},{"ORG_NAME":"水利部黄河水利委员会","SAFA_SCORE":61.82},{"ORG_NAME":"水利部松辽水利委员会","SAFA_SCORE":65.71}],"aqpgMonth":[{"AQPG":39.49,"COUNTDATE":"2017-10"},{"AQPG":46.66,"COUNTDATE":"2017-11"},{"AQPG":49.91,"COUNTDATE":"2017-12"},{"AQPG":40.44,"COUNTDATE":"2018-01"},{"AQPG":56.39,"COUNTDATE":"2018-02"},{"AQPG":45.01,"COUNTDATE":"2018-03"}],"sgxx":[{"QTSG":0,"SGQS":2,"ZDSG":1,"SWRS":30,"JDSG":0,"TDSG":0,"YBSG":1}]}
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

    public static class MetaBean {
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
            return message == null ? "" : message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * yhxx : {"YHXX":[{"GPDB":0,"YZG":0,"HIDDGRAD":"1","YHZS":6798,"YQWG":0,"HIDDGRADNAME":"一般隐患","WZG":6798},{"GPDB":0,"YZG":0,"HIDDGRAD":"2","YHZS":24,"YQWG":0,"HIDDGRADNAME":"重大隐患","WZG":24}],"YHZS":6822,"WZG":6822}
         * aqpgScore : {"JYPX":null,"SG":24.42,"WXY":6.88,"YH":10.44,"AQPG":45.01,"COUNTDATE":"2018-03","XXSB":null,"KH":-0.06,"BZH":3.34,"GLZB2":null,"GLZB1":3.28}
         * gzkh : [{"AVGSCORE":45.14}]
         * fxyxx : [{"WBA":629,"YBA":0,"WGK":8,"YGK":7}]
         * bzh : [{"COUNT":0,"SJ":0,"YJ":0,"EJ":0}]
         * aqpgPm : [{"ORG_NAME":"水利部长江水利委员会","SAFA_SCORE":7.93},{"ORG_NAME":"水利部淮河水利委员会","SAFA_SCORE":39.33},{"ORG_NAME":"水利部珠江水利委员会","SAFA_SCORE":41.13},{"ORG_NAME":"水利部海河水利委员会","SAFA_SCORE":46.18},{"ORG_NAME":"水利部太湖流域管理局","SAFA_SCORE":52.98},{"ORG_NAME":"水利部黄河水利委员会","SAFA_SCORE":61.82},{"ORG_NAME":"水利部松辽水利委员会","SAFA_SCORE":65.71}]
         * aqpgMonth : [{"AQPG":39.49,"COUNTDATE":"2017-10"},{"AQPG":46.66,"COUNTDATE":"2017-11"},{"AQPG":49.91,"COUNTDATE":"2017-12"},{"AQPG":40.44,"COUNTDATE":"2018-01"},{"AQPG":56.39,"COUNTDATE":"2018-02"},{"AQPG":45.01,"COUNTDATE":"2018-03"}]
         * sgxx : [{"QTSG":0,"SGQS":2,"ZDSG":1,"SWRS":30,"JDSG":0,"TDSG":0,"YBSG":1}]
         */

        private YhxxBean yhxx;
        private AqpgScoreBean aqpgScore;
        private List<GzkhBean> gzkh;
        private List<FxyxxBean> fxyxx;
        private List<BzhBean> bzh;
        private List<AqpgPmBean> aqpgPm;
        private List<AqpgMonthBean> aqpgMonth;
        private List<SgxxBean> sgxx;

        public YhxxBean getYhxx() {
            return yhxx;
        }

        public void setYhxx(YhxxBean yhxx) {
            this.yhxx = yhxx;
        }

        public AqpgScoreBean getAqpgScore() {
            if(aqpgScore==null)
                return new AqpgScoreBean();
            return aqpgScore;
        }

        public void setAqpgScore(AqpgScoreBean aqpgScore) {
            this.aqpgScore = aqpgScore;
        }

        public List<GzkhBean> getGzkh() {
            if (gzkh == null) {
                return new ArrayList<>();
            }
            return gzkh;
        }

        public void setGzkh(List<GzkhBean> gzkh) {
            this.gzkh = gzkh;
        }

        public List<FxyxxBean> getFxyxx() {
            if (fxyxx == null) {
                return new ArrayList<>();
            }
            return fxyxx;
        }

        public void setFxyxx(List<FxyxxBean> fxyxx) {
            this.fxyxx = fxyxx;
        }

        public List<BzhBean> getBzh() {
            if (bzh == null) {
                return new ArrayList<>();
            }
            return bzh;
        }

        public void setBzh(List<BzhBean> bzh) {
            this.bzh = bzh;
        }

        public List<AqpgPmBean> getAqpgPm() {
            if (aqpgPm == null) {
                return new ArrayList<>();
            }
            return aqpgPm;
        }

        public void setAqpgPm(List<AqpgPmBean> aqpgPm) {
            this.aqpgPm = aqpgPm;
        }

        public List<AqpgMonthBean> getAqpgMonth() {
            if (aqpgMonth == null) {
                return new ArrayList<>();
            }
            return aqpgMonth;
        }

        public void setAqpgMonth(List<AqpgMonthBean> aqpgMonth) {
            this.aqpgMonth = aqpgMonth;
        }

        public List<SgxxBean> getSgxx() {
            if (sgxx == null) {
                return new ArrayList<>();
            }
            return sgxx;
        }

        public void setSgxx(List<SgxxBean> sgxx) {
            this.sgxx = sgxx;
        }

        public static class YhxxBean {
            /**
             * YHXX : [{"GPDB":0,"YZG":0,"HIDDGRAD":"1","YHZS":6798,"YQWG":0,"HIDDGRADNAME":"一般隐患","WZG":6798},{"GPDB":0,"YZG":0,"HIDDGRAD":"2","YHZS":24,"YQWG":0,"HIDDGRADNAME":"重大隐患","WZG":24}]
             * YHZS : 6822
             * WZG : 6822
             */

            private int YHZS;
            private int WZG;
            private List<YHXXBean> YHXX;

            public int getYHZS() {
                return YHZS;
            }

            public void setYHZS(int YHZS) {
                this.YHZS = YHZS;
            }

            public int getWZG() {
                return WZG;
            }

            public void setWZG(int WZG) {
                this.WZG = WZG;
            }

            public List<YHXXBean> getYHXX() {
                if (YHXX == null) {
                    return new ArrayList<>();
                }
                return YHXX;
            }

            public void setYHXX(List<YHXXBean> YHXX) {
                this.YHXX = YHXX;
            }

            public static class YHXXBean implements Serializable{
                /**
                 * GPDB : 0
                 * YZG : 0
                 * HIDDGRAD : 1
                 * YHZS : 6798
                 * YQWG : 0
                 * HIDDGRADNAME : 一般隐患
                 * WZG : 6798
                 */

                private int GPDB;
                private int YZG;
                private String HIDDGRAD;
                private int YHZS;
                private int YQWG;
                private String HIDDGRADNAME;
                private int WZG;

                public int getGPDB() {
                    return GPDB;
                }

                public void setGPDB(int GPDB) {
                    this.GPDB = GPDB;
                }

                public int getYZG() {
                    return YZG;
                }

                public void setYZG(int YZG) {
                    this.YZG = YZG;
                }

                public String getHIDDGRAD() {
                    return HIDDGRAD == null ? "" : HIDDGRAD;
                }

                public void setHIDDGRAD(String HIDDGRAD) {
                    this.HIDDGRAD = HIDDGRAD;
                }

                public int getYHZS() {
                    return YHZS;
                }

                public void setYHZS(int YHZS) {
                    this.YHZS = YHZS;
                }

                public int getYQWG() {
                    return YQWG;
                }

                public void setYQWG(int YQWG) {
                    this.YQWG = YQWG;
                }

                public String getHIDDGRADNAME() {
                    return HIDDGRADNAME == null ? "" : HIDDGRADNAME;
                }

                public void setHIDDGRADNAME(String HIDDGRADNAME) {
                    this.HIDDGRADNAME = HIDDGRADNAME;
                }

                public int getWZG() {
                    return WZG;
                }

                public void setWZG(int WZG) {
                    this.WZG = WZG;
                }
            }
        }

        public static class AqpgScoreBean implements Serializable{
            /**
             * JYPX : null
             * SG : 24.42
             * WXY : 6.88
             * YH : 10.44
             * AQPG : 45.01
             * COUNTDATE : 2018-03
             * XXSB : null
             * KH : -0.06
             * BZH : 3.34
             * GLZB2 : null
             * GLZB1 : 3.28
             */

            private Object JYPX;
            private double SG;
            private double WXY;
            private double YH;
            private double AQPG;
            private String COUNTDATE;
            private Object XXSB;
            private double KH;
            private double BZH;
            private Object GLZB2;
            private double GLZB1;

            public Object getJYPX() {
                return JYPX;
            }

            public void setJYPX(Object JYPX) {
                this.JYPX = JYPX;
            }

            public double getSG() {
                return SG;
            }

            public void setSG(double SG) {
                this.SG = SG;
            }

            public double getWXY() {
                return WXY;
            }

            public void setWXY(double WXY) {
                this.WXY = WXY;
            }

            public double getYH() {
                return YH;
            }

            public void setYH(double YH) {
                this.YH = YH;
            }

            public double getAQPG() {
                return AQPG;
            }

            public void setAQPG(double AQPG) {
                this.AQPG = AQPG;
            }

            public String getCOUNTDATE() {
                return COUNTDATE == null ? "" : COUNTDATE;
            }

            public void setCOUNTDATE(String COUNTDATE) {
                this.COUNTDATE = COUNTDATE;
            }

            public Object getXXSB() {
                return XXSB;
            }

            public void setXXSB(Object XXSB) {
                this.XXSB = XXSB;
            }

            public double getKH() {
                return KH;
            }

            public void setKH(double KH) {
                this.KH = KH;
            }

            public double getBZH() {
                return BZH;
            }

            public void setBZH(double BZH) {
                this.BZH = BZH;
            }

            public Object getGLZB2() {
                return GLZB2;
            }

            public void setGLZB2(Object GLZB2) {
                this.GLZB2 = GLZB2;
            }

            public double getGLZB1() {
                return GLZB1;
            }

            public void setGLZB1(double GLZB1) {
                this.GLZB1 = GLZB1;
            }
        }

        public static class GzkhBean implements Serializable{
            /**
             * AVGSCORE : 45.14
             */

            private double AVGSCORE;

            public double getAVGSCORE() {
                return AVGSCORE;
            }

            public void setAVGSCORE(double AVGSCORE) {
                this.AVGSCORE = AVGSCORE;
            }
        }

        public static class FxyxxBean implements Serializable{
            /**
             * WBA : 629
             * YBA : 0
             * WGK : 8
             * YGK : 7
             */

            private int WBA;
            private int YBA;
            private int WGK;
            private int YGK;

            public int getWBA() {
                return WBA;
            }

            public void setWBA(int WBA) {
                this.WBA = WBA;
            }

            public int getYBA() {
                return YBA;
            }

            public void setYBA(int YBA) {
                this.YBA = YBA;
            }

            public int getWGK() {
                return WGK;
            }

            public void setWGK(int WGK) {
                this.WGK = WGK;
            }

            public int getYGK() {
                return YGK;
            }

            public void setYGK(int YGK) {
                this.YGK = YGK;
            }
        }

        public static class BzhBean implements Serializable{
            /**
             * COUNT : 0
             * SJ : 0
             * YJ : 0
             * EJ : 0
             */

            private int COUNT;
            private int SJ;
            private int YJ;
            private int EJ;

            public int getCOUNT() {
                return COUNT;
            }

            public void setCOUNT(int COUNT) {
                this.COUNT = COUNT;
            }

            public int getSJ() {
                return SJ;
            }

            public void setSJ(int SJ) {
                this.SJ = SJ;
            }

            public int getYJ() {
                return YJ;
            }

            public void setYJ(int YJ) {
                this.YJ = YJ;
            }

            public int getEJ() {
                return EJ;
            }

            public void setEJ(int EJ) {
                this.EJ = EJ;
            }
        }

        public static class AqpgPmBean implements Serializable{
            /**
             * ORG_NAME : 水利部长江水利委员会
             * SAFA_SCORE : 7.93
             */

            private String ORG_NAME;
            private double SAFA_SCORE;

            public String getORG_NAME() {
                return ORG_NAME == null ? "" : ORG_NAME;
            }

            public void setORG_NAME(String ORG_NAME) {
                this.ORG_NAME = ORG_NAME;
            }

            public double getSAFA_SCORE() {
                return SAFA_SCORE;
            }

            public void setSAFA_SCORE(double SAFA_SCORE) {
                this.SAFA_SCORE = SAFA_SCORE;
            }
        }

        public static class AqpgMonthBean implements Serializable{
            /**
             * AQPG : 39.49
             * COUNTDATE : 2017-10
             */

            private double AQPG;
            private String COUNTDATE;

            public double getAQPG() {
                return AQPG;
            }

            public void setAQPG(double AQPG) {
                this.AQPG = AQPG;
            }

            public String getCOUNTDATE() {
                return COUNTDATE == null ? "" : COUNTDATE;
            }

            public void setCOUNTDATE(String COUNTDATE) {
                this.COUNTDATE = COUNTDATE;
            }
        }

        public static class SgxxBean implements Serializable{
            /**
             * QTSG : 0
             * SGQS : 2
             * ZDSG : 1
             * SWRS : 30
             * JDSG : 0
             * TDSG : 0
             * YBSG : 1
             */

            private int QTSG;
            private int SGQS;
            private int ZDSG;
            private int SWRS;
            private int JDSG;
            private int TDSG;
            private int YBSG;

            public int getQTSG() {
                return QTSG;
            }

            public void setQTSG(int QTSG) {
                this.QTSG = QTSG;
            }

            public int getSGQS() {
                return SGQS;
            }

            public void setSGQS(int SGQS) {
                this.SGQS = SGQS;
            }

            public int getZDSG() {
                return ZDSG;
            }

            public void setZDSG(int ZDSG) {
                this.ZDSG = ZDSG;
            }

            public int getSWRS() {
                return SWRS;
            }

            public void setSWRS(int SWRS) {
                this.SWRS = SWRS;
            }

            public int getJDSG() {
                return JDSG;
            }

            public void setJDSG(int JDSG) {
                this.JDSG = JDSG;
            }

            public int getTDSG() {
                return TDSG;
            }

            public void setTDSG(int TDSG) {
                this.TDSG = TDSG;
            }

            public int getYBSG() {
                return YBSG;
            }

            public void setYBSG(int YBSG) {
                this.YBSG = YBSG;
            }
        }
    }
}
