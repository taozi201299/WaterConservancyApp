package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/16.
 */

public class BisWoasObj extends HttpBaseResponse<BisWoasObj>{
    String asseStat;
    String woasStartime;
    String asseOrgType;
    String compEvalScor;
    String finalScor;
    String woasWiunGuid;
    String guid;
    String gradStat;
    String otheAddterm;
    String woasGuid;
    String woasDeadline;
    String woasGroupGuid;
    String woasWiunName;
    String evalStat;
    String asseScor;

    public String getAsseStat() {
        return asseStat == null ? "" : asseStat;
    }

    public void setAsseStat(String asseStat) {
        this.asseStat = asseStat;
    }

    public String getWoasStartime() {
        return woasStartime == null ? "" : woasStartime;
    }

    public void setWoasStartime(String woasStartime) {
        this.woasStartime = woasStartime;
    }

    public String getAsseOrgType() {
        return asseOrgType == null ? "" : asseOrgType;
    }

    public void setAsseOrgType(String asseOrgType) {
        this.asseOrgType = asseOrgType;
    }

    public String getCompEvalScor() {
        return compEvalScor == null ? "" : compEvalScor;
    }

    public void setCompEvalScor(String compEvalScor) {
        this.compEvalScor = compEvalScor;
    }

    public String getFinalScor() {
        return finalScor == null ? "" : finalScor;
    }

    public void setFinalScor(String finalScor) {
        this.finalScor = finalScor;
    }

    public String getWoasWiunGuid() {
        return woasWiunGuid == null ? "" : woasWiunGuid;
    }

    public void setWoasWiunGuid(String woasWiunGuid) {
        this.woasWiunGuid = woasWiunGuid;
    }

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGradStat() {
        return gradStat == null ? "" : gradStat;
    }

    public void setGradStat(String gradStat) {
        this.gradStat = gradStat;
    }

    public String getOtheAddterm() {
        return otheAddterm == null ? "" : otheAddterm;
    }

    public void setOtheAddterm(String otheAddterm) {
        this.otheAddterm = otheAddterm;
    }

    public String getWoasGuid() {
        return woasGuid == null ? "" : woasGuid;
    }

    public void setWoasGuid(String woasGuid) {
        this.woasGuid = woasGuid;
    }

    public String getWoasDeadline() {
        return woasDeadline == null ? "" : woasDeadline;
    }

    public void setWoasDeadline(String woasDeadline) {
        this.woasDeadline = woasDeadline;
    }

    public String getWoasGroupGuid() {
        return woasGroupGuid == null ? "" : woasGroupGuid;
    }

    public void setWoasGroupGuid(String woasGroupGuid) {
        this.woasGroupGuid = woasGroupGuid;
    }

    public String getWoasWiunName() {
        return woasWiunName == null ? "" : woasWiunName;
    }

    public void setWoasWiunName(String woasWiunName) {
        this.woasWiunName = woasWiunName;
    }

    public String getEvalStat() {
        return evalStat == null ? "" : evalStat;
    }

    public void setEvalStat(String evalStat) {
        this.evalStat = evalStat;
    }

    public String getAsseScor() {
        return asseScor == null ? "" : asseScor;
    }

    public void setAsseScor(String asseScor) {
        this.asseScor = asseScor;
    }
}
