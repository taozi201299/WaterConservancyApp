package com.syberos.shuili.amap;

/**
 * Created by Administrator on 2018/3/29.
 */


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.syberos.shuili.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jidan on 18-3-28.
 */

@SuppressLint("MissingPermission")
public class MapActivity extends Activity {
    private final String TAG = MapActivity.class.getSimpleName();
    private final static long duration = 5 * 60 * 1000;
    private final Criteria criteria = new Criteria();
    private final static long minUpdateTime = duration;
    private final static float minUpdateDistance = 0;

    private ArrayList<Person> list = new ArrayList<>();
    private WebView webView;
    private String mLan = "";
    private String mLon = "";
    private LocationManager locationManager;
    private boolean hasShowMap = false;
    private List<TracingPoint> lineTracingPoints;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_layout);

        // 获取系统LocationManager服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        lineTracingPoints = new ArrayList<>();

        // 精度
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 设置不需要获取海拔方向数据
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        // 设置允许产生资费
        criteria.setCostAllowed(true);
        // 要求低耗电
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        webmap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerListener();
    }

    @Override
    protected void onPause() {
        unregisterAllListeners();
        super.onPause();
    }

    public void webmap() {//地图定位

        webView = (WebView) findViewById(R.id.webview);

//        BasicHandle handle = new BasicHandle();
//        aQuery.auth(handle);

        webView.getSettings().setDatabaseEnabled(true);//开启数据库
        webView.setFocusable(true);//获取焦点
        webView.requestFocus();

        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();//设置数据库路径
        webView.getSettings().setCacheMode(webView.getSettings().LOAD_CACHE_ELSE_NETWORK);//本地缓存
        webView.getSettings().setBlockNetworkImage(false);//显示网络图像
        webView.getSettings().setLoadsImagesAutomatically(true);//显示网络图像
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);//插件支持
        webView.getSettings().setSupportZoom(false);//设置是否支持变焦
        webView.getSettings().setJavaScriptEnabled(true);//支持JavaScriptEnabled
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持JavaScriptEnabled
        webView.getSettings().setGeolocationEnabled(true);//定位
        webView.getSettings().setGeolocationDatabasePath(dir);//数据库
        webView.getSettings().setDomStorageEnabled(true);//缓存 （ 远程web数据的本地化存储）
        webView.loadUrl("file:///android_asset/index.html");
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("111111111111", "222222222");
                getLocation();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            //重写WebChromeClient的onGeolocationPermissionsShowPrompt
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                Log.d(TAG, message);
                AlertDialog.Builder b = new AlertDialog.Builder(MapActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });
    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(MapActivity.this, "map test", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public Person getPersonObject(int index) {
            return list.get(index);
        }

        @JavascriptInterface
        public int getSize() {
            return list.size();
        }

        @JavascriptInterface
        public String getLan() {
            return mLan;
        }

        @JavascriptInterface
        public String getLon() {
            return mLon;
        }

        @JavascriptInterface
        public void queryNearGEOInfo(String jsonString) {
            Log.d(TAG, "1111111111111" + jsonString);
            @SuppressLint("StaticFieldLeak")
            GetJsonDataTask getJsonDataTask = new GetJsonDataTask() {
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        Log.d(TAG, "=========" + jsonObject.getString("message"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                    webView.loadUrl("javascript:showNearInfo(" + s + ")");
                }
            };
            getJsonDataTask.execute(jsonString);
        }
    }

    void getLocation() {
        isOpenGPS();

        // 从GPS获取最近的定位信息
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateView(location);

        registerListener();

    }

    private void updateView(Location location) {
        if (location != null) {
            Log.d("1111", String.valueOf(location.getLatitude()));
            Log.d("111", String.valueOf(location.getLongitude()));
            mLan = String.valueOf(location.getLatitude());
            mLon = String.valueOf(location.getLongitude());

            if (!hasShowMap) {
                webView.loadUrl("javascript:showMap(" + mLon + ',' + mLan + ")");
                hasShowMap = true;
            }

            updateNearInfo(mLon, mLan, String.valueOf(50));

//            updateLineAddPoint(mLon, mLan);
//            updatePoint(mLon, mLan);

//            String lines = load("/mnt/sdcard/lineGeoJson.json");
//

//            Log.d(TAG, "===========================================================\n");
//            Log.d(TAG, "=====" + lines.length());
//
//            webView.loadUrl("javascript:showLines(" + lines + ")");
        } else {
            // TODO: 2018/5/4 显示加载中
        }
    }

    private void updateNearInfo(final String lon, final String lan, final String radius) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"coordinates\":[");
        sb.append(lon);
        sb.append(",");
        sb.append(lan);
        sb.append("],\"type\":\"Point\"},\"properties\":{\"HYDC\":\"FB010001\",\"Name\":\"长江\",\"OBJ_CODE\":\"FB010001\",\"OBJ_NAME\":\"长江\",\"UserID\":0},\"type\":\"Feature\"}]}");

        Log.d(TAG, "\n=============================updateNearInfo==============================\n");

        webView.loadUrl("javascript:getNearGEOInfo(" + sb.toString() + ", " + radius + ")");
    }

    private void updatePoint(final String lon, final String lan) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"coordinates\":[");
        sb.append(lon);
        sb.append(",");
        sb.append(lan);
        sb.append("],\"type\":\"Point\"},\"properties\":{\"HYDC\":\"FB010001\",\"Name\":\"长江\",\"OBJ_CODE\":\"FB010001\",\"OBJ_NAME\":\"长江\",\"UserID\":0},\"type\":\"Feature\"}]}");

        Log.d(TAG, "\n==============================updatePoint=============================\n");

        webView.loadUrl("javascript:updateCurrentPoint(" + sb.toString() + ")");
    }

    private void updateLineAddPoint(final String lon, final String lan) {
        TracingPoint tracingPoint = new TracingPoint();
        tracingPoint.longitude = lon;
        tracingPoint.latitude = lan;
        lineTracingPoints.add(tracingPoint);

        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"FeatureCollection\",\"features\":[{\"geometry\":{\"coordinates\":[");

        for (int i = 0; i < lineTracingPoints.size(); ++i) {
            TracingPoint point = lineTracingPoints.get(i);
            if (i > 0) {
                sb.append(",");
            }
            sb.append("[");
            sb.append(point.longitude);
            sb.append(",");
            sb.append(point.latitude);
            sb.append("]");
        }

        sb.append("],\"type\":\"LineString\"},\"properties\":{\"HYDC\":\"FB010001\",\"Name\":\"长江\",\"OBJ_CODE\":\"FB010001\",\"OBJ_NAME\":\"长江\",\"UserID\":0},\"type\":\"Feature\"}]}");

        Log.d(TAG, "\n===============================updateLineAddPoint============================\n");

        webView.loadUrl("javascript:updateCurrentLine(" + sb.toString() + ")");
    }

    public class Person {
        private String name;
        private String age;
        private String sex;

        @JavascriptInterface
        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @JavascriptInterface
        public String getSex() {
            return sex;
        }

        @JavascriptInterface
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    private String load(final String fileName) {
        try {
            return getStringFromFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    private void isOpenGPS() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
            dialog.setMessage("GPS未打开，是否打开?");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    // 设置完成后返回到原来的界面
                    MapActivity.this.startActivityForResult(intent, 0);
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    private void registerListener() {
        unregisterAllListeners();
        String bestProvider =
                locationManager.getBestProvider(criteria, false);
        String bestAvailableProvider =
                locationManager.getBestProvider(criteria, true);

        Log.d(TAG, bestProvider + " / " + bestAvailableProvider);

        if (bestProvider == null)
            Log.d(TAG, "No Location Providers exist on device.");
        else if (bestProvider.equals(bestAvailableProvider))
            locationManager.requestLocationUpdates(bestAvailableProvider,
                    minUpdateTime, minUpdateDistance,
                    bestAvailableProviderListener);
        else {
            locationManager.requestLocationUpdates(bestProvider,
                    minUpdateTime, minUpdateDistance, bestProviderListener);

            if (bestAvailableProvider != null)
                locationManager.requestLocationUpdates(bestAvailableProvider,
                        minUpdateTime, minUpdateDistance,
                        bestAvailableProviderListener);
            else {
                List<String> allProviders = locationManager.getAllProviders();
                for (String provider : allProviders)
                    locationManager.requestLocationUpdates(provider, 0, 0,
                            bestProviderListener);
                Log.d(TAG, "No Location Providers currently available.");
            }
        }
    }

    private void unregisterAllListeners() {
        locationManager.removeUpdates(bestProviderListener);
        locationManager.removeUpdates(bestAvailableProviderListener);
    }

    private void reactToLocationChange(Location location) {
        updateView(location);
    }

    private LocationListener bestProviderListener
            = new LocationListener() {
        public void onLocationChanged(Location location) {
            reactToLocationChange(location);
        }

        public void onProviderDisabled(String provider) {
            reactToLocationChange(locationManager.getLastKnownLocation(provider));
        }

        public void onProviderEnabled(String provider) {
            reactToLocationChange(locationManager.getLastKnownLocation(provider));
            registerListener();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private LocationListener bestAvailableProviderListener =
            new LocationListener() {
                public void onProviderEnabled(String provider) {
                    reactToLocationChange(locationManager.getLastKnownLocation(provider));
                    registerListener();
                }

                public void onProviderDisabled(String provider) {
                    reactToLocationChange(locationManager.getLastKnownLocation(provider));
                }

                public void onLocationChanged(Location location) {
                    reactToLocationChange(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {

                }
            };


    private class TracingPoint {
        public String longitude; // 精度
        public String latitude; // 纬度
    }


    private class RequestNearInfoThread extends Thread {
        @Override
        public void run() {
            String path = "http://192.168.1.11:8091/WEGIS-00-WEB_SERVICE/WSWebService";

            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                StringBuilder params = new StringBuilder();
                params.append("{\"requestData\":{\"typeList\":[\"CWS\"],\"radius\":10,\"targetId\":\"search.GeoSearchLogic\",\"geo\":\"%7b%22type%22%3a%22Point%22%2c%22coordinates%22%3a%5b108.9829419%2c22.796416%5d%7d\"}}");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(params.toString());
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

                int statusCode = conn.getResponseCode();
                Log.d(TAG, "==========" + statusCode);
                if (statusCode == 200) {
                    // 获取响应的输入流对象
                    InputStream is = conn.getInputStream();
                    // 创建字节输出流对象
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    // 定义读取的长度
                    int len = 0;
                    // 定义缓冲区
                    byte buffer[] = new byte[1024];
                    // 按照缓冲区的大小，循环读取

                    StringBuilder builder = new StringBuilder();

                    while ((len = is.read(buffer)) != -1) {
                        builder.append(new String(buffer, 0, len, "UTF-8"));
                    }

                    // 释放资源
                    is.close();
                    baos.close();
                    // 返回字符串
                    Log.d(TAG, "==========1111" + builder.toString());
                    final JSONObject result;
                    try {
                        result = new JSONObject(builder.toString());
                        Log.d(TAG, result.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            super.run();
        }
    }
}
