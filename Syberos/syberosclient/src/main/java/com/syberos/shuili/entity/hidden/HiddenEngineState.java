package com.syberos.shuili.entity.hidden;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * Created by Administrator on 2018/4/24.
 */

public class HiddenEngineState extends HttpBaseResponse<HiddenEngineState> {
    String engStat;


    public String getEngStat() {
        return engStat == null ? "" : engStat;
    }

    public void setEngStat(String engStat) {
        this.engStat = engStat;
    }
}
