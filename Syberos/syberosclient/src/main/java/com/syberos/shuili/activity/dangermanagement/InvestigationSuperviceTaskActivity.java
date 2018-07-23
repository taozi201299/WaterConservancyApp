package com.syberos.shuili.activity.dangermanagement;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.syberos.shuili.entity.UserExtendInfo;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-23.
 * todo 待修改 需要和7个子系统确定 行政版
 *
 */

public class InvestigationSuperviceTaskActivity extends BaseActivity implements CommonAdapter.OnItemClickListener ,PullRecyclerView.OnPullRefreshListener{
    private final String TAG = InvestigationSuperviceTaskActivity.class.getSimpleName();
    private final String Title = "隐患督办";
    @BindView(R.id.recyclerView_investigation)
    PullRecyclerView recyclerView;
    InvestigationSuperviceTaskActivity.InvestigationAdapter investigationAdapter ;
    HiddenInvestigationTaskInfo hiddenInvestigationTaskInfo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_task_layout;
    }

    @Override
    public void initListener() {
        recyclerView.setHasMore(false);
        recyclerView.setOnPullRefreshListener(this);
    }
    private void closeLoadingDialog(){
        closeDataDialog();
        recyclerView.refreshOrLoadComplete();
    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getTaskList();

    }
    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        investigationAdapter = new InvestigationSuperviceTaskActivity.InvestigationAdapter(this,R.layout.activity_investigation_task_item);
        recyclerView.setAdapter(investigationAdapter);
        investigationAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",hiddenInvestigationTaskInfo.dataSource.get(position));
        intentActivity(this,InvestigationSuperviceDetailActivity.class,false,bundle);
    }

    @Override
    public void onRefresh() {
        getTaskList();
    }

    @Override
    public void onLoadMore() {

    }

    private void getTaskList(){
        String url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/selectObjHiddTodoList/";
        HashMap<String,String> params = new HashMap<>();
        UserExtendInfo info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        //params.put("orgGuid",info.getOrgId());
        params.put("orgGuid","B28351744A0E4587AAB7E26CD2C5DE0E");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenInvestigationTaskInfo = gson.fromJson(result,HiddenInvestigationTaskInfo.class);
                if(hiddenInvestigationTaskInfo.totalCount.equals("0")){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                    // TODO: 2018/4/10 没有获取到相关数据
                }else {
                    investigationAdapter.setData(hiddenInvestigationTaskInfo.dataSource);
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
    private class InvestigationAdapter extends CommonAdapter<HiddenInvestigationTaskInfo> {


        public InvestigationAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, HiddenInvestigationTaskInfo investigationInfo) {
            String type = investigationInfo.getHiddGrad();
            LinearLayout ll_type = null;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            Button btnSupervice = (Button)holder.getView(R.id.btn_supervice);
            RelativeLayout rl_supervice = (RelativeLayout)holder.getView(R.id.ll_supervise);
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
            ( (TextView)(holder.getView(R.id.tv_content))).setText("待补充");
            rl_supervice.setVisibility(View.VISIBLE);
            btnSupervice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2018/4/12 督办
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",hiddenInvestigationTaskInfo);
                    intentActivity(InvestigationSuperviceTaskActivity.this,
                            InvestigationSuperviceFormActivity.class,false,bundle);
                }
            });
            if(investigationInfo.getIsList().equals("1")){
                btnSupervice.setText("已督办");
                btnSupervice.setEnabled(false);
                rl_supervice.setBackgroundColor(getResources().getColor(R.color.gray_bb));
            }else {
                btnSupervice.setText("督办");
            }



        }
    }
}
