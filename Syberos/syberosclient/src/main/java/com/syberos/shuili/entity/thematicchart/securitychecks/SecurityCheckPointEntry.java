package com.syberos.shuili.entity.thematicchart.securitychecks;

import com.syberos.shuili.entity.thematicchart.PointEntry;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BZB on 2018/7/19.
 * Project: Syberos.
 * Package：com.syberos.shuili.entity.thematicchart.securitychecks.
 */
public class SecurityCheckPointEntry implements Serializable{
    //  (水利稽查|安全检查：流域（“1”）、监管(“2”)、直管(“3”),分本部数据("0"))
    Map<String, SecurityCheckInfoEntry> securityCheckInfoEntry;
}
