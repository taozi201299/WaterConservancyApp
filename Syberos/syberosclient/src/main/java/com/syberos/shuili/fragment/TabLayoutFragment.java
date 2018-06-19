package com.syberos.shuili.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.activity.dangermanagement.InvestigationQueryOneActivity;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseLazyFragment;
import com.syberos.shuili.entity.hidden.HiddenInvestigationTaskInfo;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.view.PullRecyclerView;


import butterknife.BindView;

import static android.content.ContentValues.TAG;

/**
 * Created by jidan on 18-3-11.
 */

public abstract class TabLayoutFragment extends BaseLazyFragment implements PullRecyclerView.OnPullRefreshListener,CommonAdapter.OnItemClickListener {
    public static final String TABLAYOUT_FRAGMENT = "TabLayout_Fragment";
    @BindView(R.id.recyclerViewLayout)
    protected PullRecyclerView recyclerViewLayout;
    protected int type;

    public TabLayoutFragment(){

    }
    public void setType (Bundle bundle){
         this.setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }

    }

    @Override
    protected int getLayoutID() {
       return R.layout.fragment_gateway_tab1;
    }
    @Override
    protected void initListener() {
        recyclerViewLayout.setOnPullRefreshListener(this);

    }
//    @Override
//    protected void initData() {
//        if(!hasStarted || !isPrepared) return;
//        switch (type){
//            case 1:
//                List<String> datas1 = new ArrayList<>();
//                datas1.add("App Enter1");
//                datas1.add("LineEdit and Expandable Layout Activity1");
//                recyclerViewLayout.setAdapter(new CommonAdapter(mContext,R.layout.simple_list_row,datas1) {
//                    @Override
//                    public void convert(ViewHolder holder, Object o) {
//
//                        TextView tv = holder.getView(R.id.textView);
//                        tv.setText(o.toString());
//
//                    }
//                });
//                break;
//            case 2:
//                List<String> datas2= new ArrayList<>();
//                datas2.add("App Enter2");
//                datas2.add("LineEdit and Expandable Layout Activity2");
//                recyclerViewLayout.setAdapter(new CommonAdapter(mContext,R.layout.simple_list_row,datas2) {
//                    @Override
//                    public void convert(ViewHolder holder, Object o) {
//
//                        TextView tv = holder.getView(R.id.textView);
//                        tv.setText(o.toString());
//
//                    }
//                });
//                break;
//            case 3:
//                List<String> datas3= new ArrayList<>();
//                datas3.add("App Enter3");
//                datas3.add("LineEdit and Expandable Layout Activity3");
//                recyclerViewLayout.setAdapter(new CommonAdapter(mContext,R.layout.simple_list_row,datas3) {
//                    @Override
//                    public void convert(ViewHolder holder, Object o) {
//
//                        TextView tv = holder.getView(R.id.textView);
//                        tv.setText(o.toString());
//
//                    }
//                });
//                break;
//        }
//    }

    @Override
    protected void initView() {
        recyclerViewLayout.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerViewLayout.setLayoutManager(new LinearLayoutManager(mContext));


    }

    @Override
    public void onRefresh() {
        LogUtils.i(TAG,"onRefresh");


    }

    @Override
    public void onLoadMore() {
        LogUtils.i(TAG,"onLoadMore");

    }

    @Override
    public void onItemClick(int position) {
        intentActivity((Activity)mContext, InvestigationQueryOneActivity.class,false,true);

    }

    class InvestigationQuery2Adapter extends CommonAdapter<HiddenInvestigationTaskInfo> {
        public InvestigationQuery2Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, HiddenInvestigationTaskInfo investigationInfo) {
            String type = investigationInfo.getHiddGrad();
            LinearLayout ll_type = null;
            ll_type = (LinearLayout)(holder.getView(R.id.ll_type));
            ShapeDrawable bgShape = null;
            switch (type){
                case "0": {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(R.string.normal);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape));
                }
                break;
                case "1": {
                    ((TextView) (holder.getView(R.id.tv_type))).setText(R.string.danger);
                    ll_type.setBackground(getResources().getDrawable(R.drawable.btn_investigation_shape_red));
                }
                break;

            }
            ( (TextView)(holder.getView(R.id.tv_title))).setText(investigationInfo.getHiddName());
            ( (TextView)(holder.getView(R.id.tv_time))).setText(investigationInfo.getRequCompDate());
            ( (TextView)(holder.getView(R.id.tv_name))).setText(investigationInfo.getEngName());
            ( (TextView)(holder.getView(R.id.tv_content))).setText(investigationInfo.getHiddClas());


        }
    }
}
