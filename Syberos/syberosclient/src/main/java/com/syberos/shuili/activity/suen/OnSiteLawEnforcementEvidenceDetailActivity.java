package com.syberos.shuili.activity.suen;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.objCase.LawEnforcementEvidenceInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;

public class OnSiteLawEnforcementEvidenceDetailActivity extends BaseActivity {

    private LawEnforcementEvidenceInformation evidenceInformation = null;

    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_law_enforcement_evidence_detail;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarTitle("执法证据详情");
        setActionBarRightVisible(View.INVISIBLE);

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        evidenceInformation = (LawEnforcementEvidenceInformation) bundle.getSerializable(
                OnSiteLawEnforcementDetailActivity.SEND_BUNDLE_KEY);

        if (null != evidenceInformation) {
            String[] types = getResources().getStringArray(R.array.law_enforcement_evidence_types);
            tv_type.setText(types[evidenceInformation.getType()]);

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("备注说明");
            ae_describe_audio.setEditText(evidenceInformation.getRemark());

            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        }
    }
}
