package com.syberos.shuili.fragment.thematic.detail;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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
import com.syberos.shuili.activity.thematic.ThematicWinsItemActivity;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.fragment.thematic.detail.detailproj.RankListFragment;
import com.syberos.shuili.listener.OnItemClickListener;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.MPChartUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 水利稽察
 */
public class ThematicDetailWinsFragment extends BaseLazyFragment {

    List<Fragment> fragments = new ArrayList<>();
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
    @BindView(R.id.pie_char_rate)
    PieChart pieCharRate;
    @BindView(R.id.pie_char_sum)
    PieChart pieCharSum;


    @BindView(R.id.tv_list_title)
    TextView tvListTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String[] mTitles = {"流域", "直管"};
    private FragmentPagerAdapter pagerAdapter;

    WinsEntry winsEntry;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_wins;
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        if(winsEntry == null)return;
        String time = CommonUtils.getCurrentDateYMD();
        String[] arrayTime = time.split("-");
        tvViewTitle.setText(arrayTime[0]+"年"+arrayTime[1]+"月"+"稽察情况");
        tvData1.setText(winsEntry.getData().getWINSQUA());
        tvDataTitle1.setText("稽察次数");
        tvData2.setText(winsEntry.getData().getWINSPROJQUA());
        tvDataTitle2.setText("项目数量");
        tvData3.setText(winsEntry.getData().getWINSPROBQUA());
        tvDataTitle3.setText("问题数量");

        tvDataUnit1.setText("个");
        tvDataUnit2.setText("个");
        tvDataUnit3.setText("个");
//        tvData4.setText("50");
//        tvDataTitle4.setText("隐患数量");

//        fragments = new ArrayList<>();
        if (fragments.size() > 0)
            fragments.clear();
        fragments.add(new RankListFragment());
        fragments.add(new RankListFragment());


        pagerAdapter = new RankViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);


        List<PieEntry> listHiddenRate = new ArrayList<>();
        ArrayList<WinsEntry.WinsProbTypeDataBean> datas = winsEntry.getData().getWINSPROBTYPEDATA();
        for(WinsEntry.WinsProbTypeDataBean bean:datas){
            PieEntry pieEntry = new PieEntry(Float.valueOf(bean.getWINSPROBQUA()),bean.getPROBCLASSNAME()+ " "+ bean.getWINSPROBQUA());
            listHiddenRate.add(pieEntry);
        }

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharRate, listHiddenRate, true);

        List<PieEntry> listHiddenSum = new ArrayList<>();
        ArrayList<WinsEntry.WinsProbCateDataBean> dataBeans = winsEntry.getData().getWINSPROBCATEDATA();
        for(WinsEntry.WinsProbCateDataBean bean : dataBeans){
            PieEntry pieEntry = new PieEntry(Float.valueOf(bean.getWINSPROBQUA()),bean.getPROBCLASSNAME() + bean.getWINSPROBQUA());
            listHiddenSum.add(pieEntry);
        }

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharSum, listHiddenSum, true);

        ArrayList<ProjectEntry> list = new ArrayList<>();
        for(WinsEntry.SubWinsDataBean bean : winsEntry.getData().getSUBWINSDATA()){
            ProjectEntry projectEntry = new ProjectEntry(bean.getOBJGUID(),bean.getOBJNAME(),bean.getWINSPROBQUA());
            list.add(projectEntry);
        }
        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list,"个");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });

    }

    @Override
    protected void initView() {
        tvListTitle.setText("水利稽察统计");
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

        llData4.setVisibility(View.GONE);
        pieChartHiddenSummarized.setVisibility(View.GONE);

    }

    public void setData(WinsEntry winsEntry){
        this.winsEntry = winsEntry;
    }
    class RankViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public RankViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        public RankViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
        return fragments.size();
    }
}

}
