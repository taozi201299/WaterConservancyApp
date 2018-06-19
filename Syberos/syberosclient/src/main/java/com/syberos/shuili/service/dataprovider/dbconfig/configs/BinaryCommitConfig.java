package com.syberos.shuili.service.dataprovider.dbconfig.configs;

/**
 * Created by Administrator on 2018/4/14.
 */


import android.content.Context;

import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;

import java.io.IOException;
import java.util.List;

/**
 * Created by jidan on 18-3-7.
 */

public class BinaryCommitConfig extends DBConfigBase {

    /**
     *业务表结构文件
     */
    private static final String BINARY_XML_FILE = "attachment.xml";


    /**
     * 所有表配置
     */
    private List<TableConfig> tableConfigList;
    /**
     * 构造函数
     * @param context 上下文
     */
    public BinaryCommitConfig(Context context) {
        super(context);
        try {
            tableConfigList = parseTablesByRes(context.getAssets().open(BINARY_XML_FILE));
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
        return DataOperationType.BINARY;
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

