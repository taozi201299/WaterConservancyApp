package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/9/6.
 */

public class BisWinsStaff extends HttpBaseResponse<BisWinsStaff>{

    private String guid;
    private String persGuid;
    private String persExpertGuid;
    private String winsGroupGuid;
    private String winsPost;
    private String note;
    private String collTime;
    private String updTime;
    private String recPers;
    private String persExpertName;
    private String persExpertPost;
    private String citNum;


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

    public String getCitNum() {
        return citNum == null ? "" : citNum;
    }

    public void setCitNum(String citNum) {
        this.citNum = citNum;
    }
}
