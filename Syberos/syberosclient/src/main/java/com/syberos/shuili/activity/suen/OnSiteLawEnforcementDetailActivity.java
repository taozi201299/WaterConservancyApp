package com.syberos.shuili.activity.suen;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.objCase.LawEnforcementEvidenceInformation;
import com.syberos.shuili.entity.objCase.ObjCase;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnSiteLawEnforcementDetailActivity extends BaseActivity {
    @BindView(R.id.ll_multimedia)
    MultimediaView llMultimedia;

//    private final static int REQUEST_CODE = 1545;

    private ObjCase lawEnforcementInformation = null;

    public static final String SEND_BUNDLE_KEY = "LawEnforcementEvidenceInformation";

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_litigant)
    TextView tv_litigant;

    @BindView(R.id.tv_time)
    TextView tv_time;

    @BindView(R.id.tv_undertaker)
    TextView tv_undertaker;


    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    List<LawEnforcementEvidenceInformation> evidenceInformationList = null;


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
        lawEnforcementInformation = (ObjCase) bundle.getSerializable(
                OnSiteLawEnforcementListActivity.SEND_BUNDLE_KEY);

        if (null != lawEnforcementInformation) {
            String caseName = lawEnforcementInformation.caseName;
            String caseLitiName = lawEnforcementInformation.caseLitiName;
            String filiTime = lawEnforcementInformation.filiTime;
            String coltra1 = lawEnforcementInformation.contra1;
            String coltra2 = lawEnforcementInformation.contra2;
            setActionBarTitle(caseName == null ? "未知" : caseName);
            tv_name.setText(lawEnforcementInformation.caseName);
            tv_litigant.setText(caseLitiName == null ? "" : caseLitiName);
            tv_time.setText(filiTime == null ? "" : filiTime);
            if (coltra1 == null) coltra1 = "";
            if (coltra2 == null) coltra2 = "";
            tv_undertaker.setText(lawEnforcementInformation.contra1 + "、" + lawEnforcementInformation.contra2);

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("案件基本情况");
            ae_describe_audio.setEditText(lawEnforcementInformation.caseSitu);
            llMultimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
