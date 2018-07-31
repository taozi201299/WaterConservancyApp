package com.syberos.shuili.activity.accident;

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
import com.syberos.shuili.App;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.accident.AccidentInformationGroup;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class EnterprisesExpressAccidentListActivity extends BaseActivity {
    private final String TAG = EnterprisesExpressAccidentListActivity.class.getSimpleName();
    /**
     * 事故单位类型
     */
    private DicInfo m_unitTypeDic ;
    /**
     * 事故类别
     */
    private DicInfo m_accidentTypeDic;
    /**
     * 按事故单位类型将快报项进行划分
     */
    HashMap<String,ArrayList<ObjAcci>> groupMap = new HashMap<>();
    private ArrayList<ObjAcci> reportInfos = new ArrayList<>();
    /**
     * 事故快报列表信息
     */
    private ObjAcci accidentInformation;
    private final String Title = "快报事故";

    public static final String SEND_BUNDLE_KEY = "ObjAcci";
    /**
     *  事故单位类型
     */
    public static final String DICINFO_KEY = "DicInfoKey";
    /**
     * 事故类型
     */
    public static final String DICINFO_KEY_ACCIDENT_TYPE = "AccidentType";
    /**
     * 单位信息
     */
    OrgInfo orgInfo;
    ArrayList<OrgInfo>orgInfos = new ArrayList<>();
    @BindView(R.id.recyclerView_express_accident)
    RecyclerView recyclerView;
    GroupedEnterprisesExpressAccidentListAdapter accidentInformationListAdapter;
    ArrayList<AccidentInformationGroup> accidentInformationGroups = null;

    @OnClick(R.id.tv_new_accident)
    void onNewAccidentClicked() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DICINFO_KEY,m_unitTypeDic);
        bundle.putSerializable(DICINFO_KEY_ACCIDENT_TYPE,m_accidentTypeDic);
        bundle.putInt("type",ObjAcci.NEW_ACCI);
        intentActivity(this, AccidentNewFormActivity.class,false,bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_express_accident_list;
    }

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {
        orgInfos.clear();
        groupMap.clear();
        reportInfos.clear();
        if(accidentInformationGroups != null) {
            accidentInformationGroups.clear();
        }
        getAccidentUnitType();
    }

    /**
     * 获取事故单位类型
     */
    private void getAccidentUnitType() {
        String url = App.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_ACCI");
        params.put("attColCode","ACCI_WIUN_TYPE");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                m_unitTypeDic  = gson.fromJson(result,DicInfo.class);
                if(m_unitTypeDic == null || m_unitTypeDic.dataSource == null || m_unitTypeDic.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getAcciCate();
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
     * 获取事故类别
     */
    private void getAcciCate(){
        String url = App.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_ACCI");
        params.put("attColCode","ACCI_CATE");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                m_accidentTypeDic  = gson.fromJson(result,DicInfo.class);
                if(m_accidentTypeDic == null  || m_accidentTypeDic.dataSource == null ||
                        m_accidentTypeDic.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getAccidentList();
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
     * 获取快报事故列表
     */
    private void getAccidentList(){
        String url =  App.strIP + "/sjjk/v1/bis/obj/getAccidentManagements/";
        HashMap<String,String>param = new HashMap<>();
        param.put("acciWiunGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                accidentInformation = gson.fromJson(result,ObjAcci.class);
                if(accidentInformation == null || !accidentInformation.code.equals("0")){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    parasAccidentInformation();
                    getAccidentUnitInfo();
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
     * 事故状态 快报 补报
     * 1 pid 不为空，该数据为补报
     * 2 pid 为空 ，通过上报状态 判断是上报 还是未上报
     * 3 已快报 1 未快报2
     *
     */
    private void parasAccidentInformation(){
        for(ObjAcci item : accidentInformation.dataSource){
            if(item.getPID() != null){
                item.setRepStat("1");
                reportInfos.add(item);
            }
        }
    }

    /**
     * 获取事故单位名称
     */
    private void getAccidentUnitInfo(){
        String url = App.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
        HashMap<String,String>params = new HashMap<>();
        for(final ObjAcci item : accidentInformation.dataSource){
            params.put("guid",item.getAcciWindGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    orgInfo = gson.fromJson(result,OrgInfo.class);
                    if(orgInfo != null && orgInfo.dataSource != null && orgInfo.dataSource.size() > 0){
                        orgInfos.add(orgInfo.dataSource.get(0));
                    }
                    if(accidentInformation.dataSource.indexOf(item) == accidentInformation.dataSource.size() - 1) {
                        closeDataDialog();
                        merageData();
                        refreshUI(accidentInformation);
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
    private OrgInfo getOrgInfo(String orgId){
        for(OrgInfo item : orgInfos){
            if(item.getGuid().equals(orgId))
                return item;
        }
        return null;
    }
    private void merageData(){
        for(ObjAcci item : accidentInformation.dataSource){
            OrgInfo info = getOrgInfo(item.getAcciWindGuid());
            if(info != null){
                item.setAccidentUnitName(info.getOrgName());
            }
        }
    }
    private void refreshUI(ObjAcci accidentInformation){
        if(accidentInformationGroups == null){
            accidentInformationGroups = new ArrayList<>();
        }
        accidentInformationGroups.clear();
        int size = accidentInformation.dataSource.size();
        ArrayList<ObjAcci>infos;
        for(int i = 0; i < size ; i++){
            ObjAcci item = accidentInformation.dataSource.get(i);
            if(item.getPID() != null)continue;
            String unitType  = item.getAcciWiunType();
            if(groupMap.containsKey(unitType)){
                infos = groupMap.get(unitType);
                infos.add(item);
            }else{
                infos = new ArrayList<>();
                infos.add(item);
                groupMap.put(item.getAcciWiunType(),infos);
            }
        }
        Object [] headArray = groupMap.keySet().toArray();
        int len = headArray.length;
        for(int i = 0 ; i < len ; i ++){
            String headCode = headArray[i].toString();
            String head = getDicName(headCode);
            accidentInformationGroups.add(new AccidentInformationGroup(head,(ArrayList<ObjAcci>)groupMap.get(headCode)));
        }
        accidentInformationListAdapter.setData(accidentInformationGroups);
        accidentInformationListAdapter.notifyDataSetChanged();
    }

    private String getDicName(String dicCode){
        String dicName = "";
        for(DicInfo dicInfo :m_unitTypeDic.dataSource){
            if(dicInfo.getDcItemCode().equals(dicCode)){
                dicName = dicInfo.getDcItemName();
                break;
            }

        }
        return dicName;
    }
    private DicInfo getAccidentTypeItem(String dicCode){
        for(DicInfo dicInfo :m_accidentTypeDic.dataSource){
            if(dicInfo.getDcItemCode().equals(dicCode)){
                return dicInfo;
            }
        }
        return new DicInfo();
    }
    private DicInfo getUnitTypeItem(String dicCode){
        for(DicInfo dicInfo :m_unitTypeDic.dataSource){
            if(dicInfo.getDcItemCode().equals(dicCode)){
                return dicInfo;
            }
        }
        return new DicInfo();
    }
    @Override
    public void initView() {
        showDataLoadingDialog();
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        accidentInformationListAdapter = new GroupedEnterprisesExpressAccidentListAdapter(mContext,new ArrayList<AccidentInformationGroup>());
        accidentInformationListAdapter.setOnChildClickListener(
                new GroupedRecyclerViewAdapter.OnChildClickListener() {
                    @Override
                    public void onChildClick(GroupedRecyclerViewAdapter adapter,
                                             BaseViewHolder holder,
                                             int groupPosition, int childPosition) {

                        Bundle bundle = new Bundle();
                        ArrayList<ObjAcci> children
                                = accidentInformationGroups.get(groupPosition).getChildren();
                        ObjAcci accidentInformation = children.get(childPosition);
                        DicInfo item  = getAccidentTypeItem(accidentInformation.getAcciCate());
                        DicInfo unitType = getUnitTypeItem(accidentInformation.getAcciWiunType());
                        bundle.putSerializable(SEND_BUNDLE_KEY, accidentInformation);
                        bundle.putSerializable(DICINFO_KEY_ACCIDENT_TYPE,item);
                        bundle.putSerializable(DICINFO_KEY,unitType);

                        bundle.putSerializable("data",reportInfos);
                        intentActivity(EnterprisesExpressAccidentListActivity.this,
                                AccidentDetailActivity.class,
                                false, bundle);
                    }
                });

        recyclerView.setAdapter(accidentInformationListAdapter);
    }

    private class GroupedEnterprisesExpressAccidentListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<AccidentInformationGroup> mGroups;

        public GroupedEnterprisesExpressAccidentListAdapter(
                Context context, ArrayList<AccidentInformationGroup> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<AccidentInformationGroup> groups){
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<ObjAcci> children = mGroups.get(groupPosition).getChildren();
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
            AccidentInformationGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            final ObjAcci accidentInformation
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            DicInfo item  = getAccidentTypeItem(accidentInformation.getAcciCate());
            String grade = item.getDcGrad() == null ?"0":"1";
            int type = Integer.valueOf(grade);
            switch (type) {
                case ObjAcci.TYPE_NORMAL: {
                    holder.setText(R.id.tv_type,R.string.accident_type_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case ObjAcci.TYPE_BIG: {
                    holder.setText(R.id.tv_type,R.string.accident_type_big);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_big_shape);
                }
                break;
                case ObjAcci.TYPE_BIGGER: {
                    holder.setText(R.id.tv_type,R.string.accident_type_bigger);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                case ObjAcci.TYPE_LARGE: {
                    holder.setText(R.id.tv_type,R.string.accident_type_large);
                    holder.setTextColor(R.id.tv_type, R.color.black);

                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_large_shape);
                }
                break;
            }

            String repStatus = accidentInformation.getRepStat() == null ? "2" : accidentInformation.getRepStat();
            type = Integer.valueOf(repStatus);
          // 1 已上报 2 未上报
            switch (type) {
                case 0:
                case ObjAcci.REPORT_QUICK:
                    holder.setText(R.id.btn_report, "快报");
                    break;
                case ObjAcci.REPORT_AFTER:
                    holder.setText(R.id.btn_report, "补报");
                    break;
            }
            holder.setText(R.id.tv_title, item.getDcItemName());
            holder.setText(R.id.tv_time, accidentInformation.getOccuTime());
            holder.setText(R.id.tv_name, accidentInformation.getCollTime() + "事故");
            holder.setText(R.id.tv_content, accidentInformation.getAcciSitu());

            holder.get(R.id.btn_report).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    ArrayList<ObjAcci> children
                            = accidentInformationGroups.get(groupPosition).getChildren();
                    ObjAcci accidentInformation = children.get(childPosition);
                    bundle.putSerializable(SEND_BUNDLE_KEY, accidentInformation);
                    bundle.putSerializable(DICINFO_KEY_ACCIDENT_TYPE,m_accidentTypeDic);
                    bundle.putSerializable(DICINFO_KEY,m_unitTypeDic);
                    bundle.putInt("type",Integer.valueOf(accidentInformation.getRepStat()));
                    intentActivity(EnterprisesExpressAccidentListActivity.this,
                            AccidentNewFormActivity.class,
                            false, bundle);
                }
            });
        }
    }
}
