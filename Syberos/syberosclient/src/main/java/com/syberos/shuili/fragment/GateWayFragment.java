package com.syberos.shuili.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.listener.Back2LoginActivityListener;
import com.syberos.shuili.network.SoapUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.utils.CommonUtils.encrypt;

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

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    private static GateWayAdatper m_adapter;
    static List<String> datas = new ArrayList<>();

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
                .setCaptureActivity(CustomScannerActivity.class) // 设置自定义的 activity_accident_query
                .initiateScan(); // 初始化扫描
    }

    @OnClick(R.id.iv_action_bar_left)
    void go2PersonalCenterActivity() {
        if (null != getOpenDrawerListener()) {
            getOpenDrawerListener().open();
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

                if (scanResult.contains(" ")) {
                    String[] scanResultArray = scanResult.split(" ");
                    if (2 == scanResultArray.length) {
                        switch (Integer.valueOf(scanResultArray[0])) { // 0-登录，1-工程，2-证书
                            case 0:
                                Log.d(TAG, "授权登录中...");
                                showLoadingDialog("授权登录中...");
                                final String methodName = "updateRelGuidUser";
                                final HashMap<String,Object> params = new HashMap<>();
                                params.put("arg0", scanResultArray[1]);
                                params.put("arg1", "yuanxinkeji");
                                params.put("arg2", encrypt("012432"));
                                class LoginRunnable implements Runnable {
                                    @Override
                                    public void run() {
                                        SoapUtils.getInstance().callWebService(params, methodName, new RequestCallback<Object>() {
                                            @Override
                                            public void onResponse(final Object result) {
                                                mHandler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        closeDialog();
                                                        Integer r = Integer.valueOf(result.toString());
                                                        Log.d(TAG, "授权登录结果：" + r);
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(final ErrorInfo.ErrorCode errorInfo) {
                                                mHandler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        closeDialog();
                                                        ToastUtils.show(errorInfo.getMessage());
                                                    }
                                                });
                                            }

                                        }, SoapUtils.SoapType.WSDL_BASE);
                                    }
                                }
                                new Thread(new LoginRunnable()).start();
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            default:
                                break;
                        }
                    } else {
                        Log.d(TAG,"=================== 1");
                    }
                } else {
                    Log.d(TAG,"=================== 0");
                }

//                Bundle bundle = new Bundle();
//                if (Strings.isValidUrl(scanResult)) {
//                } else {
//                    // TODO: 18-3-22 test show project details
//                    bundle.putString(ProjectDetailsActivity.RESULT_UNIT,
//                            "南京市河道管理处");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_NAME,
//                            "长江南京新济州河段河道整治工程");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_CODE,
//                            "1220000008881");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PARENT_UNIT,
//                            "南京市水务局");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_STATUS,
//                            "在建工程");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_START,
//                            "2014年01月");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_END,
//                            "2018年6月");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_PRICE,
//                            "800万元");
//                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_IMPORTANCE,
//                            "市重点");
//
//                    intentActivity(getActivity(), ProjectDetailsActivity.class,
//                            false, bundle);
//                }

                ToastUtils.show("Scan Result: " + scanResult);

            }
        }
    }

    TabAdapter tabAdapter;

    public static final String[] tabTitle = new String[]{"安监咨讯", "三类人员", "标准化", "教育培训"};

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
        tv_action_bar_title.setVisibility(View.INVISIBLE);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            GateWayTableLayoutFragment fragment = new GateWayTableLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TabLayoutFragment.TABLAYOUT_FRAGMENT,i+1);
            fragment.setType(bundle);
            fragments.add(fragment);
        }
        tabAdapter = new TabAdapter(getChildFragmentManager(), fragments, tabTitle);
        m_adapter = new GateWayAdatper(mContext,R.layout.simple_list_row,datas);
        //给ViewPager设置适配器
        vp_content.setAdapter(tabAdapter);
        //将TabLayout和ViewPager关联起来。
        tl_tab.setupWithViewPager(vp_content);
        //设置可以滑动
        tl_tab.setTabMode(TabLayout.MODE_FIXED);

    }

    public void setBack2LoginActivityListener(
            Back2LoginActivityListener back2LoginActivityListener) {
        this.back2LoginActivityListener = back2LoginActivityListener;
    }
    public static class GateWayTableLayoutFragment extends TabLayoutFragment{
        public GateWayTableLayoutFragment(){

        }

        @Override
        protected void initData() {
            datas.clear();
            switch (type){
                case 1:
                case 2:
                case 3:
                    for(int i = 0 ; i <10 ;i++) {
                        String info = "海淀区小月河水利工程";
                        datas.add(info);
                    }
                    recyclerViewLayout.setAdapter(m_adapter);
                    m_adapter.setData(datas);
                    m_adapter.notifyDataSetChanged();
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