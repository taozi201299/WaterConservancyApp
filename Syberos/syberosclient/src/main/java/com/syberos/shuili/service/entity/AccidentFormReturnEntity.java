package com.syberos.shuili.service.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/17.
 */

public class AccidentFormReturnEntity implements Serializable {

    /**
     * message : 请求成功
     * status : REQ001
     * data : {"code":"0","msg":"数据添加成功","totalCount":0,"data":{"currentPage":null,"pageSize":null,"sSearch":null,"sEcho":0,"iDisplayStart":null,"iDisplayLength":null,"files":null,"hf":null,"guid":"147b4cc1aa374e4ea91d84d17c94570b","acciWiunGuid":"13B9B5C0FA6C425891A7F8DECF86A24A","engGuid":null,"tendGuid":null,"acciWiunType":"1","acciCate":null,"occuTime":"2018-10-08 16:05:04","occuLoc":null,"missNum":null,"serInjNum":"0","casNum":"0","econLoss":"0","acciSitu":null,"rescTreaMeas":null,"contaPers":null,"offiTel":null,"repStat":"0","note":"移动端接口测试","collTime":"2018-10-08 16:05:26","updTime":null,"recPers":"10bd94958c5c480e8177c043881e0212","pGuid":null,"acciGrad":"1","acciInveDto":null},"detailMsg":null}
     */

    private String message;
    private String status;
    private DataBeanX data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * code : 0
         * msg : 数据添加成功
         * totalCount : 0
         * data : {"currentPage":null,"pageSize":null,"sSearch":null,"sEcho":0,"iDisplayStart":null,"iDisplayLength":null,"files":null,"hf":null,"guid":"147b4cc1aa374e4ea91d84d17c94570b","acciWiunGuid":"13B9B5C0FA6C425891A7F8DECF86A24A","engGuid":null,"tendGuid":null,"acciWiunType":"1","acciCate":null,"occuTime":"2018-10-08 16:05:04","occuLoc":null,"missNum":null,"serInjNum":"0","casNum":"0","econLoss":"0","acciSitu":null,"rescTreaMeas":null,"contaPers":null,"offiTel":null,"repStat":"0","note":"移动端接口测试","collTime":"2018-10-08 16:05:26","updTime":null,"recPers":"10bd94958c5c480e8177c043881e0212","pGuid":null,"acciGrad":"1","acciInveDto":null}
         * detailMsg : null
         */

        private String code;
        private String msg;
        private int totalCount;
        private DataBean data;
        private Object detailMsg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public Object getDetailMsg() {
            return detailMsg;
        }

        public void setDetailMsg(Object detailMsg) {
            this.detailMsg = detailMsg;
        }

        public static class DataBean  implements Serializable{
            /**
             * currentPage : null
             * pageSize : null
             * sSearch : null
             * sEcho : 0
             * iDisplayStart : null
             * iDisplayLength : null
             * files : null
             * hf : null
             * guid : 147b4cc1aa374e4ea91d84d17c94570b
             * acciWiunGuid : 13B9B5C0FA6C425891A7F8DECF86A24A
             * engGuid : null
             * tendGuid : null
             * acciWiunType : 1
             * acciCate : null
             * occuTime : 2018-10-08 16:05:04
             * occuLoc : null
             * missNum : null
             * serInjNum : 0
             * casNum : 0
             * econLoss : 0
             * acciSitu : null
             * rescTreaMeas : null
             * contaPers : null
             * offiTel : null
             * repStat : 0
             * note : 移动端接口测试
             * collTime : 2018-10-08 16:05:26
             * updTime : null
             * recPers : 10bd94958c5c480e8177c043881e0212
             * pGuid : null
             * acciGrad : 1
             * acciInveDto : null
             */

            private Object currentPage;
            private Object pageSize;
            private Object sSearch;
            private int sEcho;
            private Object iDisplayStart;
            private Object iDisplayLength;
            private Object files;
            private Object hf;
            private String guid;
            private String acciWiunGuid;
            private Object engGuid;
            private Object tendGuid;
            private String acciWiunType;
            private Object acciCate;
            private String occuTime;
            private Object occuLoc;
            private Object missNum;
            private String serInjNum;
            private String casNum;
            private String econLoss;
            private Object acciSitu;
            private Object rescTreaMeas;
            private Object contaPers;
            private Object offiTel;
            private String repStat;
            private String note;
            private String collTime;
            private Object updTime;
            private String recPers;
            private Object pGuid;
            private String acciGrad;
            private Object acciInveDto;

