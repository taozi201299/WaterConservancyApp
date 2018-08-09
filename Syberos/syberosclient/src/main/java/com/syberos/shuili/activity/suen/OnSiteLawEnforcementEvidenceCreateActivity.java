package com.syberos.shuili.activity.suen;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.objCase.LawEnforcementEvidenceInformation;
import com.syberos.shuili.utils.Arrays2;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OnSiteLawEnforcementEvidenceCreateActivity extends BaseActivity {

    public static final String RESULT_KEY = "LawEnforcementEvidenceInformation";

    private List<String> types;
    private OptionsPickerView typePicker = null;
    private LawEnforcementEvidenceInformation submitInformation = null;

    @BindView(R.id.ev_severity)
    EnumView ev_severity;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;


    @OnClick(R.id.tv_submit)
    void onSubmitClicked() {

        submitInformation.setRemark(ae_describe_audio.getEditText());
        submitInformation.setTime(Strings.formatDatetimeHour(System.currentTimeMillis()));

        Intent intent = new Intent();
        intent.putExtra(RESULT_KEY, submitInformation);
        setResult(RESULT_OK, intent);
        activityFinish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.onActivityResult(requestCode,requestCode,data);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_law_enforcement_evidence_create;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        showTitle("新增证据");
        setActionBarRightVisible(View.INVISIBLE);
        submitInformation = new LawEnforcementEvidenceInformation();
        types = Arrays2.stringArrayToListString(getResources().getStringArray(
                R.array.law_enforcement_evidence_types));
        ev_severity.setEntries(types);
    }
}
