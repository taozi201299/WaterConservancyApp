package com.syberos.shuili.entity.accident;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ObjAcci extends HttpBaseResponse<ObjAcci> {

    public static final int TYPE_NORMAL = 0; // 一般事故
    public static final int TYPE_BIG = 1; // 较大事故
    public static final int TYPE_BIGGER = 2; // 重大事故
    public static final int TYPE_LARGE = 3; // 特大事故

    public static final int REPORT_QUICK = 2; // 快报
    public static final int REPORT_AFTER = 1; // 补报

    public static final int UNIT_SELF = 0; // 本单位
    public static final int UNIT_OTHER = 1; // 参建单位/下级单位

    public static final int NEW_ACCI = 3;
    String acciWiunType;
    String acciTreaResu;
    String occuTime;
    String PID;
    String acciCate;
    String casNum;
    String collTime;
    String serInjNum;
    String occuLoc;
    String id;
    String acciGrad;
    String acciSitu;
    String ifPhoRep;
    String repStat;
    String missNum;
    String rescTreaMeas;
    String acciCause;
    String acciWindGuid;
    String note;
    String ifRespAcci;
    String econLoss;
    String AccidentUnitName;
    String acciWinunTypeName;
    String acciCateName;


    public String getAcciWiunType() {
        return acciWiunType;
    }

    public void setAcciWiunType(String acciWiunType) {
        this.acciWiunType = acciWiunType;
    }

    public String getAcciTreaResu() {
        return acciTreaResu;
    }

    public void setAcciTreaResu(String acciTreaResu) {
        this.acciTreaResu = acciTreaResu;
    }

    public String getOccuTime() {
        return occuTime;
    }

    public void setOccuTime(String occuTime) {
        this.occuTime = occuTime;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getAcciCate() {
        return acciCate;
    }

    public void setAcciCate(String acciCate) {
        this.acciCate = acciCate;
    }

    public String getCasNum() {
        return casNum;
    }

    public void setCasNum(String casNum) {
        this.casNum = casNum;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getSerInjNum() {
        return serInjNum;
    }

    public void setSerInjNum(String serInjNum) {
        this.serInjNum = serInjNum;
    }

    public String getOccuLoc() {
        return occuLoc;
    }

    public void setOccuLoc(String occuLoc) {
        this.occuLoc = occuLoc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcciGrad() {
        return acciGrad;
    }

    public void setAcciGrad(String acciGrad) {
        this.acciGrad = acciGrad;
    }

    public String getAcciSitu() {
        return acciSitu;
    }

    public void setAcciSitu(String acciSitu) {
        this.acciSitu = acciSitu;
    }

    public String getIfPhoRep() {
        return ifPhoRep;
    }

    public void setIfPhoRep(String ifPhoRep) {
        this.ifPhoRep = ifPhoRep;
    }

    public String getRepStat() {
        return repStat;
    }

    public void setRepStat(String repStat) {
        this.repStat = repStat;
    }

    public String getMissNum() {
        return missNum;
    }

    public void setMissNum(String missNum) {
        this.missNum = missNum;
    }

    public String getRescTreaMeas() {
        return rescTreaMeas;
    }

    public void setRescTreaMeas(String rescTreaMeas) {
        this.rescTreaMeas = rescTreaMeas;
    }

    public String getAcciCause() {
        return acciCause;
    }

    public void setAcciCause(String acciCause) {
        this.acciCause = acciCause;
    }

    public String getAcciWindGuid() {
        return acciWindGuid;
    }

    public void setAcciWindGuid(String acciWindGuid) {
        this.acciWindGuid = acciWindGuid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIfRespAcci() {
        return ifRespAcci;
    }

    public void setIfRespAcci(String ifRespAcci) {
        this.ifRespAcci = ifRespAcci;
    }

    public String getEconLoss() {
        return econLoss;
    }

    public void setEconLoss(String econLoss) {
        this.econLoss = econLoss;
    }

    public String getAccidentUnitName() {
        return AccidentUnitName;
    }

    public void setAccidentUnitName(String accidentUnitName) {
        AccidentUnitName = accidentUnitName;
    }

    public String getAcciCateName() {
        return acciCateName;
    }

    public void setAcciCateName(String acciCateName) {
        this.acciCateName = acciCateName;
    }

    public String getAcciWinunTypeName() {
        return acciWinunTypeName;
    }

    public void setAcciWinunTypeName(String acciWinunTypeName) {
        this.acciWinunTypeName = acciWinunTypeName;
    }
}
