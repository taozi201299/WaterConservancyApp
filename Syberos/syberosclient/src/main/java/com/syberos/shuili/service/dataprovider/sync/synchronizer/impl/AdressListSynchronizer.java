package com.syberos.shuili.service.dataprovider.sync.synchronizer.impl;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;

import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dataprovider.dbconfig.DBConfFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jidan on 18-3-12.
 */
public class AdressListSynchronizer extends SynchronizerBase {
        /**
         * 构造函数
         *
         * @param context Context
         */
        public AdressListSynchronizer(Context context) {
            super(context);
        }

        /**
         * 重写
         * 初始化本地存储
         */
        @Override
        public void initLocalStorage() {
            List<TableConfig> tableList =  DBConfFactory.getDBConfigManager(context).getTables(DataOperationType.USER_INFO);
            for(TableConfig config :tableList) {
                createTable(config.getName(), config.getFieldList());
            }
        }

    @Override
    public void syncData(Bundle bundle) {
        dbHelper.deleteAll(DBDefinition.USER_TABLE);
        List<UserInformationEntity> infos = bundle.getParcelableArrayList("data");
        dbHelper.beginTransaction();
        int size = infos.size();
        for(int i = 0 ; i < size; i++){
            UserInformationEntity info = infos.get(i);
            ContentValues values = new ContentValues();
            values.put(DBDefinition.id,info.id);
            values.put(DBDefinition.admDutyLevel ,info.admDutyLevel);
            values.put(DBDefinition.depCode,info.depCode);
            values.put(DBDefinition.depId,info.depId);
            values.put(DBDefinition.depName,info.depName);
            values.put(DBDefinition.modifier,info.modifier);
            values.put(DBDefinition.note,info.note);
            values.put(DBDefinition.orgCode,info.orgCode);
            values.put(DBDefinition.orgId,info.orgId);
            values.put(DBDefinition.orgName,info.orgName);
            values.put(DBDefinition.persId,info.persId);
            values.put(DBDefinition.persName ,info.persName);
            values.put(DBDefinition.persType,info.persType);
            values.put(DBDefinition.phone,info.phone);
            values.put(DBDefinition.status,info.status);
            values.put(DBDefinition.ts,info.ts);
            values.put(DBDefinition.note,info.note);
            values.put(DBDefinition.userCode,info.userCode);
            values.put(DBDefinition.userName,info.userName);
            values.put(DBDefinition.userType,info.userType);
            dbHelper.insert(DBDefinition.USER_TABLE,values);

        }
        dbHelper.setTransactionSuccessful();
        dbHelper.endTransaction();


    }

    @Override
    public void deleteData(Object data) {
        ArrayList<String> messageIds = (ArrayList)data;
        String whereClause = DBDefinition.messageId + " =? ";
        String[] whereArgs ;
        for(String id :messageIds){
            whereArgs = new String[]{id};
            dbHelper.delete(DBDefinition.MESSAGE_TABLE,whereClause,whereArgs);
        }

    }

    @Override
    public void setInfoStatus(Object data) {

    }

    @Override
    public void synsMapInfo(Object data) {

    }
}
