package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.hidden.HiddenSupervice;
import com.syberos.shuili.entity.publicentry.GroupInformationEntity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-23.
 * todo 待修改 需要和7个子系统确定 行政版
 * 重大隐患和逾期的一般隐患  督办 -- 直属 水行政
 *
 */

public class InvestigationSuperviceTaskActivity extends BaseActivity{
    private final String TAG = InvestigationSuperviceTaskActivity.class.getSimpleName();
    private final String Title = "隐患督办";
    @BindView(R.id.recyclerView_investigation)
    RecyclerView recyclerView;
    GroupedEnterprisesExpressAccidentListAdapter groupedEnterprisesExpressAccidentListAdapter = null;
    HiddenSupervice hiddenSupervice = null;
    HiddenSupervice hiddenSupervice1 = null ; // 下级水行政
    ArrayList<GroupInformationEntity<HiddenSupervice>>accidentInformationGroups = null;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_task_layout;
    }

    @Override
    public void initListener() {
        groupedEnterprisesExpressAccidentListAdapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
                if(groupPosition >=accidentInformationGroups.size())
                    return;
                if(childPosition >= accidentInformationGroups.get(groupPosition).getChildren().size()){
                    return;
                }
                HiddenSupervice item = accidentInformationGroups.get(groupPosition).getChildren().get(childPosition);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",item);
                intentActivity(InvestigationSuperviceTaskActivity.this,InvestigationSuperviceDetailActivity.class,false,bundle);
            }
        });
    }
    private void closeLoadingDialog(){
        closeDataDialog();
    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        accidentInformationGroups.clear();
        getDirectUnit();

    }
    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        accidentInformationGroups = new ArrayList<>();
        groupedEnterprisesExpressAccidentListAdapter = new GroupedEnterprisesExpressAccidentListAdapter(mContext,accidentInformationGroups);
        recyclerView.setAdapter(groupedEnterprisesExpressAccidentListAdapter);
    }
    private void getDirectUnit(){
        String url = GlobleConstants.strIP +"/sjjk/v1/obj/hidd/selectDirectUnit/";
        HashMap<String,String>params = new HashMap<>();
        params.put("adminWiunGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenSupervice = gson.fromJson(result,HiddenSupervice.class);
                if(hiddenSupervice == null || hiddenSupervice.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                ArrayList<HiddenSupervice>datas = new ArrayList<>();
                if(hiddenSupervice.dataSource.size() > 0) {
                    if(hiddenSupervice.dataSource.size() > 0) {
                        for(HiddenSupervice item: hiddenSupervice.dataSource){
                            if(item.getSupStat() == null || item.getSupStat().isEmpty()){
                                if(item.getAccepDate() == null || item.getAccepDate().isEmpty()){
                                    datas.add(item);
                                }
                            }
                        }
                        if(datas.size() > 0)
                        accidentInformationGroups.add(new GroupInformationEntity<HiddenSupervice>("直属单位", datas));
                    }
                }
                getLowerWaterList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void  getLowerWaterList(){
        String url = GlobleConstants.strIP +"/sjjk/v1/obj/hidd/selectLowerWater/";
        HashMap<String,String>params = new HashMap<>();
        params.put("pguid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hiddenSupervice1 = gson.fromJson(result,HiddenSupervice.class);
                if(hiddenSupervice1 == null || hiddenSupervice1.dataSource == null){
                    closeLoadingDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                ArrayList<HiddenSupervice>datas = new ArrayList<>();
                if(hiddenSupervice1.dataSource.size() > 0) {
                    for(HiddenSupervice item: hiddenSupervice1.dataSource){
                        if(item.getSupStat() == null || item.getSupStat().isEmpty()){
                            if(item.getAccepDate() == null || item.getAccepDate().isEmpty()){
                                datas.add(item);
                            }
                        }
                    }
                    if(datas.size() > 0) {
                        accidentInformationGroups.add(new GroupInformationEntity<HiddenSupervice>("下级水行政", datas));
                    }
                }
                refreshUI();


            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage() + "获取下级水行政隐患失败");
                refreshUI();
            }
        });
    }
    private void refreshUI(){
        closeDataDialog();
        if(accidentInformationGroups.size() == 0){
            ToastUtils.show("没有待督办任务");
            return;
        }
        groupedEnterprisesExpressAccidentListAdapter.setData(accidentInformationGroups);
        groupedEnterprisesExpressAccidentListAdapter.notifyDataSetChanged();

    }

    private class GroupedEnterprisesExpressAccidentListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<GroupInformationEntity<HiddenSupervice>> mGroups;

        public GroupedEnterprisesExpressAccidentListAdapter(
                Context context, ArrayList<GroupInformationEntity<HiddenSupervice>> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<GroupInformationEntity<HiddenSupervice>> groups){
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<HiddenSupervice> children = mGroups.get(groupPosition).getChildren();
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
            return R.layout.activity_investigation_task_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            if(groupPosition >= mGroups.size()) return;
            GroupInformationEntity entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            if(groupPosition >= mGroups.size()) return;
            final HiddenSupervice  investigationInfo = mGroups.get(groupPosition).getChildren().get(childPosition);
            String type = investigationInfo.getHiddGrad();
            LinearLayout ll_type = holder.get(R.id.ll_type);
            Button btnSupervice = holder.get(R.id.btn_supervice);
            RelativeLayout rl_supervice = holder.get(R.id.ll_supervise);
            ShapeDrawable bgShape = null;
            switch (type){
                case "1": {
                    ((TextView) (holder.get(R.id.tv_type))).setText(R.string.normal);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                }
                break;
                case "2": {
                    ((TextView) (holder.get(R.id.tv_type))).setText(R.string.danger);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                }
                break;

            }
            ( (TextView)(holder.get(R.id.tv_title))).setText(investigationInfo.getHiddName());
            ( (TextView)(holder.get(R.id.tv_time))).setText(investigationInfo.getRequCompDate());
            ( (TextView)(holder.get(R.id.tv_name))).setText(investigationInfo.getEngName());
            ((TextView) (holder.get(R.id.tv_content))).setText(investigationInfo.getHiddDesc());
            rl_supervice.setVisibility(View.VISIBLE);
            btnSupervice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2018/4/12 督办
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",investigationInfo);
                    intentActivity(InvestigationSuperviceTaskActivity.this,
                            InvestigationSuperviceFormActivity.class,false,bundle);
                }
            });
            btnSupervice.setText("督办");
        }
    }
}
