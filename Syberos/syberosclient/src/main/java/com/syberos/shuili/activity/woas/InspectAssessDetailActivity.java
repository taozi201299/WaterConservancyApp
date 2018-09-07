package com.syberos.shuili.activity.woas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.syberos.shuili.entity.woas.BisWoasStaff;
import com.syberos.shuili.entity.woas.WoasRecordCount;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;


import java.util.HashMap;

import butterknife.BindView;

/**
 * 考核组详情  水利稽查
 */
public class InspectAssessDetailActivity extends BaseActivity  implements View.OnClickListener {
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

    @BindView(R.id.rl_prob_count)
    RelativeLayout rl_prob_count;
    @BindView(R.id.tv_problem_count)
    TextView tv_problem_count;

    WoasRecordCount woasRecordCount ;
    /**
     * 考核成员对象
     */
    private BisWoasStaff bisWoasStaff;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_detail;
    }

    @Override
    public void initListener() {
        rl_prob_count.setOnClickListener(this);
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
        params.put("guid",info.getAssePlanGuid());
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
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/staff/bisWoasStaffs/";
        HashMap<String,String> params = new HashMap<>();
        params.put("woasGropGuid",info.getGuid());
        params.put("woasGropGuid","7a967a72a577495dacc07d2525df97cd");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWoasStaff = gson.fromJson(result,BisWoasStaff.class);
                if(bisWoasStaff == null || bisWoasStaff.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getRecordCount();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
    /**
     * 获取考核对象
     */
    private void getWoasObj(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/obj/bisWoasObjs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("woasGroupGuid",info.getGuid());
        params.put("woasGroupGuid","7a967a72a577495dacc07d2525df97cd");
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
                    getPersonInfo();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }
    private void getRecordCount(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/deuc/selectInspectionPointsRecordCount/";
        HashMap<String,String>params = new HashMap<>();
        params.put("woasGropGuid",info.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                woasRecordCount = gson.fromJson(result,WoasRecordCount.class);
                if(woasRecordCount == null || woasRecordCount.getData() == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                refreshUI();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
    private void refreshUI() {
        BisWoasProg data = bisWoasProg.dataSource.get(0);
        // 所属考核方案
        tv_check_plan.setText(data.getProgName());
        // 考核时间
        tv_check_time.setText(data.getStartTime() + "--" + data.getEndTime());
        // 考核内容
        tv_check_content.setText("");
        // 组长名称
        tv_group_leader.setText("");
        // 组长单位
        tv_group_unit.setText(info.getLeadWiun());
        if(bisWoasStaff.dataSource.size() > 0) {
            String unit = "";
            for(BisWoasStaff bisWoasStaff :bisWoasStaff.dataSource){
                unit += bisWoasStaff.getOrgName();
                unit +=" ";
            }
            // 组员单位
            tv_member_unit.setText(unit);
        }
        // 专家姓名
        tv_check_person.setText("");
        if(woasRecordCount.getData().size() > 0) {
            tv_problem_count.setText(woasRecordCount.getData().get(0));
        }
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_prob_count:
                Bundle bundle = new Bundle();
                bundle.putSerializable("bisWoasObj",bisWoasObj);
                bundle.putSerializable("bisWoasGrop",info);
                intentActivity(InspectAssessDetailActivity.this,SafetyProductionDeductMarkListActivity.class,false,bundle);

                break;
        }
    }

}
