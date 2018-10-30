package com.syberos.shuili.activity.dangersource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.dangersource.BisHazRegDetail;
import com.syberos.shuili.entity.objCase.ObjLayer;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 企事业危险源查询
 */
public class HazSearchListForEntActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = HazSearchListForEntActivity.class.getSimpleName();

    private final String Title = "危险源查询";

    public static final String SEND_BUNDLE_KEY = "DangerousInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;
    @BindView(R.id.searchClearEditText)
    ClearEditText searchClearEditText;

    @BindView(R.id.tv_quit_search)
    TextView tv_quit_search;
    private List<ObjHaz> searchResultList = new ArrayList<>();
    DangerousListAdapter listAdapter;
    ObjHaz inspectionList  = null;
    private DicInfo hazsGrade = null;
    private int iSucessCount = 0;
    private int iFailedCount = 0;
    private boolean bSearch = false;
    @OnClick(R.id.tv_quit_search)
    void onCancelSearchClicked() {
        searchClearEditText.clearFocus();
        bSearch = false;
        refreshUI();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjHaz information = inspectionList.dataSource.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this, HazDetailForEntActivity.class, false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_list;
    }

    @Override
    public void initListener() {

    }
    private void getHazsDic(){
        String url  = GlobleConstants.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_HAZ");
        params.put("attColCode","HAZ_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hazsGrade  = gson.fromJson(result,DicInfo.class);
                if(hazsGrade == null || hazsGrade.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getHazsList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private String getHazsGradeName(String dicCode){
        String dicName = "";
        if(hazsGrade != null){
            for(DicInfo dicInfo :hazsGrade.dataSource){
                if(dicInfo.getDcItemCode().equals(dicCode)){
                    dicName = dicInfo.getDcItemName();
                    break;
                }
            }
        }
        return dicName;
    }
    private void getHazsList(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/obj/objHazs/";
        HashMap<String,String>params = new HashMap<>();
        final UserExtendInformation info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        params.put("orgGuid",info.getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                inspectionList = gson.fromJson(result,ObjHaz.class);
                if(inspectionList != null && inspectionList.dataSource != null && inspectionList.dataSource.size() > 0){
                    getHazDetail();
                }else {
                    closeDataDialog();
                    ToastUtils.show("没有风险源信息");
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    private void getHazDetail(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/obj/selectHazInfoDetails/";
        HashMap<String,String>params = new HashMap<>();
        for(final ObjHaz item: inspectionList.dataSource){
            params.put("guid",item.getGuid());
            params.put("hazGuid",item.getGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    BisHazRegDetail bisHazRegDetail = gson.fromJson(result,BisHazRegDetail.class);
                    if(bisHazRegDetail == null || bisHazRegDetail.dataSource == null || bisHazRegDetail.dataSource.size() == 0){

                    }else {
                        item.setHazName(bisHazRegDetail.dataSource.get(0).getHazName());
                        item.setEngineName(bisHazRegDetail.dataSource.get(0).getEngName());
                        item.setHazStat(bisHazRegDetail.dataSource.get(0).getHazStat());
                    }
                    if(iSucessCount +iFailedCount == inspectionList.dataSource.size()){
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == inspectionList.dataSource.size()){
                        refreshUI();
                    }
                }
            });
        }

    }
    private void refreshUI(){
        closeDataDialog();
        if(bSearch){
            listAdapter.setData(searchResultList);

        }else {
            if (inspectionList != null) {
                for (ObjHaz item : inspectionList.dataSource) {
                    item.setHiddGradName(getHazsGradeName(item.getHiddGrad()));
                }
                listAdapter.setData(inspectionList.dataSource);
            }
        }
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        iSucessCount = 0;
        iFailedCount = 0;
        bSearch = false;
        getHazsDic();

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        showDataLoadingDialog();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new HazSearchListForEntActivity.DangerousListAdapter(this,
                R.layout.activity_recorded_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
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


    }
    void filterData(final String text) {
        searchResultList.clear();
        for (ObjHaz item : inspectionList.dataSource) {
            if(item.getHazName() == null)continue;
            if (item.getHazName().contains(text)) {
                searchResultList.add(item);
            }
        }
        refreshUI();
    }
    private class DangerousListAdapter extends CommonAdapter<ObjHaz> {
        public DangerousListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, ObjHaz dangerousInformation) {
            int type = Integer.valueOf(dangerousInformation.getHiddGrad());
            LinearLayout ll_type =  (holder.getView(R.id.ll_type));
            RelativeLayout ll_report_after = (holder.getView(R.id.ll_report_after));
            ll_report_after.setVisibility(View.GONE);
            (holder.getView(R.id.tv_time)).setVisibility(View.GONE);
            (holder.getView(R.id.ll_content)).setVisibility(View.GONE);

            switch (type) {
                case  GlobleConstants.HAZ_HTYPE_NORMAL: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_normal);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                }
                break;
                case GlobleConstants.HAZ_TYPE_BIGER: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_big_shape));
                }
                break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    dangerousInformation.getHazName());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    dangerousInformation.getEngineName());
        }
    }
}
