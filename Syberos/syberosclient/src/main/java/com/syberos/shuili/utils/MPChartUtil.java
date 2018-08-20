package com.syberos.shuili.utils;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;

import java.util.ArrayList;
import java.util.List;

public class MPChartUtil {

    private static volatile MPChartUtil instance = null;

    public static MPChartUtil getInstance() {
        if (instance == null) {
            synchronized (MPChartUtil.class) {
                if (instance == null) {
                    return new MPChartUtil();
                }
            }
        }
        return instance;
    }

    public void initPieCharHiddenRate(Context context, PieChart pieChart, List<PieEntry> strings, boolean isShowLegend) {

        PieDataSet dataSet = new PieDataSet(strings, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(context.getResources().getColor(R.color.pie_chart_color_level_one));
        colors.add(context.getResources().getColor(R.color.pie_chart_color_level_two));
        colors.add(context.getResources().getColor(R.color.pie_chart_color_level_three));
        colors.add(context.getResources().getColor(R.color.pie_chart_color_level_four));
        colors.add(context.getResources().getColor(R.color.pie_chart_color_level_five));
        dataSet.setColors(colors);
        // 获取pieCahrt图列

//        pieCharHiddenRate.invalidate();
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
//        pieCharHiddenRate.setHoleRadius(0.8f);
        pieChart.setHoleRadius(65f);
        pieChart.setDrawHoleEnabled(true);

        pieChart.setHoleColor(Color.TRANSPARENT);
//        pieChart.setHoleColor(R.color.transparent);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setTransparentCircleRadius(65f);
        pieChart.setData(pieData);
        pieChart.setNoDataTextColor(Color.RED);
//        pieCharHiddenRate.show
        pieChart.setNoDataText("暂无数据");
        if (isShowLegend) {
            pieChart.setExtraOffsets(-10, 0, 10, 0);
        }
//        pieCharHiddenRate.setTranslationX(20);
//        pieCharHiddenRate.setPivotX();
        initLegend(context, pieChart, isShowLegend);
        pieChart.invalidate();

    }

    private void initLegend(Context context, PieChart pieChart, boolean isShowLegend) {
        if (isShowLegend) {
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
            l.setTextColor(context.getResources().getColor(R.color.text_gray_color));//设置图例标签文本的颜色
        } else {
            Legend l = pieChart.getLegend();
            l.setEnabled(false);
        }
    }
}
