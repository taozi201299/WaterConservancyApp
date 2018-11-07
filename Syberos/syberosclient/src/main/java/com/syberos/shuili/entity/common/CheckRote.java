package com.syberos.shuili.entity.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/6.
 */

public class CheckRote implements Serializable {
    /**
     * requestData : {"type":"bis","objInfoList":[{"guid":"11111111","checkTime":"2018-01-01","staTime":"2018-01-01 00:00:00","endTime":"2018-01-01 00:00:00","geo":" {\"type\":\"LineString\",\"coordinates\":[[114.42437474301,30.60709472432],[113.77873556293,34.759229070912],[116.32152879234,39.895030431052]]}","geoType":1}],"targetId":"update.InsertBisSinLogic"}
     */

    private RequestDataBean requestData;

    public RequestDataBean getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestDataBean requestData) {
        this.requestData = requestData;
    }

    public static class RequestDataBean implements Serializable {
        /**
         * type : bis
         * objInfoList : [{"guid":"11111111","checkTime":"2018-01-01","staTime":"2018-01-01 00:00:00","endTime":"2018-01-01 00:00:00","geo":" {\"type\":\"LineString\",\"coordinates\":[[114.42437474301,30.60709472432],[113.77873556293,34.759229070912],[116.32152879234,39.895030431052]]}","geoType":1}]
         * targetId : update.InsertBisSinLogic
         */

        private String type;
        private String targetId;
        private List<ObjInfoListBean> objInfoList;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public List<ObjInfoListBean> getObjInfoList() {
            return objInfoList;
        }

        public void setObjInfoList(List<ObjInfoListBean> objInfoList) {
            this.objInfoList = objInfoList;
        }

        public static class ObjInfoListBean {
            /**
             * guid : 11111111
             * checkTime : 2018-01-01
             * staTime : 2018-01-01 00:00:00
             * endTime : 2018-01-01 00:00:00
             * geo :  {"type":"LineString","coordinates":[[114.42437474301,30.60709472432],[113.77873556293,34.759229070912],[116.32152879234,39.895030431052]]}
             * geoType : 1
             */

            private String guid;
            private String checkTime;
            private String staTime;
            private String endTime;
            private String geo;
            private int geoType;

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getCheckTime() {
                return checkTime;
            }

            public void setCheckTime(String checkTime) {
                this.checkTime = checkTime;
            }

            public String getStaTime() {
                return staTime;
            }

            public void setStaTime(String staTime) {
                this.staTime = staTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getGeo() {
                return geo;
            }

            public void setGeo(String geo) {
                this.geo = geo;
            }

            public int getGeoType() {
                return geoType;
            }

            public void setGeoType(int geoType) {
                this.geoType = geoType;
            }
        }
    }
}
