package com.syberos.shuili.entity.bao_biao_guan_li;

import com.syberos.shuili.entity.HttpHiddenBaseResponse;

/**
 * created by：toby on 18-4-25 09:57
 * email：zhaodongshuang@syberos.com
 * 隐患报表
 */
public class HiddenDangerReport extends HttpHiddenBaseResponse<HiddenDangerReport> {

    public final static int BUTTON_REPORT = 0;      // 上报
    public final static int BUTTON_RECALL = 1;      // 撤回
    public final static int BUTTON_REPORTED = 2;    // 已上报
    public final static int BUTTON_RETURN = 3;      // 退回重报
    public final static int BUTTON_URGED = 4;       // 催报

    public final static int LINK_YES     = 1;    // 已上报
    public final static int LINK_RETURNED  = 3;    // 已撤销
    public final static int LINK_REFUNDED  = 2;    // 被退回
    public final static int LINK_NO = 2 ; // 未上报



    private String name;                // 名称
    private int buttonStatus;           // 按钮状态
    private int linkStatus;             // 链接状态


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(int buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public int getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(int linkStatus) {
        this.linkStatus = linkStatus;
    }

}
