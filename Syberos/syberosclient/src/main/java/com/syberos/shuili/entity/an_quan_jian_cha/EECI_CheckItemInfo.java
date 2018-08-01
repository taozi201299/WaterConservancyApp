package com.syberos.shuili.entity.an_quan_jian_cha;

import com.syberos.shuili.entity.HttpBaseResponse;

/**
 * created by：toby on 18-4-20 14:01
 * email：zhaodongshuang@syberos.com
 */
public class EECI_CheckItemInfo
        extends HttpBaseResponse<EECI_CheckItemInfo> {

    private String name;                    // 检查项名称
    private boolean isYesChecked = false;   // “是” 选项是否选中
    private boolean isNOChecked = false;    // “否” 选项是否选中
    private int count = -1;                 // 数量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isYesChecked() {
        return isYesChecked;
    }

    public void setYesChecked(boolean yesChecked) {
        isYesChecked = yesChecked;
    }

    public boolean isNOChecked() {
        return isNOChecked;
    }

    public void setNOChecked(boolean NOChecked) {
        isNOChecked = NOChecked;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
