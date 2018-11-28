package com.syberos.shuili.activity.wins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroupAll;
import com.syberos.shuili.entity.wins.BisWinsProj;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;

/**
 * 稽察项目选择
 */
public class InspectProjectSelectActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    ListAdapter listAdapter;
    BisWinsProj inspectionProjects = null;
    BisWinsGroupAll bisWinsGroupAll = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_project_select;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if(bisWinsGroupAll == null){
            Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
            bisWinsGroupAll = (BisWinsGroupAll) bundle.getSerializable("bisWinsGroupAll");
        }
        if(bisWinsGroupAll == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
         showDataLoadingDialog();
         getInspectionProject();
    }

    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarTitle("稽察项目选择");
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_inspect_project_select_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }
    private void getInspectionProject(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/bis/wins/proj/selectInspectionTeamAllProjectNames/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("bwgGuid",bisWinsGroupAll.getGuid());
       // params.put("bwgGuid","600fa3a0453640bc8212983a30cffa6b");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                inspectionProjects = gson.fromJson(result,BisWinsProj.class);
                if(inspectionProjects == null || inspectionProjects.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(inspectionProjects.dataSource.size() == 0){
                    ToastUtils.show("没有稽察项目");
                }else {
                    refreshUI();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
private void refreshUI(){
        listAdapter.setData(inspectionProjects.dataSource);
        listAdapter.notifyDataSetChanged();

}
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("inspectionProjects",inspectionProjects.dataSource.get(position));
        intentActivity(this, InspectNewProblemActivity.class, false, bundle);
    }

    private class ListAdapter extends CommonAdapter<BisWinsProj> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisWinsProj information) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getProjName());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
