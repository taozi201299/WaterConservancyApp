package com.syberos.shuili.activity.wins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.wins.BisWinsProg;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 稽查查询详情
 */
public class InspectQueryDetailActivity extends TranslucentActivity {

    private BisWinsProg bisWinsProg;
    private BisWinsGroup bisWinsGroup ;

    @BindView(R.id.ll_groups)
    LinearLayout ll_groups;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;


    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_query_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        getWinsGroupByWinsProgGuid();

    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        bisWinsProg = (BisWinsProg) bundle.getSerializable(
                InspectQueryListActivity.SEND_BUNDLE_KEY);

        if (null != bisWinsProg) {
            tv_action_bar_title.setText(bisWinsProg.getWinsProjType());
        }
    }

    /**
     * 根据稽查方案GUID 获取稽查组信息
     */
    private void  getWinsGroupByWinsProgGuid(){
        String url = GlobleConstants.strIP +"/sjjk/v1/bis/wins/prog/selectWinsGroupInfoByWinsProgGuid/";
        HashMap<String,String> params = new HashMap<>();
        params.put("winsProgGuid",bisWinsProg.getBwpGuid());
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                bisWinsGroup = gson.fromJson(result,BisWinsGroup.class);
                if(bisWinsGroup == null || bisWinsGroup.dataSource == null){
                    ToastUtils.show("获取稽查组信息错误");
                }else {
                   addGroupItems(bisWinsGroup.dataSource);
                }

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show("获取稽查组信息错误");
            }
        });
    }
    private void addGroupItems(final List<BisWinsGroup> groups) {
        ll_groups.removeAllViews();
        int size = groups.size();
        for(int i = 0; i< size; i++) {
            View layout = LayoutInflater.from(this).inflate(R.layout.layout_inspect_query_group_item,
                    ll_groups, false);
            TextView name = (TextView) layout.findViewById(R.id.tv_name);
            name.setText(groups.get(i).getWinsGroupNum());
            ll_groups.addView(layout);
            final int finalI = i;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bisWinsGroup",groups.get(finalI));
                    bundle.putSerializable("bisWinsProg",bisWinsProg);
                    intentActivity(InspectQueryDetailActivity.this,InspectQueryGroupDetailActivity.class,false,bundle);
                }
            });
        }
    }

}
