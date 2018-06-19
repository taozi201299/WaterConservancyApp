// IAddressListCallback.aidl
package com.syberos.shuili.service;
import com.syberos.shuili.service.UserInformationEntity;
// Declare any non-default types here with import statements

interface IAddressListCallback {

          void onSuccess(in List<UserInformationEntity> info);

          void onError(int errorCode,String errString);
}
