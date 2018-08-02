package com.syberos.shuili.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.fragment.securitycloud.BaseSecurityCloudFragment;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by jidan on 18-3-10.
 */

public class SecurityCloudFragment extends BaseFragment {
    private final String TAG = SecurityCloudFragment.class.getSimpleName();
    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    BaseSecurityCloudFragment riverFragment;//流域机构
    BaseSecurityCloudFragment straightTubeFragment;//直管工程
    BaseSecurityCloudFragment supervisionFragment;//行业监管
    Fragment[] fragments;
    String strJsonData = "{\"accidentInfoEntry\":{\"accLevelFourCount\":0,\"accLevelOneCount\":10,\"accLevelThreeCount\":10,\"accLevelTwoCount\":10,\"deathCount\":10,\"score\":10,\"totalCount\":10},\"compScoreTrend\":{\"dataList\":[{\"date\":null,\"score\":10}],\"qualifiedScore\":10},\"hiddenInfoEntry\":{\"majorHadSupervisingCount\":10,\"majorHiddenCount\":10,\"majorHiddenHadRectifyCount\":10,\"majorHiddenNoRectifyCount\":10,\"majorLateNoRectifyCount\":10,\"noRectifyCount\":10,\"normalHiddenCount\":10,\"normalHiddenHadRectifyCount\":10,\"normalHiddenNoRectifyCount\":10,\"normalLateNoRectifyCount\":10,\"score\":10,\"totalHiddenCount\":10},\"rankList\":[{\"id\":null,\"name\":\"测试数据\",\"score\":10}],\"riskSourceEntry\":{\"hadControl\":10,\"hadRecord\":10,\"noControl\":10,\"noRecord\":10,\"score\":10},\"straightTubeManageEntry\":{\"dataList\":[{\"taskId\":\"1\",\"partReportCount\":10,\"partUnReportCount\":10},{\"taskId\":\"2\",\"partReportCount\": 10,\"partUnReportCount\": 10},{\"taskId\":\"3\",\"partReportCount\": 10,\"partUnReportCount\": 10}, {\"taskId\":\"4\",\"partReportCount\": 10,\"partUnReportCount\": 10}],\"perTrainingHours\":10,\"score\":10,\"trainingPersonCount\":10},\"supervisionMangeEntry\":{\"score\":10,\"standardLevelOneCount\":10,\"standardLevelThreeCount\":10,\"standardLevelTwoCount\":10,\"workAssessmentScore\":10},\"synthesisInfoEntry\":{\"chainRatio\":\"测试数据\",\"sameTimeRatio\":\"测试数据\",\"score\":88}}";
    private MyViewPagerAdapter mPageAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initViewAndData();
    }

    public void initViewAndData() {
        straightTubeFragment = new BaseSecurityCloudFragment(strJsonData, "1");
        riverFragment = new BaseSecurityCloudFragment(strJsonData, "2");
        supervisionFragment = new BaseSecurityCloudFragment(null, "3");

        fragments = new Fragment[]{straightTubeFragment, riverFragment, supervisionFragment};
        mPageAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(mPageAdapter);
        circleIndicator.setViewPager(viewpager);
        viewpager.setCurrentItem(1);
    }

    @Override
    protected void initView() {


    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        FragmentManager fragmentManager;

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }


    }
}
