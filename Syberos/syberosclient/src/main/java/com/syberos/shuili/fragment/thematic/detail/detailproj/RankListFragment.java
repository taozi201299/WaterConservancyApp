package com.syberos.shuili.fragment.thematic.detail.detailproj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.RecyclerAdapterGeneral;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.thematicchart.PointEntry;
import com.syberos.shuili.entity.thematicchart.ProjectEntry;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RankListFragment extends BaseLazyFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerAdapterGeneral adapterGeneral;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_recycler_list;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToastUtils.show("RankListFragment onCreate has run");
    }

    public void updateData(List<ProjectEntry> list) {
        adapterGeneral.setData(list);
    }

    @Override
    protected void initData() {
        ToastUtils.show("init Data  run too");
        List<ProjectEntry> list = new ArrayList<>();
        list.add(new ProjectEntry("rerw", "北京", 100));
        list.add(new ProjectEntry("rerw", "上海", 120));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));
        list.add(new ProjectEntry("rerw", "广东", 150));

        adapterGeneral = new RecyclerAdapterGeneral(list, "个");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapterGeneral);
    }

    @Override
    protected void initView() {

    }


}