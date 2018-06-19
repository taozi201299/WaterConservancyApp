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
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.entity.HistoryPatrolInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 该文件暂不使用
 */
public class IssueTrackingDetailActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String TAG = IssueTrackingDetailActivity.class.getSimpleName();

    public static final String SEND_BUNDLE_KEY = "HistoryPatrolInformation";
    private DangerousInformation information = null;

    @BindView(R.id.tv_unit)
    TextView tv_unit;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.tv_date_time)
    TextView tv_date_time;

    @BindView(R.id.recyclerView_record_review)
    RecyclerView recyclerView;

    HistoryPatrolListAdapter listAdapter;
    List<HistoryPatrolInformation> informationList = null;

    @OnClick(R.id.tv_look_detail)
    void onLookDetailClicked() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(InspectionListForEnterpriseActivity.SEND_BUNDLE_KEY, information);

        intentActivity(this, IssueTrackingDangerousDetailActivity.class,
                false, bundle);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        HistoryPatrolInformation information = informationList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);

        intentActivity(this, IssueTrackingHistoryPatrolDetailActivity.class,
                false, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_issue_tracking_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (DangerousInformation) bundle.getSerializable(
                RecordReviewListActivity.SEND_BUNDLE_KEY);

        if (null != information) {

            setActionBarTitle(information.getDangerousName());

            tv_unit.setText(information.getDangerousUnit());
            tv_date_time.setText(information.getTime());

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("危险源描述");
            ae_describe_audio.setEditText(information.getDangerousContent());
            ae_describe_audio.setAudio("111", 3);


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
        //    ((TextView) (holder.getView(R.id.arrhpli_tv_content))).setText(information.getContent());
        }
    }
}
