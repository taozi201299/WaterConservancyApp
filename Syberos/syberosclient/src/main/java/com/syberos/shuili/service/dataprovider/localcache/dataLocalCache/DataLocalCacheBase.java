package com.syberos.shuili.service.dataprovider.localcache.dataLocalCache;

import android.content.Context;
import android.os.Handler;

import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.localcache.ILocalCacheListener;
import com.syberos.shuili.utils.FileOperate;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by jidan on 18-3-8.
 * 各模块本地缓存管理模块基类
 */
public abstract class DataLocalCacheBase implements IDataLocalCache {

    /**
     * 业务类型
     */
    protected DataOperationType type;

    /**
     * 文件操作对象
     */
    private FileOperate fileOperation;
    protected Context context;

    /**
     * 数据库操作对象
     */
    protected DBHelper dbHelper;
    protected  ArrayList<CommitTask>submitTask = new ArrayList();
    protected  ArrayList<CommitTask>submitBinaryTask = new ArrayList<>();

    /**
     * 构造函数
     *
     * @param context            Context
     */
    public DataLocalCacheBase(Context context) {
        this.context = context;
        this.fileOperation = new FileOperate();
    }

    @Override
    public abstract boolean addCache(String url, Map params, Map files, IResultCallback callback);
    public abstract  boolean addCache(LocalCacheEntity localCacheEntity, List<AttachMentInfoEntity>attachMentInfoEntities, IResultCallback callback);

    @Override
    public abstract void submitLocalCache();

    @Override
    public void setDBAdapter(DBHelper helper) {
        this.dbHelper = helper;
    }
    public class CommitTask {
        private String uuid;

        public CommitTask(String uuid) {
            this.uuid = uuid;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
