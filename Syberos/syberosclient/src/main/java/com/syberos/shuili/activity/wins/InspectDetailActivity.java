package com.syberos.shuili.activity.wins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.inspect.InspectProblemInformation;
import com.syberos.shuili.entity.inspect.BisWinsDetail;
import com.syberos.shuili.entity.inspect.BisWinsGroup;
import com.syberos.shuili.entity.inspect.BisWinsProg;
import com.syberos.shuili.entity.inspect.BisWinsProj;
import com.syberos.shuili.entity.inspect.BisWinsRegi;
import com.syberos.shuili.entity.inspect.BisWinsStaff;
import com.syberos.shuili.entity.inspect.ObjWinsPlan;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 稽查方案详情
 */
public class InspectDetailActivity extends TranslucentActivity {

    public static final String SEND_BUNDLE_KEY = "InspectProblemInformation";
    private final static int MAX_SCALING_SIZE = 5;
    /**
     * 稽查计划对象
     */
    private ObjWinsPlan objWinsPlan = null;
    /**
     * 稽查方案
     */
    private BisWinsProg bisWinsProg = null;
    /**
     * 稽查组
     */
    private BisWinsGroup bisWinsGroup = null;
    /**
     * 稽查区域
     */
    private BisWinsRegi bisWinsRegi = null;
    /**
     * 稽查人员
     */
    private BisWinsStaff bisWinsStaff = null;
    /**
     * 稽查项目
     */
    private BisWinsProj bisWinsProj = null;

    private BisWinsDetail bisWinsDetail = null;

    @BindView(R.id.tv_batch)
    TextView tv_batch;              // 稽查批次

    @BindView(R.id.tv_time)
    TextView tv_time;               // 稽查时间

    @BindView(R.id.tv_area)
    TextView tv_area;               // 稽查地区

    @BindView(R.id.tv_special)
    TextView tv_special;            // 特派员

    @BindView(R.id.tv_assistant)
    TextView tv_assistant;          // 特派员助理

    @BindView(R.id.tv_experts)
    TextView tv_experts;            // 稽查专家

    @BindView(R.id.ll_project_items)
    LinearLayout ll_project_items;

    @BindView(R.id.ll_problems)
    LinearLayout ll_problems;

    @BindView(R.id.ll_scale)
    LinearLayout ll_scale;

    @BindView(R.id.iv_scale_direction)
    ImageView iv_scale_direction;

    private boolean isAllInspectProjectsScaling = false;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

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
        objWinsPlan = (ObjWinsPlan) bundle.getSerializable("objWinsPlan");
        bisWinsProg = (BisWinsProg)bundle.getSerializable("bisWinsProg");
    }

    /**
     * 从稽查组和稽查人员表中获取稽查人员信息
     */
    private void getObjPlanDetail(){
        // 1 通过稽查方案ID获取特派员ID
        String url = "";
        HashMap<String,String> params = new HashMap<>();
        params.put("WinsProgGuid",bisWinsProg.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsGroup = gson.fromJson(result,BisWinsGroup.class);
                if(bisWinsGroup == null || bisWinsGroup.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(bisWinsGroup.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                    return;
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    /**
     * 根据稽查组ID 获取人员信息
     */
    private void getWinsStaff(){
        String url = "";
        HashMap<String,String> params = new HashMap<>();
        params.put("winsGroupGuid",bisWinsGroup.dataSource.get(0).getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsStaff = gson.fromJson(result,BisWinsStaff.class);
                if(bisWinsStaff == null || bisWinsStaff.dataSource == null ||
                        bisWinsStaff.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getBisWinRegi(){
        final String url = "";
        HashMap<String,String>params = new HashMap<>();
        params.put("winsProgGuid",bisWinsProg.getGuid());
        params.put("winsGroupGuid",bisWinsGroup.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsRegi = gson.fromJson(result,BisWinsRegi.class);
                if(bisWinsRegi == null || bisWinsRegi.dataSource == null
                        || bisWinsRegi.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getBisWinsProj();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    /**
     * 从稽查项目表中获取稽查对象 根据稽查组ID
     */
    private void getBisWinsProj(){
        String url = "";
        HashMap<String,String>params = new HashMap<>();
        params.put("winsGroupGuid",bisWinsGroup.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsProj = gson.fromJson(result,BisWinsProj.class);
                if(bisWinsProj == null || bisWinsProj.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                mergeData();
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void mergeData(){
        if(bisWinsDetail == null){
            bisWinsDetail = new BisWinsDetail();
        }
        bisWinsDetail.setWinsPlanName(objWinsPlan.getWinsPlanName());
        bisWinsDetail.setWinsArrayNum(objWinsPlan.getWinsArrayNum());
        bisWinsDetail.setStartTime(bisWinsProg.getStartTime());
        bisWinsDetail.setEndTime(bisWinsProg.getEndTime());
        bisWinsDetail.setSpeStafGuid(bisWinsGroup.getSpeStafGuid());
        bisWinsDetail.setSpeStafAssiGuid(bisWinsGroup.getSpeStafAssiGuid());
        bisWinsDetail.setAreaName(bisWinsRegi.getAreaName());
        bisWinsDetail.setPersExpertName(bisWinsStaff.getPersExpertName());
    }
    private void refreshUI(){
        if(bisWinsDetail== null) return;
        tv_batch.setText(bisWinsDetail.getWinsArrayNum());
        tv_time.setText(bisWinsDetail.getStartTime() +"-" + bisWinsDetail.getEndTime());
        tv_area.setText(bisWinsDetail.getAreaName());
        tv_special.setText(bisWinsDetail.getSpeStafGuid());
        tv_assistant.setText(bisWinsDetail.getSpeStafAssiGuid());
        tv_experts.setText(bisWinsDetail.getPersExpertName());

        ll_project_items.removeAllViews();
        addInspectProjectItems();

    }
    /**
     * 从稽查问题表中获取该稽查组下的稽查问题
     */
    private void getBisWinsProb(){

    }
    private void addInspectProjectItems() {
        for (BisWinsProj item : bisWinsProj.dataSource) {
            addInspectProjectItem(item.getProjName());
        }
    }

    private void addInspectProjectItem(final String projectName) {
        View layout = LayoutInflater.from(this).inflate(R.layout.layout_inspect_project_item,
                ll_project_items, false);

        TextView name = (TextView) layout.findViewById(R.id.tv_project_name);
        name.setText(projectName);

        ll_project_items.addView(layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/4/30 检查项的检查问题界面
            }
        });
    }

    private void addInspectProblems(final List<InspectProblemInformation> problems) {
        for (InspectProblemInformation problem : problems) {
            addInspectProblem(problem);
        }
    }

    private void addInspectProblem(final InspectProblemInformation problem) {
        View layout = LayoutInflater.from(this).inflate(R.layout.inspect_detail_problem_list_item,
                ll_problems, false);



        ll_problems.addView(layout);
    }

}
