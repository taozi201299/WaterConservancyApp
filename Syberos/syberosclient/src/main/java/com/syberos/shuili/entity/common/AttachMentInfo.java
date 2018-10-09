package com.syberos.shuili.entity.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class AttachMentInfo implements Serializable {
    /**
     * code : 0
     * msg : 请求正常返回
     * totalCount : 1
     * data : [{"guid":"919c7930bcdf43bda53387147a1f5eda","medName":"picture_1538992392615.jpg","medTitl":null,"keyWord":null,"estaPop":null,"estaDate":null,"estaWiunName":null,"medType":"9","medPath":null,"medSize":null,"medExt":null,"isSec":null,"secGrad":null,"docLang":null,"imgShootTm":null,"imgViewTp":null,"vidLen":null,"abs":null,"note":null,"collTime":"2018-10-08 00:00:00","updTime":null,"recPers":null}]
     */

    private int code;
    private String msg;
    private int totalCount;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * guid : 919c7930bcdf43bda53387147a1f5eda
         * medName : picture_1538992392615.jpg
         * medTitl : null
         * keyWord : null
         * estaPop : null
         * estaDate : null
         * estaWiunName : null
         * medType : 9
         * medPath : null
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
         * collTime : 2018-10-08 00:00:00
         * updTime : null
         * recPers : null
         */

        private String guid;
        private String medName;
        private String medTitl;
        private String keyWord;
        private String estaPop;
        private String estaDate;
        private String estaWiunName;
        private String medType;
        private String medPath;
        private String medSize;
        private String medExt;
        private String isSec;
        private String secGrad;
        private String docLang;
        private String imgShootTm;
        private String imgViewTp;
        private String vidLen;
        private String abs;
        private String note;
        private String collTime;
        private String updTime;
        private String recPers;

        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getMedName() {
            return medName == null ? "" : medName;
        }

        public void setMedName(String medName) {
            this.medName = medName;
        }

        public String getMedTitl() {
            return medTitl == null ? "" : medTitl;
        }

        public void setMedTitl(String medTitl) {
            this.medTitl = medTitl;
        }

        public String getKeyWord() {
            return keyWord == null ? "" : keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getEstaPop() {
            return estaPop == null ? "" : estaPop;
        }

        public void setEstaPop(String estaPop) {
            this.estaPop = estaPop;
        }

        public String getEstaDate() {
            return estaDate == null ? "" : estaDate;
        }

        public void setEstaDate(String estaDate) {
            this.estaDate = estaDate;
        }

        public String getEstaWiunName() {
            return estaWiunName == null ? "" : estaWiunName;
        }

        public void setEstaWiunName(String estaWiunName) {
            this.estaWiunName = estaWiunName;
        }

        public String getMedType() {
            return medType == null ? "" : medType;
        }

        public void setMedType(String medType) {
            this.medType = medType;
        }

        public String getMedPath() {
            return medPath == null ? "" : medPath;
        }

        public void setMedPath(String medPath) {
            this.medPath = medPath;
        }

        public String getMedSize() {
            return medSize == null ? "" : medSize;
        }

        public void setMedSize(String medSize) {
            this.medSize = medSize;
        }

        public String getMedExt() {
            return medExt == null ? "" : medExt;
        }

        public void setMedExt(String medExt) {
            this.medExt = medExt;
        }

        public String getIsSec() {
            return isSec == null ? "" : isSec;
        }

        public void setIsSec(String isSec) {
            this.isSec = isSec;
        }

        public String getSecGrad() {
            return secGrad == null ? "" : secGrad;
        }

        public void setSecGrad(String secGrad) {
            this.secGrad = secGrad;
        }

        public String getDocLang() {
            return docLang == null ? "" : docLang;
        }

        public void setDocLang(String docLang) {
            this.docLang = docLang;
        }

        public String getImgShootTm() {
            return imgShootTm == null ? "" : imgShootTm;
        }

        public void setImgShootTm(String imgShootTm) {
            this.imgShootTm = imgShootTm;
        }

        public String getImgViewTp() {
            return imgViewTp == null ? "" : imgViewTp;
        }

        public void setImgViewTp(String imgViewTp) {
            this.imgViewTp = imgViewTp;
        }

        public String getVidLen() {
            return vidLen == null ? "" : vidLen;
        }

        public void setVidLen(String vidLen) {
            this.vidLen = vidLen;
        }

        public String getAbs() {
            return abs == null ? "" : abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
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
    }
}
