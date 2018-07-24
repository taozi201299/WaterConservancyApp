package com.example.testmodule.securitycloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZB on 2018/7/23.
 * Project: Syberos.
 * Package：com.example.testmodule.securitycloud.
 */
public class StraightTubeManageEntry implements Serializable{
    int score;
    int trainingPersonCount;//培训总人数
    int perTrainingHours;// 平均学时数
    //        检查报表：已上报xx  未上报 xx
//        考核自评表：已上报xx 未上报xx
//        隐患报表：已上报xx 未上报xx
//        事故报表：已上报xx 未上报xx
    List<ReportInfoItemEntry> dataList;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTrainingPersonCount() {
        return trainingPersonCount;
    }

    public void setTrainingPersonCount(int trainingPersonCount) {
        this.trainingPersonCount = trainingPersonCount;
    }

    public int getPerTrainingHours() {
        return perTrainingHours;
    }

    public void setPerTrainingHours(int perTrainingHours) {
        this.perTrainingHours = perTrainingHours;
    }

    public List<ReportInfoItemEntry> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<ReportInfoItemEntry> dataList) {
        this.dataList = dataList;
    }

    public static class ReportInfoItemEntry implements Serializable {
        int partReportCount;
        int partUnReportCount;

        public int getPartReportCount() {
            return partReportCount;
        }

        public void setPartReportCount(int partReportCount) {
            this.partReportCount = partReportCount;
        }

        public int getPartUnReportCount() {
            return partUnReportCount;
        }

        public void setPartUnReportCount(int partUnReportCount) {
            this.partUnReportCount = partUnReportCount;
        }
    }

}
