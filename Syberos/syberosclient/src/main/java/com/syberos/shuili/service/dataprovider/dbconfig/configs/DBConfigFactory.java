package com.syberos.shuili.service.dataprovider.dbconfig.configs;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.IDBConfig;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;



/**
 * Created by jidan on 18-3-7.
 */
public class DBConfigFactory {

    /**
     * 根据操作类型DataOperationType 获取数据库配置的方法
     *
     * @param type    DataOperationType
     * @param context context
     * @return IDBConfig的实现类
     */
    public static IDBConfig getDBConfig(DataOperationType type, Context context) {

        IDBConfig iConfig = null;

        switch (type) {
            case BUSINESS_SUBMIT:
                iConfig = new BusinessCommitConfig(context);
                break;
            case USER_INFO:
                iConfig = new AddressListConfig(context);
                break;
            case Message_Info:
                iConfig = new MessageConfig(context);
                break;
            case BINARY:
                iConfig = new BinaryCommitConfig(context);
                break;
            case Map_Info:
                iConfig = new MapConfig(context);
                break;
        }
        return iConfig;
    }
}
