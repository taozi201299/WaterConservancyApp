package com.syberos.shuili.entity.wins;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ZhaoDongshuang
 * @date: 2018/4/15
 * @time: 下午9:20
 * @email: ZhaoDongshuang@syberos.com
 */
public class InspectPlanGroup implements Serializable {

    private String header;
    private List<BisWinsProg> children;

    public InspectPlanGroup(String header, List<BisWinsProg> children) {
        this.header = header;
        this.children = children;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<BisWinsProg> getChildren() {
        return children;
    }

    public void setChildren(List<BisWinsProg> children) {
        this.children = children;
    }

}
