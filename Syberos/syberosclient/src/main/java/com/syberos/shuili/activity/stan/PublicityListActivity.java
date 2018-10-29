package com.syberos.shuili.activity.stan;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
import com.syberos.shuili.entity.basicbusiness.AttOrgBase;
import com.syberos.shuili.entity.standardization.ObjStanAppl;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公示
 * 0:补充材料 1:形式初审 2:资料审查3.现场复核4.会议审定 5.公示 6.发证公告 7.拒绝达标 8.已发证
 */


public class PublicityListActivity extends TranslucentActivity implements PullRecyclerView.OnPullRefreshListener {
    @BindView(R.id.recyclerView_list)
    PullRecyclerView recyclerView;

    ListAdapter listAdapter = null;
    ArrayList<ObjStanAppl> selectedReviewItemInformationList = new ArrayList<>();
    private ObjStanAppl objStanAppl = null;
    private int iSucessCount = 0;
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
                R.layout.dialog_publicity_review, null);
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
        return R.layout.activity_publicity_list;
    }

    @Override
    public void initListener() {
        recyclerView.setOnPullRefreshListener(this);
        recyclerView.setHasMore(false);

    }
    private void closeLoadingDialog(){
        closeDataDialog();
        recyclerView.refreshOrLoadComplete();
    }
    private void getObjStanAppls(){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/appl/objStanAppls/";
        HashMap<String,String>params = new HashMap<>();
        params.put("stat","5");
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
                    refreshUI();
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
        listAdapter.setData(objStanAppl.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        getObjStanAppls();
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_publicity_list_item);
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
     * 提交到公示公告表  提交内容
     */
    private void  commit(){
        showLoadingDialog("数据提交中...");
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
                    insetInfo2ObjPuno(item);
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    ToastUtils.show(errorInfo.getMessage());

                }
            });
        }
    }
    public static Date getDateAfter(Date d, int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }
    private void insetInfo2ObjPuno(ObjStanAppl item){
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/puno/objPuno/";
        HashMap<String,String> params = new HashMap<>();
        params.put("titl",item.getApplOrgName() + "达标申请公示公告中");
        params.put("cont","");
        params.put("punoType","2");
        params.put("releTime", CommonUtils.getCurrentDate());
        Date date = null;
        try {
            date = CommonUtils.stringToDate(params.get("releTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String endTime = String.valueOf(getDateAfter(date,5));
        params.put("endTime",endTime);
        params.put("relePers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
        params.put("releOrgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("valiTime","5天");
        params.put("stat","3");
        params.put("collTime",CommonUtils.getCurrentDate());
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserId());
        params.put("stanReviGuid",item.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {


            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {

            }
        });
    }
}
