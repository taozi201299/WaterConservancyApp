package com.syberos.shuili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jidan on 18-3-11.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] tabTitle;


    public TabAdapter(FragmentManager fm, List<Fragment> fragments,String[]tabTitle) {
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //设置tablayout标题
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];

    }
}
