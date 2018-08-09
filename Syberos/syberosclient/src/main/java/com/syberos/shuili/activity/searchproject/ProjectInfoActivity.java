package com.syberos.shuili.activity.searchproject;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.utils.Strings;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/9.
 */

public class ProjectInfoActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_info;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if (null != bundle) {
            String url = bundle.getString("url");
            webView.loadUrl(url);
        }

    }

    @Override
    public void initView() {
        showTitle("工程详情");
        setActionBarRightVisible(View.INVISIBLE);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.requestFocus();



    }
}
