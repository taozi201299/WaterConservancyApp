package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 稽查地区
 */

public class BisWinsRegi extends HttpBaseResponse<BisWinsRegi> {
    String guid;
    String orgGuid;
    String areaName;
    String winsProgGuid;
    String winsGroupGuid;
    String note;
    String collTime;
    String updTime;
    String recPers;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getAreaName() {
        return areaName == null ? "" : areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWinsProgGuid() {
        return winsProgGuid == null ? "" : winsProgGuid;
    }

    public void setWinsProgGuid(String winsProgGuid) {
        this.winsProgGuid = winsProgGuid;
    }

    public String getWinsGroupGuid() {
        return winsGroupGuid == null ? "" : winsGroupGuid;
    }

    public void setWinsGroupGuid(String winsGroupGuid) {
        this.winsGroupGuid = winsGroupGuid;
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


    /**
     * Created by Administrator on 2018/4/30.
     * 稽查人员表
     */

    public static class BisWinsStaff extends HttpBaseResponse<BisWinsStaff> {
        String guid;
        /**
         * 人员GUID
         */
        String persGuid;
        /**
         * 专家GUID
         */
        String persExpertGuid;
        /**
         * 所在稽察组GUID
         */
        String winsGroupGuid;
        /**
         * 稽察岗位
         */
        String winsPost;
        /**
         * 专家姓名
         */
        String persExpertName;
        /**
         * 专家领域
         */
        String persExpertPost;
        String note;
        String collTime;
        String updTime;
        String recPers;


        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getPersGuid() {
            return persGuid == null ? "" : persGuid;
        }

        public void setPersGuid(String persGuid) {
            this.persGuid = persGuid;
        }

        public String getPersExpertGuid() {
            return persExpertGuid == null ? "" : persExpertGuid;
        }

        public void setPersExpertGuid(String persExpertGuid) {
            this.persExpertGuid = persExpertGuid;
        }

        public String getWinsGroupGuid() {
            return winsGroupGuid == null ? "" : winsGroupGuid;
        }

        public void setWinsGroupGuid(String winsGroupGuid) {
            this.winsGroupGuid = winsGroupGuid;
        }

        public String getWinsPost() {
            return winsPost == null ? "" : winsPost;
        }

        public void setWinsPost(String winsPost) {
            this.winsPost = winsPost;
        }

        public String getPersExpertName() {
            return persExpertName == null ? "" : persExpertName;
        }

        public void setPersExpertName(String persExpertName) {
            this.persExpertName = persExpertName;
        }

        public String getPersExpertPost() {
            return persExpertPost == null ? "" : persExpertPost;
        }

        public void setPersExpertPost(String persExpertPost) {
            this.persExpertPost = persExpertPost;
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
    }
}
