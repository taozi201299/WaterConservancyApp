package com.syberos.shuili.service.dataprovider.sync.synchronizer.impl;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;

import com.syberos.shuili.service.dataprovider.dbconfig.DBConfFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */

public class MapSynchronizer extends SynchronizerBase {
    /**
     * 构造函数
     *
     * @param context Context
     */
    public MapSynchronizer(Context context) {
        super(context);
    }

    @Override
    public void initLocalStorage() {
        List<TableConfig> tableList =  DBConfFactory.getDBConfigManager(context).getTables(DataOperationType.Map_Info);
        for(TableConfig config :tableList) {
            createTable(config.getName(), config.getFieldList());
        }
    }

    @Override
    public void syncData(Bundle bundle) {

    }

    @Override
    public void deleteData(Object data) {

    }

    @Override
    public void setInfoStatus(Object data) {

    }

    @Override
    public void synsMapInfo(Object data) {
        dbHelper.beginTransaction();
        HashMap<String,String> map  = (HashMap<String, String>) data;
        String mapUrl = map.get("url");
        String serviceID = map.get("serviceID");
        String mapContent = map.get("content");
        ContentValues values = new ContentValues();
        values.put(DBDefinition.mapUrl,mapUrl);
        values.put(DBDefinition.serviceID ,serviceID);
        values.put(DBDefinition.mapContent,mapContent);
        String where = DBDefinition.mapUrl + " = ?";
        String[] selectionArgs = new String[]{};
        ArrayList list = dbHelper.query(DBDefinition.MAP_TABLE,null,where,selectionArgs);
        if(list != null && list.size() !=0){
            dbHelper.update(DBDefinition.MAP_TABLE,values,where,selectionArgs);
        }else {
            dbHelper.insert(DBDefinition.MAP_TABLE, values);
        }
        dbHelper.setTransactionSuccessful();
        dbHelper.endTransaction();
    }
}
