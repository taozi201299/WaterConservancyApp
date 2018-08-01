package com.syberos.shuili.activity.woas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.woas.InspectAssessHistory;
import com.syberos.shuili.entity.woas.InspectAssessInfo;
import com.syberos.shuili.entity.woas.InspectAssessPlanBatchInfo;
import com.syberos.shuili.entity.woas.InspectAssessPlanInfo;
import com.syberos.shuili.utils.Strings;

import java.util.List;

import butterknife.BindView;

public class InspectAssessDetailRealSituationActivity extends BaseActivity {

    private InspectAssessInfo info = null;

    @BindView(R.id.ll_container_0)
    LinearLayout ll_container_0;

    @BindView(R.id.ll_container_1)
    LinearLayout ll_container_1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_detail_real_situation;
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
        info = (InspectAssessInfo) bundle.getSerializable(
                InspectAssessDetailActivity.SEND_BUNDLE_KEY_0);

        if (null != info) {

            setActionBarTitle(info.getName());
            setActionBarRightVisible(View.INVISIBLE);

            List<InspectAssessPlanInfo> inspectAssessInfoList = info.getInspectAssessPlanInfoList();
            for (final InspectAssessPlanInfo info : inspectAssessInfoList) {
                View view = LayoutInflater.from(mContext).inflate(
                        R.layout.activity_inspect_assess_detail_real_situation_inspect_item,
                        null);

                ((TextView) view.findViewById(R.id.tv_expand_name)).setText(info.getName());
                ((TextView) view.findViewById(R.id.tv_expand_time)).setText(info.getTime());

                List<InspectAssessPlanBatchInfo> inspectAssessPlanBatchInfoList
                        = info.getInspectAssessPlanBatchInfoList();

                for (final InspectAssessPlanBatchInfo info1 : inspectAssessPlanBatchInfoList) {
                    LinearLayout ll_expandable_container
                            = (LinearLayout) view.findViewById(R.id.ll_expandable_container);

                    View view1 = LayoutInflater.from(mContext).inflate(
                            R.layout.activity_inspect_assess_detail_real_situation_inspect_item_batch,
                            null);

                    ((TextView) view1.findViewById(R.id.tv_batch)).setText(info1.getBatch());
                    ((TextView) view1.findViewById(R.id.tv_time)).setText(info1.getTime());
                    ((TextView) view1.findViewById(R.id.tv_projects)).setText(info1.getProjects());

                    ll_expandable_container.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                    ll_expandable_container.addView(view1);
                }

                ll_container_0.addView(view);

            }

            List<InspectAssessHistory> inspectAssessHistoryList = info.getInspectAssessHistoryList();
            for (final InspectAssessHistory info1 : inspectAssessHistoryList) {
                View view2 = LayoutInflater.from(mContext).inflate(
                        R.layout.activity_inspect_assess_detail_real_situation_history_item,
                        null);

                ((TextView) view2.findViewById(R.id.tv_time)).setText(info1.getTime());
                ((TextView) view2.findViewById(R.id.tv_score)).setText(
                        String.format("%s分", info1.getScore()));

                ll_container_1.addView(view2);
            }

        }
    }
}
