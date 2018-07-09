package com.syberos.shuili.activity.dangersource;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import com.syberos.shuili.entity.dangersource.BisHazMajRegWrit;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 行政版危险源核销审核 重大危险源备案核销表
 * 需要修改
 */
public class WriteOffVerificationListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {
    private final String TAG = WriteOffVerificationListActivity.class.getSimpleName();

    private final String Title = "核销审核";

    public static final String SEND_BUNDLE_KEY = "DangerousInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;
    DangerousListAdapter listAdapter;
    ObjHaz objHaz  = null;
    ArrayList<ObjHaz>objHazs = new ArrayList<>();
    private DicInfo hazsGrade = null;
    BisHazMajRegWrit bisHazMajRegWrit = null;
    private ArrayList<OrgInfo>orgInfos = new ArrayList<>();

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjHaz information = objHazs.get(position);
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
    private void getHazMajRegWritList(){
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/haz/maj/bisHazMajRegWrits/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hazStat","2");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson  = new Gson();
                bisHazMajRegWrit = (BisHazMajRegWrit)gson.fromJson(result,BisHazMajRegWrit.class);
                if(bisHazMajRegWrit != null){
                    getHazsList();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private void getHazsList(){
        final int size = bisHazMajRegWrit.dataSource.size();
        for(int i = 0 ; i < size; i++) {
            BisHazMajRegWrit item = bisHazMajRegWrit.dataSource.get(i);
            String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHazs/";
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", item.getHazGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeDataDialog();
                    Gson gson = new Gson();
                    objHaz = (ObjHaz) gson.fromJson(result, ObjHaz.class);
                    if(objHaz == null || objHaz.dataSource == null || !objHaz.code.equals("0")){
                        String errMsg = ErrorInfo.ErrorCode.valueOf(-5).getMessage();
                        ToastUtils.show(errMsg);
                        return;
                    }
                    objHazs.add(objHaz.dataSource.get(0));
                    if(objHazs.size() == size){
                        getEnterPriseName();
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
    private void  getEnterPriseName(){
        final int size = objHazs.size();
        for(int i = 0; i < size; i ++) {
            final ObjHaz item = objHazs.get(i);
            String url = "http://192.168.1.8:8080/wcsps-supervision/v1/jck/obj/objEngs/";
            HashMap<String, String> params = new HashMap<>();
            params.put("guid", item.getEngGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    ObjectEngine objectEngine = null;
                    objectEngine = gson.fromJson(result, ObjectEngine.class);
                    if(!objectEngine.code.equals("0")){
                        String errMsg = ErrorInfo.ErrorCode.valueOf(-5).getMessage();
                        ToastUtils.show(errMsg);
                        return;
                    }
                    if(objectEngine.dataSource.size() >0) {
                        item.setEngineName(objectEngine.dataSource.get(0).getEngName());
                    }else {
                        item.setEngineName("未知");
                    }
                    if(objHazs.indexOf(item) == size -1){
                        getUnitName();
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
    private void getUnitName(){
        final int size = objHazs.size();
        for(int i = 0; i < size; i ++) {
            final ObjHaz item = objHazs.get(i);
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
                    if(objHazs.indexOf(item) == size -1){
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
        if(objHazs != null){
            listAdapter.setData(objHazs);
            listAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void initData() {
        getHazMajRegWritList();
    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        showDataLoadingDialog();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new WriteOffVerificationListActivity.DangerousListAdapter(this,
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
        public void convert(ViewHolder holder, final ObjHaz dangerousInformation) {
            int type = DangerousInformation.TYPE_BIGER;
            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            RelativeLayout ll_report_after = (RelativeLayout)(holder.getView(R.id.ll_report_after));
            ll_report_after.setVisibility(View.VISIBLE);
            ((TextView)holder.getView(R.id.tv_time)).setVisibility(View.GONE);
            ((LinearLayout)holder.getView(R.id.ll_content)).setVisibility(View.GONE);
            Button btn = (Button)(holder.getView(R.id.btn_text));
            btn.setText("审核");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY, dangerousInformation);
                    bundle.putInt("title",0);
                    intentActivity(WriteOffVerificationListActivity.this,RecordReviewConfirmActivity.class,false,bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_type))).setText(
                    getHazsGradeName(String.valueOf(type)));
            switch (type) {
                case DangerousInformation.TYPE_NORMAL:{
                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                }
                break;
                case DangerousInformation.TYPE_BIGER: {
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
