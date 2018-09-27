package com.syberos.shuili.entity.objCase;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/23.
 */

/**
 * 监督执法 案件信息对象
 */
public class ObjCase extends HttpBaseResponse<ObjCase> {
    public String guid;
    public String caseName;
    /**
     * 立案时间
     */
    public String filiTime;
    /**
     * 执法单位
     */
    public String suneOrgGuidName;
    /**
     * 案件当事人名称
     */
    public String caseLitiName;
    /**
     * 案件办理进度
     */
    public String caseProg;
    /**
     * 案卷号
     */
    public String caseNum;
    /**
     * 案件基本情况
     */
    public String caseSitu;
    public String contra1;
    public String contra2;

    public String getGuid() {
        return guid == null ? "" : guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCaseName() {
        return caseName == null ? "" : caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getFiliTime() {
        return filiTime == null ? "" : filiTime;
    }

    public void setFiliTime(String filiTime) {
        this.filiTime = filiTime;
    }

    public String getSuneOrgGuidName() {
        return suneOrgGuidName == null ? "" : suneOrgGuidName;
    }

    public void setSuneOrgGuidName(String suneOrgGuidName) {
        this.suneOrgGuidName = suneOrgGuidName;
    }

    public String getCaseLitiName() {
        return caseLitiName == null ? "" : caseLitiName;
    }

    public void setCaseLitiName(String caseLitiName) {
        this.caseLitiName = caseLitiName;
    }

    public String getCaseProg() {
        return caseProg == null ? "" : caseProg;
    }

    public void setCaseProg(String caseProg) {
        this.caseProg = caseProg;
    }

    public String getCaseNum() {
        return caseNum == null ? "" : caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getCaseSitu() {
        return caseSitu == null ? "" : caseSitu;
    }

    public void setCaseSitu(String caseSitu) {
        this.caseSitu = caseSitu;
    }

    public String getContra1() {
        return contra1 == null ? "" : contra1;
    }

    public void setContra1(String contra1) {
        this.contra1 = contra1;
    }

    public String getContra2() {
        return contra2 == null ? "" : contra2;
    }

    public void setContra2(String contra2) {
        this.contra2 = contra2;
    }
}
