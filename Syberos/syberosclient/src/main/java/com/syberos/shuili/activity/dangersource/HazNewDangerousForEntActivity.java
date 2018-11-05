package com.syberos.shuili.activity.dangersource;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 新建巡视问题
 */
public class HazNewDangerousForEntActivity extends BaseActivity  implements BaseActivity.IDialogInterface{

    @BindView(R.id.ae_describe_controls_audio)
    AudioEditView ae_describe_controls_audio;

    @BindView(R.id.ae_describe_problem_audio)
    AudioEditView ae_describe_problem_audio;

    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    ObjHaz itemInfo;
    @OnClick(R.id.tv_rejected)
    void onCanceledClicked() {

        activityFinish();
    }

    @OnClick(R.id.tv_passed)
    void onOKClicked() {
        commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_new_dangerous;
    }

    @Override
    public void initListener() {
        setDialogInterface(this);
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        itemInfo = (ObjHaz)bundle.getSerializable("data");
    }

    @Override
    public void initView() {
        setInitActionBar(true);
        setActionBarTitle("新建巡查记录");
        setActionBarRightVisible(View.INVISIBLE);
        setFinishOnBackKeyDown(false);
    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }
    @Override
    public void dialogClick() {
        commitForm();
    }

    @Override
    public void dialogCancel() {

    }
    private boolean checkParam(){
        boolean bRet = false;
        if(ae_describe_problem_audio.getEditText() == null || ae_describe_problem_audio.getEditText().isEmpty()){
            ToastUtils.show("发现问题内容不能为空");
            return  bRet;
        }
        if(ae_describe_controls_audio.getEditText() == null || ae_describe_controls_audio.getEditText().isEmpty()){
            ToastUtils.show("处理措施不能为空");
            return  bRet;
        }
        return  true;
    }
    private void commitForm(){
        if(!checkParam())return;
        showDataLoadingDialog();
        String url = GlobleConstants.strCJIP + "/cjapi/cj/yuanXin/Danger/create";
        HashMap<String, String> params = new HashMap<>();
        params.put("hazGuid", itemInfo.getGuid());
        params.put("patTime", "");
        params.put("patPers", SyberosManagerImpl.getInstance().getCurrentUserInfo().getUserName());
        params.put("probFound", ae_describe_problem_audio.getEditText());
        params.put("treaMeas", ae_describe_controls_audio.getEditText());
        params.put("note", "移动端测试");
        params.put("recPers", SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 0;
        localCacheEntity.attachType = 0;
        localCacheEntity.params = params;
        ArrayList<AttachMentInfoEntity>attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();
        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                time.replace("-","");
                info.medPath = App.sCode + "/BIS_HAZ_PAT_REC/"+time+ "/"+info.medName;
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(item.localFile.getPath());
                info.localPath = item.localFile.getPath();
                info.medSize = file.length();
                info.bisTableName = "BIS_HAZ_PAT_REC";
                info.bisGuid = itemInfo.getGuid();
                info.localStatus = "1";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "2"; // 图片
                }else if(item.type == MultimediaView.LocalAttachmentType.AUDIO) {
                    info.medType = "3"; // 音频
                }else if(item.type == MultimediaView.LocalAttachmentType.VIDEO){
                    info.medType = "4";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachMentInfoEntities.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                finish();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    HazNewDangerousForEntActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前风险源内容未提交，确定退出？");
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
}
