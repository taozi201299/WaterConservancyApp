package com.syberos.shuili.activity.inspect;

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
import com.syberos.shuili.entity.InspectProblemInformation;
import com.syberos.shuili.entity.inspect.BisWinsProb;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/30.
 */

public class InspectionProblemsAcitvity extends BaseActivity {
    @BindView(R.id.inspec_prob_recycleview)
    RecyclerView inspec_prob_recycleview;

    private BisWinsProb bisWinsProb = null;

    private ProblemAdapter problemAdapter ;
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
        problemAdapter = new ProblemAdapter(this,R.layout.inspect_detail_problem_list_item);
        inspec_prob_recycleview.setAdapter(problemAdapter);
    }

    public class ProblemAdapter extends CommonAdapter<BisWinsProb>{

        public ProblemAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, BisWinsProb bisWinsProb) {
            RelativeLayout rl_detail_problem_item = holder.getView(R.id.rl_detail_problem_item);
            rl_detail_problem_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                //    bundle.putSerializable(SEND_BUNDLE_KEY, problem);
                    intentActivity(InspectionProblemsAcitvity.this,
                            InspectProblemDetailActivity.class, false, bundle);
                }
            });


            int type = Integer.valueOf(bisWinsProb.getProbCate());
            LinearLayout ll_type = holder.getView(R.id.ll_type);
            TextView tv_type = holder.getView(R.id.tv_type);
            switch (type) {
                case InspectProblemInformation.SEVERITY_NORMAL: {
                    tv_type.setText(R.string.severity_normal);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_color_inspect_type_normal));
                }
                break;
                case InspectProblemInformation.SEVERITY_BIG: {
                    tv_type.setText(R.string.severity_big);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_color_inspect_type_big));
                }
                break;
                case InspectProblemInformation.SEVERITY_LARGE: {
                    tv_type.setText(R.string.severity_large);

                    ll_type.setBackground(getResources().getDrawable(
                            R.drawable.btn_color_inspect_type_large));
                }
                break;
            }
            TextView tv_titile = holder.getView(R.id.tv_title);
            TextView tv_time = holder.getView(R.id.tv_time);
            TextView tv_name = holder.getView(R.id.tv_name);
            tv_titile.setText(bisWinsProb.getGuid());
            tv_time.setText(bisWinsProb.getCollTime());
            tv_name.setText(bisWinsProb.getProbType());
        }
    }
}
