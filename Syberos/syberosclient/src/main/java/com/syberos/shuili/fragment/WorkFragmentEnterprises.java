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
import com.syberos.shuili.activity.accident.EnterprisesQueryAccidentListActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepTaskForEntActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationCancelForEnterprise;
import com.syberos.shuili.activity.dangermanagement.InvestigationEngineForEntActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationQueryActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationRectifyTaskForEnterpriseActivity;
import com.syberos.shuili.activity.dangersource.InspectionListForEnterpriseActivity;
import com.syberos.shuili.activity.dangersource.IssueTrackingListActivity;
import com.syberos.shuili.activity.qrcode.CustomScannerActivity;
import com.syberos.shuili.activity.reports.EnterprisesAccidentReportActivity;
import com.syberos.shuili.activity.reports.EnterprisesCheckReportActivity;
import com.syberos.shuili.activity.reports.EnterprisesHiddenDangerReportActivity;
import com.syberos.shuili.activity.reports.EnterprisesRatingReportActivity;
import com.syberos.shuili.activity.searchproject.ProjectDetailsActivity;
import com.syberos.shuili.activity.securitycheck.EnterprisesElementCheckListActivity;
import com.syberos.shuili.activity.securitycheck.EnterprisesOnSiteCheckListActivity;
import com.syberos.shuili.activity.securitycheck.SecurityCheckMapTrailsActivity;
import com.syberos.shuili.activity.work.NotificationCenterActivity;
import com.syberos.shuili.activity.work.TodoWorkActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PopupButton.ImportMenuView;
import com.syberos.shuili.view.PopupButton.RippleLayout;

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

    private int[] moduleChildReportCheckIcon = {R.mipmap.icon_action_learn, R.mipmap.icon_action_learn, R.mipmap.icon_action_learn, R.mipmap.icon_action_learn};
    private int[] moduleChildSecurityCheckIcon = {R.mipmap.icon_action_learn, R.mipmap.icon_action_learn, R.mipmap.icon_action_learn};
    private int[] moduleChildHiddenDangerIcon = {R.mipmap.icon_action_learn, R.mipmap.icon_action_learn, R.mipmap.icon_action_learn, R.mipmap.icon_action_learn};
    private int[] moduleChildAccidentIcon = {R.mipmap.icon_action_learn, R.mipmap.icon_action_learn};
    private int[] moduleChildDangerIcon = {R.mipmap.icon_action_learn, R.mipmap.icon_action_learn, R.mipmap.icon_action_learn};

    private int[] moduleColor = {R.color.button_login_pressed, R.color.duty_color,
            R.color.material_yellow_100, R.color.material_blue_grey_300, R.color.duty_color};


    @OnClick(R.id.iv_action_bar_right_1)
    void go2locationActivity() {
        ToastUtils.show("TODO：请求打开查附近功能");
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
        moduleNames = getResources().getStringArray(R.array.module_enterprise);
        moduleChildReportCheckNames = getResources().getStringArray(R.array.module_child_enterprise_baobiao);
        moduleChildSecurityCheckNames = getResources().getStringArray(R.array.module_child_enterprise_anquan);
        moduleChildHiddenDangerNames = getResources().getStringArray(R.array.module_child_enterprise_yinhuan);
        moduleChildAccidentNames = getResources().getStringArray(R.array.module_child_enterprise_shigu);
        moduleChildDangerName = getResources().getStringArray(R.array.module_child_enterprise_weixianyuan);
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
                if (childNames[j].equals(getResources().getString(R.string.module_child_yinhuan_chaxun))) {
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
                intentActivity((Activity) mContext, EnterprisesHiddenDangerReportActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_shigu))) {
                intentActivity((Activity) mContext, EnterprisesAccidentReportActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_anquan))) {
                intentActivity((Activity) mContext, EnterprisesCheckReportActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_baobiao_gongzuo))) {
                intentActivity((Activity) mContext, EnterprisesRatingReportActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_anquan_jianchayuansu))) {
                intentActivity((Activity) mContext, EnterprisesElementCheckListActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_anquan_xianchangjiancha))) {
                intentActivity((Activity) mContext,SecurityCheckMapTrailsActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_paicha))) {
                Bundle bundle = new Bundle();
                bundle.putString("type",strResource.getString(R.string.module_child_yinhuan_paicha));
                intentActivity((Activity) mContext, InvestigationEngineForEntActivity.class, false,bundle);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_zhenggai))) {
                intentActivity((Activity) mContext, InvestigationRectifyTaskForEnterpriseActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_yanshou))) {
                intentActivity((Activity) mContext, InvestigationAccepTaskForEntActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_yinhuan_chaxun))) {
                intentActivity((Activity) mContext, InvestigationQueryActivity.class, false, true);
            } else if (itemTag.equals(getResources().getString(R.string.module_child_yinhuan_xiaohao))) {
                intentActivity((Activity) mContext, InvestigationCancelForEnterprise.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_shigu_kuaibao))) {
                intentActivity((Activity) mContext, EnterpriseAccidentListAcitvity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_shigu_chaxun))) {
                intentActivity((Activity) mContext, EnterprisesQueryAccidentListActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_xuncha))) {
                intentActivity((Activity) mContext, InspectionListForEnterpriseActivity.class, false, true);
            } else if (itemTag.equals(strResource.getString(R.string.module_child_weixianyuan_weiti))) {
                intentActivity((Activity) mContext, IssueTrackingListActivity.class, false, true);
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
                    bundle.putString("url", scanResult);
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
