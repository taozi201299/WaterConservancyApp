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
import com.syberos.shuili.view.PullRecyclerView;

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
        implements CommonAdapter.OnItemClickListener,PullRecyclerView.OnPullRefreshListener {

    public static final String SEND_BUNDLE_KEY = "EnterprisesOnSiteCheckInfo";

    private List<EnterprisesOnSiteCheckInfo> infoList = null;
    private ListAdapter adapter;

    @BindView(R.id.pullRecyclerView)
    PullRecyclerView pullRecyclerView;
    ObjSins objSins = null;
    BisSinsSche bisSinsSche = null;
    private HashMap<String,BisSinsSche> map = new HashMap<>();

    int iSucessCount = 0;
    int iFailedCount = 0;


    @Override
    protected void onPause() {
        super.onPause();
        clear();
    }

    private void clear(){
        iFailedCount = 0;
        iSucessCount = 0;
        map.clear();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_on_site_check_list;
    }

    @Override
    public void initListener() {
        pullRecyclerView.setOnPullRefreshListener(this);
        pullRecyclerView.setHasMore(false);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle(getResources().getString(R.string.module_child_anquan_xianchangjiancha));
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        pullRecyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(this,
                R.layout.activity_enterprises_on_site_check_list_item);
        adapter.setOnItemClickListener(this);

        pullRecyclerView.setAdapter(adapter);
        showDataLoadingDialog();
        getObjSins();

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        ObjSins item = objSins.dataSource.get(position);
        BisSinsSche bisSinsSche = map.get(item.getGuid()).dataSource.get(0);
        bundle.putSerializable("objSins",item);
        bundle.putSerializable("bisSinsSche", bisSinsSche);
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
                    pullRecyclerView.refreshOrLoadComplete();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                pullRecyclerView.refreshOrLoadComplete();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void  getPlanInfo(){
        // 外键 检查部署guid
        final ArrayList<ObjSins> infos  = (ArrayList<ObjSins>) objSins.dataSource;
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/sins/sche/bisSinsSches/";
        HashMap<String,String> params = new HashMap<>();
        final int size =infos.size();
        for(int i = 0; i < size ; i ++){
            if(iFailedCount != 0) break;
            final ObjSins item = infos.get(i);
            params.put("sinsGuid",item.getGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    bisSinsSche = gson.fromJson(result,BisSinsSche.class);
                    if(bisSinsSche != null){
                        map.put(item.getGuid(),bisSinsSche);
                    }
                    ++iSucessCount;
                    if(iSucessCount == size){
                        closeDataDialog();
                        pullRecyclerView.refreshOrLoadComplete();
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    ++iFailedCount  ;
                    closeDataDialog();
                    pullRecyclerView.refreshOrLoadComplete();
                    ToastUtils.show(errorInfo.getMessage());
                }

            });

        }
    }
    private void refreshUI(){
        adapter.setData(objSins.dataSource);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        clear();
        getObjSins();
    }

    @Override
    public void onLoadMore() {

    }

    private class ListAdapter extends CommonAdapter<ObjSins> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjSins information) {
            final BisSinsSche bisSinsSche ;
            ArrayList<BisSinsSche>data = (ArrayList<BisSinsSche>) map.get(information.getGuid()).dataSource;
            if(data != null && data.size() > 0){
                bisSinsSche = data.get(0);
            }else {
                return;
            }
            ((TextView) (holder.getView(R.id.tv_time))).setText(String.format("%s-%s",
                    bisSinsSche.getScheStartTime(), bisSinsSche.getScheCompTime()));
            ((TextView) (holder.getView(R.id.tv_content))).setText(bisSinsSche.getScheCont());

            TextView tv_check = (TextView) (holder.getView(R.id.tv_check));

            tv_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 开始检查
                    Bundle bundle =new Bundle();
                    bundle.putSerializable("objSins",information);
                    bundle.putSerializable("bisSche",bisSinsSche);
                    intentActivity((Activity) mContext, SecurityCheckMapTrailsActivity.class,
                            false, bundle);
                }
            });
        }
    }
}
