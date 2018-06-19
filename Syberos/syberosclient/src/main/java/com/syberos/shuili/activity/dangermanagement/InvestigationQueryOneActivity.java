package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-23.
 */

public class InvestigationQueryOneActivity extends BaseActivity {
    private final String TAG = InvestigationQueryOneActivity.class.getSimpleName();
    @BindView(R.id.tl_tab)
    TabLayout tl_tab;

    @BindView(R.id.vp_content)
    ViewPager vp_content;
    TabAdapter tabAdapter;

    private static InvestigationQuery2Adatper m_adapter;
    static List<HiddenInvestigationTaskInfo> datas = new ArrayList<>();
    public static final String[] tabTitle = new String[]{"全部隐患", "重大隐患", "一般隐患"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_query_one_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle("隐患查询");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            InvestigationQuery2Fragment fragment = new InvestigationQuery2Fragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TabLayoutFragment.TABLAYOUT_FRAGMENT,i+1);
            fragment.setType(bundle);
            fragments.add(fragment);
        }
        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments, tabTitle);
        //给ViewPager设置适配器
        vp_content.setAdapter(tabAdapter);
        //将TabLayout和ViewPager关联起来。
        tl_tab.setupWithViewPager(vp_content);
        //设置可以滑动
        tl_tab.setTabMode(TabLayout.MODE_FIXED);
        m_adapter = new InvestigationQuery2Adatper(this,R.layout.activity_investigation_task_item);

    }
    public static class InvestigationQuery2Fragment extends TabLayoutFragment{
        public InvestigationQuery2Fragment(){

        }

        @Override
        protected void initData() {
            datas.clear();
            switch (type){
                case 1:
                    for(int i = 0; i< 5 ; i ++) {
                        HiddenInvestigationTaskInfo investigationInfo = new HiddenInvestigationTaskInfo();
                        datas.add(investigationInfo);
                    }
                    for(int i = 0; i< 5 ; i ++) {
                        HiddenInvestigationTaskInfo investigationInfo = new HiddenInvestigationTaskInfo();
                        datas.add(investigationInfo);
                    }
                    break;

                case 2:
                    for(int i = 0; i< 5 ; i ++) {
                        HiddenInvestigationTaskInfo investigationInfo = new HiddenInvestigationTaskInfo();
                        datas.add(investigationInfo);
                    }
                    break;

                case 3:
                    for(int i = 0; i< 5 ; i ++) {
                        HiddenInvestigationTaskInfo investigationInfo = new HiddenInvestigationTaskInfo();
                        datas.add(investigationInfo);
                    }
                    break;
            }
            recyclerViewLayout.setAdapter(m_adapter);
            m_adapter.setData(datas);
            m_adapter.notifyDataSetChanged();

        }
    }
    private class InvestigationQuery2Adatper extends CommonAdapter<HiddenInvestigationTaskInfo>{
        public InvestigationQuery2Adatper(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, HiddenInvestigationTaskInfo investigationInfo) {
            String type = investigationInfo.getHiddGrad();
            LinearLayout ll_type = null;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            ShapeDrawable bgShape = null;
            switch (type){
                case "0": {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(R.string.normal);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                }
                break;
                case "1": {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(R.string.danger);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                }
                break;

            }
            ( (TextView)(holder.getView(R.id.tv_title))).setText(investigationInfo.getHiddName());
            ( (TextView)(holder.getView(R.id.tv_time))).setText(investigationInfo.getRequCompDate());
            ( (TextView)(holder.getView(R.id.tv_name))).setText(investigationInfo.getEngName());
            ( (TextView)(holder.getView(R.id.tv_content))).setText(investigationInfo.getHiddClas());


        }
    }
}
