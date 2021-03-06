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
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
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

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-23.
 * 隐患详情activity
 */

public class InvestigationAccepDetailActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = InvestigationAcceptFormForEntActivity.class.getSimpleName();
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
    private ArrayList<MultimediaView>multimediaViews = new ArrayList<>();
    int iSucessCount = 0;
    int iFailedCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_accep_detail_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ll_multimedia.cancel();
        for(MultimediaView view: multimediaViews){
            view.cancel();
        }
    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        investigationInfo = (ObjHidden) bundle.getSerializable("data");
        if(investigationInfo == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            finish();
        }
        multimediaViews.clear();
        BusinessConfig.getAttachMents(investigationInfo.getGuid(),"OBJ_HIDD",ll_multimedia);
        if("10".equals(investigationInfo.getHiddGrad())) {
            getHiddenCheckDetail();
        }else {
            getInvestigationDetail();
        }


    }

    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarTitle("隐患详情");
        scrollView.setVisibility(View.GONE);
        ll_commit.setVisibility(View.GONE);
        setActionBarRightVisible(View.INVISIBLE);
        (findViewById(R.id.iv_action_bar2_right)).setVisibility(View.INVISIBLE);
        ll_check_info = rl_accept_detail.findViewById(R.id.layout_check_info);
        ll_supervise_info = rl_accept_detail.findViewById(R.id.layout_supervice_info);
        ll_supervise_info.setVisibility(View.GONE);
        ll_rectify_container = rl_accept_detail.findViewById(R.id.ll_rectify_container);
        iv_location = ll_check_info.findViewById(R.id.iv_location);
        ev_des_audio = ll_check_info.findViewById(R.id.ev_des_audio);
        ll_multimedia = ll_check_info.findViewById(R.id.ll_multimedia);
        iv_location.setVisibility(View.GONE);
        ev_des_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ll_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);



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
     * 重大隐患核实信息
     */
    private void getHiddenCheckDetail(){

        String url = GlobleConstants.strIP +"/sjjk/v1/bis/hidd/bisHiddVeris/";
        urlTags.add(url);
        HashMap<String,String> params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenProjectInfo = (HiddenProjectInfo)gson.fromJson(result,HiddenProjectInfo.class);
                if(hiddenProjectInfo == null || hiddenProjectInfo.dataSource == null ){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getInvestigationDetail();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    /**
     *隐患排查信息
     */
    private void getInvestigationDetail(){

        String url = GlobleConstants.strIP +"/sjjk/v1/bis/hidd/bisHiddInves/";
        urlTags.add(url);
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
                if(hiddenInvesInfo.dataSource.size() > 0) {
                    getUnitNameByOrgID(hiddenInvesInfo.dataSource.get(0).getInspOrgGuid());
                }else {
                    getRectifyProgress();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    /**
     * 排查单位名称
     */
    private void getUnitNameByOrgID(String id){
        String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",id);
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                AttOrgBase attOrgBase  = gson.fromJson(result,AttOrgBase.class);
                if(attOrgBase == null || attOrgBase.dataSource == null || attOrgBase.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                hiddenInvesInfo.dataSource.get(0).setUnitName(attOrgBase.dataSource.get(0).getOrgName());
                getRectifyProgress();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    /**
     * 整改进度
     */
    private void getRectifyProgress(){
        String url =  GlobleConstants.strIP +"/sjjk/v1/bis/hidd/rect/bisHiddRectProgs/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenRectifyProgerssInfo = gson.fromJson(result,HiddenRectifyProgerssInfo.class);
                if(hiddenRectifyProgerssInfo == null || hiddenRectifyProgerssInfo.dataSource == null ){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getPersonName();
                }
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
                hiddenSupserviceInfo = gson.fromJson(result,HiddenSupserviceInfo.class);
                if(hiddenSupserviceInfo == null || hiddenSupserviceInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
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
    /**
     * 隐患整改信息
     */
    private void getRectifyInfo(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/hidd/rect/bisHiddRectImpls/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenRectifyPlanInfo =  gson.fromJson(result,HiddenRectifyPlanInfo.class);
               if(hiddenRectifyPlanInfo == null || hiddenRectifyPlanInfo.dataSource == null ){
                   closeDataDialog();
                   ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
               }else {
                   getSupserviceInfo();
               }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                getSupserviceInfo();
            }
        });
    }
    /**
     * 隐患验收信息
     */
    private void getRectifyAcceptInfo(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/bis/hidd/rect/bisHiddRectAcces/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenAcceptInfo = (gson.fromJson(result,HiddenAcceptInfo.class));
                if(hiddenAcceptInfo == null || hiddenAcceptInfo.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
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
    private void refreshUI(){
        closeDataDialog();
        scrollView.setVisibility(View.VISIBLE);
        // 工程基本信息
        tv_projectName.setText(investigationInfo.getEngName());
        if ("1".equals(investigationInfo.getHiddGrad())) {
            investigationInfo.setHiddGradName("一般隐患");

        } else if ("2".equals(investigationInfo.getHiddGrad())) {
            investigationInfo.setHiddGradName("重大隐患");
        }else{
            investigationInfo.setHiddGradName("一般隐患");
        }
        tv_level.setText(investigationInfo.getHiddGradName());
        tv_type.setText(investigationInfo.getHiddClassName());
        tv_location.setText(investigationInfo.getProPart());
        ev_des_audio.setEditText(investigationInfo.getHiddDesc());
//        // 1 隐患核实信息
//        if("1".equals(this.hiddenProjectInfo.totalCount)) {
//            hiddenProjectInfo = this.hiddenProjectInfo.dataSource.get(0);
//
//            tv_measure_info.setText("不存在该字段");
//            ev_des_audio.setEditText(hiddenProjectInfo.getHiddDesc());
//        }
        // 隐患排查
        if(hiddenInvesInfo != null && "1".equals(this.hiddenInvesInfo.totalCount)) {
            hiddenInvesInfo = this.hiddenInvesInfo.dataSource.get(0);
            tv_other.setText(hiddenInvesInfo.getInspLeader());
            tv_other2.setText(hiddenInvesInfo.getInspMem());
            tv_other3.setText(hiddenInvesInfo.getUnitName());
            tv_other4.setText(hiddenInvesInfo.getRecPers());
            tv_other5.setText(hiddenInvesInfo.getInspDate());
        }
        if(hiddenRectifyPlanInfo != null && Integer.valueOf(this.hiddenRectifyPlanInfo.totalCount)> 0){
            HiddenRectifyPlanInfo info = hiddenRectifyPlanInfo.dataSource.get(0);
            tv_unit_name.setText(info.getGoverRespWiunName());
            tv_rectify_plan_time.setText(info.getCollTime());
            ev_rectify_target_audio.setEditText("");
            ev_rectify_emergency_plan_audio.setEditText(info.getEmerPlanSame());
            ev_ll_rectify_describe_audio.setEditText("");
        }
        //整改信息
        if(hiddenRectifyProgerssInfo != null && hiddenRectifyProgerssInfo.totalCount != null && Integer.valueOf(hiddenRectifyProgerssInfo.totalCount) > 0 ){
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
        multimediaViews.add(ll_rectify_multimedia);
        tv_rectify_result = rectifyView.findViewById(R.id.tv_rectify_result) ;
        ev_rectify_opinion_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ll_rectify_opinion_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        multimediaViews.add(ll_rectify_opinion_multimedia);
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
}
