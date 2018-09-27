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
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.dangersource.BisHazMajRegWrit;
import com.syberos.shuili.entity.dangersource.BisHazReg;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.HAZ_HTYPE_NORMAL;
import static com.syberos.shuili.config.GlobleConstants.HAZ_TYPE_BIGER;
import static com.syberos.shuili.config.GlobleConstants.hazGradeMap;
import static com.syberos.shuili.config.GlobleConstants.strIP;

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
    BisHazReg bisHazReg = null;
    BisHazMajRegWrit bisHazMajRegWrit = null;
    ArrayList<String>regOrgGuid = new ArrayList<>();
    ArrayList<BisHazReg>datas = new ArrayList<>();
    private int iSucessCount = 0;
    private int iFailedCount = 0;

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        BisHazReg information = datas.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this, HazDetailActivity.class, false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_list;
    }

    @Override
    public void initListener() {

    }
    public void getAllRegOrgGUID(){
        String url = strIP +"/sjjk/v1/bis/haz/maj/bisHazMajRegWrits/";
        HashMap<String,String>params = new HashMap<>();
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                bisHazMajRegWrit = gson.fromJson(result,BisHazMajRegWrit.class);
                if(bisHazMajRegWrit == null || bisHazMajRegWrit.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                for(BisHazMajRegWrit bisHazMajRegWrit :bisHazMajRegWrit.dataSource){
                    if(!regOrgGuid.contains(bisHazMajRegWrit.getRegOrgGuid())){
                        regOrgGuid.add(bisHazMajRegWrit.getRegOrgGuid());
                    }
                }
                getHazList();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    public void getHazList(){
        final int size = regOrgGuid.size();
        for(int i = 0; i <size;i++) {
            String url = strIP + "/sjjk/v1/bis/obj/haz/selectObjHazWithBisHazMajRegWrit/";
            HashMap<String, String> params = new HashMap<>();
            params.put("orgGuid",regOrgGuid.get(i));
            params.put("hazStat", "2");
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++;
                    Gson gson = new Gson();
                    bisHazReg = gson.fromJson(result, BisHazReg.class);
                    if (bisHazReg == null || bisHazReg.dataSource == null) {
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    } else if (bisHazReg.dataSource.size() == 0) {
                    } else {
                        for(BisHazReg item : bisHazReg.dataSource){
                            datas.add(item);
                        }
                    }
                    if(iSucessCount + iFailedCount == size){
                        refreshUI();
                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount + iFailedCount == size){
                        refreshUI();
                    }
                }
            });
        }
    }
    private void refreshUI(){
        closeDataDialog();
        listAdapter.setData(datas);
        listAdapter.notifyDataSetChanged();
    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        iSucessCount = 0;
        iFailedCount =0;
        datas.clear();
        regOrgGuid.clear();
        getAllRegOrgGUID();
    }
    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new DangerousListAdapter(this,
                R.layout.activity_bis_haz_reg_layout);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);


    }

    private class DangerousListAdapter extends CommonAdapter<BisHazReg> {
        public DangerousListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, final BisHazReg dangerousInformation) {
            int type =  HAZ_HTYPE_NORMAL;
            LinearLayout ll_type = (LinearLayout) (holder.getView(R.id.ll_type));
            RelativeLayout ll_report_after = (RelativeLayout)(holder.getView(R.id.ll_report_after));
            ll_report_after.setVisibility(View.VISIBLE);
            Button btn = (Button)(holder.getView(R.id.btn_text));
            btn.setText("审核");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY, dangerousInformation);
                    bundle.putInt("title",1);
                    intentActivity(WriteOffVerificationListActivity.this,RecordReviewConfirmActivity.class,false,bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_type))).setText(
                    hazGradeMap.get(String.valueOf(type)));
            switch (type) {
                case HAZ_HTYPE_NORMAL:{
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_normal);
                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_normal_shape));
                }
                break;
                case HAZ_TYPE_BIGER: {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(
                            R.string.dangerous_type_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_dangerous_type_big_shape));
                }
                break;
            }
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    dangerousInformation.hazName);
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    dangerousInformation.engName);
            ((TextView)(holder.getView(R.id.tv_content))).setText(dangerousInformation.wiunName);
            ((TextView)holder.getView(R.id.tv_time)).setVisibility(View.GONE);
        }
    }
}
