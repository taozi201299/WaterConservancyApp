package com.syberos.shuili.activity.stan;


import android.os.Bundle;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.standardization.ReviewItemInformation;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CheckableButton;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

public class SceneReviewDetailActivity extends TranslucentActivity {

    private ObjStanRevis reviewItemInformation = null;
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


        // send reviewItemInformation to server
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scene_review_detail;
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
        reviewItemInformation = (ObjStanRevis) bundle.getSerializable(
                SceneReviewListActivity.SEND_BUNDLE_KEY);

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

    private void clearAllChecked() {
        cb_level_0.setChecked(false);
        cb_level_1.setChecked(false);
        cb_level_2.setChecked(false);
        cb_level_3.setChecked(false);
    }
    /**
     * 提交到标准化评审记录表
     */
    private void  commit(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/revi/bisStanReviRec/";
        HashMap<String,String> params= new HashMap<>();
        params.put("reviType","3");
        if(currentLevel == ReviewItemInformation.LEVEL_3)
        params.put("ifAgree","2");
        else {
            params.put("ifAgree","1");
        }
        params.put("recomLevel",String.valueOf(currentLevel)); // 现场审核评定等级
        params.put("siteReviNote",ae_describe_audio.getEditText()); // 现场复审备注
        params.put("recPers","");
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        url += reviewItemInformation.getGuid() +"/"+"?";
        for(String key :params.keySet()){
            url += key;
            url +="=";
            url += params.get(key);
            url += "&";
        }
        url = url.substring(0,url.length() -1);
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                    ToastUtils.show("提交成功");
                    finish();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
}
