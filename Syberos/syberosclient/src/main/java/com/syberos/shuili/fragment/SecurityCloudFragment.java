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
    BaseSecurityCloudFragment baseFragment;//流域机构
    BaseSecurityCloudFragment straightTubeFragment;//直管工程
    BaseSecurityCloudFragment supervisionFragment;//行业监管
    Fragment[] fragments;
    String strJsonData = "{\"accidentInfoEntry\":{\"accLevelFourCount\":0,\"accLevelOneCount\":0,\"accLevelThreeCount\":0,\"accLevelTwoCount\":0,\"deathCount\":0,\"score\":0,\"totalCount\":0},\"compScoreTrend\":{\"dataList\":[{\"date\":null,\"score\":0}],\"qualifiedScore\":0},\"hiddenInfoEntry\":{\"majorHadSupervisingCount\":0,\"majorHiddenCount\":0,\"majorHiddenHadRectifyCount\":0,\"majorHiddenNoRectifyCount\":0,\"majorLateNoRectifyCount\":0,\"noRectifyCount\":0,\"normalHiddenCount\":0,\"normalHiddenHadRectifyCount\":0,\"normalHiddenNoRectifyCount\":0,\"normalLateNoRectifyCount\":0,\"score\":0,\"totalHiddenCount\":0},\"rankList\":[{\"id\":null,\"name\":null,\"score\":0}],\"riskSourceEntry\":{\"hadControl\":0,\"hadRecord\":0,\"noControl\":0,\"noRecord\":0,\"score\":0},\"straightTubeManageEntry\":{\"dataList\":[{\"partReportCount\":0,\"partUnReportCount\":0}],\"perTrainingHours\":0,\"score\":0,\"trainingPersonCount\":0},\"supervisionMangeEntry\":{\"score\":0,\"standardLevelOneCount\":0,\"standardLevelThreeCount\":0,\"standardLevelTwoCount\":0,\"workAssessmentScore\":0},\"synthesisInfoEntry\":{\"chainRatio\":null,\"sameTimeRatio\":null,\"score\":88}}";

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        baseFragment = new BaseSecurityCloudFragment("流域机构", strJsonData);
        straightTubeFragment = new BaseSecurityCloudFragment("直管工程", strJsonData);
        supervisionFragment = new BaseSecurityCloudFragment("行业监管", strJsonData);
        fragments = new Fragment[]{straightTubeFragment, baseFragment, supervisionFragment};
        MyViewPagerAdapter mPageAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        viewpager.setOffscreenPageLimit(3);
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
