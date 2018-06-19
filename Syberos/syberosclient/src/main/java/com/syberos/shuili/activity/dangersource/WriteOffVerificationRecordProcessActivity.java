package com.syberos.shuili.activity.dangersource;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.entity.ProcessStepInfo;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;
import com.syberos.shuili.view.VerticalStepsView.VerticalStepsViewIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * 该文件暂不使用
 */
public class WriteOffVerificationRecordProcessActivity extends BaseActivity {

    @BindView(R.id.tv_unit)
    TextView tv_unit;

    @BindView(R.id.tv_code)
    TextView tv_code;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.listView)
    ListView listView;

    private List<ProcessStepInfo> stepsInfoList = null;

    private DangerousInformation information = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_write_off_verification_record_process;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        setActionBarTitle("备案记录");

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (DangerousInformation)bundle.getSerializable(
                WriteOffVerificationListActivity.SEND_BUNDLE_KEY);
        if (null != information) {

        }

        ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
        ae_describe_audio.setLabelText("备注");
        ae_describe_audio.setEditText("划拨维修经费5万元，对堤坝进行修整，确保水库安全" +
                "运行，对损坏的堤坝进行修复，加固。");
        ae_describe_audio.setAudio("111",3);


        stepsInfoList = new ArrayList<>();

        for (int i = 1; i <= 9; ++i) {
            ProcessStepInfo stepInfo = new ProcessStepInfo();

            if (i <= 5) {
                stepInfo.setStepCompleted(true);
                if (i == 5) {
                    stepInfo.setCurrent(true);
                }
            }

            stepInfo.setStepTime("2017-12-0" + i);
            stepInfo.setStepTitle("核销申请 0" + i);
            stepInfo.setStepTitleColor((i % 3 == 2) ? R.color.red : R.color.black);

            stepsInfoList.add(stepInfo);
        }

        StepsInfoListAdapter listAdapter = new StepsInfoListAdapter();
        listView.setDividerHeight(0);
        listView.setAdapter(listAdapter);
    }


    private static class ViewHolder {
        VerticalStepsViewIndicator stepViewIndicator;
        TextView stepTime;
        TextView stepTitle;
        TextView stepContent;
    }

    private class StepsInfoListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return null == stepsInfoList ? 0 : stepsInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return null == stepsInfoList ? null : stepsInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == stepsInfoList || stepsInfoList.size() <= 0) {
                return convertView;
            }

            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(WriteOffVerificationRecordProcessActivity.this)
                        .inflate(R.layout.widget_vertical_steps_item, null);
                holder = new ViewHolder();
                holder.stepViewIndicator
                        = convertView.findViewById(R.id.steps_indicator_view);
                holder.stepTime
                        = convertView.findViewById(R.id.steps_label_time);
                holder.stepTitle
                        = convertView.findViewById(R.id.steps_label_title);
                holder.stepContent
                        = convertView.findViewById(R.id.steps_label_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ProcessStepInfo stepInfo = stepsInfoList.get(position);

            if (null != stepInfo) {
                holder.stepViewIndicator.setStepNumber(stepInfo.getStepNumber());
                holder.stepViewIndicator.setStepCompleted(stepInfo.isStepCompleted());
                holder.stepViewIndicator.setIsCurrentStep(stepInfo.isCurrent());
                holder.stepViewIndicator.invalidate();

                holder.stepTime.setText(stepInfo.getStepTime());
                holder.stepTitle.setText(stepInfo.getStepTitle());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.stepTitle.setTextColor(
                            getResources().getColor(stepInfo.getStepTitleColor(), null));
                } else {
                    holder.stepTitle.setTextColor(
                            getResources().getColor(stepInfo.getStepTitleColor()));
                }
                holder.stepContent.setText(stepInfo.getStepContent());
            } else {
                //TODO
            }

            return convertView;
        }
    }
}
