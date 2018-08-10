package com.syberos.shuili.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class NoticeFormInfo implements Serializable {
    public String userGuid;
    public boolean all;
    public List<String> list = new ArrayList<>();
}
