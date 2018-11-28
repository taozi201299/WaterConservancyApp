package com.syberos.shuili.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.login.LoginActivity;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.listener.Back2LoginActivityListener;
import com.syberos.shuili.network.SoapUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.utils.CommonUtils.encrypt;
import static com.syberos.shuili.utils.CommonUtils.fileCopy;

/**
 * Created by jidan on 18-3-10.
 * 门户 for 行政
 */

public class GateWayFragment extends BaseFragment {
    private final String TAG = GateWayFragment.class.getSimpleName();
    @BindView(R.id.tl_tab)
    TabLayout tl_tab;

    @BindView(R.id.vp_content)
    ViewPager vp_content;

    @BindView(R.id.tv_action_bar2_title)
    TextView tv_action_bar2_title;
    @BindView(R.id.iv_action_bar2_left)
    ImageView iv_action_bar2_left;
    @BindView(R.id.iv_action_bar2_right)
    ImageView iv_action_bar2_right;


    private static GateWayAdatper m_adapter;
    static List<String> datas = new ArrayList<>();

    private Back2LoginActivityListener back2LoginActivityListener = null;
    private boolean bUnLogin = false;
    TabAdapter tabAdapter;

    public static final String[] tabTitle = new String[]{"三类人员", "标准化", "教育培训"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gateway;
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tv_action_bar2_title.setText("门户");
        if(!bUnLogin) {
            iv_action_bar2_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) mContext).finish();
                }
            });
        }
        iv_action_bar2_right.setVisibility(View.INVISIBLE);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            GateWayTableLayoutFragment fragment = new GateWayTableLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TabLayoutFragment.TABLAYOUT_FRAGMENT,i+1);
            fragment.setType(bundle);
            fragments.add(fragment);
        }
        tabAdapter = new TabAdapter(getChildFragmentManager(), fragments, tabTitle);
        //给ViewPager设置适配器
        vp_content.setAdapter(tabAdapter);
        //将TabLayout和ViewPager关联起来。
        tl_tab.setupWithViewPager(vp_content);
        //设置可以滑动
        tl_tab.setTabMode(TabLayout.MODE_FIXED);
        if(bUnLogin){
            iv_action_bar2_left.setImageResource(R.mipmap.icon_person);
            iv_action_bar2_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    go2LoginActivity();
                    ((Activity) mContext).finish();
                }
            });
        }

    }

    public void setBack2LoginActivityListener(
            Back2LoginActivityListener back2LoginActivityListener) {
        this.back2LoginActivityListener = back2LoginActivityListener;
    }
    public void setView(){
        bUnLogin = true;

    }
    private void go2LoginActivity(){
        intentActivity((Activity) mContext, LoginActivity.class,true,true);
    }
    public static class GateWayTableLayoutFragment extends TabLayoutFragment{
        public GateWayTableLayoutFragment(){

        }

        @Override
        protected void initData() {
            webview.getSettings().setUseWideViewPort(true);
            webview.getSettings().setLoadWithOverviewMode(true);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebChromeClient(new WebChromeClient()
            {

                @Override
                public boolean onJsAlert(WebView view, String url, String message,
                                         JsResult result)
                {
                    // TODO Auto-generated method stub
                    return super.onJsAlert(view, url, message, result);
                }

            });
            webview.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    String  ttsa = GlobleConstants.str7GeIP0 +"/eutr/moni/public/ttsa";
                    String stan = GlobleConstants.str7GeIP0 +"/eutr/moni/public/stan";
                    String study = GlobleConstants.str7GeIP0 +"/eutr/eutr/public/publicstudy";
                    if(url.equals(ttsa) ||url.equals(stan) || url.equals(study)) {
                        view.loadUrl(url);
                        return true;
                    }else {
                        return false;
                    }
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String viewPort = "var viewPortTag=document.createElement('meta'); viewPortTag.id='viewport'; viewPortTag.name = 'viewport';viewPortTag.content = 'width=1280; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;'; document.getElementsByTagName('head')[0].appendChild(viewPortTag);";
                    view.loadUrl("javascript:" + viewPort);
                }
            });
            int index = type;
            switch (index){
                // 三类人员
                case 1:
                    webview.loadUrl(GlobleConstants.str7GeIP0 +"/eutr/moni/public/ttsa");
                    break;
                // 标准化
                case 2:
                    webview.loadUrl(GlobleConstants.str7GeIP0 +"/eutr/moni/public/stan");
                    break;
                // 教育培训
                case 3:
                    webview.loadUrl(GlobleConstants.str7GeIP0 +"/eutr/eutr/public/publicstudy");
                    break;
            }

        }
    }
    public class GateWayAdatper extends CommonAdapter<String> {

        public GateWayAdatper(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, String s) {
                        TextView tv = holder.getView(R.id.textView);
                        tv.setText(s);
        }
    }
}