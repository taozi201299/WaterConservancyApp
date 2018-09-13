package com.syberos.shuili.fragment.thematic.detail.detailproj;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.utils.MPChartUtil;

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
public class ThematicDetailHiddenProjFragment extends BaseLazyFragment {

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
    @BindView(R.id.tv_had_no_rectified_value1)
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

        tvData1.setText(20 + "");
        tvDataTitle1.setText("未整改数量");

        tvData2.setText(1000 + "");
        tvDataTitle2.setText("隐患总数量");

        List<PieEntry> listHiddenRate = new ArrayList<>();
        listHiddenRate.add(new PieEntry(20, "一般隐患数量 " + 20 + ""));
        listHiddenRate.add(new PieEntry(30, "重大隐患数量 " + 30 + ""));

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharHiddenRate, listHiddenRate, true);


        List<ProjectEntry> list = new ArrayList<>();
        list.add(new ProjectEntry("rerw", "北京", "100"));
        list.add(new ProjectEntry("rerw", "上海", "120"));
        list.add(new ProjectEntry("rerw", "广东", "150"));

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
    }
}