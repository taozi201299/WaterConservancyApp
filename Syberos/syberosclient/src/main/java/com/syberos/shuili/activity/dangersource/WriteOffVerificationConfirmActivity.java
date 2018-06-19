package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;

import com.syberos.shuili.R;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.ClearableEditText.ClearableEditText;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 该文件暂不使用
 */
public class WriteOffVerificationConfirmActivity extends BaseActivity {

    @BindView(R.id.ce_unit)
    ClearableEditText ce_unit;

    @BindView(R.id.ce_code)
    ClearableEditText ce_code;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    private DangerousInformation information = null;

    @OnClick(R.id.tv_passed)
    void onConfirmClicked() {


        ToastUtils.show("TODO: 处理核销确认后续工作");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_write_off_verification_confirm;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (DangerousInformation)bundle.getSerializable(
                WriteOffVerificationListActivity.SEND_BUNDLE_KEY);
        if (null != information) {

        }

        ae_describe_audio.setLabelText("备注");
        ae_describe_audio.setEditText("划拨维修经费5万元，对堤坝进行修整，确保水库安全" +
                "运行，对损坏的堤坝进行修复，加固。");
        ae_describe_audio.setAudio("111",3);
    }
}
