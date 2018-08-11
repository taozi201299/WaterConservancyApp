package com.syberos.shuili.fragment.thematic;

import android.content.Context;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by BZB on 2018/7/9.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.chart.
 */
public class ChartFragment extends BaseLazyFragment {
    private Context context;
    @BindView(R.id.pie_char)
    PieChart pieChart;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {


    }
    private void initCharView(){
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"aaa"));
        strings.add(new PieEntry(70f,"bbb"));
        PieDataSet dataSet = new PieDataSet(strings,"Label");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.yellow));
        colors.add(getResources().getColor(R.color.blue));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(12f);

        pieChart.setData(pieData);
        pieChart.invalidate();

        Description description = new Description();
        description.setText("description");
        pieChart.setDescription(description);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);
    }
    @Override
    protected void initView() {
        initCharView();

    }
}
