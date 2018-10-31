package com.syberos.shuili.activity.stan;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.standardization.BisStanReviRec;
import com.syberos.shuili.entity.standardization.ObjStanAppl;
import com.syberos.shuili.entity.standardization.ReviewItemInformation;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CheckableButton;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

public class ReviewAndApprovalDetailActivity extends BaseActivity implements BaseActivity.IDialogInterface{

    private ObjStanAppl reviewItemInformation = null;
    private int currentLevel = ReviewItemInformation.LEVEL_3;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @BindView(R.id.cb_level_0)
    CheckableButton cb_level_0;

    @BindView(R.id.cb_level_1)
    CheckableButton cb_level_1;

    @BindView(R.id.cb_level_2)
    CheckableButton cb_level_2;

    @BindView(R.id.cb_level_3)
    CheckableButton cb_level_3;

    @BindView(R.id.rb_appr_yes)
    RadioButton rb_appr_yes;
    @BindView(R.id.rb_appr_no)
    RadioButton rb_appr_no;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @OnClick(R.id.cb_level_0)
    void onCheckableButton0Clicked() {
        if (!cb_level_0.isChecked()) {
            clearAllChecked();
            cb_level_0.setChecked(true);
            currentLevel = ReviewItemInformation.LEVEL_0;
        }
    }

    @OnClick(R.id.cb_level_1)
    void onCheckableButton1Clicked() {
        if (!cb_level_1.isChecked()) {
            clearAllChecked();
            cb_level_1.setChecked(true);
            currentLevel = ReviewItemInformation.LEVEL_1;
        }
    }

    @OnClick(R.id.cb_level_2)
    void onCheckableButton2Clicked() {
        if (!cb_level_2.isChecked()) {
            clearAllChecked();
            cb_level_2.setChecked(true);
            currentLevel = ReviewItemInformation.LEVEL_2;
        }
    }

    @OnClick(R.id.cb_level_3)
    void onCheckableButton3Clicked() {
        if (!cb_level_3.isChecked()) {
            clearAllChecked();
            cb_level_3.setChecked(true);
            currentLevel = ReviewItemInformation.LEVEL_3;
        }
    }

    @OnClick(R.id.tv_ok)
    void onSubmitClicked() {
        commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scene_review_detail;
    }

    @Override
    public void initListener() {
        setDialogInterface(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setFinishOnBackKeyDown(false);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        reviewItemInformation = (ObjStanAppl) bundle.getSerializable("data");
        if (null != reviewItemInformation) {
            currentLevel = Integer.valueOf(reviewItemInformation.getApplGrade());
            switch (currentLevel) {
                case ReviewItemInformation.LEVEL_0:
                    cb_level_0.setChecked(true);
                    break;
                case ReviewItemInformation.LEVEL_1:
                    cb_level_1.setChecked(true);
                    break;
                case ReviewItemInformation.LEVEL_2:
                    cb_level_2.setChecked(true);
                    break;
                default:
                    cb_level_3.setChecked(true);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.onActivityResult(requestCode, requestCode, data);
    }

    private void clearAllChecked() {
        cb_level_0.setChecked(false);
        cb_level_1.setChecked(false);
        cb_level_2.setChecked(false);
        cb_level_3.setChecked(false);
    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    ReviewAndApprovalDetailActivity.this);
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
     * 提交到现场检查审批表（水利部）
     */
    private void  commitForm(){
        showDataLoadingDialog("数据提交中...");
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/conf/examappr/bisConfExamappr";
        HashMap<String,String> params= new HashMap<>();
        params.put("apprOpin",ae_describe_audio.getEditText());
        if(rb_appr_yes.isChecked()){
            params.put("apprStat","0");
        }else if(rb_appr_no.isChecked()){
            params.put("apprStat","1");
        }
        url += reviewItemInformation.getBisScheReviGuid() +"/"+"?";
        for(String key :params.keySet()){
            url += key;
            url +="=";
            url += params.get(key);
            url += "&";
        }
        url = url.substring(0,url.length() -1);
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        localCacheEntity.url = url;
        localCacheEntity.attachType = 1; // 0 暫存 1 提交
        localCacheEntity.params = params;
        ArrayList<AttachMentInfoEntity>attachments = new ArrayList<>();
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  mv_multimedia.getBinaryFile();


/**
 * 1多媒体关系表 2 多媒体基础信息表
 * 先提交多媒体基础信息表，提交成功后，提交多媒体关系表
 * 文档	1  图片	2  音频	3  视频 4 其他	9

 */
        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                info.medPath = "stan/BIS_STAN_REVI_REC/"+time+ "/"+info.medName;
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(item.localFile.getPath());
                info.localPath = item.localFile.getPath();
                info.medSize = file.length();
                info.bisTableName = "BIS_STAN_REVI_REC";
                info.bisGuid = reviewItemInformation.getGuid();
                info.localStatus = "1";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "2"; // 图片
                }else if(item.type == MultimediaView.LocalAttachmentType.AUDIO) {
                    info.medType = "3"; // 音频
                }else if(item.type == MultimediaView.LocalAttachmentType.VIDEO){
                    info.medType = "4";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachments.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                updateState();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    @Override
    public void dialogClick() {
        commitForm();
    }

    @Override
    public void dialogCancel() {

    }
    private void updateState(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppl/";
        HashMap<String,String> params= new HashMap<>();
        params.put("stat","5");
        url += reviewItemInformation.getGuid() +"/"+"?";
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
}
