package com.syberos.shuili.activity.dangersource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.entity.UserExtendInfo;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;

/**
 * 企事业危险源查询
 */
public class IssueTrackingListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = IssueTrackingListActivity.class.getSimpleName();

    private final String Title = "危险源查询";

    public static final String SEND_BUNDLE_KEY = "DangerousInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;
    IssueTrackingListActivity.DangerousListAdapter listAdapter;
    ObjHaz inspectionList  = null;
    private DicInfo hazsGrade = null;

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjHaz information = inspectionList.dataSource.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this, InspectionDetailActivity.class, false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_list;
    }

    @Override
    public void initListener() {

    }
    private void getHazsDic(){
        String url  = "http://192.168.1.8:8080/wcsps-supervision/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_HAZ");
        params.put("attColCode","HIDD_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hazsGrade  = gson.fromJson(result,DicInfo.class);
                if(hazsGrade == null || hazsGrade.dataSource ==null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getHazsList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private String getHazsGradeName(String dicCode){
        String dicName = "";
        if(hazsGrade != null){
            for(DicInfo dicInfo :hazsGrade.dataSource){
                if(dicInfo.getDcItemCode().equals(dicCode)){
                    dicName = dicInfo.getDcItemName();
                    break;
                }
            }
        }
        return dicName;
    }
    private void getHazsList(){
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHazs/";
        HashMap<String,String>params = new HashMap<>();
        final UserExtendInfo info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        //params.put("orgGuid",info.getOrgId());
        params.put("orgGuid","537AD1AB8E7447AAA249AB22A5344955");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                inspectionList = (ObjHaz)gson.fromJson(result,ObjHaz.class);
                if(inspectionList != null){
                    for(ObjHaz item : inspectionList.dataSource){
                        boolean bRet = getEnterPriseName(item);
                        if(!bRet){
                            String errMsg  = ErrorInfo.ErrorCode.valueOf(-2).getMessage();
                            ToastUtils.show(errMsg);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    // TODO: 2018/5/14 need to modify
    private boolean getEnterPriseName(final ObjHaz item){
        boolean bRet = true;
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/jck/obj/objEngs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",item.getEngGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                ObjectEngine objectEngine  = null;
                objectEngine = gson.fromJson(result,ObjectEngine.class);
                int index = inspectionList.dataSource.indexOf(item);
                ObjHaz objHaz = inspectionList.dataSource.get(index);
                objHaz.setEngineName(objectEngine.dataSource.get(0).getEngName());
                if(index == inspectionList.dataSource.size() -1) {
                    getUnitName();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
        return bRet;


    }
    private void getUnitName(){
        final int size = inspectionList.dataSource.size();
        for(int i = 0; i < size; i ++) {
            final ObjHaz item = inspectionList.dataSource.get(i);
            String url = "http://192.168.1.8:8080/wcsps-supervision/v1/att/org/base/attOrgBases/";
            HashMap<String,String>params = new HashMap<>();
            params.put("guid",item.getOrgGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    OrgInfo orgInfo = gson.fromJson(result,OrgInfo.class);
                    if(orgInfo != null && orgInfo.dataSource != null && orgInfo.dataSource.size() > 0){
                        item.setOrgName(orgInfo.dataSource.get(0).getOrgName());
                    }else {
                        item.setOrgName("未知");
                    }
                    if(inspectionList.dataSource.indexOf(item) == size -1){
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
        if(inspectionList != null){
            listAdapter.setData(inspectionList.dataSource);
            listAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void initData() {
        getHazsDic();

    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        showDataLoadingDialog();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new IssueTrackingListActivity.DangerousListAdapter(this,
                R.layout.activity_recorded_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);


    }

    private class DangerousListAdapter extends CommonAdapter<ObjHaz> {
        public DangerousListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, ObjHaz dangerousInformation) {
            int type = Integer.valueOf(dangerousInformation.getHiddGrad());
            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            RelativeLayout ll_report_after = (RelativeLayout)(holder.getView(R.id.ll_report_after));
            ll_report_after.setVisibility(View.GONE);
            ((TextView)holder.getView(R.id.tv_time)).setVisibility(View.GONE);
            ((LinearLayout)holder.getView(R.id.ll_content)).setVisibility(View.GONE);

            switch (type) {
                case DangerousInformation.TYPE_NORMAL: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_normal);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                }
                break;
                case DangerousInformation.TYPE_BIGGEST: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_big_shape));
                }
                break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    dangerousInformation.getHazName());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    dangerousInformation.getEngineName());
        }
    }
}
