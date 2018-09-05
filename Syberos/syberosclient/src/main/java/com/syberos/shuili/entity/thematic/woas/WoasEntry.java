package com.syberos.shuili.entity.thematic.woas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WoasEntry implements Serializable {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"AVGSCORE":{"TOTALSCOR":1144,"UNUM":21},"WOAS":{"id":null,"guid":"cdeb414d059f4ab3b250b8c568920b07","woasThem":"2018安全检查","lareId":"[2018000]字","woasType":"1","woasStartime":"2018-10-01 00:00:00","woasDeadline":"2018-12-31 00:00:00","sendStat":null,"orgGuid":"D7862390F88443AE87FA9DD1FE45A8B6","isForw":null,"woasGuid":null,"woasFileFiel":null,"otheFileFiel":null,"note":null,"collTime":"2018-01-01 00:00:00","updTime":null,"recPers":null,"orgName":"中华人民共和国水利部","woasTypeName":"水利稽察考核","avgScor":null},"SCORERANK":[{"BFINALSCOR":83,"AWOASWIUNGUID":"23C5E698A37247BA8D74622D81E2B037","ARANKNUM":1,"BRANKNUM":5,"BGRADSTAT":"2","AGRADSTAT":"1","AFINALSCOR":95,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"中国水利报社","ADCODE":"000000"},{"BFINALSCOR":37,"AWOASWIUNGUID":"31F501AD74504AA7B1B0945C9A989755","ARANKNUM":2,"BRANKNUM":16,"BGRADSTAT":"1","AGRADSTAT":"3","AFINALSCOR":86,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"中国水利博物馆","ADCODE":"000000"},{"BFINALSCOR":39,"AWOASWIUNGUID":"E7B505FDEA1A4AE19E75672721187C8E","ARANKNUM":3,"BRANKNUM":14,"BGRADSTAT":"0","AGRADSTAT":"3","AFINALSCOR":85,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部建设管理与质量安全中心","ADCODE":"000000"},{"BFINALSCOR":40,"AWOASWIUNGUID":"4DCAB2C5A38149B3AA316148D9317EEF","ARANKNUM":4,"BRANKNUM":13,"BGRADSTAT":"2","AGRADSTAT":"2","AFINALSCOR":85,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部发展研究中心","ADCODE":"000000"},{"BFINALSCOR":43,"AWOASWIUNGUID":"13B9B5C0FA6C425891A7F8DECF86A24A","ARANKNUM":5,"BRANKNUM":12,"BGRADSTAT":"2","AGRADSTAT":"2","AFINALSCOR":80,"ORGLAT":28.14007,"ORGLONG":113.006344,"ORGNAME":"湖南澧水流域水利水电开发有限公司","ADCODE":"000000"},{"BFINALSCOR":36,"AWOASWIUNGUID":"0ADC8B975E4B4F0685F16594F4625A1A","ARANKNUM":6,"BRANKNUM":17,"BGRADSTAT":"2","AGRADSTAT":"0","AFINALSCOR":71,"ORGLAT":37.872514,"ORGLONG":112.562594,"ORGNAME":"黄河万家寨水利枢纽有限公司","ADCODE":"000000"},{"BFINALSCOR":92,"AWOASWIUNGUID":"C519CD4AC001420097DC092816F85DB6","ARANKNUM":7,"BRANKNUM":2,"BGRADSTAT":"1","AGRADSTAT":"3","AFINALSCOR":68,"ORGLAT":39.88457,"ORGLONG":116.352597,"ORGNAME":"水利部机关服务中心（水利部机关服务局）","ADCODE":"000000"},{"BFINALSCOR":84,"AWOASWIUNGUID":"02D2BB5DAFAD406DABDDE2861A9DF500","ARANKNUM":8,"BRANKNUM":4,"BGRADSTAT":"1","AGRADSTAT":"2","AFINALSCOR":64,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"水利部农村水电及电气化发展局","ADCODE":"000000"},{"BFINALSCOR":33,"AWOASWIUNGUID":"5CEE47F6E8184657BB102401B124B3F4","ARANKNUM":9,"BRANKNUM":18,"BGRADSTAT":"3","AGRADSTAT":"3","AFINALSCOR":58,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"水利部南水北调规划设计管理局","ADCODE":"000000"},{"BFINALSCOR":48,"AWOASWIUNGUID":"8729A08719CA4F5DB1B65081F63347A8","ARANKNUM":10,"BRANKNUM":9,"BGRADSTAT":"2","AGRADSTAT":"3","AFINALSCOR":55,"ORGLAT":34.69134647,"ORGLONG":113.74821983,"ORGNAME":"水利部小浪底水利枢纽管理中心","ADCODE":"000000"},{"BFINALSCOR":74,"AWOASWIUNGUID":"5712032989024424A276349B3C51FCA5","ARANKNUM":11,"BRANKNUM":7,"BGRADSTAT":"1","AGRADSTAT":"0","AFINALSCOR":54,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"中国灌溉排水发展中心","ADCODE":"000000"},{"BFINALSCOR":47,"AWOASWIUNGUID":"2DB4223E4A754C92827424AE7A5CF125","ARANKNUM":12,"BRANKNUM":10,"BGRADSTAT":"1","AGRADSTAT":"3","AFINALSCOR":54,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部水库移民开发局","ADCODE":"000000"},{"BFINALSCOR":4,"AWOASWIUNGUID":"B63B0042767842AAA4DBBC2E1539C149","ARANKNUM":13,"BRANKNUM":21,"BGRADSTAT":"2","AGRADSTAT":"2","AFINALSCOR":48,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"中国水利水电出版社","ADCODE":"000000"},{"BFINALSCOR":8,"AWOASWIUNGUID":"1F9177491B5145BF89C99078F6A96FF9","ARANKNUM":14,"BRANKNUM":20,"BGRADSTAT":"2","AGRADSTAT":"1","AFINALSCOR":48,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"中国水利水电科学研究院","ADCODE":"000000"},{"BFINALSCOR":54,"AWOASWIUNGUID":"7DB2F4BD3FD24A6992FDF0DEB6C26B37","ARANKNUM":15,"BRANKNUM":8,"BGRADSTAT":"1","AGRADSTAT":"1","AFINALSCOR":46,"ORGLAT":32.08648095,"ORGLONG":118.75185551,"ORGNAME":"水利部交通运输部国家能源局南京水利科学研究院","ADCODE":"000000"},{"BFINALSCOR":99,"AWOASWIUNGUID":"EA1923035A7C4F118E544050C68A8475","ARANKNUM":16,"BRANKNUM":1,"BGRADSTAT":"2","AGRADSTAT":"0","AFINALSCOR":44,"ORGLAT":39.953482,"ORGLONG":116.384725,"ORGNAME":"水利部水利水电规划设计总院","ADCODE":"000000"},{"BFINALSCOR":81,"AWOASWIUNGUID":"995BD75652584B60B4427656CF36B3C0","ARANKNUM":17,"BRANKNUM":6,"BGRADSTAT":"3","AGRADSTAT":"3","AFINALSCOR":32,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部综合事业局","ADCODE":"000000"},{"BFINALSCOR":90,"AWOASWIUNGUID":"3F77A3319EE042B782F630E71B4D815E","ARANKNUM":18,"BRANKNUM":3,"BGRADSTAT":"1","AGRADSTAT":"1","AFINALSCOR":24,"ORGLAT":34.80293476,"ORGLONG":111.0924804,"ORGNAME":"水利部三门峡疗养院","ADCODE":"000000"},{"BFINALSCOR":19,"AWOASWIUNGUID":"2A28602FFCE2469BA94E80EA299F6378","ARANKNUM":19,"BRANKNUM":19,"BGRADSTAT":"2","AGRADSTAT":"3","AFINALSCOR":22,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部预算执行中心","ADCODE":"000000"},{"BFINALSCOR":45,"AWOASWIUNGUID":"7EF6B4C23F3548D5AE625A75BC891EAF","ARANKNUM":20,"BRANKNUM":11,"BGRADSTAT":"3","AGRADSTAT":"3","AFINALSCOR":14,"ORGLAT":39.88456,"ORGLONG":116.352052,"ORGNAME":"水利部水文局(水利部水利信息中心)","ADCODE":"000000"},{"BFINALSCOR":37,"AWOASWIUNGUID":"75EE301CD2B64853B7E63397F86C5A24","ARANKNUM":21,"BRANKNUM":15,"BGRADSTAT":"2","AGRADSTAT":"3","AFINALSCOR":11,"ORGLAT":30.21769971,"ORGLONG":120.07646056,"ORGNAME":"国际小水电中心","ADCODE":"000000"}],"RECAVGSCORE":[{"WOAS_STARTM":"2018","TOTALSCOR":1144,"UNUM":21},{"WOAS_STARTM":"2017","TOTALSCOR":1063,"UNUM":21},{"WOAS_STARTM":"2016","TOTALSCOR":1093,"UNUM":21}]}
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
            return message == null ? "" : message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * AVGSCORE : {"TOTALSCOR":1144,"UNUM":21}
         * WOAS : {"id":null,"guid":"cdeb414d059f4ab3b250b8c568920b07","woasThem":"2018安全检查","lareId":"[2018000]字","woasType":"1","woasStartime":"2018-10-01 00:00:00","woasDeadline":"2018-12-31 00:00:00","sendStat":null,"orgGuid":"D7862390F88443AE87FA9DD1FE45A8B6","isForw":null,"woasGuid":null,"woasFileFiel":null,"otheFileFiel":null,"note":null,"collTime":"2018-01-01 00:00:00","updTime":null,"recPers":null,"orgName":"中华人民共和国水利部","woasTypeName":"水利稽察考核","avgScor":null}
         * SCORERANK : [{"BFINALSCOR":83,"AWOASWIUNGUID":"23C5E698A37247BA8D74622D81E2B037","ARANKNUM":1,"BRANKNUM":5,"BGRADSTAT":"2","AGRADSTAT":"1","AFINALSCOR":95,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"中国水利报社","ADCODE":"000000"},{"BFINALSCOR":37,"AWOASWIUNGUID":"31F501AD74504AA7B1B0945C9A989755","ARANKNUM":2,"BRANKNUM":16,"BGRADSTAT":"1","AGRADSTAT":"3","AFINALSCOR":86,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"中国水利博物馆","ADCODE":"000000"},{"BFINALSCOR":39,"AWOASWIUNGUID":"E7B505FDEA1A4AE19E75672721187C8E","ARANKNUM":3,"BRANKNUM":14,"BGRADSTAT":"0","AGRADSTAT":"3","AFINALSCOR":85,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部建设管理与质量安全中心","ADCODE":"000000"},{"BFINALSCOR":40,"AWOASWIUNGUID":"4DCAB2C5A38149B3AA316148D9317EEF","ARANKNUM":4,"BRANKNUM":13,"BGRADSTAT":"2","AGRADSTAT":"2","AFINALSCOR":85,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部发展研究中心","ADCODE":"000000"},{"BFINALSCOR":43,"AWOASWIUNGUID":"13B9B5C0FA6C425891A7F8DECF86A24A","ARANKNUM":5,"BRANKNUM":12,"BGRADSTAT":"2","AGRADSTAT":"2","AFINALSCOR":80,"ORGLAT":28.14007,"ORGLONG":113.006344,"ORGNAME":"湖南澧水流域水利水电开发有限公司","ADCODE":"000000"},{"BFINALSCOR":36,"AWOASWIUNGUID":"0ADC8B975E4B4F0685F16594F4625A1A","ARANKNUM":6,"BRANKNUM":17,"BGRADSTAT":"2","AGRADSTAT":"0","AFINALSCOR":71,"ORGLAT":37.872514,"ORGLONG":112.562594,"ORGNAME":"黄河万家寨水利枢纽有限公司","ADCODE":"000000"},{"BFINALSCOR":92,"AWOASWIUNGUID":"C519CD4AC001420097DC092816F85DB6","ARANKNUM":7,"BRANKNUM":2,"BGRADSTAT":"1","AGRADSTAT":"3","AFINALSCOR":68,"ORGLAT":39.88457,"ORGLONG":116.352597,"ORGNAME":"水利部机关服务中心（水利部机关服务局）","ADCODE":"000000"},{"BFINALSCOR":84,"AWOASWIUNGUID":"02D2BB5DAFAD406DABDDE2861A9DF500","ARANKNUM":8,"BRANKNUM":4,"BGRADSTAT":"1","AGRADSTAT":"2","AFINALSCOR":64,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"水利部农村水电及电气化发展局","ADCODE":"000000"},{"BFINALSCOR":33,"AWOASWIUNGUID":"5CEE47F6E8184657BB102401B124B3F4","ARANKNUM":9,"BRANKNUM":18,"BGRADSTAT":"3","AGRADSTAT":"3","AFINALSCOR":58,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"水利部南水北调规划设计管理局","ADCODE":"000000"},{"BFINALSCOR":48,"AWOASWIUNGUID":"8729A08719CA4F5DB1B65081F63347A8","ARANKNUM":10,"BRANKNUM":9,"BGRADSTAT":"2","AGRADSTAT":"3","AFINALSCOR":55,"ORGLAT":34.69134647,"ORGLONG":113.74821983,"ORGNAME":"水利部小浪底水利枢纽管理中心","ADCODE":"000000"},{"BFINALSCOR":74,"AWOASWIUNGUID":"5712032989024424A276349B3C51FCA5","ARANKNUM":11,"BRANKNUM":7,"BGRADSTAT":"1","AGRADSTAT":"0","AFINALSCOR":54,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"中国灌溉排水发展中心","ADCODE":"000000"},{"BFINALSCOR":47,"AWOASWIUNGUID":"2DB4223E4A754C92827424AE7A5CF125","ARANKNUM":12,"BRANKNUM":10,"BGRADSTAT":"1","AGRADSTAT":"3","AFINALSCOR":54,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部水库移民开发局","ADCODE":"000000"},{"BFINALSCOR":4,"AWOASWIUNGUID":"B63B0042767842AAA4DBBC2E1539C149","ARANKNUM":13,"BRANKNUM":21,"BGRADSTAT":"2","AGRADSTAT":"2","AFINALSCOR":48,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"中国水利水电出版社","ADCODE":"000000"},{"BFINALSCOR":8,"AWOASWIUNGUID":"1F9177491B5145BF89C99078F6A96FF9","ARANKNUM":14,"BRANKNUM":20,"BGRADSTAT":"2","AGRADSTAT":"1","AFINALSCOR":48,"ORGLAT":40.02194405,"ORGLONG":116.24406861,"ORGNAME":"中国水利水电科学研究院","ADCODE":"000000"},{"BFINALSCOR":54,"AWOASWIUNGUID":"7DB2F4BD3FD24A6992FDF0DEB6C26B37","ARANKNUM":15,"BRANKNUM":8,"BGRADSTAT":"1","AGRADSTAT":"1","AFINALSCOR":46,"ORGLAT":32.08648095,"ORGLONG":118.75185551,"ORGNAME":"水利部交通运输部国家能源局南京水利科学研究院","ADCODE":"000000"},{"BFINALSCOR":99,"AWOASWIUNGUID":"EA1923035A7C4F118E544050C68A8475","ARANKNUM":16,"BRANKNUM":1,"BGRADSTAT":"2","AGRADSTAT":"0","AFINALSCOR":44,"ORGLAT":39.953482,"ORGLONG":116.384725,"ORGNAME":"水利部水利水电规划设计总院","ADCODE":"000000"},{"BFINALSCOR":81,"AWOASWIUNGUID":"995BD75652584B60B4427656CF36B3C0","ARANKNUM":17,"BRANKNUM":6,"BGRADSTAT":"3","AGRADSTAT":"3","AFINALSCOR":32,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部综合事业局","ADCODE":"000000"},{"BFINALSCOR":90,"AWOASWIUNGUID":"3F77A3319EE042B782F630E71B4D815E","ARANKNUM":18,"BRANKNUM":3,"BGRADSTAT":"1","AGRADSTAT":"1","AFINALSCOR":24,"ORGLAT":34.80293476,"ORGLONG":111.0924804,"ORGNAME":"水利部三门峡疗养院","ADCODE":"000000"},{"BFINALSCOR":19,"AWOASWIUNGUID":"2A28602FFCE2469BA94E80EA299F6378","ARANKNUM":19,"BRANKNUM":19,"BGRADSTAT":"2","AGRADSTAT":"3","AFINALSCOR":22,"ORGLAT":39.9196397,"ORGLONG":116.35741442,"ORGNAME":"水利部预算执行中心","ADCODE":"000000"},{"BFINALSCOR":45,"AWOASWIUNGUID":"7EF6B4C23F3548D5AE625A75BC891EAF","ARANKNUM":20,"BRANKNUM":11,"BGRADSTAT":"3","AGRADSTAT":"3","AFINALSCOR":14,"ORGLAT":39.88456,"ORGLONG":116.352052,"ORGNAME":"水利部水文局(水利部水利信息中心)","ADCODE":"000000"},{"BFINALSCOR":37,"AWOASWIUNGUID":"75EE301CD2B64853B7E63397F86C5A24","ARANKNUM":21,"BRANKNUM":15,"BGRADSTAT":"2","AGRADSTAT":"3","AFINALSCOR":11,"ORGLAT":30.21769971,"ORGLONG":120.07646056,"ORGNAME":"国际小水电中心","ADCODE":"000000"}]
         * RECAVGSCORE : [{"WOAS_STARTM":"2018","TOTALSCOR":1144,"UNUM":21},{"WOAS_STARTM":"2017","TOTALSCOR":1063,"UNUM":21},{"WOAS_STARTM":"2016","TOTALSCOR":1093,"UNUM":21}]
         */

        private AVGSCOREBean AVGSCORE;
        private WOASBean WOAS;
        private List<SCORERANKBean> SCORERANK;
        private List<RECAVGSCOREBean> RECAVGSCORE;

        public AVGSCOREBean getAVGSCORE() {
            return AVGSCORE;
        }

        public void setAVGSCORE(AVGSCOREBean AVGSCORE) {
            this.AVGSCORE = AVGSCORE;
        }

        public WOASBean getWOAS() {
            return WOAS;
        }

        public void setWOAS(WOASBean WOAS) {
            this.WOAS = WOAS;
        }

        public List<SCORERANKBean> getSCORERANK() {
            if (SCORERANK == null) {
                return new ArrayList<>();
            }
            return SCORERANK;
        }

        public void setSCORERANK(List<SCORERANKBean> SCORERANK) {
            this.SCORERANK = SCORERANK;
        }

        public List<RECAVGSCOREBean> getRECAVGSCORE() {
            if (RECAVGSCORE == null) {
                return new ArrayList<>();
            }
            return RECAVGSCORE;
        }

        public void setRECAVGSCORE(List<RECAVGSCOREBean> RECAVGSCORE) {
            this.RECAVGSCORE = RECAVGSCORE;
        }

        public static class AVGSCOREBean implements Serializable{
            /**
             * TOTALSCOR : 1144
             * UNUM : 21
             */

            private int TOTALSCOR;
            private int UNUM;

            public int getTOTALSCOR() {
                return TOTALSCOR;
            }

            public void setTOTALSCOR(int TOTALSCOR) {
                this.TOTALSCOR = TOTALSCOR;
            }

            public int getUNUM() {
                return UNUM;
            }

            public void setUNUM(int UNUM) {
                this.UNUM = UNUM;
            }
        }

        public static class WOASBean implements Serializable {
            /**
             * id : null
             * guid : cdeb414d059f4ab3b250b8c568920b07
             * woasThem : 2018安全检查
             * lareId : [2018000]字
             * woasType : 1
             * woasStartime : 2018-10-01 00:00:00
             * woasDeadline : 2018-12-31 00:00:00
             * sendStat : null
             * orgGuid : D7862390F88443AE87FA9DD1FE45A8B6
             * isForw : null
             * woasGuid : null
             * woasFileFiel : null
             * otheFileFiel : null
             * note : null
             * collTime : 2018-01-01 00:00:00
             * updTime : null
             * recPers : null
             * orgName : 中华人民共和国水利部
             * woasTypeName : 水利稽察考核
             * avgScor : null
             */

            private Object id;
            private String guid;
            private String woasThem;
            private String lareId;
            private String woasType;
            private String woasStartime;
            private String woasDeadline;
            private Object sendStat;
            private String orgGuid;
            private Object isForw;
            private Object woasGuid;
            private Object woasFileFiel;
            private Object otheFileFiel;
            private Object note;
            private String collTime;
            private Object updTime;
            private Object recPers;
            private String orgName;
            private String woasTypeName;
            private Object avgScor;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getGuid() {
                return guid == null ? "" : guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getWoasThem() {
                return woasThem == null ? "" : woasThem;
            }

            public void setWoasThem(String woasThem) {
                this.woasThem = woasThem;
            }

            public String getLareId() {
                return lareId == null ? "" : lareId;
            }

            public void setLareId(String lareId) {
                this.lareId = lareId;
            }

            public String getWoasType() {
                return woasType == null ? "" : woasType;
            }

            public void setWoasType(String woasType) {
                this.woasType = woasType;
            }

            public String getWoasStartime() {
                return woasStartime == null ? "" : woasStartime;
            }

            public void setWoasStartime(String woasStartime) {
                this.woasStartime = woasStartime;
            }

            public String getWoasDeadline() {
                return woasDeadline == null ? "" : woasDeadline;
            }

            public void setWoasDeadline(String woasDeadline) {
                this.woasDeadline = woasDeadline;
            }

            public Object getSendStat() {
                return sendStat;
            }

            public void setSendStat(Object sendStat) {
                this.sendStat = sendStat;
            }

            public String getOrgGuid() {
                return orgGuid == null ? "" : orgGuid;
            }

            public void setOrgGuid(String orgGuid) {
                this.orgGuid = orgGuid;
            }

            public Object getIsForw() {
                return isForw;
            }

            public void setIsForw(Object isForw) {
                this.isForw = isForw;
            }

            public Object getWoasGuid() {
                return woasGuid;
            }

            public void setWoasGuid(Object woasGuid) {
                this.woasGuid = woasGuid;
            }

            public Object getWoasFileFiel() {
                return woasFileFiel;
            }

            public void setWoasFileFiel(Object woasFileFiel) {
                this.woasFileFiel = woasFileFiel;
            }

            public Object getOtheFileFiel() {
                return otheFileFiel;
            }

            public void setOtheFileFiel(Object otheFileFiel) {
                this.otheFileFiel = otheFileFiel;
            }

            public Object getNote() {
                return note;
            }

            public void setNote(Object note) {
                this.note = note;
            }

            public String getCollTime() {
                return collTime == null ? "" : collTime;
            }

            public void setCollTime(String collTime) {
                this.collTime = collTime;
            }

            public Object getUpdTime() {
                return updTime;
            }

            public void setUpdTime(Object updTime) {
                this.updTime = updTime;
            }

            public Object getRecPers() {
                return recPers;
            }

            public void setRecPers(Object recPers) {
                this.recPers = recPers;
            }

            public String getOrgName() {
                return orgName == null ? "" : orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getWoasTypeName() {
                return woasTypeName == null ? "" : woasTypeName;
            }

            public void setWoasTypeName(String woasTypeName) {
                this.woasTypeName = woasTypeName;
            }

            public Object getAvgScor() {
                return avgScor;
            }

            public void setAvgScor(Object avgScor) {
                this.avgScor = avgScor;
            }
        }

        public static class SCORERANKBean implements Serializable {
            /**
             * BFINALSCOR : 83
             * AWOASWIUNGUID : 23C5E698A37247BA8D74622D81E2B037
             * ARANKNUM : 1
             * BRANKNUM : 5
             * BGRADSTAT : 2
             * AGRADSTAT : 1
             * AFINALSCOR : 95
             * ORGLAT : 39.9196397
             * ORGLONG : 116.35741442
             * ORGNAME : 中国水利报社
             * ADCODE : 000000
             */

            private int BFINALSCOR;
            private String AWOASWIUNGUID;
            private int ARANKNUM;
            private int BRANKNUM;
            private String BGRADSTAT;
            private String AGRADSTAT;
            private int AFINALSCOR;
            private double ORGLAT;
            private double ORGLONG;
            private String ORGNAME;
            private String ADCODE;

            public int getBFINALSCOR() {
                return BFINALSCOR;
            }

            public void setBFINALSCOR(int BFINALSCOR) {
                this.BFINALSCOR = BFINALSCOR;
            }

            public String getAWOASWIUNGUID() {
                return AWOASWIUNGUID == null ? "" : AWOASWIUNGUID;
            }

            public void setAWOASWIUNGUID(String AWOASWIUNGUID) {
                this.AWOASWIUNGUID = AWOASWIUNGUID;
            }

            public int getARANKNUM() {
                return ARANKNUM;
            }

            public void setARANKNUM(int ARANKNUM) {
                this.ARANKNUM = ARANKNUM;
            }

            public int getBRANKNUM() {
                return BRANKNUM;
            }

            public void setBRANKNUM(int BRANKNUM) {
                this.BRANKNUM = BRANKNUM;
            }

            public String getBGRADSTAT() {
                return BGRADSTAT == null ? "" : BGRADSTAT;
            }

            public void setBGRADSTAT(String BGRADSTAT) {
                this.BGRADSTAT = BGRADSTAT;
            }

            public String getAGRADSTAT() {
                return AGRADSTAT == null ? "" : AGRADSTAT;
            }

            public void setAGRADSTAT(String AGRADSTAT) {
                this.AGRADSTAT = AGRADSTAT;
            }

            public int getAFINALSCOR() {
                return AFINALSCOR;
            }

            public void setAFINALSCOR(int AFINALSCOR) {
                this.AFINALSCOR = AFINALSCOR;
            }

            public double getORGLAT() {
                return ORGLAT;
            }

            public void setORGLAT(double ORGLAT) {
                this.ORGLAT = ORGLAT;
            }

            public double getORGLONG() {
                return ORGLONG;
            }

            public void setORGLONG(double ORGLONG) {
                this.ORGLONG = ORGLONG;
            }

            public String getORGNAME() {
                return ORGNAME == null ? "" : ORGNAME;
            }

            public void setORGNAME(String ORGNAME) {
                this.ORGNAME = ORGNAME;
            }

            public String getADCODE() {
                return ADCODE == null ? "" : ADCODE;
            }

            public void setADCODE(String ADCODE) {
                this.ADCODE = ADCODE;
            }
        }

        public static class RECAVGSCOREBean implements Serializable {
            /**
             * WOAS_STARTM : 2018
             * TOTALSCOR : 1144
             * UNUM : 21
             */

            private String WOAS_STARTM;
            private int TOTALSCOR;
            private int UNUM;

            public String getWOAS_STARTM() {
                return WOAS_STARTM == null ? "" : WOAS_STARTM;
            }

            public void setWOAS_STARTM(String WOAS_STARTM) {
                this.WOAS_STARTM = WOAS_STARTM;
            }

            public int getTOTALSCOR() {
                return TOTALSCOR;
            }

            public void setTOTALSCOR(int TOTALSCOR) {
                this.TOTALSCOR = TOTALSCOR;
            }

            public int getUNUM() {
                return UNUM;
            }

            public void setUNUM(int UNUM) {
                this.UNUM = UNUM;
            }
        }
    }
}
