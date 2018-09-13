package com.syberos.shuili.activity.thematic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.thematic.haz.HazEntry;
import com.syberos.shuili.utils.MPChartUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/9/13.
 */

public class ThematicHazItemDetailActivity extends BaseActivity {
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
    @BindView(R.id.pie_char_haz_rate)
    PieChart pieCharHazRate;
    @BindView(R.id.tv_value_1_1)
    TextView tvValue11;
    @BindView(R.id.tv_value_1_2)
    TextView tvValue12;
    @BindView(R.id.tv_value_1_3)
    TextView tvValue13;
    @BindView(R.id.tv_late_value1)
    TextView tvLateValue1;
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
    @BindView(R.id.tv_value_2_5)
    TextView tvValue25;
    @BindView(R.id.tv_value_3_1)
    TextView tvValue31;
    @BindView(R.id.tv_value_3_2)
    TextView tvValue32;
    @BindView(R.id.tv_value_3_3)
    TextView tvValue33;
    @BindView(R.id.tv_value_3_4)
    TextView tvValue34;
    @BindView(R.id.tv_value_3_5)
    TextView tvValue35;
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private HazEntry.EveryEngBean hazEntry = null;


    @Override
    public int getLayoutId() {
         return R.layout.fragment_thematic_detail_haz;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        hazEntry = (HazEntry.EveryEngBean) getIntent().getSerializableExtra("hazData");
        tvDataTitle1.setText("已管控数量");
        tvData2.setText(hazEntry.getWGK()+"");
        tvDataTitle2.setText("未管控数量");

        List<PieEntry> dataList=new ArrayList<>();
        dataList.add(new PieEntry(hazEntry.getGENERALNOTREG(),"一般危险源数量 "+hazEntry.getGENERALNOTREG()));
        dataList.add(new PieEntry(hazEntry.getGENERALNOTREG(),"重大危险源数量 "+hazEntry.getGENERALNOTREG()));
        MPChartUtil.getInstance().initPieCharHiddenRate(mContext,pieCharHazRate,dataList,true);

        tvValue11.setText(hazEntry.getGENERALHAVECONTROL()+"");
        tvValue12.setText(hazEntry.getGENERALNOTCONTROL()+"");
        tvValue13.setText(hazEntry.getGENERALCONTROLRATE());

        tvValue21.setText(hazEntry.getMAJORHAVECONTROL()+"");
        tvValue22.setText(hazEntry.getMAJORNOTCONTROL()+"");
        tvValue23.setText(hazEntry.getMAJORCONTROLRATE());
        tvValue24.setText(hazEntry.getMAJORHAVEREG()+"");
        tvValue25.setText(hazEntry.getMAJORREGRATE());

        tvValue31.setText(10+"");
        tvValue32.setText(4+"");
        tvValue33.setText( "66%");
        tvValue34.setText(10+"");
        tvValue35.setText("80%");

    }

    @Override
    public void initView() {
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
        recyclerView.setVisibility(View.GONE);

    }
}
