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

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        baseFragment = new BaseSecurityCloudFragment("流域机构","66");
        straightTubeFragment = new BaseSecurityCloudFragment("直管工程","88");
        supervisionFragment = new BaseSecurityCloudFragment("行业监管","56");
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
