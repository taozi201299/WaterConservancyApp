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
import com.syberos.shuili.entity.thematic.hidden.HiddenEntryTest;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.fragment.HematicMapFragment;
import com.syberos.shuili.listener.OnItemClickListener;
import com.syberos.shuili.utils.MPChartUtil;
import com.syberos.shuili.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 * 隐患
 */
public class ThematicDetailHiddenFragment extends BaseLazyFragment {

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


    //RecyclerView
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_data_unit_1)
    TextView tvDataUnit1;
    @BindView(R.id.tv_data_unit_2)
    TextView tvDataUnit2;
    @BindView(R.id.tv_data_unit_3)
    TextView tvDataUnit3;
    @BindView(R.id.tv_data_unit_4)
    TextView tvDataUnit4;
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

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_hidden;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        onHiddenData(getHiddenEntryTest());

//        List<PieEntry> listSummarise = new ArrayList<>();
//        listSummarise.add(new PieEntry(16, "已排查单位数量"));
//        listSummarise.add(new PieEntry(16, "为排查单位数量"));
//        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieChartSummarized, listSummarise, false);
//        tvChartValue1.setText(16 + "");
//        tvChartValueTitle1.setText("已排查单位数量");
//        tvChartValue2.setText(16 + "");
//        tvChartValueTitle2.setText("为排查单位数量");

    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    public HiddenEntryTest getHiddenEntryTest() {
        return hiddenEntryTest;
    }

    public void setHiddenEntryTest(HiddenEntryTest hiddenEntryTest) {
        this.hiddenEntryTest = hiddenEntryTest;
    }

    HiddenEntryTest hiddenEntryTest;

    public void onHiddenData(HiddenEntryTest hiddenEntryTest) {
        ToastUtils.show("also had get Data");
        tvViewTitle.setText("");
        tvData1.setText(hiddenEntryTest.getData().getHIDDTOTALQUA() - hiddenEntryTest.getData().getHIDDRECTQUA() + "");
        tvDataTitle1.setText("未整改数量");

        tvData2.setText(hiddenEntryTest.getData().getHIDDTOTALQUA() + "");
        tvDataTitle2.setText("隐患总数量");

        List<PieEntry> listHiddenRate = new ArrayList<>();
        listHiddenRate.add(new PieEntry(hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA(), "一般隐患数量 " + hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA() + ""));
        listHiddenRate.add(new PieEntry(hiddenEntryTest.getData().getHIDDGRAD1TOTALQUA(), "重大隐患数量 " + hiddenEntryTest.getData().getHIDDGRAD1TOTALQUA() + ""));

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharHiddenRate, listHiddenRate, true);


        tvValue11.setText(hiddenEntryTest.getData().getHIDDGRAD0RECTQUA() + "");
        tvValue12.setText(hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA() - hiddenEntryTest.getData().getHIDDGRAD0RECTQUA() + "");
        tvValue13.setText(hiddenEntryTest.getData().getHIDDGRAD0LATEQUA() + "");
        tvValue14.setText(hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA() == 0 ? 0 + "" : hiddenEntryTest.getData().getHIDDGRAD0RECTQUA() / hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA() * 100.0 + "%");
        tvValue21.setText(hiddenEntryTest.getData().getHIDDGRAD0RECTQUA() + "");
        tvValue22.setText(hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA() - hiddenEntryTest.getData().getHIDDGRAD0RECTQUA() + "");
        tvValue23.setText(hiddenEntryTest.getData().getHIDDGRAD0LATEQUA() + "");
        tvValue24.setText(hiddenEntryTest.getData().getHIDDGRAD1LISTQUA()+"");
        tvValue25.setText( hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA()== 0 ? 0 + "" :hiddenEntryTest.getData().getHIDDGRAD0RECTQUA() / hiddenEntryTest.getData().getHIDDGRAD0TOTALQUA() * 100.0 + "%");

        List<ProjectEntry> list = new ArrayList<>();
        for (HiddenEntryTest.DataBean.ITEMDATABean bean : hiddenEntryTest.getData().getITEMDATA())
            list.add(new ProjectEntry(bean.getENG_GUID(), bean.getENG_NAME(), bean.getHIDDTOTALQUA()));

        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ThematicDetailProjActivity.class);
                intent.putExtra("typeValue", HematicMapFragment.Hidden);
                startActivity(intent);
            }
        });


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

    }

}
