package com.syberos.shuili.activity.wins;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsProg;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.winsProjType;
import static com.syberos.shuili.config.GlobleConstants.winsTypeMap;

/**
 * 稽查详情
 */
public class InspectQueryGroupDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String SEND_BUNDLE_KEY = "InspectProblemInformation";
    private final String Title = "稽查组信息";
    /**
     * 稽查组
     */
    private BisWinsGroup bisWinsGroup = null;

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
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsGroup = (BisWinsGroup) bundle.getSerializable("bisWinsGroup");
        bisWinsProg = (BisWinsProg)bundle.getSerializable("bisWinsProg");
        if(bisWinsGroup == null || bisWinsProg == null){
            ToastUtils.show("参数错误");
            activityFinish();
        }
        refreshUI();
    }

    private void refreshUI(){
        tv_batch.setText(bisWinsProg.getWinsArrayCode());
        tv_time.setText(bisWinsProg.getStartTime() +"--"+bisWinsProg.getEndTime());
        tv_projType.setText(winsProjType.get(bisWinsProg.getWinsProjType()));
        tv_special.setText(bisWinsGroup.getSpeStafName());
        tv_assistant.setText("");
        tv_winsType.setText(winsTypeMap.get(bisWinsProg.getWinsType()));

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
        intentActivity(InspectQueryGroupDetailActivity.this,InspectionProblemsAcitvity.class,false,bundle);

    }
}
