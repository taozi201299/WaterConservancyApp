package com.syberos.shuili.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.syberos.shuili.service.dataprovider.DataProvider;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.utils.CrashHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jidan on 18-3-7.
 */

public class SyberosService extends Service {
    DataProvider dataProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        dataProvider = DataProvider.getInstance(getApplicationContext());
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLibInterface;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private ISyberosWaterConservancy.Stub mLibInterface = new ISyberosWaterConservancy.Stub() {


        @Override
        public void addLocalCache(String url, Map params, Map files, IResultCallback callback) throws RemoteException {
            dataProvider.addLocalCache(url,params,files,callback);
        }

        @Override
        public void addLocalCacheForLocalCacheEntity(LocalCacheEntity localCache,List<AttachMentInfoEntity> attachMentInfoEntities, IResultCallback callback) throws RemoteException {
            dataProvider.CommonAddLocalCache(localCache,attachMentInfoEntities,callback);
        }

        @Override
        public void getAddressList(int type,Map params, IAddressListCallback callback) throws RemoteException {
            dataProvider.getAddressList(type,params,callback);

        }

        @Override
        public void setCurrentUserInfo(UserInformationEntity info) throws RemoteException {
            dataProvider.setCurrentUserInfo(info);

        }

        @Override
        public String getCurrentUserId() throws RemoteException {
            return dataProvider.getCurrentUserId();
        }

        @Override
        public UserInformationEntity getCurrentUserInfo() throws RemoteException {
            return dataProvider.getCurrentUserInfo();
        }

        @Override
        public void getMessageList(Map params, IMessageListCallback callback) throws RemoteException {
            dataProvider.getMessageList(params,callback);
        }

        @Override
        public void getOffMessageList(Map params, IMessageListCallback callback) throws RemoteException {
            dataProvider.getOffMessageList(params,callback);
        }

        @Override
        public void deleteMessage(List<String> messageIds) throws RemoteException {
            dataProvider.deleteMessage(messageIds);

        }

        @Override
        public void setMessageStatus(List<String> messageIds) throws RemoteException {
            dataProvider.setMessageStatus(messageIds);

        }

        @Override
        public void getLocalAccidentList(Map params, IAccidentListCallback callback) throws RemoteException {
            dataProvider.getLocalAccidentList(params,callback);
        }

        @Override
        public void syncMapInfo(Map params) throws RemoteException {
            dataProvider.syncMapInfo(params);

        }

        @Override
        public String getMapUrl(String url, String serviceId) throws RemoteException {
            final String[] mapUrl = {""};
            HashMap<String,String>param = new HashMap<>();
            param.put(DBDefinition.mapUrl,url);
            param.put(DBDefinition.serviceID,serviceId);
            dataProvider.getMapUrl(param, new StringCallBack() {
                @Override
                public void sucess(String result) {
                    mapUrl[0] =  result;
                }

                @Override
                public void onFailed(int code) {

                }
            });
            return mapUrl[0];
        }

        public void clearCache(){
            dataProvider.clearCache();
        }

    };
  public   interface  StringCallBack{
        void sucess(String result);
        void onFailed(int code);
    }





}
