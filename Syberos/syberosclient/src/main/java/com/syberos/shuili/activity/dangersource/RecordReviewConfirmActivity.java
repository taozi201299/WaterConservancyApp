package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.dangersource.BisHazReg;
import com.syberos.shuili.entity.dangersource.BisHazRegDetail;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 危险源审核 行政版  提交到重大危险源备案核销信息
 */
public class RecordReviewConfirmActivity extends BaseActivity {

    @BindView(R.id.ce_unit)
    TextView ce_unit;

    @BindView(R.id.ce_code)
    TextView ce_code;
    @BindView(R.id.tv_accident_liability_label)
    TextView tv_accident_liability_label;
    @BindView(R.id.tv_code_label)
    TextView tv_code_label;


    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    private BisHazReg information = null;
    private BisHazRegDetail bisHazRegDetail = null;
    /**
     * 0 备案 1 核销
     */
    int titleType;


    @OnClick(R.id.tv_passed)
    void onConfirmClicked() {
        commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_record_review_confirm;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getBisHazRegDetail();


    }

    private void getBisHazRegDetail(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/obj/selectHazInfoDetails/";
        HashMap<String,String>param = new HashMap<>();
        param.put("guid",information.getGuid());
        param.put("hazGuid",information.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisHazRegDetail = gson.fromJson(result,BisHazRegDetail.class);
                if(bisHazRegDetail == null || bisHazRegDetail.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    if(titleType == 0) {
                        ce_code.setText("ba");
                    }else {
                        ce_code.setText("hx");
                    }

                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }
    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (BisHazReg)bundle.getSerializable(
                RecordReviewListActivity.SEND_BUNDLE_KEY);
        titleType = bundle.getInt("title");
        String title = "";

        if(titleType == 0){
            title = "备案审核";
            tv_accident_liability_label.setText("是否备案");
            tv_code_label.setText("备案号");
        }else {
          title = "核销审核";
            tv_accident_liability_label.setText("是否核销");
            tv_code_label.setText("核销号");
        }
        showTitle(title);

        if (null != information) {
            ce_unit.setText(information.getWiunName());
        }

        ae_describe_audio.setLabelText("备注");
    }
    private  void commit(){
        String url;
        if(titleType == 0) {
            url = GlobleConstants.str7GeIP + "/maha/maha/BisHazMajReg/mobile/insert";
        }else {
            url = GlobleConstants.str7GeIP + "/maha/maha/BisHazMajReg/mobile/writInsert";
        }
        HashMap<String,String> params= new HashMap<>();
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgName",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName());
        params.put("hazGuid",information.getGuid());
        params.put("collTime",CommonUtils.getCurrentDate());
        params.put("note",ae_describe_audio.getEditText().toString());
        if(titleType == 0) {
            params.put("regTime", CommonUtils.getCurrentDate()); //备案日期
        }
        if(titleType == 1) {
            params.put("recCode","");
            params.put("regTime",information.getRegTime());
            params.put("writeOffTime",CommonUtils.getCurrentDate()); // 核销时间
        }
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        localCacheEntity.commitType = 0;
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
