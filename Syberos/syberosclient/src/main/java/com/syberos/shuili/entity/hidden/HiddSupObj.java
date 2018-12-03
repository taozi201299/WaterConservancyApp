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
        private String recPers;
        private String supWiunCode;
        private String collTime;
        private String orgName;
        private String supLegPers;
        private String guid;
        private String updTime;
        private String supOpin;
        private String hiddGuid;
        private String supLareId;
        private String rectPeri;
        private String supDate;
        private String note;


        public String getIsList() {
            return isList == null ? "" : isList;
        }

        public void setIsList(String isList) {
            this.isList = isList;
        }

        public String getRecPers() {
            return recPers == null ? "" : recPers;
        }

        public void setRecPers(String recPers) {
            this.recPers = recPers;
        }

        public String getSupWiunCode() {
            return supWiunCode == null ? "" : supWiunCode;
        }

        public void setSupWiunCode(String supWiunCode) {
            this.supWiunCode = supWiunCode;
        }

        public String getCollTime() {
            return collTime == null ? "" : collTime;
        }

        public void setCollTime(String collTime) {
            this.collTime = collTime;
        }

        public String getOrgName() {
            return orgName == null ? "" : orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getSupLegPers() {
            return supLegPers == null ? "" : supLegPers;
        }

        public void setSupLegPers(String supLegPers) {
            this.supLegPers = supLegPers;
        }

        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getUpdTime() {
            return updTime == null ? "" : updTime;
        }

        public void setUpdTime(String updTime) {
            this.updTime = updTime;
        }

        public String getSupOpin() {
            return supOpin == null ? "" : supOpin;
        }

        public void setSupOpin(String supOpin) {
            this.supOpin = supOpin;
        }

        public String getHiddGuid() {
            return hiddGuid == null ? "" : hiddGuid;
        }

        public void setHiddGuid(String hiddGuid) {
            this.hiddGuid = hiddGuid;
        }

        public String getSupLareId() {
            return supLareId == null ? "" : supLareId;
        }

        public void setSupLareId(String supLareId) {
            this.supLareId = supLareId;
        }

        public String getRectPeri() {
            return rectPeri == null ? "" : rectPeri;
        }

        public void setRectPeri(String rectPeri) {
            this.rectPeri = rectPeri;
        }

        public String getSupDate() {
            return supDate == null ? "" : supDate;
        }

        public void setSupDate(String supDate) {
            this.supDate = supDate;
        }

        public String getNote() {
            return note == null ? "" : note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
