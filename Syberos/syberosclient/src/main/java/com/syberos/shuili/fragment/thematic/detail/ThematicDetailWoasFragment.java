package com.syberos.shuili.fragment.thematic.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.securitycloud.SecurityCloudEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.view.DialPlateView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 工作考核
 */
public class ThematicDetailWoasFragment extends BaseLazyFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.view_dial_plate)
    DialPlateView viewDialPlate;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_score_title)
    TextView tvScoreTitle;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private XAxis xAxis;         //X坐标轴
    private YAxis yAxisLeft;
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_woas;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        viewDialPlate.updateData(86);
        tvScore.setText("86");
        tvScoreTitle.setText("平均得分");
        tvTitle.setText("2018年直管工程工作考核情况");

        List<ProjectEntry> list = new ArrayList<>();
        list.add(new ProjectEntry("rerw", "北京", 100));
        list.add(new ProjectEntry("rerw", "上海", 120));
        list.add(new ProjectEntry("rerw", "广东", 150));

        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }
    private void initLineCharView(LineChart lineChart, List<SecurityCloudEntry.SingleMonthScore> dataList) {
        LineData lineData = getLineData(dataList);
        xAxis = lineChart.getXAxis();
        yAxisLeft = lineChart.getAxisLeft();
//        lineChart.setBackgroundColor(Color.argb(200, 173, 215, 210));// 设置图表背景 参数是个Color对象
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description); //图表默认右下方的描述，参数是String对象
        lineChart.setNoDataText("暂无数据");   //没有数据时显示在中央的字符串，参数是String对象

        lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
//        lineChart.setGridBackgroundColor(Color.rgb(250, 0, 0));//设置格子背景色,参数是Color类型对象

        lineChart.setDrawBorders(false);     //设置图表内格子外的边框是否显示
        //Enabling / disabling interaction
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(false); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
//        lineChart.setMarker();
        lineChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
//        lineChart.setHighlightEnabled(false);  //If set to true, highlighting/selecting values via touch is possible for all underlying DataSets.
        lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
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
        xAxis.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        xAxis.setDrawGridLines(true); //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        xAxis.setTextColor(getResources().getColor(R.color.text_gray_color)); //X轴上的刻度的颜色
        xAxis.setTextSize(12); //X轴上的刻度的字的大小 单位dp
        xAxis.setTextColor(Color.parseColor("#004e96"));
//      xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
//        xAxis.setGridColor(getResources().getColor(R.color.color_blue_004e96)); //X轴上的刻度竖线的颜色
//        xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        xAxis.enableGridDashedLine(10, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标
        xAxis.isDrawLabelsEnabled();
        final List<String> dateList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
////            Date date = new Date(Long.parseLong(dataList.get(i).getDate()));
//            Date date = new Date(System.currentTimeMillis()+i*(1000*60*60*24*30));
//            @SuppressLint("SimpleDateFormat")
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
//            String strDate=format.format(date);
//            dateList.add(strDate);
//            LogUtil.e(TAG, "initLineCharView: strDate:"+(System.currentTimeMillis()+i*(1000*60*60*24*30))+"----" );
//        }
        dateList.add("一月");
        dateList.add("二月");
        dateList.add("三月");
        dateList.add("四月");
        dateList.add("五月");
        dateList.add("六月");
//        final String[] data = new String[]{"一月", "二月", "三月", "四月", "五月", "六月"};
        xAxis.setLabelCount(dateList.size() - 1);
//      X轴专用
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateList.get((int) ((value + 0.5)));//[(int) value];
            }
        });
        xAxis.setAvoidFirstLastClipping(false);
//        xAxis.setSpaceBetweenLabels(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//把坐标轴放在上下 参数有：TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE or BOTTOM_INSIDE.
        xAxis.setLabelRotationAngle(330);
        xAxis.setDrawLabels(true);
        //可以设置一条警戒线，如下：
        LimitLine ll = new LimitLine(80, "（合格线：" + 80 + "分)");
        ll.enableDashedLine(10, 8, 0);
        ll.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll.setLineColor(getResources().getColor(R.color.button_rush_0bede5));
        ll.setLineWidth(1f);
        ll.setTextColor(Color.WHITE);
        ll.setTextSize(12f);
        // .. and more styling options

//      Y轴专用

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        yAxisLeft.addLimitLine(ll);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setEnabled(true);
        yAxisLeft.setDrawLabels(false);
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
//        yAxis.resetAxisMaxValue();    //重新设置Y轴坐标最大为多少，自动调整
//        yAxis.resetAxisMinValue();    //重新设置Y轴坐标，自动调整
        yAxisLeft.setInverted(false);    //Y轴坐标反转,默认是false,即下小上大
        yAxisLeft.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        yAxisLeft.setSpaceBottom(10);    //Y轴坐标距底有多少距离，即留白
//        yAxis.setShowOnlyMinMax(false);    //参数如果为true Y轴坐标只显示最大值和最小值
        yAxisLeft.setLabelCount(100, false);    //第一个参数是Y轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  //参数是INSIDE_CHART(Y轴坐标在内部) 或 OUTSIDE_CHART(在外部（默认是这个）)
//        yAxis.setValueFormatter(new IAxisValueFormatter() {
        yAxisLeft.setDrawGridLines(false);
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return null;
//            }
//        });
//              Sets a custom ValueFormatter for this axis. This interface allows to format/modify
//              the original label text and instead return a customized text.


        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setEnabled(false);
        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
//        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
//        mLegend.setFormSize(2f);// 字体
//        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(1000); // 立即执行的动画,x轴
    }

    private LineData getLineData(List<SecurityCloudEntry.SingleMonthScore> dataList) {

        ArrayList<Entry> entries = new ArrayList<Entry>();     //坐标点的集合
//        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
        Random random = new Random(10);
        for (int i = 0; i < 6; i++) {
//            Entry entry = new Entry(i, dataList.get(i).getScore());

            Entry entry = new Entry(i, random.nextInt(100));
            entries.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "得分");    //坐标线，LineDataSet(坐标点的集合, 线的描述或名称);
//        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.isDrawCircleHoleEnabled();
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleHoleRadius(5);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setDrawValues(true);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        lineDataSet.setCircleRadius(10);
        lineDataSet.setLineWidth(2);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);     //以左边坐标轴为准 还是以右边坐标轴为基准
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>(); //坐标线的集合。
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);  //LineData(X坐标轴的集合, 坐标线的集合);
        lineChart.setData(data);   //为图表添加 数据
        lineChart.invalidate(); // 重新更新显示

        return data;
    }
}
