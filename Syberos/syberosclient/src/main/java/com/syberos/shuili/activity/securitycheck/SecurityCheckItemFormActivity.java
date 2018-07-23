package com.syberos.shuili.activity.securitycheck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.basicbusiness.AttOrgExt;
import com.syberos.shuili.entity.basicbusiness.RelEngOrg;
import com.syberos.shuili.entity.securitycheck.BisEutrOfflRec;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by jidan on 18-4-6.
 * 安全检查被检对象详情
 * 安全检查被检对象包含单位和工程 如何进行区分
 * todo 需要修改
 */

public class SecurityCheckItemFormActivity extends BaseActivity{
    @BindView(R.id.ll_object_container_0)
    LinearLayout ll_object_container_0;
    @BindView(R.id.ll_object_container_1)
    LinearLayout ll_object_container_1;
    @BindView(R.id.ll_hidden_object_container)
    LinearLayout ll_hidden_object_container;

    @BindView(R.id.tv_check_0)
    TextView tv_check_0;
    @BindView(R.id.tv_check_1)
    TextView tv_check_1;
    @BindView(R.id.tc_check_2)
    TextView tc_check_2;
    RelSinsGroupWiun relSinsGroupWiun = null;
    RelEngOrg relEngOrg = null;
    AttOrgExt attOrgExt = null;
    /**
     * 培训信息
     */
    BisEutrOfflRec bisEutrOfflRec = null;
    @Override
    public int getLayoutId() {
        return R.layout.activity_security_check_item_form_layout;
    }

    @Override
    public void initListener() {

    }



    @Override
    public void initData() {
        if(relSinsGroupWiun == null) {
            Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
            relSinsGroupWiun = (RelSinsGroupWiun) bundle.getSerializable("checkItem");
            if(relSinsGroupWiun == null){
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                return;
            }
        }
        showTitle("被检对象详情---" + relSinsGroupWiun.getEngName());
        getUnitIDByEngGuid();


    }

    @Override
    public void initView() {
        setActionBarRightVisible(View.INVISIBLE);
    }
    private void getUnitIDByEngGuid(){
        String url = "http://192.168.1.8:8080/sjjk/v1/jck/rel/relEngOrgs/";
        HashMap<String,String>params = new HashMap<>();
        params.put("engGuid",relSinsGroupWiun.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                relEngOrg = gson.fromJson(result,RelEngOrg.class);
                if(relEngOrg == null || relEngOrg.dataSource == null || relEngOrg.dataSource.size() == 0){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getUnitInfo();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getUnitInfo(){
       String url = "http://192.168.1.8:8080/sjjk/v1/att/org/ext/attOrgExts/";
       HashMap<String,String>params = new HashMap<>();
      // params.put("orgGuid",relEngOrg.dataSource.get(0).getOrgGuid());
        params.put("orgGuid","9A1223ACDF57405DB2C3D374AD1BAEEA");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                attOrgExt = gson.fromJson(result,AttOrgExt.class);
                if(attOrgExt == null || attOrgExt.dataSource == null || attOrgExt.dataSource.size() == 0) {
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getBisEutrOfflRecs();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getBisEutrOfflRecs(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/eutr/bisEutrOfflRecs/";
        HashMap<String,String>params = new HashMap<>();
       // params.put("orgGuid",attOrgExt.getOrgGuid());
        params.put("orgGuid","F83199FDD35E49FF9643A6C394DBBF45");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisEutrOfflRec = gson.fromJson(result,BisEutrOfflRec.class);
                if(bisEutrOfflRec == null || bisEutrOfflRec.dataSource == null ){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getObjCase();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getObjCase(){
        refreshUI();
    }
    private View getView(View view, int layoutID){
        return view.findViewById(layoutID);
    }
    private void refreshUI(){
        tv_check_0.setText(attOrgExt.dataSource.get(0).getWiunName());
        tv_check_1.setText(attOrgExt.dataSource.get(0).getLegRep());
        tc_check_2.setText(attOrgExt.dataSource.get(0).getStanGrad());
        TextView tv_expand_name;
        TextView tv_child_0;
        TextView tv_child_1;
        TextView tv_child_2;
        TextView tv_child_3;
        TextView tv_child_4;
        for(BisEutrOfflRec item:bisEutrOfflRec.dataSource){
            View expandView = LayoutInflater.from(mContext).inflate(R.layout.view_expandable_item, null);
            tv_expand_name = (TextView)getView(expandView,R.id.tv_expand_name);
            tv_child_0 = (TextView)getView(expandView,R.id.tv_child_0);
            tv_child_1 = (TextView)getView(expandView,R.id.tv_child_1);
            tv_child_2 = (TextView)getView(expandView,R.id.tv_child_2);
            tv_child_3 = (TextView)getView(expandView,R.id.tv_child_3);
            tv_child_4 = (TextView)getView(expandView,R.id.tv_child_4);
            tv_expand_name.setText(item.getEutrThem());
            tv_child_0.setText(item.getEutrThem());
            tv_child_1.setText(item.getEutrNum());
            tv_child_2.setText(item.getEutrType());
            tv_child_3.setText(item.getEutrStartDate() +"--"+ item.getEutrEndDate());
            tv_child_4.setText(item.getEutrCont());
            ll_object_container_0.addView(expandView);
        }
        for(int i = 0 ; i < 5 ; i++) {
            View expandView = LayoutInflater.from(mContext).inflate(R.layout.view_expandable_item, null);
            ll_object_container_1.addView(expandView);
        }
    }
}
