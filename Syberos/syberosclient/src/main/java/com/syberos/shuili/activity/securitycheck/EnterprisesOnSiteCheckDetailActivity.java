package com.syberos.shuili.activity.securitycheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepDetailActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.an_quan_jian_cha.EnterprisesOnSiteCheckInfo;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.common.CheckRoteItem;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.securitycheck.BisSinsRec;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.entity.securitycheck.ObjSins;
import com.syberos.shuili.entity.test.EngineDetailBean;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

import static com.syberos.shuili.config.GlobleConstants.strIP;

public class EnterprisesOnSiteCheckDetailActivity extends BaseActivity {

    public static final String DEFAULT_BUNDLE_NAME = "bundle";

    /**
     *检查方案对象
     */
    BisSinsSche info;
    /**
     * 安全检查对象
     */
    ObjSins objSins;
    /**
     * 安全检查记录对象
     */
    private BisSinsRec bisSinsRec;

    @BindView(R.id.tv_start_time)
    TextView tv_start_time;

    @BindView(R.id.tv_end_time)
    TextView tv_end_time;

    @BindView(R.id.tv_check_content)
    TextView tv_check_content;

    @BindView(R.id.ll_check_road_container)
    LinearLayout ll_check_road_container;
    @BindView(R.id.ll_hidden_object_container)
    LinearLayout ll_hidden_object_container;

    ObjHidden objHidden =null;
    CheckRoteItem checkRoteItem = null;

    int iSucessCount = 0;
    int iFailedCount = 0 ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_on_site_check_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getRotes();
        getHiddenInfo();

    }

    @Override
    public void initView() {
        setActionBarTitle("现场检查");
        setActionBarRightVisible(View.INVISIBLE);
        setInitActionBar(true);
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        bisSinsRec = (BisSinsRec) bundle.getSerializable("bisSinsRec");
        if (null != bisSinsRec) {
            tv_start_time.setText(bisSinsRec.startDate);
            tv_end_time.setText(bisSinsRec.endDate);
            tv_check_content.setText(bisSinsRec.inspCont);

            // ll_check_road_container init view

            // ll_hiddlen_object_title init view
        }
    }
    private void getHiddenInfo(){
        String url = strIP +"/sjjk/v1/bis/obj/objHidds/";
        HashMap<String ,String > params = new HashMap<>();
        params.put("orgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("inspRecGuid",bisSinsRec.guid);
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                objHidden = gson.fromJson(result,ObjHidden.class);
                if(objHidden == null || objHidden.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getEngName();
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    /**
     * 获取轨迹信息
     */
    private void getRotes(){
      String url =  GlobleConstants.mapServer + "/WEGIS-00-WEB_SERVICE/WSWebService";
      HashMap<String,String>params = new HashMap<>();
      params.put("guid",bisSinsRec.guid);
      params.put("type","bis");
      params.put("targetId","search.SearchObjGeoLogic");
      SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
          @Override
          public void onResponse(String result) {
              ll_check_road_container.removeAllViews();
              Gson gson = new Gson();
              checkRoteItem  = gson.fromJson(result, CheckRoteItem.class);
              if(checkRoteItem != null && checkRoteItem.getResultInfoList() != null && checkRoteItem.getResultInfoList().size() > 0){
                  ArrayList<CheckRoteItem.ResultInfoListBean> listBeans = (ArrayList<CheckRoteItem.ResultInfoListBean>) checkRoteItem.getResultInfoList();
                  int size = listBeans.size();
                  for(int i = 0; i < size; i++){
                      addRoteInfo(listBeans.get(i));
                  }
              }


          }

          @Override
          public void onFailure(ErrorInfo.ErrorCode errorInfo) {

          }
      });


    }
    private void addHiddenItems(List<ObjHidden> hiddenItemInfos) {
        if (hiddenItemInfos.size() <= 0) {
            closeDataDialog();
            return;
        }
        ll_hidden_object_container.removeAllViews();
        for (ObjHidden hiddenItemInfo : hiddenItemInfos) {
            addHiddenItem(hiddenItemInfo);
        }
    }
    private void getEngName(){
        if (objHidden.dataSource.size() <= 0) {
            closeDataDialog();
            return;
        }
        final int count = objHidden.dataSource.size();
        for (final ObjHidden hiddenItemInfo : objHidden.dataSource) {
            String url = GlobleConstants.strIP + "/sjjk/v1/jck/obj/objEngs/";
            HashMap<String,String>params = new HashMap<>();
            params.put("guid",hiddenItemInfo.getEngGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++ ;
                    Gson gson = new Gson();
                    ObjectEngine objectEngine = gson.fromJson(result,ObjectEngine.class);
                    if(objectEngine != null && objectEngine.dataSource.size() > 0){
                        hiddenItemInfo.setEngName(objectEngine.dataSource.get(0).getEngName());
                    }
                    if(iSucessCount + iFailedCount == count){
                        closeDataDialog();
                        addHiddenItems(objHidden.dataSource);
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                      iFailedCount ++;
                    if(iSucessCount + iFailedCount == count){
                        closeDataDialog();
                        addHiddenItems(objHidden.dataSource);
                    }
                }
            });

        }
    }
    private void addHiddenItem(final ObjHidden hiddenItemInfo) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.activity_enterprises_element_check_detail_hidden_item, null);

        ((TextView) (view.findViewById(R.id.tv_title))).setText(hiddenItemInfo.getHiddName());
        ((TextView) (view.findViewById(R.id.tv_time))).setText(hiddenItemInfo.getCollTime());
        ((TextView) (view.findViewById(R.id.tv_project))).setText(hiddenItemInfo.getEngName());

        String[] levelNames = getResources().getStringArray(
                R.array.enterprises_element_check_level);
        int  level = Integer.valueOf(hiddenItemInfo.getHiddGrad());
        TextView tv_type = (TextView) (view.findViewById(R.id.tv_type));
        tv_type.setText(levelNames[level]);

//        LinearLayout ll_type = (LinearLayout) (view.findViewById(R.id.ll_type));
        switch (level) {
            case 0: {
                tv_type.setBackground(getResources().getDrawable(
                        R.drawable.bg_enterprises_element_check_level_normal));
            }
            break;
            case 1: {
                tv_type.setBackground(getResources().getDrawable(
                        R.drawable.bg_enterprises_element_check_level_large));
                break;
            }
        }

        ll_hidden_object_container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",hiddenItemInfo);
                intentActivity(EnterprisesOnSiteCheckDetailActivity.this, InvestigationAccepDetailActivity.class,false,bundle);
            }
        });
    }

    private void addRoteInfo(final CheckRoteItem.ResultInfoListBean resultInfoListBean){
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.activity_engine_item_layout, null);
        view.setBackgroundResource(R.color.transparent);

        ((TextView) (view.findViewById(R.id.tv_name))).setText(resultInfoListBean.getChecktime());
        ll_check_road_container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",resultInfoListBean);
                intentActivity(EnterprisesOnSiteCheckDetailActivity.this, EnterpriseSecurityCheckRoteActivity.class,false,bundle);
            }
        });
    }

}
