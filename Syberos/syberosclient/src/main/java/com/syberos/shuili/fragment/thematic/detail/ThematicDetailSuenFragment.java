package com.syberos.shuili.fragment.thematic.detail;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by BZB on 2018/8/11.
 * Project: Sybe    ros.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 安检执法
 */
public class ThematicDetailSuenFragment extends BaseLazyFragment {
    @BindView(R.id.tv_view_title)
    TextView tvViewTitle;
    @BindView(R.id.iv_mark_pot_2)
    ImageView ivMarkPot2;
    @BindView(R.id.tv_chart_value_2)
    TextView tvChartValue2;
    @BindView(R.id.tv_chart_value_title_2)
    TextView tvChartValueTitle2;
    @BindView(R.id.iv_mark_pot_4)
    ImageView ivMarkPot4;
    @BindView(R.id.tv_chart_value_4)
    TextView tvChartValue4;
    @BindView(R.id.tv_chart_value_title_4)
    TextView tvChartValueTitle4;
    @BindView(R.id.iv_mark_pot_1)
    ImageView ivMarkPot1;
    @BindView(R.id.tv_chart_value_1)
    TextView tvChartValue1;
    @BindView(R.id.tv_chart_value_title_1)
    TextView tvChartValueTitle1;
    @BindView(R.id.iv_mark_pot_3)
    ImageView ivMarkPot3;
    @BindView(R.id.tv_chart_value_3)
    TextView tvChartValue3;
    @BindView(R.id.tv_chart_value_title_3)
    TextView tvChartValueTitle3;
    @BindView(R.id.pie_chart_hidden_summarized)
    PieChart pieChartHiddenSummarized;
    @BindView(R.id.tv_data_1)
    TextView tvData1;
    @BindView(R.id.tv_data_title_1)
    TextView tvDataTitle1;
    @BindView(R.id.ll_data_1)
    LinearLayout llData1;
    @BindView(R.id.tv_data_2)
    TextView tvData2;
    @BindView(R.id.tv_data_title_2)
    TextView tvDataTitle2;
    @BindView(R.id.ll_data_2)
    LinearLayout llData2;
    @BindView(R.id.tv_data_3)
    TextView tvData3;
    @BindView(R.id.tv_data_title_3)
    TextView tvDataTitle3;
    @BindView(R.id.ll_data_3)
    LinearLayout llData3;
    @BindView(R.id.tv_data_4)
    TextView tvData4;
    @BindView(R.id.tv_data_title_4)
    TextView tvDataTitle4;
    @BindView(R.id.ll_data_4)
    LinearLayout llData4;
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_suen;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        int statusKey = bundle.getInt("statusKey");
        if (statusKey == 3 || statusKey == 4) {
            llData3.setVisibility(View.VISIBLE);
            tvData3.setText(5 + "");
            tvDataTitle3.setText("已整改单位数");
        }
        tvData1.setText(2 + "");
        tvDataTitle1.setText("被处罚单位数");
        tvData2.setText(5 + "");
        tvDataTitle2.setText("已整改单位数");


        List<ProjectEntry> list = new ArrayList<>();
        list.add(new ProjectEntry("rerw", "北京", 100));
        list.add(new ProjectEntry("rerw", "上海", 120));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));

        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
//        adapter.setListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
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

        pieChartHiddenSummarized.setVisibility(View.GONE);

        llData3.setVisibility(View.GONE);
        llData4.setVisibility(View.GONE);
    }

}
