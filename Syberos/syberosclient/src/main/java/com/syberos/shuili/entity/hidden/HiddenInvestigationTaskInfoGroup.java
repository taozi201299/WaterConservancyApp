package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * created by：toby on 18-4-19 15:50
 * email：zhaodongshuang@syberos.com
 */
public class HiddenInvestigationTaskInfoGroup
        extends HttpBaseResponse<HiddenInvestigationTaskInfoGroup> {

    private String header;
    private List<HiddenInvestigationTaskInfo> children;

    public HiddenInvestigationTaskInfoGroup(String header,
                                            List<HiddenInvestigationTaskInfo> children) {
        this.header = header;
        this.children = children;
    }


    public String getHeader() {
        return header == null ? "" : header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<HiddenInvestigationTaskInfo> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<HiddenInvestigationTaskInfo> children) {
        this.children = children;
    }
}
