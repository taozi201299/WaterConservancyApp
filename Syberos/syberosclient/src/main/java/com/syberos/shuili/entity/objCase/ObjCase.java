package com.syberos.shuili.entity.objCase;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/23.
 */

/**
 * 监督执法 案件信息对象
 */
public class ObjCase extends HttpBaseResponse<ObjCase> {
    public String guid;
    public String caseName;
    /**
     * 立案时间
     */
    public String filiTime;
    /**
     * 执法单位
     */
    public String suneOrgGuidName;
    /**
     * 案件当事人名称
     */
    public String caseLitiName;
    /**
     * 案件办理进度
     */
    public String caseProg;
    /**
     * 案卷号
     */
    public String caseNum;
    /**
     * 案件基本情况
     */
    public String caseSitu;
    public String contra1;
    public String contra2;
}
