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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
    RecyclerAdapter recyclerAdapter;
    private List<PieEntry> accPieEntrys;
    private XAxis xAxis;         //X坐标轴
    private YAxis yAxis;         //Y坐标轴
    private LineChart lineChart;

    @SuppressLint("ValidFragment")
    public BaseSecurityCloudFragment(@Nullable String strJsonData, String type) {

        this.strJsonData = strJsonData;
        this.type = type;
    }

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

    /**
     * 初始化曲线图
     *
     * @param compScoreTrend
     */
    private void initTrendView(SecurityCloudEntry.CompScoreTrend compScoreTrend) {
        lineChart = viewGradeTrend.findViewById(R.id.line_chart);
        initLineCharView(lineChart, getLineData());

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

            initPieCharView(pieCharManager, list);
        }
    }

    /**
     * 初始化风险源数据
     *
     * @param riskSourceEntry
     */
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
                initPieCharView((PieChart) pieCharRisk.findViewById(R.id.pie_char_risk), riskPieEntrys);
            }
        } else {
            ToastUtils.show("获取风险源数据错误");
        }
    }

    /**
     * 初始化隐患数据
     *
     * @param hiddenInfoEntry
     */
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

    /**
     * 初始化事故数据
     *
     * @param accidentInfoEntry
     */
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
            initPieCharView((PieChart) cardViewAcc.findViewById(R.id.pie_char_acc), accPieEntrys);
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

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud_river_area;
    }

    @Override
    protected void initListener() {

    }

    private void initLineCharView(LineChart lineChart, LineData lineData) {
        xAxis = lineChart.getXAxis();
        yAxis = lineChart.getAxisLeft();

//        lineChart.setBackgroundColor(Color.argb(200, 173, 215, 210));// 设置图表背景 参数是个Color对象

//        lineChart.setDescription(new Description()); //图表默认右下方的描述，参数是String对象
//        lineChart.setDescriptionColor(Color.rgb(227, 135, 0));  //上面字的颜色，参数是Color对象
//      lineChart.setDescriptionPosition(400f,600f);    //上面字的位置，参数是float类型，像素，从图表左上角开始计算
//      lineChart.setDescriptionTypeface();     //上面字的字体，参数是Typeface 对象
//        lineChart.setDescriptionTextSize(16);    //上面字的大小，float类型[6,16]

        lineChart.setNoDataText("没有数据呢(⊙o⊙)");   //没有数据时显示在中央的字符串，参数是String对象

        lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
//        lineChart.setGridBackgroundColor(Color.rgb(250, 0, 0));//设置格子背景色,参数是Color类型对象

        lineChart.setDrawBorders(true);     //设置图表内格子外的边框是否显示
        lineChart.setBorderColor(Color.TRANSPARENT
        );   //上面的边框颜色
        lineChart.setBorderWidth(20);       //上面边框的宽度，float类型，dp单位
//      lineChart.setMaxVisibleValueCount();设置图表能显示的最大值，仅当setDrawValues()属性值为true时有用


        //Interaction with the Chart 图表的交互

        //Enabling / disabling interaction
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽

        lineChart.setScaleEnabled(true);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴

        lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true

//        lineChart.setHighlightEnabled(false);  //If set to true, highlighting/selecting values via touch is possible for all underlying DataSets.
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true

        lineChart.setAutoScaleMinMaxEnabled(false);


        // Chart fling / deceleration
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。


        //Highlighting programmatically

//        highlightValues(Highlight[] highs)
//               Highlights the values at the given indices in the given DataSets. Provide null or an empty array to undo all highlighting.
//        highlightValue(int xIndex, int dataSetIndex)
//               Highlights the value at the given x-index in the given DataSet. Provide -1 as the x-index or dataSetIndex to undo all highlighting.
//        getHighlighted()
//               Returns an Highlight[] array that contains information about all highlighted entries, their x-index and dataset-index.


        //其他请参考https://github.com/PhilJay/MPAndroidChart/wiki/Interaction-with-the-Chart
        //如手势相关方法，选择回调方法


//        The Axis 坐标轴相关的,XY轴通用
        xAxis.setEnabled(true);     //是否显示X坐标轴 及 对应的刻度竖线，默认是true
        xAxis.setDrawAxisLine(true); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxis.setDrawGridLines(true); //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true

        xAxis.setTextColor(Color.rgb(145, 13, 64)); //X轴上的刻度的颜色
        xAxis.setTextSize(5); //X轴上的刻度的字的大小 单位dp
//      xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
        xAxis.setGridColor(Color.rgb(145, 13, 64)); //X轴上的刻度竖线的颜色
        xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        xAxis.enableGridDashedLine(40, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标


        //可以设置一条警戒线，如下：
        LimitLine ll = new LimitLine(10f, "警戒线");
        ll.setLineColor(Color.RED);
        ll.setLineWidth(4f);
        ll.setTextColor(Color.GRAY);
        ll.setTextSize(12f);
        // .. and more styling options
        xAxis.addLimitLine(ll);


//      X轴专用
//        xAxis.setLabelsToSkip(1);    //设置坐标相隔多少，参数是int类型
//        xAxis.resetLabelsToSkip();   //将自动计算坐标相隔多少
        xAxis.setAvoidFirstLastClipping(true);
//        xAxis.setSpaceBetweenLabels(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);//把坐标轴放在上下 参数有：TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE or BOTTOM_INSIDE.

//      Y轴专用
        yAxis.setStartAtZero(false);    //设置Y轴坐标是否从0开始
        yAxis.setAxisMaxValue(50);    //设置Y轴坐标最大为多少
//        yAxis.resetAxisMaxValue();    //重新设置Y轴坐标最大为多少，自动调整
        yAxis.setAxisMinValue(10);    //设置Y轴坐标最小为多少
//        yAxis.resetAxisMinValue();    //重新设置Y轴坐标，自动调整
        yAxis.setInverted(false);    //Y轴坐标反转,默认是false,即下小上大
        yAxis.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        yAxis.setSpaceBottom(0);    //Y轴坐标距底有多少距离，即留白
//        yAxis.setShowOnlyMinMax(false);    //参数如果为true Y轴坐标只显示最大值和最小值
        yAxis.setLabelCount(10, false);    //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  //参数是INSIDE_CHART(Y轴坐标在内部) 或 OUTSIDE_CHART(在外部（默认是这个）)
//      yAxis.setValueFormatter(YAxisValueFormatterf);
//              Sets a custom ValueFormatter for this axis. This interface allows to format/modify
//              the original label text and instead return a customized text.


        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(2f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(1000); // 立即执行的动画,x轴
    }

    private LineData getLineData() {

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();     //坐标点的集合
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

        Entry c1e1 = new Entry(100.000f, 1); //坐标点的值，Entry(Y坐标，X坐标)；
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(50.000f, 2);
        valsComp1.add(c1e2);

        Entry c2e1 = new Entry(30.000f, 1); //坐标点的值，Entry(Y坐标，X坐标)；
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(80.000f, 3);
        valsComp2.add(c2e2);

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company");    //坐标线，LineDataSet(坐标点的集合, 线的描述或名称);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);     //以左边坐标轴为准 还是以右边坐标轴为基准
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>(); //坐标线的集合。
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        ArrayList<String> xVals = new ArrayList<String>();      //X坐标轴的值的集合
        xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");
        xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");

        LineData data = new LineData(dataSets);  //LineData(X坐标轴的集合, 坐标线的集合);
        lineChart.setData(data);   //为图表添加 数据
        lineChart.invalidate(); // 重新更新显示

        return data;
    }

    private void initPieCharView(PieChart pieChart, List<PieEntry> strings) {


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
}
