package com.syberos.shuili.activity.thematic;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.utils.MPChartUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class ThematicHiddenItemActivity extends BaseActivity {
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
    @BindView(R.id.tv_data_unit_1)
    TextView tvDataUnit1;
    @BindView(R.id.tv_data_title_1)
    TextView tvDataTitle1;
    @BindView(R.id.ll_data_1)
    LinearLayout llData1;
    @BindView(R.id.tv_data_2)
    TextView tvData2;
    @BindView(R.id.tv_data_unit_2)
    TextView tvDataUnit2;
    @BindView(R.id.tv_data_title_2)
    TextView tvDataTitle2;
    @BindView(R.id.ll_data_2)
    LinearLayout llData2;
    @BindView(R.id.tv_data_3)
    TextView tvData3;
    @BindView(R.id.tv_data_unit_3)
    TextView tvDataUnit3;
    @BindView(R.id.tv_data_title_3)
    TextView tvDataTitle3;
    @BindView(R.id.ll_data_3)
    LinearLayout llData3;
    @BindView(R.id.tv_data_4)
    TextView tvData4;
    @BindView(R.id.tv_data_unit_4)
    TextView tvDataUnit4;
    @BindView(R.id.tv_data_title_4)
    TextView tvDataTitle4;
    @BindView(R.id.ll_data_4)
    LinearLayout llData4;
    @BindView(R.id.pie_char_hidden_rate)
    PieChart pieCharHiddenRate;
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
    @BindView(R.id.tv_value_2_5)
    TextView tvValue25;
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.line)
    View line;

    private HiddenEntry.DataBean.ITEMDATABean itemdataBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_thematic_detail_hidden;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        itemdataBean = (HiddenEntry.DataBean.ITEMDATABean) getIntent().getSerializableExtra("data");
        tvViewTitle.setText(itemdataBean.getOBJNAME());
        tvData1.setText(itemdataBean.getHIDDTOTALQUA() - itemdataBean.getHIDDRECTQUA() + "");
        tvDataTitle1.setText("未整改数量");

        tvData2.setText(itemdataBean.getHIDDTOTALQUA() + "");
        tvDataTitle2.setText("隐患总数量");

        List<PieEntry> listHiddenRate = new ArrayList<>();
        listHiddenRate.add(new PieEntry(itemdataBean.getHIDDGRAD1TOTALQUA(), "一般隐患数量 " + itemdataBean.getHIDDGRAD1TOTALQUA() + ""));
        listHiddenRate.add(new PieEntry(itemdataBean.getHIDDGRAD2TOTALQUA(), "重大隐患数量 " + itemdataBean.getHIDDGRAD2TOTALQUA() + ""));

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharHiddenRate, listHiddenRate, true);


        tvValue11.setText(itemdataBean.getHIDDGRAD1RECTQUA() + "");
        tvValue12.setText(itemdataBean.getHIDDGRAD1TOTALQUA() - itemdataBean.getHIDDGRAD1RECTQUA() + "");
        tvValue13.setText(itemdataBean.getHIDDGRAD1LATEQUA() + "");
        tvValue14.setText(itemdataBean.getHIDDGRAD1TOTALQUA() == 0 ? 0 + "" : itemdataBean.getHIDDGRAD1RECTQUA() / itemdataBean.getHIDDGRAD1TOTALQUA() * 100.0 + "%");

        tvValue21.setText(itemdataBean.getHIDDGRAD2RECTQUA() + "");
        tvValue22.setText(itemdataBean.getHIDDGRAD2TOTALQUA() - itemdataBean.getHIDDGRAD2RECTQUA() + "");
        tvValue23.setText(itemdataBean.getHIDDGRAD2LATEQUA() + "");
        tvValue24.setText(itemdataBean.getHIDDGRAD2LISTQUA() + "");
        tvValue25.setText(itemdataBean.getHIDDGRAD2TOTALQUA() == 0 ? 0 + "" : itemdataBean.getHIDDGRAD2RECTQUA() / itemdataBean.getHIDDGRAD2TOTALQUA() * 100.0 + "%");

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
        tvListTitle.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
