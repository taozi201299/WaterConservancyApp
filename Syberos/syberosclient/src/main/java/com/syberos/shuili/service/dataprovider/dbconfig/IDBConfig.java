package com.syberos.shuili.service.dataprovider.dbconfig;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;

import java.util.List;


/**
 * Created by jidan on 18-3-7.
 */
public interface IDBConfig {
    /**
     * 获取TableConfig的结合List
     *
     * @return List<TableConfig>
     */
    List<TableConfig> getTables();

    /**
     * 根据表名获取 TableConfig
     *
     * @param name 表名
     * @return TableConfig
     */
    TableConfig getTable(String name);

    /**
     * 获取数据操作类型
     *
     * @return 类型
     */
    DataOperationType getType();
}
