package com.syberos.shuili.activity.securitycheck;

import android.support.v7.app.AppCompatActivity;
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
import com.syberos.shuili.entity.an_quan_jian_cha.EnterprisesOnSiteCheckInfo;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.securitycheck.BisSinsSche;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class EnterprisesOnSiteCheckDetailActivity extends BaseActivity {

    public static final String DEFAULT_BUNDLE_NAME = "bundle";

    BisSinsSche info;

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

    @BindView(R.id.ll_hiddlen_object_title)
    LinearLayout ll_hiddlen_object_title;

    ObjHidden objHidden =null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_on_site_check_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle("现场检查");
        setActionBarRightVisible(View.INVISIBLE);

        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        info = (BisSinsSche) bundle.getSerializable("bisSinsSche");
        if (null != info) {
            tv_start_time.setText(info.getScheStartTime());
            tv_end_time.setText(info.getScheCompTime());
            tv_check_content.setText(info.getScheCont());

            // ll_check_road_container init view

            // ll_hiddlen_object_title init view
        }
    }
    private void getHiddenInfo(){
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHidds/";
        HashMap<String ,String > params = new HashMap<>();
        //  params.put("orgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgGuid","537AD1AB8E7447AAA249AB22A5344955");
        //   params.put("seCheckItemGuid",bisSeChit.getGuid());
        params.put("SinsScheGuid","a1");
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
                addHiddenItems(objHidden.dataSource);
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void getRotes(){

    }
    private void addHiddenItems(List<ObjHidden> hiddenItemInfos) {
        if (hiddenItemInfos.size() <= 0) {
            return;
        }
        for (ObjHidden hiddenItemInfo : hiddenItemInfos) {
            addHiddenItem(hiddenItemInfo);
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

        LinearLayout ll_type = (LinearLayout) (view.findViewById(R.id.ll_type));
        switch (level) {
            case 0: {
                ll_type.setBackground(getResources().getDrawable(
                        R.drawable.bg_enterprises_element_check_level_normal));
            }
            break;
            case 1: {
                ll_type.setBackground(getResources().getDrawable(
                        R.drawable.bg_enterprises_element_check_level_large));
                break;
            }
        }

        ll_hidden_object_container.addView(view);
    }

}
