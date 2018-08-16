package com.syberos.shuili.fragment.thematic.detail.detailproj;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 安全检查
 */
public class ThematicDetailSinsProjFragment extends BaseLazyFragment {

    private final String typeSummarise = "SUMMARISE";
    private final String typeHiddenRate = "HIDDENRATE";
    // 概括
    @BindView(R.id.tv_view_title)
    TextView tvViewTitle;
    @BindView(R.id.iv_mark_pot_2)
    ImageView ivMarkPot2;
    @BindView(R.id.tv_chart_value_1)
    TextView tvChartValue1;
    @BindView(R.id.tv_chart_value_title_1)
    TextView tvChartValueTitle1;
    @BindView(R.id.iv_mark_pot_4)
    ImageView ivMarkPot4;
    @BindView(R.id.tv_chart_value_2)
    TextView tvChartValue2;
    @BindView(R.id.tv_chart_value_title_2)
    TextView tvChartValueTitle2;
    @BindView(R.id.iv_mark_pot_1)
    ImageView ivMarkPot1;
    @BindView(R.id.tv_chart_value_3)
    TextView tvChartValue3;
    @BindView(R.id.tv_chart_value_title_3)
    TextView tvChartValueTitle3;
    @BindView(R.id.iv_mark_pot_3)
    ImageView ivMarkPot3;
    @BindView(R.id.tv_chart_value_4)
    TextView tvChartValue4;
    @BindView(R.id.tv_chart_value_title_4)
    TextView tvChartValueTitle4;
    @BindView(R.id.pie_chart_hidden_summarized)
    PieChart pieChartSummarized;
    @BindView(R.id.ll_data_1)
    LinearLayout llData1;
    @BindView(R.id.tv_data_1)
    TextView tvData1;
    @BindView(R.id.tv_data_title_1)
    TextView tvDataTitle1;
    @BindView(R.id.ll_data_2)
    LinearLayout llData2;
    @BindView(R.id.tv_data_2)
    TextView tvData2;
    @BindView(R.id.tv_data_title_2)
    TextView tvDataTitle2;
    @BindView(R.id.ll_data_3)
    LinearLayout llData3;
    @BindView(R.id.tv_data_3)
    TextView tvData3;
    @BindView(R.id.tv_data_title_3)
    TextView tvDataTitle3;
    @BindView(R.id.ll_data_4)
    LinearLayout llData4;
    @BindView(R.id.tv_data_4)
    TextView tvData4;
    @BindView(R.id.tv_data_title_4)
    TextView tvDataTitle4;

    //隐患等级占比
    @BindView(R.id.pie_char_hidden_rate)
    PieChart pieCharHiddenRate;
    @BindView(R.id.tv_count3)
    TextView tvCount3;
    @BindView(R.id.tv_had_rectified_value1)
    TextView tvHadRectifiedValue1;
    @BindView(R.id.tv_no_rectified_value1)
    TextView tvNoRectifiedValue1;
    @BindView(R.id.tv_late_value1)
    TextView tvLateValue1;
    @BindView(R.id.div)
    View div;
    @BindView(R.id.tv_count4)
    TextView tvCount4;
    @BindView(R.id.tv_had_rectified_value2)
    TextView tvHadRectifiedValue2;
    @BindView(R.id.tv_no_rectified_value2)
    TextView tvNoRectifiedValue2;
    @BindView(R.id.tv_late_value2)
    TextView tvLateValue2;
    @BindView(R.id.tv_had_supervise_value)
    TextView tvHadSuperviseValue;

    //RecyclerView
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.layout_recycler_view)
    View layoutRecycler;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_hidden;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvViewTitle.setText("");

