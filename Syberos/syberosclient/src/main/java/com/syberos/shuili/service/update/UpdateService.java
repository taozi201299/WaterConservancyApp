package com.syberos.shuili.service.update;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.RemoteViews;

import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.https.HttpsUtils;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.R;

import java.io.File;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jidan on 18-3-16.
 */

public class UpdateService extends Service {

    /**
     * 通知栏管理器
     */
    NotificationManager nm = null;

    /**
     * 通知对象
     */
    Notification notification = null;

    long iFileLength = 0;
    float fDownLoadLength= 0;

    private static String appDir = "/sdcard/";
    private static String appName = "shuli.apk";
    private int mProcess = 0;

    private  final String id = "channel_1";
    private  final String name = "channel_name_1";
    NotificationChannel channel = null;



    /**
     * 处理流程对象
     */
    public enum processType {
        /**
         * 开始处理
         */
        PROCESS_BEGIN,

        /**
         * 处理中
         */
        PROCESS_LOADING,

        /**
         * 处理完成
         */
        PROCESS_FINISH,

        /**
         * 处理失败
         */
        PROCESS_FAILED,
        /**
         * 处理取消
         */
        PROCESS_CANCEL
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra("url");
        doAppUpdate(url);
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpUtils.getInstance().cancleHttp("doAppUndate");
    }

    public Notification.Builder getChannelNotification(String title, String content) {
            return new Notification.Builder(getApplicationContext(), id)
                    .setContentTitle(title)
                    .setChannelId(id)
                    .setContentText(content)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setCustomContentView(new RemoteViews(getPackageName(), R.layout.notification_custom_view))
                    .setAutoCancel(false);
    }
    public NotificationCompat.Builder getNotification_25(String title, String content) {
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCustomContentView(new RemoteViews(getPackageName(), R.layout.notification_custom_view))
                .setAutoCancel(true);
    }
    private void doAppUpdate(String url){
        String apUrl = UpdateManager.DEFAULT_SERVER +"/mapp/"+ "downloadVersion";
        HashMap<String,String> map = new HashMap<>();
        String tag = "downloadVersion";
        map.put("fileUrl",url);
        File file = new File(appDir  +appName);
        if(file.exists()){
            file.delete();
        }
        setNotification(processType.PROCESS_BEGIN,appDir+ appName,0);
        HttpUtils.getInstance().requestNet_download(apUrl, map, tag, new FileCallback(appDir,appName) {
            @Override
            public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                setNotification(processType.PROCESS_FINISH,appDir + appName,0);
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                iFileLength = totalSize;
                if((int)(progress *100) > mProcess) {
                    setNotification(processType.PROCESS_LOADING, appDir + appName, progress);
                    mProcess ++ ;
                }
            }

            @Override
            public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                setNotification(processType.PROCESS_FAILED,appDir + appName,0);
            }
        });

    }

    /**
     * 设置notification
     *
     * @param type       下载类型
     * @param filename   文件名称
     * @param downloaded 下载进度大小
     */
    private void setNotification(processType type, String filename, float downloaded) {
        if(channel == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            }
        }
        switch (type) {
            case PROCESS_BEGIN: {
                nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    nm.createNotificationChannel(channel);
                }
                String notification_text_loading = "下载中";
                if (notification == null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notification = getChannelNotification("水利安监", notification_text_loading).build();
                    } else {
                       notification = getNotification_25("水利安监",notification_text_loading).build();
                    }
                    if (notification.contentView == null) {
                        notification.contentView = new RemoteViews(getPackageName(), R.layout.notification_custom_view);
                    }
                    notification.flags = Notification.FLAG_ONGOING_EVENT;
                    notification.contentView.setTextViewText(R.id.down_tv, notification_text_loading);
                    notification.contentView.setProgressBar(R.id.pb, 100, 0, false);
                    break;
                }
            }
            case PROCESS_LOADING: {
                notification.contentView.setTextViewText(R.id.process, (Integer.toString((int) (downloaded * 100)) + "%"));
                notification.contentView.setProgressBar(R.id.pb, 100, (int) (downloaded * 100), false);
                break;
            }
            case PROCESS_FAILED: {
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                String notification_text_failed = "下载失败";
                notification.contentView.setTextViewText(R.id.down_tv, notification_text_failed);
                stopSelf();
                break;
            }
            case PROCESS_FINISH: {
                String notification_text_finish = "下载完成";
                notification.contentView.setTextViewText(R.id.down_tv, notification_text_finish);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                /**
                 * 需要适配7.0的安装
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                {
                    installIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(this, "com.syberos.shuili.fileprovider", new File(filename));
                    installIntent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    installIntent.setDataAndType(Uri.parse("file://" + filename), "application/vnd.android.package-archive");
                }
                notification.contentIntent = PendingIntent.getActivity(this, 0, installIntent, 0);
                nm.notify(0, notification);
                stopSelf();
                return;
            }
            case PROCESS_CANCEL:
                break;
            default:
                stopSelf();
                break;
        }
        nm.notify(0, notification);

    }
}
