package com.syberos.shuili.service.dataprovider.localcache.dataLocalCache.impl;
import android.content.ContentValues;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okhttputils.callback.StringCallback;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.App;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.IResultCallback;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DBDefinition;
import com.syberos.shuili.service.dataprovider.localcache.dataLocalCache.DataLocalCacheBase;
import com.syberos.shuili.service.entity.AccidentFormReturnEntity;
import com.syberos.shuili.service.entity.FormCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.NetworkUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jidan on 18-3-8.
 * 提交业务模块
 */
public class BusinessdataLocalCache extends DataLocalCacheBase {

    int iFailedCount = 0;
    int iSuccessCount = 0;
    int iTotalCacheCount = 0;
    List cacheList;
    List binariesList;
    boolean isRunning = false;
    boolean submitBinaryRunning = false;
    /**
     * 构造函数
     *
     * @param context Context
     */
    public BusinessdataLocalCache(Context context) {
        super(context);
    }

    @Override
    public boolean addCache(String url, Map params, Map files, IResultCallback callback) {
        // TODO: 2018/4/14 id 用于标记任务ID
        return true;
    }

    @Override
    public boolean addCache(LocalCacheEntity localCacheEntity,List<AttachMentInfoEntity>attachMentInfoEntities, IResultCallback callback) {
        if(localCacheEntity == null) return false;
        writeCacheTable(localCacheEntity);
        writeBinaryTable(localCacheEntity,attachMentInfoEntities);
        try {
            callback.onSuccess();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(NetworkUtil.isNetworkAvailable()){
           submitLocalCache();
        }
        return true;
    }
    private  void writeCacheTable(LocalCacheEntity localCacheEntity){
        HashMap<String,String>valueMap = (HashMap<String, String>) localCacheEntity.params;
        String strID = localCacheEntity.seriesKey;
        String strUrl = localCacheEntity.url;
        Gson gson = new Gson();
        String strJson = gson.toJson(valueMap);
        String strStatus = String.valueOf(localCacheEntity.type);
        ContentValues values = new ContentValues();
        values.put("ID", strID);
        values.put("url", strUrl);
        values.put("JsonString", strJson);
        values.put("LocalStatus", strStatus);
        values.put("commitType",localCacheEntity.commitType);
        dbHelper.insert(DBDefinition.COMMIT_TABLE, values);

    }
    private void writeBinaryTable(LocalCacheEntity localCacheEntity,List<AttachMentInfoEntity>attachMentInfoEntities){
        if(attachMentInfoEntities == null) return;
        for(AttachMentInfoEntity info : attachMentInfoEntities){
            ContentValues values = new ContentValues();
            values.put("seriesKey",localCacheEntity.seriesKey);
            values.put("url",info.url == null ?"http://192.168.1.8:8080/sjjk/v1/jck/attMedBase/":info.url);
            values.put("medName", info.medName);
            values.put("medType", info.medType);
            values.put("medPath", info.medPath);
            values.put("collTime", CommonUtils.getCurrentDate());
            values.put("bisTableName", info.bisTableName == null ?"":info.bisTableName);
            values.put("bisGuid", info.bisGuid == null ? "":info.bisGuid);
            values.put("fromDate", info.fromDate);
            values.put("LocalStatus",info.localStatus);
            dbHelper.insert(DBDefinition.ATTACHMENT_TABLE, values);
        }

    }
    @Override
    public void submitLocalCache() {
        if(submitTask.size() <=1){
            submitTask.add(new CommitTask(UUID.randomUUID().toString()));
        }
        submitJson();
        if (submitBinaryTask.size() <= 1) {
            submitBinaryTask.add(new CommitTask(UUID.randomUUID().toString()));
        }
        submitBinary();
    }
    /**
     * 从数据库读取二进制数据并提交
     */
    private void submitBinary() {
        if (submitBinaryRunning)
            return;

        if (submitBinaryTask.size() >= 1) {
            submitBinaryRunning = true;
            String selection = DBDefinition.localStatus + "=?";
            String[] selectionArgs = {"1"};
            binariesList = dbHelper.query(DBDefinition.ATTACHMENT_TABLE, null, selection, selectionArgs, AttachMentInfoEntity.class);
            if (binariesList == null || binariesList.isEmpty()) {
                submitBinaryTask.remove(0);
                submitBinaryRunning = false;
                submitBinary();
            } else {
                commitBinaryCache();
            }
        }
    }
    /**
     * 从数据库读取设局并提交
     */
    private void submitJson(){
        if(isRunning)
            return;
        if(submitTask.size() >=1){
            isRunning = true;
            iTotalCacheCount = 0;
            iSuccessCount = 0;
            iFailedCount = 0;
            String selection = DBDefinition.localStatus + "=?";
            String[] selectionArgs = {"1"};
            cacheList = dbHelper.query(DBDefinition.COMMIT_TABLE,null,null,null,FormCacheEntity.class);
            if(cacheList == null || cacheList.isEmpty()){
                submitTask.remove(0);
                isRunning = false;
                submitJson();
            }else {
                iTotalCacheCount = cacheList.size();
                commitCache();
            }
        }
    }
    private void commitCache(){
        if(cacheList != null && !cacheList.isEmpty()) {
            final FormCacheEntity entity = (FormCacheEntity)cacheList.remove(0);
            String strJson = entity.JsonString;
            Gson gson = new Gson();
            HashMap<String,String>params = gson.fromJson(strJson, HashMap.class);
            final String url = entity.url;
            if(entity.commitType  == 0) {
                HttpUtils.getInstance().requestNet_post(url, params, url, new RequestCallback<String>() {
                    @Override
                    public void onResponse(String result) {
                        iSuccessCount++;
                        // TODO: 2018/4/16 如果是新增事件 需要将附件localStatus 修改为1
                        if (entity.LocalStatus.equals("0")) {
                            Gson gson = new Gson();
                            AccidentFormReturnEntity accidentFormReturnEntity = gson.fromJson(result, AccidentFormReturnEntity.class);
                            updateAttachLocalStatus(url, entity.ID, accidentFormReturnEntity.dataSource.getGuid());
                        }
                        deleteFormCache(entity.ID);
                        resultProcess();
                    }

                    @Override
                    public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                        iFailedCount++;
                        resultProcess();
                    }
                });
            }else if(entity.commitType == 1) {
                HttpUtils.getInstance().requestNet_put(url, params, entity.ID, new RequestCallback<String>() {
                    @Override
                    public void onResponse(String result) {
                        Log.d("22222222222222", result);
                        iSuccessCount++;
                        // TODO: 2018/4/16 如果是新增事件 需要将附件localStatus 修改为1
                        if (entity.LocalStatus.equals("0")) {
                            Gson gson = new Gson();
                            AccidentFormReturnEntity accidentFormReturnEntity = gson.fromJson(result, AccidentFormReturnEntity.class);
                            updateAttachLocalStatus(url, entity.ID, accidentFormReturnEntity.dataSource.getGuid());
                        }
                        deleteFormCache(entity.ID);
                        resultProcess();
                    }

                    @Override
                    public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                        iFailedCount++;
                        resultProcess();
                    }
                });
            }
        }
    }
    private void updateAttachLocalStatus(String url,String id,String response){
        /**
         * 新增事故返回
         */
        if(url.equals(App.strCJIP +"/wcsps-api/cj/yuanXin/Accident/create") ||
                url.equals(App.strCJIP +"/wcsps-api/cj/obj/hidd/addObjHidd")) {
            String selection = DBDefinition.seriesKey + "=?";
            String[] selectionArgs = {id};
            List list = dbHelper.query(DBDefinition.COMMIT_TABLE, null, null, null, AttachMentInfoEntity.class);
            if (list == null || list.isEmpty()) {
                return;
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBDefinition.bisGuid, response);
                contentValues.put(DBDefinition.localStatus, "1");
                dbHelper.update(DBDefinition.ATTACHMENT_TABLE, contentValues, selection, selectionArgs);
            }
            submitLocalCache();
        }
        }
    private void resultProcess() {
        if (iSuccessCount + iFailedCount == iTotalCacheCount) {
            submitTask.remove(0);
            isRunning = false;
            submitJson();
        } else {
            commitCache();
        }
    }
    private void commitBinaryCache(){
        if (binariesList != null && !binariesList.isEmpty()) {
            final AttachMentInfoEntity entity = (AttachMentInfoEntity) binariesList.remove(0);
            String url = entity.url;
            String strMedGuid = UUID.randomUUID().toString().replace("-","");
            HashMap<String,String> params = new HashMap<>();
            File file = new File(entity.medPath);
            params.put("bisTablename",entity.bisTableName);
            params.put("bisGuid",entity.bisGuid);
            params.put("medName",entity.medName);
            params.put("medType",entity.medType);
            params.put("medTitl",entity.medName);
            params.put("medPath",entity.medPath);
            params.put("medSize",String.valueOf(file.length()));
            params.put("collTime",CommonUtils.getCurrentDate());
            params.put("medGuid",strMedGuid);
            params.put("fromDate",CommonUtils.getCurrentDate());
            HashMap<String,File>binaryFiles = new HashMap<>();
            binaryFiles.put(entity.medName,file);
            HttpUtils.getInstance().requestNet_File(url, params, binaryFiles, url, new StringCallback() {
                @Override
                public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                    resultBinaryProcess();
                    deleteBinary(entity.medName,entity.medPath);
                }

                @Override
                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                    resultBinaryProcess();
                }
            });
        }
    }
    /**
     * 处理二进制提交结果
     */
    private void resultBinaryProcess() {
        if (binariesList.isEmpty()) {
            submitBinaryTask.remove(0);
            submitBinaryRunning = false;
            submitBinary();
        } else {
           commitBinaryCache();
        }
    }
    private void deleteFormCache(String id){
        String whereClause = DBDefinition.ID + "=?";
        String[] whereArgs = new String[]{id};
        dbHelper.delete(DBDefinition.COMMIT_TABLE, whereClause, whereArgs);
    }
    private void deleteBinary(String name,String path){
        /**
         * step 1 删除本地文件
         */
        String fileName = path + "/" + name;
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
            /**
             * Step 2. 删除二进制缓存表中对应数据
             */
        String whereClause = DBDefinition.medName + "=?";
        String[] whereArgs = new String[]{name};
        dbHelper.delete(DBDefinition.ATTACHMENT_TABLE, whereClause, whereArgs);
}
}
