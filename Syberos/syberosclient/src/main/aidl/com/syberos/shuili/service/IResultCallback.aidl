// IResultCallback.aidl
package com.syberos.shuili.service;

// Declare any non-default types here with import statements

interface IResultCallback {
     /**
      * 返回成功回调方法
      */
     void onSuccess();

     /**
      * 返回失败时回调方法
      * @param errorCode 错误码
      * @param errorMsg 错误信息描述
      */
     void onFailed(int errorCode);
}
