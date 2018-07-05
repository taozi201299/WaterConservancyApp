package com.syberos.shuili.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.accident.EnterpriseAccidentListAcitvity;
import com.syberos.shuili.activity.accident.EnterprisesExpressAccidentListActivity;
import com.syberos.shuili.activity.accident.EnterprisesQueryAccidentListActivity;
import com.syberos.shuili.activity.accident.ExpressAccidentListActivity;
import com.syberos.shuili.activity.accident.QueryAccidentListActivity;
import com.syberos.shuili.activity.biao_zhun_hua.DataReviewListActivity;
import com.syberos.shuili.activity.biao_zhun_hua.NoticeListActivity;
import com.syberos.shuili.activity.biao_zhun_hua.PublicityListActivity;
import com.syberos.shuili.activity.biao_zhun_hua.ReviewAndApprovalListActivity;
import com.syberos.shuili.activity.biao_zhun_hua.SceneReviewListActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepTaskActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationCheckTaskActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationQueryActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationSuperviceTaskActivity;
import com.syberos.shuili.activity.dangersource.RecordReviewListActivity;
import com.syberos.shuili.activity.dangersource.RecordedListActivity;
import com.syberos.shuili.activity.dangersource.WriteOffVerificationListActivity;
import com.syberos.shuili.activity.gong_zuo_kao_he.InspectAssessListActivity;
import com.syberos.shuili.activity.gong_zuo_kao_he.SafetyProductionListActivity;
import com.syberos.shuili.activity.inspect.InspectDetailActivity;
import com.syberos.shuili.activity.inspect.InspectQueryListActivity;
import com.syberos.shuili.activity.inspect.OnSiteInspectListActivity;
import com.syberos.shuili.activity.jian_du_zhi_fa.OnSiteLawEnforcementListActivity;
import com.syberos.shuili.activity.jian_du_zhi_fa.LawEnforcementQueryActivity;
import com.syberos.shuili.activity.biao_zhun_hua.FormalReviewListActivity;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.activity.reports.AccidentReportActivity;
import com.syberos.shuili.activity.reports.HiddenDangerReportActivity;
import com.syberos.shuili.activity.reports.JobRatingReportActivity;
import com.syberos.shuili.activity.reports.SecurityCheckReportActivity;
import com.syberos.shuili.activity.searchproject.ProjectDetailsActivity;
import com.syberos.shuili.activity.securitycheck.SecurityCheckQueryListActivity;
import com.syberos.shuili.activity.securitycheck.SecurityCheckTaskActivity;
import com.syberos.shuili.activity.securitycheck.SecurityHiddenTraceTaskActivity;
import com.syberos.shuili.activity.work.NotificationCenterActivity;
import com.syberos.shuili.activity.work.TodoWorkActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.amap.ShowNearlyInfoActivity;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jidan on 18-3-10.
 * 办公首页 for 行政
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
        ToastUtils.show("TODO：请求打开查附近功能");
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
                .setCaptureActivity(CustomScannerActivity.class) // 设置自定义的 activity
                .initiateScan(); // 初始化扫描
    }
    private String[] moduleNames;
    private String[]moduleChildReportNames;
    private String[]moduleChildSecurityCheckNames;
    private String[]moduleChildHiddenDangerNames;
    private String[]moduleChildAccidentNames;
    private String[]moduleChildDangerName;
    private String[]moduleChildStandardizationNames;
    private String[]moduleChildWorkCheckNames;
    private String[]moduleChildLawEnforcementNames;
    private String[]moduleChildInspectionName;


    private int[]moduleChildReportIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildSecurityCheckIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildHiddenDangerIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildAccidentIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildDangerIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildStandardizationIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildWorkCheckIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildLawEnforcementIcon = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};
    private int[]moduleChildInspectionIcons = {R.mipmap.icon_action_learn,R.mipmap.icon_action_learn,R.mipmap.icon_action_learn};

    private int[]moduleColor = {R.color.button_login_pressed,R.color.duty_color,R.color.material_yellow_100,R.color.material_blue_grey_300,
    R.color.button_login_pressed,R.color.duty_color,R.color.material_yellow_500,R.color.material_yellow_500,R.color.material_yellow_500};

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
        moduleNames = getResources().getStringArray(R.array.module);
        moduleChildReportNames = getResources().getStringArray(R.array.module_child_baobiao);
        moduleChildSecurityCheckNames = getResources().getStringArray(R.array.module_child_anquan);
        moduleChildHiddenDangerNames = getResources().getStringArray(R.array.module_child_yinhuan);
        moduleChildAccidentNames = getResources().getStringArray(R.array.module_child_shigu);
        moduleChildDangerName = getResources().getStringArray(R.array.module_child_weixianyuan);
        moduleChildStandardizationNames = getResources().getStringArray(R.array.module_child_biaozhunhua);
        moduleChildWorkCheckNames = getResources().getStringArray(R.array.module_child_gongzuokaohe);
        moduleChildLawEnforcementNames = getResources().getStringArray(R.array.module_child_anjian);
        moduleChildInspectionName = getResources().getStringArray(R.array.module_child_shuilijicha);
        int moduleCount = moduleNames.length;
        for(int i = 0; i< moduleCount ; i++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_module_item,null);
            moduleViewHolder = new ModuleViewHolder(mContext,view,null);
            moduleViewHolder.tv_moduleName.setText(moduleNames[i]);
            GradientDrawable drawable = (GradientDrawable)moduleViewHolder.ll_module_name.getBackground();
            //改变drawable的背景填充色

            drawable.setColor(getResources().getColor(moduleColor[i]));

            String[]childNames = getModuleNames(moduleNames[i]);
            int[]childIcon = getModulesIcon(moduleNames[i]);
            if(childNames == null)return;
            int size = childNames.length;
            for(int j = 0 ; j < size ;j ++) {
                if(childNames[j].equals(getResources().getString(R.string.module_child_yinhuan_chaxun)) ||
                        childNames[j].equals(getResources().getString(R.string.module_child_yinhuan_heshi)) ||
                        childNames[j].equals(getResources().getString(R.string.module_child_weixianyuan_yibeian))) {
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
                intentActivity((Activity)mContext, HiddenDangerReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_shigu))){
                intentActivity((Activity)mContext, AccidentReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_anquan))){
                intentActivity((Activity)mContext, SecurityCheckReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_baobiao_gongzuo))){
                intentActivity((Activity)mContext, JobRatingReportActivity.class,
                        false, true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_anquan_xianchangjiancha))){
                intentActivity((Activity)mContext, SecurityCheckTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_anquan_yinhuangenzong))){
                intentActivity((Activity)mContext, SecurityHiddenTraceTaskActivity.class,false,true);

            }else if(itemTag.equals(strResource.getString(R.string.module_child_anquan_jianchachaxun))){
                intentActivity((Activity)mContext, SecurityCheckQueryListActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_heshi))){
                intentActivity((Activity) mContext,InvestigationCheckTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_xiaohao))){
                intentActivity((Activity)mContext,InvestigationAccepTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_duban))){
                intentActivity((Activity)mContext,InvestigationSuperviceTaskActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_yinhuan_chaxun))){
                intentActivity((Activity)mContext, InvestigationQueryActivity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_shigu_kuaibao))){
                intentActivity((Activity)mContext,EnterpriseAccidentListAcitvity.class,false,true);
            }else if(itemTag.equals(strResource.getString(R.string.module_child_shigu_chaxun))){
                intentActivity((Activity)mContext,EnterprisesQueryAccidentListActivity.class,false,true);
            }
            else if(itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_beian))){
                intentActivity((Activity)mContext,RecordReviewListActivity.class,false,true);
            } else if(itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_hexiao))){
                intentActivity((Activity)mContext, WriteOffVerificationListActivity.class, false, true);
            } else if(itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_yibeian))){
                intentActivity((Activity)mContext, RecordedListActivity.class, false, true);
            } else if(itemTag.equals(strResource.getString(R.string.module_child_biaozhunhua_xingshichushen))){
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
                intentActivity((Activity)mContext, OnSiteInspectListActivity.class,
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
        if (intentResult != null) {
            if (intentResult.getContents() == null) {

            } else {
                // scanResult 为获取到的字符串
                String scanResult = intentResult.getContents();

                Bundle bundle = new Bundle();
                if (Strings.isValidUrl(scanResult)) {
                } else {
                    // TODO: 18-3-22 test show project details
                    bundle.putString(ProjectDetailsActivity.RESULT_UNIT,
                            "南京市河道管理处");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_NAME,
                            "长江南京新济州河段河道整治工程");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_CODE,
                            "1220000008881");
                    bundle.putString(ProjectDetailsActivity.RESULT_PARENT_UNIT,
                            "南京市水务局");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_STATUS,
                            "在建工程");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_START,
                            "2014年01月");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_END,
                            "2018年6月");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_PRICE,
                            "800万元");
                    bundle.putString(ProjectDetailsActivity.RESULT_PROJECT_IMPORTANCE,
                            "市重点");

                    intentActivity(getActivity(), ProjectDetailsActivity.class,
                            false, bundle);
                }

                ToastUtils.show("Scan Result: " + scanResult);
            }
        }
    }

}
