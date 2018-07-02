package com.syberos.shuili.service.dataprovider.handler.datahandler.impl;

import android.content.Context;

import com.syberos.shuili.service.SyberosService;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.handler.datahandler.DataHandlerBase;

import java.util.ArrayList;
import java.util.HashMap;
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
         String result = getUrl(params.get(DBDefinition.mapUrl).toString(),params.get(DBDefinition.serviceID).toString());
        ((SyberosService.StringCallBack)callback).sucess(result);
    }

    @Override
    public void onLineRequest(Map params, Object callback) {
        super.onLineRequest(params, callback);
    }
    private String getUrl(String url,String serviceId){
        String result = "";
        String where = DBDefinition.mapUrl + " = ? and " + DBDefinition.serviceID + " = ?";
        String[] selectionArgs = new String[]{url,serviceId};
        ArrayList<HashMap<String,String>> info = dbHelper.query(DBDefinition.MAP_TABLE,null,where,selectionArgs);
        for(HashMap map: info){
            result = (String) map.get(DBDefinition.mapContent);
        }
        return result;
    }
}
