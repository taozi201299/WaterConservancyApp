package com.syberos.shuili.fragment.thematic;

import android.annotation.SuppressLint;
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
import com.syberos.shuili.entity.thematic.woas.WoasEntry;
import com.syberos.shuili.network.retrofit.BaseObserver;
import com.syberos.shuili.network.retrofit.RetrofitHttpMethods;
import com.syberos.shuili.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/6/26.
 * 工作考核
 */

public class WoasChartFragment extends BaseLazyFragment {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.radio_btn_benbu)
    RadioButton radioBtnBenbu;
    @BindView(R.id.radio_btn_zhiguan)
    RadioButton radioBtnZhiguan;
    @BindView(R.id.radio_btn_jianguan)
    RadioButton radioBtnJianguan;
    @BindView(R.id.radio_btn_liuyu)
    RadioButton radioBtnLiuyu;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.radio_group_area)
    RadioGroup radioGroupArea;
    Unbinder unbinder;
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
        radioBtnJianguan.setVisibility(View.GONE);
        radioBtnLiuyu.setVisibility(View.GONE);
        radioBtnZhiguan.setVisibility(View.GONE);
        // 行政区划
        if ("1".equals(App.jurdAreaType)) {
            orgType = 1;
        } else if ("3".equals(App.jurdAreaType) || "4".equals(App.jurdAreaType)) {
            orgType = 2;
        }
        if (orgType == 1) {
            if (orgLevel == 1) {
                // 部级用户  直管 流域 监管
                radioBtnJianguan.setVisibility(View.VISIBLE);
                radioBtnLiuyu.setVisibility(View.VISIBLE);
                radioBtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            } else if (orgLevel == 2) {
                // 省级  直管 监管
                radioBtnJianguan.setVisibility(View.VISIBLE);
                radioBtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            } else if (orgLevel == 3) {
                // 市 直管 监管
                radioBtnJianguan.setVisibility(View.VISIBLE);
                radioBtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            } else if (orgLevel == 4) {
                // 县  直管
                radioBtnZhiguan.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.radio_btn_zhiguan);
            }
        } else if (orgType == 2) {
            radioBtnZhiguan.setVisibility(View.VISIBLE);
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
                        webView.loadUrl("file:///android_asset/chart/woas_liuyu.html");
                    else {
                        webView.loadUrl("file:///android_asset/chart/woas.html");
                    }
                } else if (orgType == 2) {
                    webView.loadUrl("file:///android_asset/chart/woas_liuyu.html");
                }
//                requestData(type);
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


    @SuppressLint("SetJavaScriptEnabled")
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
            if (orgLevel == 1) iMapLevel = -1;
            else iMapLevel = 4;
            webView.loadUrl("file:///android_asset/chart/woas.html");
        } else if (orgType == 2) {
            iMapLevel = 0;
            webView.loadUrl("file:///android_asset/chart/woas_liuyu.html");
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
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
    }

    WoasEntry woasEntry;

    public WoasEntry getWoasEntry() {
        return woasEntry;
    }

    public void setWoasEntry(WoasEntry woasEntry) {
        this.woasEntry = woasEntry;
    }

    private void refreshUI() {
        bShowMap = true;
        if(webView == null){
            closeDataDialog();
            webMap();
            return;
        }
        webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
        List<HiddenChartFragment.Point> list = new ArrayList<>();
        list.clear();
        int type;

        if (radioBtnZhiguan.isChecked()) {
            type = 1;
        } else if (radioBtnJianguan.isChecked()) {
            type = 2;
        } else if (radioBtnLiuyu.isChecked()) {
            type = 3;
        } else {
            type = 1;
        }
        showDataLoadingDialog();
        RetrofitHttpMethods.getInstance().getThematicWoas(new BaseObserver<WoasEntry>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WoasEntry woasEntry) {
                closeDataDialog();
                setWoasEntry(woasEntry);

                List<Point> list = new ArrayList<>();
                list.clear();
                if (woasEntry == null || woasEntry.getData() == null) {
                    ToastUtils.show("未获取到数据");
                    return;
                }
                for (WoasEntry.DataBean.SCORERANKBean bean :
                        woasEntry.getData().getSCORERANK()) {
                    list.add(new Point(bean.getORGLONG() + "", bean.getORGLAT() + "", bean.getAFINALSCOR() + ""));
                }
//                setHiddenEntry(hiddenEntry);
                addMarkInfo(list);
                EventBus.getDefault().postSticky(woasEntry);
            }

            @Override
            public void onError(Throwable e) {
                closeDataDialog();

            }

            @Override
            public void onComplete() {

            }
        }, type + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), "", "");
//        }, type + "", "D7862390F88443AE87FA9DD1FE45A8B6", "", "");
    }

    class Point {
        public Point(String lon, String lat, String value) {
            this.lon = lon;
            this.lat = lat;
            this.value = value;
        }

        String lon;
        String lat;
        String value;
    }

    private void addMarkInfo(List<Point> list) {
        if(webView == null)return;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        webView.loadUrl("javascript:updateCurrentPoint(" + jsonStr + ")");
    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {

        }

        @JavascriptInterface
        public void toast(String str) {
            Toast.makeText(mContext, "map test", Toast.LENGTH_SHORT).show();
        }

    }

    public void setMapData(MapBoundBean mapData) {
        String center = mapData.centerXY;
        String[] array = center.split(",");
        iMapLevel = 4;
        mLon = array[0];
        mLat = array[1];
        if (bLoadFinish && !bShowMap) {
            bShowMap = true;
            refreshUI();
        }
    }

    private void addMarkInfo() {
        webView.loadUrl("javascript:updateCurrentPoint(" + mLon + ',' + mLat + ")");
    }
}
