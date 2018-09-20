package com.syberos.shuili.entity.test;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */

public class EngineDetailBean implements Serializable {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"CHECKFILE":[],"CHECKINFO":{}}
     */

    private MetaBean meta;
    private DataBean data;

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

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * CHECKFILE : []
         * CHECKINFO : {}
         */

        private CHECKINFOBean CHECKINFO;
        private List<CheckFileBean> CHECKFILE;

        public CHECKINFOBean getCHECKINFO() {
            return CHECKINFO;
        }

        public void setCHECKINFO(CHECKINFOBean CHECKINFO) {
            this.CHECKINFO = CHECKINFO;
        }

        public List<CheckFileBean> getCHECKFILE() {
            return CHECKFILE;
        }

        public void setCHECKFILE(List<CheckFileBean> CHECKFILE) {
            this.CHECKFILE = CHECKFILE;
        }

    }

    public static class CHECKINFOBean implements Serializable {
        /**
         * CHECKOPIN : test
         * IFSPILLWAY : 1
         * IFCHECK : 1
         * GUID : 0BAB4FFBE3D24076B20E767CB5C45D8F
         */

        private String CHECKOPIN;
        private String IFSPILLWAY;
        private String IFCHECK;
        private String GUID;

        public String getCHECKOPIN() {
            return CHECKOPIN;
        }

        public void setCHECKOPIN(String CHECKOPIN) {
            this.CHECKOPIN = CHECKOPIN;
        }

        public String getIFSPILLWAY() {
            return IFSPILLWAY;
        }

        public void setIFSPILLWAY(String IFSPILLWAY) {
            this.IFSPILLWAY = IFSPILLWAY;
        }

        public String getIFCHECK() {
            return IFCHECK;
        }

        public void setIFCHECK(String IFCHECK) {
            this.IFCHECK = IFCHECK;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }
    }
    public static class CheckFileBean implements Serializable{

        /**
         * guid : 15b46d7b464a498a94148732fa187712
         * medName : 4.jpg
         * medTitl : null
         * keyWord : null
         * estaPop : null
         * estaDate : null
         * estaWiunName : null
         * medType : 2
         * medPath : http://192.168.1.11:7080/desu//static/images/yhdfh/0BAB4FFBE3D24076B20E767CB5C45D8F4.jpg
         * medSize : null
         * medExt : null
         * isSec : null
         * secGrad : null
         * docLang : null
         * imgShootTm : null
         * imgViewTp : null
         * vidLen : null
         * abs : null
         * note : null
         * collTime : 2018-09-19 15:48:35
         * updTime : null
         * recPers : null
         */

        private String guid;
        private String medName;
        private Object medTitl;
        private Object keyWord;
        private Object estaPop;
        private Object estaDate;
        private Object estaWiunName;
        private String medType;
        private String medPath;
        private Object medSize;
        private Object medExt;
        private Object isSec;
        private Object secGrad;
        private Object docLang;
        private Object imgShootTm;
        private Object imgViewTp;
        private Object vidLen;
        private Object abs;
        private Object note;
        private String collTime;
        private Object updTime;
        private Object recPers;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getMedName() {
            return medName;
        }

        public void setMedName(String medName) {
            this.medName = medName;
        }

        public Object getMedTitl() {
            return medTitl;
        }

        public void setMedTitl(Object medTitl) {
            this.medTitl = medTitl;
        }

        public Object getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(Object keyWord) {
            this.keyWord = keyWord;
        }

        public Object getEstaPop() {
            return estaPop;
        }

        public void setEstaPop(Object estaPop) {
            this.estaPop = estaPop;
        }

        public Object getEstaDate() {
            return estaDate;
        }

        public void setEstaDate(Object estaDate) {
            this.estaDate = estaDate;
        }

        public Object getEstaWiunName() {
            return estaWiunName;
        }

        public void setEstaWiunName(Object estaWiunName) {
            this.estaWiunName = estaWiunName;
        }

        public String getMedType() {
            return medType;
        }

        public void setMedType(String medType) {
            this.medType = medType;
        }

        public String getMedPath() {
            return medPath;
        }

        public void setMedPath(String medPath) {
            this.medPath = medPath;
        }

        public Object getMedSize() {
            return medSize;
        }

        public void setMedSize(Object medSize) {
            this.medSize = medSize;
        }

        public Object getMedExt() {
            return medExt;
        }

        public void setMedExt(Object medExt) {
            this.medExt = medExt;
        }

        public Object getIsSec() {
            return isSec;
        }

        public void setIsSec(Object isSec) {
            this.isSec = isSec;
        }

        public Object getSecGrad() {
            return secGrad;
        }

        public void setSecGrad(Object secGrad) {
            this.secGrad = secGrad;
        }

        public Object getDocLang() {
            return docLang;
        }

        public void setDocLang(Object docLang) {
            this.docLang = docLang;
        }

        public Object getImgShootTm() {
            return imgShootTm;
        }

        public void setImgShootTm(Object imgShootTm) {
            this.imgShootTm = imgShootTm;
        }

        public Object getImgViewTp() {
            return imgViewTp;
        }

        public void setImgViewTp(Object imgViewTp) {
            this.imgViewTp = imgViewTp;
        }

        public Object getVidLen() {
            return vidLen;
        }

        public void setVidLen(Object vidLen) {
            this.vidLen = vidLen;
        }

        public Object getAbs() {
            return abs;
        }

        public void setAbs(Object abs) {
            this.abs = abs;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public String getCollTime() {
            return collTime;
        }

        public void setCollTime(String collTime) {
            this.collTime = collTime;
        }

        public Object getUpdTime() {
            return updTime;
        }

        public void setUpdTime(Object updTime) {
            this.updTime = updTime;
        }

        public Object getRecPers() {
            return recPers;
        }

        public void setRecPers(Object recPers) {
            this.recPers = recPers;
        }
    }
}
