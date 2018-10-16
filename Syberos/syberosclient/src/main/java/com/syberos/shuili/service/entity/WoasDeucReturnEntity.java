package com.syberos.shuili.service.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/16.
 */

public class WoasDeucReturnEntity implements Serializable {
    /**
     * code : 0
     * msg : 数据添加成功
     * data : {"id":null,"currentPage":0,"pageSize":0,"date_start":null,"date_end":null,"guid":"543f192294f04cd392ad97979e96e5de","note":"APP Client","collTime":"2018-10-16 14:14:17","updTime":null,"recPers":"f3f154bd87ce4aa6aaacf996fe5b2477","woasWiunGuid":"6e37106cb84948298c0d611819033dd9","woasGuid":"4fc9e3fe13ef4852a07be52ceaaa6cf9","woasGropGuid":"0f56a78ade4c472dba2e9dce16adc4fc","fianDeuc":"10","deucNote":null,"woasType":"2"}
     * totalCount : null
     * detailMsg : null
     */

    private String code;
    private String msg;
    private DataBean data;
    private String totalCount;
    private String detailMsg;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
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

    public String getTotalCount() {
        return totalCount == null ? "" : totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getDetailMsg() {
        return detailMsg == null ? "" : detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }


    public static class DataBean implements Serializable{
        /**
         * id : null
         * currentPage : 0
         * pageSize : 0
         * date_start : null
         * date_end : null
         * guid : 543f192294f04cd392ad97979e96e5de
         * note : APP Client
         * collTime : 2018-10-16 14:14:17
         * updTime : null
         * recPers : f3f154bd87ce4aa6aaacf996fe5b2477
         * woasWiunGuid : 6e37106cb84948298c0d611819033dd9
         * woasGuid : 4fc9e3fe13ef4852a07be52ceaaa6cf9
         * woasGropGuid : 0f56a78ade4c472dba2e9dce16adc4fc
         * fianDeuc : 10
         * deucNote : null
         * woasType : 2
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
        private String woasWiunGuid;
        private String woasGuid;
        private String woasGropGuid;
        private String fianDeuc;
        private String deucNote;
        private String woasType;

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

        public String getWoasWiunGuid() {
            return woasWiunGuid == null ? "" : woasWiunGuid;
        }

        public void setWoasWiunGuid(String woasWiunGuid) {
            this.woasWiunGuid = woasWiunGuid;
        }

        public String getWoasGuid() {
            return woasGuid == null ? "" : woasGuid;
        }

        public void setWoasGuid(String woasGuid) {
            this.woasGuid = woasGuid;
        }

        public String getWoasGropGuid() {
            return woasGropGuid == null ? "" : woasGropGuid;
        }

        public void setWoasGropGuid(String woasGropGuid) {
            this.woasGropGuid = woasGropGuid;
        }

        public String getFianDeuc() {
            return fianDeuc == null ? "" : fianDeuc;
        }

        public void setFianDeuc(String fianDeuc) {
            this.fianDeuc = fianDeuc;
        }

        public String getDeucNote() {
            return deucNote == null ? "" : deucNote;
        }

        public void setDeucNote(String deucNote) {
            this.deucNote = deucNote;
        }

        public String getWoasType() {
            return woasType == null ? "" : woasType;
        }

        public void setWoasType(String woasType) {
            this.woasType = woasType;
        }
    }
}
