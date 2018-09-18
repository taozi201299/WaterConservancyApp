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
import com.syberos.shuili.entity.standardization.BisStanReviRec;
import com.syberos.shuili.entity.standardization.ObjStanRevis;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomEdit;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

// 审定 数据库中缺少审定意见字段 从审核记录表中获取

public class ReviewAndApprovalListActivity extends TranslucentActivity implements PullRecyclerView.OnPullRefreshListener{

    @BindView(R.id.recyclerView_list)
    PullRecyclerView recyclerView;

    ListAdapter listAdapter = null;
    ArrayList<BisStanReviRec> selectedReviewItemInformationList = new ArrayList<>();
    BisStanReviRec bisStanReviRec = null;

    @OnClick(R.id.tv_review)
    void onReviewClicked() {
        // use selectedReviewItemInformationList
        if(selectedReviewItemInformationList.size() == 0){
            ToastUtils.show("没有需要审核的项");
            return;
        }
        final Dialog repetitionDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
                R.layout.dialog_approval_review, null);
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
        Button bt_cancel = (Button)v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("TODO: 不同意，后续处理逻辑: " + customEdit.getText().toString());
                repetitionDialog.dismiss();
            }
        });

        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("TODO: 同意，后续处理逻辑: " + customEdit.getText().toString());
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
        return R.layout.activity_review_and_approval_list;
    }

    @Override
    public void initListener() {
        recyclerView.setHasMore(false);
        recyclerView.setOnPullRefreshListener(this);

    }
    private void getobjStanRevisList() {
        String url = GlobleConstants.strIP + "/sjjk/v1/obj/stan/revi/selectStanrdReview/";
        HashMap<String,String> param = new HashMap<>();
        param.put("reviType","3");
        param.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String response) {
                closeDataDialog();
                recyclerView.refreshOrLoadComplete();
                Gson gson = new Gson();
                bisStanReviRec = (BisStanReviRec)gson.fromJson(response,BisStanReviRec.class);
                if(bisStanReviRec != null && bisStanReviRec.dataSource != null){
                    refreshUI();
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
    private void refreshUI(){
        listAdapter.setData(bisStanReviRec.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        getobjStanRevisList();
    }

    @Override
    public void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_review_and_approval_list_item);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onRefresh() {
        getobjStanRevisList();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<BisStanReviRec> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(final ViewHolder holder, final BisStanReviRec information) {

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
                    information.getWiunName());
            // 申请时间
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getApplTime());
            // 申请等级
            ((TextView) (holder.getView(R.id.tv_level))).setText(information.getApplGrade());

        }
    }
    /**
     * 提交到标准化评审记录表
     */
    private void  commit(){
        String url = "http://192.168.1.8:8080/sjjk/v1/obj/stan/revi/bisStanReviRec/";
        HashMap<String,String> params= new HashMap<>();
        params.put("reviType","4");
        // 审定意见
        int size = selectedReviewItemInformationList.size();
        for(BisStanReviRec item : selectedReviewItemInformationList){
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
            SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities,new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    ToastUtils.show("提交成功");
                    finish();
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    ToastUtils.show(errorInfo.getMessage());

                }
            });
        }
    }
}