//        List<PieEntry> listSummarise = new ArrayList<>();
//        listSummarise.add(new PieEntry(16, "已排查单位数量"));
//        listSummarise.add(new PieEntry(16, "为排查单位数量"));
//        initPieCharHiddenRate(pieChartSummarized, listSummarise, 2, typeSummarise);
//        tvChartValue1.setText(16 + "");
//        tvChartValueTitle1.setText("已排查单位数量");
//        tvChartValue2.setText(16 + "");
//        tvChartValueTitle2.setText("为排查单位数量");

        tvData1.setText(20 + "");
        tvDataTitle1.setText("未整改数量");

        tvData2.setText(1000 + "");
        tvDataTitle2.setText("隐患总数量");

        List<PieEntry> listHiddenRate = new ArrayList<>();
        listHiddenRate.add(new PieEntry(20, "一般隐患数量 " + 20 + ""));
        listHiddenRate.add(new PieEntry(30, "重大隐患数量 " + 30 + ""));

        initPieCharHiddenRate(pieCharHiddenRate, listHiddenRate, 2, typeHiddenRate);


        List<ProjectEntry> list = new ArrayList<>();
        list.add(new ProjectEntry("rerw", "北京", 100));
        list.add(new ProjectEntry("rerw", "上海", 120));
        list.add(new ProjectEntry("rerw", "广东", 150));

//        HiddenRecyclerAdapter adapter = new HiddenRecyclerAdapter(list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(adapter);
//        adapter.setListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ToastUtils.show("第" + position + "个 item 点击了");
//            }
//        });
    }

    @Override
    protected void initView() {
        ivMarkPot1.setVisibility(View.GONE);
        tvChartValue1.setVisibility(View.GONE);
        tvChartValueTitle1.setVisibility(View.GONE);

        ivMarkPot2.setVisibility(View.GONE);
        tvChartValue2.setVisibility(View.GONE);
        tvChartValueTitle2.setVisibility(View.GONE);

        ivMarkPot3.setVisibility(View.GONE);
        tvChartValue3.setVisibility(View.GONE);
        tvChartValueTitle3.setVisibility(View.GONE);

        ivMarkPot4.setVisibility(View.GONE);
        tvChartValue4.setVisibility(View.GONE);
        tvChartValueTitle4.setVisibility(View.GONE);

        pieChartSummarized.setVisibility(View.GONE);

        llData3.setVisibility(View.GONE);
        llData4.setVisibility(View.GONE);

        layoutRecycler.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.GONE);

    }


    private void initPieCharHiddenRate(PieChart pieChart, List<PieEntry> strings, int count, String type) {

        PieDataSet dataSet = new PieDataSet(strings, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.pie_chart_color_level_one));
        colors.add(getResources().getColor(R.color.pie_chart_color_level_two));
//        colors.add(getResources().getColor(R.color.pie_chart_color_level_three));
//        colors.add(getResources().getColor(R.color.pie_chart_color_level_four));
        dataSet.setColors(colors);
        // 获取pieCahrt图列
        Legend l = pieChart.getLegend();
        if (type.equals(typeHiddenRate)) {
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
        } else {
            l.setEnabled(false);
        }
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
        if (type.equals(typeHiddenRate)) {
            pieChart.setExtraOffsets(-10, 0, 10, 0);
        }
//        pieCharHiddenRate.setTranslationX(20);
//        pieCharHiddenRate.setPivotX();
        pieChart.invalidate();
    }

    class HiddenRecyclerAdapter extends BaseRecyclerAdapter<HiddenViewHold> {
        private OnItemClickListener listener;
        List<ProjectEntry> list;
        HiddenViewHold holder;

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public HiddenRecyclerAdapter(List<ProjectEntry> list) {
            this.list = list;
        }

        @Override
        public HiddenViewHold getViewHolder(View view) {
            return holder;
        }

        @Override
        public HiddenViewHold onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thematic_hidden_rake_view, null);
            holder = new HiddenViewHold(view);
            return holder;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(HiddenViewHold holder, @SuppressLint("RecyclerView") final int position, boolean isItem) {
            holder.tvName.setText(list.get(position).getProName());
            holder.tvNum.setText(position + "");
            holder.tvScore.setText(list.get(position).getProTroubleCount() + "");
            holder.tvScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });

            holder.tvNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, position);
                }
            });


        }

        @Override
        public int getAdapterItemCount() {
            return list.size();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class HiddenViewHold extends RecyclerView.ViewHolder {
        TextView tvNum;
        TextView tvName;
        TextView tvScore;

        public HiddenViewHold(View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_position);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }
}
