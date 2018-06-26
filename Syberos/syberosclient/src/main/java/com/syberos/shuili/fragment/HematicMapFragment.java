package com.syberos.shuili.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.fragment.chart.AccidentChartFragment;
import com.syberos.shuili.fragment.chart.DanagerSourceChartFragment;
import com.syberos.shuili.fragment.chart.HiddenChartFragment;
import com.syberos.shuili.fragment.chart.SinsChartFragment;
import com.syberos.shuili.fragment.chart.StanChartFragment;
import com.syberos.shuili.fragment.chart.SuenChartFragment;
import com.syberos.shuili.fragment.chart.WinsChartFragment;
import com.syberos.shuili.fragment.chart.WoasChartFragment;
import com.syberos.shuili.listener.Back2LoginActivityListener;
import com.syberos.shuili.listener.OpenDrawerListener;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jidan on 18-3-10.
 */

public class HematicMapFragment extends BaseFragment {
    private static final String TAG = HematicMapFragment.class.getSimpleName();
    private static final String Hidden = "隐患";
    private static final String Acci = "事故";
    private static final String Haz = "危险源";
    private static final  String Stan = "标准化";
    private static final String Sins = "安全检查";
    private static final String Woas = "工作考核";
    private static final String Wins = "水利稽察";
    private static final String Suen = "安监执法";
    @BindView(R.id.tl_tab)
    TabLayout tl_tab;

    @BindView(R.id.vp_content)
    ViewPager vp_content;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    private OpenDrawerListener openDrawerListener = null;
    private Back2LoginActivityListener back2LoginActivityListener = null;

    @OnClick(R.id.iv_action_bar_right_2)
    void popupWindow() {
        ((TranslucentActivity) getActivity()).initShare("分享", "http://www.163.com").showShareView();
    }

    @OnClick(R.id.iv_action_bar_right_1)
    void go2ScanActivity() {
        IntentIntegrator intentIntegrator =
                IntentIntegrator.forSupportFragment(this);
        intentIntegrator
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setPrompt("将二维码/条形码放入框内，即可自动扫描")//写那句提示的话
                .setOrientationLocked(false)//扫描方向固定
                .setCaptureActivity(CustomScannerActivity.class) // 设置自定义的 activity
                .initiateScan(); // 初始化扫描
    }

    @OnClick(R.id.iv_action_bar_left)
    void go2PersonalCenterActivity() {
        if (null != openDrawerListener) {
            openDrawerListener.open();
        } else if (null != back2LoginActivityListener){
            // 没有登录
            back2LoginActivityListener.back();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult
                = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {

            } else {
                // scanResult 为获取到的字符串
                String scanResult = intentResult.getContents();
                ToastUtils.show("Scan Result: " + scanResult);
            }
        }
    }

    TabAdapter tabAdapter;
    public static final String[] tabTitle = new String[]{Hidden, Acci, Haz, Stan,Sins,Woas,Wins,Suen};

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
        tv_action_bar_title.setVisibility(View.INVISIBLE);
        List<Fragment> fragments = new ArrayList<>();
        BaseLazyFragment fragment = null;
        for (int i = 0; i < tabTitle.length; i++) {
            switch (tabTitle[i]){
                case Hidden:
                    fragment = new HiddenChartFragment();
                    break;
                case Acci:
                    fragment = new AccidentChartFragment();
                    break;
                case Haz:
                    fragment = new DanagerSourceChartFragment();
                    break;
                case Stan:
                    fragment = new StanChartFragment();
                    break;
                case Sins:
                    fragment = new SinsChartFragment();
                    break;
                case Woas:
                    fragment = new WoasChartFragment();
                    break;
                case Wins:
                    fragment = new WinsChartFragment();
                    break;
                case Suen:
                    fragment = new SuenChartFragment();
                    break;
            }
            fragments.add(fragment);
        }
        tabAdapter = new TabAdapter(getChildFragmentManager(), fragments, tabTitle);
        //给ViewPager设置适配器
        vp_content.setAdapter(tabAdapter);
        //将TabLayout和ViewPager关联起来。
        tl_tab.setupWithViewPager(vp_content);
        //设置可以滑动
        tl_tab.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    public void setOpenDrawerListener(OpenDrawerListener openDrawerListener) {
        this.openDrawerListener = openDrawerListener;
    }
}
