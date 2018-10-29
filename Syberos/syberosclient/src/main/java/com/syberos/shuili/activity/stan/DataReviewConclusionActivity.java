package com.syberos.shuili.activity.stan;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.standardization.ObjStanAppl;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.CustomEdit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 材料审核提交
 */
public class DataReviewConclusionActivity extends BaseActivity implements BaseActivity.IDialogInterface {

    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_area)
    ClearableEditText tv_area;
    @BindView(R.id.tv_person)
    ClearableEditText tv_person;
    @BindView(R.id.et_content)
    CustomEdit et_content;
    @BindView(R.id.ll_type)
    RelativeLayout ll_type;
    ObjStanAppl selectedReviewItemInformationList = null;
    @BindView(R.id.iv_action_bar2_right_search)
    ImageView ivActionBar2RightSearch;
    @BindView(R.id.tv_quitSearch)
    TextView tvQuitSearch;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_department)
    LinearLayout llDepartment;
    @BindView(R.id.rb_revi_yes)
    RadioButton rbReviYes;
    @BindView(R.id.rb_revi_no)
    RadioButton rbReviNo;
    @BindView(R.id.rb_revi_no_1)
    RadioButton rbReviNo1;
    @BindView(R.id.rl_if_site_revi)
    RelativeLayout rlIfSiteRevi;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    @BindView(R.id.tv_appr_unit)
    ClearableEditText tvApprUnit;
    @BindView(R.id.tv_appr_leader)
    ClearableEditText tvApprLeader;
    @BindView(R.id.tv_appr_leader_1)
    ClearableEditText tvApprLeader1;
    @BindView(R.id.tv_appr_startTime)
    TextView tvApprStartTime;
    @BindView(R.id.rl_appr_start)
    RelativeLayout rlApprStart;
    @BindView(R.id.rl_appr_endTime)
    RelativeLayout rl_appr_endTime;
    @BindView(R.id.tv_appr_endTime)
    TextView tvApprEndTime;
    @BindView(R.id.fl_appr_info)
    CardView fl_appr_info;
    @BindView(R.id.rg_site_revi)
    RadioGroup rg_site_revi;
    @BindView(R.id.ae_arrp_pers)
    AudioEditView ae_arrp_pers;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @OnClick(R.id.tv_commit)
    void onAcceptedClicked() {
        commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_review_conclusion;
    }

    @Override
    public void initListener() {
        setDialogInterface(this);
        ll_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeClicked(tv_time);
            }
        });
        rl_appr_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeClicked(tvApprStartTime);
            }
        });
        rl_appr_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeClicked(tvApprEndTime);
            }
        });
        rg_site_revi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fl_appr_info.setVisibility(View.GONE);
                if(checkedId == R.id.rb_revi_no){
                    fl_appr_info.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        selectedReviewItemInformationList = (ObjStanAppl) bundle.getSerializable("data");
        if (selectedReviewItemInformationList == null) {
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            finish();
        }
        tv_time.setText(selectedReviewItemInformationList.getStartTime());
        tv_area.setText(selectedReviewItemInformationList.getConfLoc());
        tv_person.setText(selectedReviewItemInformationList.getPartPers());


    }

    @Override
    public void initView() {
        setActionBarTitle("材料审查结论");
        setActionBarRightVisible(View.INVISIBLE);
        setInitActionBar(true);
        setFinishOnBackKeyDown(false);
        tv_time.setText(CommonUtils.getCurrentDate());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    DataReviewConclusionActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前内容未保存，确定退出？");
            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityFinish();
                    customDialog.dismiss();
                }
            });
            customDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 提交到标准化评审记录表
     */
    private void commitForm() {
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/informa/revi/bisInformaRevi/";
        HashMap<String, String> params = new HashMap<>();
        params.put("apprOpin", et_content.getEditableText().toString());
        params.put("startTime", tv_time.getText().toString());
        params.put("compTime", "");
        params.put("confLoc", tv_area.getText().toString());
        params.put("partPers", tv_person.getText().toString());
        if (rbReviYes.isChecked()) {
            params.put("arrpStat", "0");
        } else if (rbReviNo.isChecked()) {
            params.put("arrpStat", "1");
            params.put("scenReviOrg",tvApprUnit.getText().toString());
            params.put("scenReviSttime",tvApprStartTime.getText().toString());
            params.put("scenReviEngtime",tvApprEndTime.getText().toString());
            params.put("scenReviLeader",tvApprLeader.getText().toString());
            params.put("scenReviDepul",tvApprLeader1.getText().toString());
            params.put("scenReviMem",ae_arrp_pers.getEditText());
        } else if (rbReviNo1.isChecked()) {
            params.put("arrpStat", "2");
        }
        url += selectedReviewItemInformationList.getBisScheReviGuid() + "/" + "?";
        for (String key : params.keySet()) {
            url += key;
            url += "=";
            url += params.get(key);
            url += "&";
        }
        url = url.substring(0, url.length() - 1);
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                updateState();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void updateState(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppl/";
        HashMap<String,String> params= new HashMap<>();
        params.put("stat","3");
            url += selectedReviewItemInformationList.getGuid() +"/"+"?";
            for(String key :params.keySet()){
                url += key;
                url +="=";
                url += params.get(key);
                url += "&";
            }
            url = url.substring(0,url.length() -1);
            LocalCacheEntity localCacheEntity = new LocalCacheEntity();
            localCacheEntity.url = url;
            ArrayList<AttachMentInfoEntity>attachMentInfoEntities = new ArrayList<>();
            localCacheEntity.params = params;
            localCacheEntity.type = 1;
            localCacheEntity.commitType = 1;
            localCacheEntity.seriesKey = UUID.randomUUID().toString();
            SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeDataDialog();
                    ToastUtils.show("提交成功");
                    activityFinish();
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
    }
    void setTimeClicked(final TextView view) {
        //时间选择器
        boolean[] type = {true, true, true, true, true, true};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选时间不应大于系统当前时间");
                    return;
                }
                view.setText(Strings.formatDatetime(date));
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }
    @Override
    public void dialogClick() {
        commitForm();

    }

    @Override
    public void dialogCancel() {

    }
}
