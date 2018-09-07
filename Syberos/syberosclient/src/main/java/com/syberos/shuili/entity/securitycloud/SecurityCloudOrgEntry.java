package com.syberos.shuili.entity.securitycloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecurityCloudOrgEntry implements Serializable{

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"yhxx":{"YHXX":[{"GPDB":1,"YZG":0,"HIDDGRAD":"1","YHZS":2411,"YQWG":0,"HIDDGRADNAME":"一般隐患","WZG":2411},{"GPDB":0,"YZG":0,"HIDDGRAD":"2","YHZS":8,"YQWG":0,"HIDDGRADNAME":"重大隐患","WZG":8}],"YHZS":2419,"WZG":2419},"aqpgScore":{"JYPX":2.38,"SG":2.75,"WXY":2.45,"YH":2.42,"AQPG":3.49,"COUNTDATE":"2018-03","XXSB":2.37,"KH":null,"BZH":null,"GLZB2":4.75,"GLZB1":null},"fxyxx":[{"WBA":91,"YBA":0,"WGK":3,"YGK":1}],"xxsb":[{"AQPG_TYPE":"yh","YSB":91,"ZS":91,"SBL":100,"WSB":0},{"AQPG_TYPE":"sg","YSB":0,"ZS":0,"SBL":0,"WSB":0},{"AQPG_TYPE":"kh","YSB":0,"ZS":0,"SBL":0,"WSB":0}],"aqpgPm":[{"ORG_NAME":"镇泉引水电站","SAFA_SCORE":2},{"ORG_NAME":"16斗东站","SAFA_SCORE":2},{"ORG_NAME":"三干渠水电站","SAFA_SCORE":3},{"ORG_NAME":"勐戛河五级水电站","SAFA_SCORE":3},{"ORG_NAME":"皂市水电站","SAFA_SCORE":3},{"ORG_NAME":"古里卡河水电站","SAFA_SCORE":3},{"ORG_NAME":"桥沟水电站","SAFA_SCORE":3},{"ORG_NAME":"鱼背山水库","SAFA_SCORE":3},{"ORG_NAME":"杨东河电厂","SAFA_SCORE":3},{"ORG_NAME":"峡门口电站","SAFA_SCORE":3},{"ORG_NAME":"西沟水电站","SAFA_SCORE":3},{"ORG_NAME":"芒牙河二级电站","SAFA_SCORE":3},{"ORG_NAME":"大家好，我是中国第一美女陈晓洁！","SAFA_SCORE":3},{"ORG_NAME":"南王水库","SAFA_SCORE":3},{"ORG_NAME":"滨州市南海供水扩建工程","SAFA_SCORE":3},{"ORG_NAME":"伊犁人民水电站","SAFA_SCORE":3},{"ORG_NAME":"小浪底水利枢纽库区","SAFA_SCORE":3},{"ORG_NAME":"孟家水库","SAFA_SCORE":3},{"ORG_NAME":"和田县喀拉格尔水电站增效扩容改造工程","SAFA_SCORE":3},{"ORG_NAME":"双河电厂","SAFA_SCORE":3},{"ORG_NAME":"恒业湖水库","SAFA_SCORE":3},{"ORG_NAME":"昌蕴峡口水电站","SAFA_SCORE":3},{"ORG_NAME":"小浪底水利枢纽水库和水工金属结构设施","SAFA_SCORE":3},{"ORG_NAME":"鄂克托赛尔水电站工程","SAFA_SCORE":3},{"ORG_NAME":"四川省自贡市小井沟水利工程","SAFA_SCORE":3},{"ORG_NAME":"丽水黄村水库","SAFA_SCORE":3},{"ORG_NAME":"阜康红星水库","SAFA_SCORE":3},{"ORG_NAME":"西霞院水利枢纽水库和水工金属结构设施","SAFA_SCORE":3},{"ORG_NAME":"和田县喀拉格尔水电站","SAFA_SCORE":3},{"ORG_NAME":"向家嘴电站","SAFA_SCORE":3},{"ORG_NAME":"江垭水电站","SAFA_SCORE":3},{"ORG_NAME":"赶场电厂","SAFA_SCORE":3},{"ORG_NAME":"金盆水电站","SAFA_SCORE":3},{"ORG_NAME":"向家嘴水库","SAFA_SCORE":3},{"ORG_NAME":"五一水电站","SAFA_SCORE":4},{"ORG_NAME":"杨东河水库","SAFA_SCORE":4},{"ORG_NAME":"大路新区市政供水项目","SAFA_SCORE":4},{"ORG_NAME":"三干渠水电站工程","SAFA_SCORE":4},{"ORG_NAME":"农村小水电测试","SAFA_SCORE":4},{"ORG_NAME":"长滩电厂","SAFA_SCORE":4},{"ORG_NAME":"玉渊潭试验水电站","SAFA_SCORE":4},{"ORG_NAME":"新疆轮台县迪那河五一水库工程","SAFA_SCORE":4},{"ORG_NAME":"西霞院水利枢纽发供电设备","SAFA_SCORE":4},{"ORG_NAME":"长城贴瓷砖工程","SAFA_SCORE":4},{"ORG_NAME":"大南沟水库","SAFA_SCORE":4},{"ORG_NAME":"北海水库","SAFA_SCORE":4},{"ORG_NAME":"苗家滩水厂","SAFA_SCORE":4},{"ORG_NAME":"桥沟水电站","SAFA_SCORE":4},{"ORG_NAME":"清风湖水库","SAFA_SCORE":4},{"ORG_NAME":"\u201c500\u201d东延供水近期一步工程五彩湾冬季调蓄水池工程","SAFA_SCORE":4},{"ORG_NAME":"重庆市巫溪县镇泉引水电站工程发电厂房土建施工工程","SAFA_SCORE":4},{"ORG_NAME":"临长水库","SAFA_SCORE":4},{"ORG_NAME":"皂市枢纽水库","SAFA_SCORE":4},{"ORG_NAME":"湖北官渡河水电发展有限公司","SAFA_SCORE":4},{"ORG_NAME":"芒缅河水电站","SAFA_SCORE":4},{"ORG_NAME":"勐戛河四级水电站","SAFA_SCORE":4},{"ORG_NAME":"小浪底水利枢纽发供电设备","SAFA_SCORE":4},{"ORG_NAME":"农村水电站","SAFA_SCORE":4},{"ORG_NAME":"鄂克托赛尔水电站","SAFA_SCORE":4},{"ORG_NAME":"鱼背山电厂","SAFA_SCORE":4},{"ORG_NAME":"两会沱水电站","SAFA_SCORE":4},{"ORG_NAME":"小鱼沟泵站","SAFA_SCORE":4},{"ORG_NAME":"2","SAFA_SCORE":4},{"ORG_NAME":"红台子水厂","SAFA_SCORE":4},{"ORG_NAME":"1","SAFA_SCORE":4},{"ORG_NAME":"西霞院水利枢纽库区","SAFA_SCORE":4},{"ORG_NAME":"22","SAFA_SCORE":4},{"ORG_NAME":"魏家峁水厂","SAFA_SCORE":4},{"ORG_NAME":"五一水电站工程","SAFA_SCORE":4},{"ORG_NAME":"火炬水电站","SAFA_SCORE":4},{"ORG_NAME":"壤渡电厂","SAFA_SCORE":4}],"aqpgMonth":[{"AQPG":3.59,"COUNTDATE":"2017-10"},{"AQPG":3.59,"COUNTDATE":"2017-11"},{"AQPG":3.59,"COUNTDATE":"2017-12"},{"AQPG":3.55,"COUNTDATE":"2018-01"},{"AQPG":3.65,"COUNTDATE":"2018-02"},{"AQPG":3.49,"COUNTDATE":"2018-03"}],"sgxx":[{"QTSG":0,"SGQS":1,"ZDSG":0,"SWRS":1,"JDSG":0,"TDSG":0,"YBSG":1}],"jypx":[{"ZXS":0,"AVG_XS":0,"ZS":0}]}
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
         * yhxx : {"YHXX":[{"GPDB":1,"YZG":0,"HIDDGRAD":"1","YHZS":2411,"YQWG":0,"HIDDGRADNAME":"一般隐患","WZG":2411},{"GPDB":0,"YZG":0,"HIDDGRAD":"2","YHZS":8,"YQWG":0,"HIDDGRADNAME":"重大隐患","WZG":8}],"YHZS":2419,"WZG":2419}
         * aqpgScore : {"JYPX":2.38,"SG":2.75,"WXY":2.45,"YH":2.42,"AQPG":3.49,"COUNTDATE":"2018-03","XXSB":2.37,"KH":null,"BZH":null,"GLZB2":4.75,"GLZB1":null}
         * fxyxx : [{"WBA":91,"YBA":0,"WGK":3,"YGK":1}]
         * xxsb : [{"AQPG_TYPE":"yh","YSB":91,"ZS":91,"SBL":100,"WSB":0},{"AQPG_TYPE":"sg","YSB":0,"ZS":0,"SBL":0,"WSB":0},{"AQPG_TYPE":"kh","YSB":0,"ZS":0,"SBL":0,"WSB":0}]
         * aqpgPm : [{"ORG_NAME":"镇泉引水电站","SAFA_SCORE":2},{"ORG_NAME":"16斗东站","SAFA_SCORE":2},{"ORG_NAME":"三干渠水电站","SAFA_SCORE":3},{"ORG_NAME":"勐戛河五级水电站","SAFA_SCORE":3},{"ORG_NAME":"皂市水电站","SAFA_SCORE":3},{"ORG_NAME":"古里卡河水电站","SAFA_SCORE":3},{"ORG_NAME":"桥沟水电站","SAFA_SCORE":3},{"ORG_NAME":"鱼背山水库","SAFA_SCORE":3},{"ORG_NAME":"杨东河电厂","SAFA_SCORE":3},{"ORG_NAME":"峡门口电站","SAFA_SCORE":3},{"ORG_NAME":"西沟水电站","SAFA_SCORE":3},{"ORG_NAME":"芒牙河二级电站","SAFA_SCORE":3},{"ORG_NAME":"大家好，我是中国第一美女陈晓洁！","SAFA_SCORE":3},{"ORG_NAME":"南王水库","SAFA_SCORE":3},{"ORG_NAME":"滨州市南海供水扩建工程","SAFA_SCORE":3},{"ORG_NAME":"伊犁人民水电站","SAFA_SCORE":3},{"ORG_NAME":"小浪底水利枢纽库区","SAFA_SCORE":3},{"ORG_NAME":"孟家水库","SAFA_SCORE":3},{"ORG_NAME":"和田县喀拉格尔水电站增效扩容改造工程","SAFA_SCORE":3},{"ORG_NAME":"双河电厂","SAFA_SCORE":3},{"ORG_NAME":"恒业湖水库","SAFA_SCORE":3},{"ORG_NAME":"昌蕴峡口水电站","SAFA_SCORE":3},{"ORG_NAME":"小浪底水利枢纽水库和水工金属结构设施","SAFA_SCORE":3},{"ORG_NAME":"鄂克托赛尔水电站工程","SAFA_SCORE":3},{"ORG_NAME":"四川省自贡市小井沟水利工程","SAFA_SCORE":3},{"ORG_NAME":"丽水黄村水库","SAFA_SCORE":3},{"ORG_NAME":"阜康红星水库","SAFA_SCORE":3},{"ORG_NAME":"西霞院水利枢纽水库和水工金属结构设施","SAFA_SCORE":3},{"ORG_NAME":"和田县喀拉格尔水电站","SAFA_SCORE":3},{"ORG_NAME":"向家嘴电站","SAFA_SCORE":3},{"ORG_NAME":"江垭水电站","SAFA_SCORE":3},{"ORG_NAME":"赶场电厂","SAFA_SCORE":3},{"ORG_NAME":"金盆水电站","SAFA_SCORE":3},{"ORG_NAME":"向家嘴水库","SAFA_SCORE":3},{"ORG_NAME":"五一水电站","SAFA_SCORE":4},{"ORG_NAME":"杨东河水库","SAFA_SCORE":4},{"ORG_NAME":"大路新区市政供水项目","SAFA_SCORE":4},{"ORG_NAME":"三干渠水电站工程","SAFA_SCORE":4},{"ORG_NAME":"农村小水电测试","SAFA_SCORE":4},{"ORG_NAME":"长滩电厂","SAFA_SCORE":4},{"ORG_NAME":"玉渊潭试验水电站","SAFA_SCORE":4},{"ORG_NAME":"新疆轮台县迪那河五一水库工程","SAFA_SCORE":4},{"ORG_NAME":"西霞院水利枢纽发供电设备","SAFA_SCORE":4},{"ORG_NAME":"长城贴瓷砖工程","SAFA_SCORE":4},{"ORG_NAME":"大南沟水库","SAFA_SCORE":4},{"ORG_NAME":"北海水库","SAFA_SCORE":4},{"ORG_NAME":"苗家滩水厂","SAFA_SCORE":4},{"ORG_NAME":"桥沟水电站","SAFA_SCORE":4},{"ORG_NAME":"清风湖水库","SAFA_SCORE":4},{"ORG_NAME":"\u201c500\u201d东延供水近期一步工程五彩湾冬季调蓄水池工程","SAFA_SCORE":4},{"ORG_NAME":"重庆市巫溪县镇泉引水电站工程发电厂房土建施工工程","SAFA_SCORE":4},{"ORG_NAME":"临长水库","SAFA_SCORE":4},{"ORG_NAME":"皂市枢纽水库","SAFA_SCORE":4},{"ORG_NAME":"湖北官渡河水电发展有限公司","SAFA_SCORE":4},{"ORG_NAME":"芒缅河水电站","SAFA_SCORE":4},{"ORG_NAME":"勐戛河四级水电站","SAFA_SCORE":4},{"ORG_NAME":"小浪底水利枢纽发供电设备","SAFA_SCORE":4},{"ORG_NAME":"农村水电站","SAFA_SCORE":4},{"ORG_NAME":"鄂克托赛尔水电站","SAFA_SCORE":4},{"ORG_NAME":"鱼背山电厂","SAFA_SCORE":4},{"ORG_NAME":"两会沱水电站","SAFA_SCORE":4},{"ORG_NAME":"小鱼沟泵站","SAFA_SCORE":4},{"ORG_NAME":"2","SAFA_SCORE":4},{"ORG_NAME":"红台子水厂","SAFA_SCORE":4},{"ORG_NAME":"1","SAFA_SCORE":4},{"ORG_NAME":"西霞院水利枢纽库区","SAFA_SCORE":4},{"ORG_NAME":"22","SAFA_SCORE":4},{"ORG_NAME":"魏家峁水厂","SAFA_SCORE":4},{"ORG_NAME":"五一水电站工程","SAFA_SCORE":4},{"ORG_NAME":"火炬水电站","SAFA_SCORE":4},{"ORG_NAME":"壤渡电厂","SAFA_SCORE":4}]
         * aqpgMonth : [{"AQPG":3.59,"COUNTDATE":"2017-10"},{"AQPG":3.59,"COUNTDATE":"2017-11"},{"AQPG":3.59,"COUNTDATE":"2017-12"},{"AQPG":3.55,"COUNTDATE":"2018-01"},{"AQPG":3.65,"COUNTDATE":"2018-02"},{"AQPG":3.49,"COUNTDATE":"2018-03"}]
         * sgxx : [{"QTSG":0,"SGQS":1,"ZDSG":0,"SWRS":1,"JDSG":0,"TDSG":0,"YBSG":1}]
         * jypx : [{"ZXS":0,"AVG_XS":0,"ZS":0}]
         */

        private YhxxBean yhxx;
        private AqpgScoreBean aqpgScore;
        private List<FxyxxBean> fxyxx;
        private List<XxsbBean> xxsb;
        private List<AqpgPmBean> aqpgPm;
        private List<AqpgMonthBean> aqpgMonth;
        private List<SgxxBean> sgxx;
        private List<JypxBean> jypx;

        public YhxxBean getYhxx() {
            return yhxx;
        }

        public void setYhxx(YhxxBean yhxx) {
            this.yhxx = yhxx;
        }

        public AqpgScoreBean getAqpgScore() {
            return aqpgScore;
        }

        public void setAqpgScore(AqpgScoreBean aqpgScore) {
            this.aqpgScore = aqpgScore;
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

        public List<XxsbBean> getXxsb() {
            if (xxsb == null) {
                return new ArrayList<>();
            }
            return xxsb;
        }

        public void setXxsb(List<XxsbBean> xxsb) {
            this.xxsb = xxsb;
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

        public List<JypxBean> getJypx() {
            if (jypx == null) {
                return new ArrayList<>();
            }
            return jypx;
        }

        public void setJypx(List<JypxBean> jypx) {
            this.jypx = jypx;
        }

        public static class YhxxBean {
            /**
             * YHXX : [{"GPDB":1,"YZG":0,"HIDDGRAD":"1","YHZS":2411,"YQWG":0,"HIDDGRADNAME":"一般隐患","WZG":2411},{"GPDB":0,"YZG":0,"HIDDGRAD":"2","YHZS":8,"YQWG":0,"HIDDGRADNAME":"重大隐患","WZG":8}]
             * YHZS : 2419
             * WZG : 2419
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
                return YHXX;
            }

            public void setYHXX(List<YHXXBean> YHXX) {
                this.YHXX = YHXX;
            }

            public static class YHXXBean {
                /**
                 * GPDB : 1
                 * YZG : 0
                 * HIDDGRAD : 1
                 * YHZS : 2411
                 * YQWG : 0
                 * HIDDGRADNAME : 一般隐患
                 * WZG : 2411
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

        public static class AqpgScoreBean {
            /**
             * JYPX : 2.38
             * SG : 2.75
             * WXY : 2.45
             * YH : 2.42
             * AQPG : 3.49
             * COUNTDATE : 2018-03
             * XXSB : 2.37
             * KH : null
             * BZH : null
             * GLZB2 : 4.75
             * GLZB1 : null
             */

            private double JYPX;
            private double SG;
            private double WXY;
            private double YH;
            private double AQPG;
            private String COUNTDATE;
            private double XXSB;
            private Object KH;
            private Object BZH;
            private double GLZB2;
            private Object GLZB1;

            public double getJYPX() {
                return JYPX;
            }

            public void setJYPX(double JYPX) {
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

            public double getXXSB() {
                return XXSB;
            }

            public void setXXSB(double XXSB) {
                this.XXSB = XXSB;
            }

            public Object getKH() {
                return KH;
            }

            public void setKH(Object KH) {
                this.KH = KH;
            }

            public Object getBZH() {
                return BZH;
            }

            public void setBZH(Object BZH) {
                this.BZH = BZH;
            }

            public double getGLZB2() {
                return GLZB2;
            }

            public void setGLZB2(double GLZB2) {
                this.GLZB2 = GLZB2;
            }

            public Object getGLZB1() {
                return GLZB1;
            }

            public void setGLZB1(Object GLZB1) {
                this.GLZB1 = GLZB1;
            }
        }

        public static class FxyxxBean {
            /**
             * WBA : 91
             * YBA : 0
             * WGK : 3
             * YGK : 1
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

        public static class XxsbBean {
            /**
             * AQPG_TYPE : yh
             * YSB : 91
             * ZS : 91
             * SBL : 100
             * WSB : 0
             */

            private String AQPG_TYPE;
            private int YSB;
            private int ZS;
            private int SBL;
            private int WSB;

            public String getAQPG_TYPE() {
                return AQPG_TYPE == null ? "" : AQPG_TYPE;
            }

            public void setAQPG_TYPE(String AQPG_TYPE) {
                this.AQPG_TYPE = AQPG_TYPE;
            }

            public int getYSB() {
                return YSB;
            }

            public void setYSB(int YSB) {
                this.YSB = YSB;
            }

            public int getZS() {
                return ZS;
            }

            public void setZS(int ZS) {
                this.ZS = ZS;
            }

            public int getSBL() {
                return SBL;
            }

            public void setSBL(int SBL) {
                this.SBL = SBL;
            }

            public int getWSB() {
                return WSB;
            }

            public void setWSB(int WSB) {
                this.WSB = WSB;
            }
        }

        public static class AqpgPmBean {
            /**
             * ORG_NAME : 镇泉引水电站
             * SAFA_SCORE : 2
             */

            private String ORG_NAME;
            private int SAFA_SCORE;

            public String getORG_NAME() {
                return ORG_NAME == null ? "" : ORG_NAME;
            }

            public void setORG_NAME(String ORG_NAME) {
                this.ORG_NAME = ORG_NAME;
            }

            public int getSAFA_SCORE() {
                return SAFA_SCORE;
            }

            public void setSAFA_SCORE(int SAFA_SCORE) {
                this.SAFA_SCORE = SAFA_SCORE;
            }
        }

        public static class AqpgMonthBean {
            /**
             * AQPG : 3.59
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

        public static class SgxxBean {
            /**
             * QTSG : 0
             * SGQS : 1
             * ZDSG : 0
             * SWRS : 1
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

        public static class JypxBean {
            /**
             * ZXS : 0
             * AVG_XS : 0
             * ZS : 0
             */

            private int ZXS;
            private int AVG_XS;
            private int ZS;

            public int getZXS() {
                return ZXS;
            }

            public void setZXS(int ZXS) {
                this.ZXS = ZXS;
            }

            public int getAVG_XS() {
                return AVG_XS;
            }

            public void setAVG_XS(int AVG_XS) {
                this.AVG_XS = AVG_XS;
            }

            public int getZS() {
                return ZS;
            }

            public void setZS(int ZS) {
                this.ZS = ZS;
            }
        }
    }
}
