package com.syberos.shuili.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.accident.AccidentListForEntAcitvity;
import com.syberos.shuili.activity.accident.AccidentQueryListForEntActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepTaskForEntActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationEngineForEntActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationRectifyTaskForEnterpriseActivity;
import com.syberos.shuili.activity.dangersource.HazListForEntActivity;
import com.syberos.shuili.activity.dangersource.HazSearchListForEntActivity;
import com.syberos.shuili.activity.dangersource.HazSearchListForFRActivity;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.activity.reports.AccReportForEntActivity;
import com.syberos.shuili.activity.reports.CheckReportForEntActivity;
import com.syberos.shuili.activity.reports.HiddenReportForEntActivity;
import com.syberos.shuili.activity.reports.WoasReportForEntActivity;
import com.syberos.shuili.activity.searchproject.ProjectInfoActivity;
import com.syberos.shuili.activity.securitycheck.EnterprisesElementCheckListActivity;
import com.syberos.shuili.activity.securitycheck.EnterprisesOnSiteCheckListActivity;
import com.syberos.shuili.activity.work.NotificationCenterActivity;
import com.syberos.shuili.activity.work.TodoWorkActivity;
import com.syberos.shuili.activity.work.TodoWorkForEntActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.amap.ShowNearlyInfoActivity;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.common.AppModule;
import com.syberos.shuili.entity.userinfo.ModuleBean;
import com.syberos.shuili.network.SoapUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PopupButton.ImportMenuView;
import com.syberos.shuili.view.PopupButton.RippleLayout;

import java.util.ArrayList;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by toby on 18-3-19.
 * 办公首页 for 企事业
 */

public class WorkFragmentEnterprises extends BaseFragment {

    @BindView(R.id.more2)
    RippleLayout ripple;

    @BindView(R.id.main_activity_import_menu)
    ImportMenuView menuView;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;
    @BindView(R.id.ll_module)
    LinearLayout ll_module;

    private ModuleViewHolder moduleViewHolder;
    private ModuleChildViewHolder moduleChildViewHolder;

    private String[] moduleNames;
    private String[] moduleChildReportCheckNames;
    private String[] moduleChildSecurityCheckNames;
    private String[] moduleChildHiddenDangerNames;
    private String[] moduleChildAccidentNames;
    private String[] moduleChildDangerName;

    private int[] moduleChildReportCheckIcon = {R.mipmap.icon_report_hidd_ent, R.mipmap.icon_report_acci_ent, R.mipmap.icon_report_check_ent, R.mipmap.icon_report_woas_ent};
    private int[] moduleChildSecurityCheckIcon = {R.mipmap.icon_check_element_ent, R.mipmap.icon_check_onsite_ent};
    private int[] moduleChildHiddenDangerIcon = {R.mipmap.icon_hidd_add_ent, R.mipmap.icon_hidd_rectify_ent, R.mipmap.icon_hidd_accept_ent};
    private int[] moduleChildAccidentIcon = {R.mipmap.icon_acci_ent, R.mipmap.icon_acci_query_ent};
    private int[] moduleChildDangerIcon = {R.mipmap.icon_haz_add_ent, R.mipmap.icon_haz_query_ent};

    private int[] moduleColor = {R.color.color_report_ent, R.color.color_check_ent,
             R.color.color_acci_ent,R.color.color_hidd_ent, R.color.color_haz_ent};


    @OnClick(R.id.iv_action_bar_right_1)
    void go2locationActivity() {
        intentActivity((Activity) mContext,ShowNearlyInfoActivity.class,false,true);
    }

    @OnClick(R.id.iv_action_bar_right_2)
    void go2ScanActivity() {
        IntentIntegrator intentIntegrator =
                IntentIntegrator.forSupportFragment(this);
        intentIntegrator
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setPrompt("将 二维码/条形码放入框内，即可自动扫描")//写那句提示的话
                .setOrientationLocked(false)//扫描方向固定
                .setCaptureActivity(CustomScannerActivity.class) // 设置自定义的 activity_accident_query
                .initiateScan(); // 初始化扫描
    }

    @OnClick(R.id.iv_action_bar_left)
    void go2PersonalCenterActivityFromWorkFragment() {
        if (null != getOpenDrawerListener()) {
            getOpenDrawerListener().open();
        }
    }

    @OnClick(R.id.ll_work_todo)
    void go2workTodoActivity() {
        intentActivity(getActivity(), TodoWorkForEntActivity.class, false, true);
    }

