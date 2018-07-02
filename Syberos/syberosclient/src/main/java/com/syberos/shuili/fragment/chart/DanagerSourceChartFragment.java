package com.syberos.shuili.fragment.chart;

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

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/6/26.
 */

public class DanagerSourceChartFragment extends BaseLazyFragment implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.webview)
    WebView webView;
    private  final int RC_PERM = 110;
    public static final String[] requestPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
    };
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_acci_chart_layout;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestMulti();
        }else{
            webMap();
        }

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
        webView.loadUrl("file:///android_asset/cache.html");
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:showMap()");

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
    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(mContext, "map test", Toast.LENGTH_SHORT).show();
        }

    }
}
