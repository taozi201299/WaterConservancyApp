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
import com.syberos.shuili.activity.thematic.ThematicSinsItemActivity;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematic.sins.SinsEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.listener.OnItemClickListener;
import com.syberos.shuili.utils.MPChartUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by BZB on 2018/8/11.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.thematic.detail.
 * <p>
 * 安全检查
 */
public class ThematicDetailSinsFragment extends BaseLazyFragment {

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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private String[] mTitles = {"流域", "直管", "监管"};
    private RankViewPagerAdapter pagerAdapter;
    List<Fragment> fragments = new ArrayList<>();

//    @BindView(R.id.recycler_view)

//    RecyclerView recyclerView;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_detail_sins;
    }

    @Override
    protected boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initListener() {
//        tabCenter.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                viewPager.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tabCenter.setCurrentTab(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    SinsEntry sinsEntry=null;

    public SinsEntry getSinsEntry() {
        return sinsEntry;
    }

    public void setSinsEntry(SinsEntry sinsEntry) {
        this.sinsEntry = sinsEntry;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onSinsData(final SinsEntry sinsEntry) {
        if (sinsEntry != null) {
            setSinsEntry(sinsEntry);
            tvViewTitle.setText("");
            tvData1.setText(sinsEntry.getData().getSINSQUA() + "");
            tvDataTitle1.setText("检查次数");
            tvData2.setText(sinsEntry.getData().getSINSOBJQUA() + "");
            tvDataTitle2.setText("检查工程数量");
//            tvData3.setText(sinsEntry.getData().getSINSOBJCOMPQUA() + "");
//            tvDataTitle3.setText("问题数量");
            tvData3.setText(sinsEntry.getData().getSINSHIDDQUA() + "");
            tvDataTitle3.setText("隐患数量");

            tvData4.setVisibility(View.GONE);
            tvDataTitle4.setVisibility(View.GONE);
            tvListTitle.setText("安全检查统计");


            List<PieEntry> listHiddenRate = new ArrayList<>();
            listHiddenRate.add(new PieEntry(Float.valueOf(sinsEntry.getData().getSINSHIDDRECTQUA()), "已检查 " + sinsEntry.getData().getSINSHIDDRECTQUA() + ""));
            listHiddenRate.add(new PieEntry(Float.valueOf(sinsEntry.getData().getSINSHIDDQUA()) - Float.valueOf(sinsEntry.getData().getSINSHIDDRECTQUA()), "未检查 " + (Integer.valueOf(sinsEntry.getData().getSINSHIDDQUA())- Integer.valueOf(sinsEntry.getData().getSINSHIDDRECTQUA())) + ""));

            MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharSinsRate, listHiddenRate, true);

            List<ProjectEntry> list = new ArrayList<>();
            for (SinsEntry.DataBean.SUBSINSDATABean bean : sinsEntry.getData().getSUBSINSDATA()) {
                list.add(new ProjectEntry(bean.getOBJGUID(), bean.getOBJNAME(), bean.getSINSHIDDQUA()));
            }
            RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list,"个");
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(adapter);
            adapter.setListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), ThematicSinsItemActivity.class);
                    intent.putExtra("data",sinsEntry.getData().getSUBSINSDATA().get(position));
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    protected void initData() {
//        if(sinsEntry==null){

//        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(sinsEntry==null){
            resetData();
        }
    }

    private void resetData() {
        setSinsEntry(sinsEntry);
        tvViewTitle.setText("");
        tvData1.setText("");
        tvData2.setText("");
        tvData3.setText("");

        tvData4.setVisibility(View.GONE);
        tvDataTitle4.setVisibility(View.GONE);

        List<PieEntry> listHiddenRate = new ArrayList<>();

        MPChartUtil.getInstance().initPieCharHiddenRate(mContext, pieCharSinsRate, listHiddenRate, true);

        List<ProjectEntry> list = new ArrayList<>();

        RecyclerAdapterGeneral adapter = new RecyclerAdapterGeneral(list,"个");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
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


//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

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
//            RankListFragment fragment=new RankListFragment();
//            return  fragment;
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
