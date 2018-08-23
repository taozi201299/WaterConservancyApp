package com.syberos.shuili.activity.securitycheck;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationAccepDetailActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.HIDD_TYPE_BIGER;
import static com.syberos.shuili.config.GlobleConstants.HIDD_TYPE_NORMAL;

/**
 * Created by Administrator on 2018/8/13.
 * 按照工程进行分类 每个工程下隐患
 */

public class SecurityCheckHiddenActivity extends BaseActivity {

    private final String Title = "安全检查隐患信息";
    @BindView(R.id.recyclerView_security_hidden)
    RecyclerView recyclerView_security_hidden;

    private RelSinsGroupWiun relSinsGroupWiun = null;
    private BisSinsScheGroup bisSinsScheGroup = null;

    private ObjHidden objHidden = null;
    ArrayList<SecurityCheckHiddenGroup> groups = new ArrayList<>();
    GroupedEnterprisesExpressAccidentListAdapter groupedEnterprisesExpressAccidentListAdapter ;

    private int iSucessCount = 0;
    private int iFailedCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_check_hidden;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        iSucessCount = 0;
        iFailedCount = 0;
        groups.clear();
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if(relSinsGroupWiun == null){
            relSinsGroupWiun = (RelSinsGroupWiun) bundle.getSerializable("checkObject");
        }if(bisSinsScheGroup == null){
            bisSinsScheGroup = (BisSinsScheGroup) bundle.getSerializable("checkGroup");
        }
        getCheckHidden();
    }

    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        groupedEnterprisesExpressAccidentListAdapter = new GroupedEnterprisesExpressAccidentListAdapter(this,groups);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView_security_hidden.setLayoutManager(layoutManager);
        recyclerView_security_hidden.setAdapter(groupedEnterprisesExpressAccidentListAdapter);
        groupedEnterprisesExpressAccidentListAdapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder, int groupPosition, int childPosition) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",groups.get(groupPosition).getChildren().get(childPosition));
                intentActivity(SecurityCheckHiddenActivity.this,InvestigationAccepDetailActivity.class,false,bundle);
            }
        });
    }

    private void getCheckHidden(){
        final int size = relSinsGroupWiun.dataSource.size();
        for(int i =0 ; i < size ; i++){
            final RelSinsGroupWiun item = relSinsGroupWiun.dataSource.get(i);
            final String url = GlobleConstants.strIP + "/sjjk/v1/bis/obj/objHidds/";
            HashMap<String,String> params = new HashMap<>();
            params.put("engGuid","6837D159F6BF40858FBD5A8C09C02DA6");
           // params.put("engGuid",item.getGuid());
           // params.put("sinsScheGuid",bisSinsScheGroup.getScheGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url,new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    iSucessCount ++ ;
                    Gson gson = new Gson();
                    objHidden = gson.fromJson(result,ObjHidden.class);
                    if(objHidden != null && objHidden.dataSource != null){
                        if(objHidden.dataSource.size() != 0){
                            SecurityCheckHiddenGroup securityCheckHiddenGroup = new SecurityCheckHiddenGroup(item.getEngName(), (ArrayList<ObjHidden>) objHidden.dataSource);
                            groups.add(securityCheckHiddenGroup);
                        }
                    }
                    if(iSucessCount + iFailedCount == size){
                        closeDataDialog();
                        refreshUI();

                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    if(iSucessCount + iFailedCount == size){
                        closeDataDialog();
                        refreshUI();
                    }
                }
            });
        }
    }
    private  void  refreshUI(){
        groupedEnterprisesExpressAccidentListAdapter.setData(groups);
        groupedEnterprisesExpressAccidentListAdapter.notifyDataSetChanged();


    }
    private static class SecurityCheckHiddenGroup implements Serializable {

        private String header;
        private ArrayList<ObjHidden> children;

        public SecurityCheckHiddenGroup(String header, ArrayList<ObjHidden> children) {
            this.header = header;
            this.children = children;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public ArrayList<ObjHidden> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ObjHidden> children) {
            this.children = children;
        }
    }
    private class GroupedEnterprisesExpressAccidentListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<SecurityCheckHiddenGroup> mGroups;

        public GroupedEnterprisesExpressAccidentListAdapter(
                Context context, ArrayList<SecurityCheckHiddenGroup> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<SecurityCheckHiddenGroup> groups){
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<ObjHidden> children = mGroups.get(groupPosition).getChildren();
            return children == null ? 0 : children.size();
        }

        @Override
        public boolean hasHeader(int groupPosition) {
            return true;
        }

        /**
         * 返回false表示没有组尾
         *
         * @param groupPosition
         * @return
         */
        @Override
        public boolean hasFooter(int groupPosition) {
            return false;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @return
         */
        @Override
        public int getFooterLayout(int viewType) {
            return 0;
        }

        /**
         * 当hasFooter返回false时，这个方法不会被调用。
         *
         * @param holder
         * @param groupPosition
         */
        @Override
        public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public int getHeaderLayout(int viewType) {
            return R.layout.adapter_header;
        }

        @Override
        public int getChildLayout(int viewType) {
            return R.layout.activity_investigation_task_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            SecurityCheckHiddenGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            final ObjHidden objHidden
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            int type = objHidden.getHiddGrad() == null ? 1: Integer.valueOf(objHidden.getHiddGrad());

            LinearLayout ll_type =  holder.get(R.id.ll_type);
            holder.get(R.id.tv_name).setVisibility(View.GONE);
            RelativeLayout rl_supervice = holder.get(R.id.ll_supervise);
            rl_supervice.setVisibility(View.GONE);
            ShapeDrawable bgShape = null;
            switch (type){
                case HIDD_TYPE_NORMAL: {
                    ((TextView) (holder.get(R.id.tv_type))).setText(R.string.normal);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                }
                break;
                case HIDD_TYPE_BIGER: {
                    ((TextView) (holder.get(R.id.tv_type))).setText(R.string.danger);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                }
                break;
                default:
                    ((TextView) (holder.get(R.id.tv_type))).setText(R.string.normal);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                    break;

            }
            ( (TextView)(holder.get(R.id.tv_title))).setText(objHidden.getHiddName());
            ( (TextView)(holder.get(R.id.tv_time))).setText(objHidden.getCollTime());
            ( (TextView)(holder.get(R.id.tv_content))).setText(objHidden.getHiddDesc());


        }
    }
}
