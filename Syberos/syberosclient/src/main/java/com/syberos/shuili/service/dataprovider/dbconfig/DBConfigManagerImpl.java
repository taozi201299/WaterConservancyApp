package com.syberos.shuili.service.dataprovider.dbconfig;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.configs.DBConfigFactory;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;

import java.util.List;


/**
 * Created by jidan on 18-3-7.
 */
public class DBConfigManagerImpl implements IDBConfigManager {

    private Context context;

    public DBConfigManagerImpl(Context context){
        this.context = context;

    }
    @Override
    public List<TableConfig> getTables(DataOperationType type) {
        IDBConfig idbConfig = DBConfigFactory.getDBConfig(type, context);
        return idbConfig.getTables();
    }

    @Override
    public TableConfig getTable(DataOperationType type, String name) {
        IDBConfig idbConfig = DBConfigFactory.getDBConfig(type, context);
        return idbConfig.getTable(name);
    }
}
