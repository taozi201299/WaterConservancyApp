package com.syberos.shuili.fragment.thematic;

import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
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
import com.syberos.shuili.entity.publicentry.LocationEntry;
import com.syberos.shuili.entity.thematic.sins.SinsEntry;
import com.syberos.shuili.entity.thematic.woas.WoasEntry;
import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.network.retrofit.BaseObserver;
import com.syberos.shuili.network.retrofit.RetrofitHttpMethods;
import com.syberos.shuili.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/6/26.
 * 安全检查专题图
 */

public class SinsChartFragment extends BaseLazyFragment {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.radio_btn_zhiguan)
    RadioButton radioBtnZhiguan;
    @BindView(R.id.radio_btn_liuyu)
    RadioButton radioBtnLiuyu;
    @BindView(R.id.radio_btn_jianguan)
    RadioButton radioBtnJianguan;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.radio_btn_benbu)
    Button radioBtnBenbu;
    @BindView(R.id.radio_group_area)
    RadioGroup radioGroupArea;

    private String mLon = "";
    private String mLat = "";
    private boolean bLoadFinish = false;
    private boolean bShowMap = false;
    private int iMapLevel = 0;
    private final static long duration = 10 * 1000;

    private int type = 1;// 1 获取直管工程数据 2 获取流域数据 3 获取监管工程数据

    private int orgLevel = BusinessConfig.getOrgLevel();
    private int orgType; // 1 行政区划 2 流域用户

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
        showDataLoadingDialog();
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
    SinsEntry sinsEntry;

    public SinsEntry getSinsEntry() {
        return sinsEntry;
    }

    public void setSinsEntry(SinsEntry sinsEntry) {
        this.sinsEntry = sinsEntry;
    }

    private void refreshUI() {
        closeDataDialog();
        bShowMap = true;
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
        RetrofitHttpMethods.getInstance().getThematicSins(new BaseObserver<SinsEntry>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SinsEntry sinsEntry) {
                setSinsEntry(sinsEntry);

                List<LocationEntry> list = new ArrayList<>();
                list.clear();
                if (sinsEntry == null || sinsEntry.getData() == null) {
                    ToastUtils.show("未获取到数据");
                    return;
                }
                for (SinsEntry.DataBean.SUBSINSDATABean bean : sinsEntry.getData().getSUBSINSDATA()) {
                    list.add(new LocationEntry(bean.getOBJLONG() + "", bean.getOBJLAT() + "", bean.getSINSQUA()+ ""));
                }
                addMarkInfo(list);
                EventBus.getDefault().postSticky(sinsEntry);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
//        },type + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), "", "");
        },type + "", "D7862390F88443AE87FA9DD1FE45A8B6", "", "");
//        RetrofitHttpMethods.getInstance().getThematicWoas(new Observer<WoasEntry>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(WoasEntry woasEntry) {
////                setWoasEntry(woasEntry);
////
////                List<WoasChartFragment.Point> list = new ArrayList<>();
////                list.clear();
////                if (woasEntry == null || woasEntry.getData() == null) {
////                    ToastUtils.show("未获取到数据");
////                    return;
////                }
////                for (WoasEntry.DataBean.SCORERANKBean bean :
////                        woasEntry.getData().getSCORERANK()) {
////                    list.add(new LocationEntry(bean.getORGLONG() + "", bean.getORGLAT() + "", bean.getAFINALSCOR() + ""));
////                }
//////                setHiddenEntry(hiddenEntry);
////                addMarkInfo(list);
////                EventBus.getDefault().postSticky(woasEntry);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
////        }, type + "", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId(), "", "");
//        }, type + "", "D7862390F88443AE87FA9DD1FE45A8B6", "", "");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        if (bLoadFinish) {
            bShowMap = true;
            webView.loadUrl("javascript:showMap(" + mLon + ',' + mLat + ',' + iMapLevel + ")");
//            todo
//            addMarkInfo();
        }
    }
    private void addMarkInfo(List<LocationEntry> list) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        webView.loadUrl("javascript:updateCurrentPoint(" + jsonStr + ")");
    }
}
