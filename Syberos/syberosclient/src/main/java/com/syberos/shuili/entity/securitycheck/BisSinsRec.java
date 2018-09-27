package com.syberos.shuili.entity.securitycheck;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/17.
 */

public class BisSinsRec extends HttpBaseResponse<BisSinsRec> {
   public String guid;
   public String sinsGuid;
   public String engGuid;
   public String tendGuid;
   public String startDate;
   public String endDate;
   public String legPers;
   public String inspCont;
   public String findHiddNum;
   public String note;
   public String collTime;
   public String updTime;;
   public String recPers;
   public String orgGuid;

   public String getGuid() {
      return guid == null ? "" : guid;
   }

   public void setGuid(String guid) {
      this.guid = guid;
   }

   public String getSinsGuid() {
      return sinsGuid == null ? "" : sinsGuid;
   }

   public void setSinsGuid(String sinsGuid) {
      this.sinsGuid = sinsGuid;
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

   public String getStartDate() {
      return startDate == null ? "" : startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate == null ? "" : endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getLegPers() {
      return legPers == null ? "" : legPers;
   }

   public void setLegPers(String legPers) {
      this.legPers = legPers;
   }

   public String getInspCont() {
      return inspCont == null ? "" : inspCont;
   }

   public void setInspCont(String inspCont) {
      this.inspCont = inspCont;
   }

   public String getFindHiddNum() {
      return findHiddNum == null ? "" : findHiddNum;
   }

   public void setFindHiddNum(String findHiddNum) {
      this.findHiddNum = findHiddNum;
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

   public String getOrgGuid() {
      return orgGuid == null ? "" : orgGuid;
   }

   public void setOrgGuid(String orgGuid) {
      this.orgGuid = orgGuid;
   }
}
