package com.syberos.shuili.service.dataprovider.dbconfig.def;


/**
 * Created by jidan on 18-3-7.
 */
/**
 * 数据库常量定义
 */
public class DBDefinition {
    public static final String COMMIT_TABLE = "Local_Cache_Table";
    public static final String USER_TABLE = "UserInfo_Table";
    public static final String MESSAGE_TABLE = "MessageInfo_Table";
    public static final String ATTACHMENT_TABLE = "AttachMent_Table";

    /**
     *  提交表table filed
     */
    public static final String FILED_ID = "ID";
    public static final String COMMIT_URL = "url";
    public static final String COMMIT_JSONPATH = "JsonFilePath";
    public static final String COMMIT_BINARY_PATH = "BinaryFilePath";
    public static final String COMMIT_STATUS = "LocalStatus";
    /**
     * 用户表table filed
     */
    public static final String id = "id";
    public static final String admDutyLevel = "admDutyLevel";
    public static final String depCode = "depCode";
    public static final String depId = "depId";
    public static final String depName = "depName";
    public static final String modifier = "modifier";
    public static final String note = "note";
    public static final String orgCode ="orgCode";
    public static final String orgId= "orgId";
    public static final String orgName = "orgName";
    public static final String password = "password";
    public static final String persId = "persId";
    public static final String persName = "persName";
    public static final String persType = "persType";
    public static final String phone ="phone";
    public static final String status ="status";
    public static final String ts = "ts";
    public static final String userCode = "userCode";
    public static final String userName = "userName";
    public static final String userType = "userType";

    /**
     * 信息表
     */
    public static String messageId = "messageId";
    public static String title = "title";
    public static String content = "content";
    public static String publisher = "publisher";
    public static String publishDate = "publishDate";
    public static String readStatus ="readStatus";
    public static String serverDate ="serverDate";
    public static String organizationId ="organizationId";
    public static String isDelete = "isDelete";

    /**
     * 多媒体文件表
     */
    public static String url = "url";
    public static String medName = "medName";
    public static String medType ="medType";
    public static String medPath = "medPath";
    public static String collTime ="collTime";
    public static String bisTableName="bisTableName";
    public static String bisGuid = "bisGuid";
    public static String fromDate = "fromDate";

    /**
     * 提交表
     */

    public static String ID = "ID";
    public static String JsonString = "JsonString";
    public static String LocalStatus = "LocalStatus";
    public static String localStatus = "localStatus";
    public static String seriesKey = "seriesKey";

}
