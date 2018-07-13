package com.syberos.shuili.fragment.chart;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;

import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseLazyFragment;

/**
 * Created by BZB on 2018/7/9.
 * Project: Syberos.
 * Package：com.syberos.shuili.fragment.chart.
 */
@SuppressLint("ValidFragment")
public class ThematicFragmentsItem extends BaseLazyFragment {
    BaseLazyFragment mFragment =null;
    FragmentTransaction transaction;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_thematic_fragment_item;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFragment = new HiddenChartFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (!mFragment.isAdded()) { // 先判断是否被add过
            transaction.add(R.id.fragment_content, mFragment).commit();
        } else {
            transaction.show(mFragment).commit();
        }
    }
    /**
     * 修改显示的内容 不会重新加载（相当于做了缓存处理，切换时仍能有数据显示）
     **/
    public void switchContent(BaseLazyFragment to) {
        try {
            if (mFragment != to) {
                transaction = getFragmentManager().beginTransaction();
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mFragment).add(R.id.fragment_content, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(mFragment).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                }
                mFragment = to;
            }else {
                transaction.hide(mFragment).show(to).commitAllowingStateLoss();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
