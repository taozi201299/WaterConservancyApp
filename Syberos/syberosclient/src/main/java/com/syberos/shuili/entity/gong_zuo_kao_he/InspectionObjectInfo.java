package com.syberos.shuili.entity.gong_zuo_kao_he;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.List;

/**
 * created by：toby on 18-4-23 10:00
 * email：zhaodongshuang@syberos.com
 * 工作考核：现场检查:考核对象信息
 */
public class InspectionObjectInfo extends HttpBaseResponse<InspectionObjectInfo> {

    private String name;                // 被考核对象名称

    // 重大隐患情况
    private int hiddenCount;            // 重大隐患情况:数量
    private int hiddenNotCorrected;     // 重大隐患情况:未整改
    private int hiddenOverdue;          // 重大隐患情况:逾期未整改
    private int hiddenChanging;         // 重大隐患情况:整改中
    private int hiddenSupervision;      // 重大隐患情况:隐患督办

    // 事故情况
    private int accidentNormal;         // 事故情况:一般事故
    private int accidentBig;            // 事故情况:特别重大事故

    // 重大危险源情况
    private int dangerSourceCount;      // 重大危险源情况:数量
    private int dangerSourceRecord;     // 重大危险源情况:备案数量

    // 培训记录
    private List<TrainingRecordInfo> trainingRecordInfoList;

    // 考核自评情况
    private int score;                  // 评分



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHiddenCount() {
        return hiddenCount;
    }

    public void setHiddenCount(int hiddenCount) {
        this.hiddenCount = hiddenCount;
    }

    public int getHiddenNotCorrected() {
        return hiddenNotCorrected;
    }

    public void setHiddenNotCorrected(int hiddenNotCorrected) {
        this.hiddenNotCorrected = hiddenNotCorrected;
    }

    public int getHiddenOverdue() {
        return hiddenOverdue;
    }

    public void setHiddenOverdue(int hiddenOverdue) {
        this.hiddenOverdue = hiddenOverdue;
    }

    public int getHiddenChanging() {
        return hiddenChanging;
    }

    public void setHiddenChanging(int hiddenChanging) {
        this.hiddenChanging = hiddenChanging;
    }

    public int getHiddenSupervision() {
        return hiddenSupervision;
    }

    public void setHiddenSupervision(int hiddenSupervision) {
        this.hiddenSupervision = hiddenSupervision;
    }

    public int getAccidentNormal() {
        return accidentNormal;
    }

    public void setAccidentNormal(int accidentNormal) {
        this.accidentNormal = accidentNormal;
    }

    public int getAccidentBig() {
        return accidentBig;
    }

    public void setAccidentBig(int accidentBig) {
        this.accidentBig = accidentBig;
    }

    public int getDangerSourceCount() {
        return dangerSourceCount;
    }

    public void setDangerSourceCount(int dangerSourceCount) {
        this.dangerSourceCount = dangerSourceCount;
    }

    public int getDangerSourceRecord() {
        return dangerSourceRecord;
    }

    public void setDangerSourceRecord(int dangerSourceRecord) {
        this.dangerSourceRecord = dangerSourceRecord;
    }

    public List<TrainingRecordInfo> getTrainingRecordInfoList() {
        return trainingRecordInfoList;
    }

    public void setTrainingRecordInfoList(List<TrainingRecordInfo> trainingRecordInfoList) {
        this.trainingRecordInfoList = trainingRecordInfoList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
