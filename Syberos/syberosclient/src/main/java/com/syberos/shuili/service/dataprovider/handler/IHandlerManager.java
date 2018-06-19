package com.syberos.shuili.service.dataprovider.handler;

import android.os.Handler;

import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by jidan on 18-3-12.
 */
public interface IHandlerManager {



    public List<UserInformationEntity> offLineRequest(int type,Map params,IAddressListCallback callback);
    public void onLineRequest(Map params, IAddressListCallback callback);

    public void offLineRequest(Map params,Object callback);
    public void onLineRequest(Map params,Object callback);



    /**
     * 设置数据库操作对象
     * @param helper 数据库操作对象
     */
    void setDBAdapter(DBHelper helper);
    void setHandler(Handler handler);

}
