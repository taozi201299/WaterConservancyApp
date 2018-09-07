package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-23 10:27
 * email：zhaodongshuang@syberos.com
 * 工作考核：现场检查:扣分信息
 */
public class DeductMarksInfo extends HttpBaseResponse<DeductMarksInfo> {

    private String guid;
    private String woasWiunGuid;
    private String woasGuid;
    private String woasGropGuid;
    private int fianDeuc;
    private String deucNote;
    private String woasType;
    private String note;
    private String collTime;
    private Object updTime;
    private String recPers;


    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public int getFianDeuc() {
        return fianDeuc;
    }

    public void setFianDeuc(int fianDeuc) {
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

    public Object getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Object updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers == null ? "" : recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }
}
