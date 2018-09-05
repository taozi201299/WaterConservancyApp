package com.syberos.shuili.activity.wins;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.syberos.shuili.entity.wins.BisWinsProgAll;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.winsProjType;
import static com.syberos.shuili.config.GlobleConstants.winsTypeMap;

/**
 * Created by Administrator on 2018/8/15.
 * 1 根据稽查组获取稽查方案
 * 2 根据稽查组获取稽查人员信息
 */

public class InspectionDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_batch)
    TextView tv_batch;              // 稽查批次

    @BindView(R.id.tv_time)
    TextView tv_time;               // 稽查时间

    @BindView(R.id.tv_projType)
    TextView tv_projType;               // 稽查地区

    @BindView(R.id.tv_special)
    TextView tv_special;            // 特派员

    @BindView(R.id.tv_assistant)
    TextView tv_assistant;          // 特派员助理

    @BindView(R.id.tv_winsType)
    TextView tv_winsType;            // 稽查专家

    /**
     * 稽查组发现的问题数量
     */
    @BindView(R.id.tv_problem_count)
    TextView tv_problem_count;

    @BindView(R.id.rl_prob_count)
    RelativeLayout rl_prob_count;
    /**
     * 传递的参数
     */
    private BisWinsGroupAll bisWinsGroupAll = null;
    /**
     * 获取的稽查组信息
     */
    private BisWinsGroup bisWinsGroup = null;

    /**
     * 稽查方案详情对象
     */
    private BisWinsProgAll bisWinsProgAll = null;
    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_detail;
    }

    @Override
    public void initListener() {
        rl_prob_count.setOnClickListener(this);
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
        params.put("bwpGuid",bisWinsGroupAll.getWinsProgGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsGroup = gson.fromJson(result,BisWinsGroup.class);
                if(bisWinsGroup == null || bisWinsGroup.dataSource == null){
                    ToastUtils.show("获取稽查组信息错误");
                }else {
                    getWinsProgByGUID();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show("获取稽查组信息错误");
            }
        });
    }
    private void getWinsProgByGUID(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/prog/bisWinsProgs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisWinsGroupAll.getWinsProgGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsProgAll = gson.fromJson(result,BisWinsProgAll.class);
                if(bisWinsProgAll == null || bisWinsProgAll.dataSource == null || bisWinsProgAll.dataSource.size() == 0){
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
        tv_batch.setText(bisWinsGroupAll.getWinsArrayCode());
        tv_time.setText(bisWinsProgAll.getStartTime() +"--"+bisWinsProgAll.getEndTime());
        tv_projType.setText(winsProjType.get(bisWinsProgAll.getWinsProjType()));
        tv_special.setText(bisWinsGroup.getSpeStafName());
        tv_assistant.setText("");
        tv_winsType.setText(winsTypeMap.get(bisWinsProgAll.getWinsType()));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_prob_count:
                go2ProblemsActivity();
                break;

        }
    }
    private void go2ProblemsActivity(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("bisWinsGroup",bisWinsGroup);
        intentActivity(InspectionDetailActivity.this,InspectionProblemsAcitvity.class,false,bundle);

    }
}
