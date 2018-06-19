package com.syberos.shuili.service.dataprovider.sync.synchronizer;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;

import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by helpzhl on 16-6-14.
 * SyncBusinessBase
 */
public abstract class SynchronizerBase implements ISynchronizer {
    /**
     * Context
     */
    protected Context context;
    /**
     * 业务类型
     */
    protected DataOperationType type;

    /**
     * 数据库操作对象
     */
    protected DBHelper dbHelper;

    protected Handler mHandler;

    /**
     * 构造函数
     * @param context Context
     */
    public SynchronizerBase(Context context){
        this.context = context;

    }
   public void setDBAdapterListener(DBHelper helper){
        this.dbHelper = helper;
    }
    /**
     * 创建数据库表格
     * @param tableName 表名称
     * @param tableFieldList 表结构
     */
    public final void createTable(String tableName, List<TableField> tableFieldList){
        String sqlStr = "create table " + tableName + "(";
        for(int i = 0; i < tableFieldList.size(); i++){
            TableField tmpField = tableFieldList.get(i);
            sqlStr += tmpField.getName() + " " + tmpField.getType();
            if(tmpField.isKey()) {
                sqlStr += " primary key";
            }
            if(!tmpField.isEnableNull()) {
                sqlStr += " not null";
            }
            if(i != tableFieldList.size() -1){
                sqlStr += ",";
            }
        }
        sqlStr += ")";
        dbHelper.execSQL(sqlStr);
    }
    public  void setHandler(Handler handler){
        this.mHandler = handler;
    }
}
