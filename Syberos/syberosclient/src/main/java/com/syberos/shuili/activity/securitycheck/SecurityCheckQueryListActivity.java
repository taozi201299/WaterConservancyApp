package com.syberos.shuili.activity.securitycheck;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.entity.securitycheck.ObjSins;
import com.syberos.shuili.listener.ItemClickedAlphaChangeListener;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.syberos.shuili.activity.securitycheck.SecurityCheckTaskActivity.SEND_BUNDLE_KEY;
import static com.syberos.shuili.config.GlobleConstants.strIP;

/**
 * 安全检查部署
 * 安全检查对象表（OBJ_SINS）
 */
public class SecurityCheckQueryListActivity extends TranslucentActivity {
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    private GroupedListAdapter groupedListAdapter;

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;

    @BindView(R.id.tv_current_month)
    TextView tv_current_month;

    @BindView(R.id.iv_action_right)
    LinearLayout iv_action_right;

    @OnClick(R.id.tv_current_month)
    void onCurrentMonthClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_right)
    void onActionBarRightClicked() {
        onSelectMonthClicked();
    }

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    /**
     * 安全检查对象表信息
     */
    private ObjSins objSins;
    /**
     * 安全检查方案表信息
     */
    private BisSinsSche bisSinsSche;
    private HashMap<String, BisSinsSche> map = new HashMap<>();

    private int iSucessCount;
    private int iFailedCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_check_query_list;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListener() {
        tv_current_month.setOnTouchListener(new ItemClickedAlphaChangeListener());
        iv_action_right.setOnTouchListener(new ItemClickedAlphaChangeListener());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initData();
            }
        });
    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        map.clear();
        iSucessCount = 0;
        iFailedCount = 0;
        getObjSins();

    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Check);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);

        groupedListAdapter
                = new GroupedListAdapter(this,
                map);

        groupedListAdapter.setOnChildClickListener(
                new GroupedRecyclerViewAdapter.OnChildClickListener() {
                    @Override
                    public void onChildClick(GroupedRecyclerViewAdapter adapter,
                                             BaseViewHolder holder,
                                             int groupPosition, int childPosition) {

                        Bundle bundle = new Bundle();
                        Object[] heads = groupedListAdapter.getHeads();
                        List<BisSinsSche> children = map.get(heads[groupPosition]).dataSource;
                        BisSinsSche item = children.get(childPosition);
                        bundle.putSerializable(SEND_BUNDLE_KEY, item);

                        intentActivity(SecurityCheckQueryListActivity.this,
                                SecurityCheckQueryDetailActivity.class,
                                false, bundle);
                    }
                });

        recyclerView.setAdapter(groupedListAdapter);
        tv_current_month.setText(CommonUtils.getCurrentDateYMD());
        checkbox.setVisibility(View.VISIBLE);
        checkbox.setChecked(false);



    }

    /**
     * 获取单位的部署通知信息
     */
    private void getObjSins() {
        String url = strIP + "/sjjk/v1/obj/sis/objSinss/";
        urlTags.add(url);
        HashMap<String, String> params = new HashMap<>();
        params.put("notIssuGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        if(checkbox.isChecked()) {
            params.put("sinsStartTime", tv_current_month.getText() + " 00:00:00");
        }
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objSins = gson.fromJson(result, ObjSins.class);
                if (objSins != null && objSins.dataSource != null) {
                    if(objSins.dataSource.size() == 0) {
                        closeDataDialog();
                        ToastUtils.show("没有相关内容");
                    }
                    getPlanInfo();

                } else {
                    closeDataDialog();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    private void getPlanInfo() {
        // 外键 检查部署guid
        final ArrayList<ObjSins> infos = (ArrayList<ObjSins>) objSins.dataSource;
        String url = strIP + "/sjjk/v1/bis/sins/sche/bisSinsSches/";
        urlTags.add(url);
        HashMap<String, String> params = new HashMap<>();
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            final ObjSins item = infos.get(i);
            params.put("sinsGuid", item.getGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount++;
                    Gson gson = new Gson();
                    bisSinsSche = gson.fromJson(result, BisSinsSche.class);
                    if (bisSinsSche != null && bisSinsSche.dataSource != null) {
                        map.put(item.getGuid(), bisSinsSche);
                    }
                    if (iSucessCount + iFailedCount == infos.size()) {
                        closeDataDialog();
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount++;
                    if (iSucessCount + iFailedCount == infos.size()) {
                        closeDataDialog();
                        refreshUI();
                    }
                }
            });
        }
    }

    private String getHeaderName(String id) {
        for (ObjSins item : objSins.dataSource) {
            if (item.getGuid().equals(id)) {
                return item.getSinsDeplName();
            }
        }
        return "未知";

    }

    private void refreshUI() {
        if(map.size() == 0)closeDataDialog();
        groupedListAdapter.setData(map);
        groupedListAdapter.notifyDataSetChanged();
    }

    private void onSelectMonthClicked() {
        //时间选择器
        boolean[] type = {true, true, true, false, false, false};

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() > System.currentTimeMillis()) {
                    ToastUtils.show("提示：所选月份不应大于系统当前月份");
                    return;
                }
                tv_current_month.setText(Strings.formatDate(date));
                if(checkbox.isChecked()) {
                    initData();
                }
            }
        })
                .isDialog(true)
                .setType(type)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    private class GroupedListAdapter extends GroupedRecyclerViewAdapter {
        private HashMap<String, BisSinsSche> mGroups;

        public GroupedListAdapter(
                Context context, HashMap groups) {
            super(context);
            mGroups = groups;
        }

        public void setData(HashMap groups) {
            mGroups = groups;

        }

        private Object[] getHeads() {
            Object[] heads = (Object[]) mGroups.keySet().toArray();
            return heads;
        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Object[] heads = getHeads();
            if(groupPosition >= heads.length)return  0;
            List<BisSinsSche> children = mGroups.get(heads[groupPosition]).dataSource;
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
            return R.layout.activity_security_check_query_list_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            Object[] heads = (Object[]) mGroups.keySet().toArray();
            if(groupPosition >= heads.length) return;
            holder.setText(R.id.tv_header, getHeaderName(heads[groupPosition].toString()));
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          int groupPosition, int childPosition) {
            Object[] heads = getHeads();
            if(groupPosition >= heads.length)return;
            List<BisSinsSche> children = mGroups.get(heads[groupPosition]).dataSource;
            if (children.size() != 0) {
                holder.setText(R.id.tv_title, children.get(childPosition).getScheName());
                holder.setText(R.id.tv_time, children.get(childPosition).getScheStartTime() + "--" + children.get(childPosition).getScheCompTime());
            }
        }
    }
}
