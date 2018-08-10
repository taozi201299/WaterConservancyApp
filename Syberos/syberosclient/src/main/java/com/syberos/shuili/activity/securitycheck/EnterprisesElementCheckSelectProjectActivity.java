package com.syberos.shuili.activity.securitycheck;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationEngineTendForEntActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.App;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.hidden.HiddenEngineState;
import com.syberos.shuili.entity.basicbusiness.ObjProject;
import com.syberos.shuili.entity.basicbusiness.RelEngOrg;
import com.syberos.shuili.entity.securitycheck.BisSeChit;
import com.syberos.shuili.entity.securitycheck.ObjSe;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.activity.securitycheck.EnterprisesOnSiteCheckDetailActivity.DEFAULT_BUNDLE_NAME;
import static com.syberos.shuili.config.GlobleConstants.strIP;

/**
 * Created by jidan on 18-3-27.
 */
public class EnterprisesElementCheckSelectProjectActivity extends BaseActivity implements CommonAdapter.OnItemClickListener,View.OnClickListener {

    private final String TAG = EnterprisesElementCheckSelectProjectActivity.class.getSimpleName();
    private final String Title = "隐患排查";
    @BindView(R.id.recyclerView_investigation)
    RecyclerView recyclerView;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    InvestigationAdapter investigationAdapter ;
    private ArrayList<ObjectEngine>built = new ArrayList<>(); // 在建
    private ArrayList<ObjectEngine>building = new ArrayList<>(); // 已建
    ArrayList<ObjectEngine>objectEngines = new ArrayList<>();
    ObjectEngine objectEngine = null;
    ObjectTend objectTend = null;
    DicInfo objectEngineType;
    ObjProject objProject = null;
    RelEngOrg relEngOrg = null;
    /**
     *  安全元素对象信息
     */
    private ObjSe information = null;
    /**
     * 安全元素下的检查项信息
     */
    private BisSeChit bisSeChit = null;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_enterprise_task_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);


    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        information = (ObjSe) bundle.getSerializable("element");
        bisSeChit  = (BisSeChit) bundle.getSerializable("checkItem");
        if(information == null || bisSeChit == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            return;
        }
        // 根据单位GUID 获取工程
        showDataLoadingDialog();
        getObjectEngineTypeDic();

    }
    private void getEngineIDSByOrgGUID(){
        String orgGuid = SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId();
        String url = strIP +"/sjjk/v1/jck/obj/selectOrgEngInfoByOrgGuid/";
        HashMap<String,String>params = new HashMap<>();
        // params.put("orgGuid",orgGuid);
        params.put("orgGuid","B2D68C74986E40C2B91DC2E73C39BD29");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objectEngine = (ObjectEngine)gson.fromJson(result,ObjectEngine.class);
                if(objectEngine != null &&objectEngine.dataSource != null){
                    parseEngines();
                }else {
                    closeDataDialog();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    /**
     * 根据工程GUID 获取在建 已建状态
     */
    private void  parseEngines(){
        String url = "";
        for(final  ObjectEngine item : objectEngine.dataSource){
            if(item.getEngType().equals("1")){
                url = strIP  + "/sjjk/v1/jck/att/eng/attEngRess/" ;
            }else  if(item.getEngType().equals("2")){
                url =  strIP  + "/sjjk/v1/jck/att/eng/attEngWagas/" ;
            }
            else if(item.getEngType().equals("3")){
                url =  strIP  + "/sjjk/v1/jck/att/eng/attEngPusts/" ;

            }else if(item.getEngType().equals("4")){
                url =  strIP  + "/sjjk/v1/jck/att/eng/attEngHysts/" ;
            }
            else if(item.getEngType().equals("5")){
                url =  strIP  + "sjjk/v1/jck/att/eng/attEngDikes/" ;
            }
            else if(item.getEngType().equals("6")){
                url =  strIP  + "/sjjk/v1/jck/att/attEngIrrs/" ;
            }else if(item.getEngType().equals("7")){
                url =  strIP  + "/sjjk/v1/jck/att/eng/attEngWadis/" ;
            }else if(item.getEngType().equals("8")){
                url = strIP  + "/sjjk/v1/jck/att/eng/attEngSds/" ;
            }
            else if(item.getEngType().equals("9")){
                url =  strIP  + "/sjjk/v1/jck/att/eng/attEngCwss/" ;
            }else if(item.getEngType().equals("10")){
                url =  strIP  + "/sjjk/v1/jck/att/eng/attEngOthes/" ;
            }
            getObjStatus(item,url);
        }

    }
    /**
     * 工程对象表
     */
    private void getObjStatus(final ObjectEngine item,String url){
        HashMap<String,String> params = new HashMap<>();
        params.put("engGuid",item.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                HiddenEngineState engineState = gson.fromJson(result,HiddenEngineState.class);
                if(engineState != null && engineState.dataSource != null && engineState.dataSource.size() > 0){
                    item.setEngStat(engineState.dataSource.get(0).getEngStat());
                }
                if(objectEngine.dataSource.indexOf(item) == objectEngine.dataSource.size() - 1){
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
        ObjectEngine item = objectEngine.dataSource.get(position);
        if(item.getEngStat().equals("1")) {
            getTendInfo(item);
        }else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",item);
            bundle.putSerializable("tend",null);
            bundle.putSerializable("element",information);
            bundle.putSerializable("checkItem",bisSeChit);
            intentActivity(EnterprisesElementCheckSelectProjectActivity.this, EnterprisesElementCheckCreateHiddenActivity.class, false, bundle);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                intentActivity(this,EnterprisesElementCheckCreateHiddenActivity.class,false,true);
                break;
        }
    }
    private void refreshUI(){
        investigationAdapter.setData(objectEngine.dataSource);
        investigationAdapter.notifyDataSetChanged();
    }

    /**
     * 根据工程GUID 获取工程名称
     */
    private void getEngineInfo(){
        String url = strIP +"/sjjk/v1/jck/obj/objEngs/";
        HashMap<String,String>params = new HashMap<>();
        for(RelEngOrg item : relEngOrg.dataSource){
            params.put("guid",item.getEngGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    ObjectEngine objectEngine  = null;
                    objectEngine = gson.fromJson(result,ObjectEngine.class);
                    objectEngines.add(objectEngine.dataSource.get(0));
                    if(objectEngines.size() == relEngOrg.dataSource.size()){
                        parseEngines();
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
    private void getTendInfo(final ObjectEngine item){
        String url = strIP +"/sjjk/v1/jck/obj/objTends/";
        HashMap<String,String>params = new HashMap<>();
        params.put("engGuid",item.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objectTend = gson.fromJson(result,ObjectTend.class);
                ArrayList<ObjectTend> list = (ArrayList<ObjectTend>) objectTend.dataSource;
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",item);
                bundle.putSerializable("tend",(ArrayList )objectTend.dataSource);
                bundle.putSerializable("element",information);
                bundle.putSerializable("checkItem",bisSeChit);

                if(list.size() == 1) {
                    intentActivity(EnterprisesElementCheckSelectProjectActivity.this, EnterprisesElementCheckCreateHiddenActivity.class, false, bundle);
                }
                else {
                    // TODO: 2018/4/19 tend list
                    intentActivity(EnterprisesElementCheckSelectProjectActivity.this,InvestigationEngineTendForEntActivity.class,false,bundle);

                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getObjectEngineTypeDic(){
        String url  = strIP +"/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_ENG");
        params.put("attColCode","ENG_TYPE");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objectEngineType  = gson.fromJson(result,DicInfo.class);
                getEngineIDSByOrgGUID();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private String getEngineType(String type){
        String engineTypeName  = "未知";
        if(objectEngine == null) return engineTypeName;
        for(DicInfo item : objectEngineType.dataSource){
            if(item.getDcItemCode().equals(type)){
                engineTypeName = item.getDcItemName();
                break;
            }
        }
        return engineTypeName;
    }
    private ArrayList<ObjectTend>getTendInfoByEngineId(String engineId){
        ArrayList<ObjectTend> objectTends = new ArrayList<>();
        String orgId = SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId();
        if(objectTend != null){
            for(ObjectTend item :objectTend.dataSource){
                if(item.getEngGuid().equals(engineId) && item.getOrgGuid().equals(orgId)){
                    objectTends.add(item);
                }
            }
        }
        return  objectTends;

    }
    private class InvestigationAdapter extends CommonAdapter<ObjectEngine>{
        public InvestigationAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }
        @Override
        public void convert(ViewHolder holder, ObjectEngine investigationInfo) {
            String type = investigationInfo.getEngName();
            LinearLayout ll_type = null;
            TextView tv_time;
            LinearLayout ll_name;
            TextView tv_content_label;
            TextView tv_content;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            ll_type.setVisibility(View.GONE);
            tv_time = (TextView)(holder.getView(R.id.tv_time));
            tv_time.setVisibility(View.GONE);
            ll_name = (LinearLayout)(holder.getView(R.id.ll_name));
            ll_name.setVisibility(View.GONE);
            tv_content_label = (TextView)(holder.getView(R.id.tv_content_label));
            tv_content_label.setText("工程类型");
            tv_content = (TextView)(holder.getView(R.id.tv_content));
            ShapeDrawable bgShape = null;
            ( (TextView)(holder.getView(R.id.tv_title))).setText(investigationInfo.getEngName());
            tv_content.setText(getEngineType(investigationInfo.getEngType()));
        }
    }

}

