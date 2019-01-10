package com.syberos.shuili.activity.reports;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.report.ObjSins;
import com.syberos.shuili.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 行政版安全检查上报和上报结果查询
 */
public class CheckDetailActivity extends TranslucentActivity {

    private ObjSins objSins = null;

    @BindView(R.id.recyclerView_query_accident)
    RecyclerView recyclerView;
    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;
    @BindView(R.id.iv_action_right)
    LinearLayout iv_action_right;

    private ListAdapter listAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprises_hidden_danger_report;
    }

    @Override
    public void initListener() {

    }
    @Override
    public void initData() {
        refreshUI();
    }
    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_enterprises_hidden_danger_report_item);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.HORIZONTAL));
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        objSins = (ObjSins) bundle.getSerializable("objSins");
        if(objSins == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
        tv_action_bar_title.setText("检查报表");
        iv_action_right.setVisibility(View.GONE);

    }
    private void refreshUI(){
        ArrayList<CheckDetailBean>checkDetailBeans = new ArrayList<>();
        if(objSins.getSwen().contains(",")){
            String [] values = objSins.getSwen().split(",");
            int size = values.length;
            for(int i = 0 ; i<size;i++){
                CheckDetailBean checkDetailBean = new CheckDetailBean();
                checkDetailBean.guid = objSins.getGuid();
                checkDetailBean.unitName = values[i];
                checkDetailBean.repStatus = objSins.getIfSendDown();
                checkDetailBeans.add(checkDetailBean);
            }
        }
        else {
            CheckDetailBean checkDetailBean = new CheckDetailBean();
            checkDetailBean.guid = objSins.getGuid();
            checkDetailBean.unitName = objSins.getSwen();
            checkDetailBean.repStatus = objSins.getIfSendDown();
            checkDetailBeans.add(checkDetailBean);
        }
        listAdapter.setData(checkDetailBeans);
        listAdapter.notifyDataSetChanged();

    }

    private class  CheckDetailBean implements Serializable{
       public String guid;
       public String unitName;
       public String repStatus;

        public String getGuid() {
            return guid == null ? "" : guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getUnitName() {
            return unitName == null ? "" : unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getRepStatus() {
            return repStatus == null ? "" : repStatus;
        }

        public void setRepStatus(String repStatus) {
            this.repStatus = repStatus;
        }
    }
    private class ListAdapter extends CommonAdapter<CheckDetailBean> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final CheckDetailBean checkDetailBean) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(checkDetailBean.unitName);
            TextView tv_refunded =  holder.getView(R.id.tv_refunded);
            TextView tv_report =  holder.getView(R.id.tv_report);
            TextView tv_recall =  holder.getView(R.id.tv_recall);
            tv_recall.setVisibility(View.GONE);
            tv_refunded.setVisibility(View.GONE);
            // 0 未上报 1 已上报
            final int linkStatus = Integer.valueOf(checkDetailBean.repStatus);
            // 上报和撤回功能
            switch (linkStatus) {
                case 0:
                    tv_report.setVisibility(View.VISIBLE);
                    tv_report.setText("上报");
                    break;
                case 1:
                    tv_report.setVisibility(View.GONE);
                    tv_recall.setVisibility(View.VISIBLE);
                    tv_refunded.setVisibility(View.GONE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color, null));
                    } else {
                        tv_refunded.setTextColor(getResources().getColor(R.color.login_page_link_text_color));
                    }
                    break;
            }
            tv_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    report(1,objSins);
                }
            });
            tv_recall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    report(2,objSins);
                }
            });
        }
    }

    private void report(int type,ObjSins objSins){
        // 0 上报 1 申请撤回
    }

}
