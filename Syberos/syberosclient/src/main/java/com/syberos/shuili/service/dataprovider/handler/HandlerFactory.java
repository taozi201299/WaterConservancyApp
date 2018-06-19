package com.syberos.shuili.service.dataprovider.handler;

import android.content.Context;


/**
 * Created by helpzhl on 16-7-5.
 * HandlerFactory
 */

public class HandlerFactory {
    /**
     * 单例对象
     */
    private static IHandlerManager INSTANCE = null;

    /**
     * 获取HandlerManager对象
     * @param context Context
     * @return IHandlerManager
     */
    public static IHandlerManager getHandlerManager(Context context) {
        if(INSTANCE == null)
            INSTANCE = new HandlerManagerImpl(context);
        return INSTANCE;
    }
}
