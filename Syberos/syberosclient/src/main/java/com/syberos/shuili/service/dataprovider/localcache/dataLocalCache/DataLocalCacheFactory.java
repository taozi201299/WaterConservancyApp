package com.syberos.shuili.service.dataprovider.localcache.dataLocalCache;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.localcache.ILocalCacheListener;
import com.syberos.shuili.service.dataprovider.localcache.dataLocalCache.impl.BusinessdataLocalCache;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by jidan on 18-3-8.
 */
public class DataLocalCacheFactory {
    private static BusinessdataLocalCache dataLocalCache = null;

    /**
     * 获取业务Cache管理对象
     * @param context Context
     * @param type 业务类型那
     * @return IDataLocalCache
     */
    public static IDataLocalCache getLocalCache(Context context, DataOperationType type){
        IDataLocalCache localCache = null;
        switch (type){
            case BUSINESS_SUBMIT:
                if(dataLocalCache == null){
                    dataLocalCache = new BusinessdataLocalCache(context);
                }
                localCache = dataLocalCache;
                break;
        }
        return localCache;
    }

    /**
     * 获取全部业务Cache管理对象map
     * @param context Context
     * @return Map<DataOperationType, IDataLocalCache>
     */
    public static Map<DataOperationType, IDataLocalCache> getAllDataLocalCacheMap(Context context){
        Map<DataOperationType, IDataLocalCache> map = new HashMap<>();
        DataOperationType[] dops = DataOperationType.values();
        for (DataOperationType type:dops) {
            map.put(type,getLocalCache(context, type));
        }
        return map;
    }
}
