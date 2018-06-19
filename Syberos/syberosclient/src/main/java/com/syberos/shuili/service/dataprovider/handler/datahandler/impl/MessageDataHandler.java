package com.syberos.shuili.service.dataprovider.handler.datahandler.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;

import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.https.HttpsUtils;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.entity.MessageInfo;
import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.IMessageListCallback;
import com.syberos.shuili.service.MessageInfoEntity;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dataprovider.DataProvider;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.handler.datahandler.DataHandlerBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by jidan on 18-3-19.
 */

public class MessageDataHandler extends DataHandlerBase {
    /**
     * 构造方法，接受了一个context对象 并且注册了networkCallback
     *
     * @param context Context
     */
    public MessageDataHandler(Context context) {
        super(context);
    }

    @Override
    public void offLineRequest(Map params, Object callback) {
        List<MessageInfoEntity> infoList = getMessageInfo();
        try {
            ((IMessageListCallback)callback).onSuccess(infoList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLineRequest(final Map params, final Object callback) {
        String url = "http://www.baidu.com";
        HttpUtils.getInstance().requestGet(url, null, "", new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                ArrayList<MessageInfoEntity>infos = (ArrayList<MessageInfoEntity>)string2MessageInfoEntity(result);
                ArrayList response = new ArrayList<Parcelable>() ;
                for(MessageInfoEntity entity:infos){
                    response.add(entity);
                }
                Message message = new Message();
                message.what = DataProvider.message_what_sync_messageInfo;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) response);
                bundle.putSerializable("params", (Serializable) params);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                try {
                    ((IMessageListCallback)callback).onError(-1,errorInfo.getMessage());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, CacheMode.DEFAULT);

    }

    private ArrayList<MessageInfoEntity>  string2MessageInfoEntity(String response){
        ArrayList<MessageInfoEntity> list = new ArrayList<>();
        MessageInfoEntity infoEntity = new MessageInfoEntity("11","test","ss","ss","ss","0",
                "22","dd");
        list.add(infoEntity);
        return list;

    }
    private List<MessageInfoEntity>getMessageInfo(){
        List<MessageInfoEntity> infoList = new ArrayList<>();
        String sql = "select * from "+ DBDefinition.MESSAGE_TABLE + " where " + DBDefinition.isDelete + " = 0 " ;
        ArrayList<HashMap<String,String>> info = dbHelper.rawQuery(sql, new String[]{});
        for(HashMap map: info){
            infoList.add(map2MessageInfo(map));
        }
        return infoList;
    }
    private MessageInfoEntity map2MessageInfo(HashMap<String,String> map){
        MessageInfoEntity  info = new MessageInfoEntity(map.get(DBDefinition.messageId),map.get(DBDefinition.title),
                map.get(DBDefinition.content),map.get(DBDefinition.publisher),map.get(DBDefinition.publishDate),
                map.get(DBDefinition.readStatus),map.get(DBDefinition.serverDate),map.get(DBDefinition.organizationId));
        return info;
    }
}
