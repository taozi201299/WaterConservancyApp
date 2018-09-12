package com.syberos.shuili.entity.thematic.stans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 * 直管对应的实体类
 */

public class StanDirectEntry implements Serializable {
    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"everyOrgList":[],"bookOrgList":[],"countOrgList":[]}
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
        private List<EveryOrgBean> everyOrgList;
        private List<BookOrgBean> bookOrgList;
        private List<CountOrgBean> countOrgList;

        public List<EveryOrgBean> getEveryOrgList() {
            return everyOrgList;
        }

        public void setEveryOrgList(List<EveryOrgBean> everyOrgList) {
            this.everyOrgList = everyOrgList;
        }

        public List<BookOrgBean> getBookOrgList() {
            return bookOrgList;
        }

        public void setBookOrgList(List<BookOrgBean> bookOrgList) {
            this.bookOrgList = bookOrgList;
        }

        public List<CountOrgBean> getCountOrgList() {
            return countOrgList;
        }

        public void setCountOrgList(List<CountOrgBean> countOrgList) {
            this.countOrgList = countOrgList;
        }
    }
    public static class EveryOrgBean implements Serializable{

        /**
         * GUID : 0933B92A73B3431A9C70FFF555A12A51
         * ORG_NAME : 中皖水务发展有限公司
         * STAN_GRAD_NAME : 三级达标
         * STAN_GRAD : 3
         * Y : 32.58
         * X : 116.78
         */

        private String GUID;
        private String ORG_NAME;
        private String STAN_GRAD_NAME;
        private String STAN_GRAD;
        private String Y;
        private String X;

        public String getGUID() {
            return GUID == null ? "" : GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getORG_NAME() {
            return ORG_NAME == null ? "" : ORG_NAME;
        }

        public void setORG_NAME(String ORG_NAME) {
            this.ORG_NAME = ORG_NAME;
        }

        public String getSTAN_GRAD_NAME() {
            return STAN_GRAD_NAME == null ? "" : STAN_GRAD_NAME;
        }

        public void setSTAN_GRAD_NAME(String STAN_GRAD_NAME) {
            this.STAN_GRAD_NAME = STAN_GRAD_NAME;
        }

        public String getSTAN_GRAD() {
            return STAN_GRAD == null ? "" : STAN_GRAD;
        }

        public void setSTAN_GRAD(String STAN_GRAD) {
            this.STAN_GRAD = STAN_GRAD;
        }

        public String getY() {
            return Y == null ? "" : Y;
        }

        public void setY(String y) {
            Y = y;
        }

        public String getX() {
            return X == null ? "" : X;
        }

        public void setX(String x) {
            X = x;
        }
    }
    public static class BookOrgBean implements Serializable{

        /**
         * CX : 16
         * CQ : 26
         * ZC : 49
         */

        private String CX;
        private String CQ;
        private String ZC;

        public String getCX() {
            return CX == null ? "" : CX;
        }

        public void setCX(String CX) {
            this.CX = CX;
        }

        public String getCQ() {
            return CQ == null ? "" : CQ;
        }

        public void setCQ(String CQ) {
            this.CQ = CQ;
        }

        public String getZC() {
            return ZC == null ? "" : ZC;
        }

        public void setZC(String ZC) {
            this.ZC = ZC;
        }
    }
    public static class CountOrgBean implements Serializable{

        /**
         * SJ : 17
         * ER : 25
         * YJ : 11
         */

        private String SJ;
        private String ER;

        public String getSJ() {
            return SJ == null ? "" : SJ;
        }

        public void setSJ(String SJ) {
            this.SJ = SJ;
        }

        public String getER() {
            return ER == null ? "" : ER;
        }

        public void setER(String ER) {
            this.ER = ER;
        }

        public String getYJ() {
            return YJ == null ? "" : YJ;
        }

        public void setYJ(String YJ) {
            this.YJ = YJ;
        }

        private String YJ;

    }
}
