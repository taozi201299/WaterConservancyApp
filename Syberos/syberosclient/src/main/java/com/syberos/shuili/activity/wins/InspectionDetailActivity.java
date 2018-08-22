package com.syberos.shuili.activity.wins;

import android.os.Bundle;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsGroupAll;
import com.syberos.shuili.entity.wins.BisWinsProg;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/15.
 * 1 根据稽查组获取稽查方案
 * 2 根据稽查组获取稽查人员信息
 */

public class InspectionDetailActivity extends BaseActivity {
    private BisWinsProg bisWinsProg = null;
    private BisWinsGroupAll bisWinsGroupAll = null;
    private BisWinsGroup bisWinsGroup = null;
    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_detail;
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
        bisWinsGroupAll = (BisWinsGroupAll) bundle.getSerializable("bisWinsGroupAll");
        if(bisWinsGroupAll == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            activityFinish();
        }
        getWinsGroupByWinsProgGuid();
    }
    /**
     * 根据稽查方案GUID 获取稽查组信息
     */
    private void  getWinsGroupByWinsProgGuid(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/wins/prog/selectWinsGroupInfoByWinsProgGuid/";
        HashMap<String,String> params = new HashMap<>();
        params.put("winsProgGuid",bisWinsProg.getBwpGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisWinsGroup = gson.fromJson(result,BisWinsGroup.class);
                if(bisWinsGroup == null || bisWinsGroup.dataSource == null){
                    ToastUtils.show("获取稽查组信息错误");
                }else {
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show("获取稽查组信息错误");
            }
        });
    }
    private void  getBisWinsProg(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/wins/prog/selectCheckProgramDetailsByAuditPlan/";
        HashMap<String,String> params = new HashMap<>();
        params.put("owpGuid",bisWinsGroupAll.getWinsPlanGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsProg = gson.fromJson(result,BisWinsProg.class);
                if(bisWinsProg == null || bisWinsProg.dataSource == null || bisWinsProg.dataSource.size() == 0){
                }else {
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
            }
        });
    }
}
