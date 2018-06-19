package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.adapter.TabAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.entity.InvestigationQuery1Info;
import com.syberos.shuili.fragment.TabLayoutFragment;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomScrollView;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-23.
 */

public class InvestigationQueryActivity extends BaseActivity implements CommonAdapter.OnItemClickListener ,ClearEditText.ITextChanged {

    private final String TAG = InvestigationQueryActivity.class.getSimpleName();
    @BindView(R.id.tl_tab)
    TabLayout tl_tab;

    @BindView(R.id.vp_content)
    ViewPager vp_content;

    TextView tv_action_bar_title;
    TabAdapter tabAdapter;

    @BindView(R.id.query_action_bar)
    LinearLayout ll_query_action_bar;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.ll_search)
    CustomScrollView ll_search;
    @BindView(R.id.tv_quitSearch)
    TextView tv_quitSearch;
    @BindView(R.id.ll_query)
    LinearLayout ll_query;

    ClearEditText clearEditText;
    ImageView iv_action_bar2_right;
    ImageView iv_action_bar2_right_search;

    private static InvestigationQuery1Adatper m_adapter;
    static List<InvestigationQuery1Info> datas = new ArrayList<>();

    public static final String[] tabTitle = new String[]{"直管工程", "流域机构", "行业监管"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_query_layout;
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
        tv_action_bar_title = (TextView)ll_query_action_bar.findViewById(R.id.tv_action_bar2_title);
        iv_action_bar2_right = (ImageView)ll_query_action_bar.findViewById(R.id.iv_action_bar2_right);
        iv_action_bar2_right_search = (ImageView)ll_query_action_bar.findViewById(R.id.iv_action_bar2_right_search);
        clearEditText = (ClearEditText)ll_query_action_bar.findViewById(R.id.clearEditText);
        tv_action_bar_title.setVisibility(View.INVISIBLE);
        iv_action_bar2_right.setVisibility(View.GONE);
        iv_action_bar2_right_search.setVisibility(View.VISIBLE);
        clearEditText.setTextChangedListener(this);
        iv_action_bar2_right_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_action_bar_title.setVisibility(View.GONE);
                iv_action_bar2_right_search.setVisibility(View.GONE);
                tv_quitSearch.setVisibility(View.VISIBLE);
                clearEditText.setVisibility(View.VISIBLE);
                vp_content.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                ll_search.setVisibility(View.VISIBLE);

                ArrayList<HiddenInvestigationTaskInfo> investigationInfos = new ArrayList<>();
                for(int i = 0; i< 3 ; i ++) {
                    HiddenInvestigationTaskInfo investigationInfo = new HiddenInvestigationTaskInfo();
                    investigationInfos.add(investigationInfo);
                }
                setSearchData(0,investigationInfos);

                ArrayList<InvestigationQuery1Info> investigationQuery1Infos = new ArrayList<>();
                for(int i = 0; i< 3 ; i ++) {
                    InvestigationQuery1Info info = new InvestigationQuery1Info();
                    info.setName("海淀区小月河水利河道工程");
                    investigationQuery1Infos.add(info);
                }
                setSearchData(1,investigationQuery1Infos);
            }

        });
        tv_quitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_action_bar_title.setVisibility(View.VISIBLE);
                iv_action_bar2_right_search.setVisibility(View.VISIBLE);
                tv_quitSearch.setVisibility(View.GONE);
                clearEditText.setVisibility(View.GONE);
                vp_content.setVisibility(View.VISIBLE);
                ll_content.setVisibility(View.VISIBLE);
                ll_search.setVisibility(View.GONE);
            }
        });
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            InvestigationQuery1Fragment fragment = new InvestigationQuery1Fragment();
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
        m_adapter = new InvestigationQuery1Adatper(mContext,R.layout.simple_list_row,datas);
        m_adapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {
        intentActivity(this,InvestigationQueryOneActivity.class,false,true);
    }

    @Override
    public void onTextChanged(String s) {
        ToastUtils.show(s);
    }

    public static class InvestigationQuery1Fragment extends TabLayoutFragment{
        public InvestigationQuery1Fragment(){

        }

        @Override
        protected void initData() {
            datas.clear();
            switch (type){
                case 1:
                case 2:
                case 3:
                    for(int i = 0 ; i <10 ;i++) {
                        InvestigationQuery1Info info = new InvestigationQuery1Info();
                        info.setName("海淀区小月河水利工程");
                        datas.add(info);
                    }
                    recyclerViewLayout.setAdapter(m_adapter);
                    m_adapter.setData(datas);
                    m_adapter.notifyDataSetChanged();
                    break;

            }

        }
    }
    public class InvestigationQuery1Adatper extends CommonAdapter<InvestigationQuery1Info> {

        public InvestigationQuery1Adatper(Context context, int layoutId, List<InvestigationQuery1Info> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, InvestigationQuery1Info investigationQuery1Info) {
            TextView tv = holder.getView(R.id.textView);
            tv.setText(investigationQuery1Info.getName());

        }
    }
    private void setSearchData(int type,List list){
        View view = null;
        switch (type){
            case 0: {
                LinearLayout ll_content0 = (LinearLayout)findViewById(R.id.ll_content0);
                ArrayList<HiddenInvestigationTaskInfo> datas = (ArrayList<HiddenInvestigationTaskInfo>) list;
                for (HiddenInvestigationTaskInfo investigationInfo : datas) {
                   view = getView(type);
                    String projectType = investigationInfo.getHiddGrad();
                    LinearLayout ll_type = null;
                    ll_type = (LinearLayout) (view.findViewById(R.id.ll_type));
                    ShapeDrawable bgShape = null;
                    switch (projectType) {
                        case "0": {
                            ((TextView) (view.findViewById(R.id.tv_type))).setText(R.string.normal);
                            ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                        }
                        break;
                        case "1": {
                            ((TextView) (view.findViewById(R.id.tv_type))).setText(R.string.danger);
                            ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                        }
                        break;

                    }
                    ((TextView) (view.findViewById(R.id.tv_title))).setText(investigationInfo.getHiddName());
                    ((TextView) (view.findViewById(R.id.tv_time))).setText(investigationInfo.getRequCompDate());
                    ((TextView) (view.findViewById(R.id.tv_name))).setText(investigationInfo.getEngName());
                    ((TextView) (view.findViewById(R.id.tv_content))).setText(investigationInfo.getHiddClas());
                    ll_content0.addView(view);

                }
            }
                break;
            case 1:
                ArrayList<InvestigationQuery1Info> datas1 = (ArrayList<InvestigationQuery1Info>) list;
                LinearLayout ll_content1 = (LinearLayout)findViewById(R.id.ll_content1);
                for(InvestigationQuery1Info investigationQuery1Info:datas1) {
                    view = getView(type);
                    TextView tv = view.findViewById(R.id.textView);
                    tv.setText(investigationQuery1Info.getName());
                    ll_content1.addView(view);
                }

                break;
            case 2:
                ArrayList<InvestigationQuery1Info> datas2 = (ArrayList<InvestigationQuery1Info>) list;
                LinearLayout ll_content2 = (LinearLayout)findViewById(R.id.ll_content2);
                for(InvestigationQuery1Info investigationQuery1Info:datas2) {
                    view = getView(type);
                    TextView tv = view.findViewById(R.id.textView);
                    tv.setText(investigationQuery1Info.getName());
                    ll_content2.addView(view);
                }
                break;
            case 3:
                ArrayList<InvestigationQuery1Info> datas3 = (ArrayList<InvestigationQuery1Info>) list;
                LinearLayout ll_content3 = (LinearLayout)findViewById(R.id.ll_content3);
                for(InvestigationQuery1Info investigationQuery1Info:datas3) {
                    view = getView(type);
                    TextView tv = view.findViewById(R.id.textView);
                    tv.setText(investigationQuery1Info.getName());
                    ll_content3.addView(view);
                }
                break;
        }

    }
    private View getView(int type){
        View view = null;
        switch (type){
            case 0:
                view = View.inflate(mContext, R.layout.activity_investigation_task_item,null);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case 1:
            case 2:
            case 3:
                view = LayoutInflater.from(mContext).inflate(R.layout.simple_list_row,null);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        }
        return view;
    }


}
