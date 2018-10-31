package com.syberos.shuili.activity.woas;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsGroup;
import com.syberos.shuili.entity.woas.BisWoasGrop;
import com.syberos.shuili.entity.woas.BisWoasObj;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerFactory;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
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

/**
 * 水利稽察考核扣分
 */
public class InspectAssessNewDeductMarksActivity extends BaseActivity implements BaseActivity.IDialogInterface{

    private final String Title = "水利稽察考核";
    /**
     * 考核组对象
     */
    BisWoasGrop bisWoasGrop = null;
    /**
     * 考核对象
     */
    private BisWoasObj bisWoasObj = null;

    @BindView(R.id.tv_woas_unit)
    TextView tv_woas_unit;

    @BindView(R.id.ce_score)
    TextView ce_score;

    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;

    @OnClick(R.id.tv_save)
    void onSubmitClicked() {
        submit();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_assess_new_deduct_marks;
    }

    @Override
    public void initListener() {
        setDialogInterface(this);

    }

    @Override
    public void initData() {
        if(bisWoasGrop == null || bisWoasObj == null) {
            Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
            bisWoasObj = (BisWoasObj) bundle.getSerializable("bisWoasObj");
            bisWoasGrop = (BisWoasGrop) bundle.getSerializable("bisWoasGroup");
            if(bisWoasObj == null || bisWoasGrop == null){
                ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
                activityFinish();
            }
        }
        updateView();
    }

    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        setFinishOnBackKeyDown(false);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.onActivityResult(requestCode, resultCode, data);
    }

    private void updateView(){
        tv_woas_unit.setText(bisWoasObj.getWoasWiunName());
    }
    @Override
    public void dialogClick() {
        commit();
    }

    @Override
    public void dialogCancel() {

    }
    private boolean checkParam(){
        boolean bRet = false;
        if(ce_score.getText().toString() == null || ce_score.getText().toString().isEmpty()){
            ToastUtils.show("考核扣分不能为空");
            return  bRet;
        }
        if(ae_describe_audio.getEditText() == null || ae_describe_audio.getEditText().isEmpty()){
            ToastUtils.show("扣分说明不能为空");
            return  bRet;
        }
        return  true;
    }
    private void submit(){
        showCommitDialog("确认提交考核结果?",0);
    }
    private void commit(){
        if(!checkParam())return;
        showDataLoadingDialog();
        String url = GlobleConstants.strZRIP +"/woas/mobile/bisWoasDeuc/";
        HashMap<String,String> params = new HashMap<>();
        params.put("woasWiunGuid",bisWoasObj.getGuid());// 被考核单位GUID
        params.put("woasGuid",bisWoasObj.getWoasGuid()); // 工作考核GUID
        params.put("woasGropGuid",bisWoasGrop.getGuid()); // 考核组GUID
        params.put("fianDeuc", ce_score.getText().toString()); //最终扣分
        params.put("deucNote",ae_describe_audio.getEditText());  //扣分说明
        params.put("woasType","1");// 考核类型
        params.put("recPers", SyberosManagerImpl.getInstance().getCurrentUserId()); // 记录人员
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 0;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  mv_multimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                time = time.replace("-","");
                info.medPath = "woas/BIS_WOAS_DEUC/"+time+ "/"+info.medName;
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(item.localFile.getPath());
                info.medSize = file.length();
                info.localPath = item.localFile.getPath();
                info.bisTableName = "BIS_WOAS_DEUC";
                info.bisGuid = bisWoasObj.getGuid();
                info.localStatus = "0";
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
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities,new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                ToastUtils.show("提交成功");
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    InspectAssessNewDeductMarksActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前内容未保存，确定退出？");
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
