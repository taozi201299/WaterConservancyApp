package com.syberos.shuili.entity.wins;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/30.
 * 水利稽查组 BIS_WINS_GROUP
 */

public class BisWinsGroup extends HttpBaseResponse<BisWinsGroup> {
    String winsPost;
    String speStafName; //特派员姓名
    String winsGroupNum; // 2)	组编号
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

    public String getWinsGroupNum() {
        return winsGroupNum == null ? "" : winsGroupNum;
    }

    public void setWinsGroupNum(String winsGroupNum) {
        this.winsGroupNum = winsGroupNum;
    }

    public String getBwgGuid() {
        return bwgGuid == null ? "" : bwgGuid;
    }

    public void setBwgGuid(String bwgGuid) {
        this.bwgGuid = bwgGuid;
    }
}
