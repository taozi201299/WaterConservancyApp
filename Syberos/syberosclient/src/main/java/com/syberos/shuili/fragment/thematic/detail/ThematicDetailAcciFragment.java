package com.syberos.shuili.fragment.thematic.detail;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.activity.thematic.ThematicAcciItemActivity;
import com.syberos.shuili.activity.thematic.ThematicHazItemDetailActivity;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.fragment.HematicMapFragment;
import com.syberos.shuili.listener.OnItemClickListener;
import com.syberos.shuili.utils.MPChartUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    private AcciEntry acciEntry;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_acci;
    }

    @Override
    protected void initListener() {

    }
    
    @Override
    protected void initData() {
        if(acciEntry == null)return;
        tvData1.setText(acciEntry.getData().getACCITOTALNUM());
        tvDataTitle1.setText("事故数量");
        tvData2.setText(acciEntry.getData().getACCITOTALCASNUM());
        tvDataTitle2.setText("死亡人数");

        tvChartValue1.setText(acciEntry.getData().getACCITGRAD1NUM());
        tvChartValueTitle1.setText("一般事故");
        tvChartValue2.setText(acciEntry.getData().getACCITGRAD2NUM());
        tvChartValueTitle2.setText("较大事故");
        tvChartValue3.setText(acciEntry.getData().getACCITGRAD3NUM());
        tvChartValueTitle3.setText("重大事故");
        tvChartValue4.setText(acciEntry.getData().getACCITGRAD4NUM());
        tvChartValueTitle4.setText("特大事故");


        tvValue11.setText(acciEntry.getData().getACCITGRAD1NUM());
        tvValue12.setText(acciEntry.getData().getACCIGRAD1CASNUM());
        tvValue13.setText(acciEntry.getData().getACCIGRAD1SERINJNUM());
        tvValue14.setText(acciEntry.getData().getACCIGRAD1ECONLOSS());

        tvValue21.setText(acciEntry.getData().getACCITGRAD2NUM());
        tvValue22.setText(acciEntry.getData().getACCIGRAD2CASNUM());
        tvValue23.setText(acciEntry.getData().getACCIGRAD2SERINJNUM());
        tvValue24.setText(acciEntry.getData().getACCIGRAD2ECONLOSS());
        List<PieEntry> pieList = new ArrayList<>();
        pieList.add(new PieEntry(Integer.valueOf(acciEntry.getData().getACCITGRAD1NUM()), "1 "));
        pieList.add(new PieEntry(Integer.valueOf(acciEntry.getData().getACCITGRAD2NUM()), "2 "));
        pieList.add(new PieEntry(Integer.valueOf(acciEntry.getData().getACCITGRAD3NUM()), "3 "));
        pieList.add(new PieEntry(Integer.valueOf(acciEntry.getData().getACCITGRAD4NUM()), "4 "));

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieChartHiddenSummarized, pieList, false);

        List<ProjectEntry> list = new ArrayList<>();
        ArrayList<AcciEntry.ITEMDATABean> datas = acciEntry.getData().getITEMDATA();
        for(AcciEntry.ITEMDATABean bean :datas){
            ProjectEntry projectEntry = new ProjectEntry(bean.getOBJGUID(),bean.getOBJNAME(),bean.getACCITOTALNUM());
            list.add(projectEntry);

        }
        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list,"个");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ThematicAcciItemActivity.class);
                intent.putExtra("data",acciEntry.getData().getITEMDATA().get(position).getACCIDATA());
                intent.putExtra("num",acciEntry.getData().getITEMDATA().get(position).getACCITOTALNUM());
                intent.putExtra("name",acciEntry.getData().getITEMDATA().get(position).getOBJNAME());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
        llData3.setVisibility(View.GONE);
        llData4.setVisibility(View.GONE);
        tvListTitle.setText("事故统计");
    }

    public void setData(AcciEntry acciEntry){
        this.acciEntry = acciEntry;
    }

}
