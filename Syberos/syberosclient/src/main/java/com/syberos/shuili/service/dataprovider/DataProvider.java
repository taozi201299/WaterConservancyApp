package com.syberos.shuili.service.dataprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.IAccidentListCallback;
import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.RuntimeContext;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dao.DBHelperFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.DBConfFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableField;
import com.syberos.shuili.service.dataprovider.handler.HandlerFactory;
import com.syberos.shuili.service.dataprovider.handler.IHandlerManager;
import com.syberos.shuili.service.dataprovider.handler.datahandler.impl.MessageDataHandler;
import com.syberos.shuili.service.dataprovider.localcache.ILocalCacheManager;
import com.syberos.shuili.service.dataprovider.localcache.LocalCacheFactory;
import com.syberos.shuili.service.dataprovider.sync.ISyncManager;
import com.syberos.shuili.service.dataprovider.sync.SyncFactory;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.ISynchronizer;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerFactory;
import com.syberos.shuili.utils.FileOperate;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.syberos.shuili.SyberosManagerImpl.Local_Type;
import static com.syberos.shuili.SyberosManagerImpl.Search_Type;
import static com.syberos.shuili.SyberosManagerImpl.Sync_Type;
import static com.syberos.shuili.service.dao.DBHelperFactory.DB_NAME;

/**
 * Created by jidan on 18-3-7.
 */

public class DataProvider {

    Context context;
    /**
     * 单例对象
     */
    private static DataProvider Instance = null;
    /**
     * 缓存管理对象
     */
    private static ILocalCacheManager localCacheManager = null;
    /**
     * 数据表管理对象
     */
    private static ISyncManager syncManager = null;

    private static IHandlerManager hanlerManager = null;

    DBHelper dbHelper;

    private HashMap<String,Object> callbacks = new HashMap<>();

    private final String messageKey = "messageKey";
    public static final int message_what_sync_userInfo = 0;
    public static final int message_what_sync_userInfo_failed = 1;
    public static final int message_what_sync_messageInfo = 2;
    public static final int message_what_sync_messageInfo_failed = 3;
    public static final int message_what_getoff_messageInfo = 4;




    private final int ON_LINE_REQUEST = 0;
    private final int OFF_REQUEST = 1;


    private boolean  bSyncSucess = false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case message_what_sync_userInfo:
                    bSyncSucess = true;
                    Bundle bundle = msg.getData();
                    syncManager.syncUserInfo(bundle);
                    break;
                case message_what_sync_userInfo_failed:
                    LogUtils.d("djksjdkjfkjsd","jfkjdkjf");
                    bSyncSucess = false;
                    break;
                case message_what_sync_messageInfo:
                    Bundle bundle1 = msg.getData();
                    syncManager.syncMessage(bundle1);
                    break;
                case message_what_sync_messageInfo_failed:
                    break;
                case message_what_getoff_messageInfo:
                    Bundle bundle2 = msg.getData();
                    getOffMessageList((Map)bundle2.get("params"),callbacks.get(messageKey));
                    break;
            }
        }
    };
    /**
     * 获取单例对象
     *
     * @param context 　Context上下文
     * @return 单例对象
     */
    public static DataProvider getInstance(Context context) {
        if (Instance == null)
            Instance = new DataProvider(context);

        return Instance;
    }

    /**
     * 初始化数据库表
     */
    private void initDB(){
        String dbFullPath = FileOperate.createFolder(1) + DBHelperFactory.DB_NAME;
        File file = new File(dbFullPath);
        if (!file.exists()) {
            dbHelper = DBHelperFactory.getDBHelper(context);
            syncManager.initTables(dbHelper);
        }else {
            if(dbHelper == null){
                dbHelper = DBHelperFactory.getDBHelper(context);
            }
        }

    }
    /**
     * 初始化数据库操作对象
     */
    private void initDBAdapter() {
        localCacheManager.setDBAdapter(dbHelper);
        syncManager.setDBAdapter(dbHelper);
        hanlerManager.setDBAdapter(dbHelper);

    }
    private void initHandler(){

        hanlerManager.setHandler(mHandler);
        syncManager.setHandler(mHandler);
    }
    /**
     * 构造函数
     */
    private DataProvider(Context context) {
        this.context = context;
        if(localCacheManager == null){
            localCacheManager = LocalCacheFactory.getLocalCacheManager(context);
        }
        if(syncManager == null){
            syncManager = SyncFactory.getSyncManager(context);
        }
        if(hanlerManager == null){
            hanlerManager = HandlerFactory.getHandlerManager(context);
        }
        initDB();
        initDBAdapter();
        initHandler();

    }
    /**
     * 提交本地缓存
     */
    public void submitCache() {
        localCacheManager.submitLocalCacheAll();




    }
   public void  addLocalCache(String url, Map params, Map files, IResultCallback callback){
        localCacheManager.addCache(url,params,files,callback);
   }
   public void CommonAddLocalCache(LocalCacheEntity localCacheEntity, List<AttachMentInfoEntity> attachMentInfoEntities,IResultCallback callback){
       localCacheManager.addCache(localCacheEntity,attachMentInfoEntities,callback);
   }
   public void  getAddressList(int type ,Map params, IAddressListCallback callback){
       switch (type){
           case Sync_Type:
               hanlerManager.onLineRequest(params,callback);
               break;
           case Local_Type:
           case Search_Type:
               hanlerManager.offLineRequest(type,params,callback);
               break;
       }
   }
   public void getMessageList(Map params,Object callback){

       if(callbacks.get(messageKey) != null){
           callbacks.remove(messageKey);

       }
       callbacks.put(messageKey,callback);
       if(NetworkUtil.isNetworkAvailable()){
           hanlerManager.onLineRequest(params,callback);
       }else{
           hanlerManager.offLineRequest(params,callback);
       }
   }
   public void getOffMessageList(Map params,Object callback){
       hanlerManager.offLineRequest(params,callback);
   }
   public void deleteMessage(List<String> messageIds){
       syncManager.deleteInfo(messageIds,DataOperationType.Message_Info);


   }
   public void setMessageStatus(List<String> messageIds){
       syncManager.setInfoStatus(messageIds,DataOperationType.Message_Info);

   }

   public void setCurrentUserInfo(UserInformationEntity information){
       RuntimeContext.setCurrentUserInfo(information);

   }
   public String getCurrentUserId(){
       return RuntimeContext.getCurrentUserId();

   }
   public UserInformationEntity getCurrentUserInfo(){
       return RuntimeContext.getCurrentUserInfo();
   }
   public void clearCache(){
       dbHelper.deleteAllTables();
   }

   public void getLocalAccidentList(Map params, IAccidentListCallback callback){
       hanlerManager.offLineRequest(params,callback);
   }




}
