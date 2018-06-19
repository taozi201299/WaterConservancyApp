package com.syberos.shuili.service.dataprovider.localcache.dataLocalCache;


import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dao.DBHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by jidan on 18-3-8.
 * IDataLocalCache
 */
public interface IDataLocalCache {
    /**
     * 添加Cache到数据库
     * @return 添加结果
     */
    boolean addCache(String url, Map params, Map files, IResultCallback callback);
    boolean addCache(LocalCacheEntity localCacheEntity, List<AttachMentInfoEntity> attachMentInfoEntityList, IResultCallback callback);

    /**
     * 提交Cache
     * @param
     */
    void submitLocalCache();

   /**
     * 设置数据库操作对象
     * @param helper 数据库操作对象
     */
    void setDBAdapter(DBHelper helper);
}
