package com.syberos.shuili.service.dataprovider.handler.datahandler.impl;

import android.content.Context;
import android.os.RemoteException;

import com.syberos.shuili.service.AccidentInformationEntity;
import com.syberos.shuili.service.IAccidentListCallback;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.handler.datahandler.DataHandlerBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/16.
 */

public class AccidentDataHandler extends DataHandlerBase {
    /**
     * 构造方法，接受了一个context对象 并且注册了networkCallback
     *
     * @param context Context
     */
    public AccidentDataHandler(Context context) {
        super(context);
    }

    @Override
    public void offLineRequest(Map params, Object callback) {
        List<AccidentInformationEntity> infoList = getAccidentList();
        try {
            ((IAccidentListCallback)callback).onSuccess(infoList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    private List<AccidentInformationEntity> getAccidentList() {
        List<AccidentInformationEntity> infoList = new ArrayList<>();
        String strSql = "select * from " + DBDefinition.COMMIT_TABLE + " where  url = http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objAcci/ " +
      "and " + DBDefinition.LocalStatus + " = 0";
        String []selectionArgs = new String[]{};
        ArrayList<HashMap<String,String>> info = dbHelper.rawQuery(strSql, selectionArgs);
        if(info != null){
            for(HashMap  item:info){
                infoList.add(map2AccidentInformationEntity(item));

            }
        }
        return infoList;
    }
    private AccidentInformationEntity map2AccidentInformationEntity(HashMap<String,String> map){
        AccidentInformationEntity accidentInformationEntity = new AccidentInformationEntity(map.get("acciWiunType"),map.get("occuTime"),map.get("acciCate"),
                map.get("casNum"),"",map.get("collTime"),
                map.get("serInjNum"),"",map.get("acciSitu"),"",map.get("acciWiunGuid"),"",map.get("econLoss"));
        return accidentInformationEntity;

    }
}
