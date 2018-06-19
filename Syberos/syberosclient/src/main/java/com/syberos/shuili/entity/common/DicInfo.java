package com.syberos.shuili.entity.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class DicInfo implements Serializable{
    @SerializedName("code")
    public String code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public List<DicInfo> dataSource;

     String dcNote;
     String dcAssoFile;
     String dcFitemCode;
     String dcItemName;
     String drToDate;
     String drAttColCode;
     String dcDicName;
     String dcFromDate;
     String dcIsMult;
     String drNote;
     String drAttTabName;
     String dcItemCode;
     String dcDicCode;
     String drAttTabCode;
     String drDpcCode;
     String dcDuid;
     String dcToDate;
     String drGuid;
     String dcGrad;
     String dcFguid;
     String drFromDate;

    public String getDcNote() {
        return dcNote;
    }

    public void setDcNote(String dcNote) {
        this.dcNote = dcNote != null ? dcNote :"";
    }

    public String getDcAssoFile() {
        return dcAssoFile;
    }

    public void setDcAssoFile(String dcAssoFile) {
        this.dcAssoFile = dcAssoFile!= null ? dcAssoFile :"";
    }

    public String getDcFitemCode() {
        return dcFitemCode;
    }

    public void setDcFitemCode(String dcFitemCode) {
        this.dcFitemCode = dcFitemCode != null ? dcFitemCode :"";
    }

    public String getDcItemName() {
        return dcItemName;
    }

    public void setDcItemName(String dcItemName) {
        this.dcItemName = dcItemName != null ? dcItemName :"";
    }

    public String getDrToDate() {
        return drToDate;
    }

    public void setDrToDate(String drToDate) {
        this.drToDate = drToDate != null ?drToDate:"";
    }

    public String getDrAttColCode() {
        return drAttColCode;
    }

    public void setDrAttColCode(String drAttColCode) {
        this.drAttColCode = drAttColCode != null ? drAttColCode :"";
    }

    public String getDcDicName() {
        return dcDicName;
    }

    public void setDcDicName(String dcDicName) {
        this.dcDicName = dcDicName != null ? dcDicName :"";
    }

    public String getDcFromDate() {
        return dcFromDate;
    }

    public void setDcFromDate(String dcFromDate) {
        this.dcFromDate = dcFromDate != null ? dcFromDate:"";
    }

    public String getDcIsMult() {
        return dcIsMult;
    }

    public void setDcIsMult(String dcIsMult) {
        this.dcIsMult = dcIsMult != null ? dcIsMult :"";
    }

    public String getDrNote() {
        return drNote;
    }

    public void setDrNote(String drNote) {
        this.drNote = drNote != null ? drNote:"";
    }

    public String getDrAttTabName() {
        return drAttTabName;
    }

    public void setDrAttTabName(String drAttTabName) {
        this.drAttTabName = drAttTabName !=null ? drAttTabName :"";
    }

    public String getDcItemCode() {
        return dcItemCode;
    }

    public void setDcItemCode(String dcItemCode) {
        this.dcItemCode = dcItemCode != null ? dcItemCode:"";
    }

    public String getDcDicCode() {
        return dcDicCode;
    }

    public void setDcDicCode(String dcDicCode) {
        this.dcDicCode = dcDicCode != null ? dcDicCode :"";
    }

    public String getDrAttTabCode() {
        return drAttTabCode;
    }

    public void setDrAttTabCode(String drAttTabCode) {
        this.drAttTabCode = drAttTabCode != null ? drAttTabCode:"";
    }

    public String getDrDpcCode() {
        return drDpcCode;
    }

    public void setDrDpcCode(String drDpcCode) {
        this.drDpcCode = drDpcCode != null ? drDpcCode:"";
    }

    public String getDcDuid() {
        return dcDuid;
    }

    public void setDcDuid(String dcDuid) {
        this.dcDuid = dcDuid!= null ? dcDuid :"";
    }

    public String getDcToDate() {
        return dcToDate;
    }

    public void setDcToDate(String dcToDate) {
        this.dcToDate = dcToDate != null ? dcToDate :"";
    }

    public String getDrGuid() {
        return drGuid;
    }

    public void setDrGuid(String drGuid) {
        this.drGuid = drGuid != null ? drGuid:"";
    }

    public String getDcGrad() {
        return dcGrad;
    }

    public void setDcGrad(String dcGrad) {
        this.dcGrad = dcGrad != null ?dcGrad:"0";
    }

    public String getDcFguid() {
        return dcFguid;
    }

    public void setDcFguid(String dcFguid) {
        this.dcFguid = dcFguid != null?dcFguid:"";
    }

    public String getDrFromDate() {
        return drFromDate;
    }

    public void setDrFromDate(String drFromDate) {
        this.drFromDate = drFromDate != null ? drFromDate:"";
    }
}
