package com.syberos.shuili.fragment;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseFragment;
import com.syberos.shuili.fragment.securitycloud.BaseSecurityCloudFragment;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by jidan on 18-3-10.
 */

public class SecurityCloudFragment extends BaseFragment {
    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private final String TAG = SecurityCloudFragment.class.getSimpleName();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_security_cloud;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
//        MyViewPagerAdapter mPageAdapter=new MyViewPagerAdapter(getFragmentManager());
//        viewpager.setAdapter(mPageAdapter);
//        viewpager.setOffscreenPageLimit(3);
//        circleIndicator.setViewPager(viewpager);
    }

    @Override
    protected void initView() {


    }
    class MyViewPagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments={new BaseSecurityCloudFragment(),new BaseSecurityCloudFragment(),new BaseSecurityCloudFragment()};
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[0];
        }

        @Override
        public int getCount() {
            return 3;
        }


    }
}
