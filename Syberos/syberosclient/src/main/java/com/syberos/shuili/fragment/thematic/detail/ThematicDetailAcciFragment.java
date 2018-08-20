package com.syberos.shuili.fragment.thematic.detail;

import android.content.Intent;
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
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.thematic.ThematicDetailProjActivity;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.fragment.HematicMapFragment;
import com.syberos.shuili.listener.OnItemClickListener;
import com.syberos.shuili.utils.MPChartUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 事故
 */
public class ThematicDetailAcciFragment extends BaseLazyFragment {
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


    //    统计信息
    @BindView(R.id.tv_value_1_1)
    TextView tvValue11;
    @BindView(R.id.tv_value_1_2)
    TextView tvValue12;
    @BindView(R.id.tv_value_1_3)
    TextView tvValue13;
    @BindView(R.id.tv_value_1_4)
    TextView tvValue14;
    @BindView(R.id.div)
    View div;
    @BindView(R.id.tv_value_2_1)
    TextView tvValue21;
    @BindView(R.id.tv_value_2_2)
    TextView tvValue22;
    @BindView(R.id.tv_value_2_3)
    TextView tvValue23;
    @BindView(R.id.tv_value_2_4)
    TextView tvValue24;

    //    list
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_acci;
    }

    @Override
    protected void initListener() {

    }
    
    @Override
    protected void initData() {
        tvData1.setText("11");
        tvDataTitle1.setText("事故数量");
        tvData2.setText("11");
        tvDataTitle2.setText("死亡人数");

        tvChartValue1.setText("4");
        tvChartValueTitle1.setText("一般事故");
        tvChartValue2.setText("16");
        tvChartValueTitle2.setText("较大事故");
        tvChartValue3.setText("4");
        tvChartValueTitle3.setText("重大事故");
        tvChartValue4.setText("16");
        tvChartValueTitle4.setText("特大事故");


        tvValue11.setText("10");
        tvValue12.setText("4");
        tvValue13.setText("6");
        tvValue14.setText("200 万");

        tvValue21.setText("6");
        tvValue22.setText("4");
        tvValue23.setText("2");
        tvValue24.setText("200万");
        List<PieEntry> pieList = new ArrayList<>();
        pieList.add(new PieEntry(4, "1 "));
        pieList.add(new PieEntry(16, "2 "));
        pieList.add(new PieEntry(4, "3 "));
        pieList.add(new PieEntry(16, "4 "));

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieChartHiddenSummarized, pieList, false);

        List<ProjectEntry> list = new ArrayList<>();
        list.add(new ProjectEntry("rerw", "北京", 100));
        list.add(new ProjectEntry("rerw", "上海", 120));
        list.add(new ProjectEntry("rerw", "广东", 150));

        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ThematicDetailHiddenProjFragment thematicDetailHiddenProjFragment=new ThematicDetailHiddenProjFragment();
//                getFragmentManager().beginTransaction().add(thematicDetailHiddenProjFragment,"ddd").commitAllowingStateLoss();
//                FragmentTransaction transaction=mContext.getSupportFragmentManager().beginTransaction();
//                transaction.add(thematicDetailHiddenProjFragment,thematicDetailHiddenProjFragment.getClass().getName()).commit();
                Intent intent = new Intent(getActivity(), ThematicDetailProjActivity.class);
                intent.putExtra("typeValue", HematicMapFragment.Acci);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
        llData3.setVisibility(View.GONE);
        llData4.setVisibility(View.GONE);
    }

}
