package com.syberos.shuili.activity.securitycheck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.an_quan_jian_cha.EECI_HiddenItemInfo;
import com.syberos.shuili.entity.an_quan_jian_cha.EnterprisesOnSiteCheckInfo;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.entity.securitycheck.ObjSins;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 企事业版 安全检查 现场检查列表
 * 8.2.1.8	安全检查对象表（OBJ_SINS） 获取下发到本单位的部署信息
 * 根据部署信息 8.2.2.9	安全检查方案（BIS_SINS_SCHE）
 */
public class EnterprisesOnSiteCheckListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    public static final String SEND_BUNDLE_KEY = "EnterprisesOnSiteCheckInfo";

    private List<EnterprisesOnSiteCheckInfo> infoList = null;
    private ListAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ObjSins objSins = null;
    BisSinsSche bisSinsSche = null;
    private HashMap<String,BisSinsSche> map = new HashMap<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_on_site_check_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getObjSins();

    }

    @Override
    public void initView() {
        showTitle(getResources().getString(R.string.module_child_anquan_xianchangjiancha));
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(this,
                R.layout.activity_enterprises_on_site_check_list_item);
        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjSins item = objSins.dataSource.get(position);
        BisSinsSche bisSinsSche = map.get(item.getGuid());
        bundle.putSerializable("bisSinsSche", item);
        intentActivity((Activity) mContext, EnterprisesOnSiteCheckDetailActivity.class,
                false, bundle);
    }

    /**
     * 获取单位的部署通知信息
     */
    private void getObjSins(){
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/obj/sis/objSinss/";
        HashMap<String,String> params = new HashMap<>();
       // params.put("notIssuWiun", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("notIssuWiun", "35B00CA81E084CFBAEB22E928BDD2B01");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objSins = gson.fromJson(result,ObjSins.class);
                if(objSins != null && objSins.dataSource != null){
                    getPlanInfo();

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
    private void  getPlanInfo(){
        // 外键 检查部署guid
        final ArrayList<ObjSins> infos  = (ArrayList<ObjSins>) objSins.dataSource;
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/sins/sche/bisSinsSches/";
        HashMap<String,String> params = new HashMap<>();
        int size =infos.size();
        for(int i = 0; i < size ;i ++){
            final ObjSins item = infos.get(i);
            params.put("sinsGuid",item.getGuid());
            final int finalI = i;
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisSinsSche = gson.fromJson(result,BisSinsSche.class);
                    if(bisSinsSche != null){
                        map.put(item.getGuid(),bisSinsSche);
                    }
                    if(finalI == infos.size()){
                        closeDataDialog();
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());
                    if(finalI == infos.size() -1){
                        closeDataDialog();
                        refreshUI();
                    }
                }

            });
        }
    }
    private void refreshUI(){
        adapter.setData(objSins.dataSource);
        adapter.notifyDataSetChanged();
    }
    private class ListAdapter extends CommonAdapter<ObjSins> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjSins information) {

            final BisSinsSche bisSinsSche = map.get(information.getGuid());
            ((TextView) (holder.getView(R.id.tv_time))).setText(String.format("%s-%s",
                    bisSinsSche.getScheStartTime(), bisSinsSche.getScheCompTime()));
            ((TextView) (holder.getView(R.id.tv_content))).setText(bisSinsSche.getScheCont());

            TextView tv_check = (TextView) (holder.getView(R.id.tv_check));

            tv_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 开始检查
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("bisSche",bisSinsSche);
                    intentActivity((Activity) mContext, SecurityCheckMapTrailsActivity.class,
                            false, true);
                }
            });
        }
    }
}
