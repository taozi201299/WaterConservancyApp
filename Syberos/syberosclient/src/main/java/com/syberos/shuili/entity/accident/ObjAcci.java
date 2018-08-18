package com.syberos.shuili.entity.accident;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ObjAcci extends HttpBaseResponse<ObjAcci> {

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
        return acciWiunType == null ? "" : acciWiunType;
    }

    public void setAcciWiunType(String acciWiunType) {
        this.acciWiunType = acciWiunType;
    }

    public String getAcciTreaResu() {
        return acciTreaResu == null ? "" : acciTreaResu;
    }

    public void setAcciTreaResu(String acciTreaResu) {
        this.acciTreaResu = acciTreaResu;
    }

    public String getOccuTime() {
        return occuTime == null ? "" : occuTime;
    }

    public void setOccuTime(String occuTime) {
        this.occuTime = occuTime;
    }

    public String getPID() {
        return PID == null ? "" : PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getAcciCate() {
        return acciCate == null ? "" : acciCate;
    }

    public void setAcciCate(String acciCate) {
        this.acciCate = acciCate;
    }

    public String getCasNum() {
        return casNum == null ? "" : casNum;
    }

    public void setCasNum(String casNum) {
        this.casNum = casNum;
    }

    public String getCollTime() {
        return collTime == null ? "" : collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }

    public String getSerInjNum() {
        return serInjNum == null ? "" : serInjNum;
    }

    public void setSerInjNum(String serInjNum) {
        this.serInjNum = serInjNum;
    }

    public String getOccuLoc() {
        return occuLoc == null ? "" : occuLoc;
    }

    public void setOccuLoc(String occuLoc) {
        this.occuLoc = occuLoc;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcciGrad() {
        return acciGrad == null ? "" : acciGrad;
    }

    public void setAcciGrad(String acciGrad) {
        this.acciGrad = acciGrad;
    }

    public String getAcciSitu() {
        return acciSitu == null ? "" : acciSitu;
    }

    public void setAcciSitu(String acciSitu) {
        this.acciSitu = acciSitu;
    }

    public String getIfPhoRep() {
        return ifPhoRep == null ? "" : ifPhoRep;
    }

    public void setIfPhoRep(String ifPhoRep) {
        this.ifPhoRep = ifPhoRep;
    }

    public String getRepStat() {
        return repStat == null ? "" : repStat;
    }

    public void setRepStat(String repStat) {
        this.repStat = repStat;
    }

    public String getMissNum() {
        return missNum == null ? "" : missNum;
    }

    public void setMissNum(String missNum) {
        this.missNum = missNum;
    }

    public String getRescTreaMeas() {
        return rescTreaMeas == null ? "" : rescTreaMeas;
    }

    public void setRescTreaMeas(String rescTreaMeas) {
        this.rescTreaMeas = rescTreaMeas;
    }

    public String getAcciCause() {
        return acciCause == null ? "" : acciCause;
    }

    public void setAcciCause(String acciCause) {
        this.acciCause = acciCause;
    }

    public String getAcciWindGuid() {
        return acciWindGuid == null ? "" : acciWindGuid;
    }

    public void setAcciWindGuid(String acciWindGuid) {
        this.acciWindGuid = acciWindGuid;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIfRespAcci() {
        return ifRespAcci == null ? "" : ifRespAcci;
    }

    public void setIfRespAcci(String ifRespAcci) {
        this.ifRespAcci = ifRespAcci;
    }

    public String getEconLoss() {
        return econLoss == null ? "" : econLoss;
    }

    public void setEconLoss(String econLoss) {
        this.econLoss = econLoss;
    }

    public String getAccidentUnitName() {
        return AccidentUnitName == null ? "" : AccidentUnitName;
    }

    public void setAccidentUnitName(String accidentUnitName) {
        AccidentUnitName = accidentUnitName;
    }

    public String getAcciWinunTypeName() {
        return acciWinunTypeName == null ? "" : acciWinunTypeName;
    }

    public void setAcciWinunTypeName(String acciWinunTypeName) {
        this.acciWinunTypeName = acciWinunTypeName;
    }

    public String getAcciCateName() {
        return acciCateName == null ? "" : acciCateName;
    }

    public void setAcciCateName(String acciCateName) {
        this.acciCateName = acciCateName;
    }
}
