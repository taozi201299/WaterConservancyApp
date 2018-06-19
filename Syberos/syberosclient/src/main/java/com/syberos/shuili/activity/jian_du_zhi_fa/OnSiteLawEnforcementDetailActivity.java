package com.syberos.shuili.activity.jian_du_zhi_fa;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.LawEnforcementEvidenceInformation;
import com.syberos.shuili.entity.LawEnforcementInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

public class OnSiteLawEnforcementDetailActivity extends BaseActivity {

//    private final static int REQUEST_CODE = 1545;

    private LawEnforcementInformation lawEnforcementInformation = null;

    public static final String SEND_BUNDLE_KEY = "LawEnforcementEvidenceInformation";

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_litigant)
    TextView tv_litigant;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_undertaker)
    TextView tv_undertaker;

    @BindView(R.id.ll_evidences)
    LinearLayout ll_evidences;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    List<LawEnforcementEvidenceInformation> evidenceInformationList = null;

//    @OnClick(R.id.tv_new_evidence)
//    void onNewEvidenceClicked() {
//        intentActivity(OnSiteLawEnforcementDetailActivity.this,
//                OnSiteLawEnforcementEvidenceCreateActivity.class, false, REQUEST_CODE);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_law_enforcement_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        setActionBarRightVisible(View.INVISIBLE);

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        lawEnforcementInformation = (LawEnforcementInformation) bundle.getSerializable(
                OnSiteLawEnforcementListActivity.SEND_BUNDLE_KEY);

        if (null != lawEnforcementInformation) {
            setActionBarTitle(lawEnforcementInformation.getName());
            tv_name.setText(lawEnforcementInformation.getName());
            tv_litigant.setText(lawEnforcementInformation.getLitigant());
            tv_time.setText(lawEnforcementInformation.getTime());
            tv_undertaker.setText(lawEnforcementInformation.getUndertaker());

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("案件基本情况");
            ae_describe_audio.setEditText(lawEnforcementInformation.getDescription());

            evidenceInformationList = lawEnforcementInformation.getEvidenceInformationList();

            // 现场执法证据列表
            ll_evidences.removeAllViews();
            addEvidenceItems(evidenceInformationList);
        }

    }

    private void addEvidenceItems(final List<LawEnforcementEvidenceInformation> informationList) {
        for (LawEnforcementEvidenceInformation information : informationList) {
            addEvidenceItem(information);
        }
    }

    private void addEvidenceItem(final LawEnforcementEvidenceInformation information) {
        View layout = LayoutInflater.from(this).inflate(
                R.layout.layout_law_enforcement_evidence_item, ll_evidences, false);

        LinearLayout ll_evidence_item = layout.findViewById(R.id.ll_evidence_item);
        ll_evidence_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(SEND_BUNDLE_KEY, information);
                intentActivity(OnSiteLawEnforcementDetailActivity.this,
                        OnSiteLawEnforcementEvidenceDetailActivity.class, false, bundle);
            }
        });

        String[] types = getResources().getStringArray(R.array.law_enforcement_evidence_types);
        ((TextView) (layout.findViewById(R.id.tv_name))).setText(types[information.getType()]);

        ((TextView) (layout.findViewById(R.id.tv_time))).setText(information.getTime());

        ll_evidences.addView(layout);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//
//                LawEnforcementEvidenceInformation information
//                        = (LawEnforcementEvidenceInformation)data.getSerializableExtra(
//                                OnSiteLawEnforcementEvidenceCreateActivity.RESULT_KEY);
//
//                addEvidenceItem(information);
//
//                // TODO: 18-4-16 将新增的证件信息上传服务器
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
