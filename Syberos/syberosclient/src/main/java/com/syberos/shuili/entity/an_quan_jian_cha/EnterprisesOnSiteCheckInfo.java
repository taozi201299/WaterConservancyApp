package com.syberos.shuili.entity.an_quan_jian_cha;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.List;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/21
 * @time: 9:33
 * @email: ZhaoDongshuang@syberos.com
 */
public class EnterprisesOnSiteCheckInfo
        extends HttpBaseResponse<EnterprisesOnSiteCheckInfo> {

    private String startTime;           // 开始时间
    private String endTime;             // 结束时间
    private String content;             // 检查内容

    private List<EECI_HiddenItemInfo> hiddenItemInfos;  // 可能存在隐患


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<EECI_HiddenItemInfo> getHiddenItemInfos() {
        return hiddenItemInfos;
    }

    public void setHiddenItemInfos(List<EECI_HiddenItemInfo> hiddenItemInfos) {
        this.hiddenItemInfos = hiddenItemInfos;
    }


}
