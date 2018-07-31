package com.syberos.shuili.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;

import com.shuili.callback.RequestCallback;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.entity.MessageInfo;
import com.syberos.shuili.entity.UserExtendInfo;
import com.syberos.shuili.entity.accident.ObjAcci;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jidan on 18-3-7.
 */

public class SyberosAidlClient {
    private final String TAG = SyberosAidlClient.class.getSimpleName();
    ISyberosWaterConservancy ILibInterface = null;
      /**
     * 是否已经绑定binder对象
     */
    private boolean mIsBind;
    private Context mContext;

    static SyberosAidlClient syberosAidlClient;
    AidlConnection mAidlConnection;
    private HandlerThread m_workThread ;
    private Handler m_workHandler;
    /**
     * 主线程handler
     */
    private Handler mHandler;

    private SyberosAidlClient(Context context){
        this.mContext = context;
        mAidlConnection = new AidlConnection();
        mHandler = new Handler(Looper.getMainLooper());
        m_workThread = new HandlerThread("WORK_THREAD");
        m_workThread.start();
        m_workHandler = new Handler(m_workThread.getLooper());
        initBindService();

    }

    public synchronized static void init(Context context){
        if(syberosAidlClient == null){
            syberosAidlClient = new SyberosAidlClient(context);
        }

    }
    public static synchronized SyberosAidlClient getInstance() {
        return syberosAidlClient;
    }
    private void initBindService() {
        if(!this.mIsBind) {
            Intent intent = new Intent(this.mContext, SyberosService.class);
            mIsBind = true;
            try {
                this.mContext.bindService(intent, mAidlConnection, Context.BIND_AUTO_CREATE);
            } catch (SecurityException var3) {
                var3.printStackTrace();
            }
        }

    }


