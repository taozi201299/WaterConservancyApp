package com.syberos.shuili.activity.dangermanagement;

import android.content.Intent;
import android.os.Bundle;
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
import com.syberos.shuili.entity.hidden.HiddenInvesInfo;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.entity.hidden.HiddenProjectInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomScrollView;
import com.syberos.shuili.view.MultimediaView;

import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-21.
 */

public class InvestigationCheckDetailActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    @BindView(R.id.ev_des_audio)
    AudioEditView ev_des_audio;
    @BindView(R.id.customScrollView)
    CustomScrollView customScrollView;
    @BindView(R.id.ll_investigation_check_detail)
    LinearLayout ll_investigation_check_detail;
    /**
     * 工程详情对象
     */
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




    private final int RequestCode = 150;
    private boolean bReadOnly = false;
    private HiddenInvestigationTaskInfo investigationInfo;
    private HiddenProjectInfo hiddenProjectInfo;
    private HiddenInvesInfo hiddenInvesInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_investiagtion_check_detail_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        investigationInfo = (HiddenInvestigationTaskInfo) bundle.getSerializable("data");
        if(investigationInfo == null){
            return;
        }
        getProjectDetail();
    }

    @Override
    public void initView() {
        String title = "隐患核实";
        setActionBarRightVisible(View.INVISIBLE);
        customScrollView.setVisibility(View.GONE);
        ll_commit.setVisibility(View.GONE);
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        if(bundle != null){
            bReadOnly = bundle.getBoolean("readonly");
        }
        ll_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_des_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ev_des_audio.setLabelText("隐患描述");
        if(bReadOnly){
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(customScrollView.getLayoutParams());
            lp.setMargins(10, 150, 20, 15);
            customScrollView.setLayoutParams(lp);
            ll_commit.setVisibility(View.GONE);
            title = "隐患排查";
        }
        showTitle(title);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                startActivityForResult(new Intent(InvestigationCheckDetailActivity.this,InvestigationCheckFormActivity.class),RequestCode);
                break;
        }

    }
    private void getProjectDetail(){

        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHidds/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",investigationInfo.getHiddGuid());
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

        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/hidd/bisHiddInves/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getHiddGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenInvesInfo = (HiddenInvesInfo)gson.fromJson(result,HiddenInvesInfo.class);
                if(hiddenInvesInfo == null || hiddenInvesInfo.dataSource == null){
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
        if(!bReadOnly){
            ll_commit.setVisibility(View.VISIBLE);
        }
        if(this.hiddenInvesInfo ==null || this.hiddenProjectInfo == null)
            return;
        HiddenProjectInfo hiddenProjectInfo ;
        HiddenInvesInfo hiddenInvesInfo ;
        if(this.hiddenProjectInfo.totalCount.equals("1")) {
            hiddenProjectInfo = this.hiddenProjectInfo.dataSource.get(0);
            tv_projectName.setText(hiddenProjectInfo.getEngGuid());
            tv_level.setText(hiddenProjectInfo.getHiddGrad());
            tv_type.setText(hiddenProjectInfo.getHiddClas());
            tv_location.setText(hiddenProjectInfo.getProPart());
            tv_measure_info.setText("不存在该字段");
            ev_des_audio.setEditText(hiddenProjectInfo.getHiddDesc());
        }
        if(this.hiddenInvesInfo.totalCount.equals("1")) {
            hiddenInvesInfo = this.hiddenInvesInfo.dataSource.get(0);
            tv_other.setText(hiddenInvesInfo.getInspLeader());
            tv_other2.setText(hiddenInvesInfo.getInspMem());
            tv_other3.setText(hiddenInvesInfo.getInspOrgGuid());
            tv_other4.setText(hiddenInvesInfo.getRecPers());
            tv_other5.setText(hiddenInvesInfo.getCollTime());
        }
    }
}
