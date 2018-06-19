package com.syberos.shuili.activity.dangersource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.HistoryPatrolInformation;
import com.syberos.shuili.entity.UserExtendInfo;
import com.syberos.shuili.entity.dangersource.InspectionPartolInfo;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 巡查记录列表
 */
public class RecordedHistoryPatrolListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = RecordedHistoryPatrolListActivity.class.getSimpleName();

    private final String Title = "巡查记录";

    public static final String SEND_BUNDLE_KEY = "HistoryPatrolInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    HistoryPatrolListAdapter listAdapter;
    InspectionPartolInfo informationList = null;

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        InspectionPartolInfo information = informationList.dataSource.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);

        intentActivity(this, RecordedHistoryPatrolDetailActivity.class,
                false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recorded_history_patrol_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/haz/bisHazPatRecs/";
        HashMap<String,String>params = new HashMap<>();
        UserExtendInfo info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
       // params.put("hazGuid",info.getOrgId());
        params.put("hazGuid","B28351744A0E4587AAB7E26CD2C5DE0E");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                informationList = (InspectionPartolInfo)gson.fromJson(result,InspectionPartolInfo.class);
                if(informationList != null){
                    listAdapter.setData(informationList.dataSource);
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    @Override
    public void initView() {
        showDataLoadingDialog();
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new HistoryPatrolListAdapter(this,
                R.layout.activity_history_patrol_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    private class HistoryPatrolListAdapter
            extends CommonAdapter<InspectionPartolInfo> {
        public HistoryPatrolListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, InspectionPartolInfo information) {
            ((TextView) (holder.getView(R.id.arrhpli_tv_time))).setText(
                    information.getCollTime());
            ((TextView) (holder.getView(R.id.arrhpli_tv_person))).setText(information.getRecPers());
        }
    }
}
