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
import com.syberos.shuili.entity.wins.BisWinsStaff;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.winsProjType;
import static com.syberos.shuili.config.GlobleConstants.winsTypeMap;

/**
 * 稽查详情
 */
public class InspectQueryGroupDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String SEND_BUNDLE_KEY = "InspectProblemInformation";
    private final String Title = "稽察组信息";
    /**
     * 稽查组
     */
    private BisWinsGroupAll bisWinsGroupAll = null;

    /**
     * 稽查方案
     */
    private BisWinsProg bisWinsProg = null;

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
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsGroupAll = (BisWinsGroupAll) bundle.getSerializable("bisWinsGroup");
        bisWinsProg = (BisWinsProg)bundle.getSerializable("bisWinsProg");
        if(bisWinsGroupAll == null || bisWinsProg == null){
            ToastUtils.show("参数错误");
            activityFinish();
        }
        getSpecStaffName();
    }
    private void  getSpecStaffName(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/staff/bisWinsStaffs/";
        HashMap<String,String> params = new HashMap<>();
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
        tv_batch.setText(bisWinsProg.getWinsArrayCode());
        tv_time.setText(bisWinsProg.getStartTime() +"--"+bisWinsProg.getEndTime());
        tv_projType.setText(winsProjType.get(bisWinsProg.getWinsProjType()));
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
        intentActivity(InspectQueryGroupDetailActivity.this,InspectionProblemsAcitvity.class,false,bundle);

    }
}
