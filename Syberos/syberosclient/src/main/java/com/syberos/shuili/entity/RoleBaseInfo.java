package com.syberos.shuili.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/9.
 */

public class RoleBaseInfo implements Serializable {

    String roleId;
    String roleCode;
    String orgCode;
    String orgName;
    String roleName;
    String roleDesc;
    String roleType;
    String Scode;
    String sname;
    String Ts;
    String Note;
    String status;
    String modifier;
    String orgJurd;
    String jurdAreaType;
    public RoleBaseInfo(){

    }

    public RoleBaseInfo(String roleId, String roleCode, String orgCode, String orgName, String roleName,
                    String roleDesc, String roleType, String Scode, String sname, String Ts,
                    String Note, String status,String modifier,String orgJurd,String jurdAreaType){
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleType = roleType;
        this.Scode = Scode;
        this.sname = sname;
        this.Ts = Ts;
        this.Note = Note;
        this.status = status;
        this.modifier = modifier;
        this.orgJurd = orgJurd;
        this.jurdAreaType =jurdAreaType;





    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String scode) {
        Scode = scode;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTs() {
        return Ts;
    }

    public void setTs(String ts) {
        Ts = ts;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getOrgJurd() {
        return orgJurd;
    }

    public void setOrgJurd(String orgJurd) {
        this.orgJurd = orgJurd;
    }

    public String getJurdAreaType() {
        return jurdAreaType;
    }

    public void setJurdAreaType(String jurdAreaType) {
        this.jurdAreaType = jurdAreaType;
    }
}