            public Object getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(Object currentPage) {
                this.currentPage = currentPage;
            }

            public Object getPageSize() {
                return pageSize;
            }

            public void setPageSize(Object pageSize) {
                this.pageSize = pageSize;
            }

            public Object getSSearch() {
                return sSearch;
            }

            public void setSSearch(Object sSearch) {
                this.sSearch = sSearch;
            }

            public int getSEcho() {
                return sEcho;
            }

            public void setSEcho(int sEcho) {
                this.sEcho = sEcho;
            }

            public Object getIDisplayStart() {
                return iDisplayStart;
            }

            public void setIDisplayStart(Object iDisplayStart) {
                this.iDisplayStart = iDisplayStart;
            }

            public Object getIDisplayLength() {
                return iDisplayLength;
            }

            public void setIDisplayLength(Object iDisplayLength) {
                this.iDisplayLength = iDisplayLength;
            }

            public Object getFiles() {
                return files;
            }

            public void setFiles(Object files) {
                this.files = files;
            }

            public Object getHf() {
                return hf;
            }

            public void setHf(Object hf) {
                this.hf = hf;
            }

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getAcciWiunGuid() {
                return acciWiunGuid;
            }

            public void setAcciWiunGuid(String acciWiunGuid) {
                this.acciWiunGuid = acciWiunGuid;
            }

            public Object getEngGuid() {
                return engGuid;
            }

            public void setEngGuid(Object engGuid) {
                this.engGuid = engGuid;
            }

            public Object getTendGuid() {
                return tendGuid;
            }

            public void setTendGuid(Object tendGuid) {
                this.tendGuid = tendGuid;
            }

            public String getAcciWiunType() {
                return acciWiunType;
            }

            public void setAcciWiunType(String acciWiunType) {
                this.acciWiunType = acciWiunType;
            }

            public Object getAcciCate() {
                return acciCate;
            }

            public void setAcciCate(Object acciCate) {
                this.acciCate = acciCate;
            }

            public String getOccuTime() {
                return occuTime;
            }

            public void setOccuTime(String occuTime) {
                this.occuTime = occuTime;
            }

            public Object getOccuLoc() {
                return occuLoc;
            }

            public void setOccuLoc(Object occuLoc) {
                this.occuLoc = occuLoc;
            }

            public Object getMissNum() {
                return missNum;
            }

            public void setMissNum(Object missNum) {
                this.missNum = missNum;
            }

            public String getSerInjNum() {
                return serInjNum;
            }

            public void setSerInjNum(String serInjNum) {
                this.serInjNum = serInjNum;
            }

            public String getCasNum() {
                return casNum;
            }

            public void setCasNum(String casNum) {
                this.casNum = casNum;
            }

            public String getEconLoss() {
                return econLoss;
            }

            public void setEconLoss(String econLoss) {
                this.econLoss = econLoss;
            }

            public Object getAcciSitu() {
                return acciSitu;
            }

            public void setAcciSitu(Object acciSitu) {
                this.acciSitu = acciSitu;
            }

            public Object getRescTreaMeas() {
                return rescTreaMeas;
            }

            public void setRescTreaMeas(Object rescTreaMeas) {
                this.rescTreaMeas = rescTreaMeas;
            }

            public Object getContaPers() {
                return contaPers;
            }

            public void setContaPers(Object contaPers) {
                this.contaPers = contaPers;
            }

            public Object getOffiTel() {
                return offiTel;
            }

            public void setOffiTel(Object offiTel) {
                this.offiTel = offiTel;
            }

            public String getRepStat() {
                return repStat;
            }

            public void setRepStat(String repStat) {
                this.repStat = repStat;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
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

            public String getRecPers() {
                return recPers;
            }

            public void setRecPers(String recPers) {
                this.recPers = recPers;
            }

            public Object getPGuid() {
                return pGuid;
            }

            public void setPGuid(Object pGuid) {
                this.pGuid = pGuid;
            }

            public String getAcciGrad() {
                return acciGrad;
            }

            public void setAcciGrad(String acciGrad) {
                this.acciGrad = acciGrad;
            }

            public Object getAcciInveDto() {
                return acciInveDto;
            }

            public void setAcciInveDto(Object acciInveDto) {
                this.acciInveDto = acciInveDto;
            }
        }
    }
}
