package com.syberos.shuili.activity.inspect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;

import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.InspectProblemInformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 稽查问题查询
 */
public class InspectQueryProblemListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String Title = "事故查询";

    public static final String SEND_BUNDLE_KEY = "InspectProblemInformation";

    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;

    ListAdapter listAdapter = null;
    List<InspectProblemInformation> inspectProblemList  = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_query_problem_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        inspectProblemList = new ArrayList<>();
        InspectProblemInformation problem1 = new InspectProblemInformation();
        problem1.setProject("河山墩水库工程项目");
        problem1.setType("前期工作");
        problem1.setDepartment("办公厅");
        problem1.setSeverity(InspectProblemInformation.SEVERITY_NORMAL);
        problem1.setContent("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem1.setAdvice("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem1.setTime("2017-08-07");
        inspectProblemList.add(problem1);

        InspectProblemInformation problem2 = new InspectProblemInformation();
        problem2.setProject("宝钢水库扩容改造项目");
        problem2.setType("计划方面");
        problem2.setDepartment("财务司");
        problem2.setSeverity(InspectProblemInformation.SEVERITY_BIG);
        problem2.setContent("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem2.setAdvice("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem2.setTime("2017-08-07");
        inspectProblemList.add(problem2);

        InspectProblemInformation problem3 = new InspectProblemInformation();
        problem3.setProject("官厅水库清污疏导项目");
        problem3.setType("建设管理");
        problem3.setDepartment("水土保持司");
        problem3.setSeverity(InspectProblemInformation.SEVERITY_LARGE);
        problem3.setContent("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem3.setAdvice("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem3.setTime("2017-08-07");
        inspectProblemList.add(problem3);

        InspectProblemInformation problem4 = new InspectProblemInformation();
        problem4.setProject("官厅水库清污疏导项目");
        problem4.setType("建设管理");
        problem4.setDepartment("水土保持司");
        problem4.setSeverity(InspectProblemInformation.SEVERITY_NORMAL);
        problem4.setContent("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem4.setAdvice("划拨维修经费5万元，对堤坝进行修整，确保水库安全运行，对损坏的堤坝进行修复，加固。");
        problem4.setTime("2017-08-07");
        inspectProblemList.add(problem4);

        listAdapter.setData(inspectProblemList);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.inspect_detail_problem_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        InspectProblemInformation problem = inspectProblemList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, problem);
        intentActivity(this,
                InspectProblemDetailActivity.class, false, bundle);
    }

    private class ListAdapter extends CommonAdapter<InspectProblemInformation> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, InspectProblemInformation problem) {
            int type = problem.getSeverity();
            LinearLayout ll_type = (LinearLayout) holder.getView(R.id.ll_type);
            TextView tv_type = (TextView) holder.getView(R.id.tv_type);
            switch (type) {
                case InspectProblemInformation.SEVERITY_NORMAL: {
                    tv_type.setText(R.string.severity_normal);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_color_inspect_type_normal));
                }
                break;
                case InspectProblemInformation.SEVERITY_BIG: {
                    tv_type.setText(R.string.severity_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_color_inspect_type_big));
                }
                break;
                case InspectProblemInformation.SEVERITY_LARGE: {
                    tv_type.setText(R.string.severity_large);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_color_inspect_type_large));
                }
                break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    problem.getProject());
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    problem.getTime());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    problem.getType());
            ((TextView) (holder.getView(R.id.tv_content))).setText(
                    problem.getContent());

        }
    }
}
