package com.syberos.shuili.service;
import com.syberos.shuili.service.AccidentInformationEntity;
// Declare any non-default types here with import statements

interface IAccidentListCallback {

          void onSuccess(in List<AccidentInformationEntity> info);

          void onError(int errorCode,String errString);
}