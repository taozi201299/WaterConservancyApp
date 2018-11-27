[1mdiff --git a/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragment.java b/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragment.java[m
[1mindex 7e50a4d..80b6207 100644[m
[1m--- a/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragment.java[m
[1m+++ b/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragment.java[m
[36m@@ -10,6 +10,8 @@[m [mimport android.support.v4.app.Fragment;[m
 import android.support.v4.view.ViewPager;[m
 import android.util.Log;[m
 import android.view.View;[m
[32m+[m[32mimport android.webkit.JsResult;[m
[32m+[m[32mimport android.webkit.WebChromeClient;[m
 import android.webkit.WebView;[m
 import android.webkit.WebViewClient;[m
 import android.widget.ImageView;[m
[36m@@ -148,12 +150,34 @@[m [mpublic class GateWayFragment extends BaseFragment {[m
 [m
         @Override[m
         protected void initData() {[m
[32m+[m[32m            webview.getSettings().setUseWideViewPort(true);[m
[32m+[m[32m            webview.getSettings().setLoadWithOverviewMode(true);[m
[32m+[m[32m            webview.getSettings().setJavaScriptEnabled(true);[m
[32m+[m[32m            webview.setWebChromeClient(new WebChromeClient()[m
[32m+[m[32m            {[m
[32m+[m
[32m+[m[32m                @Override[m
[32m+[m[32m                public boolean onJsAlert(WebView view, String url, String message,[m
[32m+[m[32m                                         JsResult result)[m
[32m+[m[32m                {[m
[32m+[m[32m                    // TODO Auto-generated method stub[m
[32m+[m[32m                    return super.onJsAlert(view, url, message, result);[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m            });[m
             webview.setWebViewClient(new WebViewClient(){[m
                 @Override[m
                 public boolean shouldOverrideUrlLoading(WebView view, String url) {[m
                     view.loadUrl(url);[m
                     return true;[m
                 }[m
[32m+[m
[32m+[m[32m                @Override[m
[32m+[m[32m                public void onPageFinished(WebView view, String url) {[m
[32m+[m[32m                    super.onPageFinished(view, url);[m
[32m+[m[32m                    String viewPort = "var viewPortTag=document.createElement('meta'); viewPortTag.id='viewport'; viewPortTag.name = 'viewport';viewPortTag.content = 'width=1280; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;'; document.getElementsByTagName('head')[0].appendChild(viewPortTag);";[m
[32m+[m[32m                    webview.loadUrl("javascript:" + viewPort);[m
[32m+[m[32m                }[m
             });[m
             int index = type;[m
             switch (index){[m
[36m@@ -170,6 +194,7 @@[m [mpublic class GateWayFragment extends BaseFragment {[m
                     webview.loadUrl(GlobleConstants.str7GeIP0 +"/eutr/eutr/public/publicstudy ");[m
                     break;[m
             }[m
[32m+[m
         }[m
     }[m
     public class GateWayAdatper extends CommonAdapter<String> {[m
[1mdiff --git a/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragmentEnterprises.java b/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragmentEnterprises.java[m
[1mindex 79740dc..c586a19 100644[m
[1m--- a/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragmentEnterprises.java[m
[1m+++ b/Syberos/syberosclient/src/main/java/com/syberos/shuili/fragment/GateWayFragmentEnterprises.java[m
[36m@@ -9,6 +9,8 @@[m [mimport android.support.design.widget.TabLayout;[m
 import android.support.v4.app.Fragment;[m
 import android.support.v4.view.ViewPager;[m
 import android.view.View;[m
[32m+[m[32mimport android.webkit.JsResult;[m
[32m+[m[32mimport android.webkit.WebChromeClient;[m
 import android.webkit.WebView;[m
 import android.webkit.WebViewClient;[m
 import android.widget.ImageView;[m
[36m@@ -118,12 +120,34 @@[m [mpublic class GateWayFragmentEnterprises extends BaseFragment {[m
 [m
         @Override[m
         protected void initData() {[m
[32m+[m[32m            webview.getSettings().setUseWideViewPort(true);[m
[32m+[m[32m            webview.getSettings().setLoadWithOverviewMode(true);[m
[32m+[m[32m            webview.getSettings().setJavaScriptEnabled(true);[m
[32m+[m[32m            webview.setWebChromeClient(new WebChromeClient()[m
[32m+[m[32m            {[m
[32m+[m
[32m+[m[32m                @Override[m
[32m+[m[32m                public boolean onJsAlert(WebView view, String url, String message,[m
[32m+[m[32m                                         JsResult result)[m
[32m+[m[32m                {[m
[32m+[m[32m                    // TODO Auto-generated method stub[m
[32m+[m[32m                    return super.onJsAlert(view, url, message, result);[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m            });[m
             webview.setWebViewClient(new WebViewClient(){[m
                 @Override[m
                 public boolean shouldOverrideUrlLoading(WebView view, String url) {[m
                     view.loadUrl(url);[m
                     return true;[m
                 }[m
[32m+[m
[32m+[m[32m                @Override[m
[32m+[m[32m                public void onPageFinished(WebView view, String url) {[m
[32m+[m[32m                    super.onPageFinished(view, url);[m
[32m+[m[32m                    String viewPort = "var viewPortTag=document.createElement('meta'); viewPortTag.id='viewport'; viewPortTag.name = 'viewport';viewPortTag.content = 'width=1280; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;'; document.getElementsByTagName('head')[0].appendChild(viewPortTag);";[m
[32m+[m[32m                    webview.loadUrl("javascript:" + viewPort);[m
[32m+[m[32m                }[m
             });[m
             int index = type;[m
             switch (index){[m
[1mdiff --git a/Syberos/syberosclient/src/main/java/com/syberos/shuili/service/update/UpdateService.java b/Syberos/syberosclient/src/main/java/com/syberos/shuili/service/update/UpdateService.java[m
[1mindex 703504f..0cac534 100644[m
[1m--- a/Syberos/syberosclient/src/main/java/com/syberos/shuili/service/update/UpdateService.java[m
[1m+++ b/Syberos/syberosclient/src/main/java/com/syberos/shuili/service/update/UpdateService.java[m
[36m@@ -181,7 +181,9 @@[m [mpublic class UpdateService extends Service {[m
                 notification.contentView.setTextViewText(R.id.down_tv, notification_text_finish);[m
                 notification.flags |= Notification.FLAG_AUTO_CANCEL;[m
                 Intent installIntent = new Intent(Intent.ACTION_VIEW);[m
[31m-[m
[32m+[m[32m                /**[m
[32m+[m[32m                 * 需要适配7.0的安装[m
[32m+[m[32m                 */[m
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)[m
                 {[m
                     installIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);[m
