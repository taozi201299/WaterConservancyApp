package com.syberos.shuili.entity.userinfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/20.
 */

public class ModuleBean implements Serializable {
    /**
     * message : 请求成功
     * status : REQ001
     * data : {}
     */

    private String message;
    private String status;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * code : 0
         * msg : 查询成功
         * totalCount : 25
         * data : []
         * detailMsg : null
         */

        private String code;
        private String msg;
        private int totalCount;
        private Object detailMsg;
        private List<ModuleInfoBean> data;

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

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public Object getDetailMsg() {
            return detailMsg;
        }

        public void setDetailMsg(Object detailMsg) {
            this.detailMsg = detailMsg;
        }

        public List<ModuleInfoBean> getData() {
            return data;
        }

        public void setData(List<ModuleInfoBean> data) {
            this.data = data;
        }
    }
    public static class ModuleInfoBean implements Serializable{

        /**
         * url : src/bll/allpages/legalproject/homepage/waterhome.html
         * p_FUNC_CODE : null
         * func_CODE : HOME
         * func_NAME : 项目法人单位管理员
         * remark : null
         * app_GUID : cjfr
         * from_DATE : 1521007728000
         * to_DATE : null
         * guid : 31A2A6F330D749BAB7AC85E10796EC05
         */

        private String url;
        private Object p_FUNC_CODE;
        private String func_CODE;
        private String func_NAME;
        private Object remark;
        private String app_GUID;
        private long from_DATE;
        private Object to_DATE;
        private String guid;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getP_FUNC_CODE() {
            return p_FUNC_CODE;
        }

        public void setP_FUNC_CODE(Object p_FUNC_CODE) {
            this.p_FUNC_CODE = p_FUNC_CODE;
        }

        public String getFunc_CODE() {
            return func_CODE;
        }

        public void setFunc_CODE(String func_CODE) {
            this.func_CODE = func_CODE;
        }

        public String getFunc_NAME() {
            return func_NAME;
        }

        public void setFunc_NAME(String func_NAME) {
            this.func_NAME = func_NAME;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getApp_GUID() {
            return app_GUID;
        }

        public void setApp_GUID(String app_GUID) {
            this.app_GUID = app_GUID;
        }

        public long getFrom_DATE() {
            return from_DATE;
        }

        public void setFrom_DATE(long from_DATE) {
            this.from_DATE = from_DATE;
        }

        public Object getTo_DATE() {
            return to_DATE;
        }

        public void setTo_DATE(Object to_DATE) {
            this.to_DATE = to_DATE;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }
    }
}
