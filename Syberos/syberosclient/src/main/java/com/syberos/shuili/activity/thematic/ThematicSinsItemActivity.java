package com.syberos.shuili.activity.thematic;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.shuili.callback.ErrorInfo;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.thematic.sins.SinsEntry;
import com.syberos.shuili.utils.MPChartUtil;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class ThematicSinsItemActivity extends BaseActivity {
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
    @BindView(R.id.pie_char_sins_rate)
    PieChart pieCharSinsRate;
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_chat_label)
            TextView tv_chat_label;

    SinsEntry.DataBean.SUBSINSDATABean  subsinsdataBean ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_thematic_detail_sins;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        subsinsdataBean = (SinsEntry.DataBean.SUBSINSDATABean) getIntent().getSerializableExtra("data");
        if(subsinsdataBean == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
        tvViewTitle.setText("");
        tvData1.setText(subsinsdataBean.getSINSQUA() + "");
        tvDataTitle1.setText("检查次数");
        tvData2.setText(subsinsdataBean.getSINSOBJQUA() + "");
        tvDataTitle2.setText("检查工程数量");
        tvData3.setText(subsinsdataBean.getSINSHIDDQUA() + "");
        tvDataTitle3.setText("隐患数量");
        tv_chat_label.setText("安全检查完成情况");
        tvData4.setVisibility(View.GONE);
        tvDataTitle4.setVisibility(View.GONE);
        tvListTitle.setText("安全检查统计");


        List<PieEntry> listHiddenRate = new ArrayList<>();
        listHiddenRate.add(new PieEntry(Float.valueOf(subsinsdataBean.getSINSHIDDRECTQUA()), "已检查 " + subsinsdataBean.getSINSHIDDRECTQUA() + ""));
        listHiddenRate.add(new PieEntry(Float.valueOf(subsinsdataBean.getSINSHIDDQUA()) - Float.valueOf(subsinsdataBean.getSINSHIDDRECTQUA()), "未检查 " +
                (Integer.valueOf(subsinsdataBean.getSINSHIDDQUA()) - Integer.valueOf(subsinsdataBean.getSINSHIDDRECTQUA())) + ""));

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharSinsRate, listHiddenRate, true);

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

        tvListTitle.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

    }

}
