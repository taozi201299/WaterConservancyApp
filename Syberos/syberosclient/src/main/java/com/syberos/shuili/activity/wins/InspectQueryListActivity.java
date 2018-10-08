package com.syberos.shuili.activity.wins;

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
import com.syberos.shuili.entity.wins.BisWinsProb;
import com.syberos.shuili.entity.wins.BisWinsProg;
import com.syberos.shuili.entity.wins.InspectPlan;
import com.syberos.shuili.entity.wins.InspectPlanGroup;
import com.syberos.shuili.entity.wins.ObjWinsPlan;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 稽查查询列表
 * OBJ_WINS_PLAN 表中获取单位的稽查计划，从稽查方案表中找到稽查方案详情
 */
public class InspectQueryListActivity extends BaseActivity {

    private final String Title = "稽查查询";

    public static final String SEND_BUNDLE_KEY = "InspectPlan";

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;
    private GroupedListAdapter groupedListAdapter;

    private List<InspectPlanGroup> inspectPlanGroups = new ArrayList<>();

    private ObjWinsPlan objWinsPlan = null;
    private BisWinsProg bisWinsProg = null;
    private int iSucessCount = 0;
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
        inspectPlanGroups.clear();
        getObjWinsPlan();

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(Title);
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
                        List<BisWinsProg> children
                                = inspectPlanGroups.get(groupPosition).getChildren();
                        BisWinsProg inspectPlan = children.get(childPosition);
                        bundle.putSerializable(SEND_BUNDLE_KEY, inspectPlan);

                        intentActivity(InspectQueryListActivity.this,
                                InspectQueryDetailActivity.class,
                                false, bundle);
                    }
                });

        recyclerView.setAdapter(groupedListAdapter);
    }

    private void getObjWinsPlan(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/wins/plan/selectAllCheckPlanInfo/";
        HashMap<String,String>params = new HashMap<>();
        params.put("planOrgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
     //   params.put("planOrgGuid","D7862390F88443AE87FA9DD1FE45A8B6");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objWinsPlan = gson.fromJson(result,ObjWinsPlan.class);
                if(objWinsPlan == null || objWinsPlan.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(objWinsPlan.dataSource.size() == 0){
                    ToastUtils.show("没查询到稽查计划");
                }else {
                    getBisWinsProg();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void  getBisWinsProg(){
        final int size = objWinsPlan.dataSource.size();
        for(int i = 0; i < size ;i++){
            final ObjWinsPlan item ;
            item = objWinsPlan.dataSource.get(i);
            String url = GlobleConstants.strIP +"/sjjk/v1/bis/wins/prog/selectCheckProgramDetailsByAuditPlan/";
            HashMap<String,String> params = new HashMap<>();
            params.put("owpGuid",item.getOwpGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    bisWinsProg = gson.fromJson(result,BisWinsProg.class);
                    if(bisWinsProg == null || bisWinsProg.dataSource == null || bisWinsProg.dataSource.size() == 0){
                    }else {
                        inspectPlanGroups.add(new InspectPlanGroup(item.getWinsPlanName(),bisWinsProg.dataSource));
                    }
                    if(iSucessCount + iFailedCount == size){
                        refreshUI();
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount + iFailedCount == size){
                        refreshUI();
                    }

                }
            });
        }
    }
    private void refreshUI() {
        closeDataDialog();
        groupedListAdapter.setData(inspectPlanGroups);
        groupedListAdapter.notifyDataSetChanged();
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
            List<BisWinsProg> children = mGroups.get(groupPosition).getChildren();
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
            BisWinsProg inspectPlan
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            holder.setText(R.id.tv_title,inspectPlanGroups.get(groupPosition).getHeader() );
            holder.setText(R.id.tv_time, inspectPlan.getStartTime()+"--"+inspectPlan.getEndTime());
            holder.setText(R.id.tv_batch, inspectPlan.getWinsArrayCode());

        }
    }
}
