package com.syberos.shuili.entity.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public class CheckRoteItem implements Serializable {
    /**
     * message : 查询成功
     * pageIndex :
     * responseSts : 0
     * resultInfoList : [{"guid":"11111111","checktime":"2018-01-01","statime":"2018-01-01 00:00:00","endtime":"2018-01-01 00:00:00","geo":"{\"type\":\"LineString\",\"coordinates\":[[114.42437474301,30.60709472432],[113.77873556293,34.759229070912],[116.32152879234,39.895030431052]]}","type":null}]
     * totalCount :
     * totalPage :
     */

    private String message;
    private String pageIndex;
    private int responseSts;
    private String totalCount;
    private String totalPage;
    private List<ResultInfoListBean> resultInfoList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getResponseSts() {
        return responseSts;
    }

    public void setResponseSts(int responseSts) {
        this.responseSts = responseSts;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<ResultInfoListBean> getResultInfoList() {
        return resultInfoList;
    }

    public void setResultInfoList(List<ResultInfoListBean> resultInfoList) {
        this.resultInfoList = resultInfoList;
    }

    public static class ResultInfoListBean implements Serializable {
        /**
         * guid : 11111111
         * checktime : 2018-01-01
         * statime : 2018-01-01 00:00:00
         * endtime : 2018-01-01 00:00:00
         * geo : {"type":"LineString","coordinates":[[114.42437474301,30.60709472432],[113.77873556293,34.759229070912],[116.32152879234,39.895030431052]]}
         * type : null
         */

        private String guid;
        private String checktime;
        private String statime;
        private String endtime;
        private String geo;
        private String type;

        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getChecktime() {
            return checktime == null ? "" : checktime;
        }

        public void setChecktime(String checktime) {
            this.checktime = checktime;
        }

        public String getStatime() {
            return statime == null ? "" : statime;
        }

        public void setStatime(String statime) {
            this.statime = statime;
        }

        public String getEndtime() {
            return endtime == null ? "" : endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getGeo() {
            return geo == null ? "" : geo;
        }

        public void setGeo(String geo) {
            this.geo = geo;
        }

        public String getType() {
            return type == null ? "" : type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
