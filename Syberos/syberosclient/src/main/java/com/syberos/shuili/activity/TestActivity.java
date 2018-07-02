package com.syberos.shuili.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.amap.AMapToWGS;
import com.syberos.shuili.amap.SecurityCheckMapTrailsActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.service.SyberosAidlClient;

import junit.framework.Test;

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

import static com.lzy.okhttputils.callback.FileCallback.DM_TARGET_FOLDER;


/**
 * Created by Administrator on 2018/6/28.
 */
@SuppressLint("MissingPermission")
public class TestActivity extends Activity  {

    private WebView webView;

    // ========== gao de api start ============

    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationClientOption mLocationOption = null;
    private final int RC_PERM = 110;
    public static final String[] requestPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
    };

    // ========== gao de api end ============

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_layout);
        ButterKnife.bind(this);
        SyberosManagerImpl.init(this);
        SyberosAidlClient.init(this);

            webMap();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
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
        webView.loadUrl("file:///android_asset/cache.html");
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:showMap()");

            }
        });
    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(TestActivity.this, "map test", Toast.LENGTH_SHORT).show();
        }


        @JavascriptInterface
        public String getLocalUrl(String serviceID, String url) {
            Log.d("map map map ", serviceID);
           String result = SyberosManagerImpl.getInstance().getMapUrl(url, serviceID);
            Log.d("map map map ", result);
            if(result == null || result.isEmpty())
            return "data:image/png;base64,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";
            return Environment.getExternalStorageDirectory() + DM_TARGET_FOLDER + result;
        }
    }

}
