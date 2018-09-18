package com.syberos.shuili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jidan on 18-3-11.
 */

public class TabAdapter extends FragmentPagerAdapter {
    Fragment currentFragment;
    private List<Fragment> fragments;
    private String[] tabTitle;
    private FragmentManager fm;
    private boolean[] flags;//标识,重新设置fragment时全设为true

    public TabAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabTitle) {
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;

    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        setCurrentFragment((Fragment) object);
        super.setPrimaryItem(container, position, object);
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (flags != null && flags[position]) {
            /**得到缓存的fragment, 拿到tag并替换成新的fragment*/
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            String fragmentTag = fragment.getTag();
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            fragment = fragments.get(position);
            ft.add(container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commit();
            /**替换完成后设为false*/
            flags[position] = false;
            if (fragment != null) {
                return fragment;
            } else {
                return super.instantiateItem(container, position);
            }
        } else {
            return super.instantiateItem(container, position);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.fragments != null) {
            flags = new boolean[fragments.size()];
            for (int i = 0; i < fragments.size(); i++) {
                flags[i] = true;
            }
        }
        this.fragments = fragments;
        notifyDataSetChanged();
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
