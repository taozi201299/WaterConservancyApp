package com.syberos.shuili.activity.gong_zuo_kao_he;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.gong_zuo_kao_he.DeductMarksInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 工作考核-安全生产考核被检对象
 * 8.2.2.76	考核项目（BIS_WOAS_PROJ）
 * 考场组下的考核项目
 * 8.2.2.72	工作考核对象（BIS_WOAS_OBJ）
 */
public class InspectAssessObjectSelectActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "DeductMarksInfo";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    private ListAdapter listAdapter;
    private List<String> informationList = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_object_select;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle("现场考核");
        setActionBarRightVisible(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(this,
                R.layout.activity_inspect_assess_object_select_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);

        if (null == informationList) {
            informationList = new ArrayList<>();
        } else {
            informationList.clear();
        }

        for (int i = 0; i < 6; ++i) {
            informationList.add("山东省水利厅" + (i + 1));
        }

        listAdapter.setData(informationList);

        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        DeductMarksInfo information = new DeductMarksInfo();
        information.setUnit(informationList.get(position));
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this, InspectAssessNewDeductMarksActivity.class,
                false, bundle);
    }

    private class ListAdapter extends CommonAdapter<String> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final String information) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information);
        }
    }
}
