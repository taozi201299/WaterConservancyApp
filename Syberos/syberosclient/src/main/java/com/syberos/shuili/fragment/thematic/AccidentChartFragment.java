package com.syberos.shuili.fragment.thematic;

import android.Manifest;
import android.os.Build;
import android.support.annotation.NonNull;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.map.MapBoundBean;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/6/26.
 */

public class AccidentChartFragment extends BaseLazyFragment implements EasyPermissions.PermissionCallbacks{
    @BindView(R.id.webview)
    WebView webView;
    private String mLon = "";
    private String mLat = "";
    private boolean bLoadFinish = false;
    private boolean bShowMap = false;
    private int iMapLevel = 0;
    private final static long duration = 10 * 1000;

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
        webMap();

    }

    @Override
    protected void initListener() {

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
        webView.loadUrl("file:///android_asset/chart/hidd.html");
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bLoadFinish = true;
                if(!bShowMap && !mLon.isEmpty() && !mLat.isEmpty()){
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
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

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
        iMapLevel = 4;
        mLon = array[0];
        mLat = array[1];
        if(bLoadFinish) {
            bShowMap = true;
            webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
            addMarkInfo();
        }
    }
    private void addMarkInfo(){
        webView.loadUrl("javascript:updateCurrentPoint(" + mLon + ',' + mLat +")");
    }
}
