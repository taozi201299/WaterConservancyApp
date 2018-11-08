package com.syberos.shuili.entity.hidden;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/8.
 */

public class HiddSupObj implements Serializable {
    /**
     * code : 0
     * msg : 请求正常返回
     * totalCount : 1
     * data : [{"isList":"1","recPers":null,"supWiunCode":null,"collTime":"2018-08-28 17:53:55","orgName":null,"supLegPers":null,"guid":"6A0460BA24E0413A88B097D9E0BA98F8","updTime":null,"supOpin":null,"hiddGuid":"F727CD913CC24FAE9E729CCF677ADA1A","supLareId":null,"rectPeri":"7/25/2018","supDate":null,"note":null}]
     */

    private int code;
    private String msg;
    private int totalCount;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * isList : 1
         * recPers : null
         * supWiunCode : null
         * collTime : 2018-08-28 17:53:55
         * orgName : null
         * supLegPers : null
         * guid : 6A0460BA24E0413A88B097D9E0BA98F8
         * updTime : null
         * supOpin : null
         * hiddGuid : F727CD913CC24FAE9E729CCF677ADA1A
         * supLareId : null
         * rectPeri : 7/25/2018
         * supDate : null
         * note : null
         */

        private String isList;
        private Object recPers;
        private Object supWiunCode;
        private String collTime;
        private Object orgName;
        private Object supLegPers;
        private String guid;
        private Object updTime;
        private Object supOpin;
        private String hiddGuid;
        private Object supLareId;
        private String rectPeri;
        private Object supDate;
        private Object note;

        public String getIsList() {
            return isList;
        }

        public void setIsList(String isList) {
            this.isList = isList;
        }

        public Object getRecPers() {
            return recPers;
        }

        public void setRecPers(Object recPers) {
            this.recPers = recPers;
        }

        public Object getSupWiunCode() {
            return supWiunCode;
        }

        public void setSupWiunCode(Object supWiunCode) {
            this.supWiunCode = supWiunCode;
        }

        public String getCollTime() {
            return collTime;
        }

        public void setCollTime(String collTime) {
            this.collTime = collTime;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
            this.orgName = orgName;
        }

        public Object getSupLegPers() {
            return supLegPers;
        }

        public void setSupLegPers(Object supLegPers) {
            this.supLegPers = supLegPers;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public Object getUpdTime() {
            return updTime;
        }

        public void setUpdTime(Object updTime) {
            this.updTime = updTime;
        }

        public Object getSupOpin() {
            return supOpin;
        }

        public void setSupOpin(Object supOpin) {
            this.supOpin = supOpin;
        }

        public String getHiddGuid() {
            return hiddGuid;
        }

        public void setHiddGuid(String hiddGuid) {
            this.hiddGuid = hiddGuid;
        }

        public Object getSupLareId() {
            return supLareId;
        }

        public void setSupLareId(Object supLareId) {
            this.supLareId = supLareId;
        }

        public String getRectPeri() {
            return rectPeri;
        }

        public void setRectPeri(String rectPeri) {
            this.rectPeri = rectPeri;
        }

        public Object getSupDate() {
            return supDate;
        }

        public void setSupDate(Object supDate) {
            this.supDate = supDate;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }
    }
}
