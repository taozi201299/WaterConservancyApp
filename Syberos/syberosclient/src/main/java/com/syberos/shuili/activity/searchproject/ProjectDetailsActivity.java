package com.syberos.shuili.activity.searchproject;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_unit)
    TextView tv_unit;

    @BindView(R.id.tv_project_name)
    TextView tv_project_name;

    @BindView(R.id.tv_project_code)
    TextView tv_project_code;

    @BindView(R.id.tv_parent_unit)
    TextView tv_parent_unit;

    @BindView(R.id.tv_project_status)
    TextView tv_project_status;

    @BindView(R.id.tv_project_start)
    TextView tv_project_start;

    @BindView(R.id.tv_project_end)
    TextView tv_project_end;

    @BindView(R.id.tv_project_price)
    TextView tv_project_price;

    @BindView(R.id.tv_project_importance)
    TextView tv_project_importance;

    private String objGuid;
    private MvEngColl mvEngColl = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_qrcode_result_project_details;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getProjectInfo();

    }

    @Override
    public void initView() {
        showTitle("工程详情");
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);

        if (null != bundle) {
           objGuid = bundle.getString("objGuid");
        }
    }
    private void getProjectInfo() {
        String url =  GlobleConstants.strIP +"/sjjk/v1/mv/eng/coll/mvEngColls/";
        HashMap<String,String>params = new HashMap<>();
        params.put("id",objGuid);
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                mvEngColl = gson.fromJson(result,MvEngColl.class);
                if(mvEngColl == null || mvEngColl.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(mvEngColl.dataSource.size() == 0){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
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
    private void  refreshUI(){

    }
}
