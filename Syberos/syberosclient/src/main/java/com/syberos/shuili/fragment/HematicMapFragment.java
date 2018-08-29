package com.syberos.shuili.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.App;
import com.syberos.shuili.activity.thematic.ThematicDetailActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.ProvinceJsonBean;
import com.syberos.shuili.entity.map.CityInfoBean;
import com.syberos.shuili.entity.map.MapBoundBean;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntryTest;
import com.syberos.shuili.fragment.thematic.detail.ThematicDetailAcciFragment;
import com.syberos.shuili.listener.ProvinceCall;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.fragment.thematic.AccidentChartFragment;
import com.syberos.shuili.fragment.thematic.DanagerSourceChartFragment;
import com.syberos.shuili.fragment.thematic.HiddenChartFragment;
import com.syberos.shuili.fragment.thematic.SinsChartFragment;
import com.syberos.shuili.fragment.thematic.StanChartFragment;
import com.syberos.shuili.fragment.thematic.SuenChartFragment;
import com.syberos.shuili.fragment.thematic.WinsChartFragment;
import com.syberos.shuili.fragment.thematic.WoasChartFragment;
import com.syberos.shuili.listener.Back2LoginActivityListener;
import com.syberos.shuili.listener.OpenDrawerListener;
import com.syberos.shuili.utils.ProvinceDialog;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.ViewPagerSlide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    public static final String Hidden = "隐患";
    public static final String Acci = "事故";
    public static final String Haz = "危险源";
    public static final String Stan = "标准化";
    public static final String Sins = "安全检查";
    public static final String Woas = "工作考核";
    public static final String Wins = "水利稽察";
    public static final String Suen = "安监执法";
    @BindView(R.id.tl_tab)
    TabLayout tl_tab;

    @BindView(R.id.vp_content)
    ViewPagerSlide vp_content;

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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getHiddenEntry(HiddenEntryTest hiddenEntryTest) {
        ToastUtils.show("has getData");
    }

    @OnClick(R.id.iv_action_bar_right_1)
    void showCharView() {
//
        int currentItem = vp_content.getCurrentItem();
        Intent intent = new Intent(getActivity(), ThematicDetailActivity.class);
        intent.putExtra("typeValue", tabTitle[currentItem]);
        intent.putExtra("ownerType", ((BaseLazyFragment) (fragments.get(currentItem))).getStatus1());
        intent.putExtra("dutyType", ((BaseLazyFragment) (fragments.get(currentItem))).getStatus2());
        switch (tabTitle[currentItem]) {
            case Hidden:
                Bundle bundle = new Bundle();
                bundle.putSerializable("hiddenData", ((HiddenChartFragment) fragments.get(currentItem)).getHiddenEntryTest());
                intent.putExtra("hiddenData", bundle);
                break;
            case Acci:
                break;
            case Haz:
                break;
            case Stan:
                break;
            case Sins:
                break;
            case Woas:
                break;
            case Wins:
                break;
            case Suen:
                break;
        }
        startActivity(intent);
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
        App.jurdAreaType = "1";
        App.orgJurd ="000000000000";
        String code = App.orgJurd;
        String url = GlobleConstants.mapServer + "/WEGIS-00-WEB_SERVICE/WSWebService";
        HashMap<String, String> params = new HashMap<>();
        params.put("templateCode", "140");
        if ("1".equals(App.jurdAreaType)) {
            params.put("type", "PROVINCE");
        } else if ("4".equals(App.jurdAreaType)) {
            params.put("type", "XZBAS");
        } else {
            params.put("type", "PROVINCE");
        }
        params.put("type", "XZBAS");
        code = code.substring(0, 6);
        params.put("guid", code);
        params.put("name", "");
        params.put("targetId", "search.GetBoundsAndCenterXYLogic");
        HttpUtils.getInstance().requestGet(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                if (!result.isEmpty()) {
                    Gson gson = new Gson();
                    mapBoundBean = gson.fromJson(result, MapBoundBean.class);
                    if (mapBoundBean == null && mapBoundBean.result == null ) {
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }else if(mapBoundBean.result.size() == 0){
                        if(App.orgJurd.equals("000000000000")){
                            MapBoundBean item = new MapBoundBean();
                            item.name = "全国";
                            item.centerXY = "108.953098279,34.2777998978";
                            mapBoundBean.result = new ArrayList<>();
                            mapBoundBean.result.add(item);
                        }
                    }
                    tv_action_bar_title.setText(mapBoundBean.result.get(0).name);
                    setMapData();
                } else {
                    ToastUtils.show("getCenterXY 内容为空");
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());
            }
        }, CacheMode.DEFAULT);
    }

    private void getCityName() {
        String url = GlobleConstants.mapServer + "/WEGIS-00-WEB_SERVICE/WSWebService?targetId=search.GetXzqhByPointLogic&point=" + "" + ',' + "" + "";
        HashMap<String, String> params = new HashMap<>();
        HttpUtils.getInstance().requestGet(url, params, url, new RequestCallback<String>() {

            @Override
            public void onResponse(String result) {
                if (result.isEmpty()) {
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
                } else {
                    ToastUtils.show("getCityName 为空");
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
            } else if (item instanceof AccidentChartFragment) {
                ((AccidentChartFragment) item).setMapData(mapBoundBean.result.get(0));
            } else if (item instanceof DanagerSourceChartFragment) {
                ((DanagerSourceChartFragment) item).setMapData(mapBoundBean.result.get(0));
            } else if (item instanceof SinsChartFragment) {
                ((SinsChartFragment) item).setMapData(mapBoundBean.result.get(0));
            } else if (item instanceof StanChartFragment) {
                ((StanChartFragment) item).setMapData(mapBoundBean.result.get(0));
            } else if (item instanceof SuenChartFragment) {
                ((SuenChartFragment) item).setMapData(mapBoundBean.result.get(0));
            } else if (item instanceof WinsChartFragment) {
                ((WinsChartFragment) item).setMapData(mapBoundBean.result.get(0));
            } else if (item instanceof WoasChartFragment) {
                ((WoasChartFragment) item).setMapData(mapBoundBean.result.get(0));
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
