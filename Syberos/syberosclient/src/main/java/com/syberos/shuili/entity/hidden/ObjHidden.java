package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/23.
 */

public class ObjHidden extends HttpBaseResponse<ObjHidden> {
    String guid ;
     String hiddName;
     String engGuid;
     String tendGuid;
     String seGuid;
     String orgGuid;
     String hiddSour;
     String hiddGrad;
     String hiddClas;
     String ifFound;
     String proPart;
     String partLong;
     String partLat;
     String hiddDesc;
     String inspRecGuid;
     String seCheckItemGuid;
     String hiddStat;
     String hiddMergGuid;
     String recOrgGuid;
     String note;
     String collTime;
     String updTime;
     String recPers;
     String hiddsGuid;
     String occuNum;
    String engName;
    String hiddGradName;
    String hiddClassName;
    boolean isAccept;

    public boolean isbExist() {
        return bExist;
    }

    public void setbExist(boolean bExist) {
        this.bExist = bExist;
    }

    boolean bExist;

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }



    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHiddName() {
        return hiddName;
    }

    public void setHiddName(String hiddName) {
        this.hiddName = hiddName;
    }

    public String getEngGuid() {
        return engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getTendGuid() {
        return tendGuid;
    }

    public void setTendGuid(String tendGuid) {
        this.tendGuid = tendGuid;
    }

    public String getSeGuid() {
        return seGuid;
    }

    public void setSeGuid(String seGuid) {
        this.seGuid = seGuid;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getHiddSour() {
        return hiddSour;
    }

    public void setHiddSour(String hiddSour) {
        this.hiddSour = hiddSour;
    }

    public String getHiddGrad() {
        return hiddGrad;
    }

    public void setHiddGrad(String hiddGrad) {
        this.hiddGrad = hiddGrad;
    }

    public String getHiddClas() {
        return hiddClas;
    }

    public void setHiddClas(String hiddClas) {
        this.hiddClas = hiddClas;
    }

    public String getIfFound() {
        return ifFound;
    }

    public void setIfFound(String ifFound) {
        this.ifFound = ifFound;
    }

    public String getProPart() {
        return proPart;
    }

    public void setProPart(String proPart) {
        this.proPart = proPart;
    }

    public String getPartLong() {
        return partLong;
    }

    public void setPartLong(String partLong) {
        this.partLong = partLong;
    }

    public String getPartLat() {
        return partLat;
    }

    public void setPartLat(String partLat) {
        this.partLat = partLat;
    }

    public String getHiddDesc() {
        return hiddDesc;
    }

    public void setHiddDesc(String hiddDesc) {
        this.hiddDesc = hiddDesc;
    }

    public String getInspRecGuid() {
        return inspRecGuid;
    }

    public void setInspRecGuid(String inspRecGuid) {
        this.inspRecGuid = inspRecGuid;
    }

    public String getSeCheckItemGuid() {
        return seCheckItemGuid;
    }

    public void setSeCheckItemGuid(String seCheckItemGuid) {
        this.seCheckItemGuid = seCheckItemGuid;
    }

    public String getHiddStat() {
        return hiddStat;
    }

    public void setHiddStat(String hiddStat) {
        this.hiddStat = hiddStat;
    }

    public String getHiddMergGuid() {
        return hiddMergGuid;
    }

    public void setHiddMergGuid(String hiddMergGuid) {
        this.hiddMergGuid = hiddMergGuid;
    }

    public String getRecOrgGuid() {
        return recOrgGuid;
    }

    public void setRecOrgGuid(String recOrgGuid) {
        this.recOrgGuid = recOrgGuid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getRecPers() {
        return recPers;
    }

    public void setRecPers(String recPers) {
        this.recPers = recPers;
    }

    public String getHiddsGuid() {
        return hiddsGuid;
    }

    public void setHiddsGuid(String hiddsGuid) {
        this.hiddsGuid = hiddsGuid;
    }

    public String getOccuNum() {
        return occuNum;
    }

    public void setOccuNum(String occuNum) {
        this.occuNum = occuNum;
    }

    public String getHiddGradName() {
        return hiddGradName;
    }

    public void setHiddGradName(String hiddGradName) {
        this.hiddGradName = hiddGradName;
    }

    public String getHiddClassName() {
        return hiddClassName;
    }

    public void setHiddClassName(String hiddClassName) {
        this.hiddClassName = hiddClassName;
    }
}
