package com.syberos.shuili.service.dataprovider.handler.datahandler.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.network.SoapUtils;
import com.syberos.shuili.service.IAddressListCallback;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.DataProvider;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.handler.datahandler.DataHandlerBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.syberos.shuili.SyberosManagerImpl.Local_Type;
import static com.syberos.shuili.SyberosManagerImpl.Search_Type;

/**
 * Company: SyberOS BeiJing
 * Project: Device Inspection
 * Created by 陈冬 on 16/6/15.
 */
public class UserInfoDataHandler extends DataHandlerBase {

    /**
     * 构造函数
     * @param context 上下文
     */
    public UserInfoDataHandler(Context context) {
        super(context);
    }


    @Override
    public List<UserInformationEntity> offLineRequest(int type,Map params,IAddressListCallback callback) {
        List<UserInformationEntity> infos = null;
        switch (type){
            case Local_Type:
                String userId = params.get("userId").toString();
                infos = getLocalAddressLis(userId,callback);
                break;
            case Search_Type:
                String text = params.get("searchText").toString();
                infos = searchAddressList(text,callback);
                break;
        }

        return infos;
    }

    private List<UserInformationEntity>  getLocalAddressLis(String userId,IAddressListCallback callback){
        List<UserInformationEntity> infos =  getUserInfo();
        try {
            callback.onSuccess(infos);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return infos;

    }
    private List<UserInformationEntity>  searchAddressList(String text,IAddressListCallback callback){
        List<UserInformationEntity> infos =  searchUserInfo(text);
        try {
            callback.onSuccess(infos);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return infos;

    }
    @Override
    public void onLineRequest(Map params, final IAddressListCallback callback) {
        String method = "getUserListInOrgBaseOrOrgWiunByOrgId";
        SoapUtils.getInstance().callWebService((HashMap<String,Object>)params, method, new RequestCallback() {
            @Override
            public void onResponse(Object result) {
                ArrayList<UserInformationEntity> infos = (ArrayList<UserInformationEntity>) string2UserInforEntity(result.toString());
                if(infos == null) {
                    try {
                        callback.onError(-4, ErrorInfo.ErrorCode.valueOf(-4).getMessage());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList infoList = new ArrayList<Parcelable>();
                for (UserInformationEntity entity : infos) {
                    infoList.add(entity);
                }
                Message message = new Message();
                message.what = DataProvider.message_what_sync_userInfo;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) infoList);
                message.setData(bundle);
                UserInfoDataHandler.this.mHandler.sendMessage(message);
                try {
                    callback.onSuccess(infos);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                Message message = new Message();
                message.what = DataProvider.message_what_sync_userInfo_failed;
                UserInfoDataHandler.this.mHandler.sendMessage(message);
                try {
                    callback.onError(errorInfo.getValue(), errorInfo.getMessage());
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        }, SoapUtils.SoapType.WSDL_BASE);
    }
    private List<UserInformationEntity> string2UserInforEntity(String response){
        response = response.replace("\n","");
        response = response.replace(" ","");
        response = response.replace("anyType","");
        response = response.replace("[","");
        response = response.replace("]","");
        String[] array = response.split(",");
        List<UserInformationEntity> infos = new ArrayList<>();
        int size = array.length;
        for(int i = 0; i< size ;i++){
            String item  = array[i].replace("{","");
            item  = item.replace("}","");
            String[]itemArray = item.split(";");
            int count = itemArray.length;
            HashMap<String,String> map = new HashMap<>();
            for(int j = 0; j < count ;j++){
             String[]childItemArray = itemArray[j].split("=");
             if(childItemArray.length == 2){
                 map.put(childItemArray[0],childItemArray[1]);
             }
            }
            UserInformationEntity informationEntity = new UserInformationEntity("","","",
                    map.get(DBDefinition.depName),
                    map.get(DBDefinition.id),"","","","",map.get(DBDefinition.orgName),"",
                    "",map.get(DBDefinition.persName),"",map.get("mobilenumb"),"","","",
                    "","");
            infos.add(informationEntity);
        }




        return infos;

    }
    private UserInformationEntity map2UserInfo(HashMap<String,String> map){
       return new UserInformationEntity(map.get(DBDefinition.admDutyLevel),map.get(DBDefinition.depCode),
               map.get(DBDefinition.depId),map.get(DBDefinition.depName),map.get(DBDefinition.id),map.get(DBDefinition.modifier),
               map.get(DBDefinition.note),map.get(DBDefinition.orgCode),
               map.get(DBDefinition.orgId),map.get(DBDefinition.orgName),map.get(DBDefinition.password),
               map.get(DBDefinition.persId),map.get(DBDefinition.persName),map.get(DBDefinition.persType),
               map.get(DBDefinition.phone),map.get(DBDefinition.status),map.get(DBDefinition.ts),
               map.get(DBDefinition.userCode),map.get(DBDefinition.userName),map.get(DBDefinition.userType));

    }
    private List<UserInformationEntity>getUserInfo(){
        List<UserInformationEntity> infoList = new ArrayList<>();
       String sql = "select * from "+ DBDefinition.USER_TABLE ;
       ArrayList<HashMap<String,String>> info = dbHelper.rawQuery(sql, new String[]{});
       for(HashMap map: info){
           infoList.add(map2UserInfo(map));
       }
       return infoList;
    }

    private List<UserInformationEntity>searchUserInfo(String text){
        List<UserInformationEntity> infoList = new ArrayList<>();
        String strSql = "select * from " + DBDefinition.USER_TABLE +
                " where "+ DBDefinition.persName + " like '%" + text + "%' or '' = '" + text + "' " +
                "or " + DBDefinition.orgName +" like '%" + text + "%' or '' = '" + text + "' " +
                "or "+ DBDefinition.depName +" like '%" + text + "%' or '' = '" + text + "' ";
        String []selectionArgs = new String[]{};
        ArrayList<HashMap<String,String>> info = dbHelper.rawQuery(strSql, selectionArgs);
        for(HashMap map: info){
            infoList.add(map2UserInfo(map));
        }
        return infoList;
    }

}
