package com.syberos.shuili.activity.jian_du_zhi_fa;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.LawEnforcementEvidenceInformation;
import com.syberos.shuili.utils.Arrays2;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OnSiteLawEnforcementEvidenceCreateActivity extends BaseActivity {

    public static final String RESULT_KEY = "LawEnforcementEvidenceInformation";

    private List<String> types;
    private OptionsPickerView typePicker = null;
    private LawEnforcementEvidenceInformation submitInformation = null;

    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @OnClick(R.id.tv_type)
    void onTypeClicked() {
        typePicker.show();
    }

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

        submitInformation = new LawEnforcementEvidenceInformation();

        typePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_type.setText(types.get(options1));
                submitInformation.setType(options1);
            }
        })
                .setTitleText("执法类型选择")
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();

        types = Arrays2.stringArrayToListString(getResources().getStringArray(
                R.array.law_enforcement_evidence_types));
        typePicker.setPicker(types);
    }
}
