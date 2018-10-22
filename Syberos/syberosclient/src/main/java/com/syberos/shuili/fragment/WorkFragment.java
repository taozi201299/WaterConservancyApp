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
import com.syberos.shuili.activity.TestActivity1;
import com.syberos.shuili.activity.accident.AccidentListActivity;
import com.syberos.shuili.activity.accident.AccidentListForEntAcitvity;
import com.syberos.shuili.activity.accident.AccidentQueryListActivity;
import com.syberos.shuili.activity.accident.AccidentQueryListForEntActivity;
import com.syberos.shuili.activity.searchproject.ProjectInfoActivity;
import com.syberos.shuili.activity.stan.DataReviewListActivity;
import com.syberos.shuili.activity.stan.NoticeListActivity;
import com.syberos.shuili.activity.stan.PublicityListActivity;
import com.syberos.shuili.activity.stan.ReviewAndApprovalListActivity;
import com.syberos.shuili.activity.stan.SceneReviewListActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepTaskActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationSuperviceTaskActivity;
import com.syberos.shuili.activity.dangersource.RecordReviewListActivity;
import com.syberos.shuili.activity.dangersource.WriteOffVerificationListActivity;
import com.syberos.shuili.activity.wins.InspectProjectSelectActivity;
import com.syberos.shuili.activity.wins.OnSiteInspectListActivity;
import com.syberos.shuili.activity.woas.InspectAssessListActivity;
import com.syberos.shuili.activity.woas.SafetyProductionListActivity;
import com.syberos.shuili.activity.wins.InspectQueryListActivity;
import com.syberos.shuili.activity.suen.OnSiteLawEnforcementListActivity;
import com.syberos.shuili.activity.suen.LawEnforcementQueryActivity;
import com.syberos.shuili.activity.stan.FormalReviewListActivity;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.activity.reports.AcciReportActivity;
import com.syberos.shuili.activity.reports.HiddenReportActivity;
import com.syberos.shuili.activity.reports.WoasReportActivity;
import com.syberos.shuili.activity.reports.CheckReportActivity;
import com.syberos.shuili.activity.securitycheck.SecurityCheckQueryListActivity;
import com.syberos.shuili.activity.securitycheck.SecurityCheckTaskActivity;
import com.syberos.shuili.activity.securitycheck.SecurityHiddenTraceTaskActivity;
import com.syberos.shuili.activity.work.NotificationCenterActivity;
import com.syberos.shuili.activity.work.TodoWorkActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.amap.ShowNearlyInfoActivity;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.network.SoapUtils;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jidan on 18-3-10.
 * 办公首页 for 行政
 * 根据机构级别和角色判断哪个模块
 */

public class WorkFragment extends BaseFragment {

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;
    @BindView(R.id.ll_module)
    LinearLayout ll_module;

