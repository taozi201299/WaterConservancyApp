package com.syberos.shuili.service.dataprovider.dbconfig;

import android.content.Context;

/**
 * Created by jidan on 18-3-7.
 */
public class DBConfFactory {
    /**
     * 单例对象
     */
    private static IDBConfigManager INSTANCE = null;

    /**
     * 获取SyncManager对象
     * @return IDBConfigManager
     */
    public static IDBConfigManager getDBConfigManager(Context context) {
        if(INSTANCE == null)
            INSTANCE = new DBConfigManagerImpl(context);
        return INSTANCE;
    }
}
