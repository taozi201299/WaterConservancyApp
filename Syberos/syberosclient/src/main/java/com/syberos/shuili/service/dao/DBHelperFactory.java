package com.syberos.shuili.service.dao;

import android.content.Context;

/**
 * Created by jidan on 18-3-7.
 */

public class DBHelperFactory {
    /**
     * 单例对象
     */
    private static DBHelper Instance = null;

    /**
     * 数据库文件名
     */
    public static final String DB_NAME = "Syberos_shuiLi.db";

    /**
     * DBHelper
     *
     * @param context Context
     * @return DBHelper
     */
    public static DBHelper getDBHelper(Context context) {
            if (Instance == null) {
                Instance = new DBHelper(context, DB_NAME);
            }
            Instance.open();
            return Instance;
    }
}
