package com.syberos.shuili.activity.jian_du_zhi_fa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.adapter.CommonAdapter;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.LawEnforcementEvidenceInformation;
import com.syberos.shuili.entity.LawEnforcementInformation;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 现场执法
 */
public class OnSiteLawEnforcementListActivity extends BaseActivity
        implements CommonAdapter.OnItemClickListener {

    private final String Title = "现场执法";
    private final static int REQUEST_CODE = 1939;

    public static final String SEND_BUNDLE_KEY = "LawEnforcementInformation";

    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView;

    private ListAdapter listAdapter = null;
    private List<LawEnforcementInformation> lawEnforcementInformationList = null;
    private LawEnforcementInformation currentAddEvidenceItem = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_law_enforcement_list;
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
        setActionBarRightVisible(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(this,
                R.layout.activity_on_site_law_enforcement_list_item);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);

        // init data
        lawEnforcementInformationList = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            LawEnforcementInformation information = new LawEnforcementInformation();
            information.setName("现场执法案件" + (i + 1));
            information.setLitigant("李白、杜甫" + (i + 1));
            information.setTime("2017-12-12 20:00");
            information.setUndertaker("王羲之" + (i + 1));
            information.setDescription("划拨维修经费5万元，对堤坝进行修整，" +
                    "确保水库安全运行，对损坏的堤坝进行修复，加固。");

            List<LawEnforcementEvidenceInformation> evidenceInformationList = new ArrayList<>();
            for (int j = 0; j < 6; ++j) {
                LawEnforcementEvidenceInformation evidenceInformation
                        = new LawEnforcementEvidenceInformation();

                evidenceInformation.setType(j);
                evidenceInformation.setRemark("防洪工作实行全面规划、统筹兼顾、预防为主、" +
                        "综合治理、局部利益服从全局利益的原则");
                evidenceInformation.setTime("2017-12-05 12:28");

                evidenceInformationList.add(evidenceInformation);
            }

            information.setEvidenceInformationList(evidenceInformationList);

            lawEnforcementInformationList.add(information);
        }

        listAdapter.setData(lawEnforcementInformationList);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        LawEnforcementInformation information = lawEnforcementInformationList.get(position);
        bundle.putSerializable(SEND_BUNDLE_KEY, information);
        intentActivity(this,
                OnSiteLawEnforcementDetailActivity.class, false, bundle);
    }

    private class ListAdapter extends CommonAdapter<LawEnforcementInformation> {
        public ListAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final LawEnforcementInformation information) {

            ((Button) (holder.getView(R.id.btn_new_evidence))).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentAddEvidenceItem = information;
                            intentActivity(OnSiteLawEnforcementListActivity.this,
                                    OnSiteLawEnforcementEvidenceCreateActivity.class, false, REQUEST_CODE);
                        }
                    });

            ((TextView) (holder.getView(R.id.tv_title))).setText(
                    information.getName());
            ((TextView) (holder.getView(R.id.tv_time))).setText(
                    information.getTime());
            ((TextView) (holder.getView(R.id.tv_name))).setText(
                    information.getLitigant());
            ((TextView) (holder.getView(R.id.tv_content))).setText(
                    information.getDescription());

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                LawEnforcementEvidenceInformation information
                        = (LawEnforcementEvidenceInformation)data.getSerializableExtra(
                        OnSiteLawEnforcementEvidenceCreateActivity.RESULT_KEY);
                List<LawEnforcementEvidenceInformation>
                        leeiList = currentAddEvidenceItem.getEvidenceInformationList();
                leeiList.add(information);
                currentAddEvidenceItem.setEvidenceInformationList(leeiList);

                // TODO: 18-4-16 将新增的证据信息上传服务器
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
