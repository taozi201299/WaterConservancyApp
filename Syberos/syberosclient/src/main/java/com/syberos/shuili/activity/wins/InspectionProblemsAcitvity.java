package com.syberos.shuili.activity.wins;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsGroupAll;
import com.syberos.shuili.entity.wins.BisWinsProb;
import com.syberos.shuili.entity.wins.BisWinsProjAll;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/30.
 * 获取某稽查组下的稽查问题
 * 分组显示 每个工程下的稽查问题
 */

public class InspectionProblemsAcitvity extends BaseActivity {
    private final String Title = "稽查问题";

    @BindView(R.id.recyclerView_inspection_prob)
    RecyclerView recyclerView_inspection_prob;
    private BisWinsProjAll bisWinsProjAll = null;
    private BisWinsGroupAll bisWinsGroupAll = null;
    private BisWinsProb bisWinsProb = null;
    HashMap<String,ArrayList<BisWinsProb>>mapValues = new HashMap<>();
    HashMap<String,String>mapProjValues = new HashMap<>();

    ArrayList<InspectionProblemGroup> groups = new ArrayList<>();
    GroupedWinsProbListAdapter groupedWinsProbListAdapter = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_problem_layout;
    }

    @Override
    public void initListener() {
        groupedWinsProbListAdapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
                // 显示稽查问题详情
                Bundle bundle = new Bundle();
                bundle.putSerializable("prob",groups.get(groupPosition).getChildren().get(childPosition));
                bundle.putString("projName",groups.get(groupPosition).getHeader());
                intentActivity(InspectionProblemsAcitvity.this,InspectProblemDetailActivity.class,false,bundle);
            }
        });

    }

    @Override
    public void initData() {
        groups.clear();
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsGroupAll = (BisWinsGroupAll) bundle.getSerializable("bisWinsGroup");
        if(bisWinsGroupAll == null){
            ToastUtils.show("参数错误");
            activityFinish();
        }
        getInspectionProject();
    }


    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        groupedWinsProbListAdapter = new GroupedWinsProbListAdapter(this,groups);
        recyclerView_inspection_prob.setAdapter(groupedWinsProbListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView_inspection_prob.setLayoutManager(layoutManager);

    }

    /**
     * 从稽查项目表中获取稽查对象 根据稽查组ID
     */
    private void getInspectionProject(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/proj/bisWinsProjs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("winsGroupGuid",bisWinsGroupAll.getGuid());
     //   params.put("winsGroupGuid","600fa3a0453640bc8212983a30cffa6b");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisWinsProjAll = gson.fromJson(result,BisWinsProjAll.class);
                if(bisWinsProjAll == null || bisWinsProjAll.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(bisWinsProjAll.dataSource.size() == 0){
                    ToastUtils.show("没有稽查项目");
                }else {
                    getWinsProblems();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    /**
     * 根据稽察组GUID和稽察地区名称查询稽察问题详情
     */
    private void getWinsProblems(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/wins/prob/bisWinsProbs/";
        HashMap<String,String>params = new HashMap<>();
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsProb = gson.fromJson(result,BisWinsProb.class);
                if(bisWinsProb == null || bisWinsProb.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    closeDataDialog();
                }else if(bisWinsProb.dataSource.size() == 0){
                    ToastUtils.show("该稽查组下没有稽查问题");
                    closeDataDialog();
                }
                else {
                    processResult();
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
    private void processResult(){
        for(BisWinsProjAll bisWinsProjAll: bisWinsProjAll.dataSource ){
            mapValues.put(bisWinsProjAll.getGuid(),new ArrayList<BisWinsProb>());
            mapProjValues.put(bisWinsProjAll.getGuid(),bisWinsProjAll.getProjName() +"("+ bisWinsProjAll.getAdminWiunName() +")");
        }
        for(BisWinsProb item : bisWinsProb.dataSource){
            ArrayList list = mapValues.get(item.getWinsProjGuid());
            if(list == null ){
                list = new ArrayList();
                list.add(item);
                mapValues.put(item.getWinsProjGuid(),list);
                continue;
            }
            list.add(item);
            mapValues.put(item.getWinsProjGuid(),list);
        }
        for(String key:mapValues.keySet()){
            if(mapValues.get(key).size() == 0)continue;
            InspectionProblemGroup inspectionProblemGroup = new InspectionProblemGroup(mapProjValues.get(key),mapValues.get(key));
            groups.add(inspectionProblemGroup);
        }
        refreshUI();
    }
    private void refreshUI(){
        closeDataDialog();
        groupedWinsProbListAdapter.setData(groups);
        groupedWinsProbListAdapter.notifyDataSetChanged();

    }
    private static class InspectionProblemGroup implements Serializable {

        private String header;
        private ArrayList<BisWinsProb> children;

        public InspectionProblemGroup(String header, ArrayList<BisWinsProb> children) {
            this.header = header;
            this.children = children;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public ArrayList<BisWinsProb> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<BisWinsProb> children) {
            this.children = children;
        }
    }
    private static class GroupedWinsProbListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<InspectionProblemGroup> mGroups;

        public GroupedWinsProbListAdapter(
                Context context, ArrayList<InspectionProblemGroup> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<InspectionProblemGroup> groups){
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<BisWinsProb> children = mGroups.get(groupPosition).getChildren();
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
            return R.layout.activity_enterprises_express_accident_list_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            InspectionProblemGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            final BisWinsProb bisWinsProb
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            RelativeLayout ll_report_after = holder.get(R.id.ll_report_after);
            ll_report_after.setVisibility(View.GONE);
            String grade = bisWinsProb.getProbCate();
            int type = Integer.valueOf(grade);
            switch (type) {
                case GlobleConstants.TYPE_NORMAL: {
                    holder.setText(R.id.tv_type,R.string.severity_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case GlobleConstants.TYPE_BIG: {
                    holder.setText(R.id.tv_type,R.string.severity_big);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_big_shape);
                }
                break;
                case GlobleConstants.TYPE_BIGGER: {
                    holder.setText(R.id.tv_type,R.string.severity_large);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                default:
                    holder.setText(R.id.tv_type,R.string.severity_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
                break;
            }


            ((TextView)holder.get(R.id.tv_name_label)).setText("问题分类");
            holder.setText(R.id.tv_time, bisWinsProb.getCollTime());
            int index = 1;
            if(!bisWinsProb.getProbType().isEmpty()){
                index = Integer.valueOf(bisWinsProb.getProbType());
            }
            holder.setText(R.id.tv_name,GlobleConstants.winsProbMap.get(index));
        }
    }
}
