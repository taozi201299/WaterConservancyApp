package com.syberos.shuili.entity.basicbusiness;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/6/6.
 */

public class MvEngColl extends HttpBaseResponse<MvEngColl> {
    String fromdate ;
    String engId;
    String besId;
    String attId;
    String id;
    String engcode;
    String name; // 工程名称
    String engstat; // 工程建设情况
    String engtype; // 工程类型
    String engTypeName;
    String charperstel;
    String stat;   // 状态
    String bisengsareid;
    String mixobjid;
    String orgguid;
    String adguid;
    String marker;
    String impograd; //重要性
    String startdate; // 开工时间
    String compdate; // 建成时间
    String oflpcode; // 所属水行政管理部门

    public String getFromdate() {
        return fromdate == null ? "" : fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getEngId() {
        return engId == null ? "" : engId;
    }

    public void setEngId(String engId) {
        this.engId = engId;
    }

    public String getBesId() {
        return besId == null ? "" : besId;
    }

    public void setBesId(String besId) {
        this.besId = besId;
    }

    public String getAttId() {
        return attId == null ? "" : attId;
    }

    public void setAttId(String attId) {
        this.attId = attId;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngcode() {
        return engcode == null ? "" : engcode;
    }

    public void setEngcode(String engcode) {
        this.engcode = engcode;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngstat() {
        return engstat == null ? "" : engstat;
    }

    public void setEngstat(String engstat) {
        this.engstat = engstat;
    }

    public String getEngtype() {
        return engtype == null ? "" : engtype;
    }

    public void setEngtype(String engtype) {
        this.engtype = engtype;
    }

    public String getEngTypeName() {
        return engTypeName == null ? "" : engTypeName;
    }

    public void setEngTypeName(String engTypeName) {
        this.engTypeName = engTypeName;
    }

    public String getCharperstel() {
        return charperstel == null ? "" : charperstel;
    }

    public void setCharperstel(String charperstel) {
        this.charperstel = charperstel;
    }

    public String getStat() {
        return stat == null ? "" : stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getBisengsareid() {
        return bisengsareid == null ? "" : bisengsareid;
    }

    public void setBisengsareid(String bisengsareid) {
        this.bisengsareid = bisengsareid;
    }

    public String getMixobjid() {
        return mixobjid == null ? "" : mixobjid;
    }

    public void setMixobjid(String mixobjid) {
        this.mixobjid = mixobjid;
    }

    public String getOrgguid() {
        return orgguid == null ? "" : orgguid;
    }

    public void setOrgguid(String orgguid) {
        this.orgguid = orgguid;
    }

    public String getAdguid() {
        return adguid == null ? "" : adguid;
    }

    public void setAdguid(String adguid) {
        this.adguid = adguid;
    }

    public String getMarker() {
        return marker == null ? "" : marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getImpograd() {
        return impograd == null ? "" : impograd;
    }

    public void setImpograd(String impograd) {
        this.impograd = impograd;
    }

    public String getStartdate() {
        return startdate == null ? "" : startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getCompdate() {
        return compdate == null ? "" : compdate;
    }

    public void setCompdate(String compdate) {
        this.compdate = compdate;
    }

    public String getOflpcode() {
        return oflpcode == null ? "" : oflpcode;
    }

    public void setOflpcode(String oflpcode) {
        this.oflpcode = oflpcode;
    }
}
