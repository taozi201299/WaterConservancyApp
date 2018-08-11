package com.syberos.shuili.fragment.thematic.detail;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 隐患
 */
public class ThematicDetailHiddenFragment extends BaseLazyFragment {
    @BindView(R.id.pie_char)
    PieChart pieChart;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_hidden;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        List<PieEntry> list=new ArrayList<>();
        list.add(new PieEntry(16, "一般事故" ));
        list.add(new PieEntry(16, "较大事故" ));

//        initPieCharView(pieChart,list);
    }

    @Override
    protected void initView() {

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

}
