package com.syberos.shuili.activity.thematic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class ThematicAcciItemActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ThematicAcciItemAdapeter thematicAcciItemAdapeter;
    ArrayList<AcciEntry.ACCIDATABean> accidataBeans;
    static String totalCount;
    String name;

    @Override
    public int getLayoutId() {
        return R.layout.recycle_list_layout;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        accidataBeans = (ArrayList<AcciEntry.ACCIDATABean>) getIntent().getSerializableExtra("data");
        totalCount = getIntent().getStringExtra("num");
        name = getIntent().getStringExtra("name");
        if (accidataBeans == null || totalCount == null || name == null) {
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            activityFinish();
        }
        showTitle(name);
        thematicAcciItemAdapeter = new ThematicAcciItemAdapeter(this, R.layout.thematic_acci_item_layout);
        recyclerView.setAdapter(thematicAcciItemAdapeter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        thematicAcciItemAdapeter.setData(accidataBeans);
        thematicAcciItemAdapeter.notifyDataSetChanged();

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarRightVisible(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private static class ThematicAcciItemAdapeter extends CommonAdapter<AcciEntry.ACCIDATABean> {
        public ThematicAcciItemAdapeter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, AcciEntry.ACCIDATABean accidataBean) {
            ((TextView) (holder.getView(R.id.tv_acci_name))).setText(accidataBean.getACCI_GRADNAME());
            ((TextView) (holder.getView(R.id.tv_value_1_1))).setText(totalCount);
            ((TextView) (holder.getView(R.id.tv_value_1_2))).setText(accidataBean.getCASNUM());
            ((TextView) (holder.getView(R.id.tv_value_1_3))).setText(accidataBean.getSERINJNUM());
            ((TextView) (holder.getView(R.id.tv_value_1_4))).setText(accidataBean.getECONLOSS());
            ((TextView) (holder.getView(R.id.tv_value_1_4))).setText(accidataBean.getECONLOSS());
            ((AudioEditView) holder.getView(R.id.ev_acci_type)).setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ((AudioEditView) holder.getView(R.id.ev_acci_des)).setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ((AudioEditView) holder.getView(R.id.ev_acci_type)).setEditText(accidataBean.getACCI_CATENAME());
            ((AudioEditView) holder.getView(R.id.ev_acci_des)).setEditText(accidataBean.getACCISITU());


        }
    }
}