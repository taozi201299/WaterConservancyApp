package com.syberos.shuili.service.update;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;

import java.util.HashMap;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by jidan on 18-3-17.
 */

public class UpdateManager {

    private static final String TAG = UpdateManager.class.getSimpleName();
    public static final String DEFAULT_SERVER = "http://172.16.160.179:8001/";
    private static final String PRODUCT_APP_ID = "5cb345b6-26d3-11e5-9325-68f728009cac";

    public static  String appUrl = "";
    public static boolean bUpdate = true;

    private static CustomDialog customDialog;

    public static void initMenuData(RequestCallback<String> callback){

        String url = DEFAULT_SERVER + "/WebApi/DataExchange/GetData/WebApp_CheckAppVersion";
        HashMap<String,String> params = new HashMap<>();
        params.put("dataKey","00-00-00-00");
        params.put("AppVersion","1");
        params.put("ApplicationID",PRODUCT_APP_ID);
        HttpUtils.getInstance().requestGet(url,params,"",callback, CacheMode.DEFAULT);

    }
    /**
     * 判断服务是否正在运行
     *
     * @return
     */
    public static boolean isServiceRunning(Context context) {
        ActivityManager manager = (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            Log.d(TAG,service.service.getClassName());
            if ("com.syberos.shuili.service.update.UpdateService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public static void showDialog(final Context context,View view){
        if (!bUpdate) {
            customDialog = new CustomDialog(context);
            customDialog.setDialogMessage("版本更新", null, null);
            customDialog.setMessage("当前已是最新版本");
            customDialog.setDialogOneBtn();
            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });

        }else {
            if(isServiceRunning(context)){
                ToastUtils.show("正在下载安装包");
                return;
            }
            customDialog = new CustomDialog(context);
            customDialog.setDialogMessage("版本更新", "稍后再说", "立即更新");
            customDialog.showUpdate();
            customDialog.setUpdate("", "");
            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.show("开始下载更新包", 0);
                    Intent intent = new Intent(context, UpdateService.class);
                    intent.putExtra("url",appUrl);
                    context.startService(intent);
                    customDialog.dismiss();
                }
            });

        }
        customDialog.show();

    }
}
