package com.syberos.shuili.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class AccidentInformationGroup implements Serializable {

    private String header;
    private ArrayList<AccidentInformation> children;

    public AccidentInformationGroup( String header, ArrayList<AccidentInformation> children) {
        this.header = header;
        this.children = children;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<AccidentInformation> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<AccidentInformation> children) {
        this.children = children;
    }
}
