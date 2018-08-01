package com.syberos.shuili.activity.securitycheck;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 安全检查被检对象选择列表
 */
public class SecurityCheckProjectSelectActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String Tag = SecurityCheckProjectSelectActivity.class.getSimpleName();
    private BisSinsScheGroup information;
    RelSinsGroupWiun relSinsGroupWiun;
    private final static int REQUEST_CODE = 1047;
    public static final String SEND_BUNDLE_KEY = "SecurityCheck";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    ListAdapter listAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_check_project_select;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if(information == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
        }else {
            getCheckObject();
        }

    }

    @Override
    public void initView() {
        setActionBarTitle("安全检查隐患排查");
        setActionBarRightVisible(View.INVISIBLE);

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (BisSinsScheGroup)bundle.getSerializable(
                SecurityCheckTaskActivity.SEND_BUNDLE_KEY);

        if (null != information) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                    DividerItemDecoration.VERTICAL));
            listAdapter = new ListAdapter(this,
                    R.layout.activity_security_check_project_select_item);
            recyclerView.setAdapter(listAdapter);
            listAdapter.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SEND_BUNDLE_KEY, relSinsGroupWiun.dataSource.get(position));
        bundle.putSerializable("group",information);
        // 判断是否存在标段
        intentActivity(this, SecurityCreateHiddenActivity.class,
                false, bundle, REQUEST_CODE);
    }
    /**
     * 获取被检对象
     */
    private void getCheckObject(){
        String url = "http://192.168.1.8:8080/sjjk/v1/rel/sins/group/wiun/selectCheckOnline/";
        HashMap<String,String> params = new HashMap<>();
        // 检查小组GUID
        params.put("guid",information.getGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                relSinsGroupWiun = (RelSinsGroupWiun)gson.fromJson(result,RelSinsGroupWiun.class);
                if(relSinsGroupWiun == null || relSinsGroupWiun.dataSource == null){
                    closeDataDialog();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }
                refreshUI();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    void refreshUI(){
        listAdapter.setData(relSinsGroupWiun.dataSource);
        listAdapter.notifyDataSetChanged();
    }
    private class ListAdapter extends CommonAdapter<RelSinsGroupWiun> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final RelSinsGroupWiun information) {
            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getEngName());
        }
    }

}
