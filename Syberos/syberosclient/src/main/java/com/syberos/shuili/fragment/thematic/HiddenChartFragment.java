package com.syberos.shuili.fragment.thematic;

import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cjt2325.cameralibrary.util.LogUtil;
import com.google.gson.Gson;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.map.MapBoundBean;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntryTest;
import com.syberos.shuili.retrofit.RetrofitApiService;
import com.syberos.shuili.retrofit.RetrofitHttpMethods;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/6/26.
 */

public class HiddenChartFragment extends BaseLazyFragment implements View.OnClickListener {

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

    HiddenEntryTest hiddenZhiguan, hiddenJianguan, hiddenLiuyu;

    @Override
    protected void initView() {
        showDataLoadingDialog();
        // 行政区划
        if ("1".equals(App.jurdAreaType)) {
            type = 1;
        } else if ("3".equals(App.jurdAreaType) || "4".equals(App.jurdAreaType)) {
            type = 2;
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

                switch (checkId) {
                    case R.id.radio_btn_zhiguan:
                        iMapLevel = 4;
                        type = 1;
//                        webView.removeAllViews();
//                        webView.loadUrl("file:///android_asset/chart/hidd.html");
                        break;
                    case R.id.radio_btn_liuyu:
                        type = 2;
                        iMapLevel = 0;
//                        webView.removeAllViews();
//                        webView.loadUrl("file:///android_asset/chart/hidd_liuyu.html");
                        break;
                    case R.id.radio_btn_jianguan:
                        iMapLevel = 4;
                        type = 3;
//                        webView.removeAllViews();
//                        webView.loadUrl("file:///android_asset/chart/hidd.html");
                        break;

                }

                RetrofitHttpMethods.getInstance().getThematicHidden(new Observer<HiddenEntryTest>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.i(TAG + "getThematicHidden:", "onSubscribe");
                    }

                    @Override
                    public void onNext(HiddenEntryTest hiddenEntryTest) {
                        LogUtils.i(TAG + "getThematicHidden:", "onNext");
                        List<Point> list = new ArrayList<>();
                        list.clear();
                        if (type == 1) {
                            for (HiddenEntryTest.DataBean.ITEMDATABean bean :
                                    hiddenEntryTest.getData().getITEMDATA()) {
                                list.add(new Point(bean.getENG_LONG() + "", bean.getENG_LAT() + "", bean.getHIDDTOTALQUA() + ""));
                            }
                        } else {
                            for (HiddenEntryTest.DataBean.ITEMDATABean bean :
                                    hiddenEntryTest.getData().getITEMDATA()) {
                                list.add(new Point(bean.getAD_LONG() + "", bean.getAD_LAT() + "", bean.getHIDDTOTALQUA() + ""));
                            }

                        }

                        setHiddenEntryTest(hiddenEntryTest);

                        addMarkInfo(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtils.i(TAG + "getThematicHidden:", "onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i(TAG + "getThematicHidden:", "onComplete");
                    }//todo 参数
                }, type + "", SyberosManagerImpl.getInstance().

                        getCurrentUserInfo().
                        getOrgId(), "", "");


            }
        });

    }

    @Override
    protected void initData() {


    }

    public HiddenEntryTest getHiddenEntryTest() {
        return hiddenEntryTest;
    }

    public void setHiddenEntryTest(HiddenEntryTest hiddenEntryTest) {
        this.hiddenEntryTest = hiddenEntryTest;
    }

    HiddenEntryTest hiddenEntryTest;

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
        if (type == 1 || type == 3) {
            iMapLevel = 4;
            webView.loadUrl("file:///android_asset/chart/hidd.html");
        } else if (type == 2) {
            iMapLevel = 0;
            webView.loadUrl("file:///android_asset/chart/hidd_liuyu.html");
        }
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "DEMO");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bLoadFinish = true;
                if (!bShowMap && !mLon.isEmpty() && !mLat.isEmpty()) {
                    closeDataDialog();
                    webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
                    List<Point> list = new ArrayList<>();
                    list.clear();
                    list.add(new Point(mLon, mLat, ""));
                    addMarkInfo(list);
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
                    RetrofitHttpMethods.getInstance().getThematicHidden(new Observer<HiddenEntryTest>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            LogUtils.i(TAG + "getThematicHidden:", "onSubscribe");
                        }

                        @Override
                        public void onNext(HiddenEntryTest hiddenEntryTest) {
                            LogUtils.i(TAG + "getThematicHidden:", "onNext");
                            List<Point> list = new ArrayList<>();
                            list.clear();
                            for (HiddenEntryTest.DataBean.ITEMDATABean bean :
                                    hiddenEntryTest.getData().getITEMDATA()) {
                                list.add(new Point(bean.getENG_LONG() + "", bean.getENG_LAT() + "", bean.getHIDDTOTALQUA() + ""));
                            }
                            setHiddenEntryTest(hiddenEntryTest);
                            addMarkInfo(list);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            LogUtils.i(TAG + "getThematicHidden:", "onError");
                        }

                        @Override
                        public void onComplete() {
                            LogUtils.i(TAG + "getThematicHidden:", "onComplete");
                        }//todo 参数
                    }, type + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), "", "");


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

    }

    public void setMapData(MapBoundBean mapData) {
        String center = mapData.centerXY;
        String[] array = center.split(",");
        if (type == 1 || type == 4) {
            iMapLevel = 4;
        } else {
            iMapLevel = 0;
        }
        mLon = array[0];
        mLat = array[1];
        if (bLoadFinish) {
            closeDataDialog();
            bShowMap = true;
            webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
            List<Point> list = new ArrayList<>();
            list.clear();
            list.add(new Point(mLon, mLat, ""));
            addMarkInfo(list);
        }
    }

    private void addMarkInfo(List<Point> list) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        webView.loadUrl("javascript:updateCurrentPoint(" + jsonStr + ")");
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
}
