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
import com.shuili.callback.RequestCallback;;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.dangersource.BisHazReg;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.HAZ_HTYPE_NORMAL;
import static com.syberos.shuili.config.GlobleConstants.HAZ_TYPE_BIGER;
import static com.syberos.shuili.config.GlobleConstants.hazGradeMap;

/**
 * 行政版危险源备案审核 重大危险源备案核销表
 * 需要修改
 */
public class RecordReviewListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {
    private final String TAG = RecordReviewListActivity.class.getSimpleName();

    private final String Title = "备案审核";

    public static final String SEND_BUNDLE_KEY = "DangerousInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;
    DangerousListAdapter listAdapter;
    BisHazReg bisHazReg = null;
    private ArrayList<OrgInfo>orgInfos = new ArrayList<>();

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        BisHazReg information = bisHazReg.dataSource.get(position);
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
    public void getHazList(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/haz/selectObjHazWithBisHazMajRegWrit/";
        HashMap<String,String>params = new HashMap<>();
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("hazStat","1");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson  = new Gson();
                bisHazReg = gson.fromJson(result,BisHazReg.class);
                if(bisHazReg == null || bisHazReg.dataSource == null){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else if(bisHazReg.dataSource.size() == 0){
                    ToastUtils.show("没有相关数据");
                }else {
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
    private void refreshUI(){
        if(bisHazReg != null){
            listAdapter.setData(bisHazReg.dataSource);
            listAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void initData() {
        showDataLoadingDialog();
        getHazList();
    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new RecordReviewListActivity.DangerousListAdapter(this,
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
            int type = DangerousInformation.TYPE_BIGER;
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
                    bundle.putInt("title",0);
                    intentActivity(RecordReviewListActivity.this,RecordReviewConfirmActivity.class,false,bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_type))).setText(
                    hazGradeMap.get(String.valueOf(type)));
            switch (type) {
                case HAZ_HTYPE_NORMAL:{
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
