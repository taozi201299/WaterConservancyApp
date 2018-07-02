package com.syberos.shuili.service.dataprovider.sync;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.ISynchronizer;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by helpzhl on 16-6-14.
 * SyncManager
 */

public class SyncManagerImpl implements ISyncManager {

    /**
     * Context
     */
    private Context context = null;


    /**
     * 构造函数
     * @param context Context
     */
    public SyncManagerImpl(Context context){
        this.context = context;

    }
    /**
     * 初始化表
     */
    public void initTables(DBHelper helper){
        List<ISynchronizer> synchronizerList = SynchronizerFactory.getAllSynchronizer(context);
        for (ISynchronizer synchronizer:synchronizerList) {
            if(synchronizer == null)continue;
            synchronizer.setDBAdapterListener(helper);
            synchronizer.initLocalStorage();
        }
    }

    @Override
    public void syncUserInfo(Bundle bundle) {
        ISynchronizer synchronizer  = SynchronizerFactory.getSynchronizer(DataOperationType.USER_INFO,context);
        synchronizer.syncData(bundle);
    }


    @Override
    public void setDBAdapter(DBHelper helper) {
        List<ISynchronizer> synchronizerList = SynchronizerFactory.getAllSynchronizer(context);
        for (ISynchronizer synchronizer:synchronizerList) {
            if(synchronizer == null)continue;
            synchronizer.setDBAdapterListener(helper);
        }
    }

    @Override
    public void syncMessage(Bundle bundle) {
        ISynchronizer synchronizer  = SynchronizerFactory.getSynchronizer(DataOperationType.Message_Info,context);
        synchronizer.syncData(bundle);
    }

    @Override
    public void setHandler(Handler handler) {
        List<ISynchronizer> synchronizerList = SynchronizerFactory.getAllSynchronizer(context);
        for (ISynchronizer synchronizer:synchronizerList) {
            if(synchronizer == null)return;
            synchronizer.setHandler(handler);
        }
    }

    @Override
    public void deleteInfo(List<String> ids, DataOperationType type) {
        ISynchronizer synchronizer  = SynchronizerFactory.getSynchronizer(type,context);
        synchronizer.deleteData(ids);
    }

    @Override
    public void setInfoStatus(List<String> ids, DataOperationType type) {
        ISynchronizer synchronizer  = SynchronizerFactory.getSynchronizer(type,context);
        synchronizer.deleteData(ids);
    }

    @Override
    public void syncMapInfo(Map params, DataOperationType type) {
        ISynchronizer synchronizer  = SynchronizerFactory.getSynchronizer(type,context);
        synchronizer.synsMapInfo(params);
    }


}

