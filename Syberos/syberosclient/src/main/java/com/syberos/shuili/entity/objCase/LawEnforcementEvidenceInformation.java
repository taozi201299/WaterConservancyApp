package com.syberos.shuili.entity.objCase;

import java.io.Serializable;

/**
 * created by：toby on 18-4-16 11:17
 * email：zhaodongshuang@syberos.com
 * 监督执法 ——> 案件详情 ——> 执法证据详情
 */
public class LawEnforcementEvidenceInformation implements Serializable {

    public final static int EVIDENCE_TYPE_0 = 0;     // 现场处理措施决定书
    public final static int EVIDENCE_TYPE_1 = 1;     // 责令限期整改指令书
    public final static int EVIDENCE_TYPE_2 = 2;     // 查封扣押决定书
    public final static int EVIDENCE_TYPE_3 = 3;     // 行政（当场）处罚决定书（单位）
    public final static int EVIDENCE_TYPE_4 = 4;     // 行政（当场）处罚决定书（个人）
    public final static int EVIDENCE_TYPE_5 = 5;     // 创建送达回执

    private int type;               // 执法类型
    private String remark;          // 备注说明
    private String time;            // 时间

    // TODO: 18-4-16 需要添加照片、视频内容记录

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
