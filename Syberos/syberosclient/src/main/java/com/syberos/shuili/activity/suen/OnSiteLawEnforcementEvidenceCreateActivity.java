package com.syberos.shuili.activity.suen;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.accident.AccidentNewFormForEntActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.objCase.ObjCase;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.Arrays2;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

public class OnSiteLawEnforcementEvidenceCreateActivity extends BaseActivity implements BaseActivity.IDialogInterface,View.OnClickListener{


    @BindView(R.id.tv_quitSearch)
    TextView tvQuitSearch;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    private List<String> types;
    private OptionsPickerView typePicker = null;
    private ObjCase objCase = null;

    @BindView(R.id.ev_severity)
    EnumView ev_severity;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.onActivityResult(requestCode, requestCode, data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_site_law_enforcement_evidence_create;
    }

    @Override
    public void initListener() {
        llCommit.setOnClickListener(this);
        setDialogInterface(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle("新增证据");
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        objCase = (ObjCase) bundle.getSerializable("data");
        if(objCase == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
        types = Arrays2.stringArrayToListString(getResources().getStringArray(
                R.array.law_enforcement_evidence_types));
        ev_severity.setEntries(types);
        ev_severity.setCurrentDetailText(types.get(0));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    OnSiteLawEnforcementEvidenceCreateActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前事故内容未保存，确定退出？");
            customDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityFinish();
                    customDialog.dismiss();
                }
            });
            customDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void dialogClick() {
        commitForm();
    }

    @Override
    public void dialogCancel() {

    }

    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message, 0);
    }

    private void commitForm() {
        showDataLoadingDialog();
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        String url = "";
        HashMap<String, String> params = new HashMap<>();
        localCacheEntity.url = url;
        localCacheEntity.type = 1;
        localCacheEntity.attachType = 0; // 0 暫存 1 提交
        localCacheEntity.params = params;
        ArrayList<AttachMentInfoEntity> attachments = new ArrayList<>();
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list = mv_multimedia.getBinaryFile();


/**
 * 1多媒体关系表 2 多媒体基础信息表
 * 先提交多媒体基础信息表，提交成功后，提交多媒体关系表
 * 文档	1  图片	2  音频	3  视频 4 其他	9

 */
        if (list != null) {
            for (MultimediaView.LocalAttachment item : list) {
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.url = GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(info.medPath);
                info.medSize = file.length();
                info.bisTableName = "OBJ_CASE";
                info.bisGuid = objCase.getGuid();
                info.localStatus = String.valueOf(1);
                if (item.type == MultimediaView.LocalAttachmentType.IMAGE) {
                    info.medType = "2"; // 图片
                } else if (item.type == MultimediaView.LocalAttachmentType.AUDIO) {
                    info.medType = "3"; // 音频
                } else if (item.type == MultimediaView.LocalAttachmentType.VIDEO) {
                    info.medType = "4";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachments.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachments, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                ToastUtils.show("提交成功");
                activityFinish();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
                activityFinish();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                commit();
                break;
            default:
                break;
        }

    }
}
