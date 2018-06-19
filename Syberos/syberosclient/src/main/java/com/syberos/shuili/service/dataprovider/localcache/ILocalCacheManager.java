package com.syberos.shuili.service.dataprovider.localcache;

import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by jidan on 18-3-8.
 */
public interface ILocalCacheManager {

    /**
     * 将待提交的数据添加到本地缓存
     * @return 添加缓存是否成功
     */
    boolean addCache(String url, Map params, Map files, IResultCallback callback);
    boolean addCache(LocalCacheEntity localCacheEntity, List<AttachMentInfoEntity> attachMentInfoEntityList, IResultCallback callback);

    /**
     * 提交本地所有业务缓存
     */
    void submitLocalCacheAll();

    /**
     * 设置数据库操作对象
     * @param helper 数据库操作对象
     */
    void setDBAdapter(DBHelper helper);
}