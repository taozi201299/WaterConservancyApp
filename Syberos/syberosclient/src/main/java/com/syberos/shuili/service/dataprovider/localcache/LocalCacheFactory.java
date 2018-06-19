package com.syberos.shuili.service.dataprovider.localcache;

import android.content.Context;

/**
 * Created by helpzhl on 16-7-5.
 * LocalCacheFactory
 */
public class LocalCacheFactory {

    /**
     * 单例对象
     */
    private static ILocalCacheManager INSTANCE = null;

    /**
     * 获取CacheManager对象
     * @param context Context
     * @return ILocalCacheManager
     */
    public static ILocalCacheManager getLocalCacheManager(Context context){
        if(INSTANCE == null)
            INSTANCE = new LocalCacheManagerImpl(context);
        return INSTANCE;
    }
}
