package com.syberos.shuili.fragment;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.App;
import com.syberos.shuili.activity.ThematicDetailActivity;
import com.syberos.shuili.entity.ProvinceJsonBean;
import com.syberos.shuili.entity.map.CityInfoBean;
import com.syberos.shuili.entity.map.MapBoundBean;
import com.syberos.shuili.listener.ProvinceCall;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.BaseLazyFragment;
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
import com.syberos.shuili.utils.ProvinceDialog;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by jidan on 18-3-10.
 */

public class HematicMapFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = HematicMapFragment.class.getSimpleName();
    private static final String Hidden = "隐患";
    private static final String Acci = "事故";
    private static final String Haz = "危险源";
    private static final String Stan = "标准化";
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

    @BindView(R.id.iv_action_bar_right_1)
    ImageView ivRight;

    private OpenDrawerListener openDrawerListener = null;
    private Back2LoginActivityListener back2LoginActivityListener = null;
    private List<Fragment> fragments;
    private final int RC_PERM = 110;
    public static final String[] requestPermissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE,};

    private String strCityName = "";
    MapBoundBean mapBoundBean = null;

//    @OnClick(R.id.tv_action_bar_title)  点击 标题（地区名） 选择地区 —— 不需要该功能了
    public void titleOnClick() {
        ProvinceDialog provinceDialog = new ProvinceDialog(mContext, new ProvinceCall() {
            @Override
            public void successGetAreaValue(Object s) {
                ToastUtils.show("所选地区编号：" + ((ProvinceJsonBean) s).getAD_CODE());
                tv_action_bar_title.setText(((ProvinceJsonBean) s).getAD_NAME());
            }

            @Override
            public void failGetAreaValue() {

            }
        });
        provinceDialog.showProvinceDialog();
    }

    @OnClick(R.id.iv_action_bar_right_1)
    void showCharView() {
//
        startActivity(new Intent(getActivity(), ThematicDetailActivity.class));
    }

    @OnClick(R.id.iv_action_bar_left)
    void go2PersonalCenterActivity() {
        if (null != openDrawerListener) {
            openDrawerListener.open();
        } else if (null != back2LoginActivityListener) {
            // 没有登录
            back2LoginActivityListener.back();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
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
    public static final String[] tabTitle = new String[]{Hidden, Acci, Haz, Stan, Sins, Woas, Wins, Suen};

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic;
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
//        tv_action_bar_title.setVisibility(View.INVISIBLE);
        requestMulti();
        fragments = new ArrayList<>();
        BaseLazyFragment fragment = null;
        for (int i = 0; i < tabTitle.length; i++) {
            switch (tabTitle[i]) {
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

    private void getCenterXY() {
        String code = App.orgJurd;
        String url = "http://192.168.1.11:8091/WEGIS-00-WEB_SERVICE/WSWebService";
        HashMap<String, String> params = new HashMap<>();
        params.put("templateCode", "140");
        if (App.jurdAreaType.equals("1")) {
            params.put("type", "PROVINCE");
        } else if (App.jurdAreaType.equals("3")) {
            params.put("type", "XZBA");
        }
        //params.put("guid",code);
        params.put("guid", "152201");
        params.put("name", "");
        params.put("targetId", "search.GetBoundsAndCenterXYLogic");
        HttpUtils.getInstance().requestGet(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                mapBoundBean = gson.fromJson(result, MapBoundBean.class);
                if (mapBoundBean != null && mapBoundBean.result != null && mapBoundBean.result.size() == 0) {
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                } else {
                    tv_action_bar_title.setText(mapBoundBean.result.get(0).name);
                    setMapData();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());
            }
        }, CacheMode.DEFAULT);
    }

    private void getCityName() {
        String url = "http://192.168.1.11:8091/WEGIS-00-WEB_SERVICE/WSWebService?targetId=search.GetXzqhByPointLogic&point=" + "" + ',' + "" + "";
        HashMap<String, String> params = new HashMap<>();
        HttpUtils.getInstance().requestGet(url, params, url, new RequestCallback<String>() {

            @Override
            public void onResponse(String result) {
                CityInfoBean cityInfoBean = new CityInfoBean();
                Gson gson = new Gson();
                cityInfoBean = gson.fromJson(result, CityInfoBean.class);
                if (cityInfoBean != null && cityInfoBean.result != null && cityInfoBean.result.size() > 0) {
                    for (CityInfoBean item : cityInfoBean.result) {
                        if (item.level.equals("3")) {
                            strCityName = item.name;
                            break;

                        }
                    }
                }


            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        }, CacheMode.DEFAULT);
    }

    private void setMapData() {
        for (Fragment item : fragments) {
            if (item instanceof HiddenChartFragment) {
                ((HiddenChartFragment) item).setMapData(mapBoundBean.result.get(0));
            }
        }
    }

    /**
     * 请求多个权限
     */
    public void requestMulti() {
        EasyPermissions.requestPermissions(this, "需要申请功能", RC_PERM, requestPermissions);
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
            getCenterXY();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_PERM) {
            ToastUtils.show("权限被拒绝，无法正常加载地图");
        }
    }
}
