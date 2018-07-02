package com.syberos.shuili.service.dataprovider.sync.synchronizer;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.impl.AdressListSynchronizer;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.impl.BinarySynchronizer;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.impl.BusinessSynchronizer;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.impl.MapSynchronizer;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.impl.MessageSynchronizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Company: SyberOS BeiJing
 * Project: Device Inspection
 * Created by 陈冬 on 16/6/15.
 * SynchronizerFactory
 */
public class SynchronizerFactory {


    /**
     * commit
     */
    private static BusinessSynchronizer businessSynchronizer = null;
    private static AdressListSynchronizer  adressListSynchronizer = null;
    private static MessageSynchronizer messageSynchronizer = null;
    private static BinarySynchronizer binarySynchronizer = null;
    private static MapSynchronizer mapSynchronizer = null;

    /**
     * 获取业务的同步实例
     * @param type 业务类型
     * @param context Context
     * @return ISynchronizer
     */
    public static ISynchronizer getSynchronizer(DataOperationType type, Context context) {
        ISynchronizer synchronizer = null;
        switch (type) {
            case BUSINESS_SUBMIT:
                if(businessSynchronizer == null) {
                    businessSynchronizer = new BusinessSynchronizer(context);
                }
                synchronizer = businessSynchronizer;
                break;
            case USER_INFO:
                if(adressListSynchronizer == null){
                    adressListSynchronizer = new AdressListSynchronizer(context);
                }
                synchronizer = adressListSynchronizer;
                break;
            case Message_Info:
                if(messageSynchronizer == null){
                    messageSynchronizer = new MessageSynchronizer(context);
                }
                synchronizer = messageSynchronizer;
                break;
            case BINARY:
                if(binarySynchronizer == null){
                    binarySynchronizer = new BinarySynchronizer(context);
                }
                synchronizer = binarySynchronizer;
                break;
            case Map_Info:
                if(mapSynchronizer == null){
                    mapSynchronizer = new MapSynchronizer(context);
                }
                synchronizer = mapSynchronizer;
                break;
            default:
                break;
        }
        return synchronizer;
    }

    /**
     * 获取所有业务的同步实例
     * @param context Context
     * @return List<ISynchronizer>
     */
    public static List<ISynchronizer> getAllSynchronizer(Context context){
        List<ISynchronizer> synchronizerList = new ArrayList<>();
        DataOperationType[] dops = DataOperationType.values();
        for (DataOperationType type:dops) {
            synchronizerList.add(getSynchronizer(type, context));
        }
        return synchronizerList;
    }

}
