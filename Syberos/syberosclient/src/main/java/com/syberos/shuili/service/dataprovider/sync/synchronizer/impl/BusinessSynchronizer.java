package com.syberos.shuili.service.dataprovider.sync.synchronizer.impl;

import android.content.Context;
import android.os.Bundle;

import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.DBConfFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerBase;

import java.util.List;


/**
 * Created by jidan on 16-8-6
 * BusinessSynchronizer.
 */
public class BusinessSynchronizer extends SynchronizerBase {
    /**
     * 构造函数
     *
     * @param context Context
     */
    public BusinessSynchronizer(Context context) {
        super(context);
    }

    /**
     * 重写
     * 初始化本地存储
     */
    @Override
    public void initLocalStorage() {
        List<TableConfig> tableList =  DBConfFactory.getDBConfigManager(context).getTables(DataOperationType.BUSINESS_SUBMIT);
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
