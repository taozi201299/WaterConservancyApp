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

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
                ArrayList<UserInformationEntity> infos = (ArrayList<UserInformationEntity>) string2UserInforEntity(result);
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
    private List<UserInformationEntity> string2UserInforEntity(Object response){
        int size ;
        List<UserInformationEntity> infos = new ArrayList<>();
        String[]array = response.toString().split("anyType");
        if(array.length == 2 && array[0].isEmpty()){
            HashMap<String,String>info = new HashMap<>();
            info.put("admDuty",(((SoapObject) response).getPropertySafelyAsString("admDuty").toString()));
            info.put("depId", (((SoapObject) response).getPropertySafelyAsString("depId").toString()));
            info.put("depName", (((SoapObject) response).getPropertySafelyAsString("depName").toString()));
            info.put("id", (((SoapObject) response).getPropertySafelyAsString("id").toString()));
            info.put("isValidUser", (((SoapObject) response).getPropertySafelyAsString("isValidUser").toString()));
            info.put("isWaterIndustry", (((SoapObject) response).getPropertySafelyAsString("isWaterIndustry").toString()));
            info.put("jurdAreaType", (((SoapObject) response).getPropertySafelyAsString("jurdAreaType").toString()));
            info.put("orgJurd",(((SoapObject) response).getPropertySafelyAsString("orgJurd").toString()));
            info.put("mobilenumb",(((SoapObject) response).getPropertySafelyAsString("mobilenumb").toString()));
            info.put("modifier", (((SoapObject) response).getPropertySafelyAsString("modifier").toString()));
            info.put("orgCode",(((SoapObject) response).getPropertySafelyAsString("orgCode").toString()));
            info.put("orgId", (((SoapObject) response).getPropertySafelyAsString("orgId").toString()));
            info.put("orgLevel", (((SoapObject) response).getPropertySafelyAsString("orgLevel").toString()));
            info.put("orgName", (((SoapObject) response).getPropertySafelyAsString("orgName").toString()));
            info.put("persId",(((SoapObject) response).getPropertySafelyAsString("persId").toString()));
            info.put("persName", (((SoapObject) response).getPropertySafelyAsString("persName").toString()));
            info.put("status", (((SoapObject) response).getPropertySafelyAsString("status").toString()));
            info.put("ts", (((SoapObject) response).getPropertySafelyAsString("ts").toString()));
            info.put("userCode",(((SoapObject) response).getPropertySafelyAsString("userCode").toString()));
            info.put("userName", (((SoapObject) response).getPropertySafelyAsString("userName").toString()));
            info.put("userPassword",(((SoapObject) response).getPropertySafelyAsString("userPassword").toString()));
            UserInformationEntity informationEntity = new UserInformationEntity("","","",
                    info.get(DBDefinition.depName),
                    info.get(DBDefinition.id),"","","","",info.get(DBDefinition.orgName),
                    "","",info.get(DBDefinition.persName),"",info.get("mobilenumb"),"","","",
                    "","");
            infos.add(informationEntity);
        }else {
            size = ((Vector) response).size();
            for (int i = 0; i < size; i++) {
                HashMap<String, String> info = new HashMap<>();
                info.put("admDuty", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("admDuty").toString()));
                info.put("depId", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("depId").toString()));
                info.put("depName", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("depName").toString()));
                info.put("id", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("id").toString()));
                info.put("isValidUser", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("isValidUser").toString()));
                info.put("isWaterIndustry", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("isWaterIndustry").toString()));
                info.put("jurdAreaType", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("jurdAreaType").toString()));
                info.put("orgJurd", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("orgJurd").toString()));
                info.put("mobilenumb", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("mobilenumb").toString()));
                info.put("modifier", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("modifier").toString()));
                info.put("orgCode", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("orgCode").toString()));
                info.put("orgId", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("orgId").toString()));
                info.put("orgLevel", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("orgLevel").toString()));
                info.put("orgName", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("orgName").toString()));
                info.put("persId", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("persId").toString()));
                info.put("persName", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("persName").toString()));
                info.put("status", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("status").toString()));
                info.put("ts", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("ts").toString()));
                info.put("userCode", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("userCode").toString()));
                info.put("userName", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("userName").toString()));
                info.put("userPassword", (((SoapObject) ((Vector) response).get(i)).getPropertySafelyAsString("userPassword").toString()));
                UserInformationEntity informationEntity = new UserInformationEntity("", "", "",
                        info.get(DBDefinition.depName),
                        info.get(DBDefinition.id), "", "", "", "", info.get(DBDefinition.orgName),
                        "","", info.get(DBDefinition.persName), "", info.get("mobilenumb"), "", "", "",
                        "", "");
                infos.add(informationEntity);
            }
        }
        return infos;

    }
    private UserInformationEntity map2UserInfo(HashMap<String,String> map){
       return new UserInformationEntity(map.get(DBDefinition.admDutyLevel),map.get(DBDefinition.depCode),
               map.get(DBDefinition.depId),map.get(DBDefinition.depName),map.get(DBDefinition.id),map.get(DBDefinition.modifier),
               map.get(DBDefinition.note),map.get(DBDefinition.orgCode),
               map.get(DBDefinition.orgId),map.get(DBDefinition.orgName),"",
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
