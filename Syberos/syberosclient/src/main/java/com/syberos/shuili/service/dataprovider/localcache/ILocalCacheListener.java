package com.syberos.shuili.service.dataprovider.localcache;


import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;

/**
 * Created by jidan on 18-3-8.
 * ILocalCacheListener
 */
public interface ILocalCacheListener {
    /**
     * 提交开始回调
     * @param type 业务类型
     */
    void onStart(DataOperationType type);

    /**
     * 提交出错回调
     * @param type　业务类型
     * @param errorCode 错误码
     * @param errorDescription 错误说明
     */
    void onError(DataOperationType type, int errorCode, String errorDescription);

    /**
     * 提交停止回调
     * @param type 业务类型
     */
    void onStop(DataOperationType type);

    /**
     * 提交进度回调
     * @param type 业务类型
     * @param progress 进度
     * @param description 说明
     */
    void onProgress(DataOperationType type, int progress, String description);

    /**
     * 提交完成回调
     * @param type 业务类型
     * @param result 结果true:成功, false:失败
     */
    void onFinish(DataOperationType type, boolean result,int errorCode,String errMsg);
}