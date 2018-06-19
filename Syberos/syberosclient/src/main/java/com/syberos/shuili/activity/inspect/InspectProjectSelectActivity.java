package com.syberos.shuili.activity.inspect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.InspectProblemInformation;
import com.syberos.shuili.entity.OnSiteInspectInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 稽查项目选择
 */
public class InspectProjectSelectActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private OnSiteInspectInformation information;
    private final static int REQUEST_CODE = 1915;

    public static final String SEND_BUNDLE_KEY = "OnSiteInspectInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    ListAdapter listAdapter;
    List<String> informationList = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_project_select;
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
        setActionBarTitle("稽察项目选择");

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (OnSiteInspectInformation)bundle.getSerializable(
                OnSiteInspectListActivity.SEND_BUNDLE_KEY);

        if (null != information) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
            listAdapter = new ListAdapter(this,
                    R.layout.activity_inspect_project_select_item);
            recyclerView.setAdapter(listAdapter);
            listAdapter.setOnItemClickListener(this);

            if (null == informationList) {
                informationList = new ArrayList<>();
            } else {
                informationList.clear();
            }

            informationList = information.getProjects();

            listAdapter.setData(informationList);
        }
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        InspectProblemInformation information = new InspectProblemInformation();
        information.setProject(informationList.get(position));
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this, InspectNewProblemActivity.class, false, bundle, REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                InspectProblemInformation information
                        = (InspectProblemInformation)data.getSerializableExtra(InspectNewProblemActivity.RESULT_KEY);

                ToastUtils.show(information.getDepartment());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
