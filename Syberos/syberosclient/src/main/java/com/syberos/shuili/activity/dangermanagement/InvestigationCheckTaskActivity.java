package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.icu.text.UnicodeSetSpanner;
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
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.entity.UserExtendInfo;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by jidan on 18-3-20.
 */

public class InvestigationCheckTaskActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {

    private final String TAG = InvestigationCheckTaskActivity.class.getSimpleName();
    private final String Title = "隐患核实";
    @BindView(R.id.recyclerView_investigation)
    RecyclerView recyclerView;
    InvestigationAdapter investigationAdapter ;
    HiddenInvestigationTaskInfo investigationInfo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_task_layout;
    }

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {
       showDataLoadingDialog();
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/selectHiddWithBisByOrgGuid/";
        HashMap<String,String>params = new HashMap<>();
        UserExtendInfo info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        //params.put("orgGuid",info.getOrgId());
        params.put("orgGuid","B28351744A0E4587AAB7E26CD2C5DE0E");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
              closeDataDialog();
                Gson gson = new Gson();
                investigationInfo = gson.fromJson(result,HiddenInvestigationTaskInfo.class);
                if(investigationInfo.totalCount.equals("0")){
                    // TODO: 2018/4/10 没有获取到相关数据
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                }else {
                    investigationAdapter.setData(investigationInfo.dataSource);
                    investigationAdapter.notifyDataSetChanged();
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
        showTitle("隐患核实");
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        investigationAdapter = new InvestigationAdapter(this,R.layout.activity_investigation_task_item);
        recyclerView.setAdapter(investigationAdapter);
        investigationAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("data",investigationInfo.dataSource.get(position));
        intentActivity(this,InvestigationCheckDetailActivity.class,false,bundle);

    }

    private class InvestigationAdapter extends CommonAdapter<HiddenInvestigationTaskInfo>{
        public InvestigationAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, HiddenInvestigationTaskInfo investigationInfo) {
            String type = investigationInfo.getHiddGrad();
            LinearLayout ll_type = null;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            ShapeDrawable bgShape = null;
            switch (type){
                case "0": {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(R.string.normal);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                }
                    break;
                case "1": {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(R.string.danger);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                }
                    break;

            }
            ( (TextView)(holder.getView(R.id.tv_title))).setText(investigationInfo.getHiddName());
            ( (TextView)(holder.getView(R.id.tv_time))).setText(investigationInfo.getHiddStat());
            ( (TextView)(holder.getView(R.id.tv_name))).setText(investigationInfo.getEngName());
            ( (TextView)(holder.getView(R.id.tv_content))).setText(investigationInfo.getHiddClas());


        }
    }

}
