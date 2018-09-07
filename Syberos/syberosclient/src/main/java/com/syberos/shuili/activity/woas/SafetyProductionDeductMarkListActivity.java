package com.syberos.shuili.activity.woas;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.publicentry.GroupInformationEntity;

import com.syberos.shuili.entity.woas.BisWoasGrop;
import com.syberos.shuili.entity.woas.BisWoasObj;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/7.
 */

public class SafetyProductionDeductMarkListActivity extends BaseActivity {

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;
    private GroupedListAdapter groupedListAdapter;

    private BisWoasGrop bisWoasGrop;
    private BisWoasObj bisWoasObj ;
    private List<GroupInformationEntity<DeductMarksInfo>> recordGroup = new ArrayList<>();

    private int iSucessCount = 0 ;
    private int iFailedCount = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_query_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        iFailedCount = 0;
        iSucessCount = 0;
        recordGroup.clear();
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWoasGrop = (BisWoasGrop) bundle.getSerializable("bisWoasGrop");
        bisWoasObj = (BisWoasObj)bundle.getSerializable("bisWoasObj");
        if(bisWoasGrop == null || bisWoasObj == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
        getRecords();

    }

    @Override
    public void initView() {
        showTitle("扣分记录");
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        groupedListAdapter
                = new GroupedListAdapter(this,
                recordGroup);
        recyclerView.setAdapter(groupedListAdapter);
        groupedListAdapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
                Bundle bundle = new Bundle();
                List<DeductMarksInfo> children
                        = recordGroup.get(groupPosition).getChildren();
                DeductMarksInfo item  = children.get(childPosition);
                bundle.putSerializable("deductMarksInfo", item);
                bundle.putString("objName",recordGroup.get(groupPosition).getHeader());

                intentActivity(SafetyProductionDeductMarkListActivity.this,
                        SafetyProductionDetailDeductMarksActivity.class,
                        false, bundle);
            }
        });


    }
    private void getRecords(){
        final int size = bisWoasObj.dataSource.size();
        for(int i = 0; i< size ; i++){
            String url= GlobleConstants.strIP + "/sjjk/v1/bis/woas/deuc/bisWoasDeucs/";
            HashMap<String,String>params = new HashMap<>();
            params.put("woasWiunGuid",bisWoasObj.dataSource.get(i).getWoasWiunGuid());
            params.put("woasGropGuid",bisWoasGrop.getGuid());
            params.put("woasWiunGuid","0fc29017b24841e88c05975f42822b3c");
            params.put("woasGropGuid","1e7d59ce9f3d4e789dd0907bfccfd476");
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    DeductMarksInfo deductMarksInfo = gson.fromJson(result,DeductMarksInfo.class);
                    if(deductMarksInfo!= null && deductMarksInfo.dataSource != null &&
                            deductMarksInfo.dataSource.size() > 0){
                        recordGroup.add(new GroupInformationEntity(bisWoasObj.dataSource.get(finalI).getWoasWiunName(), (ArrayList<DeductMarksInfo>) deductMarksInfo.dataSource));
                    }
                    if(iSucessCount +iFailedCount == size){
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == size){
                        refreshUI();
                    }

                }
            });
        }

    }
    private void refreshUI(){
        closeDataDialog();
        groupedListAdapter.setData(recordGroup);
        groupedListAdapter.notifyDataSetChanged();
    }
    private class GroupedListAdapter extends GroupedRecyclerViewAdapter {


        private List<GroupInformationEntity<DeductMarksInfo>> mGroups;

        public GroupedListAdapter(
                Context context, List<GroupInformationEntity<DeductMarksInfo>> groups) {
            super(context);
            mGroups = groups;
        }

        public void setData(List<GroupInformationEntity<DeductMarksInfo>> groups) {
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            List<DeductMarksInfo> children = mGroups.get(groupPosition).getChildren();
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
            return R.layout.activity_safety_production_detail_deduct_marks;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            GroupInformationEntity entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          int groupPosition, int childPosition) {
            DeductMarksInfo deductMarksInfo
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            holder.setText(R.id.tv_time, deductMarksInfo.getCollTime());
            holder.setText(R.id.tv_unit, mGroups.get(groupPosition).getHeader());
            holder.setText(R.id.tv_score,String.valueOf(deductMarksInfo.getFianDeuc()));

        }
    }
}
