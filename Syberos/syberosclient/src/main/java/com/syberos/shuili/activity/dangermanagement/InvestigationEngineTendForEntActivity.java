package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.securitycheck.EnterprisesElementCheckCreateHiddenActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.securitycheck.BisSeChit;
import com.syberos.shuili.entity.securitycheck.BisSinsRec;
import com.syberos.shuili.entity.securitycheck.ObjSe;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by Administrator on 2018/4/19.
 * 工程标段
 */

public class InvestigationEngineTendForEntActivity extends BaseActivity implements CommonAdapter.OnItemClickListener,PullRecyclerView.OnPullRefreshListener{
    private final String Title = "工程标段选择";
    @BindView(R.id.recyclerView_investigation)
    PullRecyclerView recyclerView;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    InvestigationEngineTendForEntActivity.InvestigationAdapter investigationAdapter ;
    MvEngColl objectEngine = null;
    ObjectTend objectTend = null;
    // 判断是哪个类型的隐患 hidden 隐患排查 element 元素检查隐患 check 现场检查隐患
    String type = "";

    /**
     *  安全元素对象信息
     */
    private ObjSe information = null;
    /**
     * 安全元素下的检查项信息
     */
    private BisSeChit bisSeChit = null;
    /**
     * 安全检查记录
     */
    private BisSinsRec bisSinsRec = null;
    @Override
    public int getLayoutId() {
        return  R.layout.activity_investigation_enterprise_task_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        type = bundle.getString("type");
        if(objectEngine ==  null) {
            objectEngine = (MvEngColl) bundle.getSerializable("data");
            if(objectEngine == null){
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                activityFinish();
            }
        }
        if(type.equals("element")) {
            if (information == null) {
                information = (ObjSe) bundle.getSerializable("element");
                if(information == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                    activityFinish();
                }
            }
            if(bisSeChit == null){
                bisSeChit = (BisSeChit)bundle.getSerializable("checkItem");
                if(bisSeChit == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                    activityFinish();
                }
            }
        }
        if(type.equals("check")) {
            if (bisSinsRec == null) {
                bisSinsRec = (BisSinsRec) bundle.getSerializable("checkItem");
                if(bisSinsRec == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                    activityFinish();
                }
            }
        }
        getTendInfo(objectEngine);

    }

    private void getTendInfo(final MvEngColl item){
            // 包含标段
            String url = GlobleConstants.strIP + "/sjjk/v1/jck/obj/objTends/";
            HashMap<String,String> params = new HashMap<>();
            params.put("engGuid",item.getId());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeLoadingDialog();
                    Gson gson = new Gson();
                    objectTend = gson.fromJson(result,ObjectTend.class);
                    if(objectTend == null || objectTend.dataSource == null){
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                        return;
                    }
                    refreshUI();
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeLoadingDialog();
                    ToastUtils.show(errorInfo.getMessage());
                }
            });
    }
    private void closeLoadingDialog(){
        closeDataDialog();
    }

    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        ll_commit.setVisibility(View.GONE);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        investigationAdapter = new InvestigationAdapter(this,R.layout.activity_investigation_task_item);
        recyclerView.setAdapter(investigationAdapter);
        investigationAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",objectEngine);
        bundle.putSerializable("tend", objectTend.dataSource.get(position));
        bundle.putString("type",type);
        bundle.putBoolean("hasTend",true);
        if(type.equals("element")) {
            bundle.putSerializable("element", information);
        }if(type.equals("check")) {
            bundle.putSerializable("checkItem", bisSinsRec);
        }
        if(type.equals("hidden")) {
            intentActivity(InvestigationEngineTendForEntActivity.this, InvestigationAddForEnterpriseActivity.class, false, bundle);
        }else {
            intentActivity(InvestigationEngineTendForEntActivity.this, EnterprisesElementCheckCreateHiddenActivity.class,false,bundle);
        }

    }
    private void refreshUI(){
        if(objectTend != null){
            investigationAdapter.setData(objectTend.dataSource);
            investigationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    private class InvestigationAdapter extends CommonAdapter<ObjectTend> {
        public InvestigationAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }
        @Override
        public void convert(ViewHolder holder, ObjectTend investigationInfo) {
            LinearLayout ll_type = null;
            TextView tv_time;
            LinearLayout ll_name;
            TextView tv_content_label;
            TextView  tv_content;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            ll_type.setVisibility(View.GONE);
            tv_time = (TextView)(holder.getView(R.id.tv_time));
            tv_time.setVisibility(View.GONE);
            ll_name = (LinearLayout)(holder.getView(R.id.ll_name));
            ll_name.setVisibility(View.GONE);
            tv_content_label = (TextView)(holder.getView(R.id.tv_content_label));
            tv_content = (TextView)(holder.getView(R.id.tv_content));
            tv_content_label.setText("责任人电话：");
            ShapeDrawable bgShape = null;
            ( (TextView)(holder.getView(R.id.tv_title))).setText(investigationInfo.getTendName());
            tv_content.setText(investigationInfo.getLegTel());
        }
    }
}