    /*********************************************** aidl interface*************************************/
    class AidlConnection implements ServiceConnection {
        AidlConnection() {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ILibInterface = ISyberosWaterConservancy.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void addLocalCache(final String url, final HashMap<String,String>params, final HashMap<String,File> files, final RequestCallback<String> callback){
        this.m_workHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    syberosAidlClient.ILibInterface.addLocalCache(url, params, files, new IResultCallback.Stub() {
                        @Override
                        public void onSuccess() throws RemoteException {
                            callback.sendResult("");
                        }

                        @Override
                        public void onFailed(int errorCode) throws RemoteException {
                            callback.sendResultFailed(errorCode);


                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });


    }
    public void addLocalCache(final LocalCacheEntity localCacheEntity, final List<AttachMentInfoEntity>attachMentInfoEntities, final RequestCallback<String> callback){
        try {
            syberosAidlClient.ILibInterface.addLocalCacheForLocalCacheEntity(localCacheEntity,attachMentInfoEntities, new IResultCallback.Stub() {
                @Override
                public void onSuccess() throws RemoteException {
                    callback.sendResult("");
                }

                @Override
                public void onFailed(int errorCode) throws RemoteException {
                    callback.sendResultFailed(errorCode);


                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

   public void getAddressList(final HashMap<String,String> map, final int  type, final SyberosManagerImpl.ResultCallback<List<UserExtendInfo>> callback){
        this.m_workHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    syberosAidlClient.ILibInterface.getAddressList(type,map, new IAddressListCallback.Stub() {
                        @Override
                        public void onSuccess(List<UserInformationEntity> info) throws RemoteException {
                            List<UserExtendInfo> information = convertInfo(info);
                            callback.onCallback(information);

                        }

                        @Override
                        public void onError(int errorCode, String errString) throws RemoteException {
                            callback.onFail(SyberosManagerImpl.ErrorCode.valueOf(errorCode));
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
   }

   public void getMessageList(HashMap<String,String> map, final SyberosManagerImpl.ResultCallback<List<MessageInfo>> callback){

       try {
           syberosAidlClient.ILibInterface.getMessageList(map, new IMessageListCallback.Stub() {
               @Override
               public void onSuccess(List<MessageInfoEntity> info) throws RemoteException {
                   List<MessageInfo> information = convert2MessageInfos(info);
                   callback.onCallback(information);
               }

               @Override
               public void onError(int errorCode, String errString) throws RemoteException {
                   callback.onFail(SyberosManagerImpl.ErrorCode.valueOf(errorCode));
               }
           });
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }
   public void getOffMessageList(HashMap<String,String>map, final SyberosManagerImpl.ResultCallback<List<MessageInfo>> callback){

       try {
           syberosAidlClient.ILibInterface.getOffMessageList(map, new IMessageListCallback.Stub() {
               @Override
               public void onSuccess(List<MessageInfoEntity> info) throws RemoteException {
                   List<MessageInfo> information = convert2MessageInfos(info);
                   callback.onCallback(information);
               }

               @Override
               public void onError(int errorCode, String errString) throws RemoteException {
                   callback.onFail(SyberosManagerImpl.ErrorCode.valueOf(errorCode));
               }

           });
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }
   public void getLocalAccidentList(HashMap<String,String>map, final SyberosManagerImpl.ResultCallback<List<ObjAcci>> callback){
       try {
           syberosAidlClient.ILibInterface.getLocalAccidentList(map, new IAccidentListCallback.Stub() {
               @Override
               public void onSuccess(List<AccidentInformationEntity> info) throws RemoteException {
                   callback.onSuccess(convert2AccidentInfo(info));
               }

               @Override
               public void onError(int errorCode, String errString) throws RemoteException {
                   callback.onFail(SyberosManagerImpl.ErrorCode.valueOf(errorCode));
               }
           });
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }

   public void  setMessageStatus(List<String> messageIds){
       try {
           syberosAidlClient.ILibInterface.setMessageStatus(messageIds);
       } catch (RemoteException e) {
           e.printStackTrace();
       }

   }
   public void deleteMessage(List<String> messageIds){
       try {
           syberosAidlClient.ILibInterface.deleteMessage(messageIds);
       } catch (RemoteException e) {
           e.printStackTrace();
       }

   }

   public void syncMapInfo(HashMap<String,String> map){
       try {
           syberosAidlClient.ILibInterface.syncMapInfo(map);
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }
   public String getMapUrl(String url,String serviceID){
       String mapUrl = "";
       try {
           mapUrl =  syberosAidlClient.ILibInterface.getMapUrl(url,serviceID);
       } catch (RemoteException e) {
           e.printStackTrace();
       }
       return mapUrl;
   }
   public void clearCache(){
       try {
           syberosAidlClient.ILibInterface.clearCache();
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }
   private List<UserExtendInfo> convertInfo(List<UserInformationEntity> infos){
       if(infos == null)return null;
       List<UserExtendInfo> informations = new ArrayList<>();
       int size = infos.size();
       for(int i = 0 ; i < size ; i++){
           UserExtendInfo information =  convert2UserExtendInfo(infos.get(i));
           informations.add(information);

       }
       return informations;

   };
    public UserExtendInfo getCurrentUserInfo(){
        UserExtendInfo information ;
        UserInformationEntity informationEntity = null;
        try {
            informationEntity = syberosAidlClient.ILibInterface.getCurrentUserInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        information = convert2UserExtendInfo(informationEntity);
        return information;
    }
    private UserExtendInfo convert2UserExtendInfo(UserInformationEntity informationEntity){
        UserExtendInfo information = new UserExtendInfo();
        information.setAdmDutyLevel(informationEntity.admDutyLevel);
        information.setDepCode(informationEntity.depCode);
        information.setDepId(informationEntity.depId);
        information.setDepName(informationEntity.depName);
        information.setId(informationEntity.id);
        information.setModifier(informationEntity.modifier);
        information.setNote(informationEntity.note);
        information.setOrgCode(informationEntity.orgCode);
        information.setOrgId(informationEntity.orgId);
        information.setOrgName(informationEntity.orgName);
        information.setPassword(informationEntity.password);
        information.setPersId(informationEntity.persId);
        information.setPersName(informationEntity.persName);
        information.setPersType(informationEntity.persType);
        information.setPhone(informationEntity.phone);
        information.setStatus(informationEntity.status);
        information.setTs(informationEntity.ts);
        information.setUserCode(informationEntity.userCode);
        information.setUserName(informationEntity.userName);
        information.setUserType(informationEntity.userType);
        return information;
    }
    private UserInformationEntity convert2UserInformationEntity(UserExtendInfo information){
         return  new UserInformationEntity(information.getAdmDutyLevel(),information.getDepCode(),information.getDepId(),
                 information.getDepName(),information.getId(),information.getModifier(),information.getNote(),information.getOrgCode(),
                 information.getOrgId(),information.getOrgName(), information.getPassword(),information.getPersId(),information.getPersName(),
                 information.getPersType(),information.getPhone(),information.getStatus(),information.getTs(),information.getUserCode(),
                 information.getUserName(),information.getUserType());
    }
    private MessageInfo convert2MessageInfo(MessageInfoEntity messageInfoEntity){
        MessageInfo info = new MessageInfo();
        info.setMessageId(messageInfoEntity.messageId);
        info.setMessageId(messageInfoEntity.title);
        info.setContent(messageInfoEntity.content);
        info.setOrganizationId(messageInfoEntity.organizationId);
        info.setPublishDate(messageInfoEntity.publishDate);
        info.setPublisher(messageInfoEntity.publisher);
        info.setReadStatus(messageInfoEntity.readStatus);
        info.setServerDate(messageInfoEntity.serverDate);
        return info;

    }
    private List<MessageInfo>convert2MessageInfos(List<MessageInfoEntity> messageInfos){
        ArrayList<MessageInfo>messages = new ArrayList<>();
        int size = messageInfos.size();
        for(int i = 0 ; i< size ; i++){
            MessageInfo message = new MessageInfo();
            message = convert2MessageInfo(messageInfos.get(i));
            messages.add(message);
        }
        return messages;
    }
    private List<ObjAcci>convert2AccidentInfo(List<AccidentInformationEntity>infos){
        ArrayList<ObjAcci> accidentInformations = new ArrayList<>();
        int size = infos.size();
        for(int i = 0; i< size; i++){
            ObjAcci accidentInformation = new ObjAcci();
            accidentInformation = convert2AccidentInfoItem(infos.get(i));
            accidentInformations.add(accidentInformation);
        }
        return accidentInformations;
    }
    private ObjAcci convert2AccidentInfoItem(AccidentInformationEntity accidentInformationEntity){
        ObjAcci accidentInformation = new ObjAcci();
        accidentInformation.setAcciWiunType(accidentInformationEntity.acciWiunType);
        accidentInformation.setOccuTime(accidentInformationEntity.occuTime);
        accidentInformation.setAcciCate(accidentInformationEntity.acciCate);
        accidentInformation.setCasNum(accidentInformationEntity.casNum);
        accidentInformation.setPID(accidentInformationEntity.pid);
        accidentInformation.setCollTime(accidentInformationEntity.collTime);
        accidentInformation.setSerInjNum(accidentInformationEntity.serInjNum);
        accidentInformation.setId(accidentInformationEntity.id);
        accidentInformation.setAcciSitu(accidentInformationEntity.acciSitu);
        accidentInformation.setIfPhoRep(accidentInformationEntity.ifPhoRep);
        accidentInformation.setAcciWindGuid(accidentInformationEntity.acciWindGuid);
        accidentInformation.setIfRespAcci(accidentInformationEntity.ifRespAcci);
        accidentInformation.setEconLoss(accidentInformationEntity.econLoss);
        return accidentInformation;


    }
    public void setCurrentUserInfo(UserExtendInfo info){
        try {
            syberosAidlClient.ILibInterface.setCurrentUserInfo(convert2UserInformationEntity(info));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public String getCurrentUserId(){
        try {
            return syberosAidlClient.ILibInterface.getCurrentUserId();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }
}
