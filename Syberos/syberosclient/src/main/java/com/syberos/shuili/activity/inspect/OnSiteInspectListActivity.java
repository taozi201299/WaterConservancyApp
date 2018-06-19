package com.syberos.shuili.activity.inspect;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.inspect.BisWinsProg;
import com.syberos.shuili.entity.inspect.ObjWinsPlan;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 现场稽查列表 行政版  该接口暂时没有提供
 * 水利稽查
 * OBJ_WINS_PLAN 表中获取单位的稽查计划，从稽查方案表中找到稽查方案详情
 */
public class OnSiteInspectListActivity extends TranslucentActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = OnSiteInspectListActivity.class.getSimpleName();

    private final String Title = "现场稽查";

    public static final String SEND_BUNDLE_KEY = "OnSiteInspectInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    @BindView(R.id.tv_action_bar2_title)
    TextView tv_action_bar2_title;

    ListAdapter listAdapter;
    private ObjWinsPlan objWinsPlan = null;
    private BisWinsProg bisWinsProg = null;
    private ArrayList<BisWinsProg>bisWinsProgs = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_inspect_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getObjWinsList();
    }

    @Override
    public void initView() {
        tv_action_bar2_title.setText(Title);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_on_site_inspect_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjWinsPlan information = objWinsPlan.dataSource.get(position);
        bundle.putSerializable("objWinsPlan", information);
        bundle.putSerializable("bisWinsProg",bisWinsProgs.get(position));
        intentActivity(this, InspectDetailActivity.class, false, bundle);
    }

    /**
     * 从稽查计划对象表中获取所有稽查列表
     */
    private void getObjWinsList(){
        String url = "";
        HashMap<String,String>params = new HashMap<>();
        params.put("planOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objWinsPlan = gson.fromJson(result,ObjWinsPlan.class);
                if(objWinsPlan == null || objWinsPlan.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(objWinsPlan.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                    return;
                }
                getWinsPlanInfo();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    /**
     * 根据稽查计划Guid从稽查方案表中获取稽查方案详情
     */
    private void getWinsPlanInfo(){
        ArrayList<ObjWinsPlan> list = (ArrayList<ObjWinsPlan>) objWinsPlan.dataSource;
        String url = "";
        HashMap<String,String>params = new HashMap<>();
        final int size = list.size();
        for(int i = 0; i<size;i ++){
            final ObjWinsPlan item = list.get(i);
            params.put("winsPlanGuid",item.getGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisWinsProg = gson.fromJson(result,BisWinsProg.class);
                    if(bisWinsProg == null || bisWinsProg.dataSource == null ||
                            bisWinsProg.dataSource.size() == 0){
                        closeDataDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    bisWinsProgs.add(bisWinsProg.dataSource.get(0));
                    item.setStartTime(bisWinsProg.dataSource.get(0).getStartTime());
                    item.setEndTime(bisWinsProg.dataSource.get(0).getEndTime());
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
    private void refreshUI(){
        listAdapter.setData(objWinsPlan.dataSource);
        listAdapter.notifyDataSetChanged();

    }
    private class ListAdapter extends CommonAdapter<ObjWinsPlan> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjWinsPlan information) {

            Button button = (Button) holder.getView(R.id.btn_input_problem);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY, information);
                    intentActivity(OnSiteInspectListActivity.this,
                            InspectProjectSelectActivity.class, false, bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getWinsPlanName());
            ((TextView) (holder.getView(R.id.tv_batch))).setText(
                    information.getWinsArrayNum());
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getStartTime() + "-"+ information.getEndTime());
        }
    }
}
