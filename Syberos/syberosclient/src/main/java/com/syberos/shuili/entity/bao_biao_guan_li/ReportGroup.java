package com.syberos.shuili.entity.bao_biao_guan_li;

import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.basicbusiness.AttOrgExt;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/3.
 */

public class ReportGroup implements Serializable{
    String header;
    ArrayList<ReportForAdmin>childen;
    public ReportGroup(String header,ArrayList<ReportForAdmin>childen){
        this.header = header;
        this.childen = childen;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ArrayList<ReportForAdmin> getChilden() {
        return childen;
    }

    public void setChilden(ArrayList<ReportForAdmin> childen) {
        this.childen = childen;
    }
}
