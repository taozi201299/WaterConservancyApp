// IMessageListCallback.aidl
package com.syberos.shuili.service;
import com.syberos.shuili.service.MessageInfoEntity;
// Declare any non-default types here with import statements

interface IMessageListCallback {

          void onSuccess(in List<MessageInfoEntity> info);

          void onError(int errorCode,String errString);
}
