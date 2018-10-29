package com.syberos.shuili.activity.stan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.standardization.ObjStanAppl;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomEdit;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 待形式初审  --- 达标申请对象表
 */
public class FormalReviewListActivity extends TranslucentActivity
        implements CommonAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;
    @BindView(R.id.tv_review)
            TextView tv_review;

    ListAdapter listAdapter = null;
    List<ObjStanAppl> selectedReviewItemInformationList = new ArrayList<>();
    private ObjStanAppl objStanAppl = null;

    private ArrayList<ObjStanAppl>result = new ArrayList<>();

    private int iSucessCount =0;
    private int iFailedCount = 0;


    @OnClick(R.id.iv_action_bar_back)
    void onBackClicked() {
        activityFinish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_formal_review_list;
    }

    @Override
    public void initListener() {

    }
    private void closeLoadingDialog(){
        closeDataDialog();
    }
    private void getObjStanAppls() {
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppls/";
        HashMap<String, String> params = new HashMap<>();
        params.put("stat", "1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objStanAppl = gson.fromJson(result, ObjStanAppl.class);
                if (objStanAppl == null || objStanAppl.dataSource == null) {
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
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

    public void getOrgName() {
        iSucessCount = 0;
        iFailedCount = 0;
        final int size = objStanAppl.dataSource.size();
        if(size == 0){
            closeDataDialog();
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
            refreshUI();
        }
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
                        closeLoadingDialog();
                        refreshUI();
                    }

                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == size) {
                        closeLoadingDialog();
                        refreshUI();
                    }
                }
            });
        }

    }
    private void refreshUI(){
        for(ObjStanAppl item : objStanAppl.dataSource){
            result.add(item);
        }
        listAdapter.setData(result);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        if(result != null) result.clear();
        iSucessCount = 0;
        iFailedCount = 0;
        if(selectedReviewItemInformationList == null ) {
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            finish();
            return;
        }
       showDataLoadingDialog();
        getObjStanAppls();

    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_scene_review_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
        tv_review.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {

    }
    private void go2Form(){
        final Dialog repetitionDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
                R.layout.dialog_formal_review, null);
        final CustomEdit customEdit = (CustomEdit) v1.findViewById(R.id.et_content);
        repetitionDialog.setContentView(v1);
        Window dialogWindow = repetitionDialog.getWindow();
        WindowManager.LayoutParams lp1 = dialogWindow.getAttributes();

        lp1.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.gravity = Gravity.CENTER;
        repetitionDialog.setCancelable(true);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp1);
        Button bt_cancel = v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repetitionDialog.dismiss();
                commit(1,customEdit.getText().toString());
            }
        });

        Button btn_center = (Button)v1.findViewById(R.id.btn_center);
        btn_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repetitionDialog.dismiss();
                commit(2,customEdit.getText().toString());
            }
        });

        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repetitionDialog.dismiss();
                commit(0,customEdit.getText().toString());
            }
        });

        repetitionDialog.show();
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
                    selectedReviewItemInformationList.clear();
                    selectedReviewItemInformationList.add(information);
                    go2Form();
                }
            });
//            background.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    checkBox.setChecked(!checkBox.isChecked());
//                }
//            });
//
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(buttonView.isShown()) {
//                        if (isChecked) {
//                            selectedReviewItemInformationList.add(information);
//                        } else {
//                            selectedReviewItemInformationList.remove(information);
//                        }
//                    }
//                }
//            });
            // 申请单位名称
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getApplOrgName());
            // 申请时间
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getApplTime());
            // 申请等级
            String grade = GlobleConstants.stanGradeMap.get(information.getApplGrade());
            ((TextView) (holder.getView(R.id.tv_level))).setText(grade == null ?"":grade);
        }
    }

    /**
     * 形式初审表 只有审批没有审核 水利部用户和主管单位用户均可完成该操作
     */
    private void commit(int result,String opinion){
        showLoadingDialog("数据提交...");
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/from/firsttria/bisFromFirsttria/";
        HashMap <String,String>params= new HashMap<>();
        for(ObjStanAppl item : selectedReviewItemInformationList){
            params.put("applGuid",item.getGuid());
            params.put("veriResu",String.valueOf(result));
            params.put("veriOpin",opinion);
            params.put("fromFirsttriaCode",item.getStanApplCode());
            params.put("collTime", CommonUtils.getCurrentDate());
            LocalCacheEntity localCacheEntity = new LocalCacheEntity();
            localCacheEntity.url = url;
            localCacheEntity.params = params;
            localCacheEntity.type = 0;
            localCacheEntity.seriesKey = UUID.randomUUID().toString();
            localCacheEntity.commitType = 0;

            ArrayList<AttachMentInfoEntity>attachMentInfoEntities = new ArrayList<>();
            SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities,new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    updateState();
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
        }
    }
    private void updateState(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppl/";
        HashMap<String,String> params= new HashMap<>();
        params.put("stat","2");
        for(ObjStanAppl item : selectedReviewItemInformationList){
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
                    closeDataDialog();
                    ToastUtils.show("提交成功");
                    initData();
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