    private ModuleViewHolder  moduleViewHolder;
    private ModuleChildViewHolder moduleChildViewHolder;
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
                .setPrompt("将二维码/条形码放入框内，即可自动扫描")//写那句提示的话
                .setOrientationLocked(false)//扫描方向固定
                .setCaptureActivity(CustomScannerActivity.class) // 设置自定义的 activity_accident_query
                .initiateScan(); // 初始化扫描
    }
    private String[]moduleChildReportNames;
    private String[]moduleChildSecurityCheckNames;
    private String[]moduleChildHiddenDangerNames;
    private String[]moduleChildAccidentNames;
    private String[]moduleChildDangerName;
    private String[]moduleChildStandardizationNames;
    private String[]moduleChildWorkCheckNames;
    private String[]moduleChildLawEnforcementNames;
    private String[]moduleChildInspectionName;


    private int[]moduleChildReportIcon = {R.mipmap.icon_report_hidd_admin,R.mipmap.icon_report_acci_admin,
            R.mipmap.icon_report_check_admin,R.mipmap.icon_report_woad_admin};
    private int[]moduleChildSecurityCheckIcon = {R.mipmap.icon_check_admin,R.mipmap.icon_check_query_admin};
    private int[]moduleChildHiddenDangerIcon = {R.mipmap.icon_hidd_sup_admin,R.mipmap.icon_hidd_accpet_admin};
    private int[]moduleChildAccidentIcon = {R.mipmap.icon_haz_reg_admin,R.mipmap.icon_haz_cancel_admin};
    private int[]moduleChildDangerIcon = {R.mipmap.icon_acci_admin,R.mipmap.icon_acci_query_admin};
    private int[]moduleChildStandardizationIcon = {R.mipmap.icon_stan_admin1,R.mipmap.icon_stan_admin2,
            R.mipmap.icon_stan_admin3,R.mipmap.icon_stan_admin4,R.mipmap.icon_stan_admin5,R.mipmap.icon_stan_admin6};
    private int[]moduleChildWorkCheckIcon = {R.mipmap.icon_woas_admin0,R.mipmap.icon_woas_admin1};
    private int[]moduleChildLawEnforcementIcon = {R.mipmap.icon_suen_admin,R.mipmap.icon_suen_query_admin};
    private int[]moduleChildInspectionIcons = {R.mipmap.icon_inspection_admin,R.mipmap.icon_inspection_query_admin};

    private int[]moduleColor = {R.color.color_report_admin,R.color.color_check_admin, R.color.color_hidd_admin,R.color.color_acci_admin,
    R.color.color_haz_admin,R.color.color_stan_admin, R.color.color_woas_admin,R.color.color_suen_admin,R.color.color_wins_admin};

    @OnClick(R.id.iv_action_bar_left)
    void go2PersonalCenterActivityFromWorkFragment() {
        if (null != getOpenDrawerListener()) {
            getOpenDrawerListener().open();
        }
    }

    @OnClick(R.id.ll_work_todo)
    void go2workTodoActivity() {
        intentActivity(getActivity(), TodoWorkActivity.class, false, true);
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
        return R.layout.fragment_work;
    }

    @Override
    protected void initListener(){
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        tv_action_bar_title.setText(getResources().getString(R.string.title_work));
        ArrayList<String> modules = new ArrayList<>();
//        modules.add(getResources().getString(R.string.module_baobiao));
//        modules.add(getResources().getString(R.string.module_anquan));
//        modules.add(getResources().getString(R.string.module_yinhuan));
//        modules.add(getResources().getString(R.string.module_shigu));
//        modules.add(getResources().getString(R.string.module_weixianyuan));
//        modules.add(getResources().getString(R.string.module_biaozhunhua));
//        modules.add(getResources().getString(R.string.module_gongzuo));
//
//        modules.add(getResources().getString(R.string.module_anjian));
//        modules.add(getResources().getString(R.string.module_shuilijicha));
        modules.add(getResources().getString(R.string.module_baobiao));
        // 工作考核
        if(App.sCodes.contains(GlobleConstants.wins)){
            modules.add(getResources().getString(R.string.module_gongzuo));
        }
        if(App.sCodes.contains(GlobleConstants.hidd)){
            modules.add(getResources().getString(R.string.module_yinhuan));
        }
        if(App.sCodes.contains(GlobleConstants.acci)){
            modules.add(getResources().getString(R.string.module_shigu));
        }
        if(App.sCodes.contains(GlobleConstants.maha)){
            modules.add(getResources().getString(R.string.module_weixianyuan));
        }
        if(App.sCodes.contains(GlobleConstants.stan)){
            modules.add(getResources().getString(R.string.module_biaozhunhua));
        }
        if(App.sCodes.contains(GlobleConstants.sins)){
            modules.add(getResources().getString(R.string.module_anquan));
        }
        if(App.sCodes.contains(GlobleConstants.suen)){
            modules.add(getResources().getString(R.string.module_anjian));
        }if(App.sCodes.contains(GlobleConstants.wins)){
            modules.add(getResources().getString(R.string.module_shuilijicha));
        }
        moduleChildReportNames = getResources().getStringArray(R.array.module_child_baobiao);
        moduleChildSecurityCheckNames = getResources().getStringArray(R.array.module_child_anquan);
        moduleChildHiddenDangerNames = getResources().getStringArray(R.array.module_child_yinhuan);
        moduleChildAccidentNames = getResources().getStringArray(R.array.module_child_shigu);
        moduleChildDangerName = getResources().getStringArray(R.array.module_child_weixianyuan);
        moduleChildStandardizationNames = getResources().getStringArray(R.array.module_child_biaozhunhua);
        moduleChildWorkCheckNames = getResources().getStringArray(R.array.module_child_gongzuokaohe);
        moduleChildLawEnforcementNames = getResources().getStringArray(R.array.module_child_anjian);
        moduleChildInspectionName = getResources().getStringArray(R.array.module_child_shuilijicha);
        int moduleCount = modules.size();
        for(int i = 0; i< moduleCount ; i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_module_item,null);
            moduleViewHolder = new ModuleViewHolder(mContext,view,null);
            moduleViewHolder.tv_moduleName.setText(modules.get(i));
            GradientDrawable drawable = (GradientDrawable)moduleViewHolder.ll_module_name.getBackground();
            //改变drawable的背景填充色

            drawable.setColor(getResources().getColor(moduleColor[i]));

            String[]childNames = getModuleNames(modules.get(i));
            int[]childIcon = getModulesIcon(modules.get(i));
            if(childNames == null)return;
            int size = childNames.length;
            for(int j = 0 ; j < size ;j ++) {
                if(childNames[j].equals(getResources().getString(R.string.module_child_yinhuan_chaxun)) ||
                        childNames[j].equals(getResources().getString(R.string.module_child_yinhuan_heshi)) ||
                        childNames[j].equals(getResources().getString(R.string.module_child_weixianyuan_yibeian))) {
                    continue;
                }
                if(modules.contains(getResources().getString(R.string.module_gongzuo))){
                    if(childNames[j].equals(getResources().getString(R.string.module_child_baobiao_yinhuan))
                            || childNames[j].equals(getResources().getString(R.string.module_child_baobiao_shigu))
                            || childNames[j].equals(getResources().getString(R.string.module_child_baobiao_anquan)))
                    continue;
                }else {
                   if(childNames[j].equals(getResources().getString(R.string.module_child_baobiao_gongzuo))){
                        continue;
                    }
                }
                if(childNames[j].equals(getResources().getString(R.string.module_child_baobiao_anquan))){
                    continue;
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

    }

    private int[]getModulesIcon(String parentName){
        int[]moduleChildIcon = {};
        if(parentName.equals(getResources().getString(R.string.module_baobiao))){
            moduleChildIcon = moduleChildReportIcon;

        }else if(parentName.equals(getResources().getString(R.string.module_anquan))){
            moduleChildIcon = moduleChildSecurityCheckIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_yinhuan))){
            moduleChildIcon = moduleChildHiddenDangerIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_shigu))){
            moduleChildIcon = moduleChildAccidentIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_weixianyuan))){
            moduleChildIcon = moduleChildDangerIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_biaozhunhua))){
            moduleChildIcon = moduleChildStandardizationIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_gongzuo))){
            moduleChildIcon = moduleChildWorkCheckIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_anjian))){
            moduleChildIcon = moduleChildLawEnforcementIcon;
        }else if(parentName.equals(getResources().getString(R.string.module_shuilijicha))){
            moduleChildIcon = moduleChildInspectionIcons;
        }
        return moduleChildIcon;
    }
    private String[] getModuleNames(String parentName){
        String[]moduleChild = {};
        if(parentName.equals(getResources().getString(R.string.module_baobiao))){
            moduleChild = moduleChildReportNames;

        }else if(parentName.equals(getResources().getString(R.string.module_anquan))){
            moduleChild = moduleChildSecurityCheckNames;
        }else if(parentName.equals(getResources().getString(R.string.module_yinhuan))){
            moduleChild = moduleChildHiddenDangerNames;
        }else if(parentName.equals(getResources().getString(R.string.module_shigu))){
            moduleChild = moduleChildAccidentNames;
        }else if(parentName.equals(getResources().getString(R.string.module_weixianyuan))){
            moduleChild = moduleChildDangerName;
        }else if(parentName.equals(getResources().getString(R.string.module_biaozhunhua))){
            moduleChild = moduleChildStandardizationNames;
        }else if(parentName.equals(getResources().getString(R.string.module_gongzuo))){
            moduleChild = moduleChildWorkCheckNames;
        }else if(parentName.equals(getResources().getString(R.string.module_anjian))){
           moduleChild = moduleChildLawEnforcementNames;
        }else if(parentName.equals(getResources().getString(R.string.module_shuilijicha))){
           moduleChild = moduleChildInspectionName;
        }
        return moduleChild;

    }

    private class ModuleViewHolder extends CommonAdapter.ViewHolder{
        private int resource;
        private LinearLayout ll_module_name;
        private TextView tv_moduleName;
        private LinearLayout ll_module_child;
        public ModuleViewHolder(Context context, View itemView, ViewGroup parent) {
            super(context, itemView, parent);
            ll_module_name = (LinearLayout)itemView.findViewById(R.id.ll_module_name);
            tv_moduleName = (TextView)itemView.findViewById(R.id.tv_module_name);
            ll_module_child = (LinearLayout)itemView.findViewById(R.id.ll_module_child);


        }
    }
    private class ModuleChildViewHolder extends CommonAdapter.ViewHolder implements View.OnClickListener{

        private ImageView iv_moduleImage;
        private TextView tv_moduleName;

        public ModuleChildViewHolder(Context context, View itemView, ViewGroup parent) {
            super(context, itemView, parent);
            iv_moduleImage = (ImageView)itemView.findViewById(R.id.iv_button_icon);
            tv_moduleName = (TextView)itemView.findViewById(R.id.tv_button_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String itemTag = (String)v.getTag();
            Resources strResource = getResources();
            if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_yinhuan))){
                intentActivity((Activity)mContext, HiddenReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_shigu))){
                intentActivity((Activity)mContext, AcciReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_anquan))){
                intentActivity((Activity)mContext, CheckReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_gongzuo))){
                intentActivity((Activity)mContext, WoasReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_anquan_xianchangjiancha))){
                intentActivity((Activity)mContext, SecurityCheckTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_anquan_yinhuangenzong))){
                intentActivity((Activity)mContext, SecurityHiddenTraceTaskActivity.class,false,true);

            }else if(itemTag.equals(strResource.getString(R.string.module_child_anquan_jianchachaxun))){
                intentActivity((Activity)mContext, SecurityCheckQueryListActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_heshi))){
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_xiaohao))){
                intentActivity((Activity)mContext,InvestigationAccepTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_duban))){
                intentActivity((Activity)mContext,InvestigationSuperviceTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_chaxun))){
            }else if(itemTag.equals(strResource.getString(R.string.module_child_shigu_kuaibao))){
                intentActivity((Activity)mContext,AccidentListActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_shigu_chaxun))){
                intentActivity((Activity)mContext,AccidentQueryListActivity.class,false,true);
            }
            else if(itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_beian))){
                intentActivity((Activity)mContext,RecordReviewListActivity.class,false,true);
            } else if(itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_hexiao))){
                intentActivity((Activity)mContext, WriteOffVerificationListActivity.class, false, true);
            }  else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_xingshichushen))){
                intentActivity((Activity)mContext, FormalReviewListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_cailiaoshenhe))){
                intentActivity((Activity)mContext, DataReviewListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_xianchangfuhe))){
                intentActivity((Activity)mContext, SceneReviewListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_shending))){
                intentActivity((Activity)mContext, ReviewAndApprovalListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_gongshi))){
                intentActivity((Activity)mContext, PublicityListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_gonggao))){
                intentActivity((Activity)mContext, NoticeListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_chaxun))){

            }else if(itemTag.equals(strResource.getString(R.string.module_child_gongzuokaohe_anquanshengchankaohe))){
                intentActivity((Activity)mContext, SafetyProductionListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_gongzuokaohe_shuilijichakaohe))){
                intentActivity((Activity)mContext, InspectAssessListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_gongzuokaohe_chaxun))){

            }else if(itemTag.equals(strResource.getString(R.string.module_child_anjian_zhifa))){
                intentActivity((Activity)mContext, OnSiteLawEnforcementListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_anjian_diaocha))){

            }else if(itemTag.equals(strResource.getString(R.string.module_child_anjian_fucha))){

            }else if(itemTag.equals(strResource.getString(R.string.module_child_anjian_chaxun))){
                intentActivity((Activity)mContext, LawEnforcementQueryActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_shuilijicha_xianchang))){
                intentActivity((Activity)mContext,OnSiteInspectListActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_shuilijicha_wenti))){

            }else if(itemTag.equals(strResource.getString(R.string.module_child_shuilijicha_chaxun))){
                intentActivity((Activity)mContext, InspectQueryListActivity.class,
                        false, true);
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
                    goShareProject(intentResult.getContents());
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
    private void goShareProject(String url){
        String strUrl = url.replace("","&type=2");
        Bundle bundle = new Bundle();
        String str = "http://192.168.1.11:8080/cas/login?redirection=true&service=";
        String result = str + strUrl;
        result += "&"+"usn="+SyberosManagerImpl.getInstance().getCurrentUserId() +"&psd="+SyberosManagerImpl.getInstance().getCurrentUserInfo().getPassword();
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
