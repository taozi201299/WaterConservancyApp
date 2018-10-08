package com.syberos.shuili.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class HiddenFormReturnEntity  implements Serializable{
    /**
     * message : 请求成功
     * status : REQ001
     * data : {"code":"0","msg":"数据添加成功","totalCount":0,"data":{"sEcho":0,"iDisplayStart":0,"iDisplayLength":0,"sSearch":null,"files":null,"hf":null,"guid":"e4f591705e1d42c59452d67486ccfbb2","hiddsGuid":null,"hiddName":"测试","engGuid":"6837D159F6BF40858FBD5A8C09C02AAA","tendGuid":"6837D159F6BF40858FBD5A8C09C02Dee","seGuid":null,"orgGuid":"13B9B5C0FA6C425891A7F8DECF86A24A","hiddSour":null,"hiddGrad":"0","hiddClas":null,"ifFound":null,"proPart":null,"partLong":null,"partLat":null,"hiddDesc":null,"occuNum":null,"inspRecGuid":null,"seCheckItemGuid":null,"hiddStat":"1","hiddMergGuid":null,"recOrgGuid":null,"note":"移动端测试","collTime":"2018-10-08 16:27:45","updTime":null,"recPers":"10bd94958c5c480e8177c043881e0212","bisHiddInveDto":[],"bisHiddVeriDto":[],"bisHiddMajVeriDto":[],"bisHiddRectImplDto":[],"bisHiddRectProgDto":[],"bisHiddRectAcceDto":[],"bisHiddMontRepDto":[],"bisMajHiddSupDto":[],"tendName":null,"engName":null,"inspDate":null,"inspOrgName":null,"inspOrgGuid":null,"files1":null},"detailMsg":null}
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
         * data : {"sEcho":0,"iDisplayStart":0,"iDisplayLength":0,"sSearch":null,"files":null,"hf":null,"guid":"e4f591705e1d42c59452d67486ccfbb2","hiddsGuid":null,"hiddName":"测试","engGuid":"6837D159F6BF40858FBD5A8C09C02AAA","tendGuid":"6837D159F6BF40858FBD5A8C09C02Dee","seGuid":null,"orgGuid":"13B9B5C0FA6C425891A7F8DECF86A24A","hiddSour":null,"hiddGrad":"0","hiddClas":null,"ifFound":null,"proPart":null,"partLong":null,"partLat":null,"hiddDesc":null,"occuNum":null,"inspRecGuid":null,"seCheckItemGuid":null,"hiddStat":"1","hiddMergGuid":null,"recOrgGuid":null,"note":"移动端测试","collTime":"2018-10-08 16:27:45","updTime":null,"recPers":"10bd94958c5c480e8177c043881e0212","bisHiddInveDto":[],"bisHiddVeriDto":[],"bisHiddMajVeriDto":[],"bisHiddRectImplDto":[],"bisHiddRectProgDto":[],"bisHiddRectAcceDto":[],"bisHiddMontRepDto":[],"bisMajHiddSupDto":[],"tendName":null,"engName":null,"inspDate":null,"inspOrgName":null,"inspOrgGuid":null,"files1":null}
         * detailMsg : null
         */

        private String code;
        private String msg;
        private int totalCount;
        private DataBean data;
        private String detailMsg;

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

        public String getDetailMsg() {
            return detailMsg;
        }

        public void setDetailMsg(String detailMsg) {
            this.detailMsg = detailMsg;
        }

        public static class DataBean implements Serializable {
            /**
             * sEcho : 0
             * iDisplayStart : 0
             * iDisplayLength : 0
             * sSearch : null
             * files : null
             * hf : null
             * guid : e4f591705e1d42c59452d67486ccfbb2
             * hiddsGuid : null
             * hiddName : 测试
             * engGuid : 6837D159F6BF40858FBD5A8C09C02AAA
             * tendGuid : 6837D159F6BF40858FBD5A8C09C02Dee
             * seGuid : null
             * orgGuid : 13B9B5C0FA6C425891A7F8DECF86A24A
             * hiddSour : null
             * hiddGrad : 0
             * hiddClas : null
             * ifFound : null
             * proPart : null
             * partLong : null
             * partLat : null
             * hiddDesc : null
             * occuNum : null
             * inspRecGuid : null
             * seCheckItemGuid : null
             * hiddStat : 1
             * hiddMergGuid : null
             * recOrgGuid : null
             * note : 移动端测试
             * collTime : 2018-10-08 16:27:45
             * updTime : null
             * recPers : 10bd94958c5c480e8177c043881e0212
             * bisHiddInveDto : []
             * bisHiddVeriDto : []
             * bisHiddMajVeriDto : []
             * bisHiddRectImplDto : []
             * bisHiddRectProgDto : []
             * bisHiddRectAcceDto : []
             * bisHiddMontRepDto : []
             * bisMajHiddSupDto : []
             * tendName : null
             * engName : null
             * inspDate : null
             * inspOrgName : null
             * inspOrgGuid : null
             * files1 : null
             */

            private int sEcho;
            private int iDisplayStart;
            private int iDisplayLength;
            private String sSearch;
            private String files;
            private String hf;
            private String guid;
            private String hiddsGuid;
            private String hiddName;
            private String engGuid;
            private String tendGuid;
            private String seGuid;
            private String orgGuid;
            private String hiddSour;
            private String hiddGrad;
            private String hiddClas;
            private String ifFound;
            private String proPart;
            private String partLong;
            private String partLat;
            private String hiddDesc;
            private String occuNum;
            private String inspRecGuid;
            private String seCheckItemGuid;
            private String hiddStat;
            private String hiddMergGuid;
            private String recOrgGuid;
            private String note;
            private String collTime;
            private String updTime;
            private String recPers;
            private String tendName;
            private String engName;
            private String inspDate;
            private String inspOrgName;
            private String inspOrgGuid;
            private String files1;

            public int getsEcho() {
                return sEcho;
            }

            public void setsEcho(int sEcho) {
                this.sEcho = sEcho;
            }

            public int getiDisplayStart() {
                return iDisplayStart;
            }

            public void setiDisplayStart(int iDisplayStart) {
                this.iDisplayStart = iDisplayStart;
            }

            public int getiDisplayLength() {
                return iDisplayLength;
            }

            public void setiDisplayLength(int iDisplayLength) {
                this.iDisplayLength = iDisplayLength;
            }

            public String getsSearch() {
                return sSearch == null ? "" : sSearch;
            }

            public void setsSearch(String sSearch) {
                this.sSearch = sSearch;
            }

            public String getFiles() {
                return files == null ? "" : files;
            }

            public void setFiles(String files) {
                this.files = files;
            }

            public String getHf() {
                return hf == null ? "" : hf;
            }

            public void setHf(String hf) {
                this.hf = hf;
            }

            public String getGuid() {
                return guid == null ? "" : guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getHiddsGuid() {
                return hiddsGuid == null ? "" : hiddsGuid;
            }

            public void setHiddsGuid(String hiddsGuid) {
                this.hiddsGuid = hiddsGuid;
            }

            public String getHiddName() {
                return hiddName == null ? "" : hiddName;
            }

            public void setHiddName(String hiddName) {
                this.hiddName = hiddName;
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

            public String getSeGuid() {
                return seGuid == null ? "" : seGuid;
            }

            public void setSeGuid(String seGuid) {
                this.seGuid = seGuid;
            }

            public String getOrgGuid() {
                return orgGuid == null ? "" : orgGuid;
            }

            public void setOrgGuid(String orgGuid) {
                this.orgGuid = orgGuid;
            }

            public String getHiddSour() {
                return hiddSour == null ? "" : hiddSour;
            }

            public void setHiddSour(String hiddSour) {
                this.hiddSour = hiddSour;
            }

            public String getHiddGrad() {
                return hiddGrad == null ? "" : hiddGrad;
            }

            public void setHiddGrad(String hiddGrad) {
                this.hiddGrad = hiddGrad;
            }

            public String getHiddClas() {
                return hiddClas == null ? "" : hiddClas;
            }

            public void setHiddClas(String hiddClas) {
                this.hiddClas = hiddClas;
            }

            public String getIfFound() {
                return ifFound == null ? "" : ifFound;
            }

            public void setIfFound(String ifFound) {
                this.ifFound = ifFound;
            }

            public String getProPart() {
                return proPart == null ? "" : proPart;
            }

            public void setProPart(String proPart) {
                this.proPart = proPart;
            }

            public String getPartLong() {
                return partLong == null ? "" : partLong;
            }

            public void setPartLong(String partLong) {
                this.partLong = partLong;
            }

            public String getPartLat() {
                return partLat == null ? "" : partLat;
            }

            public void setPartLat(String partLat) {
                this.partLat = partLat;
            }

            public String getHiddDesc() {
                return hiddDesc == null ? "" : hiddDesc;
            }

            public void setHiddDesc(String hiddDesc) {
                this.hiddDesc = hiddDesc;
            }

            public String getOccuNum() {
                return occuNum == null ? "" : occuNum;
            }

            public void setOccuNum(String occuNum) {
                this.occuNum = occuNum;
            }

            public String getInspRecGuid() {
                return inspRecGuid == null ? "" : inspRecGuid;
            }

            public void setInspRecGuid(String inspRecGuid) {
                this.inspRecGuid = inspRecGuid;
            }

            public String getSeCheckItemGuid() {
                return seCheckItemGuid == null ? "" : seCheckItemGuid;
            }

            public void setSeCheckItemGuid(String seCheckItemGuid) {
                this.seCheckItemGuid = seCheckItemGuid;
            }

            public String getHiddStat() {
                return hiddStat == null ? "" : hiddStat;
            }

            public void setHiddStat(String hiddStat) {
                this.hiddStat = hiddStat;
            }

            public String getHiddMergGuid() {
                return hiddMergGuid == null ? "" : hiddMergGuid;
            }

            public void setHiddMergGuid(String hiddMergGuid) {
                this.hiddMergGuid = hiddMergGuid;
            }

            public String getRecOrgGuid() {
                return recOrgGuid == null ? "" : recOrgGuid;
            }

            public void setRecOrgGuid(String recOrgGuid) {
                this.recOrgGuid = recOrgGuid;
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

            public String getTendName() {
                return tendName == null ? "" : tendName;
            }

            public void setTendName(String tendName) {
                this.tendName = tendName;
            }

            public String getEngName() {
                return engName == null ? "" : engName;
            }

            public void setEngName(String engName) {
                this.engName = engName;
            }

            public String getInspDate() {
                return inspDate == null ? "" : inspDate;
            }

            public void setInspDate(String inspDate) {
                this.inspDate = inspDate;
            }

            public String getInspOrgName() {
                return inspOrgName == null ? "" : inspOrgName;
            }

            public void setInspOrgName(String inspOrgName) {
                this.inspOrgName = inspOrgName;
            }

            public String getInspOrgGuid() {
                return inspOrgGuid == null ? "" : inspOrgGuid;
            }

            public void setInspOrgGuid(String inspOrgGuid) {
                this.inspOrgGuid = inspOrgGuid;
            }

            public String getFiles1() {
                return files1 == null ? "" : files1;
            }

            public void setFiles1(String files1) {
                this.files1 = files1;
            }
        }
    }
}
