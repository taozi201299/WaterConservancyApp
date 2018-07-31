package com.syberos.shuili.entity.dangersource;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/7/20.
 * 危险源备案
 */


public class BisHazReg extends HttpBaseResponse<BisHazReg> {
    public String hazName;
    public String guid;;
    public String regTime;
    public String wiunName;
    public String ROWNO;
    public String collTime;
    public String hazStat;
    public String engName;
    public String writeOffTime;
}
