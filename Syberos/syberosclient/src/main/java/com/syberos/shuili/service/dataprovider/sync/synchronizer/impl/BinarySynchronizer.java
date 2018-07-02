package com.syberos.shuili.service.dataprovider.sync.synchronizer.impl;

import android.content.Context;
import android.os.Bundle;

import com.syberos.shuili.service.dataprovider.dbconfig.DBConfFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerBase;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */

public class BinarySynchronizer extends SynchronizerBase {
    public BinarySynchronizer(Context context) {
        super(context);
    }
    /**
     * 重写
     * 初始化本地存储
     */
    @Override
    public void initLocalStorage() {
        List<TableConfig> tableList =  DBConfFactory.getDBConfigManager(context).getTables(DataOperationType.BINARY);
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

    }

}