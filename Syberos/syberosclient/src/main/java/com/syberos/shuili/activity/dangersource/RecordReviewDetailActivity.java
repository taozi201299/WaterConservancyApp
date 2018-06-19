package com.syberos.shuili.activity.dangersource;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.syberos.shuili.R;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.entity.DangerousInformation;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomEdit;
import com.syberos.shuili.view.MultimediaView;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordReviewDetailActivity extends TranslucentActivity {

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.ae_identify_describe_audio)
    AudioEditView ae_identify_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    private DangerousInformation information = null;

    @OnClick(R.id.rl_record)
    void onRecordReviewProcessClicked() {
        intentActivity(this, RecordReviewProcessActivity.class, false, true);
    }

    @OnClick(R.id.rl_patrol)
    void onRecordReviewPatrolClicked() {
        intentActivity(this, RecordReviewHistoryPatrolListActivity.class,
                false, true);
    }

    @OnClick(R.id.tv_rejected)
    void onRejectedClicked() {
        final Dialog confirmDialog = new Dialog(this);
        View v1 = LayoutInflater.from(this).inflate(
                R.layout.activity_dangerous_record_rejected_form, null);
        final CustomEdit customEdit = (CustomEdit) v1.findViewById(R.id.et_content);
        confirmDialog.setContentView(v1);
        Window dialogWindow = confirmDialog.getWindow();
        WindowManager.LayoutParams lp1 = dialogWindow.getAttributes();

        lp1.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp1.gravity = Gravity.CENTER;
        confirmDialog.setCancelable(false);
        dialogWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_shape));
        dialogWindow.setAttributes(lp1);
        Button bt_cancel = (Button)v1.findViewById(R.id.btn_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
        Button btn_confirm = (Button)v1.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("TODO: 处理驳回业务，后续处理逻辑: " + customEdit.getText().toString());
                confirmDialog.dismiss();
            }
        });

        confirmDialog.show();
    }

    @OnClick(R.id.tv_passed)
    void onPassedClicked() {
        if (null != information) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(RecordReviewListActivity.SEND_BUNDLE_KEY, information);
            intentActivity(this, RecordReviewConfirmActivity.class, false, bundle);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_record_review_detail;
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
                RecordReviewListActivity.SEND_BUNDLE_KEY);

        if (null != information) {

            mv_multimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);

            ae_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_describe_audio.setLabelText("危险源描述");
            ae_describe_audio.setEditText("核实描述核实描述核实描述核实描述" +
                    "核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述");
            ae_describe_audio.setAudio("111",3);
            ae_describe_audio.setAudio("222",4);

            ae_identify_describe_audio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            ae_identify_describe_audio.setLabelText("辨识评价描述");
            ae_identify_describe_audio.setEditText("核实描述核实描述核实描述核实描述" +
                    "核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述核实描述");
            ae_identify_describe_audio.setAudio("111",3);
            ae_identify_describe_audio.setAudio("222",4);
        }
    }
}
