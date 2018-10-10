package com.syberos.shuili.fragment.thematic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.syberos.shuili.entity.thematic.haz.HazEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.network.retrofit.BaseObserver;
import com.syberos.shuili.network.retrofit.RetrofitHttpMethods;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/6/26.
 */

public class HazChartFragment extends BaseLazyFragment implements View.OnClickListener {

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
    private String TAG = getClass().getSimpleName();
    private String mLon = "";
    private String mLat = "";
    private boolean bLoadFinish = false;
    private boolean bShowMap = false;
    private int iMapLevel = 0;
    private final static long duration = 10 * 1000;
    private int type = 1;// 1 获取直管工程数据 2 获取流域数据 3 获取监管工程数据

    private int orgLevel = BusinessConfig.getOrgLevel();
    private int orgType; // 1 行政区划 2 流域用户

    private boolean bFirst = true;

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
        App.jurdAreaType = "1";
        App.orgJurd = "000000000000";
        orgLevel = 1;
        rbtnJianguan.setVisibility(View.GONE);
        rbtnLiuyu.setVisibility(View.GONE);
        rbtnZhiguan.setVisibility(View.GONE);
        // 行政区划
        if ("1".equals(App.jurdAreaType)) {
            orgType = 1;
        } else if ("3".equals(App.jurdAreaType) || "4".equals(App.jurdAreaType)) {
            orgType = 2;
        }
        if (orgType == 1) {
            if (orgLevel == 1) {
                // 部级用户  直管 流域 监管
                rbtnJianguan.setVisibility(View.VISIBLE);
                rbtnLiuyu.setVisibility(View.VISIBLE);
                rbtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
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
                rbtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            }
        } else if (orgType == 2) {
            rbtnZhiguan.setVisibility(View.VISIBLE);
            radioGroup.check(R.id.radio_btn_zhiguan);
        }
        webMap();
    }

    @Override
    protected void initListener() {
        bLoadFinish = false;
        bShowMap = false;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                webView.removeAllViews();
                bShowMap = false;
                switch (checkId) {
                    case R.id.radio_btn_zhiguan:
                        if (orgType == 1) {
                            if (orgLevel == 1) iMapLevel = -1;
                            else iMapLevel = 4;
                        } else if (orgType == 2) {
                            iMapLevel = 0;
                        }
                        type = 1;
                        break;
                    case R.id.radio_btn_liuyu:
                        if (orgLevel == 1) iMapLevel = -2;
                        else iMapLevel = 0;
                        type = 3;
                        break;
                    case R.id.radio_btn_jianguan:
                        if (orgLevel == 1) iMapLevel = -1;
                        else iMapLevel = 4;
                        type = 2;
                        break;
                }
                if (orgType == 1) {
                    if (type == 3)
                        webView.loadUrl("file:///android_asset/chart/haz_liuyu.html");
                    else {
                        webView.loadUrl("file:///android_asset/chart/haz.html");
                    }
                } else if (orgType == 2) {
                    webView.loadUrl("file:///android_asset/chart/haz_liuyu.html");
                }
                setStatus1(type);
            }
        });

    }
    @Override
    protected void initData() {
        Log.d(TAG,"----------------initData");
        if(!bFirst) {
            showDataLoadingDialog();
            webMap();
        }
        bFirst = false;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG,"---------------destoryView");
        bFirst = true;
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"-----------------oncreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public HazEntry getHazEntry() {
        return hazEntry;
    }

    public void setHazEntry(HazEntry hazEntry) {
        this.hazEntry = hazEntry;
    }

    HazEntry hazEntry;

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
        webView.removeAllViews();
        if (orgType == 1 || orgType == 3) {
            if (orgLevel == 1) iMapLevel = -1;
            else iMapLevel = 4;
            if (type == 3)
                webView.loadUrl("file:///android_asset/chart/haz_liuyu.html");
            else {
                webView.loadUrl("file:///android_asset/chart/haz.html");
            }
        } else if (orgType == 2) {
            iMapLevel = 0;
            webView.loadUrl("file:///android_asset/chart/haz_liuyu.html");
        }
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bLoadFinish = true;
                if (!bShowMap && !mLon.isEmpty() && !mLat.isEmpty()) {
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
    public void onClick(View v) {


    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(mContext, "map test", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String getHazInfo(String orgGuid) {
            Gson gson = new Gson();
            String jsonStr = "";
            HazEntry hazEntry = getHazEntry();
            HazInfo hazInfo ;
            for(HazEntry.EveryEngBean bean : hazEntry.getData().getEveryEngList()){
                if(orgGuid.equals(bean.getORGCODE())){
                    hazInfo = new HazInfo();
                    hazInfo.name = bean.getORGNAME();
                    hazInfo.hazCount = String.valueOf(bean.getGENERALCONTROLCOUNT());
                    jsonStr = gson.toJson(hazInfo);
                    break;
                }
            }
            return jsonStr;
        }
    }

    public static class HazInfo{
        String  name;
        String  hazCount;
    }

    public void setMapData(MapBoundBean mapData) {
        String center = mapData.centerXY;
        String[] array = center.split(",");
        if (orgType == 1 || orgType == 4) {
            if (orgLevel == 1) iMapLevel = -2;
            else iMapLevel = 4;
        } else {
            iMapLevel = 0;
        }
        mLon = array[0];
        mLat = array[1];
        if (bLoadFinish && !bShowMap) {
            refreshUI();
        }
    }

    private void addMarkInfo(List<Point> list) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        if(webView == null)return;
        webView.loadUrl("javascript:updateCurrentPoint(" + jsonStr + ")");
    }

    private void refreshUI() {
        closeDataDialog();
        bShowMap = true;
        if(webView == null){
            closeDataDialog();
            webMap();
            return;
        }
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
        showDataLoadingDialog();
        RetrofitHttpMethods.getInstance().getThematicHaz(new BaseObserver<HazEntry>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.i(TAG + "getThematicHidden:", "onSubscribe");
            }

            @Override
            public void onNext(HazEntry hazEntry) {
                closeDataDialog();
                LogUtils.i(TAG + "getThematicHidden:", "onNext");
                List<Point> list = new ArrayList<>();
                list.clear();
                if (hazEntry == null || hazEntry.getData() == null) {
                    ToastUtils.show("未获取到数据");
                    return;
                }
                for(HazEntry.EveryEngBean bean : hazEntry.getData().getEveryEngList()){
                    Point point = new Point(String.valueOf(bean.getX()),String.valueOf(bean.getY()),String.valueOf(bean.getGENERALREGCOUNT()),bean.getORGCODE());
                    list.add(point);
                }
                setHazEntry(hazEntry);
                addMarkInfo(list);
                EventBus.getDefault().postSticky(hazEntry);
            }

            @Override
            public void onError(Throwable e) {
                closeDataDialog();
                e.printStackTrace();
                LogUtils.i(TAG + "getThematicHaz:", "onError");
            }

            @Override
            public void onComplete() {
                LogUtils.i(TAG + "getThematicHaz:", "onComplete");
            }//todo 参数
        }, type + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());


    }
    private void  setData(HazEntry hazEntry){
        this.hazEntry = hazEntry;
    }
    public HazEntry getData(){
        return this.hazEntry;
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
}
