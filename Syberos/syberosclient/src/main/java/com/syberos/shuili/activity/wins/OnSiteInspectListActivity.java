package com.syberos.shuili.activity.wins;

import android.content.Context;
import android.os.Bundle;
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
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsGroupAll;
import com.syberos.shuili.entity.wins.BisWinsProg;
import com.syberos.shuili.entity.wins.BisWinsProgAll;
import com.syberos.shuili.entity.wins.BisWinsProjAll;
import com.syberos.shuili.entity.wins.BisWinsStaff;
import com.syberos.shuili.entity.wins.ObjWinsPlan;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * 现场稽察列表 行政版  该接口暂时没有提供
 * 水利稽察
 * BIS_WINS_STAFF 表中获取该用户所在的稽察组
 * 根据稽察组ID获取组信息
 *
 * 8.2.2.83	水利稽察组（BIS_WINS_GROUP）
 * 从水利稽察组中获取所在的稽察组
 */
public class OnSiteInspectListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener,PullRecyclerView.OnPullRefreshListener {

    private final String TAG = OnSiteInspectListActivity.class.getSimpleName();

    private final String Title = "现场稽察";

    public static final String SEND_BUNDLE_KEY = "OnSiteInspectInformation";

    @BindView(R.id.recyclerView_record_review)
    PullRecyclerView recyclerView;

    @BindView(R.id.tv_action_bar2_title)
    TextView tv_action_bar2_title;

    ListAdapter listAdapter;
    /**
     * 稽察组对象
     */
    private BisWinsGroupAll bisWinsGroupAll = null;
    private BisWinsGroupAll bisWinsGroupAll1 = null;
    ArrayList<BisWinsGroupAll>datas = new ArrayList<>();

    private String personId;

