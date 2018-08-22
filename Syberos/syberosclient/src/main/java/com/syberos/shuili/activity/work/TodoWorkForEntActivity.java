package com.syberos.shuili.activity.work;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepTaskForEntActivity;
import com.syberos.shuili.activity.dangermanagement.InvestigationAcceptFormActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.TodoWorkInfo;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.strCJIP;
import static com.syberos.shuili.config.GlobleConstants.strZJIP;

/**
 * Created by Administrator on 2018/4/4.
 * 企事业端 代办任务
 */

public class TodoWorkForEntActivity extends BaseActivity implements PullRecyclerView.OnPullRefreshListener,CommonAdapter.OnItemClickListener{

    private final String TAG = TodoWorkForEntActivity.class.getSimpleName();
    private final String Title = "代办工作";
    @BindView(R.id.pullRecylerView)
    PullRecyclerView pullRecyclerView;
    private TodoWorkAdapter adapter;
    List<TodoWorkInfo> datas = new ArrayList<>();

    private int pageIndex = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_todo_work;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getData();
    }

    private void getData(){
        String url = strZJIP+"/pprty/WSRest/service/backlog";
        HashMap<String,String> params = new HashMap<>();
    //    params.put("roleCode",SyberosManagerImpl.getInstance().getCurrentUserInfo().getRoleExtInfoList().get(0).getRoleCode());
       params.put("roleCode","130821199103278829");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                datas.clear();
                pullRecyclerView.refreshOrLoadComplete();
                pageIndex ++ ;
                Gson gson = new Gson();
                TodoWorkInfo todoWorkInfo = gson.fromJson(result, TodoWorkInfo.class);
                if(todoWorkInfo.dataSource.list!=null) {
                    datas = todoWorkInfo.dataSource.list;
                }
                refreshUI();
                pullRecyclerView.setHasMore(todoWorkInfo.dataSource.hasMore=="true");
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
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        adapter = new TodoWorkAdapter(mContext,R.layout.layout_todo_work_item,datas);
        pullRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        pullRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        pullRecyclerView.setAdapter(adapter);
        pullRecyclerView.setOnPullRefreshListener(this);
        adapter.setOnItemClickListener(this);

    }

    /**
     * 根据权限模块进行过滤
     */
    private void refreshUI(){
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(int position) {
        TodoWorkInfo todoWorkInfo = datas.get(position);
        String moduleName = todoWorkInfo.getModName();
        String appCode = todoWorkInfo.getAppCode(); // "cjfr"
        String busiCode = todoWorkInfo.getBusiCode();
        String busiName = todoWorkInfo.getBusiName();
        String nextStep = todoWorkInfo.getNextStep();

        // 隐患
        if(GlobleConstants.Module_Name_Hidd.equalsIgnoreCase(moduleName)) {
            ObjHidden objHidden = new ObjHidden();
            objHidden.setGuid(busiCode);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",objHidden);
            bundle.putSerializable("todoWork",todoWorkInfo);
            bundle.putString("type","0");
            intentActivity(TodoWorkForEntActivity.this,InvestigationAcceptFormActivity.class,false,bundle);
        }
        // 事故
        else if ( GlobleConstants.Module_Name_Acci.equalsIgnoreCase(moduleName)){

        }
        // 风险源
        else if(GlobleConstants.Module_Name_Haz.equalsIgnoreCase(moduleName)){

        }
        // 安全检查
        else if(GlobleConstants.Module_Name_Sins.equalsIgnoreCase(moduleName)){

        }
        // 安全检查报表
        else if(GlobleConstants.Module_Name_Report_Check.equalsIgnoreCase(moduleName)){

        }
        //隐患报表
        else if(GlobleConstants.Module_Name_Report_Hidd.equalsIgnoreCase(moduleName)){

        }
        // 事故报表
        else if(GlobleConstants.Module_Name_Report_Acci.equalsIgnoreCase(moduleName)){

        }else {
            final CustomDialog customDialog = new CustomDialog(TodoWorkForEntActivity.this);
            customDialog.setDialogMessage(Title, "", null);
            customDialog.setMessage("请在电脑端进行处理");
            customDialog.setDialogOneBtn();

            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            customDialog.show();
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        getData();


    }

    @Override
    public void onLoadMore() {
        getData();

    }

    private class TodoWorkAdapter extends CommonAdapter<TodoWorkInfo> {

        public TodoWorkAdapter(Context context, int layoutId, List<TodoWorkInfo> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, TodoWorkInfo todoWorkInfo) {
            ((TextView)(holder.getView(R.id.tv_todo_work_time))).setText(todoWorkInfo.getMtime());
            ((TextView)(holder.getView(R.id.tv_todo_work_title))).setText(todoWorkInfo.getBusiName());

        }
    }
}
