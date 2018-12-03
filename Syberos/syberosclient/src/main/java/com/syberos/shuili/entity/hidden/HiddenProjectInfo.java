package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/12.
 */

public class HiddenProjectInfo extends HttpBaseResponse<HiddenProjectInfo> {

    public final static int LEVEL_NORMAL = 0;
    public final static int LEVEL_BIG = 1;

    String username;
    String timestamp;
    String nonce;
    String sign;
    String guid;
    /**
     * 隐患名称
     */
    String hiddName;
    /**
     * 所属工程
     */
    String engGuid;

    public String getEngName() {
        return engName == null ? "" : engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    String engName;
    /**
     * 所属标段
     */
    String tendGuid;
    /**
     * 所属元素
     */
    String seGuid;
    /**
     * 隐患发生单位
     */
    String orgGuid;
    /**
     * 隐患来源
     */
    String hiddSour;
    /**
     * 隐患级别
     */
    String hiddGrad;
    /**
     * 隐患分类
     */
    String hiddClas;

    public String getHiddClasName() {
        return hiddClasName == null ? "" : hiddClasName;
    }

    public void setHiddClasName(String hiddClasName) {
        this.hiddClasName = hiddClasName;
    }

    String hiddClasName;
    /**
     * 是否已经被发现
     */
    String ifFound;
    /**
     * 涉及部位
     */
    String proPart;
    /**
     * 部位经度
     */
    String partLong;
    /**
     * 部位纬度
     */
    String partLat;
    /**
     * 隐患描述
     */
    String hiddDesc;
    /**
     * 安全检查记录GUID
     */
    String inspRecGuid;
    /**
     * 安全元素检查项GUID
     */
    String seCheckItemGuid;
    /**
     * 隐患状态
     */
    String hiddStat;
    /**
     * 合并隐患GUID
     */
    String hiddMergGuid;
    /**
     * 记录人员单位
     */
    String recOrgGuid;
    /**
     * 备注
     */
    String note;
    /**
     * 采集时间
     */
    String collTime;
    /**
     * 更新时间
     */
    String updTime;
    /**
     * 记录人员
     */
    String recPers;
    /**
     * 出现次数
     */
    String occuNum;
    /**
     * 隐患库GUID
     */
    String hiddsGuid;


    public static int getLevelNormal() {
        return LEVEL_NORMAL;
    }

    public static int getLevelBig() {
        return LEVEL_BIG;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp == null ? "" : timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce == null ? "" : nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSign() {
        return sign == null ? "" : sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getHiddName() {
        return hiddName == null ? "" : hiddName;
    }

    public void setHiddName(String hiddName) {
        this.hiddName = hiddName;
    }

    public String getEngGuid() {
        return engGuid == null ? "" : engGuid;
    }

    public void setEngGuid(String engGuid) {
        this.engGuid = engGuid;
    }

    public String getTendGuid() {
        return tendGuid == null ? "" : tendGuid;
    }

    public void setTendGuid(String tendGuid) {
        this.tendGuid = tendGuid;
    }

    public String getSeGuid() {
        return seGuid == null ? "" : seGuid;
    }

    public void setSeGuid(String seGuid) {
        this.seGuid = seGuid;
    }

    public String getOrgGuid() {
        return orgGuid == null ? "" : orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getHiddSour() {
        return hiddSour == null ? "" : hiddSour;
    }

    public void setHiddSour(String hiddSour) {
        this.hiddSour = hiddSour;
    }

    public String getHiddGrad() {
        return hiddGrad == null ? "" : hiddGrad;
    }

    public void setHiddGrad(String hiddGrad) {
        this.hiddGrad = hiddGrad;
    }

    public String getHiddClas() {
        return hiddClas == null ? "" : hiddClas;
    }

    public void setHiddClas(String hiddClas) {
        this.hiddClas = hiddClas;
    }

    public String getIfFound() {
        return ifFound == null ? "" : ifFound;
    }

    public void setIfFound(String ifFound) {
        this.ifFound = ifFound;
    }

    public String getProPart() {
        return proPart == null ? "" : proPart;
    }

    public void setProPart(String proPart) {
        this.proPart = proPart;
    }

    public String getPartLong() {
        return partLong == null ? "" : partLong;
    }

    public void setPartLong(String partLong) {
        this.partLong = partLong;
    }

    public String getPartLat() {
        return partLat == null ? "" : partLat;
    }

    public void setPartLat(String partLat) {
        this.partLat = partLat;
    }

    public String getHiddDesc() {
        return hiddDesc == null ? "" : hiddDesc;
    }

    public void setHiddDesc(String hiddDesc) {
        this.hiddDesc = hiddDesc;
    }

    public String getInspRecGuid() {
        return inspRecGuid == null ? "" : inspRecGuid;
    }

    public void setInspRecGuid(String inspRecGuid) {
        this.inspRecGuid = inspRecGuid;
    }

    public String getSeCheckItemGuid() {
        return seCheckItemGuid == null ? "" : seCheckItemGuid;
    }

    public void setSeCheckItemGuid(String seCheckItemGuid) {
        this.seCheckItemGuid = seCheckItemGuid;
    }

    public String getHiddStat() {
        return hiddStat == null ? "" : hiddStat;
    }

    public void setHiddStat(String hiddStat) {
        this.hiddStat = hiddStat;
    }

    public String getHiddMergGuid() {
        return hiddMergGuid == null ? "" : hiddMergGuid;
    }

    public void setHiddMergGuid(String hiddMergGuid) {
        this.hiddMergGuid = hiddMergGuid;
    }

    public String getRecOrgGuid() {
        return recOrgGuid == null ? "" : recOrgGuid;
    }

    public void setRecOrgGuid(String recOrgGuid) {
        this.recOrgGuid = recOrgGuid;
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

    public String getOccuNum() {
        return occuNum == null ? "" : occuNum;
    }

    public void setOccuNum(String occuNum) {
        this.occuNum = occuNum;
    }

    public String getHiddsGuid() {
        return hiddsGuid == null ? "" : hiddsGuid;
    }

    public void setHiddsGuid(String hiddsGuid) {
        this.hiddsGuid = hiddsGuid;
    }
}
