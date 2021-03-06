package com.syberos.shuili.activity.dangermanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.hidden.HiddenAcceptInfo;
import com.syberos.shuili.entity.hidden.HiddenInvesInfo;
import com.syberos.shuili.entity.hidden.HiddenProjectInfo;
import com.syberos.shuili.entity.hidden.HiddenRectifyPlanInfo;
import com.syberos.shuili.entity.hidden.HiddenRectifyProgerssInfo;
import com.syberos.shuili.entity.hidden.HiddenSupserviceInfo;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomScrollView;
import com.syberos.shuili.view.MultimediaView;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-23.
 * 企事业版隐患详情
 */

public class InvestigationRectifyDetailForEnterpriseActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = InvestigationRectifyDetailForEnterpriseActivity.class.getSimpleName();
    @BindView(R.id.rl_accept_detail)
    RelativeLayout rl_accept_detail;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;
    @BindView(R.id.scrollView)
    CustomScrollView scrollView;
    /**
     * 隐患核实信息
     */
    LinearLayout ll_check_info;
    @BindView(R.id.tv_projectName)
    TextView tv_projectName;
    @BindView(R.id.tv_level)
    TextView tv_level;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_measure_info)
    TextView tv_measure_info;
    @BindView(R.id.tv_other1)
    TextView tv_other;
    @BindView(R.id.tv_other2)
    TextView tv_other2;
    @BindView(R.id.tv_other3)
    TextView tv_other3;
    @BindView(R.id.tv_other4)
    TextView tv_other4;
    @BindView(R.id.tv_other5)
    TextView tv_other5;
    ImageView iv_location;
    AudioEditView ev_des_audio;
    MultimediaView ll_multimedia;
    /**
     * 隐患督办信息
     */
    LinearLayout ll_supervise_info;
    @BindView(R.id.ev_supervise_des_audio)
    AudioEditView ev_supervise_des_audio;
    @BindView(R.id.tv_supervise_time)
    TextView tv_supervise_time;


    /**
     * 隐患整改方案
     */
    @BindView(R.id.layout_rectify_plan_info)
    LinearLayout ll_rectify_plan_info;
    @BindView(R.id.tv_unit_name)
    TextView tv_unit_name;
    @BindView(R.id.tv_rectify_plan_time)
    TextView tv_rectify_plan_time;
    @BindView(R.id.tv_rect_leg_per)
    TextView tv_rect_leg_per;
    @BindView(R.id.tv_rect_leg_per_phone)
    TextView tv_rect_leg_per_phone;
    @BindView(R.id.ev_rectify_target_audio)
    AudioEditView ev_rectify_target_audio;
    @BindView(R.id.ev_rectify_emergency_plan_audio)
    AudioEditView ev_rectify_emergency_plan_audio;
    @BindView(R.id.ev_ll_rectify_describe_audio)
    AudioEditView ev_ll_rectify_describe_audio;
    /**
     * 整改记录信息
     */
    LinearLayout ll_rectify_container;
    TextView tv_rectify_label;
    TextView tv_rectify_time;
    TextView tv_rectify_member;
    AudioEditView ev_rectify_des_audio;
    MultimediaView ll_rectify_multimedia;
    TextView tv_accept_member;
    AudioEditView ev_rectify_opinion_audio;
    MultimediaView ll_rectify_opinion_multimedia;
    TextView tv_rectify_result;


    @BindView(R.id.action_bar)
    LinearLayout ll_action_bar;
    /**
     * 接受传递过来的参数
     */
    private ObjHidden investigationInfo;
    /**
     * 核实信息
     */
    private HiddenProjectInfo hiddenProjectInfo;
    /**
     * 排查信息
     */
    private HiddenInvesInfo hiddenInvesInfo;
    /**
     * 隐患验收信息
     */
    private HiddenAcceptInfo hiddenAcceptInfo;
    /**
     * 隐患整改方案
     */
    private HiddenRectifyPlanInfo hiddenRectifyPlanInfo;
    /**
     * 隐患整改记录
     */
    private HiddenRectifyProgerssInfo hiddenRectifyProgerssInfo;
    /**
     * 隐患督办信息
     */
    private HiddenSupserviceInfo hiddenSupserviceInfo;

    private ArrayList<MultimediaView> multimediaViews = new ArrayList<>();

    private int iSucessCount = 0;
    private int iFailedCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_accep_detail_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        investigationInfo = (ObjHidden) bundle.getSerializable("data");
        if(investigationInfo == null){
            return;
        }
        multimediaViews.clear();
        BusinessConfig.getAttachMents(investigationInfo.getGuid(),"OBJ_HIDD",ll_multimedia);
        getInvestigationDetail();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ll_multimedia.cancel();
        for(MultimediaView view:multimediaViews) {
            view.cancel();
        }
    }

    @Override
    public void initView() {
        setInitActionBar(true);
        scrollView.setVisibility(View.GONE);
        ll_commit.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_action_bar2_title)).setText("隐患详情");
        (findViewById(R.id.iv_action_bar2_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        (findViewById(R.id.iv_action_bar2_right)).setVisibility(View.INVISIBLE);
        ll_check_info = rl_accept_detail.findViewById(R.id.layout_check_info);
        ll_supervise_info = rl_accept_detail.findViewById(R.id.layout_supervice_info);
        ll_supervise_info.setVisibility(View.GONE);
        ll_rectify_container = rl_accept_detail.findViewById(R.id.ll_rectify_container);
        ll_rectify_plan_info.setVisibility(View.VISIBLE);
        iv_location = ll_check_info.findViewById(R.id.iv_location);
        ev_des_audio = ll_check_info.findViewById(R.id.ev_des_audio);
        ll_multimedia = ll_check_info.findViewById(R.id.ll_multimedia);
        iv_location.setVisibility(View.GONE);
        ev_des_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ll_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_rectify_target_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_rectify_emergency_plan_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_ll_rectify_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                intentActivity(this,InvestigationAcceptFormForEntActivity.class,false,true);
                break;
        }
    }
    /**
     *隐患排查信息
     */
    private void getInvestigationDetail(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/hidd/bisHiddInves/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenInvesInfo = gson.fromJson(result,HiddenInvesInfo.class);
                if(hiddenInvesInfo == null || hiddenInvesInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(hiddenInvesInfo.dataSource.size() > 0){
                    getOrgName(hiddenInvesInfo.dataSource.get(0).getInspOrgGuid());
                }
                getRectifyProgress();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    private void getOrgName(String guid){
        String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
        HashMap<String,String>params = new HashMap<>();
            params.put("guid",guid);
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    OrgInfo orgInfo = gson.fromJson(result,OrgInfo.class);
                    if(orgInfo != null && orgInfo.dataSource != null && orgInfo.dataSource.size() > 0){
                        hiddenInvesInfo.dataSource.get(0).setUnitName(orgInfo.dataSource.get(0).getOrgName());
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {

                }
            });

    }

    /**
     * 整改进度
     */
    private void getRectifyProgress(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/bis/hidd/rect/bisHiddRectProgs/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenRectifyProgerssInfo = gson.fromJson(result,HiddenRectifyProgerssInfo.class);
                if(hiddenRectifyProgerssInfo == null || hiddenRectifyProgerssInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }

                getPersonName();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getPersonName(){
        final int size = hiddenRectifyProgerssInfo.dataSource.size();
        if(size == 0){
            getRectifyInfo();
        }else {
            for(final HiddenRectifyProgerssInfo item : hiddenRectifyProgerssInfo.dataSource){
                SyberosManagerImpl.getInstance().getPerName(item.getRecPers(), new RequestCallback<Object>() {
                    @Override
                    public void onResponse(Object result) {
                        iSucessCount ++;
                        if(result!= null) {
                            if (((SoapObject) result).getPropertySafelyAsString("persName").toString() != null) {
                                item.setRecPersName(((SoapObject) result).getPropertySafelyAsString("persName").toString());
                            }
                        }
                        if(iSucessCount + iFailedCount == size){
                            getRectifyInfo();
                        }

                    }

                    @Override
                    public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                        iFailedCount ++;
                        if(iSucessCount + iFailedCount == size){
                            getRectifyInfo();
                        }

                    }
                });
            }
        }
    }
    /**
     * 隐患督办信息
     */
    private void getSupserviceInfo(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/bis/maj/bisMajHiddSups/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenSupserviceInfo =  gson.fromJson(result,HiddenSupserviceInfo.class);
                if(hiddenSupserviceInfo == null || hiddenSupserviceInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    /**
     * 隐患整改落实信息
     */
    private void getRectifyInfo(){
        String url =  GlobleConstants.strIP +"/sjjk/v1/bis/hidd/rect/selectBisHiddRectImplWithAttOrgBase/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenRectifyPlanInfo =  gson.fromJson(result,HiddenRectifyPlanInfo.class);
                if(hiddenRectifyPlanInfo == null || hiddenRectifyPlanInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getSupserviceInfo();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void refreshUI(){
        closeDataDialog();
        scrollView.setVisibility(View.VISIBLE);
        // 工程基本信息
        tv_projectName.setText(investigationInfo.getEngName() == null ?"":investigationInfo.getEngName());
        if("1".equalsIgnoreCase(investigationInfo.getHiddGrad())) {
            tv_level.setText(R.string.normal);
        }else if("2".equalsIgnoreCase(investigationInfo.getHiddGrad())){
            tv_level.setText(R.string.danger);
        }else {
            tv_level.setText(R.string.normal);
        }
        tv_type.setText(investigationInfo.getHiddClassName() == null ?"":investigationInfo.getHiddClassName());
        tv_location.setText(investigationInfo.getProPart() == null ?"":investigationInfo.getProPart());
        ev_des_audio.setEditText(investigationInfo.getHiddDesc());
        // 隐患排查
        if("1".equals(this.hiddenInvesInfo.totalCount)) {
            hiddenInvesInfo = this.hiddenInvesInfo.dataSource.get(0);
            tv_other.setText(hiddenInvesInfo.getInspLeader());
            tv_other2.setText(hiddenInvesInfo.getInspMem());
            tv_other3.setText(hiddenInvesInfo.getUnitName());
            tv_other4.setText(hiddenInvesInfo.getRecPers());
            tv_other5.setText(hiddenInvesInfo.getCollTime());
        }
        if(this.hiddenRectifyPlanInfo.totalCount != null) {
            if (Integer.valueOf(this.hiddenRectifyPlanInfo.totalCount) > 0) {
                HiddenRectifyPlanInfo item = hiddenRectifyPlanInfo.dataSource.get(0);
                tv_unit_name.setText(item.getGoverRespWiunName());
                tv_rect_leg_per.setText(item.getRectLegPers());
                tv_rect_leg_per_phone.setText(item.getRectPersOffiTel());
                tv_rectify_plan_time.setText(item.getRequCompDate());
                ev_rectify_target_audio.setEditText(item.getGoveObjeTasks());
                ev_rectify_target_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
                ev_rectify_emergency_plan_audio.setEditText(item.getEmerPlanSame());
                ev_rectify_emergency_plan_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
                ev_ll_rectify_describe_audio.setEditText(item.getCorrMeas());
                ev_ll_rectify_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            }
        }
        //整改信息
        if(hiddenRectifyProgerssInfo.totalCount != null && Integer.valueOf(hiddenRectifyProgerssInfo.totalCount) > 0 ){
            int rectifyProcessCount = Integer.valueOf(hiddenRectifyProgerssInfo.totalCount);
            for(int i = 0 ; i < rectifyProcessCount ; i ++){
                View rectifyView = View.inflate(mContext,R.layout.activity_investigation_rectify_item,null);
                initElements(rectifyView,0);
                int index = i + 1;
                tv_rectify_label.setText(index +"次整改");
                tv_rectify_time.setText(hiddenRectifyProgerssInfo.dataSource.get(i).getCollTime());
                tv_rectify_member.setText(hiddenRectifyProgerssInfo.dataSource.get(i).getRecPersName());
                ev_rectify_des_audio.setEditText(hiddenRectifyProgerssInfo.dataSource.get(i).getRectProg());
                BusinessConfig.getAttachMents(hiddenRectifyProgerssInfo.dataSource.get(i).getHiddGuid(),"BIS_HIDD_RECT_PROG",ll_rectify_multimedia);
                multimediaViews.add(ll_rectify_multimedia);
            }


        }if(hiddenSupserviceInfo.totalCount != null && Integer.valueOf(this.hiddenSupserviceInfo.totalCount) > 0){
            ev_supervise_des_audio.setEditText(hiddenSupserviceInfo.getNote() == null ?"":hiddenSupserviceInfo.getNote());
            tv_supervise_time.setText(hiddenSupserviceInfo.getCollTime() == null ?"":hiddenSupserviceInfo.getCollTime());
        }

    }
    private void initElements(View rectifyView,int type){
        RelativeLayout rl_rectify = rectifyView.findViewById(R.id.ll_rectify);
        RelativeLayout rl_rectify_member = rectifyView.findViewById(R.id.ll_rectify_member);
        RelativeLayout rl_rectify_describe = rectifyView.findViewById(R.id.ll_rectify_describe);
        RelativeLayout rl_attachment = rectifyView.findViewById(R.id.ll_attachment);

        RelativeLayout rl_accept_member = rectifyView.findViewById(R.id.ll_accept_member);
        RelativeLayout ll_rectify_opinion_describe = rectifyView.findViewById(R.id.ll_rectify_opinion_describe);
        RelativeLayout ll_rectify_opinion_attachment = rectifyView.findViewById(R.id.ll_rectify_opinion_attachment);
        RelativeLayout ll_rectify_result = rectifyView.findViewById(R.id.ll_rectify_result) ;

        tv_rectify_label = rectifyView.findViewById(R.id.tv_rectify_label);
        tv_rectify_time  = rectifyView.findViewById(R.id.tv_rectify_time);
        tv_rectify_member = rectifyView.findViewById(R.id.tv_rectify_member);
        ev_rectify_des_audio = rectifyView.findViewById(R.id.ev_rectify_des_audio);
        ll_rectify_multimedia = rectifyView.findViewById(R.id.ll_rectify_multimedia);
        tv_accept_member = rectifyView.findViewById(R.id.tv_accept_member);
        ev_rectify_opinion_audio = rectifyView.findViewById(R.id.ev_rectify_opinion_audio);
        ll_rectify_opinion_multimedia = rectifyView.findViewById(R.id.ll_rectify_opinion_multimedia);
        ev_rectify_des_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ll_rectify_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        tv_rectify_result = rectifyView.findViewById(R.id.tv_rectify_result) ;
        ev_rectify_opinion_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ll_rectify_opinion_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_rectify_opinion_audio.setLabelText("验收意见");
        ev_rectify_opinion_audio.setEditText("");
        ll_rectify_container.addView(rectifyView);
        switch (type){
            // 整改信息
            case 0:
                break;
            // 验收信息
            case 1:
                break;
        }
    }
    private void setNetWorkError(){
        String errMsg  = ErrorInfo.ErrorCode.valueOf(-2).getMessage();
        ToastUtils.show(errMsg);

    }
}
