package com.syberos.shuili.activity.accident;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.AccidentInformationGroup;
import com.syberos.shuili.entity.accident.AttOrgBaseForAcci;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccidentQueryListActivity extends BaseActivity implements EnumView.IEnumClick {
    private final String TAG = AccidentListActivity.class.getSimpleName();
    @BindView(R.id.ev_unit)
    EnumView evUnit;
    /**
     * 事故单位类型
     */
    private DicInfo m_unitTypeDic;
    /**
     * 事故类别
     */
    private DicInfo m_accidentTypeDic;
    /**
     * 按事故单位类型将快报项进行划分
     */
    HashMap<String, ArrayList<ObjAcci>> groupMap = new HashMap<>();
    private ArrayList<ObjAcci> reportInfos = new ArrayList<>();
    ArrayList<OrgInfo> orgInfos = new ArrayList<>();
    ArrayList<ObjAcci> datas = new ArrayList<>();
    /**
     * 事故快报列表信息
     */
    private ObjAcci accidentInformation;
    private final String Title = "事故查询";

    public static final String SEND_BUNDLE_KEY = "ObjAcci";
    /**
     * 事故单位类型
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
    @BindView(R.id.recyclerView_express_accident)
    RecyclerView recyclerView;
    GroupedEnterprisesExpressAccidentListAdapter accidentInformationListAdapter;
    ArrayList<AccidentInformationGroup> accidentInformationGroups = new ArrayList<>();

    private AttOrgBaseForAcci attOrgBaseForAcci = null;

    private int iLastIndex = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_accident_query;
    }

    @Override
    public void initListener() {
        evUnit.setListener(this);
    }

    @Override
    public void initData() {
    }

    /**
     * 下级行政单位事故
     */
    private void getAccidentUnit() {
        String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
        urlTags.add(url);
        HashMap<String, String> params = new HashMap<>();
        params.put("pguid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                attOrgBaseForAcci = gson.fromJson(result, AttOrgBaseForAcci.class);
                if (attOrgBaseForAcci == null || attOrgBaseForAcci.dataSource == null) {
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                } else if (attOrgBaseForAcci.dataSource.size() == 0) {
                    closeDataDialog();
                    ToastUtils.show("没有相关的事故信息");
                } else {
                    setUnitList();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());

            }
        });
    }

    private void setUnitList() {
        closeDataDialog();
        ArrayList<String> unitList = new ArrayList<>();
        if (attOrgBaseForAcci != null && attOrgBaseForAcci.dataSource.size() > 0) {
            int size = attOrgBaseForAcci.dataSource.size();
            for (int i = 0; i < size; i++) {
                unitList.add(attOrgBaseForAcci.dataSource.get(i).getOrgName());

            }
            evUnit.setEntries(unitList);
        }

    }

    /**
     * 获取快报事故列表
     */
    private void getAccidentList(int index) {
        accidentInformationGroups.clear();
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/obj/getAccidentManagements/";
        urlTags.add(url);
        HashMap<String, String> param = new HashMap<>();
        param.put("acciWinuGuid", attOrgBaseForAcci.dataSource.get(index).getGuid());
        final int finalIndex = index;
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    accidentInformation = gson.fromJson(result, ObjAcci.class);
                    if (accidentInformation == null || !accidentInformation.code.equals("0")) {
                        return;
                    }
                    parasAccidentInformation();
                    String header = attOrgBaseForAcci.dataSource.get(finalIndex).getOrgName();
                    ArrayList<ObjAcci> datas = new ArrayList<>();
                    datas.addAll(accidentInformation.dataSource);
                    for (ObjAcci item : accidentInformation.dataSource) {
                        item.setAccidentUnitName(header);
                        if (item.getPID() != null && !item.getPID().isEmpty()) {
                            datas.remove(item);
                        }
                    }
                    accidentInformationGroups.add(new AccidentInformationGroup(header, (ArrayList<ObjAcci>) accidentInformation.dataSource));
                    refreshUI();
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
            });
    }

    private void parasAccidentInformation() {
        for (ObjAcci item : accidentInformation.dataSource) {
            if (item.getPID() != null && !item.getPID().isEmpty()) {
                item.setRepStat("1");
                reportInfos.add(item);
            }
        }
    }

    private OrgInfo getOrgInfo(String orgId) {
        for (OrgInfo item : orgInfos) {
            if (item.getGuid().equals(orgId))
                return item;
        }
        return null;
    }

    private void refreshUI() {
        closeDataDialog();
        accidentInformationListAdapter.setData(accidentInformationGroups);
        accidentInformationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Acci);
        setInitActionBar(true);
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        accidentInformationListAdapter = new GroupedEnterprisesExpressAccidentListAdapter(mContext, new ArrayList<AccidentInformationGroup>());
        accidentInformationListAdapter.setOnChildClickListener(
                new GroupedRecyclerViewAdapter.OnChildClickListener() {
                    @Override
                    public void onChildClick(GroupedRecyclerViewAdapter adapter,
                                             BaseViewHolder holder,
                                             int groupPosition, int childPosition) {
                        if (groupPosition >= accidentInformationGroups.size())
                            return;
                        if (childPosition >= accidentInformationGroups.get(groupPosition).getChildren().size()) {
                            return;
                        }
                        Bundle bundle = new Bundle();
                        ArrayList<ObjAcci> children
                                = accidentInformationGroups.get(groupPosition).getChildren();
                        ObjAcci accidentInformation = children.get(childPosition);
                        bundle.putSerializable(SEND_BUNDLE_KEY, accidentInformation);

                        ArrayList<ObjAcci> datas = new ArrayList<>();
                        for (ObjAcci objAcci : reportInfos) {
                            if (objAcci.getId().equalsIgnoreCase(accidentInformation.getId())) {
                                datas.add(objAcci);
                            }
                        }
                        bundle.putSerializable("data", datas);
                        intentActivity(AccidentQueryListActivity.this,
                                AccidentDetailActivity.class,
                                false, bundle);
                    }
                });

        recyclerView.setAdapter(accidentInformationListAdapter);

        accidentInformationListAdapter.notifyDataSetChanged();
        showDataLoadingDialog();
        reportInfos.clear();
        if (accidentInformationGroups != null) {
            accidentInformationGroups.clear();
        }
        getAccidentUnit();
    }
    @Override
    public void onEnumClick() {
        int index = evUnit.getCurrentIndex();
        if(index == iLastIndex)return;
        else {
            iLastIndex = index;
            getAccidentList(iLastIndex);
        }
    }


    private class GroupedEnterprisesExpressAccidentListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<AccidentInformationGroup> mGroups;

        public GroupedEnterprisesExpressAccidentListAdapter(
                Context context, ArrayList<AccidentInformationGroup> groups) {
            super(context);
            mGroups = groups;
        }

        public void setData(ArrayList<AccidentInformationGroup> groups) {
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition >= mGroups.size()) return 0;
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
            if (groupPosition >= mGroups.size()) return;
            AccidentInformationGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            if (groupPosition >= mGroups.size()) return;
            final ObjAcci accidentInformation
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            RelativeLayout ll_report_after = holder.get(R.id.ll_report_after);
            ll_report_after.setVisibility(View.GONE);
            String grade = accidentInformation.getAcciGrad() == null || accidentInformation.getAcciGrad().isEmpty() ? "1" : accidentInformation.getAcciGrad();
            int type = Integer.valueOf(grade);
            switch (type) {
                case GlobleConstants.TYPE_NORMAL: {
                    holder.setText(R.id.tv_type, R.string.accident_type_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case GlobleConstants.TYPE_BIG: {
                    holder.setText(R.id.tv_type, R.string.accident_type_big);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_big_shape);
                }
                break;
                case GlobleConstants.TYPE_BIGGER: {
                    holder.setText(R.id.tv_type, R.string.accident_type_bigger);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                case GlobleConstants.TYPE_LARGE: {
                    holder.setText(R.id.tv_type, R.string.accident_type_large);
                    holder.setTextColor(R.id.tv_type, R.color.black);

                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_large_shape);
                }
                break;
                default:
                    holder.setText(R.id.tv_type, R.string.accident_type_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
            }

            holder.setText(R.id.tv_title, GlobleConstants.acciClassMap.get(accidentInformation.getAcciCate()));
            holder.setText(R.id.tv_time, accidentInformation.getOccuTime());
            holder.setText(R.id.tv_name, accidentInformation.getAccidentUnitName());
        }
    }
}
