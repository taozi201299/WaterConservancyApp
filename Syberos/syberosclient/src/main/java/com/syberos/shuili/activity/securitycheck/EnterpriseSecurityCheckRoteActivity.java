package com.syberos.shuili.activity.securitycheck;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuili.callback.ErrorInfo;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.common.CheckRoteItem;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/11/7.
 */

public class EnterpriseSecurityCheckRoteActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private final static String TAG = EnterpriseSecurityCheckRoteActivity.class.getSimpleName();
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.ll_base_info)
    LinearLayout llBaseInfo;
    private WebView webView;
    private String mLan = "39.895030431052";
    private String mLon = "116.32152879234";
    private boolean hasShowMap = false;
    CheckRoteItem.ResultInfoListBean resultInfoListBean = null;
    private List<TracingPoint> lineTracingPoints = new ArrayList<>();
    private final int RC_PERM = 110;
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
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        resultInfoListBean = (CheckRoteItem.ResultInfoListBean) bundle.getSerializable("data");
        if (resultInfoListBean == null) {
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }

        tvStartTime.setText("开始时间 " +  resultInfoListBean.getStatime());
        tvEndTime.setText("结束时间" + resultInfoListBean.getEndtime());
        String geo = resultInfoListBean.getGeo();
        String points = geo.split("coordinates")[1].split(":")[1].replace("[", "").replace("]", "");

        points = points.replace("}","");
        String[] array = points.split(",");
        int len = array.length;
        for (int i = 0; i < len-1;i = i+2) {
            TracingPoint point = new TracingPoint();
            point.latitude = array[i + 1];
            point.longitude = array[i];
            if(i == 0) {
                mLan = array[i + 1];
                mLon = array[i];
            }
            lineTracingPoints.add(point);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestMulti();
        } else {
            webMap();
        }

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        showDataLoadingDialog();
        showTitle("检查轨迹");
        llBaseInfo.setVisibility(View.VISIBLE);
        setActionBarRightVisible(View.INVISIBLE);


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
        webView.loadUrl("file:///android_asset/mobile_security_check.html");
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Location location = new Location("");
                location.setLatitude(Double.valueOf(mLan));
                location.setLongitude(Double.valueOf(mLon));
                updateView(location);
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
    }

    /**
     * 请求多个权限
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
        if (requestCode == RC_PERM) {
            webMap();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_PERM) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(EnterpriseSecurityCheckRoteActivity.this, "map test", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String getLan() {
            return mLan;
        }

        @JavascriptInterface
        public String getLon() {
            return mLon;
        }
    }

    private void updateView(Location location) {
        if (location != null) {
            mLon = String.valueOf(location.getLongitude());
            mLan = String.valueOf(location.getLatitude());

            Log.d(TAG, "=====local=====>" + mLon + ", " + mLan);

            if (!hasShowMap) {
                webView.loadUrl("javascript:showMap(" + mLon + ',' + mLan + ")");
                hasShowMap = true;
                closeDataDialog();
            }
            updateCurrentPoint(mLon, mLan);
            updateLineAddPoint(mLon, mLan);

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

    private class TracingPoint {
        public String longitude; // 精度
        public String latitude; // 纬度
    }

}
