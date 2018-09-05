package com.syberos.shuili.fragment.thematic;

import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.entity.map.MapBoundBean;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;
import com.syberos.shuili.network.retrofit.RetrofitHttpMethods;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/6/26.
 * 水利稽查专题图
 */

public class WinsChartFragment extends BaseLazyFragment implements  EasyPermissions.PermissionCallbacks {
    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.radio_btn_jianguan)
    RadioButton rbtnJianguan;

    @BindView(R.id.radio_btn_zhiguan)
    RadioButton rbtnZhiguan;

    @BindView(R.id.radio_btn_liuyu)
    RadioButton rbtnLiuyu;

    @BindView(R.id.radio_group_type)
    RadioGroup radio_group_type;
    @BindView(R.id.radio_type_liuyu)
    RadioButton rbTypeLiuyu;
    @BindView(R.id.radio_type_jianguan)
    RadioButton rbTypeJianguan;
    private String TAG = getClass().getSimpleName();
    private String mLon = "";
    private String mLat = "";
    private boolean bLoadFinish = false;
    private boolean bShowMap = false;
    private int iMapLevel = 0;
    private final static long duration = 10 * 1000;
    private int type = 1;// 1 获取直管工程数据 2 获取流域数据 3 获取监管工程数据
    private String subType = "1"; // 1 流域 2 监管

    private int orgLevel = BusinessConfig.getOrgLevel();
    private int orgType; // 1 行政区划 2 流域用户

    private WinsEntry winsEntry;

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
        return R.layout.fragment_wins_chart_layout;
    }


    @Override
    protected void initView() {
        App.jurdAreaType = "1";
        App.orgJurd ="000000000000";
        orgLevel = 1;
        showDataLoadingDialog();
        rbtnJianguan.setVisibility(View.GONE);
        rbtnLiuyu.setVisibility(View.GONE);
        rbtnZhiguan.setVisibility(View.GONE);
        radio_group_type.setVisibility(View.GONE);
        // 行政区划
        if ("1".equals(App.jurdAreaType)) {
            orgType = 1;
        } else if ("3".equals(App.jurdAreaType) || "4".equals(App.jurdAreaType)) {
            orgType = 2;
        }
        if(orgType == 1) {
            if (orgLevel == 1) {
                // 部级用户  直管 流域 监管
                rbtnJianguan.setVisibility(View.VISIBLE);
                rbtnLiuyu.setVisibility(View.VISIBLE);
                rbtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
                radio_group_type.setVisibility(View.VISIBLE);
                radio_group_type.check(R.id.radio_type_jianguan);
            } else if (orgLevel == 2) {
                // 省级  直管 监管
                rbtnJianguan.setVisibility(View.VISIBLE);
                rbtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            } else if (orgLevel == 3) {
                // 市 直管 监管
                rbtnJianguan.setVisibility(View.VISIBLE);
                rbtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            } else if (orgLevel == 4) {
                // 县  直管
            }
        }else  if(orgType == 2){
              // 本流域的水利稽查信息
        }
        webMap();

    }

    @Override
    protected void initListener() {
        bLoadFinish = false;
        bShowMap = false;
        radio_group_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bShowMap = false;
                webView.removeAllViews();
                switch (checkedId){
                    case R.id.radio_type_jianguan:
                        subType = "2";
                        if(orgLevel == 1) iMapLevel= -1;
                        else  iMapLevel = 4;
                        webView.loadUrl("file:///android_asset/chart/wins.html");
                        break;
                    case R.id.radio_type_liuyu:
                        subType= "1";
                        if(orgLevel == 1) iMapLevel = -2 ;
                        else  iMapLevel = 0;
                        webView.loadUrl("file:///android_asset/chart/wins_liuyu.html");
                        break;
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                bShowMap = false;
                switch (checkId) {
                    case R.id.radio_btn_zhiguan:
                        if(orgLevel == 1) {
                            radio_group_type.setVisibility(View.VISIBLE);
                            subType = "1";
                            radio_group_type.check(R.id.radio_type_jianguan);
                        }
                        type = 1;
                        if(orgType == 1) {
                            if(orgLevel == 1) iMapLevel= -1;
                            else  iMapLevel = 4;
                            if(rbTypeLiuyu.isChecked()) {
                                subType = "1";
                            }else if(rbtnJianguan.isChecked()){
                                subType = "2";
                            }
                        }
                        webView.removeAllViews();
                        break;
                    case R.id.radio_btn_liuyu:
                        radio_group_type.setVisibility(View.GONE);
                        if(orgLevel == 1) iMapLevel = -2 ;
                        else  iMapLevel = 0;
                        type = 2;
                        break;
                    case R.id.radio_btn_jianguan:
                        radio_group_type.setVisibility(View.GONE);
                        if(orgLevel == 1) iMapLevel = -1;
                        else  iMapLevel = 4;
                        type = 3;
                        break;
                }
                if(orgType == 1) {
                    if(type == 2)
                        webView.loadUrl("file:///android_asset/chart/wins_liuyu.html");
                    else {
                        webView.loadUrl("file:///android_asset/chart/wins.html");
                    }
                }
                else if(orgType == 2){
                    webView.loadUrl("file:///android_asset/chart/wins_liuyu.html");
                }
            }
        });

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
        if (orgType == 1 || orgType == 3) {
            if(orgLevel == 1){
                iMapLevel = -1;
            }
            else iMapLevel = 4;
            webView.loadUrl("file:///android_asset/chart/wins.html");
        } else if (orgType == 2) {
            iMapLevel = 0;
            webView.loadUrl("file:///android_asset/chart/wins_liuyu.html");
        }
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bLoadFinish = true;
                if (!bShowMap && !mLon.isEmpty() && !mLat.isEmpty()) {
                    bShowMap = true;
                    refreshUI();
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            //重写WebChromeClient的onGeolocationPermissionsShowPrompt
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
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
        @JavascriptInterface
        public String getAcciInfoForMark(String orgGuid){
            Gson gson = new Gson();
            String jsonStr = "";
            for(WinsEntry.SubWinsDataBean bean :winsEntry.getData().getSUBWINSDATA()){
                if(bean.getOBJGUID().equals(orgGuid)){
                    AcciInfo acciInfo = new AcciInfo(bean.getOBJNAME(),bean.getWINSPROBQUA(),bean.getWINSPROJQUA(),
                            bean.getWINSQUA());
                    jsonStr = gson.toJson(acciInfo);
                    break;
                }
            }

            return jsonStr;

        }

    }

    public void setMapData(MapBoundBean mapData) {
        String center = mapData.centerXY;
        String[] array = center.split(",");
        if (orgType == 1 || orgType == 4) {
            if(orgLevel == 1){
                iMapLevel = -1;
            }else {
                iMapLevel = 4;
            }
        } else {
            iMapLevel = 0;
        }
        mLon = array[0];
        mLat = array[1];
        if (bLoadFinish && !bShowMap) {
            bShowMap = true;
            refreshUI();
        }
    }

    private void addMarkInfo(List<Point> list) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        webView.loadUrl("javascript:updateCurrentPoint(" + jsonStr + ")");
    }

    private void  refreshUI(){
        closeDataDialog();
        webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
        List<Point> list = new ArrayList<>();
        list.clear();
        int type;
        if (rbtnZhiguan.isChecked()) {
            type = 1;
        } else if (rbtnJianguan.isChecked()) {
            type = 2;
        } else if (rbtnLiuyu.isChecked()) {
            type = 3;
        } else {
            type = 1;
        }
        if(type == 1) {
            if (rbTypeJianguan.isChecked()) {
                subType = "2";
            } else if (rbTypeLiuyu.isChecked()) {
                subType = "1";
            }
        }else {
            subType = "";
        }
        RetrofitHttpMethods.getInstance().getThematicWins(new Observer<WinsEntry>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(WinsEntry winsEntry) {
                LogUtils.i(TAG, "onNext");
                List<Point> list = new ArrayList<>();
                list.clear();
                if(winsEntry == null || winsEntry.getData() == null){
                    ToastUtils.show("未获取到数据");
                    return;
                }
                for(WinsEntry.SubWinsDataBean bean:winsEntry.getData().getSUBWINSDATA()){
                    list.add(new Point(bean.getOBJLONG(),bean.getOBJLAT(),bean.getWINSPROBQUA(),bean.getOBJGUID()));
                }
                setData(winsEntry);
                addMarkInfo(list);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtils.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                LogUtils.i(TAG, "onComplete");
            }//todo 参数
        }, type + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), subType, "","");
    }
    private void  setData(WinsEntry winsEntry){
        this.winsEntry = winsEntry;
    }
    public WinsEntry getData(){
        return this.winsEntry;
    }
    class Point {
        public Point(String lon, String lat, String value,String guid) {
            this.lon = lon;
            this.lat = lat;
            this.value = value;
            this.guid = guid;
        }
        String lon;
        String lat;
        String value;
        String guid;
    }
    class AcciInfo{
        public AcciInfo(String orgName,String acciCasNum,String acciLoss,String occNum){
            this.orgName = orgName;
            this.acciCasNum = acciCasNum;
            this.acciLoss = acciLoss;
            this.occNum = occNum;

        }
        String orgName;
        String acciCasNum;
        String acciLoss;
        String occNum;
    }
}
