package com.syberos.shuili.service.dataprovider.handler;

import android.content.Context;
import android.os.Handler;

import com.syberos.shuili.service.IAccidentListCallback;
import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.IMessageListCallback;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.SyberosService;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.handler.datahandler.DataHandlerFactory;
import com.syberos.shuili.service.dataprovider.handler.datahandler.IDataHandler;

import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;

public class HandlerManagerImpl implements IHandlerManager {
    private Context context;


    /**
     * 构造函数
     * @param context Context
     */
    public HandlerManagerImpl(Context context) {
        this.context = context;
    }


    @Override
    public List<UserInformationEntity> offLineRequest(int type, Map params, IAddressListCallback callback) {
        IDataHandler dh = DataHandlerFactory.getDataHandler(DataOperationType.USER_INFO, context);
        return dh.offLineRequest(type,params,callback);

    }

    @Override
    public void onLineRequest(Map params, IAddressListCallback callback) {
        IDataHandler dh = DataHandlerFactory.getDataHandler(DataOperationType.USER_INFO, context);
        dh.onLineRequest(params,callback);
    }

    @Override
    public void offLineRequest(Map params, Object callback) {
        IDataHandler dh = null;
        if(callback instanceof IMessageListCallback) {
            dh = DataHandlerFactory.getDataHandler(DataOperationType.Message_Info, context);
        }else if(callback instanceof IAccidentListCallback){
            dh = DataHandlerFactory.getDataHandler(DataOperationType.Accident_Info,context);
        }else if(callback instanceof SyberosService.StringCallBack){
            dh = DataHandlerFactory.getDataHandler(DataOperationType.Map_Info,context);
        }
        dh.offLineRequest(params,callback);
    }

    @Override
    public void onLineRequest(Map params, Object callback) {
        IDataHandler dh = null;
        if(callback instanceof IMessageListCallback) {
            dh = DataHandlerFactory.getDataHandler(DataOperationType.Message_Info, context);
        }
        dh.onLineRequest(params,callback);
    }

    @Override
    public void setDBAdapter(DBHelper helper) {
        List<IDataHandler> handlerList = DataHandlerFactory.getAllDataHandler(context);
        for (IDataHandler dataHandler:handlerList) {
            if(dataHandler == null)continue;
            dataHandler.setDBAdapter(helper);
        }
    }

    @Override
    public void setHandler(Handler handler) {
        List<IDataHandler> handlerList = DataHandlerFactory.getAllDataHandler(context);
        for (IDataHandler dataHandler:handlerList) {
            if(dataHandler == null)continue;
            dataHandler.setHandler(handler);
        }

    }
}
