package com.syberos.shuili.service.dataprovider.dbconfig;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;

import java.util.List;

/**
 * Created by jidan on 18-3-7.
 */
public interface IDBConfigManager {
    /**
     * 根据操作类型（DataOperationType），获得此模块下所有的TableConfig
     *
     * @param type 操作类型 DataOperationType
     * @return 此模块下所有的TableConfig
     */
    List<TableConfig> getTables(DataOperationType type);

    /**
     * 根据操作类型（DataOperationType）和表名（name），获得此模块该表的TableConfig
     *
     * @param type 操作类型（DataOperationType）
     * @param name 表名（name）
     * @return TableConfig
     */
    TableConfig getTable(DataOperationType type, String name);
}




