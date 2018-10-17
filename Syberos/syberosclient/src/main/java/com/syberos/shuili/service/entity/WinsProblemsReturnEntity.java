package com.syberos.shuili.service.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/17.
 */

public class WinsProblemsReturnEntity implements Serializable{
    /**
     * code : 0
     * msg : 数据添加成功
     * data : {"id":null,"currentPage":0,"pageSize":0,"date_start":null,"date_end":null,"guid":"1b1f2e4aea4f47ec801946d7eea83410","note":"客户端测试数据","collTime":"2018-10-17 18:15:55","updTime":null,"recPers":null,"probType":"1","probCate":"1","probDep":null,"probDesc":"aa","rectSugg":"aa","rectConc":null,"unrecReson":null,"rectMeas":null,"rectLed":null,"isRect":null,"trackPeop":null,"winsProjGuid":"102c4a87458e451ca41fe0d6139a2d02"}
     * totalCount : null
     * detailMsg : null
     */

    private String code;
    private String msg;
    private DataBean data;
    private Object totalCount;
    private Object detailMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Object totalCount) {
        this.totalCount = totalCount;
    }

    public Object getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(Object detailMsg) {
        this.detailMsg = detailMsg;
    }

    public static class DataBean implements Serializable {
        /**
         * id : null
         * currentPage : 0
         * pageSize : 0
         * date_start : null
         * date_end : null
         * guid : 1b1f2e4aea4f47ec801946d7eea83410
         * note : 客户端测试数据
         * collTime : 2018-10-17 18:15:55
         * updTime : null
         * recPers : null
         * probType : 1
         * probCate : 1
         * probDep : null
         * probDesc : aa
         * rectSugg : aa
         * rectConc : null
         * unrecReson : null
         * rectMeas : null
         * rectLed : null
         * isRect : null
         * trackPeop : null
         * winsProjGuid : 102c4a87458e451ca41fe0d6139a2d02
         */

        private String id;
        private int currentPage;
        private int pageSize;
        private String date_start;
        private String date_end;
        private String guid;
        private String note;
        private String collTime;
        private String updTime;
        private String recPers;
        private String probType;
        private String probCate;
        private String probDep;
        private String probDesc;
        private String rectSugg;
        private String rectConc;
        private String unrecReson;
        private String rectMeas;
        private String rectLed;
        private String isRect;
        private String trackPeop;
        private String winsProjGuid;


        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getDate_start() {
            return date_start == null ? "" : date_start;
        }

        public void setDate_start(String date_start) {
            this.date_start = date_start;
        }

        public String getDate_end() {
            return date_end == null ? "" : date_end;
        }

        public void setDate_end(String date_end) {
            this.date_end = date_end;
        }

        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getNote() {
            return note == null ? "" : note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getCollTime() {
            return collTime == null ? "" : collTime;
        }

        public void setCollTime(String collTime) {
            this.collTime = collTime;
        }

        public String getUpdTime() {
            return updTime == null ? "" : updTime;
        }

        public void setUpdTime(String updTime) {
            this.updTime = updTime;
        }

        public String getRecPers() {
            return recPers == null ? "" : recPers;
        }

        public void setRecPers(String recPers) {
            this.recPers = recPers;
        }

        public String getProbType() {
            return probType == null ? "" : probType;
        }

        public void setProbType(String probType) {
            this.probType = probType;
        }

        public String getProbCate() {
            return probCate == null ? "" : probCate;
        }

        public void setProbCate(String probCate) {
            this.probCate = probCate;
        }

        public String getProbDep() {
            return probDep == null ? "" : probDep;
        }

        public void setProbDep(String probDep) {
            this.probDep = probDep;
        }

        public String getProbDesc() {
            return probDesc == null ? "" : probDesc;
        }

        public void setProbDesc(String probDesc) {
            this.probDesc = probDesc;
        }

        public String getRectSugg() {
            return rectSugg == null ? "" : rectSugg;
        }

        public void setRectSugg(String rectSugg) {
            this.rectSugg = rectSugg;
        }

        public String getRectConc() {
            return rectConc == null ? "" : rectConc;
        }

        public void setRectConc(String rectConc) {
            this.rectConc = rectConc;
        }

        public String getUnrecReson() {
            return unrecReson == null ? "" : unrecReson;
        }

        public void setUnrecReson(String unrecReson) {
            this.unrecReson = unrecReson;
        }

        public String getRectMeas() {
            return rectMeas == null ? "" : rectMeas;
        }

        public void setRectMeas(String rectMeas) {
            this.rectMeas = rectMeas;
        }

        public String getRectLed() {
            return rectLed == null ? "" : rectLed;
        }

        public void setRectLed(String rectLed) {
            this.rectLed = rectLed;
        }

        public String getIsRect() {
            return isRect == null ? "" : isRect;
        }

        public void setIsRect(String isRect) {
            this.isRect = isRect;
        }

        public String getTrackPeop() {
            return trackPeop == null ? "" : trackPeop;
        }

        public void setTrackPeop(String trackPeop) {
            this.trackPeop = trackPeop;
        }

        public String getWinsProjGuid() {
            return winsProjGuid == null ? "" : winsProjGuid;
        }

        public void setWinsProjGuid(String winsProjGuid) {
            this.winsProjGuid = winsProjGuid;
        }
    }
}
