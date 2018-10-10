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
import com.syberos.shuili.entity.wins.BisWinsStaff;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
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
     * 稽查方案详情对象
     */
    private BisWinsProgAll bisWinsProgAll = null;
    private BisWinsStaff bisWinsStaff = null;
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
        setInitActionBar(true);
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsGroupAll = (BisWinsGroupAll) bundle.getSerializable("bisWinsGroupAll");
        if(bisWinsGroupAll == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            activityFinish();
        }
        getWinsProgByGUID();
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
          getSpecStaffName();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void  getSpecStaffName(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/staff/bisWinsStaffs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisWinsGroupAll.getSpeStafGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsStaff = gson.fromJson(result,BisWinsStaff.class);
                if(bisWinsStaff!= null && bisWinsStaff.dataSource!= null && bisWinsStaff.dataSource.size() > 0){
                    bisWinsGroupAll.setSpecStaffName(bisWinsStaff.dataSource.get(0).getPersExpertName());
                }

                getSpecStafAssiName();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });


    }
    private void getSpecStafAssiName(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/staff/bisWinsStaffs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",bisWinsGroupAll.getSpeStafAssiGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsStaff = gson.fromJson(result,BisWinsStaff.class);
                if(bisWinsStaff!= null && bisWinsStaff.dataSource!= null && bisWinsStaff.dataSource.size() > 0){
                    bisWinsGroupAll.setSpecStaffAssiName(bisWinsStaff.dataSource.get(0).getPersExpertName());
                }
                getExportName();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
    private void getExportName(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/staff/bisWinsStaffs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("winsGroupGuid",bisWinsGroupAll.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                ArrayList<String> exportName = new ArrayList<>();
                bisWinsStaff = gson.fromJson(result,BisWinsStaff.class);
                if(bisWinsStaff!= null && bisWinsStaff.dataSource!= null && bisWinsStaff.dataSource.size() > 0){
                    for(BisWinsStaff item : bisWinsStaff.dataSource){
                        if(!exportName.contains(item.getPersExpertName())){
                            exportName.add(item.getPersExpertName());
                        }
                    }
                }
                String name = exportName.toString();
                name = name.replace("[","");
                name = name.replace("]","");
                bisWinsGroupAll.setSpecExportName(name);
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });

    }
    private void refreshUI(){
        tv_batch.setText(bisWinsGroupAll.getWinsArrayCode());
        tv_time.setText(bisWinsProgAll.dataSource.get(0).getStartTime() +"--"+bisWinsProgAll.dataSource.get(0).getEndTime());
        tv_projType.setText("");
        tv_special.setText(bisWinsGroupAll.getSpecStaffName());
        tv_assistant.setText(bisWinsGroupAll.getSpecStaffAssiName());
        tv_winsType.setText(bisWinsGroupAll.getSpecExportName());

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
        bundle.putSerializable("bisWinsGroup",bisWinsGroupAll);
        intentActivity(InspectionDetailActivity.this,InspectionProblemsAcitvity.class,false,bundle);

    }
}
