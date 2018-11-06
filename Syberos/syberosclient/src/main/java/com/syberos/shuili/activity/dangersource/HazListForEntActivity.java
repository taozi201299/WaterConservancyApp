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
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.dangersource.BisHazRegDetail;
import com.syberos.shuili.entity.userinfo.UserExtendInformation;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * 企事业单位功能 风险源巡视
 */
public class HazListForEntActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = HazListForEntActivity.class.getSimpleName();

    private final String Title = "危险源巡查";

    public static final String SEND_BUNDLE_KEY = "DangerousInformation";

    private DicInfo hazsGrade = null;

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;
    @BindView(R.id.ll_search)
            LinearLayout ll_search;
    DangerousListAdapter listAdapter;
    ObjHaz inspectionList  = null;
    private int iSucessCount = 0;
    private int iFailedCount = 0;
    ArrayList<ObjHaz> datas = new ArrayList<>();

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjHaz information = datas.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this, HazDetailForEntActivity.class, false, bundle);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_list;
    }

    @Override
    public void initListener() {

    }

    private void getHazsDic(){
        String url  = GlobleConstants.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_HAZ");
        params.put("attColCode","HAZ_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                hazsGrade  = gson.fromJson(result,DicInfo.class);
                if(hazsGrade == null || hazsGrade.dataSource == null){
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
        String url = GlobleConstants.strIP + "/sjjk/v1/bis/obj/objHazs/";
        HashMap<String,String>params = new HashMap<>();
        final UserExtendInformation info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        params.put("orgGuid",info.getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                inspectionList = (ObjHaz)gson.fromJson(result,ObjHaz.class);
                if(inspectionList != null && inspectionList.dataSource != null && inspectionList.dataSource.size() > 0){
                    getHazDetail();
                }else {
                    closeDataDialog();
                    ToastUtils.show("没有风险源信息");
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    private void getHazDetail(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/obj/selectHazInfoDetails/";
        HashMap<String,String>params = new HashMap<>();
        for(final ObjHaz item: inspectionList.dataSource){
            params.put("guid",item.getGuid());
            params.put("hazGuid",item.getGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                   BisHazRegDetail bisHazRegDetail = gson.fromJson(result,BisHazRegDetail.class);
                    if(bisHazRegDetail == null || bisHazRegDetail.dataSource == null || bisHazRegDetail.dataSource.size() == 0){

                    }else {
                        item.setHazName(bisHazRegDetail.dataSource.get(0).getHazName());
                        item.setEngineName(bisHazRegDetail.dataSource.get(0).getEngName());
                        item.setHazStat(bisHazRegDetail.dataSource.get(0).getHazStat());
                    }
                    if(iSucessCount +iFailedCount == inspectionList.dataSource.size()){
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount +iFailedCount == inspectionList.dataSource.size()){
                        refreshUI();
                    }
                }
            });
        }

    }
    private void refreshUI(){
        closeDataDialog();
        if(inspectionList != null){
            for(ObjHaz item: inspectionList.dataSource){
                item.setHiddGradName(GlobleConstants.hazGradeMap.get(item.getHazCode()));
                if("3".equals(item.getHazStat()) || "4".equals(item.getHazStat())){
                    continue;
                }else {
                    datas.add(item);
                }
            }
            listAdapter.setData(datas);
            listAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void initData() {
        iFailedCount = 0;
        iSucessCount = 0;
        datas.clear();
        showDataLoadingDialog();
        getHazsList();
    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Haz_Ent);
        setInitActionBar(true);
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        ll_search.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new DangerousListAdapter(this,
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
            int type = Integer.valueOf(dangerousInformation.getHiddGrad());
            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            RelativeLayout ll_report_after = (RelativeLayout)(holder.getView(R.id.ll_report_after));
            ll_report_after.setVisibility(View.VISIBLE);
            (holder.getView(R.id.tv_time)).setVisibility(View.GONE);
            (holder.getView(R.id.ll_content)).setVisibility(View.GONE);
            Button btn = (holder.getView(R.id.btn_text));
            btn.setText("巡查");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",dangerousInformation);
                    intentActivity(HazListForEntActivity.this,HazNewDangerousForEntActivity.class,false,bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_type))).setText(dangerousInformation.getHiddGradName());
            switch (type) {
                    case GlobleConstants.HAZ_HTYPE_NORMAL:{
                        ((TextView) (holder.getView(R.id.tv_type))).setText(
                                R.string.dangerous_type_normal);
                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                }
                break;
                case GlobleConstants.HAZ_TYPE_BIGER: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_big_shape));
                }
                break;
                default:
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_normal);
                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                    break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    dangerousInformation.getHazName());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    dangerousInformation.getEngineName());
        }
    }
}
