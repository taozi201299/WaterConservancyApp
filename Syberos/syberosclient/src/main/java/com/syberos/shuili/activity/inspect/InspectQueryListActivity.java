package com.syberos.shuili.activity.inspect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.inspect.InspectPlan;
import com.syberos.shuili.entity.inspect.InspectPlanGroup;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 稽查查询列表
 */
public class InspectQueryListActivity extends BaseActivity {

    private final String Title = "事故查询";

    public static final String SEND_BUNDLE_KEY = "InspectPlan";

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;

    private HashMap<String, List<InspectPlan>> groupMap = new HashMap<>();

    private GroupedListAdapter groupedListAdapter;

    private List<InspectPlanGroup> inspectPlanGroups;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_query_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);

        groupedListAdapter
                = new GroupedListAdapter(this,
                inspectPlanGroups);

        groupedListAdapter.setOnChildClickListener(
                new GroupedRecyclerViewAdapter.OnChildClickListener() {
                    @Override
                    public void onChildClick(GroupedRecyclerViewAdapter adapter,
                                             BaseViewHolder holder,
                                             int groupPosition, int childPosition) {

                        Bundle bundle = new Bundle();
                        List<InspectPlan> children
                                = inspectPlanGroups.get(groupPosition).getChildren();
                        InspectPlan inspectPlan = children.get(childPosition);
                        bundle.putSerializable(SEND_BUNDLE_KEY, inspectPlan);

                        intentActivity(InspectQueryListActivity.this,
                                InspectQueryDetailActivity.class,
                                false, bundle);
                    }
                });

        recyclerView.setAdapter(groupedListAdapter);

        groupedListAdapter.notifyDataSetChanged();

        refreshUI();
    }

    private void refreshUI() {
        if (inspectPlanGroups == null) {
            inspectPlanGroups = new ArrayList<>();
        }
        inspectPlanGroups.clear();

        for (int m = 0; m < 2; m++) {

            List<InspectPlan> infos = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                InspectPlan item = new InspectPlan();
                item.setName("稽察方案名称" + (i + 1));
                item.setTime("2017-07-07 —— 2017-08-07");
                item.setBatch("2017年度第三批稽察");

                List<String> groups = new ArrayList<>();
                groups.add("小组名称1");
                groups.add("小组名称2");
                item.setGroups(groups);

                item.setSpecial("赵博士");
                item.setAssistant("王某某");
                item.setExperts("李白，杜甫，苏轼，苏哲");
                item.setProject("对象工程1，对象工程2");
                item.setProblemCount("4");
                infos.add(item);
            }

            groupMap.put("稽察计划" + (m + 1), infos);
        }

        Object[] headArray = groupMap.keySet().toArray();
        int len = headArray.length;
        for (int i = 0; i < len; i++) {
            String headCode = headArray[i].toString();
            inspectPlanGroups.add(new InspectPlanGroup(headCode, groupMap.get(headCode)));
            groupedListAdapter.setData(inspectPlanGroups);
            groupedListAdapter.notifyDataSetChanged();
        }
    }

    private class GroupedListAdapter extends GroupedRecyclerViewAdapter {


        private List<InspectPlanGroup> mGroups;

        public GroupedListAdapter(
                Context context, List<InspectPlanGroup> groups) {
            super(context);
            mGroups = groups;
        }

        public void setData(List<InspectPlanGroup> groups) {
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            List<InspectPlan> children = mGroups.get(groupPosition).getChildren();
            return children == null ? 0 : children.size();
        }

        @Override
        public boolean hasHeader(int groupPosition) {
            return true;
        }

        /**
         * 返回false表示没有组尾
         *
         * @param groupPosition
         * @return
         */
        @Override
        public boolean hasFooter(int groupPosition) {
            return false;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @return
         */
        @Override
        public int getFooterLayout(int viewType) {
            return 0;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @param holder
         * @param groupPosition
         */
        @Override
        public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public int getHeaderLayout(int viewType) {
            return R.layout.adapter_header;
        }

        @Override
        public int getChildLayout(int viewType) {
            return R.layout.activity_inspect_query_list_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            InspectPlanGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          int groupPosition, int childPosition) {
            InspectPlan inspectPlan
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            holder.setText(R.id.tv_title, inspectPlan.getName());
            holder.setText(R.id.tv_time, inspectPlan.getTime());
            holder.setText(R.id.tv_batch, inspectPlan.getBatch());

        }
    }
}
