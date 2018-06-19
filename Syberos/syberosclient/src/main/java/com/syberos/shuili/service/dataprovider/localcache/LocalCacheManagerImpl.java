package com.syberos.shuili.service.dataprovider.localcache;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dao.DBHelperFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.localcache.dataLocalCache.DataLocalCacheFactory;
import com.syberos.shuili.service.dataprovider.localcache.dataLocalCache.IDataLocalCache;
import com.syberos.shuili.utils.NetworkUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by jidan on 18-3-8.
 * 对外接口，本地数据的保存 提交
 */
public class LocalCacheManagerImpl implements ILocalCacheManager {

    /**
     * Context 上下文
     */
    private Context context = null;

    /**
     * 数据库操作对象
     */
    private DBHelper dbHelper = null;

    /**
     * 全部提交业务Cache模块列表
     */
    private Map<DataOperationType, IDataLocalCache> localCacheMap = null;


    /**
     * 构造函数
     * @param context Context
     */
    public LocalCacheManagerImpl(Context context){
        this.context = context;
    }

    @Override
    public boolean addCache(String url, Map params, Map files, IResultCallback callback) {
        DataOperationType type = DataOperationType.BUSINESS_SUBMIT;
        IDataLocalCache dataLocalCache = DataLocalCacheFactory.getLocalCache(context, type);
        dataLocalCache.addCache(url,params,files,callback);
        return true;
    }
    public boolean addCache(LocalCacheEntity localCacheEntity, List<AttachMentInfoEntity>attachMentInfoEntities,IResultCallback callback){
        DataOperationType type = DataOperationType.BUSINESS_SUBMIT;
        IDataLocalCache dataLocalCache = DataLocalCacheFactory.getLocalCache(context, type);
        dataLocalCache.addCache(localCacheEntity,attachMentInfoEntities,callback);
        return true;
    }

    @Override
    public void submitLocalCacheAll() {
        /**
         * 　Step 1. 检查数据库文件是否存在，不存在直接返回
         */
        String dbFullPath = "/sdcard" + "/" + DBHelperFactory.DB_NAME;
        File file = new File(dbFullPath);
        if(!file.exists()){
            return;
        }

        /**
         * Step 2. 检查数据库中的表个数是否 > 1，少于１个直接返回
         */
        String[] selectionArgs = new String[] {"table"};
        ArrayList<HashMap<String, String>> list = dbHelper.rawQuery("select name from sqlite_master where type=?", selectionArgs);
        if(list.size() <= 1){
            return;
        }

        /**
         * Step 3. 检查网络状态，无网络直接返回
         */
        if (!NetworkUtil.isNetworkAvailable()) {
            return;
        }

        /**
         * Step 4. 各个模块依次提交缓存
         */
        localCacheMap = DataLocalCacheFactory.getAllDataLocalCacheMap(context);
        for (IDataLocalCache localCache : localCacheMap.values()) {
            if(localCache != null)
                localCache.submitLocalCache();
        }

    }
    @Override
    public void setDBAdapter(DBHelper helper) {
        this.dbHelper = helper;
        localCacheMap = DataLocalCacheFactory.getAllDataLocalCacheMap(context);
        for (IDataLocalCache localCache : localCacheMap.values()) {
            if(localCache != null)
                localCache.setDBAdapter(helper);
        }

    }
}
