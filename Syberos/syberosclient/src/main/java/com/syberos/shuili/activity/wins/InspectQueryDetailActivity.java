package com.syberos.shuili.activity.wins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.inspect.InspectPlan;
import com.syberos.shuili.utils.Strings;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 稽查查询详情
 */
public class InspectQueryDetailActivity extends TranslucentActivity {

    private InspectPlan inspectPlan;

    @BindView(R.id.ll_groups)
    LinearLayout ll_groups;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

    @OnClick(R.id.tv_detail)
    void onDetailClicked() {
        intentActivity(this,
                InspectQueryProblemListActivity.class, false, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_query_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        inspectPlan = (InspectPlan) bundle.getSerializable(
                InspectQueryListActivity.SEND_BUNDLE_KEY);

        if (null != inspectPlan) {

            tv_action_bar_title.setText(inspectPlan.getName());

            List<String> groups = inspectPlan.getGroups();
            addGroupItems(groups);
        }
    }

    private void addGroupItems(final List<String> groups) {
        if (groups.size() > 0) {
            for (String group : groups) {
                addGroupItem(group);
            }
        }
    }

    private void addGroupItem(final String groupName) {
        View layout = LayoutInflater.from(this).inflate(R.layout.layout_inspect_query_group_item,
                ll_groups, false);

        TextView name = (TextView) layout.findViewById(R.id.tv_name);
        name.setText(groupName);

        ll_groups.addView(layout);
    }
}
