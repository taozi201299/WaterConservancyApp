package com.syberos.shuili.activity.thematic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.thematic.stans.StanDirectEntry;
import com.syberos.shuili.entity.thematic.stans.StanSuperviseEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.listener.OnItemClickListener;
import com.syberos.shuili.utils.MPChartUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/9/26.
 */

public class ThematicStansItemActivity extends BaseActivity {
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
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    StanDirectEntry.BookOrgBean bookOrgBean;
    StanDirectEntry.EveryOrgBean everyOrgBean;
    StanDirectEntry.CountOrgBean countOrgBean;

    StanSuperviseEntry.EveryOrgBean everyOrgBean1;
    StanSuperviseEntry.BookOrg bookOrg;
    StanSuperviseEntry.CountOrg countOrg;

    String type;

    @Override
    public int getLayoutId() {
         return R.layout.fragment_thematic_detail_stan;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        if("0".equals(type)){
            bookOrgBean = (StanDirectEntry.BookOrgBean) getIntent().getSerializableExtra("book");
            countOrgBean = (StanDirectEntry.CountOrgBean) getIntent().getSerializableExtra("count");
            everyOrgBean = (StanDirectEntry.EveryOrgBean) getIntent().getSerializableExtra("every");

        }else {
            bookOrg = (StanSuperviseEntry.BookOrg) getIntent().getSerializableExtra("book");
            countOrg = (StanSuperviseEntry.CountOrg) getIntent().getSerializableExtra("count");
            everyOrgBean1 = (StanSuperviseEntry.EveryOrgBean) getIntent().getSerializableExtra("every");
        }
        int yJCount = 0 ; // 一级达标数量
        int eRCount = 0;  // 二级达标数量
        int sJCount  = 0; // 三级达标数量

        int cXCount ; // 证书撤销
        int zCCount;  //  证书正常
        int cQCount ; // 证书超期
        if("1".equals(type)){
            yJCount = Integer.valueOf(everyOrgBean1.getYJ());
            eRCount = Integer.valueOf(everyOrgBean1.getEJ());
            sJCount = Integer.valueOf(everyOrgBean1.getSJ());
            cXCount = Integer.valueOf(bookOrg.getCX());
            zCCount = Integer.valueOf(bookOrg.getZC());
            cQCount = Integer.valueOf(bookOrg.getCQ());
            tvData1.setText(yJCount + eRCount + sJCount +"");
            tvDataTitle1.setText("达标企业数量");
            tvData2.setVisibility(View.GONE);
            tvDataTitle2.setVisibility(View.GONE);
            tvValue11.setText(zCCount + "");
            tvValue12.setText(cXCount + "");
            tvValue13.setText(cQCount + "");
            List<PieEntry> dataList = new ArrayList<>();
            dataList.add(new PieEntry(Float.valueOf(String.valueOf(yJCount)), "一级达标数量 " + yJCount));
            dataList.add(new PieEntry(Float.valueOf(String.valueOf(eRCount)), "二级达标数量 " + eRCount));
            dataList.add(new PieEntry(Float.valueOf(String.valueOf(sJCount)), "三级达标数量 " + sJCount));
            MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharHazRate, dataList, true);

        }else if("0".equals(type)){
            yJCount = Integer.valueOf(countOrgBean.getYJ());
            eRCount = Integer.valueOf(countOrgBean.getER());
            sJCount = Integer.valueOf(countOrgBean.getSJ());
            cXCount = Integer.valueOf(bookOrgBean.getCX());
            zCCount = Integer.valueOf(bookOrgBean.getZC());
            cQCount = Integer.valueOf(bookOrgBean.getCQ());
            tvData1.setText(yJCount + eRCount + sJCount +"");
            tvDataTitle1.setText("达标企业数量");
            tvData2.setVisibility(View.GONE);
            tvDataTitle2.setVisibility(View.GONE);
            tvValue11.setText(zCCount + "");
            tvValue12.setText(cXCount + "");
            tvValue13.setText(cQCount + "");
            List<PieEntry> dataList = new ArrayList<>();
            dataList.add(new PieEntry(Float.valueOf(String.valueOf(yJCount)), "一级达标数量 " + yJCount));
            dataList.add(new PieEntry(Float.valueOf(String.valueOf(eRCount)), "二级达标数量 " + eRCount));
            dataList.add(new PieEntry(Float.valueOf(String.valueOf(sJCount)), "三级达标数量 " + sJCount));
            MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharHazRate, dataList, true);
        }
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
        tvListTitle.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);


    }

}