    private int iSucessCount = 0;
    private int iFailedCount = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_inspect_list;
    }

    @Override
    public void initListener() {
        recyclerView.setOnPullRefreshListener(this);
        recyclerView.setHasMore(false);

    }

    @Override
    public void initData() {
//        datas.clear();
//        iSucessCount = 0;
//        iFailedCount = 0;
//        showDataLoadingDialog();
//        getPersonID();
    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Wins);
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_on_site_inspect_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
        datas.clear();
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getPersonID();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bisWinsGroupAll", datas.get(position));
        intentActivity(OnSiteInspectListActivity.this,
                InspectionDetailActivity.class, false, bundle);
    }
    private void getPersonID(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/staff/bisWinsStaffs/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("persGuid",SyberosManagerImpl.getInstance().getCurrentUserId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                BisWinsStaff bisWinsStaff = gson.fromJson(result,BisWinsStaff.class);
                if(bisWinsStaff == null || bisWinsStaff.dataSource == null ){
                    closeDataDialog();
                    recyclerView.refreshOrLoadComplete();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }if(bisWinsStaff.dataSource.size() == 0){
                    closeDataDialog();
                    recyclerView.refreshOrLoadComplete();
                    return;
                }
               personId =  bisWinsStaff.dataSource.get(0).getGuid();
               /// getBisWinsGroupBySepStafGuid();
                getBisWinsGroupBySepStafAssiGuid();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                recyclerView.refreshOrLoadComplete();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getBisWinsGroupBySepStafGuid(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/group/bisWinsGroups/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("speStafGuid",personId);
      //  params.put("speStafGuid","95873d092b294fccb619e3c56dec9dfe");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsGroupAll = gson.fromJson(result, BisWinsGroupAll.class);
                if(bisWinsGroupAll == null || bisWinsGroupAll.dataSource == null){
                    closeDataDialog();
                    recyclerView.refreshOrLoadComplete();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }else {
                    datas.addAll(bisWinsGroupAll.dataSource);
                    getBisWinsGroupBySepStafAssiGuid();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                recyclerView.refreshOrLoadComplete();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getBisWinsGroupBySepStafAssiGuid(){
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/wins/group/bisWinsGroups/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("speStafAssiGuid",personId);
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisWinsGroupAll1 = gson.fromJson(result, BisWinsGroupAll.class);
                if(bisWinsGroupAll1 == null || bisWinsGroupAll1.dataSource == null){
                    closeDataDialog();
                    recyclerView.refreshOrLoadComplete();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                if(bisWinsGroupAll1.dataSource.size() == 0){
                    closeDataDialog();
                    recyclerView.refreshOrLoadComplete();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                    return;
                }
                datas.addAll(bisWinsGroupAll1.dataSource);
              getWinsArrayCode();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                recyclerView.refreshOrLoadComplete();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    //稽察计划编码	WINS_PLAN_CODE
   // 稽察批次编号	WINS_ARRAY_CODE

    private void getWinsArrayCode(){
        iSucessCount = 0;
        iFailedCount = 0;
        final int size = datas.size();
        BisWinsGroupAll item;
        for(int i = 0; i < size; i++) {
            item = datas.get(i);
            String url = GlobleConstants.strIP +"/sjjk/v1/bis/wins/prog/bisWinsProgs/";
            urlTags.add(url);
            HashMap<String, String> params = new HashMap<>();
            params.put("guid",item.getWinsProgGuid());
            final BisWinsGroupAll finalItem = item;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    BisWinsProgAll bisWinsProgAll;
                    Gson gson = new Gson();
                    bisWinsProgAll = gson.fromJson(result, BisWinsProgAll.class);
                    if (bisWinsProgAll == null || bisWinsProgAll.dataSource == null || bisWinsProgAll.dataSource.size() == 0) {
                    } else {
                        finalItem.setWinsArrayCode(bisWinsProgAll.dataSource.get(0).getWinsArrayCode());
                        finalItem.setWinsPlanCode(bisWinsProgAll.dataSource.get(0).getWinsPlanGuid());
                        finalItem.setStartTime(bisWinsProgAll.dataSource.get(0).getStartTime());
                        finalItem.setEndTime(bisWinsProgAll.dataSource.get(0).getEndTime());
                    }
                    if(iSucessCount +iFailedCount == size){
                        getWinsPlanName();
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == size){
                        getWinsPlanName();
                    }
                }
            });
        }


    }
    private void getWinsPlanName(){
        iSucessCount = 0;
        iFailedCount = 0;
        final int size = datas.size();
        BisWinsGroupAll item ;
        for(int i = 0; i < size; i++) {
            item = datas.get(i);
            if(item.getWinsPlanCode() == null || item.getWinsPlanCode().isEmpty())continue;
            String url = GlobleConstants.strIP  + "/sjjk/v1/obj/wins/plan/objWinsPlans/";
            urlTags.add(url);
            HashMap<String,String>params = new HashMap<>();
            params.put("guid",item.getWinsPlanCode());
            final BisWinsGroupAll finalItem = item;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++ ;
                    Gson gson = new Gson();
                    ObjWinsPlan objWinsPlan ;
                    objWinsPlan = gson.fromJson(result,ObjWinsPlan.class);
                    if(objWinsPlan != null && objWinsPlan.dataSource != null
                            || objWinsPlan.dataSource.size() > 0){
                        finalItem.setWinsPlanName(objWinsPlan.dataSource.get(0).getWinsPlanName());

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

    private void refreshUI(){
        closeDataDialog();
        recyclerView.refreshOrLoadComplete();
        if(datas.size() == 0){
            ToastUtils.show("未获取到稽察任务");
        }
        listAdapter.setData(datas);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        datas.clear();
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getPersonID();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<BisWinsGroupAll> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final BisWinsGroupAll information) {

            Button button = (Button) holder.getView(R.id.btn_input_problem);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bisWinsGroupAll", information);
                    intentActivity(OnSiteInspectListActivity.this,
                            InspectProjectSelectActivity.class, false, bundle);
                }
            });
            ((TextView) (holder.getView(R.id.tv_plan_value))).setText(information.getWinsPlanName());
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                   "稽查组:" +  information.getWinsGroupNum());
            ((TextView) (holder.getView(R.id.tv_batch))).setText(
                    information.getWinsArrayCode());
            ((TextView) (holder.getView(R.id.tv_time))).setText(information.getCollTime());
        }
    }
}
