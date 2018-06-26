package com.syberos.shuili.service.dataprovider.handler.datahandler.impl;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.handler.datahandler.DataHandlerBase;

import java.util.Map;

/**
 * Created by Administrator on 2018/6/26.
 */

public class MapDataHandler extends DataHandlerBase {
    /**
     * 构造方法，接受了一个context对象 并且注册了networkCallback
     *
     * @param context Context
     */
    public MapDataHandler(Context context) {
        super(context);
    }

    @Override
    public void offLineRequest(Map params, Object callback) {
        super.offLineRequest(params, callback);
    }

    @Override
    public void onLineRequest(Map params, Object callback) {
        super.onLineRequest(params, callback);
    }
}
