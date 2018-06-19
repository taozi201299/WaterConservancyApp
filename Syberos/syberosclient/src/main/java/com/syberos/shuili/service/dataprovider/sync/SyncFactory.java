package com.syberos.shuili.service.dataprovider.sync;

import android.content.Context;


/**
 * Created by helpzhl on 16-7-5.
 * SyncFactory
 */
public class SyncFactory {
    /**
     * 单例对象
     */
    private static ISyncManager INSTANCE = null;

    /**
     * 获取SyncManager对象
     * @param context Context
     * @return ISyncManager
     */
    public static ISyncManager getSyncManager(Context context) {
        if(INSTANCE == null)
            INSTANCE = new SyncManagerImpl(context);
        return INSTANCE;
    }
}
