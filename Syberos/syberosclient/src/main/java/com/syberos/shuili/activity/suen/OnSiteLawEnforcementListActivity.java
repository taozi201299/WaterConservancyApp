package com.syberos.shuili.activity.suen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.objCase.ObjCase;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * 行政版功能模块
 * 现场执法
 */
public class OnSiteLawEnforcementListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String Title = "现场执法";
    private final static int REQUEST_CODE = 1939;

    public static final String SEND_BUNDLE_KEY = "objCase";

    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;

    private ListAdapter listAdapter = null;
    ObjCase objCase = null;
    ArrayList<ObjCase>datas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_law_enforcement_list;
    }

    @Override
    public void initListener() {
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getObjCaseList();
    }
    private void getObjCaseList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/case/objCases/";
        urlTags.add(url);
        HashMap<String,String>param = new HashMap<>();
        param.put("suneOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objCase = gson.fromJson(result,ObjCase.class);
                if(objCase == null || objCase.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }else if(objCase.dataSource.size() == 0){
                    ToastUtils.show("没有相关的任务");
                    return;
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
    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Suen);
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_on_site_law_enforcement_list_item);
        recyclerView.setAdapter(listAdapter);

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjCase information = objCase.dataSource.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this,
                OnSiteLawEnforcementDetailActivity.class, false, bundle);
    }

    private void refreshUI(){
        datas.clear();
        for(ObjCase item: objCase.dataSource){
            if(item.getIfArch().equals("1")){
                continue;
            }else {
                datas.add(item);
            }
        }
        listAdapter.setData(datas);
        listAdapter.notifyDataSetChanged();
    }
    private class ListAdapter extends CommonAdapter<ObjCase> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjCase information) {

            ((Button) (holder.getView(R.id.btn_new_evidence))).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("data",information);
                            intentActivity(OnSiteLawEnforcementListActivity.this,
                                    OnSiteLawEnforcementEvidenceCreateActivity.class, false, bundle);
                        }
                    });

            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getCaseName());
            ((TextView) (holder.getView(R.id.ll_case_code_value))).setText(
                    information.getCaseNum());
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getFiliTime());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    information.getCaseLitiName());
            ((TextView) (holder.getView(R.id.tv_content))).setText(
                    information.getCaseSitu());

        }
    }
}
