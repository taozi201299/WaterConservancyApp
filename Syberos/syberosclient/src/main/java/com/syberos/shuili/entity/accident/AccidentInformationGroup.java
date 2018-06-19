package com.syberos.shuili.entity.accident;

import java.io.Serializable;
import java.util.ArrayList;

public class AccidentInformationGroup implements Serializable {

    private String header;
    private ArrayList<ObjAcci> children;

    public AccidentInformationGroup(String header, ArrayList<ObjAcci> children) {
        this.header = header;
        this.children = children;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<ObjAcci> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ObjAcci> children) {
        this.children = children;
    }
}
