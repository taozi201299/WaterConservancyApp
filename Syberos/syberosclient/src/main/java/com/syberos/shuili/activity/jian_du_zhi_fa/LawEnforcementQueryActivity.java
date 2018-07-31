package com.syberos.shuili.activity.jian_du_zhi_fa;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.App;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.objCase.ObjLayer;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LawEnforcementQueryActivity extends TranslucentActivity
        implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "LawQueryInformation";

    private ListAdapter listAdapter = null;
    private List<ObjLayer> searchResultList = new ArrayList<>();
    ObjLayer objLayer = null;

    @BindView(R.id.searchClearEditText)
    ClearEditText searchClearEditText;

    @BindView(R.id.tv_quit_search)
    TextView tv_quit_search;

    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;

    private boolean bSearch = false;

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        CommonUtils.hideSoftPan(searchClearEditText);
        searchClearEditText.clearFocus();
        activityFinish();
    }

    @OnClick(R.id.tv_quit_search)
    void onCancelSearchClicked() {
        searchClearEditText.clearFocus();
        bSearch = false;
        refreshUI();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_law_enforcement_query;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        bSearch = false;
        searchClearEditText.clearFocus();
        showDataLoadingDialog();
        getObjLayerList();

    }

    @Override
    public void initView() {
        searchClearEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_quit_search.setVisibility(View.VISIBLE);
                } else {
                    if (searchClearEditText != null) {
                        CommonUtils.hideSoftPan(searchClearEditText);
                        searchClearEditText.setText("");
                    }

                    tv_quit_search.setVisibility(View.GONE);
                }

                if (searchClearEditText != null) {
                    searchClearEditText.onFocusChange(v, hasFocus);
                }
            }
        });

        searchClearEditText.setTextChangedListener(new ClearEditText.ITextChanged() {
            @Override
            public void onTextChanged(String s) {
                bSearch = true;
                if (TextUtils.isEmpty(s.trim())) {
                    bSearch = false;
                    // 显示历史结果
                    refreshUI();
                } else {
                    // 显示搜索结果
                    filterData(s.trim());
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_law_enforcement_query_item);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        listAdapter.setOnItemClickListener(this);
    }

    void filterData(final String text) {
        searchResultList.clear();
        for (ObjLayer item : objLayer.dataSource) {
            if(item.lareName == null)continue;
            if (item.lareName.contains(text)) {
                searchResultList.add(item);
            }
        }
        refreshUI();
    }

    private void getObjLayerList(){
        String url = App.strIP + "/sjjk/v1/obj/lare/objLares/";
        HashMap<String,String>params = new HashMap<>();
      //  params.put("orgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgGuid","5E554BC50F634822AE1308CB85947B8A");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objLayer = gson.fromJson(result,ObjLayer.class);
                if(objLayer == null || objLayer.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(objLayer.dataSource.size() == 0){
                    ToastUtils.show("无法律法规文件");
                }
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    private void refreshUI(){
        if(bSearch){
            listAdapter.setData(searchResultList);
        }else {
            listAdapter.setData(objLayer.dataSource);
        }
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjLayer information = null;
        if(bSearch){
            information = searchResultList.get(position);
        }else {
            information = objLayer.dataSource.get(position);
        }
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this,
                LawEnforcementQueryDetailActivity.class, false, bundle);
    }

    private class ListAdapter extends CommonAdapter<ObjLayer> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, ObjLayer information) {

            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.lareName == null ?"无名":information.lareName);

        }
    }

    private static class ViewHolder{
        TextView itemText;
    }
}
