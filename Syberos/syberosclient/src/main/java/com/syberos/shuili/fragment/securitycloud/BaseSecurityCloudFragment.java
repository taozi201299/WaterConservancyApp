package com.syberos.shuili.fragment.securitycloud;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.entity.securitycloud.SecurityCloudEntry;
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
//    ImageView imageView;
            DialPlateView viewDialPlate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pie_char)
    PieChart pieChart;
    @BindView(R.id.tv_score_title)
    TextView tvScoreTitle;
    String title;
    String titleDetail;
    SecurityCloudEntry securityCloudEntry;

    public void initViewData() {
        Gson gson = new Gson();
        if (strJsonData != null) {
            securityCloudEntry = gson.fromJson(strJsonData, SecurityCloudEntry.class);
        }
        int score = securityCloudEntry.getSynthesisInfoEntry().getScore();
        titleDetail = new String(new StringBuilder(title).append("·安全评分·").append(score).append("分"));
        tvTitle.setText(title);
        collapsingToolbarLayout.setTitle(titleDetail);
        tvScore.setText(score + "");
        viewDialPlate.updateData(score);

        initCharView();
    }

    public void updataData(String title, String strJsonData) {
        this.title = title;
        this.strJsonData = strJsonData;
        initData();
    }

    @SuppressLint("ValidFragment")
    public BaseSecurityCloudFragment(String title) {
        this.title = title;
    }

    @SuppressLint("ValidFragment")
    public BaseSecurityCloudFragment(String title, @Nullable String strJsonData) {
        this.title = title;
        this.strJsonData = strJsonData;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud_river_area;
    }

    @Override
    protected void initListener() {

    }

    private void initCharView() {
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(11f, "一般事故 11"));
        strings.add(new PieEntry(4f, "较大事故 4"));
        strings.add(new PieEntry(3f, "重大事故 3"));
        strings.add(new PieEntry(2f, "特大事故 2"));

        PieDataSet dataSet = new PieDataSet(strings, "Label");
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.pie_chart_color_level_one));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_two));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_three));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_four));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(false);

//        pieData.setValueFormatter(new PercentFormatter());
//        pieData.setValueTextSize(12f);

        Legend legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setTextSize(14);
        legend.setTextColor(getResources().getColor(R.color.text_gray_color));
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setXOffset(20);
        legend.setFormSize(14);
        legend.setFormToTextSpace(12);
        legend.setYOffset(20);
//        pieChart.invalidate();
//        是否允许点击
        pieChart.setTouchEnabled(false);
//        是否允许旋转
        pieChart.setRotationEnabled(false);
        Description description=new Description();
        description.setText("");
        pieChart.setDescription(description);
//        pieChart.setHoleRadius(0.8f);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setData(pieData);
        pieChart.setExtraLeftOffset(-80);
        pieChart.setNoDataText("暂无数据");

    }

    @Override
    protected void initData() {
        initViewData();

        appBarLayout.addOnOffsetChangedListener(this);
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
