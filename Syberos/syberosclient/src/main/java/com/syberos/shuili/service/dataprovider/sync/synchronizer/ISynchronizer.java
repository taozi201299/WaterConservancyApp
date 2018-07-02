package com.syberos.shuili.service.dataprovider.sync.synchronizer;


import android.os.Bundle;
import android.os.Handler;

import com.syberos.shuili.service.dao.DBHelper;

import java.util.Map;


/**
 * Created by helpzhl on 16-6-14.
 * ISynchronizer
 */
public interface ISynchronizer {


    /**
     * 设置数据库操作对象
     * @param helper 数据库操作对象
     */
    void setDBAdapterListener(DBHelper helper);
    /**
     * 初始化本地存储
     */
    abstract void initLocalStorage();

    void syncData(Bundle bundle);

    void setHandler(Handler handler);

    void deleteData(Object data);
    void setInfoStatus(Object data);
    void synsMapInfo(Object data);



}
