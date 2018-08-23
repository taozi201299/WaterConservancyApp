package com.syberos.shuili.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.syberos.shuili.R;
import com.syberos.shuili.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


public class CommonUtils {
    private static final String TAG = CommonUtils.class.getName();

    /**
     * 解析json数据
     *
     * @param
     * @param
     * @return
     */

    public static String beanToJson(Object o) {
        Gson gson = new Gson();
        String bean = gson.toJson(o);
        return bean;
    }

    /**
     * 解析json数据
     *
     * @param jsonString
     * @param clazz
     * @return
     */

    public static <T> T jsonToBean(String jsonString, Class<T> clazz) {
        Gson gson = new Gson();
        T bean = gson.fromJson(jsonString, clazz);
        return bean;
    }

    public static boolean isWorkDay(String pTime) {
        try {
            int time = dayForWeek(pTime);

            if (time == 6 || time == 7) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public  static Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        AssetManager am = App.globalContext().getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
    public  static List<File> listFileSortByModifyTime(String path) {
        List<File> list = getFiles(path, new ArrayList<File>());
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return -1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        return list;
    }
    public  static List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }
    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return  formatter.format(curDate);
    }
    public static Date stringToDate(String strTime)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = formatter.parse("2016-11-21");

        return date;
    }
    //字符串转指定格式时间
    public static String getMyDate(String str) {
        return StringToDate(str, "yyyy-MM-dd", "yyyy-M-d");
    }

    //字符串转指定格式时间
    public static String getToDate(String str) {
        return StringToDate(str, "yyyy-M-d", "yyyy-MM-dd");
    }

    /**
     * 根据对应日期格式转成相应格式
     * @param dateStr
     * @param dateFormatStr
     * @param formatStr
     * @return
     */
    public static String StringToDate(String dateStr, String dateFormatStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try{
            date = sdf.parse(dateStr);
        } catch (ParseException e){
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatStr);

        return s.format(date);
    }
    /**
     * 通过隐式意图调用系统安装程序安装APK
     */
    public static void installApk(Context context, String fileUrl) {
        LogUtils.d("路径", fileUrl);
        File file = new File(
                fileUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(context, "com.syberos.eim.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersion(String packageName) {
        try {
            PackageManager manager = App.globalContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    //版本名
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    //版本号
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    private static PackageInfo getPackageInfo() {
        PackageInfo pi = null;

        try {
            PackageManager pm = App.globalContext().getPackageManager();
            pi = pm.getPackageInfo(App.globalContext().getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 判断当前日期是星期几<br>
     * <br>
     *
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
//        dayForWeek = c.get(Calendar.DAY_OF_WEEK);
//        LogUtils.d("星期",dayForWeek);
        return dayForWeek;
    }

    /**
     * 判断当前日期是星期几<br>
     * <br>
     *
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayOfWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        dayForWeek = c.get(Calendar.DAY_OF_WEEK);
//        LogUtils.d("星期",dayForWeek);
        return dayForWeek;
    }

    public static String dayForWeekToString(String pTime) {


        String weekDay = "";
        try {
            String day = dayOfWeek(pTime) + "";
            LogUtils.d("星期", pTime + "------------" + day);
            if ("1".equals(day)) {
                weekDay = "周日";
            } else if ("2".equals(day)) {
                weekDay = "周一";
            } else if ("3".equals(day)) {
                weekDay = "周二";
            } else if ("4".equals(day)) {
                weekDay = "周三";
            } else if ("5".equals(day)) {
                weekDay = "周四";
            } else if ("6".equals(day)) {
                weekDay = "周五";
            } else if ("7".equals(day)) {
                weekDay = "周六";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekDay;

    }

    /**
     * unicode 转换成 utf-8
     *
     * @param theString
     * @return
     * @author fanhui
     * 2007-3-15
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * 获取本地ip
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            String ipv4 = null;
            List<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nilist) {
                List<InetAddress> ialist = Collections.list(ni.getInetAddresses());
                for (InetAddress address : ialist) {
                    ipv4 = address.getHostAddress();
                    if (!address.isLoopbackAddress() && (address instanceof Inet4Address))
                        return ipv4;
                }
            }
        } catch (SocketException ex) {

        }
        return "0.0.0.0";
    }


    public static boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    /***
     * 获取网关IP地址
     * @return
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
        } catch (Exception e) {
        }
        return null;
    }


    private static String formatIpAddress(int ipAdress) {

        return (ipAdress & 0xFF) + "." +
                ((ipAdress >> 8) & 0xFF) + "." +
                ((ipAdress >> 16) & 0xFF) + "." +
                (ipAdress >> 24 & 0xFF);
    }

    public static SSLContext getSSLContext() {
        // 生成SSLContext对象
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");

            // 从assets中加载证书
            InputStream inStream = App.globalContext().getAssets().open("ca" + "/" + "clientkey.pem");

            // 证书工厂
            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
            Certificate cer = cerFactory.generateCertificate(inStream);

            // 密钥库
            KeyStore kStore = KeyStore.getInstance("PKCS12");
            kStore.load(null, null);
            kStore.setCertificateEntry("trust", cer);// 加载证书到密钥库中
            // 密钥管理器
            KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFactory.init(kStore, null);// 加载密钥库到管理器

            // 信任管理器
            TrustManagerFactory tFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tFactory.init(kStore);// 加载密钥库到信任管理器
            // 初始化
            sslContext.init(keyFactory.getKeyManagers(), tFactory.getTrustManagers(), new SecureRandom());
            return sslContext;
        } catch (Exception e) {
            e.printStackTrace();
            return sslContext;
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }


    /**
     * 月份格式化
     *
     * @param mouth
     * @return
     */
    public static String mouthFormat(String mouth) {
        StringBuffer sb = new StringBuffer();
        String[] mouthSplit = mouth.split("-");
        sb.append(mouthSplit[0]);
        sb.append("年");
        sb.append(mouthSplit[1]);
        sb.append("月");

        return sb.toString();
    }

    /**
     * 关闭软键盘
     *
     * @param editText
     */
    public static void hideSoftPan(EditText editText) {
        if (editText != null) {
            InputMethodManager imm =
                    (InputMethodManager) App.globalContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }

    }

    public static int getSessionNotificationId(String sessionId, int sessionType) {

        String sessionKey = getSessionKey(sessionId, sessionType);
        int hashedNotificationId = (int) hashBKDR(sessionKey);
        return hashedNotificationId;
    }


    private static String getSessionKey(String sessionId, int sessionType) {
        return String.format("%s_%d", sessionId, sessionType);
    }

    private static String getSessionKey(String sessionId) {
        return String.format("%s_%d", sessionId);
    }

    /*
    * Java文件操作 获取文件扩展名
    *
    */
    private static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            String lowerPath = filename.toLowerCase();
            if (lowerPath.endsWith(".jpg") || lowerPath.endsWith(".jpeg")) {
                return "jpg";
            } else if (lowerPath.endsWith(".png")) {
                return "png";
            } else if (lowerPath.endsWith(".gif")) {
                return "gif";
            } else {
                int dot = lowerPath.lastIndexOf('.');
                if ((dot > -1) && (dot < (lowerPath.length() - 1))) {
                    return lowerPath.substring(dot + 1);
                }
            }
            return "png";
        }

        return filename;
    }
    /*
     * Java文件操作 获取不带扩展名的文件名
     *
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static String getFileName(String fileName) {

        if (TextUtils.isEmpty(fileName)) {
            return "";
        } else {
            String[] file = fileName.split("/");
            return file[file.length - 1];
        }
    }

public static boolean isSameImage(String pathUrl) {
    if(getPicType(pathUrl).equals(getExtensionName(pathUrl))){
        return true;
    }
    LogUtils.d("isSameImage","原始格式："+getPicType(pathUrl)+"现在格式："+getExtensionName(pathUrl) + " path:" + pathUrl);
    return  false;
}

    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 根据文件流判断图片类型
     * @param path
     * @return jpg/png/gif/bmp
     */
    public static String getPicType(String path) {
        //读取文件的前几个字节来判断图片格式
        String TYPE_GIF = "gif";
        String TYPE_PNG = "png";
         String TYPE_BMP = "bmp";
         String TYPE_JPG = "jpg";
        String TYPE_UNKNOWN = "unknown";
        FileInputStream  fis = null;
        byte[] b = new byte[4];
        try {
            fis = new FileInputStream(new File(path));
            fis.read(b, 0, b.length);
            String type = bytesToHexString(b).toUpperCase();
            if (type.contains("FFD8FF")) {
                return TYPE_JPG;
            } else if (type.contains("89504E47")) {
                return TYPE_PNG;
            } else if (type.contains("47494638")) {
                return TYPE_GIF;
            } else if (type.contains("424D")) {
                return TYPE_BMP;
            }else{
                return TYPE_UNKNOWN;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // come from
    // http://www.partow.net/programming/hashfunctions/index.html#BKDRHashFunction
    private static long hashBKDR(String str) {
        long seed = 131; // 31 131 1313 13131 131313 etc..
        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }

        return hash;
    }

    /**
     * dip 转换成 px
     *
     * @param dip
     * @return
     */
    public static float dip2Dimension(float dip) {
        DisplayMetrics displayMetrics = App.globalContext().getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    /**
     * @param dip
     * @param complexUnit {@link TypedValue#COMPLEX_UNIT_DIP} {@link TypedValue#COMPLEX_UNIT_SP}}
     * @return
     */
    public static float toDimension(float dip, int complexUnit) {
        DisplayMetrics displayMetrics = App.globalContext().getResources().getDisplayMetrics();
        return TypedValue.applyDimension(complexUnit, dip, displayMetrics);
    }

    /**
     * 获取devieID
     *
     */
    public static String getDeviceId() {
        TelephonyManager tm =
                (TelephonyManager) App.globalContext().getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
        return deviceId;

    }

    /**
     * 获取devieID
     *
     */
    public static Display getWindowDisplay() {
        WindowManager windowManager =
                (WindowManager) App.globalContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display;

    }

    /**
     * 时间格式化
     *
     * @param time
     * @return
     */

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 创建文件
     *
     * @return
     */
    public static File createTmpFile() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 已挂载
            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "syberos_" + timeStamp + "";
            File tmpFile = new File(pic, fileName + ".jpg");
            return tmpFile;
        } else {
            File cacheDir = App.globalContext().getCacheDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "syveros_" + timeStamp + "";
            File tmpFile = new File(cacheDir, fileName + ".jpg");
            return tmpFile;
        }

    }


    public static File getCacheDirCreateIfNotExist() {
        File cachePath = new File(getInnerCacheDir());
        if (!cachePath.isDirectory()) {
            try {
                cachePath.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                new File(cachePath, ".nomedia").createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cachePath;
    }

    public static String getInnerCacheDir() {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = App.globalContext().getExternalCacheDir().getPath();
        } else {
            cachePath = App.globalContext().getCacheDir().getPath();
        }
        return cachePath;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = App.globalContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = App.globalContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 文件大小单位转换
     *
     * @param size
     * @return
     */
    public static String setFileSize(long size) {
        DecimalFormat df = new DecimalFormat("###.##");
        float f = ((float) size / (float) (1024 * 1024));

        if (f < 1.0) {
            float f2 = ((float) size / (float) (1024));

            return df.format(new Float(f2).doubleValue()) + "KB";

        } else {
            return df.format(new Float(f).doubleValue()) + "M";
        }

    }

    private final static char[] mChars = "0123456789ABCDEF".toCharArray();

    /**
     * 字符串转换成十六进制字符串
     *
     * @param str String 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; i++) {
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
            sb.append(mChars[bs[i] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 获取视频缩略图
     *
     * @param vedioPath
     * @param wiht
     * @param height
     * @return
     */
    public static Bitmap getVedioBitmap(String vedioPath, int wiht, int height) {
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(vedioPath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        if (bitmap != null) {
            bitmap = Bitmap.createScaledBitmap(bitmap, wiht, height, false);
        }
        return bitmap;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 根据指定的图像路径和大小来获取缩略图
     * 此方法有两点好处：
     *     1. 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度，
     *        第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。
     *     2. 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使
     *        用这个工具生成的图像不会被拉伸。
     * @param imagePath 图像的路径
     * @param width 指定输出图像的宽度
     * @param height 指定输出图像的高度
     * @return 生成的缩略图
     */
    public static Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);


        return bitmap;
    }
    /**
     * 表情转换
     *
     * @param png
     * @param tvFace
     */
    public static void faceTOImage(String png, TextView tvFace) {
        tvFace.setText(handler(tvFace, png));
    }

    private static SpannableStringBuilder handler(final TextView gifTextView,
                                                  String content) {
        SpannableStringBuilder sb = new SpannableStringBuilder(content);
        String regex = "(\\[.*?\\])";
//        String regex = "(\\#\\[face/png/f_static_)\\d{3}(.png\\]\\#)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);

        while (m.find()) {
            String tempText = m.group();
            try {
//                String num = tempText.substring(
//                        "#[face/png/f_static_".length(), tempText.length()
//                                - ".png]#".length());
//                String gif = "face/gif/f" + num + ".gif";
                /**
                 * 如果open这里不抛异常说明存在gif，则显示对应的gif
                 * 否则说明gif找不到，则显示png
                 * */
//                InputStream is = mContext.getAssets().open(gif);
//
//                sb.setSpan(new AnimatedImageSpan(new AnimatedGifDrawable(is,
//                                new AnimatedGifDrawable.UpdateListener() {
//                                    @Override
//                                    public void update() {
//                                        gifTextView.postInvalidate();
//                                    }
//                                })), m.start(), m.end(),
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                is.close();

                String num = tempText.substring(
                        1, 6);
                String png = "face/small/" + num + ".png";
                sb.setSpan(
                        new ImageSpan(App.globalContext(), BitmapFactory
                                .decodeStream(App.globalContext().getAssets()
                                        .open(png))), m.start(), m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (Exception e) {
                String png;
                if (tempText.contains("#[")) {
                    png = tempText.substring("#[".length(),
                            tempText.length() - "]#".length());
                } else {
                    png = tempText;
                }

                try {
                    sb.setSpan(
                            new ImageSpan(App.globalContext(), BitmapFactory
                                    .decodeStream(App.globalContext().getAssets()
                                            .open(png))), m.start(), m.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return sb;
    }

    /**
     * 将时间转成long
     *
     * @param time
     * @return
     */
    public static Date stringToDateToLong(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = sdf.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }

    /**
     * Date
     *
     * @param time
     * @return
     */
    public static Date stringToDateToLongYMD(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }

    /**
     *yyyy年MM月dd HH:mm
     * @param time
     * @return
     */
    public static String StringDateToNian(String time){
       long newTime =  StringDateToLong(time);
        String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm")
                .format(new Date(newTime));
        return date;
    }
    /**
     * 将时间转成long
     *
     * @param time
     * @return
     */
    public static long StringDateToLong(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            Date date = sdf.parse(time);
            long formatTime = date.getTime();
            return formatTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 将时间转成long
     *
     * @param time
     * @return
     */
    public static long StringTimeToLong(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(time);
            long formatTime = date.getTime();
            return formatTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }



    /**
     * 获取文件的大小
     *
     * @param path
     * @return
     */
    public static int getFileSize(String path) {
        File file = new File(path);
        return (int) file.length();
    }

    /**
     * 时间格式化
     *
     * @param
     * @return
     */
    public static String formatDate(Date timeDate) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .format(timeDate);
        return date;
    }

    /**
     * 时间格式化
     *
     * @param
     * @return
     */
    public static String formatDateDay(Date timeDate) {
        String date = new SimpleDateFormat("yyyy-MM-dd")
                .format(timeDate);
        return date;
    }
    /**
     * 时间格式化
     *
     * @param
     * @return
     */
    public static String formatOtherDateDay(Date timeDate) {
        String date = new SimpleDateFormat("yyyy/MM/dd")
                .format(timeDate);
        return date;
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 时间格式化
     *
     * @param
     * @return
     */
    public static String formatDateTttendanceDay(Date timeDate) {
        String date = new SimpleDateFormat("yyyy-M-d")
                .format(timeDate);
        return date;
    }
    /**
     * 时间格式化
     *
     * @param
     * @return
     */
    public static String formatDateYear(long timeDate) {
        String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm")
                .format(new Date(timeDate));
        return date;
    }
    /**
     * 时间格式化
     *
     * @param
     * @return
     */
    public static String formatDateToHour(long timeDate) {
        String date = new SimpleDateFormat("HH:mm")
                .format(new Date(timeDate));
        return date;
    }
    /**
     * 判断sd卡是否满了
     *
     * @return
     */
    public static boolean isSDCardFull() {
        File path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        if (availableBlocks * blockSize == 0) {
            return true;
        }
        return false;
    }
    /**
     * 复制asset文件到指定目录
     * @param oldPath  asset下的路径
     * @param newPath  SD卡下保存路径
     */
    public static void CopyAssets(String oldPath, String newPath) {
        try {
            String fileNames[] = App.globalContext().getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(newPath);
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    CopyAssets(oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = App.globalContext().getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            LogUtils.e("文件error",e.toString());
            e.printStackTrace();
        }
    }


    public static String copyData(String fileName) {
        InputStream in = null;
        FileOutputStream out = null;

        File file1 = App.globalContext().getDir("ca", App.globalContext().MODE_PRIVATE);
        String path = file1 + "/" + fileName; // data/data目录
        File file = new File(path);
        if (!file.exists()) {
            try {
                in = App.globalContext().getAssets().open("ca" + "/" + fileName); // 从assets目录下复制
                out = new FileOutputStream(file);
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {

                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }

        }
        return file1.toString();
    }

    public static String copy() {

        try {
            String packName = App.globalContext().getPackageName();
            String dataPath = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + packName + "/" + "files" + "/" + "ca" + "/";
            String data_dat = dataPath + "clientcert.pem";
            String data_config = dataPath + "clientkey.pem";
            File file = new File(dataPath.trim());
            File dataFile = new File(dataPath.trim());
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!dataFile.exists()) {
                dataFile.mkdirs();
            }
            copyToSD(new File(data_dat.trim()), "ca/clientcert.pem");
            copyToSD(new File(data_config.trim()), "ca/clientkey.pem");

            return dataPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void copyToSD(File filepath, String fileName) {
        try {
            if (!filepath.exists()) {
                filepath.createNewFile();
                InputStream is = null;
                is = App.globalContext().getAssets().open(
                        fileName);
                int data = is.available();
                FileOutputStream fos = new FileOutputStream(filepath);
                byte[] buffer = new byte[data];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getPicturesPath() throws IOException {
        File f =  new File(Environment.getExternalStorageDirectory() + "/SyberosIM/Pictures");
        if (!f.exists()) {
            f.mkdirs();
        }

        return f.getAbsolutePath();
    }
    /**
     *
     * @param srcPath
     * @return
     */
    public static String getSnapImage(String srcPath){
       Bitmap bitmap  = getImageThumbnail(srcPath,120,240);
        if (bitmap == null) {
            return "";
        }
        try {
        File f =  new File(Environment.getExternalStorageDirectory() + "/SyberosIM/snapImage");
            if (!f.exists())
                f.mkdirs();

                File snapPath = new File(f.getAbsolutePath()+"/"+getFileName(srcPath));
                if(!snapPath.exists()){
                    snapPath.createNewFile();

                FileOutputStream out = new FileOutputStream(snapPath);
                Bitmap.CompressFormat type = Bitmap.CompressFormat.PNG;
                if (getExtensionName(srcPath).equals("jpg")) {
                    type = Bitmap.CompressFormat.JPEG;
                }
                bitmap.compress(type, 90, out);
                out.flush();
                out.close();
                    LogUtils.d("getSnapImage",snapPath.getAbsolutePath());
                    return snapPath.getAbsolutePath();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        return "";
    }

    /**
     * 手机是否静音
     * @return
     */
    public static boolean isSilentMode() {
        AudioManager mAudioManager =
                (AudioManager)App.globalContext().getSystemService(Context.AUDIO_SERVICE);
        return mAudioManager.getRingerMode() != AudioManager.RINGER_MODE_NORMAL;
    }
    /**
     * 图片压缩
     *
     * @param srcPath
     * @return
     */
    public static String compressImage(String srcPath) {
        String temp_path = "";
        Bitmap image = getimage(srcPath);
        LogUtils.d("图片的大小", "-----"+getBitmapDegree(srcPath));
        image = rotateBitmapByDegree(image,getBitmapDegree(srcPath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        LogUtils.d("图片的大小", baos.toByteArray().length / 1024 + "-----"+getBitmapDegree(srcPath));
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        try {
            temp_path = saveFile(bitmap, srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp_path;
    }

    /**
     * 图片压缩
     *
     * @param srcPath
     * @return
     */
    public static String compressDutyImage(String srcPath) {
        String temp_path = "";
        Bitmap image = getimage(srcPath);
        image = rotateBitmapByDegree(image,180);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        LogUtils.d("图片的大小", baos.toByteArray().length / 1024 + "-----"+getBitmapDegree(srcPath));
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        try {
            temp_path = saveFile(bitmap, srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp_path;
    }

    /**
     * 图片压缩
     *
     * @param srcPath
     * @return
     */
    public static String compressAlarmImage(String srcPath) {
        String temp_path = "";
        Bitmap image = getimage(srcPath);
//        image = rotateBitmapByDegree(image,180);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        LogUtils.d("图片的大小", baos.toByteArray().length / 1024 + "-----"+getBitmapDegree(srcPath));
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        try {
            temp_path = saveFile(bitmap, srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp_path;
    }
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * 打开一个文件
     *
     * @param filePath 文件的绝对路径
     */
    public static void openFile(Context mcontext, final String filePath)

    {
        String ext = filePath.substring(filePath.lastIndexOf('.')).toLowerCase(Locale.US);
        LogUtils.d("openFile", filePath + "---------" + ext);
        try {
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            String temp = ext.substring(1);
            String mime = mimeTypeMap.getMimeTypeFromExtension(temp);

            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            File file = new File(filePath);
          //  intent.setDataAndType(AppUtil.getUriForFile(mcontext, file), mime);
            mcontext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show("无法打开后缀名为" + ext + "的文件！", ToastUtils.LENGTH_LONG);
        }
    }

    /**
     * 获 取文件时间长度
     *
     * @return
     */

    public static int getRecordLong(String filePath) {
        MediaPlayer mp = null;
        try {
            mp = new MediaPlayer();
            mp.setDataSource(filePath);
            mp.prepare();
            mp.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int duration = mp.getDuration();
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
        return duration;
    }

    public static final String insertImage(final File path, ContentResolver cr, Bitmap source) {
        long createTime = System.currentTimeMillis();
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, path.getName());
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, path.getName());
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, createTime);
        values.put(MediaStore.MediaColumns.DATE_ADDED, createTime);
        values.put(MediaStore.MediaColumns.DATA, path.getAbsolutePath());
        values.put(MediaStore.MediaColumns.SIZE, path.length());
        values.put(MediaStore.Images.ImageColumns.DATE_TAKEN, createTime);
        values.put(MediaStore.Images.ImageColumns.ORIENTATION, 0);
        values.put(MediaStore.Images.ImageColumns.ORIENTATION, 0);
        values.put(MediaStore.MediaColumns.MIME_TYPE, getMimeType(path.getName()));
        LogUtils.v(TAG,"fileSize:" + path.length());
        LogUtils.v(TAG, "path" + path.getAbsolutePath());
        Uri url = null;
        String stringUrl = null;
        try {
            url  = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }

                long id = ContentUris.parseId(url);
                // Wait until MINI_KIND thumbnail is generated.
                Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id,
                        MediaStore.Images.Thumbnails.MINI_KIND, null);
                // This is for backward compatibility.
                Bitmap microThumb = StoreThumbnail(cr, miniThumb, id, 50F, 50F,
                        MediaStore.Images.Thumbnails.MICRO_KIND);
            } else {
                LogUtils.e(TAG, "Failed to create thumbnail, removing original");
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "Failed to insert image");
            e.printStackTrace();
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }

    private static final Bitmap StoreThumbnail(
            ContentResolver cr,
            Bitmap source,
            long id,
            float width, float height,
            int kind) {
        // create the matrix to scale it
        Matrix matrix = new Matrix();

        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();

        matrix.setScale(scaleX, scaleY);

        Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
                source.getWidth(),
                source.getHeight(), matrix,
                true);

        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Images.Thumbnails.KIND,     kind);
        values.put(MediaStore.Images.Thumbnails.IMAGE_ID, (int)id);
        values.put(MediaStore.Images.Thumbnails.HEIGHT,   thumb.getHeight());
        values.put(MediaStore.Images.Thumbnails.WIDTH,    thumb.getWidth());

        Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

        try {
            OutputStream thumbOut = cr.openOutputStream(url);

            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
            thumbOut.close();
            return thumb;
        }
        catch (FileNotFoundException ex) {
            return null;
        }
        catch (IOException ex) {
            return null;
        }
    }

    //  获取文件的 MIME_TYPE
    private static String getMimeType(String file) {
        String lowerPath = file.toLowerCase();
        if (lowerPath.endsWith(".jpg") || lowerPath.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (lowerPath.endsWith(".gif")) {
            return "image/gif";
        }  else if (lowerPath.endsWith(".png")) {
            return "image/png";
        }
        return "image/jpeg";
    }

    public static void fileCopy(String srcFileNmae, String destFileName) {
        try {
            File srcFile = new File(srcFileNmae);
            if (!srcFile.exists()) {
                return;
            }

            File destFile = new File(destFileName);
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            FileOutputStream os = new FileOutputStream(destFile);
            FileInputStream is = new FileInputStream(srcFileNmae);
            int data = is.available();
            byte[] buffer = new byte[data];

            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 保存图片到系统相册
     *
     * @param path
     */
    public static String savePhoto(final File path) {
        if (!path.exists()) {
            return App.globalContext().getString(R.string.app_name);
        }
        String urlPath = null;
        try {
            urlPath = getPicturesPath() + "/" + path.getName();
            fileCopy(path.getAbsolutePath(), urlPath);
            App.globalContext().getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(urlPath))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MediaScannerConnection scannerConnectionOuter = new Object() {
            final MediaScannerConnection scannerConnection
                    = new MediaScannerConnection(App.globalContext(),
                    new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {
                    scannerConnection.scanFile(path.getAbsolutePath(), "image/jpeg");
                }

                @Override
                public void onScanCompleted(String path, Uri uri) {
                    if (uri != null) {

                    }
                    scannerConnection.disconnect();
                }
            });

        }.scannerConnection;

        scannerConnectionOuter.connect();

        return null;
    }

    /**
     * 是否是管理员
     * @param id
     * @return
     */
    public static boolean isManager(String managerId,String id){
        if(managerId.equals(id))
            return true;

            return false;
    }


    /**
     * 保存文件
     *
     * @param bm
     * @param filepath
     * @throws IOException
     */
    public static String saveFile(Bitmap bm, String filepath) throws IOException {
        String path = getSDPath() + "/eim_Imagetemp/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + getFileName(filepath));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));

        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        LogUtils.d("图片压缩地址", myCaptureFile.toString() + "-----");
        return myCaptureFile.toString();
    }

    // 图片按比例大小压缩方法（根据路径获取图片并压缩）
    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 800f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 正则表达式 判断邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断储存空间时候小于某值
     *
     * @param sizeMb
     * @return
     */
    public static boolean isAvaiableSpace(int sizeMb) {

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            String sdcard = Environment.getExternalStorageDirectory().getPath();
//	    	     File file = new File(sdcard);
            StatFs statFs = new StatFs(sdcard);
            long blockSize = statFs.getBlockSize();
            long blocks = statFs.getAvailableBlocks();
            long availableSpare = (blocks * blockSize) / (1024 * 1024);
//	    	     int availableSpare = (int) (statFs.getBlockSize()*((long)statFs.getAvailableBlocks()-4))/(1024*1024);//以比特计算 换算成MB
            System.out.println("availableSpare = " + availableSpare);
            LogUtils.d("isAvaiableSpace", "isAvaiableSpace: " + availableSpare);
            if (sizeMb > availableSpare) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
    /**
     * @param assetpath  asset下的路径
     * @param SDpath     SDpath下保存路径
     */
    public static void AssetToSD(String assetpath,String SDpath){

        AssetManager asset=App.globalContext().getAssets();
        //循环的读取asset下的文件，并且写入到SD卡
        String[] filenames=null;
        FileOutputStream out = null;
        InputStream in=null;
        try {
            filenames = asset.list(assetpath);
            if(filenames.length>0){//说明是目录
                //创建目录
                getDirectory(assetpath);

                for(String fileName:filenames){
                    AssetToSD(assetpath+"/"+fileName, SDpath+"/"+fileName);
                }
            }else{//说明是文件，直接复制到SD卡
                File SDFlie=new File(SDpath);

                if(!SDFlie.exists()){
                    if(!SDFlie.getParentFile().exists()){
                        SDFlie.getParentFile().mkdirs();//创建
                    }
                    SDFlie.createNewFile();
                    //将内容写入到文件中
                    in=asset.open(assetpath);
                    out= new FileOutputStream(SDFlie);
                    byte[] buffer = new byte[1024];
                    int byteCount=0;
                    while((byteCount=in.read(buffer))!=-1){
                        out.write(buffer, 0, byteCount);
                    }
                    out.flush();
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(out!=null){
                    out.close();
                    out=null;
                }
                if(in!=null){
                    in.close();
                    in=null;
                }
                /**
                 * 关闭报错，java.lang.RuntimeException:
                 * Unable to start activity_accident_query ComponentInfo
                 * {com.example.wealth/com.example.wealth.UI.main}:
                 * java.lang.RuntimeException: Assetmanager has been closed
                 */
//              if(asset!=null){
//                  asset.close();
//                  asset=null;
//                  }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //分级建立文件夹
    public static void getDirectory(String path){
        //对SDpath进行处理，分层级建立文件夹
        String[]  s=path.split("/");
        String str=Environment.getExternalStorageDirectory().toString();
        for (int i = 0; i < s.length; i++) {
            str=str+"/"+s[i];
            File file=new File(str);
            if(!file.exists()){
                file.mkdir();
            }
        }

    }

    /**
     * 检测某ActivityUpdate是否在当前Task的栈顶
     */
    public static boolean isTopActivy(String cmdName) {
        ActivityManager manager =
                (ActivityManager) App.globalContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;

        if (null != runningTaskInfos) {
            cmpNameTemp = ((runningTaskInfos.get(0).topActivity).toString());
            LogUtils.d("当前运行的activity", cmpNameTemp);
        }

        if (null == cmpNameTemp) return false;
        return cmpNameTemp.equals(cmdName);
    }

    public static boolean isAppRunInBackground() {
        ActivityManager am
                = (ActivityManager) App.globalContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(App.globalContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 用来判断服务是否在运行.
     *
     * @param className 判断的服务名字
     *                  [url=home.php?mod=space&uid=309376]@return[/url] isRunning ：true 在运行 、false 不在运行
     */
    public static boolean isServiceRunning(String className) {
        //默认标记：为false
        boolean isRunning = false;
        ActivityManager activityManager
                = (ActivityManager) App.globalContext().getSystemService(Context.ACTIVITY_SERVICE);
        //获取正在运行的服务
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(30);
        //如果没有，那么返回false
        if (!(serviceList.size() > 0)) {
            return false;
        }
        //如果有，那么迭代List，判断是否有当前某个服务
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }


    /**
     * 获取App具体设置
     *
     * @param context 上下文
     */
    public static void getAppDetailsSettings(Context context, int requestCode) {
        getAppDetailsSettings(context, context.getPackageName(), requestCode);
    }

    /**
     * 获取App具体设置
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void getAppDetailsSettings(Context context, String packageName, int requestCode) {
        if (TextUtils.isEmpty(packageName)) return;
        ((AppCompatActivity) context).startActivityForResult(
                getAppDetailsSettingsIntent(packageName), requestCode);
    }

    /**
     * 获取App具体设置的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 通过任务管理器杀死进程
     * 需添加权限 {@code <uses-permission android:name="android.permission.RESTART_PACKAGES"/>}</p>
     *
     * @param context
     */
    public static void restart(Context context) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startMain);
            System.exit(0);
        } else {// android2.1
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            am.restartPackage(context.getPackageName());
        }
    }
    /**
     * 判断字符串是否为空
     *
     * @param string 字符串
     * @return boolean值
     */
    public static boolean isNull(String string) {
        if (string != null && !"null".equalsIgnoreCase(string) && !"".equals(string)) {
            return false;
        }
        return true;
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source 原数据
     * @return true 有表情符 false 没有表情符
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }
    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return true 表情符 false 非表情符
     */
    static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    /**
     * md5加密
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.trim().toUpperCase();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "";
    }

    public final static String  encrypt(String plaintext) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    public static String getFormatHMS(long time){
        time=time/1000;//总秒数
        int s= (int) (time%60);//秒
        int m= (int) (time/60);//分
        int h=(int) (time/3600);//秒
        return String.format("%02d:%02d:%02d",h,m,s);
    }

}
