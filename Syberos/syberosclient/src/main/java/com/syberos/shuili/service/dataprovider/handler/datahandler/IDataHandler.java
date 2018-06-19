package com.syberos.shuili.service.dataprovider.handler.datahandler;
import android.os.Handler;

import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;

import java.util.List;
import java.util.Map;

public interface IDataHandler {

    public List<UserInformationEntity> offLineRequest(int type, Map params, IAddressListCallback callback);
    public void onLineRequest(Map params,IAddressListCallback callback);
    public void setDBAdapter(DBHelper helper) ;
    void setHandler(Handler handler);

    public void offLineRequest(Map params,Object callback);
    public void onLineRequest(Map params,Object callback);
}
