package com.syberos.shuili.entity.objCase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * created by：toby on 18-4-16 11:09
 * email：zhaodongshuang@syberos.com
 * 监督执法 ——> 案件详情
 */
public class LawEnforcementInformation implements Serializable{

    private String name;        // 案件名称
    private String litigant;    // 当事人
    private String time;        // 立案时间
    private String undertaker;  // 承办人员
    private String description; // 案件基本情况

    private List<LawEnforcementEvidenceInformation> evidenceInformationList;   // 现场执法证据


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLitigant() {
        return litigant == null ? "" : litigant;
    }

    public void setLitigant(String litigant) {
        this.litigant = litigant;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUndertaker() {
        return undertaker == null ? "" : undertaker;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LawEnforcementEvidenceInformation> getEvidenceInformationList() {
        if (evidenceInformationList == null) {
            return new ArrayList<>();
        }
        return evidenceInformationList;
    }

    public void setEvidenceInformationList(List<LawEnforcementEvidenceInformation> evidenceInformationList) {
        this.evidenceInformationList = evidenceInformationList;
    }
}
