package com.syberos.shuili.activity.stan;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.standardization.BisStanReviRec;
import com.syberos.shuili.entity.standardization.ObjPuno;
import com.syberos.shuili.entity.standardization.ObjStanAppl;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.OnClick;

// 公告 从公告公示表中获取

public class NoticeListActivity extends TranslucentActivity implements PullRecyclerView.OnPullRefreshListener {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;

    ListAdapter listAdapter = null;
    ArrayList<ObjStanAppl> selectedReviewItemInformationList = new ArrayList<>();
    private ObjStanAppl objStanAppl = null;
    private  int iSucessCount = 0;
    private int iFailedCount = 0;

    @OnClick(R.id.tv_review)
    void onReviewClicked() {
        // use selectedReviewItemInformationList
        if(selectedReviewItemInformationList.size() == 0){
            ToastUtils.show("没有需要审核的项");
            return;
        }
        final Dialog repetitionDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
                R.layout.dialog_notice_review, null);
        repetitionDialog.setContentView(v1);
        Window dialogWindow = repetitionDialog.getWindow();
        WindowManager.LayoutParams lp1 = dialogWindow.getAttributes();

        lp1.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.gravity = Gravity.CENTER;
        repetitionDialog.setCancelable(true);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp1);
        Button bt_cancel = (Button)v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repetitionDialog.dismiss();
            }
        });

        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repetitionDialog.dismiss();
                commit();
            }
        });

        repetitionDialog.show();
    }

    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_list;
    }

    @Override
    public void initListener() {

    }
    private void getObjStanAppls(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppls/";
        HashMap<String,String>params = new HashMap<>();
        params.put("stat","6");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objStanAppl = gson.fromJson(result,ObjStanAppl.class);
                if(objStanAppl == null || objStanAppl.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
                else if(objStanAppl.dataSource.size() == 0){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                }
                getOrgName();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());

            }
        });
    }
    private void refreshUI(){
        listAdapter.setData(objStanAppl.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getObjStanAppls();
    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Stan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_notice_list_item);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onRefresh() {
        getObjStanAppls();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<ObjStanAppl> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(final ViewHolder holder, final ObjStanAppl information) {

            final CheckBox checkBox = (CheckBox) holder.getView(R.id.cb_select);

            LinearLayout background = (LinearLayout) holder.getView(R.id.ll_background);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isShown()) {
                        if (isChecked) {
                            selectedReviewItemInformationList.add(information);
                        } else {
                            selectedReviewItemInformationList.remove(information);
                        }
                    }
                }
            });

            // 申请单位名称
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getApplOrgName());
            // 申请时间
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getApplTime());
            // 申请等级
            ((TextView) (holder.getView(R.id.tv_level))).setText(information.getApplGrade());
        }
    }
    /**
     * 提交到公示公告表
     */
    private void  commit(){
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog("数据提交中...");
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppl/";
        HashMap<String,String> params= new HashMap<>();
        params.put("stat","8");
        for(final ObjStanAppl item : selectedReviewItemInformationList){
            url += item.getGuid() +"/"+"?";
            for(String key :params.keySet()){
                url += key;
                url +="=";
                url += params.get(key);
                url += "&";
            }
            url = url.substring(0,url.length() -1);
            LocalCacheEntity localCacheEntity = new LocalCacheEntity();
            localCacheEntity.url = url;
            ArrayList<AttachMentInfoEntity>attachMentInfoEntities = new ArrayList<>();
            localCacheEntity.params = params;
            localCacheEntity.type = 1;
            localCacheEntity.commitType = 1;
            localCacheEntity.seriesKey = UUID.randomUUID().toString();
            SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    update2ObjPuno(item);
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }
    public void getOrgName() {
        iSucessCount = 0;
        iFailedCount = 0;
        final int size = objStanAppl.dataSource.size();
        for(int i = 0; i< size; i++) {
            String url = GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", objStanAppl.dataSource.get(i).getApplOrgGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    AttOrgBase attOrgBase = gson.fromJson(result, AttOrgBase.class);
                    if (attOrgBase != null && attOrgBase.dataSource != null && attOrgBase.dataSource.size() > 0) {
                        objStanAppl.dataSource.get(finalI).setApplOrgName(attOrgBase.dataSource.get(0).getOrgName());
                    }
                    if(iSucessCount +iFailedCount == size) {
                        getObjPunGuid();
                    }

                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == size) {
                        getObjPunGuid();
                    }
                }
            });
        }

    }
    private void getObjPunGuid(){
        iSucessCount = 0;
        iFailedCount = 0;
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/puno/objPunos/";
        HashMap<String,String>params = new HashMap<>();
        for(final ObjStanAppl item: objStanAppl.dataSource) {
            params.put("stanReviGuid", item.getGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    ObjPuno objPuno = gson.fromJson(result,ObjPuno.class);
                    if(objPuno != null && objPuno.dataSource != null && objPuno.dataSource.size() > 0){
                        item.setObjPunoGuid(objPuno.dataSource.get(0).getGuid());
                    }
                    if(iSucessCount + iFailedCount == objStanAppl.dataSource.size()){
                        closeDataDialog();
                        refreshUI();
                    }

                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    if(iSucessCount + iFailedCount == objStanAppl.dataSource.size()){
                        closeDataDialog();
                        refreshUI();
                    }
                }
            });
        }

    }
    private void update2ObjPuno(ObjStanAppl item){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/puno/objPuno/";
        HashMap<String,String> params = new HashMap<>();
        params.put("stat","3");
        url += item.getObjPunoGuid() +"/"+"?";
        for(String key :params.keySet()){
            url += key;
            url +="=";
            url += params.get(key);
            url += "&";
        }
        url = url.substring(0,url.length() -1);
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity>attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                iSucessCount ++ ;
                if(iSucessCount + iFailedCount == objStanAppl.dataSource.size()){
                    closeDataDialog();
                    ToastUtils.show("提交成功");
                }


            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                iFailedCount ++;
                if(iSucessCount + iFailedCount == objStanAppl.dataSource.size()){
                    closeDataDialog();
                    ToastUtils.show("提交成功");
                }

            }
        });
    }
}
