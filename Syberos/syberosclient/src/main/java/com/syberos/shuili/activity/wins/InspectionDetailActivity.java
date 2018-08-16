package com.syberos.shuili.activity.wins;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsGroupAll;
import com.syberos.shuili.entity.wins.BisWinsProg;

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
        return 0;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

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
