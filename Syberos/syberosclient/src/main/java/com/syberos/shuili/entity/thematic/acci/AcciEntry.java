package com.syberos.shuili.entity.thematic.acci;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/29.
 */

public class AcciEntry implements Serializable {


    MetaBean meta;
    DataBean data;
    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean implements Serializable {
        /**
         * message : ok
         * success : true
         */

        private String message;
        private boolean success;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

    public static class DataBean implements Serializable {
        String ACCIGRAD3CASNUM;
        String ACCIGRAD2MISSNUM;
        String ACCITOTALNUM;
        String ACCIGRAD2ECONLOSS;
        String ACCITOTALCASNUM;
        String ACCIGRAD2CASNUM;
        String ACCIGRAD4ECONLOSS;
        String ACCIGRAD1MISSNUM;
        String ACCIGRAD4SERINJNUM;
        String ACCIGRAD1SERINJNUM;
        String ACCIGRAD3MISSNUM;
        String ACCITGRAD4NUM;
        String ACCIGRAD3SERINJNUM;
        String ACCIGRAD4CASNUM;
        String ACCITOTALSERINJNUM;
        ArrayList<ITEMDATABean>ITEMDATA;
        String ACCIGRAD4MISSNUM;
        String ACCITGRAD2NUM;
        String ACCITOTALMISSNUM;
        String ACCITOTALECONLOSS;
        String ACCIGRAD3ECONLOSS;
        String ACCITGRAD1NUM;
        String ACCITGRAD3NUM;
        String ACCIGRAD1CASNUM;
        String ACCIGRAD1ECONLOSS;
        String ACCIGRAD2SERINJNUM;

        public String getACCIGRAD3CASNUM() {
            return ACCIGRAD3CASNUM == null ? "" : ACCIGRAD3CASNUM;
        }

        public void setACCIGRAD3CASNUM(String ACCIGRAD3CASNUM) {
            this.ACCIGRAD3CASNUM = ACCIGRAD3CASNUM;
        }

        public String getACCIGRAD2MISSNUM() {
            return ACCIGRAD2MISSNUM == null ? "" : ACCIGRAD2MISSNUM;
        }

        public void setACCIGRAD2MISSNUM(String ACCIGRAD2MISSNUM) {
            this.ACCIGRAD2MISSNUM = ACCIGRAD2MISSNUM;
        }

        public String getACCITOTALNUM() {
            return ACCITOTALNUM == null ? "" : ACCITOTALNUM;
        }

        public void setACCITOTALNUM(String ACCITOTALNUM) {
            this.ACCITOTALNUM = ACCITOTALNUM;
        }

        public String getACCIGRAD2ECONLOSS() {
            return ACCIGRAD2ECONLOSS == null ? "" : ACCIGRAD2ECONLOSS;
        }

        public void setACCIGRAD2ECONLOSS(String ACCIGRAD2ECONLOSS) {
            this.ACCIGRAD2ECONLOSS = ACCIGRAD2ECONLOSS;
        }

        public String getACCITOTALCASNUM() {
            return ACCITOTALCASNUM == null ? "" : ACCITOTALCASNUM;
        }

        public void setACCITOTALCASNUM(String ACCITOTALCASNUM) {
            this.ACCITOTALCASNUM = ACCITOTALCASNUM;
        }

        public String getACCIGRAD2CASNUM() {
            return ACCIGRAD2CASNUM == null ? "" : ACCIGRAD2CASNUM;
        }

        public void setACCIGRAD2CASNUM(String ACCIGRAD2CASNUM) {
            this.ACCIGRAD2CASNUM = ACCIGRAD2CASNUM;
        }

        public String getACCIGRAD4ECONLOSS() {
            return ACCIGRAD4ECONLOSS == null ? "" : ACCIGRAD4ECONLOSS;
        }

        public void setACCIGRAD4ECONLOSS(String ACCIGRAD4ECONLOSS) {
            this.ACCIGRAD4ECONLOSS = ACCIGRAD4ECONLOSS;
        }

        public String getACCIGRAD1MISSNUM() {
            return ACCIGRAD1MISSNUM == null ? "" : ACCIGRAD1MISSNUM;
        }

        public void setACCIGRAD1MISSNUM(String ACCIGRAD1MISSNUM) {
            this.ACCIGRAD1MISSNUM = ACCIGRAD1MISSNUM;
        }

        public String getACCIGRAD4SERINJNUM() {
            return ACCIGRAD4SERINJNUM == null ? "" : ACCIGRAD4SERINJNUM;
        }

        public void setACCIGRAD4SERINJNUM(String ACCIGRAD4SERINJNUM) {
            this.ACCIGRAD4SERINJNUM = ACCIGRAD4SERINJNUM;
        }

        public String getACCIGRAD1SERINJNUM() {
            return ACCIGRAD1SERINJNUM == null ? "" : ACCIGRAD1SERINJNUM;
        }

        public void setACCIGRAD1SERINJNUM(String ACCIGRAD1SERINJNUM) {
            this.ACCIGRAD1SERINJNUM = ACCIGRAD1SERINJNUM;
        }

        public String getACCIGRAD3MISSNUM() {
            return ACCIGRAD3MISSNUM == null ? "" : ACCIGRAD3MISSNUM;
        }

        public void setACCIGRAD3MISSNUM(String ACCIGRAD3MISSNUM) {
            this.ACCIGRAD3MISSNUM = ACCIGRAD3MISSNUM;
        }

        public String getACCITGRAD4NUM() {
            return ACCITGRAD4NUM == null ? "" : ACCITGRAD4NUM;
        }

        public void setACCITGRAD4NUM(String ACCITGRAD4NUM) {
            this.ACCITGRAD4NUM = ACCITGRAD4NUM;
        }

        public String getACCIGRAD3SERINJNUM() {
            return ACCIGRAD3SERINJNUM == null ? "" : ACCIGRAD3SERINJNUM;
        }

        public void setACCIGRAD3SERINJNUM(String ACCIGRAD3SERINJNUM) {
            this.ACCIGRAD3SERINJNUM = ACCIGRAD3SERINJNUM;
        }

        public String getACCIGRAD4CASNUM() {
            return ACCIGRAD4CASNUM == null ? "" : ACCIGRAD4CASNUM;
        }

        public void setACCIGRAD4CASNUM(String ACCIGRAD4CASNUM) {
            this.ACCIGRAD4CASNUM = ACCIGRAD4CASNUM;
        }

        public String getACCITOTALSERINJNUM() {
            return ACCITOTALSERINJNUM == null ? "" : ACCITOTALSERINJNUM;
        }

        public void setACCITOTALSERINJNUM(String ACCITOTALSERINJNUM) {
            this.ACCITOTALSERINJNUM = ACCITOTALSERINJNUM;
        }

        public ArrayList<ITEMDATABean> getITEMDATA() {
            if (ITEMDATA == null) {
                return new ArrayList<>();
            }
            return ITEMDATA;
        }

        public void setITEMDATA(ArrayList<ITEMDATABean> ITEMDATA) {
            this.ITEMDATA = ITEMDATA;
        }

        public String getACCIGRAD4MISSNUM() {
            return ACCIGRAD4MISSNUM == null ? "" : ACCIGRAD4MISSNUM;
        }

        public void setACCIGRAD4MISSNUM(String ACCIGRAD4MISSNUM) {
            this.ACCIGRAD4MISSNUM = ACCIGRAD4MISSNUM;
        }

        public String getACCITGRAD2NUM() {
            return ACCITGRAD2NUM == null ? "0" : ACCITGRAD2NUM;
        }

        public void setACCITGRAD2NUM(String ACCITGRAD2NUM) {
            this.ACCITGRAD2NUM = ACCITGRAD2NUM;
        }

        public String getACCITOTALMISSNUM() {
            return ACCITOTALMISSNUM == null ? "" : ACCITOTALMISSNUM;
        }

        public void setACCITOTALMISSNUM(String ACCITOTALMISSNUM) {
            this.ACCITOTALMISSNUM = ACCITOTALMISSNUM;
        }

        public String getACCITOTALECONLOSS() {
            return ACCITOTALECONLOSS == null ? "" : ACCITOTALECONLOSS;
        }

        public void setACCITOTALECONLOSS(String ACCITOTALECONLOSS) {
            this.ACCITOTALECONLOSS = ACCITOTALECONLOSS;
        }

        public String getACCIGRAD3ECONLOSS() {
            return ACCIGRAD3ECONLOSS == null ? "" : ACCIGRAD3ECONLOSS;
        }

        public void setACCIGRAD3ECONLOSS(String ACCIGRAD3ECONLOSS) {
            this.ACCIGRAD3ECONLOSS = ACCIGRAD3ECONLOSS;
        }

        public String getACCITGRAD1NUM() {
            return ACCITGRAD1NUM == null ? "0" : ACCITGRAD1NUM;
        }

        public void setACCITGRAD1NUM(String ACCITGRAD1NUM) {
            this.ACCITGRAD1NUM = ACCITGRAD1NUM;
        }

        public String getACCITGRAD3NUM() {
            return ACCITGRAD3NUM == null ? "" : ACCITGRAD3NUM;
        }

        public void setACCITGRAD3NUM(String ACCITGRAD3NUM) {
            this.ACCITGRAD3NUM = ACCITGRAD3NUM;
        }

        public String getACCIGRAD1CASNUM() {
            return ACCIGRAD1CASNUM == null ? "" : ACCIGRAD1CASNUM;
        }

        public void setACCIGRAD1CASNUM(String ACCIGRAD1CASNUM) {
            this.ACCIGRAD1CASNUM = ACCIGRAD1CASNUM;
        }

        public String getACCIGRAD1ECONLOSS() {
            return ACCIGRAD1ECONLOSS == null ? "" : ACCIGRAD1ECONLOSS;
        }

        public void setACCIGRAD1ECONLOSS(String ACCIGRAD1ECONLOSS) {
            this.ACCIGRAD1ECONLOSS = ACCIGRAD1ECONLOSS;
        }

        public String getACCIGRAD2SERINJNUM() {
            return ACCIGRAD2SERINJNUM == null ? "" : ACCIGRAD2SERINJNUM;
        }

        public void setACCIGRAD2SERINJNUM(String ACCIGRAD2SERINJNUM) {
            this.ACCIGRAD2SERINJNUM = ACCIGRAD2SERINJNUM;
        }
    }
    public static class ITEMDATABean implements Serializable {
        String ACCIGRAD3CASNUM;
        String ACCIGRAD2MISSNUM;
        String ACCITOTALNUM;  // 总个数
        String ACCIGRAD2ECONLOSS;
        String ACCITOTALCASNUM;
        String ACCIGRAD2CASNUM;
        String ACCIGRAD4ECONLOSS;
        String OBJGUID;
        String ACCIGRAD1MISSNUM;
        String ACCIGRAD4SERINJNUM;
        String ACCIGRAD1SERINJNUM;
        String ACCIGRAD3MISSNUM;
        String ACCITGRAD4NUM;
        String ACCIGRAD3SERINJNUM;
        String ACCIGRAD4CASNUM;
        String OBJLAT;
        String ACCITOTALSERINJNUM;
        String ACCIGRAD4MISSNUM;
        String ACCITGRAD2NUM;
        String ACCITOTALMISSNUM;
        String ACCITOTALECONLOSS; // 总的经济损失
        String ACCIGRAD3ECONLOSS;
        String ACCITGRAD1NUM;
        String ACCITGRAD3NUM;
        ArrayList<ACCIDATABean>ACCIDATA;
        String ACCIGRAD1CASNUM; // 总的死亡人数
        String ACCIGRAD1ECONLOSS;
        String OBJLONG;
        String ACCIGRAD2SERINJNUM;
        String OBJNAME;

        public String getACCIGRAD3CASNUM() {
            return ACCIGRAD3CASNUM == null ? "" : ACCIGRAD3CASNUM;
        }

        public void setACCIGRAD3CASNUM(String ACCIGRAD3CASNUM) {
            this.ACCIGRAD3CASNUM = ACCIGRAD3CASNUM;
        }

        public String getACCIGRAD2MISSNUM() {
            return ACCIGRAD2MISSNUM == null ? "" : ACCIGRAD2MISSNUM;
        }

        public void setACCIGRAD2MISSNUM(String ACCIGRAD2MISSNUM) {
            this.ACCIGRAD2MISSNUM = ACCIGRAD2MISSNUM;
        }

        public String getACCITOTALNUM() {
            return ACCITOTALNUM == null ? "" : ACCITOTALNUM;
        }

        public void setACCITOTALNUM(String ACCITOTALNUM) {
            this.ACCITOTALNUM = ACCITOTALNUM;
        }

        public String getACCIGRAD2ECONLOSS() {
            return ACCIGRAD2ECONLOSS == null ? "" : ACCIGRAD2ECONLOSS;
        }

        public void setACCIGRAD2ECONLOSS(String ACCIGRAD2ECONLOSS) {
            this.ACCIGRAD2ECONLOSS = ACCIGRAD2ECONLOSS;
        }

        public String getACCITOTALCASNUM() {
            return ACCITOTALCASNUM == null ? "" : ACCITOTALCASNUM;
        }

        public void setACCITOTALCASNUM(String ACCITOTALCASNUM) {
            this.ACCITOTALCASNUM = ACCITOTALCASNUM;
        }

        public String getACCIGRAD2CASNUM() {
            return ACCIGRAD2CASNUM == null ? "" : ACCIGRAD2CASNUM;
        }

        public void setACCIGRAD2CASNUM(String ACCIGRAD2CASNUM) {
            this.ACCIGRAD2CASNUM = ACCIGRAD2CASNUM;
        }

        public String getACCIGRAD4ECONLOSS() {
            return ACCIGRAD4ECONLOSS == null ? "" : ACCIGRAD4ECONLOSS;
        }

        public void setACCIGRAD4ECONLOSS(String ACCIGRAD4ECONLOSS) {
            this.ACCIGRAD4ECONLOSS = ACCIGRAD4ECONLOSS;
        }

        public String getACCIGRAD1MISSNUM() {
            return ACCIGRAD1MISSNUM == null ? "" : ACCIGRAD1MISSNUM;
        }

        public void setACCIGRAD1MISSNUM(String ACCIGRAD1MISSNUM) {
            this.ACCIGRAD1MISSNUM = ACCIGRAD1MISSNUM;
        }

        public String getACCIGRAD4SERINJNUM() {
            return ACCIGRAD4SERINJNUM == null ? "" : ACCIGRAD4SERINJNUM;
        }

        public void setACCIGRAD4SERINJNUM(String ACCIGRAD4SERINJNUM) {
            this.ACCIGRAD4SERINJNUM = ACCIGRAD4SERINJNUM;
        }

        public String getACCIGRAD1SERINJNUM() {
            return ACCIGRAD1SERINJNUM == null ? "" : ACCIGRAD1SERINJNUM;
        }

        public void setACCIGRAD1SERINJNUM(String ACCIGRAD1SERINJNUM) {
            this.ACCIGRAD1SERINJNUM = ACCIGRAD1SERINJNUM;
        }

        public String getACCIGRAD3MISSNUM() {
            return ACCIGRAD3MISSNUM == null ? "" : ACCIGRAD3MISSNUM;
        }

        public void setACCIGRAD3MISSNUM(String ACCIGRAD3MISSNUM) {
            this.ACCIGRAD3MISSNUM = ACCIGRAD3MISSNUM;
        }

        public String getACCITGRAD4NUM() {
            return ACCITGRAD4NUM == null ? "" : ACCITGRAD4NUM;
        }

        public void setACCITGRAD4NUM(String ACCITGRAD4NUM) {
            this.ACCITGRAD4NUM = ACCITGRAD4NUM;
        }

        public String getACCIGRAD3SERINJNUM() {
            return ACCIGRAD3SERINJNUM == null ? "" : ACCIGRAD3SERINJNUM;
        }

        public void setACCIGRAD3SERINJNUM(String ACCIGRAD3SERINJNUM) {
            this.ACCIGRAD3SERINJNUM = ACCIGRAD3SERINJNUM;
        }

        public String getACCIGRAD4CASNUM() {
            return ACCIGRAD4CASNUM == null ? "" : ACCIGRAD4CASNUM;
        }

        public void setACCIGRAD4CASNUM(String ACCIGRAD4CASNUM) {
            this.ACCIGRAD4CASNUM = ACCIGRAD4CASNUM;
        }

        public String getACCITOTALSERINJNUM() {
            return ACCITOTALSERINJNUM == null ? "" : ACCITOTALSERINJNUM;
        }

        public void setACCITOTALSERINJNUM(String ACCITOTALSERINJNUM) {
            this.ACCITOTALSERINJNUM = ACCITOTALSERINJNUM;
        }

        public String getACCIGRAD4MISSNUM() {
            return ACCIGRAD4MISSNUM == null ? "" : ACCIGRAD4MISSNUM;
        }

        public void setACCIGRAD4MISSNUM(String ACCIGRAD4MISSNUM) {
            this.ACCIGRAD4MISSNUM = ACCIGRAD4MISSNUM;
        }

        public String getACCITGRAD2NUM() {
            return ACCITGRAD2NUM == null ? "" : ACCITGRAD2NUM;
        }

        public void setACCITGRAD2NUM(String ACCITGRAD2NUM) {
            this.ACCITGRAD2NUM = ACCITGRAD2NUM;
        }

        public String getACCITOTALMISSNUM() {
            return ACCITOTALMISSNUM == null ? "" : ACCITOTALMISSNUM;
        }

        public void setACCITOTALMISSNUM(String ACCITOTALMISSNUM) {
            this.ACCITOTALMISSNUM = ACCITOTALMISSNUM;
        }

        public String getACCITOTALECONLOSS() {
            return ACCITOTALECONLOSS == null ? "" : ACCITOTALECONLOSS;
        }

        public void setACCITOTALECONLOSS(String ACCITOTALECONLOSS) {
            this.ACCITOTALECONLOSS = ACCITOTALECONLOSS;
        }

        public String getACCIGRAD3ECONLOSS() {
            return ACCIGRAD3ECONLOSS == null ? "" : ACCIGRAD3ECONLOSS;
        }

        public void setACCIGRAD3ECONLOSS(String ACCIGRAD3ECONLOSS) {
            this.ACCIGRAD3ECONLOSS = ACCIGRAD3ECONLOSS;
        }

        public String getACCITGRAD1NUM() {
            return ACCITGRAD1NUM == null ? "" : ACCITGRAD1NUM;
        }

        public void setACCITGRAD1NUM(String ACCITGRAD1NUM) {
            this.ACCITGRAD1NUM = ACCITGRAD1NUM;
        }

        public String getACCITGRAD3NUM() {
            return ACCITGRAD3NUM == null ? "" : ACCITGRAD3NUM;
        }

        public void setACCITGRAD3NUM(String ACCITGRAD3NUM) {
            this.ACCITGRAD3NUM = ACCITGRAD3NUM;
        }

        public ArrayList<ACCIDATABean> getACCIDATA() {
            if (ACCIDATA == null) {
                return new ArrayList<>();
            }
            return ACCIDATA;
        }

        public void setACCIDATA(ArrayList<ACCIDATABean> ACCIDATA) {
            this.ACCIDATA = ACCIDATA;
        }

        public String getACCIGRAD1CASNUM() {
            return ACCIGRAD1CASNUM == null ? "" : ACCIGRAD1CASNUM;
        }

        public void setACCIGRAD1CASNUM(String ACCIGRAD1CASNUM) {
            this.ACCIGRAD1CASNUM = ACCIGRAD1CASNUM;
        }

        public String getACCIGRAD1ECONLOSS() {
            return ACCIGRAD1ECONLOSS == null ? "" : ACCIGRAD1ECONLOSS;
        }

        public void setACCIGRAD1ECONLOSS(String ACCIGRAD1ECONLOSS) {
            this.ACCIGRAD1ECONLOSS = ACCIGRAD1ECONLOSS;
        }

        public String getACCIGRAD2SERINJNUM() {
            return ACCIGRAD2SERINJNUM == null ? "" : ACCIGRAD2SERINJNUM;
        }

        public void setACCIGRAD2SERINJNUM(String ACCIGRAD2SERINJNUM) {
            this.ACCIGRAD2SERINJNUM = ACCIGRAD2SERINJNUM;
        }

        public String getOBJNAME() {
            return OBJNAME == null ? "" : OBJNAME;
        }

        public void setOBJNAME(String OBJNAME) {
            this.OBJNAME = OBJNAME;
        }

        public String getOBJGUID() {
            return OBJGUID == null ? "" : OBJGUID;
        }

        public void setOBJGUID(String OBJGUID) {
            this.OBJGUID = OBJGUID;
        }

        public String getOBJLAT() {
            return OBJLAT == null ? "" : OBJLAT;
        }

        public void setOBJLAT(String OBJLAT) {
            this.OBJLAT = OBJLAT;
        }

        public String getOBJLONG() {
            return OBJLONG == null ? "" : OBJLONG;
        }

        public void setOBJLONG(String OBJLONG) {
            this.OBJLONG = OBJLONG;
        }
    }
    public static class ACCIDATABean implements Serializable{
        String CASNUM;
        String ECONLOSS;
        String GUID;
        String ACCIGRAD;
        String ACCI_GRADNAME;
        String ACCI_CATENAME;
        String ACCISITU;
        String ENGGUID;
        String ORGGUID;
        String ENGNAME;
        String SERINJNUM;
        String MISSNUM;
        String ACCICATE;
        String ORGNAME;

        public String getCASNUM() {
            return CASNUM == null ? "" : CASNUM;
        }

        public void setCASNUM(String CASNUM) {
            this.CASNUM = CASNUM;
        }

        public String getECONLOSS() {
            return ECONLOSS == null ? "" : ECONLOSS;
        }

        public void setECONLOSS(String ECONLOSS) {
            this.ECONLOSS = ECONLOSS;
        }

        public String getGUID() {
            return GUID == null ? "" : GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getACCIGRAD() {
            return ACCIGRAD == null ? "" : ACCIGRAD;
        }

        public void setACCIGRAD(String ACCIGRAD) {
            this.ACCIGRAD = ACCIGRAD;
        }

        public String getACCI_GRADNAME() {
            return ACCI_GRADNAME == null ? "" : ACCI_GRADNAME;
        }

        public void setACCI_GRADNAME(String ACCI_GRADNAME) {
            this.ACCI_GRADNAME = ACCI_GRADNAME;
        }

        public String getACCI_CATENAME() {
            return ACCI_CATENAME == null ? "" : ACCI_CATENAME;
        }

        public void setACCI_CATENAME(String ACCI_CATENAME) {
            this.ACCI_CATENAME = ACCI_CATENAME;
        }

        public String getACCISITU() {
            return ACCISITU == null ? "" : ACCISITU;
        }

        public void setACCISITU(String ACCISITU) {
            this.ACCISITU = ACCISITU;
        }

        public String getENGGUID() {
            return ENGGUID == null ? "" : ENGGUID;
        }

        public void setENGGUID(String ENGGUID) {
            this.ENGGUID = ENGGUID;
        }

        public String getORGGUID() {
            return ORGGUID == null ? "" : ORGGUID;
        }

        public void setORGGUID(String ORGGUID) {
            this.ORGGUID = ORGGUID;
        }

        public String getENGNAME() {
            return ENGNAME == null ? "" : ENGNAME;
        }

        public void setENGNAME(String ENGNAME) {
            this.ENGNAME = ENGNAME;
        }

        public String getSERINJNUM() {
            return SERINJNUM == null ? "" : SERINJNUM;
        }

        public void setSERINJNUM(String SERINJNUM) {
            this.SERINJNUM = SERINJNUM;
        }

        public String getMISSNUM() {
            return MISSNUM == null ? "" : MISSNUM;
        }

        public void setMISSNUM(String MISSNUM) {
            this.MISSNUM = MISSNUM;
        }

        public String getACCICATE() {
            return ACCICATE == null ? "" : ACCICATE;
        }

        public void setACCICATE(String ACCICATE) {
            this.ACCICATE = ACCICATE;
        }

        public String getORGNAME() {
            return ORGNAME == null ? "" : ORGNAME;
        }

        public void setORGNAME(String ORGNAME) {
            this.ORGNAME = ORGNAME;
        }
    }

}
