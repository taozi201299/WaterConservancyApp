package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 水利稽察组 BIS_WINS_GROUP
 */

public class BisWinsGroup extends HttpBaseResponse<BisWinsGroup> {
    String winsPost;
    String speStafName; //特派员姓名

    public String getBwgWinsGroupNum() {
        return bwgWinsGroupNum == null ? "" : bwgWinsGroupNum;
    }

    public void setBwgWinsGroupNum(String bwgWinsGroupNum) {
        this.bwgWinsGroupNum = bwgWinsGroupNum;
    }

    String bwgWinsGroupNum; // 2)	组编号
    String bwgGuid;



    public String getWinsPost() {
        return winsPost == null ? "" : winsPost;
    }

    public void setWinsPost(String winsPost) {
        this.winsPost = winsPost;
    }

    public String getSpeStafName() {
        return speStafName == null ? "" : speStafName;
    }

    public void setSpeStafName(String speStafName) {
        this.speStafName = speStafName;
    }


    public String getBwgGuid() {
        return bwgGuid == null ? "" : bwgGuid;
    }

    public void setBwgGuid(String bwgGuid) {
        this.bwgGuid = bwgGuid;
    }
}
