package com.syberos.shuili.service.dataprovider.handler.datahandler;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;

import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataHandlerBase implements IDataHandler {


    private Context context;

    /**
     * 数据库访问对象
     */
    protected DBHelper dbHelper;

    protected Handler mHandler;

    /**
     * 构造方法，接受了一个context对象 并且注册了networkCallback
     *
     * @param context Context
     */
    public DataHandlerBase(Context context) {
        this.context = context;
    }

    @Override
    public List<UserInformationEntity> offLineRequest(int type, Map params, IAddressListCallback callback) {
        return null;
    }

    @Override
    public void onLineRequest(Map params, IAddressListCallback callback) {

    }

    @Override
    public void setDBAdapter(DBHelper helper) {
        this.dbHelper = helper;

    }

    @Override
    public void setHandler(Handler handler) {
        mHandler = handler;

    }

    public void offLineRequest(Map params,Object callback){};
    public void onLineRequest(Map params,Object callback){};
}
