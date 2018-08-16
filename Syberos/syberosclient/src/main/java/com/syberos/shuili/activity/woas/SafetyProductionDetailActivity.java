package com.syberos.shuili.activity.woas;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.entity.woas.InspectionObjectInfo;
import com.syberos.shuili.entity.woas.OnSiteInspectionInfo;
import com.syberos.shuili.utils.Strings;

import java.util.List;

import butterknife.BindView;

/**
 * 安全生产考核详情界面
 *
 * 1 获取考核方案信息
 */
public class SafetyProductionDetailActivity extends BaseActivity {

    public static final String SEND_BUNDLE_KEY_0 = "InspectionObjectInfo";
    public static final String SEND_BUNDLE_KEY_1 = "DeductMarksInfo";

    private OnSiteInspectionInfo info;

    @BindView(R.id.tv_check_plan)
    TextView tv_check_plan;

    @BindView(R.id.tv_check_time)
    TextView tv_check_time;

    @BindView(R.id.tv_check_content)
    TextView tv_check_content;

    @BindView(R.id.tv_group_leader)
    TextView tv_group_leader;

    @BindView(R.id.tv_group_unit)
    TextView tv_group_unit;

    @BindView(R.id.tv_member_unit)
    TextView tv_member_unit;

    @BindView(R.id.tv_check_person)
    TextView tv_check_person;

    @BindView(R.id.ll_container_0)
    LinearLayout ll_container_0;

    @BindView(R.id.ll_container_1)
    LinearLayout ll_container_1;



    @Override
    public int getLayoutId() {
        return R.layout.activity_safety_production_detail;
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
        info = (OnSiteInspectionInfo) bundle.getSerializable(
                SafetyProductionListActivity.SEND_BUNDLE_KEY);
        if (null != info) {
            setActionBarTitle(info.getName());
            setActionBarRightVisible(View.INVISIBLE);

            // 所属考核方案
            tv_check_plan.setText(info.getPlan());
            // 考核时间
            tv_check_time.setText(info.getTime());
            // 考核内容
            tv_check_content.setText(info.getContent());
            // 组长名称
            tv_group_leader.setText(info.getGroupName());
            // 组长单位
            tv_group_unit.setText(info.getGroupUnit());
            // 组员单位
            tv_member_unit.setText(info.getChildUnit());
            // 专家姓名
            tv_check_person.setText(info.getExperts());

            List<InspectionObjectInfo> inspectionObjectInfoList = info.getInspectionObjectInfoList();
            for (final InspectionObjectInfo objectInfo : inspectionObjectInfoList) {
                View view = LayoutInflater.from(mContext).inflate(
                        R.layout.activity_safety_production_detail_check_item, null);

                ((TextView) view.findViewById(R.id.tv_item_name)).setText(objectInfo.getName());

                TextView btn = (TextView) view.findViewById(R.id.iv_arrow_right);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(SEND_BUNDLE_KEY_0, objectInfo);
                        intentActivity((Activity) mContext,
                                SafetyProductionDetailRealSituationActivity.class,
                                false, bundle);
                    }
                });
                ll_container_0.addView(view);
            }

            List<DeductMarksInfo> deductMarksInfoList = info.getDeductMarksInfoList();
            for (final DeductMarksInfo marksInfo : deductMarksInfoList) {
                View view = LayoutInflater.from(mContext).inflate(
                        R.layout.activity_safety_production_detail_deduct_marks, null);

                ((TextView) view.findViewById(R.id.tv_time)).setText(marksInfo.getTime());
                ((TextView) view.findViewById(R.id.tv_unit)).setText(marksInfo.getUnit());
                ((TextView) view.findViewById(R.id.tv_score)).setText(
                        String.format("%s分", marksInfo.getScore()));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(SEND_BUNDLE_KEY_1, marksInfo);
                        intentActivity((Activity) mContext,
                                SafetyProductionDetailDeductMarksActivity.class,
                                false, bundle);
                    }
                });
                ll_container_1.addView(view);
            }

        }
    }
}
