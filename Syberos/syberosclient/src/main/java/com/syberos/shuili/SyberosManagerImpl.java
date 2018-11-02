package com.syberos.shuili;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.entity.MessageInfo;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.userinfo.UserExtendInfo;
import com.syberos.shuili.network.SoapUtils;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.SyberosAidlClient;
import com.syberos.shuili.utils.NetworkUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jidan on 18-3-7.
 * 接口文件
 */

public class SyberosManagerImpl {

    public final static int Sync_Type = 0;
    public final static int Local_Type = 1;
    public final  static int Search_Type = 2;

    private final String TAG = SyberosManagerImpl.class.getSimpleName();
    private static Context mContext;
    private static SyberosManagerImpl Instance = null;
    private static SyberosAidlClient syberosAidlClient;
    private HandlerThread mWorkThread;
    private Handler mWorkHandler;
    private SoapUtils soapUtils;
    private HttpUtils httpsUtils;
    private static Handler mHandler;
    /**
     * 事故单位类型
     */
    private DicInfo m_unitTypeDic = null ;
    /**
     * 事故类别
     */
    private DicInfo m_accidentTypeDic = null;
    private SyberosManagerImpl(Context context){
        mContext = context;
        this.mWorkThread = new HandlerThread("WORK_THREAD");
        this.mWorkThread.start();
        this.mWorkHandler = new Handler(mWorkThread.getLooper());
        mHandler = new Handler();
        soapUtils = SoapUtils.getInstance();
        httpsUtils =HttpUtils.getInstance();
    }
    public static void init(Context context){
        synchronized (SyberosManagerImpl.class){
            if(Instance == null){
                Instance = new SyberosManagerImpl(context);
            }
            SyberosAidlClient.init(context);
            syberosAidlClient = SyberosAidlClient.getInstance();
        }
    }
    public static SyberosManagerImpl getInstance(){
        if(Instance == null){
            throw  new NullPointerException("未初始化");
        }
        return Instance;
    }
    /**
     * sync callback
     * has return value
     * @param <T>
     */
    abstract static class SyncCallback<T> extends ResultCallback<T> {
        SyncCallback() {
        }

        public void onFail(int errorCode) {
            this.onError(ErrorCode.valueOf(errorCode));
        }

        public void onFail(ErrorCode errorCode) {
            this.onError(errorCode);
        }

        public void onCallback(T t) {
            this.onSuccess(t);
        }
    }

    /**
     * async callback
     * @param <T>
     * has return value
     */
    public abstract static class ResultCallback<T> {
        public ResultCallback() {
        }

        public abstract void onSuccess(T var1);

        public abstract void onError(ErrorCode var1);

        public void onFail(final int errorCode) {
            mHandler.post(new Runnable() {
                public void run() {
                    ResultCallback.this.onError(ErrorCode.valueOf(errorCode));
                }
            });
        }

        public void onFail(final ErrorCode errorCode) {
            mHandler.post(new Runnable() {
                public void run() {
                    ResultCallback.this.onError(errorCode);
                }
            });
        }

        public void onCallback(final T t) {
            mHandler.post(new Runnable() {
                public void run() {
                    ResultCallback.this.onSuccess(t);
                }
            });
        }

        public static class Result<T> {
            public T t;

            public Result() {
            }
        }
    }
    public static enum ErrorCode {
        UNKNOWN(-1, "未知错误"),
        NETWORKERROR(-2,"网络错误");


        private int code;
        private String msg;

        private ErrorCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getValue() {
            return this.code;
        }

        public String getMessage() {
            return this.msg;
        }

