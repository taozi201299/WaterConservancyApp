package com.syberos.shuili.activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.shuili.callback.ErrorInfo;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.test.EngineInfoBean;
import com.syberos.shuili.utils.ToastUtils;
import java.util.List;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;


public class TestMapActivity extends BaseActivity {
    @BindView(R.id.view_gdmap)
    MapView view_gdmap;
    AMap aMap ;
    EngineInfoBean engineInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view_gdmap.onCreate(savedInstanceState);
        initMap();
    }

    public int getLayoutId() {
        return R.layout.test_map_activity;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        engineInfo = (EngineInfoBean) bundle.getSerializable("item");
        if(engineInfo == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            activityFinish();
        }
        List<EngineInfoBean.EngineBean> engineInfoBeans = engineInfo.getData().getData();
        int size = engineInfoBeans.size();
        for (int i = 0; i < size; i++) {
            aMap.addMarker(new MarkerOptions().anchor(1.5f, 3.5f)
                    .position(new LatLng(Double.valueOf(engineInfoBeans.get(i).getY()),//设置纬度
                            Double.valueOf(engineInfoBeans.get(i).getX())))//设置经度
                    .title(engineInfoBeans.get(i).getRES_NAME())
                    .snippet(engineInfoBeans.get(i).getRES_TYPE() + "&" +engineInfoBeans.get(i).getDEAD_LEV())
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.default_avatar_background)));
        }
    }
    @Override
    public void initView() {

    }
    public void initMap() {
        if (aMap == null) {
            aMap = view_gdmap.getMap();
            aMap.getUiSettings().setMyLocationButtonEnabled(false); // 是否显示定位按钮
            aMap.setMyLocationEnabled(false);//显示定位层并且可以触发定位,默认是flase
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
            aMap.moveCamera(CameraUpdateFactory.zoomTo(0));//设置地图缩放级别
            //设置自定义弹窗
            aMap.setInfoWindowAdapter(new WindowAdapter(this));
            //绑定信息窗点击事件
            aMap.setOnInfoWindowClickListener(new WindowAdapter(this));
            aMap.setOnMarkerClickListener(new WindowAdapter(this));
        }
    }
    public class WindowAdapter implements AMap.InfoWindowAdapter, AMap.OnMarkerClickListener,
            AMap.OnInfoWindowClickListener{

        private Context context;
        private static final String TAG = "WindowAdapter";

        public WindowAdapter(Context context) {
            this.context = context;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            //关联布局
            View view = LayoutInflater.from(context).inflate(R.layout.test_map_info_item_layout, null);
            //标题
            TextView title =  view.findViewById(R.id.info_title);
            String content = marker.getSnippet();
            String[] values = content.split("&");
            //工程类型
            TextView type =  view.findViewById(R.id.info_type);
            //工程级别
            TextView level =  view.findViewById(R.id.info_grade);
            title.setText(marker.getTitle());
            type.setText(values[0]);
            level.setText(values[1]);
            return view;
        }

        //如果用自定义的布局，不用管这个方法,返回null即可
        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(Marker marker) {
            return false;
        }

        //绑定信息窗点击事件
        @Override
        public void onInfoWindowClick(Marker marker) {
            go2Activity(marker);
        }
    }
    private void go2Activity(Marker marker){
        EngineInfoBean.EngineBean item = getItem(marker.getPosition().longitude,marker.getPosition().latitude);
        if(item == null){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",item);
        intentActivity(TestMapActivity.this,TestFormActivity.class,false,bundle);

    }
    private EngineInfoBean.EngineBean getItem(double x,double y){
        for(EngineInfoBean.EngineBean bean:engineInfo.getData().getData()){
            if(bean.getX().equals(String.valueOf(x)) && bean.getY().equals(String.valueOf(y))){
                return bean;
            }
        }
        return null;

    }
}