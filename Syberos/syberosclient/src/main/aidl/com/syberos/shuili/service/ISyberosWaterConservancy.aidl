// ISyberosWaterConservancy.aidl
package com.syberos.shuili.service;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.IMessageListCallback;
import com.syberos.shuili.service.MessageInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.AccidentInformationEntity;
import com.syberos.shuili.service.IAccidentListCallback;

// Declare any non-default types here with import statements

 interface ISyberosWaterConservancy {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void addLocalCache(String url,in Map params,in Map files,IResultCallback callback);
   void addLocalCacheForLocalCacheEntity(in LocalCacheEntity localCache,in List<AttachMentInfoEntity> attachs,IResultCallback callback);
   void getAddressList(int type,in Map params,IAddressListCallback callback);
   void setCurrentUserInfo(in UserInformationEntity info);
   String getCurrentUserId();
   UserInformationEntity getCurrentUserInfo();
   void getMessageList(in Map params,IMessageListCallback callback);
   void getOffMessageList(in Map params,IMessageListCallback callback);
   void deleteMessage(in List<String>messageIds);
   void setMessageStatus(in List<String> messageIds);
   void getLocalAccidentList(in Map params,IAccidentListCallback callback);
   void syncMapInfo(in Map params);
   String getMapUrl(String url,String serviceId);
   void clearCache();


}