        public static ErrorCode valueOf(int code) {
            ErrorCode[] arry = values();
            int len = arry.length;

            for(int i = 0; i < len; ++i) {
                ErrorCode c = arry[i];
                if(code == c.getValue()) {
                    return c;
                }
            }
            return UNKNOWN;
        }
    }
    public void submit(String url, HashMap<String,String> params, HashMap<String,File> files,RequestCallback<String> callback){
        if(NetworkUtil.isNetworkAvailable()){
           syberosAidlClient.addLocalCache(url,params,files,callback);

        }else {

        }
    }
    public void submit(LocalCacheEntity localCacheEntity, List<AttachMentInfoEntity>attachMentInfoEntity,RequestCallback<String> callback){
        syberosAidlClient.addLocalCache(localCacheEntity,attachMentInfoEntity,callback);
    }
    /*-----------------------------------soap 协议接口-------------------------------------------*/
    public void login(final HashMap<String, Object> params, final String methodName, final RequestCallback<Object> callback){
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                soapUtils.callWebService(params,methodName,callback,SoapUtils.SoapType.WSDL_BASE);
            }
        });
    }
    public void getAllOrgInfo(final  HashMap<String,Object> params,final  String methodName,final RequestCallback<Object> callback){
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                soapUtils.callWebService(params,methodName,callback,SoapUtils.SoapType.WSDL_WIUM_EXT);
            }
        });
    }
    public void changePwdForWater(final HashMap<String, Object> params, final String methodName, final RequestCallback<Object> callback){
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                soapUtils.callWebService(params,methodName,callback,SoapUtils.SoapType.WSDL_BASE_FOR_WATER);
            }
        });
    }
    public void changePwd(final HashMap<String, Object> params, final String methodName, final RequestCallback<Object> callback){
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                soapUtils.callWebService(params,methodName,callback,SoapUtils.SoapType.WSDL_BASE);
            }
        });
    }
    public void getOrgBaseInfo(final HashMap<String,Object> params,final String methodName,final RequestCallback<Object> callback ){
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                soapUtils.callWebService(params,methodName,callback, SoapUtils.SoapType.WSDL_WIUM);
            }
        });
    }
    public void updateUserInfo(final UserExtendInfo info, final String methodName, final RequestCallback<UserExtendInfo> callback){
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                soapUtils.updateUserInfo(info,methodName,callback);
            }
        });

    }
    /*------------------------------------------http 接口----------------------------------------------*/

    public String getCurrentUserId() {
        return syberosAidlClient.getCurrentUserId();
    }

    public UserExtendInformation getCurrentUserInfo(){
        return syberosAidlClient.getCurrentUserInfo();
    }
    public void setCurrentUserInfo(UserExtendInformation info){
        syberosAidlClient.setCurrentUserInfo(info);
    }

    /**
     * 获取通讯录列表（服务器）
     * @param map
     * @param type
     * @param callback
     */
    private void getAddressList(HashMap<String,String> map,int type,final ResultCallback<List<UserExtendInformation>> callback){
        syberosAidlClient.getAddressList(map,type,callback);
    }


    /**
     * 获取本地通讯录列表
     * @param map
     * @param callback
     */
    public void getLocalAddressList(HashMap<String,String> map,final ResultCallback<List<UserExtendInformation>>callback){
        syberosAidlClient.getAddressList(map,Local_Type,callback);

    }
    /**
     * 获取本地快报事故
     */
    public void getLocalAccidentList(HashMap<String,String>map, final ResultCallback<List<ObjAcci>> callback){
     syberosAidlClient.getLocalAccidentList(map,callback);
    }

    /**
     * 通讯录查找
     * @param map
     * @param callback
     */
    public void searchAddressList(HashMap<String,String> map,ResultCallback<List<UserExtendInformation>> callback){
        syberosAidlClient.getAddressList(map,Search_Type,callback);

    }

    /**
     * 同步通讯录到本地
     * @param map
     * @param callback
     */
    public void syncAddressList(HashMap<String,String> map,ResultCallback<List<UserExtendInformation>> callback ){
        syberosAidlClient.getAddressList(map,Sync_Type,callback);

    }

    /**
     * 获取消息列表（服务器）
     * @param map
     * @param callback
     */
    public void getMessageList(HashMap<String,String> map, ResultCallback<List<MessageInfo>> callback){
        syberosAidlClient.getMessageList(map,callback);
    }

    /**
     * 获取本地消息列表
     * @param map
     * @param callback
     */
    public void getOffMessageList(HashMap<String,String>map,ResultCallback<List<MessageInfo>>callback){
        syberosAidlClient.getOffMessageList(map,callback);
    }

    //------------------------------map interface----------------------------------------------------
    public void syncMapInfo(HashMap<String,String> map){
        syberosAidlClient.syncMapInfo(map);
    }
    public String getMapUrl(String url,String serviceID){
       String result =  syberosAidlClient.getMapUrl(url,serviceID);
       return result;
    }

    /**
     * 标记消息为已读
     * @param messageIds
     */
    public void setMessageStatus(List<String> messageIds){
        syberosAidlClient.setMessageStatus(messageIds);
    }

    /**
     * 删除本地消息
     * @param messageIds
     */
    public void deleteMessage(List<String> messageIds){
        syberosAidlClient.deleteMessage(messageIds);
    }

    /**
     * 清除缓存
     */

    public void clearCache(){
        syberosAidlClient.clearCache();

    }
    public void commitCache(){
        syberosAidlClient.commitCache();
    }
    /**
     * 获取事故单位类型
     */
    public  DicInfo getUnitTypeDic(){
      return m_unitTypeDic;
    }
    /**
     * 获取事故类别字典
     */
    public DicInfo getAccidentTypeDic(){
        return m_accidentTypeDic;
    }
    public void setUnitType(DicInfo unitType){
        this.m_unitTypeDic = unitType;
    }
    public void setAccidentType(DicInfo accidentType){
        this.m_accidentTypeDic = accidentType;
    }

    public void requestGet_Default(String url, HashMap<String,String> params,String tag, final RequestCallback<String> callback){
        HttpUtils.getInstance().requestGet(url,params,tag,callback,CacheMode.DEFAULT);
    }
    public void requestGet_RequestFailedForCache(String url,HashMap<String,String>params,String tag,final RequestCallback<String>callback){
        HttpUtils.getInstance().requestGet(url,params,tag,callback,CacheMode.REQUEST_FAILED_READ_CACHE);
    }
    public void requestGet_IfNone(String url,HashMap<String,String>params,String tag,final RequestCallback<String>callback){
        HttpUtils.getInstance().requestGet(url,params,tag,callback,CacheMode.IF_NONE_CACHE_REQUEST);
    }
    public void requestGet_FristCache(String url,HashMap<String,String>params,String tag,final RequestCallback<String>callback){
        HttpUtils.getInstance().requestGet(url,params,tag,callback,CacheMode.FIRST_CACHE_THEN_REQUEST);
    }
}
