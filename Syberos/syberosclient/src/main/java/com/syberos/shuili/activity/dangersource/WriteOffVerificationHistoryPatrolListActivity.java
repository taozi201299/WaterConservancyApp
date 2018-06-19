package com.syberos.shuili.activity.dangersource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.HistoryPatrolInformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * 该文件暂不使用
 */
public class WriteOffVerificationHistoryPatrolListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = WriteOffVerificationHistoryPatrolListActivity.class.getSimpleName();

    private final String Title = "巡查记录";

    public static final String SEND_BUNDLE_KEY = "HistoryPatrolInformation";

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    HistoryPatrolListAdapter listAdapter;
    List<HistoryPatrolInformation> informationList = null;

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        HistoryPatrolInformation information = informationList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);

        intentActivity(this, WriteOffVerificationHistoryPatrolDetailActivity.class,
                false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_write_off_verification_history_patrol_list;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle(Title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new HistoryPatrolListAdapter(this,
                R.layout.activity_history_patrol_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);

        if (null == informationList) {
            informationList = new ArrayList<>();
        } else {
            informationList.clear();
        }

        int j = 0;
        for (int i = 0; i < 3; i++) {
            HistoryPatrolInformation information
                    = new HistoryPatrolInformation();
            information.setContent("危险源裸露，尚未移除，有轻微泄露危险源裸露，尚未移除，" +
                    "有轻微泄露危险源裸露，尚未移除，有轻微泄露危险源裸露");
            information.setControls("危险源裸露，尚未移除，有轻微泄露危险源裸露，尚未移除，有轻微泄" +
                    "露危险源裸露，尚未移除，有轻微泄露危险源裸露");
            information.setTime("10:2" + i);
            information.setReporter("李小白");
            information.setId(String.valueOf(j));
            ++j;
            informationList.add(information);
        }

        for (int i = 0; i < 4; i++) {
            HistoryPatrolInformation information
                    = new HistoryPatrolInformation();
            information.setContent("危险源裸露，尚未移除，有轻微泄露危险源裸露，尚未移除，" +
                    "有轻微泄露危险源裸露，尚未移除，有轻微泄露危险源裸露");
            information.setControls("危险源裸露，尚未移除，有轻微泄露危险源裸露，尚未移除，有轻微泄" +
                    "露危险源裸露，尚未移除，有轻微泄露危险源裸露");
            information.setTime("2018-04-07 12:3" + i);
            information.setReporter("李大白");
            information.setId(String.valueOf(j));
            ++j;
            informationList.add(information);
        }

        listAdapter.setData(informationList);
    }

    private class HistoryPatrolListAdapter
            extends CommonAdapter<HistoryPatrolInformation> {
        public HistoryPatrolListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void convert(ViewHolder holder, HistoryPatrolInformation information) {
            ((TextView) (holder.getView(R.id.arrhpli_tv_time))).setText(
                    information.getTime());
          //  ((TextView) (holder.getView(R.id.arrhpli_tv_content))).setText(information.getContent());
        }
    }
}
