package com.syberos.shuili.activity.wins;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.accident.AccidentInformationGroup;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.inspect.InspectProblemInformation;
import com.syberos.shuili.entity.inspect.BisWinsProb;
import com.syberos.shuili.view.grouped_adapter.adapter.GroupedRecyclerViewAdapter;
import com.syberos.shuili.view.grouped_adapter.holder.BaseViewHolder;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/30.
 * 分组显示 每个工程下的稽查问题
 */

public class InspectionProblemsAcitvity extends BaseActivity {
    private final String Title = "稽查问题";

    @BindView(R.id.recyclerView_inspection_prob)
    RecyclerView recyclerView_inspection_prob;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_problem_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle("");
        setActionBarRightVisible(View.INVISIBLE);

    }
    private static class InspectionProblemGroup implements Serializable {

        private String header;
        private ArrayList<ObjAcci> children;

        public InspectionProblemGroup(String header, ArrayList<ObjAcci> children) {
            this.header = header;
            this.children = children;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public ArrayList<ObjAcci> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ObjAcci> children) {
            this.children = children;
        }
    }
    private static class GroupedEnterprisesExpressAccidentListAdapter extends GroupedRecyclerViewAdapter {


        private ArrayList<AccidentInformationGroup> mGroups;

        public GroupedEnterprisesExpressAccidentListAdapter(
                Context context, ArrayList<AccidentInformationGroup> groups) {
            super(context);
            mGroups = groups;
        }
        public void setData(ArrayList<AccidentInformationGroup> groups){
            mGroups = groups;

        }

        @Override
        public int getGroupCount() {
            return mGroups == null ? 0 : mGroups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<ObjAcci> children = mGroups.get(groupPosition).getChildren();
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
            return R.layout.activity_enterprises_express_accident_list_item;
        }

        @Override
        public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
            AccidentInformationGroup entity = mGroups.get(groupPosition);
            holder.setText(R.id.tv_header, entity.getHeader());
        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder,
                                          final int groupPosition, final int childPosition) {

            final ObjAcci accidentInformation
                    = mGroups.get(groupPosition).getChildren().get(childPosition);
            RelativeLayout ll_report_after = holder.get(R.id.ll_report_after);
            ll_report_after.setVisibility(View.GONE);
            String grade = accidentInformation.getAcciGrad() == null ?"0":accidentInformation.getAcciGrad();
            int type = Integer.valueOf(grade);
            switch (type) {
                case ObjAcci.TYPE_NORMAL: {
                    holder.setText(R.id.tv_type,R.string.accident_type_normal);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case ObjAcci.TYPE_BIG: {
                    holder.setText(R.id.tv_type,R.string.accident_type_big);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_big_shape);
                }
                break;
                case ObjAcci.TYPE_BIGGER: {
                    holder.setText(R.id.tv_type,R.string.accident_type_bigger);
                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                case ObjAcci.TYPE_LARGE: {
                    holder.setText(R.id.tv_type,R.string.accident_type_large);
                    holder.setTextColor(R.id.tv_type, R.color.black);

                    holder.setBackgroundRes(R.id.ll_type,
                            R.drawable.btn_accident_type_large_shape);
                }
                break;
            }

            holder.setText(R.id.tv_time, accidentInformation.getOccuTime());
            holder.setText(R.id.tv_name, accidentInformation.getAccidentUnitName());
        }
    }
}
