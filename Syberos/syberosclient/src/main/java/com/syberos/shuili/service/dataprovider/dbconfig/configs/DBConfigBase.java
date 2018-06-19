package com.syberos.shuili.service.dataprovider.dbconfig.configs;

import android.content.Context;
import android.util.Xml;

import com.syberos.shuili.service.dataprovider.dbconfig.IDBConfig;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableConfig;
import com.syberos.shuili.service.dataprovider.dbconfig.def.TableField;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jidan on 18-3-7.
 */
public abstract class DBConfigBase implements IDBConfig {

    /**
     * context
     */
    protected Context context;

    /**
     * 获取TableConfig的结合List
     *
     * @return List<TableConfig>
     */
    @Override
    public abstract List<TableConfig> getTables() ;
    /**
     * 根据表名获取 TableConfig
     *
     * @param name 表名
     * @return TableConfig
     */
    @Override
    public abstract TableConfig getTable(String name);
    /**
     * 获取数据操作类型
     *
     * @return 类型
     */
    @Override
    public abstract DataOperationType getType();

    /**
     * 构造函数
     * @param context 上下文
     */
    public DBConfigBase(Context context) {
        this.context = context;
    }

    /**
     * 解析数据库表
     * @param stream  stream
     * @return
     */
    protected List<TableConfig> parseTablesByRes(InputStream stream) {
        TableParser parser = new TableParser();
        return parser.parseTables(stream);
    }

    /**
     * 解析数据库表
     */
    public class TableParser {
        private String TAG_TABLES = "Tables";
        private String TAG_TABLE = "Table";
        private String TAG_FIELD = "Field";
        private String ATTR_NAME = "name";
        private String ATTR_DES = "description";
        private String ATTR_TYPE = "type";
        private String ATTR_LENGTH = "length";
        private String ATTR_KEY = "isKey";
        private String ATTR_NULL = "enableNull";

        protected List<TableConfig> parseTables(InputStream stream) {
            List<TableConfig> out = new ArrayList<>();
            TableConfig table = new TableConfig();
            XmlPullParser parser = Xml.newPullParser();
            try {
                parser.setInput(stream, "utf-8");
                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals(TAG_TABLES)) {
                                break;
                            } else if (parser.getName().equals(TAG_TABLE)) {
                                table = new TableConfig();
                                table.setName(parser.getAttributeValue(null, ATTR_NAME));
                                table.setDescription(parser.getAttributeValue(null, ATTR_DES));
                            } else if (parser.getName().equals(TAG_FIELD)) {
                                TableField field = new TableField();
                                field.setName(parser.getAttributeValue(null, ATTR_NAME));
                                field.setType(parser.getAttributeValue(null, ATTR_TYPE));
                                String tmp = parser.getAttributeValue(null, ATTR_LENGTH);
                                field.setLength(Integer.valueOf(tmp));
                                tmp = parser.getAttributeValue(null, ATTR_KEY);
                                field.setKey(tmp.equals("true"));
                                tmp = parser.getAttributeValue(null, ATTR_NULL);
                                field.setEnableNull(tmp.equals("true"));
                                table.addField(field);
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals(TAG_TABLE)) {
                                out.add(table);
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return out;
        }
    }
}

