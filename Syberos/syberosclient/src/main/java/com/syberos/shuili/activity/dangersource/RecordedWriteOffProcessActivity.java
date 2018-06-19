package com.syberos.shuili.activity.dangersource;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.ProcessStepInfo;
import com.syberos.shuili.view.VerticalStepsView.VerticalStepsViewIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 该文件暂不使用
 */
public class RecordedWriteOffProcessActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    private List<ProcessStepInfo> stepsInfoList = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recorded_write_off_process;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle("核销记录");

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
                convertView = LayoutInflater.from(RecordedWriteOffProcessActivity.this)
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
