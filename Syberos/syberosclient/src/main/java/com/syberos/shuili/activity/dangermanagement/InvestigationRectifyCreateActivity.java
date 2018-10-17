package com.syberos.shuili.activity.dangermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.App;
import com.syberos.shuili.activity.accident.AccidentNewFormForEntActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.hidden.ObjHidden;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * Created by jidan on 18-3-31.
 */

public class InvestigationRectifyCreateActivity extends BaseActivity implements View.OnClickListener ,BaseActivity.IDialogInterface {
    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @BindView(R.id.ev_rectify_des)
    AudioEditView ev_rectify_des;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    /**
     * 接受传递过来的参数
     */
    private ObjHidden investigationInfo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_enterprice_rectify_create_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);
        setDialogInterface(this);
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        investigationInfo = (ObjHidden) bundle.getSerializable("data");
        if(investigationInfo == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            finish();
        }

    }

    @Override
    public void initView() {
        showTitle("隐患整改");
        setInitActionBar(true);
        setActionBarRightVisible(View.INVISIBLE);
        ev_rectify_des.setLabelText("整改描述");

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }

    @Override
    public void onClick(View v) {
        showCommitDialog("确认提交整改?",0);


    }

    @Override
    public void dialogClick() {
        commit();

    }

    @Override
    public void dialogCancel() {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    InvestigationRectifyCreateActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前内容未提交，确定退出？");
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
    private boolean checkParam(){
        boolean bRet = false;
        if(ev_rectify_des.getEditText() == null || ev_rectify_des.getEditText().isEmpty()){
            ToastUtils.show("整改描述不能为空");
            return  bRet;
        }
        return true;
    }
    private void commit(){
        if(!checkParam())return;
        showDataLoadingDialog();
        String url = GlobleConstants.strCJIP +"/cjapi/cj/bis/hidd/rectPro/addObjHiddRectPro";
        HashMap<String,String> params = new HashMap<>();
        params.put("hiddGuid",investigationInfo.getGuid());//隐患GUID
        params.put("rectProg",ev_rectify_des.getEditText()); //整改进度情况
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserId());// 上报人
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                info.bisTableName = "BIS_HIDD_RECT_PROG";
                info.bisGuid = investigationInfo.getGuid();
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
}
