package com.syberos.shuili.service.dataprovider.sync.synchronizer.impl;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;

import com.syberos.shuili.service.MessageInfoEntity;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dataprovider.DataProvider;
import com.syberos.shuili.service.dataprovider.dbconfig.DBConfFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jidan on 18-3-19.
 */

public class MessageSynchronizer extends SynchronizerBase {
    /**
     * 构造函数
     *
     * @param context Context
     */
    public MessageSynchronizer(Context context) {
        super(context);
    }

    @Override
    public void initLocalStorage() {
        List<TableConfig> tableList =  DBConfFactory.getDBConfigManager(context).getTables(DataOperationType.Message_Info);
        for(TableConfig config :tableList) {
            createTable(config.getName(), config.getFieldList());
        }
    }

    @Override
    public void syncData(Bundle bundle) {
        List<MessageInfoEntity> infos = bundle.getParcelableArrayList("data");
        dbHelper.beginTransaction();
        int size = infos.size();
        String[] column = {DBDefinition.messageId};
        String where = DBDefinition.messageId + " = ?" + " and " + DBDefinition.isDelete + " = 0";
        String[] selectionArgs;
        for(int i = 0 ; i < size; i++){
            MessageInfoEntity info = infos.get(i);
            selectionArgs = new String[]{info.messageId};
            List list = dbHelper.query(DBDefinition.MESSAGE_TABLE, column, where, selectionArgs);
            if (list == null || list.isEmpty()) {
                ContentValues values = new ContentValues();
                values.put(DBDefinition.messageId,info.messageId);
                values.put(DBDefinition.title ,info.title);
                values.put(DBDefinition.content,info.content);
                values.put(DBDefinition.publisher,info.publisher);
                values.put(DBDefinition.publishDate,info.publishDate);
                values.put(DBDefinition.readStatus,"0");
                values.put(DBDefinition.serverDate,info.serverDate);
                values.put(DBDefinition.organizationId,info.organizationId);
                values.put(DBDefinition.isDelete,"0");
                dbHelper.insert(DBDefinition.MESSAGE_TABLE,values);
            }else{
                // TODO: 18-3-19 do nothing
            }
        }
        dbHelper.setTransactionSuccessful();
        dbHelper.endTransaction();
        Message message = new Message();
        message.what = DataProvider.message_what_getoff_messageInfo;
        message.setData(bundle);
        mHandler.sendMessage(message);

    }

    @Override
    public void deleteData(Object data) {
        ArrayList<String> messageIds = (ArrayList)data;
        String where = DBDefinition.messageId + " = ?";
        String[] selectionArgs;
        for(String id :messageIds){
            selectionArgs = new String[]{id};
            ContentValues values = new ContentValues();
            values.put(DBDefinition.isDelete,"1");
            dbHelper.update(DBDefinition.MESSAGE_TABLE,values,where,selectionArgs);
        }

    }

    @Override
    public void setInfoStatus(Object data) {
        ArrayList<String> messageIds = (ArrayList)data;
        String where = DBDefinition.messageId + " = ?";
        String[] selectionArgs;
        for(String id :messageIds){
            selectionArgs = new String[]{id};
            ContentValues values = new ContentValues();
            values.put(DBDefinition.readStatus,"1");
            dbHelper.update(DBDefinition.MESSAGE_TABLE,values,where,selectionArgs);
        }
    }

}