    @OnClick(R.id.ll_notification_center)
    void go2notificationCenterActivity() {
        intentActivity(getActivity(), NotificationCenterActivity.class, false, true);
    }

    @OnClick(R.id.ll_learn_center)
    void go2learnCenterActivity() {
        ToastUtils.show("TODO：请求打开学习中心");
    }

    private final String TAG = WorkFragment.class.getSimpleName();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_enterprises;
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tv_action_bar_title.setText(getResources().getString(R.string.title_work));
        moduleChildReportCheckNames = getResources().getStringArray(R.array.module_child_enterprise_baobiao);
        moduleChildSecurityCheckNames = getResources().getStringArray(R.array.module_child_enterprise_anquan);
        moduleChildHiddenDangerNames = getResources().getStringArray(R.array.module_child_enterprise_yinhuan);
        moduleChildAccidentNames = getResources().getStringArray(R.array.module_child_enterprise_shigu);
        moduleChildDangerName = getResources().getStringArray(R.array.module_child_enterprise_weixianyuan);

        ArrayList<String>moduleNameList = new ArrayList<>();

        ArrayList<String>moduleIds = new ArrayList<>();
        if(GlobleConstants.moduleBean != null){
            for(ModuleBean.ModuleInfoBean bean : GlobleConstants.moduleBean.getData().getData()){
                moduleIds.add(bean.getGuid());
            }
        }
        ArrayList<String>names = new ArrayList<>();
        for(String guid : moduleIds){
            if(GlobleConstants.moduleMap.get(guid) != null && !GlobleConstants.moduleMap.get(guid).isEmpty()) {
                names.add(GlobleConstants.moduleMap.get(guid));
            }
        }
        ArrayList<String>modules = new ArrayList<>();
        if(App.appModule != null && App.appModule.getData() != null) {
            ArrayList<AppModule.DataBean>list = (ArrayList<AppModule.DataBean>) App.appModule.getData();
            for(AppModule.DataBean item :list){
                if("1".equals(item.getModActive()) && "水行政用户".equals(item.getUserTypeName())){
                    modules.add(item.getBisModName());
                }
            }
        }
        if(!modules.contains("安全检查（企）")){
            names.remove("安全检查");
        }
        if(!modules.contains("危险源（企）")){
            names.remove("危险源");
        }
        if(!modules.contains("事故（企）")){
            names.remove("事故");
        }
        if(!modules.contains("隐患（企）")){
            names.remove("隐患");
        }

        /**
         * 监理和施工没有事故模块
         */
        if(GlobleConstants.CJJL.equalsIgnoreCase(App.sCode) ||GlobleConstants.CJSG.equalsIgnoreCase(App.sCode)){
            int count  = names.size();
            int index = -1;
            for(int i = 0 ; i < count ; i++){
                if(getResources().getString(R.string.module_shigu).equals(names.get(i))) {
                    index = i;
                   break;
                }
            }
            if(index != -1) {
                names.remove(index);
            }
        }
        if( names.size() != 0 ) {
            moduleNames = new String[names.size()];
            names.toArray(moduleNames);
        }else {
            moduleNames = getResources().getStringArray(R.array.module_enterprise);
        }
        App.moduleName.clear();
        for(int i = 0; i < moduleNames.length ; i++) {
            App.moduleName.add(moduleNames[i]);
        }
        int moduleCount = moduleNames.length;

        for (int i = 0; i < moduleCount; i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_module_item, null);
            moduleViewHolder = new ModuleViewHolder(mContext, view, null);
            moduleViewHolder.tv_moduleName.setText(moduleNames[i]);
            GradientDrawable drawable = (GradientDrawable) moduleViewHolder.ll_module_name.getBackground();
            //改变drawable的背景填充色
            drawable.setColor(getResources().getColor(moduleColor[i]));

