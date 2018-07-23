package com.syberos.shuili.activity.reports;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.bao_biao_guan_li.BisAcciRecRep;
import com.syberos.shuili.entity.bao_biao_guan_li.BisOrgMonRepPeri;
import com.syberos.shuili.entity.bao_biao_guan_li.ReportForAdmin;
import com.syberos.shuili.entity.bao_biao_guan_li.ReportGroup;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

public class  AccidentReportActivity extends BaseActivity{

    @BindView(R.id.reportRecycleView)
    RecyclerView reportRecycleView;
    @BindView(R.id.tv_current_month)
    TextView tv_current_month;


    final String Tag = AccidentReportActivity.class.getSimpleName();
    final String title = "事故报表";
    String header[] = {"本单位","直管单位","监管单位"};
    ArrayList<ReportForAdmin>directUnit = new ArrayList<>();
    ArrayList<ReportForAdmin>supervisionUnit = new ArrayList<>();
    ArrayList<ReportGroup> mGroups = new ArrayList<>();
    GroupedReportListAdapter groupedReportListAdapter;
    ReportForAdmin reportForAdmin;
    ArrayList<ReportForAdmin>reportForAdmins = new ArrayList<>();
    BisOrgMonRepPeri bisOrgMonRepPeri;
    BisAcciRecRep bisAcciRecRep;
    AttOrgBase orgBase ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_report_header_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getReportUnitID();
    }

    @Override
    public void initView() {
        setInitActionBar(false);
        groupedReportListAdapter = new GroupedReportListAdapter(mContext,null);
    }
    private void getReportUnitID(){
        String url = "http://192.168.1.8:8080/sjjk/v1/att/org/base/attOrgBases/";
        HashMap<String,String>params = new HashMap<>();
        params.put("pguid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgType","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                orgBase = gson.fromJson(result,AttOrgBase.class);
                if(orgBase == null || orgBase.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getReportUnitInfo();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getReportUnitInfo(){
        String url = "http://192.168.1.8:8080/sjjk/v1/att/org/ext/attOrgExts/";
        HashMap<String,String>params = new HashMap<>();
        ArrayList<AttOrgBase>list = (ArrayList<AttOrgBase>) orgBase.dataSource;
        final int size =list.size();
        if(size == 0){
            processResult();
        }else {
         for( int i = 0; i< size; i++) {
             AttOrgBase item = list.get(i);
             params.put("orgGuid", item.getGuid());
             final int finalI = i;
             SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                 @Override
                 public void onResponse(String result) {
                     Gson gson = new Gson();
                     reportForAdmin = gson.fromJson(result, ReportForAdmin.class);
                     if (reportForAdmin == null || reportForAdmin.dataSource == null || reportForAdmin.dataSource.size() == 0) {
                         ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                         return;
                     }else {
                         reportForAdmins.add(reportForAdmin.dataSource.get(0));
                     }
                     if(finalI == size -1) {
                         processResult();
                     }
                 }
                 @Override
                 public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                     closeDataDialog();
                     ToastUtils.show(errorInfo.getMessage());
                 }
             });
         }
        }
    }
    private void getUnitReportStatus(){
        // 2 未上报 1 已上报 已上报需要查看具体状态 上报后可以申请撤回
        String url= "http://192.168.1.8:8080/sjjk/v1/bis/org/mon/rep/hazy-bisOrgMonRepPeris/";
        HashMap<String,String>params = new HashMap<>();
        params.put("repTime",tv_current_month.getText().toString());
        params.put("repType","1");
        final int size = reportForAdmins.size();
        for(int i = 0; i< size;i++) {
            //params.put("repOrgGuid", "F83199FDD35E49FF9643A6C394DBBF45");
            final ReportForAdmin item = reportForAdmins.get(i);
            params.put("repOrgGuid",item.getOrgGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisOrgMonRepPeri = gson.fromJson(result, BisOrgMonRepPeri.class);
                    if (bisOrgMonRepPeri == null || bisOrgMonRepPeri.dataSource == null || bisOrgMonRepPeri.dataSource.size() == 0) {
                        item.setReportStatus("2");
                        item.setReportDone(false);
                    }else {
                        item.setReportStatus("1");
                        item.setReportDone(true);
                    }
                    if(finalI == size -1){
                        getStatusDetail();
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }
    private void getStatusDetail(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/acci/rec/rep/hazy-bisAcciRecReps/";
        HashMap<String,String>params = new HashMap<>();
        ArrayList<ReportForAdmin>list = (ArrayList<ReportForAdmin>) reportForAdmin.dataSource;
        final int size = list.size();
        ReportForAdmin item = null;
        for(int i = 0 ; i < size ; i++){
            item = list.get(i);
            if("2".equals(item.getReportStatus()))continue;
            params.put("repGuid",item.getOrgGuid());
            final ReportForAdmin finalItem = item;
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisAcciRecRep = gson.fromJson(result,BisAcciRecRep.class);
                    if(bisAcciRecRep == null || bisAcciRecRep.dataSource == null){
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    if(bisAcciRecRep.dataSource.size() > 0) {
                        finalItem.setReportStatus(bisAcciRecRep.dataSource.get(0).getRepAct());
                    }
                    if(finalI == size -1){
                        closeDataDialog();
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
    }
    private void processResult() {
        if(reportForAdmin != null && reportForAdmin.dataSource != null) {
            for (ReportForAdmin item : reportForAdmin.dataSource) {
                if ("1".equals(item.getIfMiniDire())) {
                    directUnit.add(item);
                } else if ("2".equals(item.getIfMiniDire())) {
                    supervisionUnit.add(item);
                }
            }
        }
        ReportForAdmin reportForAdmin = new ReportForAdmin();
        reportForAdmin.setOrgGuid(SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        reportForAdmin.setWiunName(SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName());
        ArrayList<ReportForAdmin>units = new ArrayList<>();
        units.add(reportForAdmin);
        mGroups.add(new ReportGroup(header[0],units));
        mGroups.add(new ReportGroup(header[1],directUnit));
        mGroups.add(new ReportGroup(header[2],supervisionUnit));
        if(reportForAdmin.dataSource != null) {
            reportForAdmin.dataSource.add(reportForAdmin);
        }
        getUnitReportStatus();

    }
    private void refreshUI(){
        groupedReportListAdapter.setData(mGroups);
        reportRecycleView.setAdapter(groupedReportListAdapter);
        groupedReportListAdapter.notifyDataSetChanged();
    }
    private class GroupedReportListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<ReportGroup> mGroups;

        public GroupedReportListAdapter(
                Context context, ArrayList<ReportGroup> groups) {
            super(context);
            mGroups = groups;
        }

        public void setData(ArrayList<ReportGroup> groups) {
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<ReportForAdmin> children = mGroups.get(groupPosition).getChilden();
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
            return R.layout.layout_sub_unit_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            ReportGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            TextView tv_sub_unit = holder.get(R.id.tv_sub_unit);
            TextView tv_returned = holder.get(R.id.tv_returned); // 已退回
            TextView tv_repetition = holder.get(R.id.tv_repetition); // 重报
            TextView tv_rush = holder.get(R.id.tv_rush); // 催报
            TextView tv_revoke  = holder.get(R.id.tv_revoke); // 撤销
            TextView tv_report = holder.get(R.id.tv_report); //上报
            TextView tv_reported = holder.get(R.id.tv_reported); // 已上报
            tv_returned.setVisibility(View.GONE);
            tv_repetition.setVisibility(View.GONE);
            tv_rush.setVisibility(View.GONE);
            tv_revoke.setVisibility(View.GONE);
            tv_report.setVisibility(View.GONE);
            tv_reported.setVisibility(View.GONE);

            final ReportForAdmin reportForAdmin
                    = mGroups.get(groupPosition).getChilden().get(childPosition);
            if(reportForAdmin.getReportStatus().equals("2")){
                if(reportForAdmin.isReportDone()){
                    // /退回
                    tv_returned.setVisibility(View.VISIBLE);
                    if(groupPosition == 0){
                        tv_repetition.setVisibility(View.VISIBLE);
                    }else {
                       tv_rush.setVisibility(View.VISIBLE);
                    }
                }else{
                    // 未上报
                    if(groupPosition == 0){
                        tv_report.setVisibility(View.VISIBLE);
                    }else {
                        tv_rush.setVisibility(View.VISIBLE);
                    }
                }
            }
            else if(reportForAdmin.getReportStatus().equals("1")){
                tv_reported.setVisibility(View.VISIBLE);
                //  已上报
            }else if(reportForAdmin.getReportStatus().equals("3")){
                if(groupPosition == 0)
                tv_repetition.setVisibility(View.VISIBLE);
                else {
                    tv_rush.setVisibility(View.VISIBLE);
                }
                // 已撤销 重报
            }else if(reportForAdmin.getReportStatus().equals("4")){
                // 申请撤销中
            }else if(reportForAdmin.getReportStatus().equals("5")){
                //同意撤销
                if(groupPosition == 0)
                    tv_repetition.setVisibility(View.VISIBLE);
                else {
                    tv_rush.setVisibility(View.VISIBLE);
                }
            }else if(reportForAdmin.getReportStatus().equals("6")){
                // 不同意撤销
            }
        }
    }
}