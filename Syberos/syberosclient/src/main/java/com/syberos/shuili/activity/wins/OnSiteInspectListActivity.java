package com.syberos.shuili.activity.wins;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsProg;
import com.syberos.shuili.entity.wins.ObjWinsPlan;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

/**
 * 现场稽查列表 行政版  该接口暂时没有提供
 * 水利稽查
 * OBJ_WINS_PLAN 表中获取单位的稽查计划，从稽查方案表中找到稽查方案详情
 *
 * 8.2.2.83	水利稽察组（BIS_WINS_GROUP）
 * 从水利稽查组中获取所在的稽查组
 */
public class OnSiteInspectListActivity extends TranslucentActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = OnSiteInspectListActivity.class.getSimpleName();

    private final String Title = "现场稽查";

    public static final String SEND_BUNDLE_KEY = "OnSiteInspectInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    @BindView(R.id.tv_action_bar2_title)
    TextView tv_action_bar2_title;

    ListAdapter listAdapter;
    private ObjWinsPlan objWinsPlan = null;
    private BisWinsProg bisWinsProg = null;
    private ArrayList<BisWinsProg>bisWinsProgs = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_inspect_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //showDataLoadingDialog();
    }

    @Override
    public void initView() {
        tv_action_bar2_title.setText(Title);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_on_site_inspect_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
    }

    private void refreshUI(){

    }
    private class ListAdapter extends CommonAdapter<ObjWinsPlan> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final ObjWinsPlan information) {

            Button button = (Button) holder.getView(R.id.btn_input_problem);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SEND_BUNDLE_KEY, information);
                    intentActivity(OnSiteInspectListActivity.this,
                            InspectProjectSelectActivity.class, false, bundle);
                }
            });

            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getWINSPLANNAME());
            ((TextView) (holder.getView(R.id.tv_batch))).setText(
                    information.getWINSARRAYNUM());
            ((TextView) (holder.getView(R.id.tv_time))).setText(information.getCOLLTIME());
        }
    }
}