            String[] childNames = getModuleNames(moduleNames[i]);
            int[] childIcon = getModulesIcon(moduleNames[i]);
            if (childNames == null) return;
            int size = childNames.length;
            for (int j = 0; j < size; j++) {
                if (childNames[j].equalsIgnoreCase(getResources().getString(R.string.module_child_yinhuan_chaxun))
                        || childNames[j].equalsIgnoreCase(getResources().getString(R.string.module_child_baobiao_gongzuo))
                        ||childNames[j].equals(getResources().getString(R.string.module_child_baobiao_anquan))) {
                    continue;
                }
                /**
                 * 技术服务 施工 监理单位 只有现场检查模块，项目法人和水利工程管理单位有元素检查
                 */
                if(GlobleConstants.CJFW.equalsIgnoreCase(App.sCode) || GlobleConstants.CJSG.equalsIgnoreCase(App.sCode) ||GlobleConstants.CJJL.equalsIgnoreCase(App.sCode)
                        ||GlobleConstants.CJYJ.equalsIgnoreCase(App.sCode)){
                    if(childNames[j].equals(getResources().getString(R.string.module_child_anquan_jianchayuansu))){
                        continue;
                    }
                }else if( GlobleConstants.CJFR.equalsIgnoreCase(App.sCode)){
                    if(childNames[j].equals(getResources().getString(R.string.module_child_anquan_xianchangjiancha))){
                        continue;
                    }
                }
                if(GlobleConstants.CJFR.equalsIgnoreCase(App.sCode) || GlobleConstants.CJJL.equalsIgnoreCase(App.sCode)
                        || GlobleConstants.CJZJ.equalsIgnoreCase(App.sCode)){
                    if(childNames[j].equals(getResources().getString(R.string.module_child_weixianyuan_xuncha))){
                        continue;
                    }

                }
                View childView = LayoutInflater.from(mContext).inflate(R.layout.layout_work_item_button, null);
                childView.setTag(childNames[j]);
                moduleChildViewHolder = new ModuleChildViewHolder(mContext, childView, null);
                moduleChildViewHolder.tv_moduleName.setText(childNames[j]);
                moduleChildViewHolder.iv_moduleImage.setImageResource(childIcon[j]);
                moduleViewHolder.ll_module_child.addView(childView);

            }
            ll_module.addView(view);
        }

        menuView.setEnabled(false);
        menuView.setActionsListener(new ImportMenuView.ActionsListener() {
            @Override
            public void itemClicked(int itemIndex) {
                ToastUtils.show("新建按钮的第 " + (itemIndex + 1) + " 项被点击");
            }

            @Override
            public void exitFinish() {
                ripple.setVisibility(View.VISIBLE);
                ripple.bringToFront();
                ripple.setEnabled(true);
            }
        });
        ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ripple.setEnabled(false);
            }
        });
        ripple.setRippleFinishListener(new RippleLayout.RippleFinishListener() {
            @Override
            public void rippleFinish(int id) {
                if (id == R.id.more2) {
                    menuView.show(WorkFragmentEnterprises.this.getContext());
                    ripple.setVisibility(View.GONE);
                }
            }
        });
    }

    private int[] getModulesIcon(String parentName) {
        int[] moduleChildIcon = {};
        if (parentName.equals(getResources().getString(R.string.module_baobiao))) {
            moduleChildIcon = moduleChildReportCheckIcon;
        } else if (parentName.equals(getResources().getString(R.string.module_anquan))) {
            moduleChildIcon = moduleChildSecurityCheckIcon;
        } else if (parentName.equals(getResources().getString(R.string.module_yinhuan))) {
            moduleChildIcon = moduleChildHiddenDangerIcon;
        } else if (parentName.equals(getResources().getString(R.string.module_shigu))) {
            moduleChildIcon = moduleChildAccidentIcon;
        } else if (parentName.equals(getResources().getString(R.string.module_weixianyuan))) {
            moduleChildIcon = moduleChildDangerIcon;
        }
        return moduleChildIcon;
    }

    private String[] getModuleNames(String parentName) {
        if(parentName == null)return null;
        String[] moduleChild = {};
        if (parentName.equals(getResources().getString(R.string.module_baobiao))) {
            moduleChild = moduleChildReportCheckNames;
        } else if (parentName.equals(getResources().getString(R.string.module_anquan))) {
            moduleChild = moduleChildSecurityCheckNames;
        } else if (parentName.equals(getResources().getString(R.string.module_yinhuan))) {
            moduleChild = moduleChildHiddenDangerNames;
        } else if (parentName.equals(getResources().getString(R.string.module_shigu))) {
            moduleChild = moduleChildAccidentNames;
        } else if (parentName.equals(getResources().getString(R.string.module_weixianyuan))) {
            moduleChild = moduleChildDangerName;
        }
        return moduleChild;

    }

    private class ModuleViewHolder extends CommonAdapter.ViewHolder {
        private int resource;
        private LinearLayout ll_module_name;
        private TextView tv_moduleName;
        private LinearLayout ll_module_child;

        public ModuleViewHolder(Context context, View itemView, ViewGroup parent) {
            super(context, itemView, parent);
            ll_module_name = (LinearLayout) itemView.findViewById(R.id.ll_module_name);
            tv_moduleName = (TextView) itemView.findViewById(R.id.tv_module_name);
            ll_module_child = (LinearLayout) itemView.findViewById(R.id.ll_module_child);


        }
    }

    private class ModuleChildViewHolder extends CommonAdapter.ViewHolder implements View.OnClickListener {

        private ImageView iv_moduleImage;
        private TextView tv_moduleName;

        public ModuleChildViewHolder(Context context, View itemView, ViewGroup parent) {
            super(context, itemView, parent);
            iv_moduleImage = (ImageView) itemView.findViewById(R.id.iv_button_icon);
            tv_moduleName = (TextView) itemView.findViewById(R.id.tv_button_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String itemTag = (String) v.getTag();
            Resources strResource = getResources();
            if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_yinhuan))) {
                intentActivity((Activity) mContext, HiddenReportForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_shigu))) {
                intentActivity((Activity) mContext, AccReportForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_anquan))) {
                intentActivity((Activity) mContext, CheckReportForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_gongzuo))) {
                intentActivity((Activity) mContext, WoasReportForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_anquan_jianchayuansu))) {//元素检查
                intentActivity((Activity) mContext, EnterprisesElementCheckListActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_anquan_xianchangjiancha))) {//现场检查
                intentActivity((Activity) mContext,EnterprisesOnSiteCheckListActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_paicha))) {
                Bundle bundle = new Bundle();
                bundle.putString("type","hidden");
                intentActivity((Activity) mContext, InvestigationEngineForEntActivity.class, false,bundle);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_zhenggai))) {
                intentActivity((Activity) mContext, InvestigationRectifyTaskForEnterpriseActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_yanshou))) {
                intentActivity((Activity) mContext, InvestigationAccepTaskForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_chaxun))) {
            } else if (itemTag.equals(getResources().getString(R.string.module_child_yinhuan_xiaohao))) {
                intentActivity((Activity) mContext, InvestigationAccepTaskForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_shigu_kuaibao))) {
                intentActivity((Activity) mContext, AccidentListForEntAcitvity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_shigu_chaxun))) {
                intentActivity((Activity) mContext, AccidentQueryListForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_xuncha))) {
                intentActivity((Activity) mContext, HazListForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_weiti))) {
                if(GlobleConstants.CJSG.equalsIgnoreCase(App.sCode) || GlobleConstants.CJFW.equalsIgnoreCase(App.sCode)
                         || GlobleConstants.CJYJ.equalsIgnoreCase(App.sCode) || GlobleConstants.CJZJ.equalsIgnoreCase(App.sCode)) {
                    intentActivity((Activity) mContext, HazSearchListForEntActivity.class, false, true);
                }else {
                    intentActivity((Activity) mContext, HazSearchListForFRActivity.class, false, true);
                }
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult
                = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        HashMap<String,String>mapValue = new HashMap<>();
        if (intentResult != null) {
            if (intentResult.getContents() == null) {

            } else {
                // scanResult 为获取到的字符串
                String scanResult = intentResult.getContents();
                if(scanResult.contains("?")){
                    String[] array  = intentResult.getContents().split("\'?");
                    if(array.length == 2){
                        scanResult = array[1];
                    }else {
                        ToastUtils.show("二维码格式错误");
                    }
                }
                String[]array =  scanResult.split("&");
                for(String item :array){
                    String[]mapArray = item.split("=");
                    if(mapArray.length == 2){
                        mapValue.put(mapArray[0],mapArray[1]);
                    }
                }
                if ("0".equals(mapValue.get("type"))) {
                    pcLogin(mapValue.get("guid").toString());
                }
                if("1".equals(mapValue.get("type").toString())){
                    // 工程二维码
                }else if("2".equals(mapValue.get("type").toString())){
                    goShare(intentResult.getContents());
                }
            }
        }else {
            ToastUtils.show("二维码识别错误");
        }
    }
    private void goShare(String url) {
        String strUrl = url.replace("","&type=2");
        Bundle bundle = new Bundle();
        String result = strUrl += "&ukey=1";
        bundle.putString("url", result);
        intentActivity(getActivity(), ProjectInfoActivity.class,
                false, bundle);
    }
    private void pcLogin(String value){
        Log.d(TAG, "授权登录中...");
        showLoadingDialog("授权登录中...");
        final String methodName = "updateRelGuidUser";
        final HashMap<String,Object> params = new HashMap<>();
        params.put("arg0", value);
        params.put("arg1", SyberosManagerImpl.getInstance().getCurrentUserInfo().getUserName());
        params.put("arg2", SyberosManagerImpl.getInstance().getCurrentUserInfo().getPassword());
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
    }
}
