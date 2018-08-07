package com.syberos.shuili.entity.report;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ReportBaseEntity<T> implements Serializable {
   private String header;
   private ArrayList<T>children;

    public ReportBaseEntity(String header,ArrayList<T>children){
        this.header = header;
        this.children = children;
    }
    public ArrayList<T> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(ArrayList<T> children) {
        this.children = children;
    }


    public String getHeader() {
        return header == null ? "" : header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
