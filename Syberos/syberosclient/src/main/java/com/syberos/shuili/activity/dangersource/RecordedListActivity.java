package com.syberos.shuili.activity.dangersource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.DangerousInformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 该文件暂不使用
 */
public class RecordedListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = RecordedListActivity.class.getSimpleName();

    private final String Title = "已备案";

    public static final String SEND_BUNDLE_KEY = "DangerousInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;
    DangerousListAdapter listAdapter;
    List<DangerousInformation> informationList = null;

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        DangerousInformation information = informationList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);

        intentActivity(this, RecordedDetailActivity.class, false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recorded_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new DangerousListAdapter(this,
                R.layout.activity_recorded_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);

        if (null == informationList) {
            informationList = new ArrayList<>();
        } else {
            informationList.clear();
        }

        int j = 0;
        for (int i = 0; i < 2; i++) {
            DangerousInformation dangerousInformation = new DangerousInformation();
            dangerousInformation.setDangerousContent("路面坍塌范围较大，对周边道路设施有较大" +
                    "路面坍塌范围较大，对周边道路设施有较大");
            dangerousInformation.setDangerousName("易燃易爆物品");
            dangerousInformation.setDangerousUnit("朝阳区双桥水利所");
            dangerousInformation.setDangerousType(DangerousInformation.TYPE_NORMAL);
            dangerousInformation.setTime("12-20");
            dangerousInformation.setDangerousId(String.valueOf(j));
            ++j;
            informationList.add(dangerousInformation);
        }

        for (int i = 0; i < 2; i++) {
            DangerousInformation dangerousInformation = new DangerousInformation();
            dangerousInformation.setDangerousContent("路面坍塌范围较大，对周边道路设施有较大" +
                    "路面坍塌范围较大，对周边道路设施有较大");
            dangerousInformation.setDangerousName("易燃易爆物品");
            dangerousInformation.setDangerousUnit("朝阳区双桥水利所");
            dangerousInformation.setDangerousType(DangerousInformation.TYPE_BIGGEST);
            dangerousInformation.setTime("12-21");
            dangerousInformation.setDangerousId(String.valueOf(j));
            ++j;
            informationList.add(dangerousInformation);
        }

        listAdapter.setData(informationList);
    }

    private class DangerousListAdapter extends CommonAdapter<DangerousInformation> {
        public DangerousListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, DangerousInformation dangerousInformation) {
            int type = dangerousInformation.getDangerousType();
            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            switch (type) {
                case DangerousInformation.TYPE_NORMAL: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_normal);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                }
                break;
                case DangerousInformation.TYPE_BIGGEST: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_big_shape));
                }
                break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    dangerousInformation.getDangerousName());
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    dangerousInformation.getTime());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    dangerousInformation.getDangerousUnit());
            ((TextView) (holder.getView(R.id.tv_content))).setText(
                    dangerousInformation.getDangerousContent());
        }
    }
}
