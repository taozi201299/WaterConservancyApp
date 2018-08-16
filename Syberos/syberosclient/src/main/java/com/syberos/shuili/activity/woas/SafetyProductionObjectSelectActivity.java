package com.syberos.shuili.activity.woas;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.woas.BisWoasObj;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * BIS_WOAS_OBJ  工作考核对象表中通过工作考核GUID	WOAS_GUID 考核组GUID	WOAS_GROUP_GUID 获取被考核对象
 */

public class SafetyProductionObjectSelectActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "DeductMarksInfo";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    private ListAdapter listAdapter;
    /**
     * 考核组对象
     */
    BisWinsGroup bisWinsGroup = null;
    /**
     * 考核对象
     */
    private BisWoasObj bisWoasObj = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_safety_production_object_select;
    }

    @Override
    public void initListener() {

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
                R.layout.activity_safety_production_object_select_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        if(bisWinsGroup == null){
            Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
            bisWinsGroup = (BisWinsGroup) bundle.getSerializable("bisWinsGroup");
            if(bisWinsGroup == null){
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                activityFinish();
            }
        }
        showDataLoadingDialog();
        getWoasObj();

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("woasGroupGuid",bisWinsGroup);
        bundle.putSerializable("bisWoasObj",bisWoasObj.dataSource.get(position));
        intentActivity(this, InspectAssessNewDeductMarksActivity.class,
                false, bundle);
    }
    private void getWoasObj(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/woas/obj/selectAssessedObjectList/";
        HashMap<String,String>params = new HashMap<>();
        params.put("woasGroupGuid",bisWinsGroup.getBwgGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisWoasObj = gson.fromJson(result,BisWoasObj.class);
                if(bisWoasObj == null || bisWoasObj.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(bisWoasObj.dataSource.size() == 0){
                    ToastUtils.show("未获取到考核组信息");
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
        listAdapter.setData(bisWoasObj.dataSource);
        listAdapter.notifyDataSetChanged();

    }

    private class ListAdapter extends CommonAdapter<BisWoasObj> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisWoasObj information) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getWoasWiunName());
        }
    }
}
