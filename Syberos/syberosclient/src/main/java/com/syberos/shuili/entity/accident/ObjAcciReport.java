package com.syberos.shuili.entity.accident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public class ObjAcciReport implements Serializable {

    /**
     * code : 0
     * msg : 请求正常返回
     * totalCount : 1
     * data : [{"guid":"b5e0fdace2334429bb5f1738aed4c7fe","acciGrad":"2","repGuid":"9f5ea2e7016c4edfad5d520feba86cf4","nSerInjNum":22,"occuTime":"2018-10-17 00:00:00","casNum":2,"acciCate":"02","wiunName":"黄河万家寨水利枢纽有限公司","nEconLoss":2,"ROWNO":1,"serInjNum":22,"nCasNum":2,"isRep":null,"econLoss":2,"occuLoc":null}]
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
        return msg == null ? "" : msg;
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
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean implements Serializable {
        /**
         * guid : b5e0fdace2334429bb5f1738aed4c7fe
         * acciGrad : 2
         * repGuid : 9f5ea2e7016c4edfad5d520feba86cf4
         * nSerInjNum : 22
         * occuTime : 2018-10-17 00:00:00
         * casNum : 2
         * acciCate : 02
         * wiunName : 黄河万家寨水利枢纽有限公司
         * nEconLoss : 2
         * ROWNO : 1
         * serInjNum : 22
         * nCasNum : 2
         * isRep : null
         * econLoss : 2
         * occuLoc : null
         */

        private String guid;
        private String acciGrad;
        private String repGuid;
        private String nSerInjNum;
        private String occuTime;
        private String casNum;
        private String acciCate;
        private String wiunName;
        private String nEconLoss;
        private int ROWNO;
        private String serInjNum;
        private String nCasNum;
        private String isRep;
        private String econLoss;
        private String occuLoc;


        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getAcciGrad() {
            return acciGrad == null ? "" : acciGrad;
        }

        public void setAcciGrad(String acciGrad) {
            this.acciGrad = acciGrad;
        }

        public String getRepGuid() {
            return repGuid == null ? "" : repGuid;
        }

        public void setRepGuid(String repGuid) {
            this.repGuid = repGuid;
        }

        public String getnSerInjNum() {
            return nSerInjNum == null ? "" : nSerInjNum;
        }

        public void setnSerInjNum(String nSerInjNum) {
            this.nSerInjNum = nSerInjNum;
        }

        public String getOccuTime() {
            return occuTime == null ? "" : occuTime;
        }

        public void setOccuTime(String occuTime) {
            this.occuTime = occuTime;
        }

        public String getCasNum() {
            return casNum == null ? "" : casNum;
        }

        public void setCasNum(String casNum) {
            this.casNum = casNum;
        }

        public String getAcciCate() {
            return acciCate == null ? "" : acciCate;
        }

        public void setAcciCate(String acciCate) {
            this.acciCate = acciCate;
        }

        public String getWiunName() {
            return wiunName == null ? "" : wiunName;
        }

        public void setWiunName(String wiunName) {
            this.wiunName = wiunName;
        }

        public String getnEconLoss() {
            return nEconLoss == null ? "" : nEconLoss;
        }

        public void setnEconLoss(String nEconLoss) {
            this.nEconLoss = nEconLoss;
        }

        public int getROWNO() {
            return ROWNO;
        }

        public void setROWNO(int ROWNO) {
            this.ROWNO = ROWNO;
        }

        public String getSerInjNum() {
            return serInjNum == null ? "" : serInjNum;
        }

        public void setSerInjNum(String serInjNum) {
            this.serInjNum = serInjNum;
        }

        public String getnCasNum() {
            return nCasNum == null ? "" : nCasNum;
        }

        public void setnCasNum(String nCasNum) {
            this.nCasNum = nCasNum;
        }

        public String getIsRep() {
            return isRep == null ? "" : isRep;
        }

        public void setIsRep(String isRep) {
            this.isRep = isRep;
        }

        public String getEconLoss() {
            return econLoss == null ? "" : econLoss;
        }

        public void setEconLoss(String econLoss) {
            this.econLoss = econLoss;
        }

        public String getOccuLoc() {
            return occuLoc == null ? "" : occuLoc;
        }

        public void setOccuLoc(String occuLoc) {
            this.occuLoc = occuLoc;
        }
    }
}
