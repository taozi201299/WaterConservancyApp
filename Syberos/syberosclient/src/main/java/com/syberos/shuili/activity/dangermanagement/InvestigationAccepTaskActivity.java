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
import com.lzy.okhttputils.cache.CacheMode;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.UserExtendInfo;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.basicbusiness.ObjProject;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by jidan on 18-3-23.
 * 行政版 隐患销号 获取隐患状态获取需要验收的隐患对象
 */

public class InvestigationAccepTaskActivity extends BaseActivity implements CommonAdapter.OnItemClickListener,PullRecyclerView.OnPullRefreshListener {
    private final String TAG = InvestigationAccepTaskActivity.class.getSimpleName();
    public static final String Title = "隐患销号";
    private final String btnText = "销号";
    /**
     *  完成整改 4
     */
    private final String strHiddState = "4";
    private ObjectEngine objectEngine;
    private ObjProject objProject;
    private ArrayList<ObjectEngine> objectEngines = new ArrayList<>();
    private ArrayList<ObjProject>objProjects = new ArrayList<>();
    @BindView(R.id.recyclerView_investigation)
    PullRecyclerView recyclerView;
    InvestigationAccepTaskActivity.InvestigationAdapter investigationAdapter ;
    ObjHidden investigationTaskInfo;
    /**
     * 隐患类型
     *
     */
    DicInfo m_hiddenClass;
    /**
     * 隐患级别
     */
    DicInfo m_hiddenGrade;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_task_layout;
    }

    @Override
    public void initListener() {
        recyclerView.setOnPullRefreshListener(this);
        recyclerView.setHasMore(false);
    }

    /**
     * 根据隐患状态查询隐患验收对象
     */
    @Override
    public void initData() {
        showDataLoadingDialog();
        getHiddenList();

    }
    private void closeLoadingDialog(){
        closeDataDialog();
        recyclerView.refreshOrLoadComplete();
    }
    //水行政发现的重大隐患和挂牌督办隐患需要销号。企事业整改完成后，水行政统一销号。
    /**
     *     1 获取全部的整改完成的隐患
     *     2 水新政单位的需要时重大和挂牌督办
     *     3 企事业整改完成的全部隐患
      */
    private void getHiddenList(){
        final String url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/objHidds/";
        HashMap<String,String> params = new HashMap<>();
        UserExtendInfo info = SyberosManagerImpl.getInstance().getCurrentUserInfo();
        params.put("hiddStat",strHiddState);
        params.put("orgGuid",info.getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url,new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                investigationTaskInfo = gson.fromJson(result,ObjHidden.class);
                if(investigationTaskInfo != null && investigationTaskInfo.dataSource != null){
                    if(investigationTaskInfo.dataSource.size() == 0){
                        closeLoadingDialog();
                        ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-7).getMessage());
                    }else {
                        getHiddenGrade();
                    }
                }
                else {
                    closeLoadingDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }

    /**
     * 根据工程GUID 获取工程名称
     */
    private void getEngineInfo(){
        String url = "http://192.168.1.8:8080/sjjk/v1/jck/obj/objEngs/";
        HashMap<String,String>params = new HashMap<>();
        for(final ObjHidden item : investigationTaskInfo.dataSource){
            params.put("guid",item.getEngGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    closeLoadingDialog();
                    Gson gson = new Gson();
                    objectEngine = gson.fromJson(result,ObjectEngine.class);
                    if(objectEngine != null && objectEngine.dataSource != null && objectEngine.dataSource.size() > 0){
                        objectEngines.add(objectEngine.dataSource.get(0));
                    }
                    if(investigationTaskInfo.dataSource.indexOf(item) == Integer.valueOf(investigationTaskInfo.totalCount) - 1){
                        merageData(1);
                        refreshUI();
                    }
                }
                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    closeLoadingDialog();
                    ToastUtils.show(errorInfo.getMessage());

                }
            });
        }
    }
    private ObjectEngine getOrgInfo(String orgId){
        for(ObjectEngine item : objectEngines){
            if(item.getGuid().equals(orgId))
                return item;
        }
        return null;
    }
    private ObjProject getProjectItem(String orgId){
        for(ObjProject item :objProjects){
            if(item.getGuid().equals(orgId))
                return item;
        }
        return null;
    }
    private void merageData(int type){
        ObjectEngine info = null;
        ObjProject project = null;
        for(ObjHidden item : investigationTaskInfo.dataSource){
            if(type == 1) {
                info = getOrgInfo(item.getEngGuid());
            }else {
                project = getProjectItem(item.getEngGuid());
            }
            String hiddenClassName = getHiddenClassName(item.getHiddClas());
            String hiddenGradeName = getHiddenGradeName(item.getHiddGrad());
            if(info != null){
                item.setEngName(info.getEngName());
            }if(project != null){
                item.setEngName(project.getEngName());
            }
            item.setHiddClassName(hiddenClassName);
            item.setHiddGradName(hiddenGradeName);
        }
    }
    /**
     * 获取隐患分类类型
     */
    private void getHiddenType() {
        String url  = "http://192.168.1.8:8080/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_HIDD");
        params.put("attColCode","HIDD_CLAS");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                m_hiddenClass  = gson.fromJson(result,DicInfo.class);
                if(m_hiddenClass == null || m_hiddenClass.dataSource == null || m_hiddenClass.dataSource.size() == 0){
                    closeLoadingDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getEngineInfo();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    /**
     * 获取隐患级别
     */
    private void getHiddenGrade(){
        String url  = "http://192.168.1.8:8080/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_HIDD");
        params.put("attColCode","HIDD_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                m_hiddenGrade  = gson.fromJson(result,DicInfo.class);
                if(m_hiddenGrade == null || m_hiddenGrade.dataSource == null || m_hiddenGrade.dataSource.size() == 0){
                    closeLoadingDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    return;
                }
                getHiddenType();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeLoadingDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    private  String  getHiddenClassName(String hiddenClass){
        for(DicInfo item:m_hiddenClass.dataSource){
            if(hiddenClass.equals(item.getDcItemCode())){
                return item.getDcItemName();
            }
        }
        return "";

    }
    private String getHiddenGradeName(String hiddenGrade){
        for(DicInfo item:m_hiddenGrade.dataSource){
            if(hiddenGrade.equals(item.getDcItemCode())){
                return item.getDcItemName();
            }
        }
        return "";
    }
    private void refreshUI(){
        investigationAdapter.setData(investigationTaskInfo.dataSource);
        investigationAdapter.notifyDataSetChanged();
    }
    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        investigationAdapter = new InvestigationAccepTaskActivity.InvestigationAdapter(this,R.layout.activity_investigation_task_item);
        recyclerView.setAdapter(investigationAdapter);
        investigationAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",investigationTaskInfo.dataSource.get(position));
        intentActivity(this,InvestigationAccepDetailActivity.class,false,bundle);
    }
    @Override
    public void onRefresh() {
        getHiddenList();
    }

    @Override
    public void onLoadMore() {

    }

    private class InvestigationAdapter extends CommonAdapter<ObjHidden> {
        public InvestigationAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjHidden investigationInfo) {
            String type = investigationInfo.getHiddGrad();
            LinearLayout ll_type = null;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            Button btnSupervice = (Button)holder.getView(R.id.btn_supervice);
            RelativeLayout rl_supervice = (RelativeLayout)holder.getView(R.id.ll_supervise);
            rl_supervice.setVisibility(View.VISIBLE);
            btnSupervice.setText(btnText);
            btnSupervice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",investigationInfo);
                    intentActivity(InvestigationAccepTaskActivity.this,InvestigationAcceptFormActivity.class,false,bundle);
                }
            });
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
            ( (TextView)(holder.getView(R.id.tv_time))).setText(investigationInfo.getCollTime());
            ( (TextView)(holder.getView(R.id.tv_name))).setText(investigationInfo.getEngName());
            ( (TextView)(holder.getView(R.id.tv_content))).setText(investigationInfo.getHiddDesc());


        }
    }
}
