package com.syberos.shuili.activity.woas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.woas.BisWoasGrop;
import com.syberos.shuili.entity.woas.BisWoasObj;
import com.syberos.shuili.entity.woas.BisWoasProg;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;


import java.util.HashMap;

import butterknife.BindView;

/**
 * 考核组详情  水利稽查
 */
public class InspectAssessDetailActivity extends BaseActivity {
    public static final String SEND_BUNDLE_KEY_0 = "InspectAssessPlanInfo";
    public static final String SEND_BUNDLE_KEY_1 = "DeductMarksInfo";

    private BisWoasGrop info;
    /**
     * 考核组方案
     */
    private BisWoasProg bisWoasProg = null;

    /**
     * 考核对象
     */
    private BisWoasObj bisWoasObj = null;

    @BindView(R.id.tv_check_plan)
    TextView tv_check_plan;

    @BindView(R.id.tv_check_time)
    TextView tv_check_time;

    @BindView(R.id.tv_check_content)
    TextView tv_check_content;

    @BindView(R.id.tv_group_leader)
    TextView tv_group_leader;

    @BindView(R.id.tv_group_unit)
    TextView tv_group_unit;

    @BindView(R.id.tv_member_unit)
    TextView tv_member_unit;

    @BindView(R.id.tv_check_person)
    TextView tv_check_person;

    @BindView(R.id.ll_container_0)
    LinearLayout ll_container_0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
    @Override
    public void initView() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        info = (BisWoasGrop) bundle.getSerializable(
                InspectAssessListActivity.SEND_BUNDLE_KEY);
        if (null != info) {
            setActionBarTitle(info.getWoasGropName());
            setActionBarRightVisible(View.INVISIBLE);
        }
        getWoasGroupInfo();
    }

    /**
     * 根据方案guid获取稽查组对应方案的信息
     */
    private void getWoasGroupInfo(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/woas/prog/selectSingleAssessmentPlan/";
        HashMap<String,String>params = new HashMap<>();
      //  params.put("guid",info.getAssePlanGuid());
        params.put("guid","9C1664EFC34B4EFEAB0CE91F86465284");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWoasProg = gson.fromJson(result,BisWoasProg.class);
                if(bisWoasProg == null || bisWoasProg.dataSource == null || bisWoasProg.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
                getWoasObj();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
    /**
     * 获取组员信息和专家信息
     */
    private void getPersonInfo(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/woas/staff/bisWoasStaffs/";
    }
    /**
     * 获取考核对象
     */
    private void getWoasObj(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/obj/bisWoasObjs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("woasGroupGuid",info.getGuid());
        params.put("woasGroupGuid","10503b7348f9401588428a546e18bfce");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisWoasObj = gson.fromJson(result,BisWoasObj.class);
                if(bisWoasObj == null || bisWoasObj.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(bisWoasObj.dataSource.size() == 0){
                    ToastUtils.show("未获取到考核组信息");
                }else {
                    refreshUI();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }

    private void refreshUI() {
        // 所属考核方案
        tv_check_plan.setText(bisWoasProg.getPROGNAME());
        // 考核时间
        tv_check_time.setText(bisWoasProg.getWOASSTARTIME() + "--" + bisWoasProg.getWOASDEADLINE());
        // 考核内容
        tv_check_content.setText("");
        // 组长名称
        tv_group_leader.setText("");
        // 组长单位
        tv_group_unit.setText("");
        // 组员单位
        tv_member_unit.setText("");
        // 专家姓名
        tv_check_person.setText("");
        for (final BisWoasObj objectInfo : bisWoasObj.dataSource) {
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.activity_safety_production_detail_check_item, null);

            ((TextView) view.findViewById(R.id.tv_item_name)).setText(objectInfo.getWoasWiunName());

            TextView btn = view.findViewById(R.id.iv_arrow_right);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY_0, objectInfo);
                    intentActivity(InspectAssessDetailActivity.this,
                            InspectAssessDetailRealSituationActivity.class,
                            false, bundle);
                }
            });
            ll_container_0.addView(view);
        }
    }

}
