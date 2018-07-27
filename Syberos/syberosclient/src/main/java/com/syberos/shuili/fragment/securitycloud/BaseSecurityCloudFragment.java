package com.syberos.shuili.fragment.securitycloud;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.entity.securitycloud.SecurityCloudEntry;
import com.syberos.shuili.entity.securitycloud.StraightTubeManageEntry;
import com.syberos.shuili.entity.securitycloud.SupervisionMangeEntry;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.DialPlateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by BZB on 2018/7/20.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.securitycloud.
 */
@SuppressLint("ValidFragment")
public class BaseSecurityCloudFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = "BaseSecurityCloudFragment";
    String strJsonData;//= "{\"accidentInfoEntry\":{\"accLevelFourCount\":0,\"accLevelOneCount\":0,\"accLevelThreeCount\":0,\"accLevelTwoCount\":0,\"deathCount\":0,\"score\":0,\"totalCount\":0},\"compScoreTrend\":{\"dataList\":[{\"date\":null,\"score\":0}],\"qualifiedScore\":0},\"hiddenInfoEntry\":{\"majorHadSupervisingCount\":0,\"majorHiddenCount\":0,\"majorHiddenHadRectifyCount\":0,\"majorHiddenNoRectifyCount\":0,\"majorLateNoRectifyCount\":0,\"noRectifyCount\":0,\"normalHiddenCount\":0,\"normalHiddenHadRectifyCount\":0,\"normalHiddenNoRectifyCount\":0,\"normalLateNoRectifyCount\":0,\"score\":0,\"totalHiddenCount\":0},\"rankList\":[{\"id\":null,\"name\":null,\"score\":0}],\"riskSourceEntry\":{\"hadControl\":0,\"hadRecord\":0,\"noControl\":0,\"noRecord\":0,\"score\":0},\"straightTubeManageEntry\":{\"dataList\":[{\"partReportCount\":0,\"partUnReportCount\":0}],\"perTrainingHours\":0,\"score\":0,\"trainingPersonCount\":0},\"supervisionMangeEntry\":{\"score\":0,\"standardLevelOneCount\":0,\"standardLevelThreeCount\":0,\"standardLevelTwoCount\":0,\"workAssessmentScore\":0},\"synthesisInfoEntry\":{\"chainRatio\":null,\"sameTimeRatio\":null,\"score\":0}}";
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_grade_time)
    TextView tvGradeTime;
    @BindView(R.id.view_dial_plate)
    DialPlateView viewDialPlate;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_acc)
    CardView cardViewAcc;

    @BindView(R.id.view_manage)
    CardView cardViewManager;

    @BindView(R.id.view_manage_direct)
    CardView cardViewManagerDirect;


    @BindView(R.id.tv_score_title)
    TextView tvScoreTitle;

    @BindView(R.id.view_hidden)
    CardView viewHidden;
    @BindView(R.id.view_risk_source)
    CardView viewRiskSource;
    @BindView(R.id.view_grade_trend)
    LinearLayout viewGradeTrend;
    @BindView(R.id.view_rank)
    LinearLayout viewRank;

    //    事故
    @BindView(R.id.pie_char_acc)
    PieChart pieCharAcc;
    @BindView(R.id.tv_score_acc)
    TextView tvScoreAcc;
    @BindView(R.id.tv_death_acc_count)
    TextView tvDeathAccCount;
    @BindView(R.id.tv_count_acc)
    TextView tvCountAcc;

    //    风险源
    @BindView(R.id.tv_count_risk)
    TextView tvCountRisk;
    @BindView(R.id.tv_count2_risk)
    TextView tvCount2;
    @BindView(R.id.tv_score_risk)
    TextView tvScoreRiskSource;
    @BindView(R.id.pie_char_risk)
    PieChart pieCharRisk;
    //  隐患
    @BindView(R.id.tv_count1_no_rectify)//未整改隐患
            TextView tvCount1Hidden;
    @BindView(R.id.tv_score_hidden)
    TextView tvScoreHidden;

    @BindView(R.id.tv_count2)//隐患总数量
            TextView tvCount2Hidden;
    @BindView(R.id.tv_count3)//一般隐患 数量
            TextView tvCount3Hidden;
    @BindView(R.id.tv_had_rectified_value1)//一般隐患 已整改
            TextView tvHadRectifiedValue1;
    @BindView(R.id.tv_no_rectified_value1)// 一般隐患 未整改
            TextView tvNoRectifiedValue1;
    @BindView(R.id.tv_late_value1)  //一般隐患 逾期未整改
            TextView tvLateValue1;
    @BindView(R.id.tv_count4)   //重大隐患 数量
            TextView tvCount4;
    @BindView(R.id.tv_had_rectified_value2) //重 已整改
            TextView tvHadRectifiedValue2;
    @BindView(R.id.tv_no_rectified_value2) //重 未整改
            TextView tvNoRectifiedValue2;
    @BindView(R.id.tv_late_value2)  //重大隐患 逾期未整改
            TextView tvLateValue2;
    @BindView(R.id.tv_had_supervise_value)  //重大隐患 已督办
            TextView tvHadSuperviseValue;

    //管理
    @BindView(R.id.tv_score_manage)
    TextView tvScoreManage;
    @BindView(R.id.pie_char_manager)
    PieChart pieCharManager;

    //    管理 -直管
    @BindView(R.id.tv_count_trainer)
    TextView tvCountTrainer;
    @BindView(R.id.tv_count_hours)
    TextView tvCountHours;
    @BindView(R.id.tv_score_manage_direct)
    TextView tvScoreManageDirect;
    @BindView(R.id.tv_check_had_report)
    TextView tvCheckHadReport;
    @BindView(R.id.tv_check_no_report)
    TextView tvCheckNoReport;
    @BindView(R.id.tv_assessment_had_report)
    TextView tvAssessmentHadReport;
    @BindView(R.id.tv_assessment_no_report)
    TextView tvAssessmentNoReport;
    @BindView(R.id.tv_hidden_had_report)
    TextView tvHiddenHadReport;
    @BindView(R.id.tv_hidden_no_report)
    TextView tvHiddenNoReport;
    @BindView(R.id.tv_acc_had_report)
    TextView tvAccHadReport;
    @BindView(R.id.tv_acc_no_report)
    TextView tvAccNoReport;


    String type;
    String title;
    String titleDetail;
    SecurityCloudEntry securityCloudEntry;
    private List<PieEntry> accPieEntrys;

    public void initViewData() {
        appBarLayout.addOnOffsetChangedListener(this);
        Gson gson = new Gson();
        if (strJsonData != null) {
            securityCloudEntry = gson.fromJson(strJsonData, SecurityCloudEntry.class);
        }

        initTitleAndView(type);
        int score = securityCloudEntry.getSynthesisInfoEntry().getScore();
        titleDetail = new String(new StringBuilder(title).append("·安全评分·").append(score).append("分"));
        tvTitle.setText(title);
        collapsingToolbarLayout.setTitle(titleDetail);
        tvScore.setText(score + "");
        viewDialPlate.updateData(score);

    }

    private void initTitleAndView(String type) {
        initAccView(securityCloudEntry.getAccidentInfoEntry());
        initHiddenView(securityCloudEntry.getHiddenInfoEntry());
        initRiskResource(securityCloudEntry.getRiskSourceEntry());
        initTrendView(securityCloudEntry.getCompScoreTrend());
        switch (type) {
            case "1":
                title = "直管工程";
                cardViewManagerDirect.setVisibility(View.VISIBLE);
                initStraightTubeManage(securityCloudEntry.getStraightTubeManageEntry());
                initRankView(securityCloudEntry.getRankList());
                break;
            case "2":
                title = "流域机构";
                cardViewManager.setVisibility(View.VISIBLE);
                initSupervisionManage(securityCloudEntry.getSupervisionMangeEntry());
                initRankView(securityCloudEntry.getRankList());
                break;
            case "3":
                title = "行业监管";
                cardViewManager.setVisibility(View.VISIBLE);
                initSupervisionManage(securityCloudEntry.getSupervisionMangeEntry());
                initRankView(securityCloudEntry.getRankList());
                break;
            case "4"://具体区域 或河流
                title = "";
                cardViewManager.setVisibility(View.VISIBLE);
                viewRank.setVisibility(View.GONE);
                break;
        }
    }

    RecyclerAdapter recyclerAdapter;

    /**
     * 初始化 排名
     *
     * @param rankList
     */
    private void initRankView(List<SecurityCloudEntry.AreaRank> rankList) {
        for (int i = 0; i < 10; i++) {
            SecurityCloudEntry.AreaRank rank = new SecurityCloudEntry.AreaRank();
            rank.setId("11");
            rank.setName("测试" + i);
            rank.setScore((10 - i) * 10);
            rankList.add(rank);
        }

        RecyclerView recyclerView = viewRank.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerAdapter = new RecyclerAdapter(rankList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRank;
        TextView tvRank;
        TextView tvName;
        TextView tvScore;

        public RecyclerViewHolder(View itemView) {

            super(itemView);
            ivRank = itemView.findViewById(R.id.iv_rank);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }

    class RecyclerAdapter extends BaseRecyclerAdapter<RecyclerViewHolder> {
        List<SecurityCloudEntry.AreaRank> list;
        RecyclerViewHolder holder;

        public RecyclerAdapter(List<SecurityCloudEntry.AreaRank> list) {
            this.list = list;
        }

        @Override
        public RecyclerViewHolder getViewHolder(View view) {
            return holder;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_rank, parent, false);
            holder = new RecyclerViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position, boolean isItem) {
            if (position == 0) {
                holder.ivRank.setVisibility(View.VISIBLE);
                holder.ivRank.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.security_gold_medal));

            } else if (position == 1) {
                holder.ivRank.setVisibility(View.VISIBLE);
                holder.ivRank.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.security_silver_medal));
            } else if (position == 2) {
                holder.ivRank.setVisibility(View.VISIBLE);
                holder.ivRank.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.security_bronze_medal));
            } else {
                holder.ivRank.setVisibility(View.GONE);
                holder.tvRank.setVisibility(View.VISIBLE);
                holder.tvRank.setText(position + 1 + "");
            }
            holder.tvScore.setText(list.get(position).getScore() + "");
            holder.tvName.setText(list.get(position).getName());
        }

        @Override
        public int getAdapterItemCount() {
            return list.size();
        }
    }

    /**
     * 初始化曲线图
     *
     * @param compScoreTrend
     */
    private void initTrendView(SecurityCloudEntry.CompScoreTrend compScoreTrend) {

    }

    /**
     * 直管的 管理 返回数据
     *
     * @param straightTubeManageEntry
     */
    private void initStraightTubeManage(StraightTubeManageEntry straightTubeManageEntry) {
        List<StraightTubeManageEntry.ReportInfoItemEntry> dataList = new ArrayList<>();
        dataList.clear();
        tvScoreManageDirect.setText(straightTubeManageEntry.getScore() + "");
        tvCountTrainer.setText(straightTubeManageEntry.getTrainingPersonCount() + "");
        tvCountHours.setText(straightTubeManageEntry.getPerTrainingHours() + "");

        dataList = straightTubeManageEntry.getDataList();
        for (StraightTubeManageEntry.ReportInfoItemEntry e : dataList) {
            if (e.getTaskId().equals("1")) {
                tvCheckHadReport.setText(e.getPartReportCount() + "");
                tvCheckNoReport.setText(e.getPartUnReportCount() + "");
            } else if (e.getTaskId().equals("2")) {
                tvAssessmentHadReport.setText(e.getPartReportCount() + "");
                tvAssessmentNoReport.setText(e.getPartUnReportCount() + "");
            } else if (e.getTaskId().equals("3")) {
                tvHiddenHadReport.setText(e.getPartReportCount() + "");
                tvHiddenNoReport.setText(e.getPartUnReportCount() + "");
            } else if (e.getTaskId().equals("4")) {
                tvAccHadReport.setText(e.getPartReportCount() + "");
                tvAccNoReport.setText(e.getPartUnReportCount() + "");
            }
        }


    }

    /**
     * 监管和流域的  管理 返回数据
     *
     * @param supervisionMangeEntry
     */
    @SuppressLint("DefaultLocale")
    private void initSupervisionManage(SupervisionMangeEntry supervisionMangeEntry) {
        tvScoreManage.setText(supervisionMangeEntry.getScore() + "");
        List<PieEntry> list = new ArrayList<>();
        int levelOne = supervisionMangeEntry.getStandardLevelOneCount();
        int levelTwo = supervisionMangeEntry.getStandardLevelTwoCount();
        int levelThree = supervisionMangeEntry.getStandardLevelThreeCount();
        if (levelOne + levelTwo + levelThree == 0) {
            initPieChartNoData(pieCharManager, "暂无数据");
        } else {
            list.add(new PieEntry(levelOne, "标准化一级" + String.format("%.1f", (levelOne * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
            list.add(new PieEntry(levelTwo, "标准化二级" + String.format("%.1f", (levelTwo * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));
            list.add(new PieEntry(levelThree, "标准化三级" + String.format("%.1f", (levelThree * 100.0f / (levelOne + levelThree + levelTwo))) + "%"));

            initCharView(pieCharManager, list);
        }
    }

    private void initRiskResource(SecurityCloudEntry.RiskSourceEntry riskSourceEntry) {
        if (riskSourceEntry != null) {
            tvScoreRiskSource.setText(riskSourceEntry.getScore() + "");
            tvCountRisk.setText(riskSourceEntry.getHadControl() + "");
            tvCount2.setText(riskSourceEntry.getNoControl() + "");
            List<PieEntry> riskPieEntrys = new ArrayList<>();

            if (riskSourceEntry.getHadRecord() + riskSourceEntry.getNoRecord() == 0) {
                PieChart pieChart = ((PieChart) viewRiskSource.findViewById(R.id.pie_char));
                initPieChartNoData(pieChart, "无风险源");
            } else {
                riskPieEntrys.add(new PieEntry(riskSourceEntry.getHadRecord(), "已备案 " + riskSourceEntry.getHadRecord()));
                riskPieEntrys.add(new PieEntry(riskSourceEntry.getNoRecord(), "未备案 " + riskSourceEntry.getNoRecord()));
                initCharView((PieChart) pieCharRisk.findViewById(R.id.pie_char_risk), riskPieEntrys);
            }
        } else {
            ToastUtils.show("获取风险源数据错误");
        }
    }

    @SuppressLint("SetTextI18n")
    private void initHiddenView(SecurityCloudEntry.HiddenInfoEntry hiddenInfoEntry) {
        if (hiddenInfoEntry != null) {
            tvScoreHidden.setText(hiddenInfoEntry.getScore() + "");
            tvCount1Hidden.setText(hiddenInfoEntry.getNoRectifyCount() + "");
            tvCount2Hidden.setText(hiddenInfoEntry.getTotalHiddenCount() + "");
            tvCount3Hidden.setText(hiddenInfoEntry.getNormalHiddenCount() + "");
            tvHadRectifiedValue1.setText(hiddenInfoEntry.getNormalHiddenHadRectifyCount() + "");
            tvNoRectifiedValue1.setText(hiddenInfoEntry.getNormalHiddenNoRectifyCount() + "");
            tvLateValue1.setText(hiddenInfoEntry.getNormalLateNoRectifyCount() + "");
            tvLateValue2.setText(hiddenInfoEntry.getMajorLateNoRectifyCount() + "");
            tvHadSuperviseValue.setText(hiddenInfoEntry.getMajorHadSupervisingCount() + "");

            tvCount4.setText(hiddenInfoEntry.getMajorHiddenCount() + "");
            tvHadRectifiedValue2.setText(hiddenInfoEntry.getMajorHiddenHadRectifyCount() + "");

        } else {
            ToastUtils.show("获取隐患数据错误");
        }

    }

    private void initAccView(SecurityCloudEntry.AccidentInfoEntry accidentInfoEntry) {
        tvScoreAcc.setText(accidentInfoEntry.getScore() + "");
        tvDeathAccCount.setText(accidentInfoEntry.getDeathCount() + "");
        tvCountAcc.setText(accidentInfoEntry.getTotalCount() + "");

        List<PieEntry> accPieEntrys = new ArrayList<>();
        if (accidentInfoEntry.getAccLevelOneCount() + accidentInfoEntry.getAccLevelTwoCount() + accidentInfoEntry.getAccLevelThreeCount() + accidentInfoEntry.getAccLevelFourCount() == 0) {
            PieChart pieChart = ((PieChart) cardViewAcc.findViewById(R.id.pie_char));
            initPieChartNoData(pieChart, "无事故发生");
        } else {
            accPieEntrys.add(new PieEntry(accidentInfoEntry.getAccLevelOneCount(), "一般事故 " + accidentInfoEntry.getAccLevelOneCount()));
            accPieEntrys.add(new PieEntry(accidentInfoEntry.getAccLevelTwoCount(), "较大事故 " + accidentInfoEntry.getAccLevelTwoCount()));
            accPieEntrys.add(new PieEntry(accidentInfoEntry.getAccLevelThreeCount(), "重大事故 " + accidentInfoEntry.getAccLevelThreeCount()));
            accPieEntrys.add(new PieEntry(accidentInfoEntry.getAccLevelFourCount(), "特大事故 " + accidentInfoEntry.getAccLevelFourCount()));
            initCharView((PieChart) cardViewAcc.findViewById(R.id.pie_char_acc), accPieEntrys);
        }
    }

    private void initPieChartNoData(PieChart pieChart, String str) {

        PieChart.LayoutParams params = new PieChart.LayoutParams(PieChart.LayoutParams.MATCH_PARENT, 25);
        pieChart.setLayoutParams(params);
        pieChart.setNoDataText(str);
        pieChart.setTouchEnabled(false);
        pieChart.setNoDataTextColor(R.color.text_black_color);
        cardViewAcc.invalidate();
    }

    public void updataData(String title, String strJsonData) {
        this.title = title;
        this.strJsonData = strJsonData;
        initData();
    }


    @SuppressLint("ValidFragment")
    public BaseSecurityCloudFragment(@Nullable String strJsonData, String type) {

        this.strJsonData = strJsonData;
        this.type = type;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud_river_area;
    }

    @Override
    protected void initListener() {

    }

    private void initCharView(PieChart pieChart, List<PieEntry> strings) {


        PieDataSet dataSet = new PieDataSet(strings, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.pie_chart_color_level_one));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_two));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_three));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_four));
        dataSet.setColors(colors);
        // 获取pieCahrt图列
        Legend l = pieChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义）
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.SQUARE); //设置图例的形状
        l.setFormSize(14);                      //设置图例的大小
        l.setFormToTextSpace(12f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
//        l.setXEntrySpace(10f);				  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(6f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(-20f);                      //设置比例块Y轴偏移量
//        l.setXOffset(-10);
        l.setTextSize(14f);                      //设置图例标签文本的大小
        l.setTextColor(getResources().getColor(R.color.text_gray_color));//设置图例标签文本的颜色
//        pieChart.invalidate();
//        是否允许点击
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(false);

        pieChart.setTouchEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDrawEntryLabels(false);
//        是否允许旋转
        pieChart.setRotationEnabled(false);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
//        pieChart.setHoleRadius(0.8f);

        pieChart.setHoleRadius(55f);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setData(pieData);
        pieChart.setNoDataTextColor(Color.RED);
//        pieChart.show

        pieChart.setNoDataText("暂无数据");
        pieChart.setExtraOffsets(-10, 0, 10, 0);
//        pieChart.setTranslationX(20);
//        pieChart.setPivotX();
        pieChart.invalidate();
    }

    @Override
    protected void initData() {
        initViewData();

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float scale = 1.0f - (-verticalOffset) / (float) (appBarLayout.getHeight() - toolbar.getHeight());
        tvScore.setScaleX(scale);
        tvScore.setScaleY(scale);
        tvScore.setAlpha(scale);

        viewDialPlate.setScaleX(scale);
        viewDialPlate.setScaleY(scale);
        viewDialPlate.setAlpha(scale);

        tvScoreTitle.setScaleX(scale);
        tvScoreTitle.setScaleY(scale);
        tvScoreTitle.setAlpha(scale);

        tvGradeTime.setScaleX(scale);
        tvGradeTime.setScaleY(scale);
        tvGradeTime.setAlpha(scale);

    }
}
