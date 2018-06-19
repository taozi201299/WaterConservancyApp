package com.syberos.shuili.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/9.
 */

public class UserExtendInfo implements Serializable{
     String admDutyLevel;
     String depCode;
     String depId;
     String depName;
     String id ;
     String modifier;
     String note;
     String orgCode;
     String orgId;
     String orgName;
     String password;
     String persId;
     String persName;
     String persType;
     String phone;
     ArrayList<RoleBaseInfo>roleExtInfoList;
     String status;
     String ts;
     String userCode;
     String userName;
     String userType;
     String sex;
     String iconUrl;
    private String sortLetter = "";
    public UserExtendInfo(){

    }
    public UserExtendInfo(String admDutyLevel,String depCode,String depId,String depName,String id,String modifier,String note,String orgCode,
                          String orgId,String orgName,String password,String persId,String persName,String persType,
                          String phone,ArrayList roleExtInfoList,String status,String ts,String userCode,String userName,
                          String userType){
        this.admDutyLevel = admDutyLevel;
        this.depCode = depCode;
        this.depId = depId;
        this.depName = depName;
        this.id = id;
        this.modifier = modifier;
        this.note = note;
        this.orgCode = orgCode;
        this.orgId = orgId;
        this.orgName = orgName;
        this.password = password;
        this.persId = persId;
        this.persName = persName;
        this.persType = persType;
        this.phone = phone;
        this.roleExtInfoList = roleExtInfoList;
        this.status = status;
        this.ts = ts;
        this.userCode = userCode;
        this.userName = userName;
        this.userType = userType;
    }
    public String getAdmDutyLevel() {
        return admDutyLevel == null ? "":admDutyLevel;
    }

    public void setAdmDutyLevel(String admDutyLevel) {
        this.admDutyLevel = admDutyLevel;
    }

    public String getDepCode() {
        return depCode == null ? "":depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepId() {
        return depId == null ?"":depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName == null ? "": depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getId() {
        return id == null ? "":id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifier() {
        return modifier == null ? "":modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getNote() {
        return note == null ?"":note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrgCode() {
        return orgCode == null ? "":orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgId() {
        return orgId == null ? "":orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName == null ?"":orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPassword() {
        return password == null ?"":password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersId() {
        return persId == null ?"":persId;
    }

    public void setPersId(String persId) {
        this.persId = persId;
    }

    public String getPersName() {
        return persName == null ? "":persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getPersType() {
        return persType == null ? "":persType;
    }

    public void setPersType(String persType) {
        this.persType = persType;
    }

    public String getPhone() {
        return phone == null ? "":phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<RoleBaseInfo> getRoleExtInfoList() {
        return roleExtInfoList == null ? new ArrayList<RoleBaseInfo>() : roleExtInfoList;
    }

    public void setRoleExtInfoList(ArrayList<RoleBaseInfo> roleExtInfoList) {
        this.roleExtInfoList = roleExtInfoList;
    }

    public String getStatus() {
        return status == null ? "":status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTs() {
        return ts == null ? "":ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getUserCode() {
        return userCode == null ? "":userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName == null ? "":userName;
    }

    public String getIconUrl() {
        return iconUrl == null ?"":iconUrl ;
    }

    public String getSex() {
        return sex == null ? "":"未知";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType == null ? "":userType ;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSortLetter(){
         return this.sortLetter;
    }
    public void setSortLetter(String letter){
        this.sortLetter = letter;

    }
}
