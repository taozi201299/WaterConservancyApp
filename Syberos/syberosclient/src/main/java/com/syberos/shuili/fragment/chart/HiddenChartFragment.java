package com.syberos.shuili.fragment.chart;

import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.amap.AMapToWGS;
import com.syberos.shuili.amap.SecurityCheckMapTrailsActivity;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.map.CityInfoBean;
import com.syberos.shuili.entity.map.MapBoundBean;
import com.syberos.shuili.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/6/26.
 */

public class HiddenChartFragment extends BaseLazyFragment implements View.OnClickListener {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.btn_zhiguan)
    Button btn_zhiguan;
    @BindView(R.id.btn_liuyu)
    Button btn_liuyu;
    @BindView(R.id.btn_jianguan)
    Button btn_jianguan;
    private String mLon = "";
    private String mLat = "";
    private boolean bLoadFinish = false;
    private boolean bShowMap = false;
    private int iMapLevel = 0;
    private final static long duration = 10 * 1000;
    private int type = 1;// 1 获取直管工程数据 2 获取流域数据 3 获取监管工程数据

    private HashMap<String, String> levels = new HashMap<String, String>() {
        {
            put("北京市", "5");
            put("上海市", "5");
            put("天津市", "5");
            put("重庆市", "5");
        }
    };

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_acci_chart_layout;
    }

    @Override
    protected void initView() {
        showDataLoadingDialog();
        // 行政区划
        if("1".equals(App.jurdAreaType)){
            type = 1;
        }else if("3".equals(App.jurdAreaType) || "4".equals(App.jurdAreaType)){
            type = 2;
        }
        webMap();

    }

    @Override
    protected void initListener() {
        btn_liuyu.setOnClickListener(this);
        btn_jianguan.setOnClickListener(this);
        btn_zhiguan.setOnClickListener(this);

    }

    @Override
    protected void initData() {


    }


    public void webMap() {//地图定位
        webView.getSettings().setDatabaseEnabled(true);//开启数据库
        webView.setFocusable(true);//获取焦点
        webView.requestFocus();
        webView.getSettings().setBlockNetworkImage(false);//显示网络图像
        webView.getSettings().setLoadsImagesAutomatically(true);//显示网络图像
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);//插件支持
        webView.getSettings().setSupportZoom(false);//设置是否支持变焦
        webView.getSettings().setJavaScriptEnabled(true);//支持JavaScriptEnabled
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持JavaScriptEnabled
        webView.getSettings().setDomStorageEnabled(true);//缓存 （ 远程web数据的本地化存储）
        if(type == 1 || type == 3) {
            iMapLevel = 4;
            webView.loadUrl("file:///android_asset/chart/hidd.html");
        }else if(type == 2){
            iMapLevel = 0;
            webView.loadUrl("file:///android_asset/chart/hidd_liuyu.html");
        }
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bLoadFinish = true;
                if(!bShowMap && !mLon.isEmpty() && !mLat.isEmpty()){
                    closeDataDialog();
                    webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
                }
                addMarkInfo();

            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            //重写WebChromeClient的onGeolocationPermissionsShowPrompt
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
    }

    @Override
    public void onClick(View v) {
        bLoadFinish = false;
        bShowMap = false;
        switch (v.getId()){
            case R.id.btn_zhiguan:
                iMapLevel = 4;
                type = 1;
                webView.removeAllViews();
                webView.loadUrl("file:///android_asset/chart/hidd.html");
                break;
            case R.id.btn_liuyu:
                type = 2;
                iMapLevel = 0;
                webView.removeAllViews();
                webView.loadUrl("file:///android_asset/chart/hidd_liuyu.html");
                break;
            case R.id.btn_jianguan:
                iMapLevel = 4;
                type = 3;
                webView.removeAllViews();
                webView.loadUrl("file:///android_asset/chart/hidd.html");
                break;

        }

    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }
        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(mContext, "map test", Toast.LENGTH_SHORT).show();
        }

    }
    public void setMapData(MapBoundBean mapData){
        String center = mapData.centerXY;
        String[]array = center.split(",");
        if(type == 1 || type == 4) {
            iMapLevel = 4;
        }else {
            iMapLevel = 0;
        }
        mLon = array[0];
        mLat = array[1];
        if(bLoadFinish) {
            closeDataDialog();
            bShowMap = true;
            webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
            addMarkInfo();
        }
    }
    private void addMarkInfo(){
        webView.loadUrl("javascript:updateCurrentPoint(" + mLon + ',' + mLat +")");
    }
}
