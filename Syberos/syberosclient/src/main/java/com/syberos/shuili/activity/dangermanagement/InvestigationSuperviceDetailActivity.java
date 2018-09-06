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
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.hidden.HiddenAcceptInfo;
import com.syberos.shuili.entity.hidden.HiddenInvesInfo;
import com.syberos.shuili.entity.hidden.HiddenProjectInfo;
import com.syberos.shuili.entity.hidden.HiddenRectifyPlanInfo;
import com.syberos.shuili.entity.hidden.HiddenRectifyProgerssInfo;
import com.syberos.shuili.entity.hidden.HiddenSupervice;
import com.syberos.shuili.entity.hidden.HiddenSupserviceInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomScrollView;
import com.syberos.shuili.view.MultimediaView;

import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-23.
 * 隐患详情信息，包含隐患核实信息 排查信息 整改方案 督办记录 整改记录 验收记录
 */

public class InvestigationSuperviceDetailActivity extends TranslucentActivity implements View.OnClickListener {
    private final String TAG = InvestigationSuperviceDetailActivity.class.getSimpleName();
    @BindView(R.id.rl_accept_detail)
    RelativeLayout rl_accept_detail;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    @BindView(R.id.customScrollView)
    CustomScrollView customScrollView;
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

    /**
     * 隐患整改方案
     */
    LinearLayout ll_rectify_plan_info;
    @BindView(R.id.tv_unit_name)
    TextView tv_unit_name;
    @BindView(R.id.tv_rectify_plan_time)
    TextView tv_rectify_plan_time;
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
    private HiddenSupervice investigationInfo;
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


    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_supervise_detail_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        investigationInfo = (HiddenSupervice) bundle.getSerializable("data");
        if(investigationInfo == null){
            return;
        }
        getProjectDetail();

    }

    @Override
    public void initView() {
        showDataLoadingDialog();
        customScrollView.setVisibility(View.GONE);
        ll_commit.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_action_bar2_title)).setText("隐患督办");
        ((ImageView)findViewById(R.id.iv_action_bar2_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.iv_action_bar2_right)).setVisibility(View.INVISIBLE);
        ll_check_info = (LinearLayout)rl_accept_detail.findViewById(R.id.layout_check_info);
        ll_supervise_info = (LinearLayout)rl_accept_detail.findViewById(R.id.layout_supervice_info);
        ll_supervise_info.setVisibility(View.GONE);
        ll_rectify_container = (LinearLayout)rl_accept_detail.findViewById(R.id.ll_rectify_container);
        iv_location = (ImageView)ll_check_info.findViewById(R.id.iv_location);
        ev_des_audio = (AudioEditView)ll_check_info.findViewById(R.id.ev_des_audio);
        ll_multimedia = (MultimediaView)ll_check_info.findViewById(R.id.ll_multimedia);
        iv_location.setVisibility(View.GONE);
        ev_des_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ll_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                ((TranslucentActivity)mContext).showTranslucentDialog(R.layout.activity_investigation_supervise_form).showShareView();
                ((TranslucentActivity)mContext).setbtnClicked(new IbtnClicked() {
                    @Override
                    public void onBtnClicked(int type) {
                        switch (type){
                            case 0:
                                // TODO: 18-3-26 cancel 
                                break;
                            case 1:
                                // TODO: 18-3-26 confirm
                                break;
                        }
                    }
                });
                break;
        }
    }
    private void getProjectDetail(){

        String url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/objHidds/";
        HashMap<String,String> params = new HashMap<>();
        params.put("guid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenProjectInfo = (HiddenProjectInfo)gson.fromJson(result,HiddenProjectInfo.class);
                if(hiddenProjectInfo == null || hiddenProjectInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getInvestigationDetail();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getInvestigationDetail(){

        String url = "http://192.168.1.8:8080/sjjk/v1/bis/hidd/bisHiddInves/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenInvesInfo = (HiddenInvesInfo)gson.fromJson(result,HiddenInvesInfo.class);
                if(hiddenInvesInfo == null || hiddenInvesInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
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
    private void getRectifyProgress(){
       String url =  "http://192.168.1.8:8080/sjjk/v1/bis/hidd/rect/bisHiddRectProgs/";
       HashMap<String,String>params = new HashMap<>();
       params.put("hiddGuid",investigationInfo.getGuid());
       SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
           @Override
           public void onResponse(String result) {
               Gson gson = new Gson();
               hiddenRectifyProgerssInfo = (HiddenRectifyProgerssInfo)gson.fromJson(result,HiddenRectifyProgerssInfo.class);
               if(hiddenRectifyProgerssInfo == null || hiddenRectifyProgerssInfo.dataSource ==  null){
                   closeDataDialog();
                   ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                   return;
               }
               getRectifyInfo();
           }

           @Override
           public void onFailure(ErrorInfo.ErrorCode errorInfo) {
               closeDataDialog();
               ToastUtils.show(errorInfo.getMessage());

           }
       });
    }
    private void getSupserviceInfo(){
        String url =  "http://192.168.1.8:8080/sjjk/v1/bis/maj/bisMajHiddSups/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenSupserviceInfo = (HiddenSupserviceInfo) gson.fromJson(result,HiddenSupserviceInfo.class);
               if(hiddenSupserviceInfo == null || hiddenSupserviceInfo.dataSource == null){
                   closeDataDialog();
                   ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                   return;
               }
                getRectifyAcceptInfo();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getRectifyInfo(){
        String url =  "http://192.168.1.8:8080/sjjk/v1/bis/hidd/rect/bisHiddRectImpls/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenRectifyPlanInfo = (HiddenRectifyPlanInfo) gson.fromJson(result,HiddenRectifyPlanInfo.class);
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
    private void getRectifyAcceptInfo(){
        String url =  "http://192.168.1.8:8080/sjjk/v1/bis/hidd/rect/bisHiddRectAcces/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenAcceptInfo = (HiddenAcceptInfo) gson.fromJson(result,HiddenAcceptInfo.class);
                if(hiddenAcceptInfo == null || hiddenAcceptInfo.dataSource == null){
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
    private void refreshUI(){
        customScrollView.setVisibility(View.VISIBLE);
        // 1 隐患核实信息
        if(this.hiddenProjectInfo.totalCount.equals("1")) {
            hiddenProjectInfo = this.hiddenProjectInfo.dataSource.get(0);
            tv_projectName.setText(hiddenProjectInfo.getEngGuid());
            tv_level.setText(hiddenProjectInfo.getHiddGrad());
            tv_type.setText(hiddenProjectInfo.getHiddClas());
            tv_location.setText(hiddenProjectInfo.getProPart());
            tv_measure_info.setText("");
            ev_des_audio.setEditText(hiddenProjectInfo.getHiddDesc());
        }
        // 隐患排查
        if(this.hiddenInvesInfo.totalCount.equals("1")) {
            hiddenInvesInfo = this.hiddenInvesInfo.dataSource.get(0);
            tv_other.setText(hiddenInvesInfo.getInspLeader());
            tv_other2.setText(hiddenInvesInfo.getInspMem());
            tv_other3.setText(hiddenInvesInfo.getInspOrgGuid());
            tv_other4.setText(hiddenInvesInfo.getRecPers());
            tv_other5.setText(hiddenInvesInfo.getCollTime());
        }
        if(Integer.valueOf(this.hiddenRectifyPlanInfo.totalCount)> 0){
            tv_unit_name.setText("");
            tv_rectify_plan_time.setText("");
            ev_rectify_target_audio.setEditText("");
            ev_rectify_emergency_plan_audio.setEditText("");
            ev_ll_rectify_describe_audio.setEditText("");
        }
        //整改信息
        int rectifyProcessCount = Integer.valueOf(hiddenRectifyProgerssInfo.totalCount);
        int acceptCount = Integer.valueOf(hiddenAcceptInfo.totalCount);
        if(rectifyProcessCount > 0){
            for(int i = 0 ; i < rectifyProcessCount ; i ++){
                View rectifyView = View.inflate(mContext,R.layout.activity_investigation_rectify_item,null);
                initElements(rectifyView,0);
                int index = i + 1;
                tv_rectify_label.setText(index +"次整改");
                tv_rectify_time.setText(hiddenRectifyProgerssInfo.getCollTime());
                tv_rectify_member.setText("");
                ev_rectify_des_audio.setEditText(hiddenRectifyProgerssInfo.getNote());
            }


        }
        // 验收信息
        if(acceptCount > 0){
            for(int i = 0 ; i < rectifyProcessCount ; i ++){
                View rectifyView = View.inflate(mContext,R.layout.activity_investigation_rectify_item,null);
                initElements(rectifyView,0);
                int index = i +1;
                tv_rectify_label.setText(index +"次验收");
                tv_accept_member.setText("");
                ev_rectify_opinion_audio.setEditText(hiddenAcceptInfo.getNote());
            }
        }if(Integer.valueOf(this.hiddenSupserviceInfo.totalCount) > 0){

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
        ev_rectify_opinion_audio.setEditText("核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述");
        ll_rectify_container.addView(rectifyView);
        switch (type){
            // 整改信息
            case 0:
                rl_accept_member.setVisibility(View.GONE);
                ll_rectify_opinion_describe.setVisibility(View.GONE);
                ll_rectify_opinion_attachment.setVisibility(View.GONE);
                ll_rectify_result.setVisibility(View.GONE);
                break;
            // 验收信息
            case 1:
                rl_rectify.setVisibility(View.GONE);
                rl_rectify_member.setVisibility(View.GONE);
                rl_rectify_describe.setVisibility(View.GONE);
                rl_attachment.setVisibility(View.GONE);
                break;
        }
    }
    private void setNetWorkError(){
        String errMsg  = ErrorInfo.ErrorCode.valueOf(-2).getMessage();
        ToastUtils.show(errMsg);

    }
}
