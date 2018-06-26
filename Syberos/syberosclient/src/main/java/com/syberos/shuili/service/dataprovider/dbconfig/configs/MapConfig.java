package com.syberos.shuili.service.dataprovider.dbconfig.configs;

import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */

public class MapConfig extends DBConfigBase {
    /**
     *业务表结构文件
     */
    private static final String MAP_XML_FILE = "map.xml";


    /**
     * 所有表配置
     */
    private List<TableConfig> tableConfigList;
    /**
     * 构造函数
     * @param context 上下文
     */
    public MapConfig(Context context) {
        super(context);
        try {
            tableConfigList = parseTablesByRes(context.getAssets().open(MAP_XML_FILE));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 获取所有表
     * @return 表列表
     */
    @Override
    public List<TableConfig> getTables() {
        return tableConfigList;
    }

    /**
     * 获取当前业务类型
     * @return 业务类型
     */
    @Override
    public DataOperationType getType() {
        return DataOperationType.Map_Info;
    }
    /**
     * 获取单个表
     * @param name 表名
     * @return 表结构
     */
    @Override
    public TableConfig getTable(String name) {
        for (TableConfig config : tableConfigList) {
            if(config.getName().equals(name)){
                return config;
            }
        }
        return null;
    }
}
