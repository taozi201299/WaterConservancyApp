package com.syberos.shuili.entity.accident;

import java.io.Serializable;
import java.util.ArrayList;

public class AccidentInfoGroup implements Serializable {

    private String header;
    private ArrayList<ObjAcciReport.DataBean> children;

    public AccidentInfoGroup(String header, ArrayList<ObjAcciReport.DataBean> children) {
        this.header = header;
        this.children = children;
    }


    public String getHeader() {
        return header == null ? "" : header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<ObjAcciReport.DataBean> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(ArrayList<ObjAcciReport.DataBean> children) {
        this.children = children;
    }
}
