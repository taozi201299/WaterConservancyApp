package com.syberos.shuili.amap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.searchproject.ProjectDetailsActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.map.NearbyEngInfoBean;
import com.syberos.shuili.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

@SuppressLint("MissingPermission")
public class ShowNearlyInfoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    private final static String TAG = ShowNearlyInfoActivity.class.getSimpleName();

    private final static boolean USE_GAO_DE_SDK_API = true;

    private final static long duration = 10 * 1000;
    private final Criteria criteria = new Criteria();
    private final static long minUpdateTime = duration;
    private final static float minUpdateDistance = 0;

    private ArrayList<SecurityCheckMapTrailsActivity.Person> list = new ArrayList<>();
    private WebView webView;
    private String mLan = "";
    private String mLon = "";
    private LocationManager locationManager;
    private boolean hasShowMap = false;
    private List<TracingPoint> lineTracingPoints;

    // ========== gao de api start ============

    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationClientOption mLocationOption = null;
    /**
     * 工程信息
     */
    NearbyEngInfoBean nearbyEngInfoBean = null;

    // ========== gao de api end ============

    @BindView(R.id.center_on_current_point)
    LinearLayout center_on_current_point;

    @OnClick(R.id.center_on_current_point)
    void onCenterOnCurrentPointClicked() {
        if (null != webView) {
            webView.loadUrl("javascript:centerOnCurrentPoint()");
        }
    }

    @BindView(R.id.change_show_range)
    LinearLayout change_show_range;

    @OnClick(R.id.change_show_range)
    void onChangeShowRangeClicked() {
        typePicker.show();
    }

    private List<String> types;
    private OptionsPickerView typePicker = null;
    private int currentShowRange = 10;

    private  final int RC_PERM = 110;
    public static final String[] requestPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_nearly_info;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if (!USE_GAO_DE_SDK_API) {
            registerListener();
        }
    }

    @Override
    public void initView() {
        showTitle("查附近");
        setActionBarRightVisible(View.INVISIBLE);
        typePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                switch (options1) {
                    case 0:
                        currentShowRange = 10;
                        break;
                    case 1:
                        currentShowRange = 15;
                        break;
                    case 2:
                        currentShowRange = 20;
                        break;
                    case 3:
                        currentShowRange = 30;
                        break;
                    default:
                        currentShowRange = 10;
                        break;
                }
                updateNearInfo(mLon, mLan, String.valueOf(currentShowRange));
            }
        })
                .setTitleText("请选择搜索半径")
                .isDialog(true)
                .setOutSideCancelable(false)
                .setSelectOptions(1)
                .build();

        types = new ArrayList<>();
        types.add("10公里");
        types.add("15公里");
        types.add("20公里");
        types.add("30公里");

        typePicker.setPicker(types);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestMulti();
        }else{
            webMap();
        }

    }

    @Override
    protected void onPause() {
        if (!USE_GAO_DE_SDK_API) {
            unregisterAllListeners();
        }
        super.onPause();
    }

    public void webMap() {//地图定位

        webView = (WebView) findViewById(R.id.webview);

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
        webView.loadUrl("file:///android_asset/mobile_show_nearly_info.html");
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("USE_GAO_DE_SDK_API", ":" + USE_GAO_DE_SDK_API);

                if (USE_GAO_DE_SDK_API) {
                    useGaoDeApiGetInfo();
                } else {
                    useLocationManagerGetInfo();
                }

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
                AlertDialog.Builder b = new AlertDialog.Builder(ShowNearlyInfoActivity.this);
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
    /**
     * 请求多个权限
     *
     */
    public void requestMulti() {
        EasyPermissions.requestPermissions(this, "需要申请功能",
                RC_PERM, requestPermissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(requestCode == RC_PERM){
            webMap();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if(requestCode == RC_PERM){
        }
    }
    private GetJsonDataTask getJsonDataTask = null;

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(ShowNearlyInfoActivity.this, "map test", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public SecurityCheckMapTrailsActivity.Person getPersonObject(int index) {
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

        @SuppressLint("StaticFieldLeak")
        @JavascriptInterface
        public void queryNearGEOInfo(String jsonString) {
            if (null != getJsonDataTask) {
                getJsonDataTask.cancel(true);
            }
            getJsonDataTask = new GetJsonDataTask() {
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if (null != s && !s.isEmpty()) {
                        webView.loadUrl("javascript:showNearInfo(" + s + ")");
                        Gson gson = new Gson();
                        nearbyEngInfoBean = gson.fromJson(s,NearbyEngInfoBean.class);
                    }else if(s == null){
                        ToastUtils.show("网络错误，获取工程数据失败");
                    }else if(s.isEmpty()){
                        ToastUtils.show("未找到工程");
                    }
                }
            };
            getJsonDataTask.execute(jsonString);
        }
        @JavascriptInterface
        public void go2ProjectInfoActivity(String guid){
            Bundle bundle = new Bundle();
            bundle.putString("guid",guid);
            intentActivity(ShowNearlyInfoActivity.this, ProjectDetailsActivity.class,false,bundle);


        }
    }

    private void useGaoDeApiGetInfo() {
        lineTracingPoints = new ArrayList<>();
        initGaoDeApi();
    }

    private void useLocationManagerGetInfo() {

        // 获取系统LocationManager服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        checkIsOpenGPS();

        lineTracingPoints = new ArrayList<>();

        // 精度
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 设置不需要获取海拔方向数据
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        // 设置允许产生资费
        criteria.setCostAllowed(true);
        // 要求低耗电
        criteria.setPowerRequirement(Criteria.POWER_HIGH);

        // 从GPS获取最近的定位信息
        Location location;
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateView(location);

        registerListener();

    }

    private void updateView(Location location) {
        if (location != null) {
            mLon = String.valueOf(location.getLongitude());
            mLan = String.valueOf(location.getLatitude());

            Log.d(TAG, "=====local=====>" + mLon + ", " + mLan);

            if (!hasShowMap) {
                webView.loadUrl("javascript:showMap(" + mLon + ',' + mLan + ")");
//                webView.loadUrl("javascript:updateStartPoint(" + mLon + ',' + mLan + ")");
                hasShowMap = true;
            }

            updateLineAddPoint(mLon, mLan);
            updateCurrentPoint(mLon, mLan);

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

    private void updateCurrentPoint(final String lon, final String lan) {
        Log.d(TAG, "\n==============================updateCurrentPoint=============================\n");

        webView.loadUrl("javascript:updateCurrentPoint(" + lon + "," + lan + ")");
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

    private void checkIsOpenGPS() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
            dialog.setMessage("GPS未打开，是否打开?");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    // 设置完成后返回到原来的界面
                    ShowNearlyInfoActivity.this.startActivityForResult(intent, 0);
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
        if (null != locationManager) {
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
    }

    private void unregisterAllListeners() {
        if (null != locationManager) {
            locationManager.removeUpdates(bestProviderListener);
            locationManager.removeUpdates(bestAvailableProviderListener);
        }
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

    private void initGaoDeApi() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(gaoDeApiLocationListener);
        //声明mLocationOption对象
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，AMapLocationMode.Battery_Saving为低功耗模式，AMapLocationMode.Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(false);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        mLocationOption.setInterval(duration);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private long lastTime = 0;

    public static String doubleToString(double num) {
        DecimalFormat df = new DecimalFormat(".00000000");
        return df.format(num);
    }

    public AMapLocationListener gaoDeApiLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            // TODO Auto-generated method stub

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表

                    AMapToWGS.LatLonPoint latLonPoint = AMapToWGS.toWGS84Point(
                            amapLocation.getLatitude(), amapLocation.getLongitude());

                    mLon = String.valueOf(latLonPoint.getLongitude());
                    mLan = String.valueOf(latLonPoint.getLatitude());

                    Log.d(TAG, "=======gaode========>" + mLon + "," + mLan);

                    if (!hasShowMap) {
                        webView.loadUrl("javascript:showMap(" + mLon + ',' + mLan + ")");
//                webView.loadUrl("javascript:updateStartPoint(" + mLon + ',' + mLan + ")");
                        mLocationClient.stopLocation();
                        center_on_current_point.setVisibility(View.VISIBLE);
                        change_show_range.setVisibility(View.VISIBLE);
                        updateNearInfo(mLon, mLan, String.valueOf(currentShowRange));
                        hasShowMap = true;
                    }

                    updateCurrentPoint(mLon, mLan);

//                    amapLocation.getAccuracy();//获取精度信息
//                    @SuppressLint("SimpleDateFormat")
//                    java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(amapLocation.getTime());
//                    df.format(date);//定位时间
//                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    amapLocation.getCountry();//国家信息
//                    amapLocation.getProvince();//省信息
//                    amapLocation.getCity();//城市信息
//                    amapLocation.getDistrict();//城区信息
//                    amapLocation.getStreet();//街道信息
//                    amapLocation.getStreetNum();//街道门牌号信息
//                    amapLocation.getCityCode();//城市编码
//                    amapLocation.getAdCode();//地区编码

//                    mLocationClient.stopLocation();//停止定位
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("info", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

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

}

