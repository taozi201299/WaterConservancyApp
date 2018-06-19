package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * created by：toby on 18-4-16 17:41
 * email：zhaodongshuang@syberos.com
 * 监督执法 ——> 执法查询
 */
public class LawQueryInformation implements Serializable {

    private String title;       // 标题
    private String content;     // 内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
