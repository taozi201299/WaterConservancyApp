package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/8/15.
 * 稽察方案
 */

public class BisWinsProg extends HttpBaseResponse<BisWinsProg>
{
    String startTime;
    String winsProjType; // 稽查项目类型
    String bwpGuid;
    String winsType; // 稽查类型
    String winsGroupNum; // 稽查组数
    String winsArrayCode; // 稽查批次编码
    String endTime;

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getWinsProjType() {
        return winsProjType == null ? "" : winsProjType;
    }

    public void setWinsProjType(String winsProjType) {
        this.winsProjType = winsProjType;
    }

    public String getBwpGuid() {
        return bwpGuid == null ? "" : bwpGuid;
    }

    public void setBwpGuid(String bwpGuid) {
        this.bwpGuid = bwpGuid;
    }

    public String getWinsType() {
        return winsType == null ? "" : winsType;
    }

    public void setWinsType(String winsType) {
        this.winsType = winsType;
    }

    public String getWinsGroupNum() {
        return winsGroupNum == null ? "" : winsGroupNum;
    }

    public void setWinsGroupNum(String winsGroupNum) {
        this.winsGroupNum = winsGroupNum;
    }

    public String getWinsArrayCode() {
        return winsArrayCode == null ? "" : winsArrayCode;
    }

    public void setWinsArrayCode(String winsArrayCode) {
        this.winsArrayCode = winsArrayCode;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
