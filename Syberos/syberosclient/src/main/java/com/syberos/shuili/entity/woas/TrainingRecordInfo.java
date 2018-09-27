package com.syberos.shuili.entity.woas;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-23 10:09
 * email：zhaodongshuang@syberos.com
 * 工作考核：现场检查:考核对象:培训信息
 */
public class TrainingRecordInfo extends HttpBaseResponse<TrainingRecordInfo> {

    private String name;                // 培训名称
    private String count;               // 培训人数
    private String type;                // 培训类型
    private String time;                // 培训时间
    private String content;             // 培训内容


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count == null ? "" : count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
