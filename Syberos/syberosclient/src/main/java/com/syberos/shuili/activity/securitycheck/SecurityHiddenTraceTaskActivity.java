package com.syberos.shuili.activity.securitycheck;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jidan on 18-4-6.
 * 该文件暂不使用
 */

public class SecurityHiddenTraceTaskActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {
    private final String TAG = SecurityHiddenTraceTaskActivity.class.getSimpleName();
    private final String Title = "隐患核实";
    @BindView(R.id.recyclerView_investigation)
    RecyclerView recyclerView;
    SecurityHiddenTraceTaskActivity.InvestigationAdapter investigationAdapter ;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_task_layout;
    }

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {
        List<HiddenInvestigationTaskInfo> investigationInfos = new ArrayList<>();

        investigationAdapter.setData(investigationInfos);
        investigationAdapter.notifyDataSetChanged();

    }

    @Override
    public void initView() {
        showTitle("隐患核实");
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        investigationAdapter = new SecurityHiddenTraceTaskActivity.InvestigationAdapter(this,R.layout.activity_investigation_task_item);
        recyclerView.setAdapter(investigationAdapter);
        investigationAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {
// TODO: 18-4-6 根据状态进入不同的activity 
        intentActivity(this,SecurityHiddenTracePushActivity.class,false,true);

    }

    private class InvestigationAdapter extends CommonAdapter<HiddenInvestigationTaskInfo> {
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
            ( (TextView)(holder.getView(R.id.tv_time))).setText(investigationInfo.getRequCompDate());
            ( (TextView)(holder.getView(R.id.tv_name))).setText(investigationInfo.getEngName());
            ( (TextView)(holder.getView(R.id.tv_content))).setText(investigationInfo.getHiddClas());


        }
    }
}
