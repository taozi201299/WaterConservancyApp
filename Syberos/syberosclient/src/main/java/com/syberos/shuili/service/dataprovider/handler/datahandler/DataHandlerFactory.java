package com.syberos.shuili.service.dataprovider.handler.datahandler;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.handler.datahandler.impl.AccidentDataHandler;
import com.syberos.shuili.service.dataprovider.handler.datahandler.impl.MessageDataHandler;
import com.syberos.shuili.service.dataprovider.handler.datahandler.impl.UserInfoDataHandler;

import java.util.ArrayList;
import java.util.List;


public class DataHandlerFactory {

    static UserInfoDataHandler userInfoDataHandler = null;
    static MessageDataHandler messageDataHandler = null;
    static AccidentDataHandler accidentDataHandler = null;


    /**
     * 根据type来返回不同的handler
     *
     * @param type type是根据BusinessMapping映射来确定返回哪个handler
     * @param context Context
     * @return 返回了不同的handler，每个handler只需要关注自己对应的业务即可
     */
    public static IDataHandler getDataHandler(DataOperationType type, Context context) {

        IDataHandler dataHandler = null;
        switch (type) {
            case BUSINESS_SUBMIT:
            case USER_INFO:
                if (userInfoDataHandler == null)
                    userInfoDataHandler = new UserInfoDataHandler(context);
                dataHandler = userInfoDataHandler;
                break;
            case Message_Info:
                if(messageDataHandler == null){
                    messageDataHandler = new MessageDataHandler(context);
                }
                dataHandler = messageDataHandler;
                break;
            case Accident_Info:
                if(accidentDataHandler == null){
                    accidentDataHandler = new AccidentDataHandler(context);
                }
                dataHandler = accidentDataHandler;
                break;
        }
        return dataHandler;
    }
    public static List<IDataHandler> getAllDataHandler(Context context){
        List<IDataHandler> handlerList = new ArrayList<>();
        DataOperationType[] dops = DataOperationType.values();
        for (DataOperationType type:dops) {
            handlerList.add(getDataHandler(type,context));
        }
        return handlerList;
    }

}
