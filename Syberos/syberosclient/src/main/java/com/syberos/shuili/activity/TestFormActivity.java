package com.syberos.shuili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.test.EngineInfoBean;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.CustomScrollView;
import com.syberos.shuili.view.MultimediaView;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;
import static com.syberos.shuili.view.MultimediaView.LocalAttachmentType.IMAGE;

/**
 * Created by Administrator on 2018/9/17.
 */

public class TestFormActivity extends BaseActivity implements BaseActivity.IDialogInterface  {
    @BindView(R.id.tv_provice_value)
    TextView tv_provice_value;
    @BindView(R.id.tv_value1)
    TextView tv_value1;
    @BindView(R.id.iv_action_bar2_left)
    ImageView ivActionBar2Left;
    @BindView(R.id.tv_action_bar2_title)
    TextView tvActionBar2Title;
    @BindView(R.id.clearEditText)
    ClearEditText clearEditText;
    @BindView(R.id.iv_action_bar2_right)
    ImageView ivActionBar2Right;
    @BindView(R.id.iv_action_bar2_right_search)
    ImageView ivActionBar2RightSearch;
    @BindView(R.id.tv_value2)
    TextView tvValue2;
    @BindView(R.id.tv_value3)
    TextView tvValue3;
    @BindView(R.id.tv_value4)
    TextView tvValue4;
    @BindView(R.id.tv_value5)
    TextView tvValue5;
    @BindView(R.id.tv_value6)
    TextView tvValue6;
    @BindView(R.id.tv_value7)
    TextView tvValue7;
    @BindView(R.id.tv_value8)
    TextView tvValue8;
    @BindView(R.id.tv_value9)
    TextView tvValue9;
    @BindView(R.id.tv_value10)
    TextView tvValue10;
    @BindView(R.id.tv_value11)
    TextView tvValue11;
    @BindView(R.id.tv_value12)
    TextView tvValue12;
    @BindView(R.id.tv_value13)
    TextView tvValue13;
    @BindView(R.id.tv_value14)
    TextView tvValue14;
    @BindView(R.id.tv_value15)
    TextView tvValue15;
    @BindView(R.id.iv_line1)
    ImageView ivLine1;
    @BindView(R.id.ae_describe_audio)
    AudioEditView aeDescribeAudio;
    @BindView(R.id.read_only_mv)
    MultimediaView read_only_mv;
    @BindView(R.id.mv_multimedia)
    MultimediaView mvMultimedia;
    @BindView(R.id.scrollView)
    CustomScrollView scrollView;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;

    EngineInfoBean.EngineBean engineBean;

    @Override
    public int getLayoutId() {
        return R.layout.test_form_activity;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        engineBean = (EngineInfoBean.EngineBean) bundle.getSerializable("item");
        if(engineBean == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
            activityFinish();
        }
        updateView();
    }

    @Override
    public void initView() {
        read_only_mv.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
        showTitle("详细信息");
        setActionBarRightVisible(View.INVISIBLE);

    }
    private void updateView(){
        tv_provice_value.setText(engineBean.getPROVINCE() + engineBean.getCITY() + engineBean.getCOUNTRY());
        tv_value1.setText(engineBean.getRES_NAME());
        tvValue2.setText(engineBean.getIFSPILLWAYNAME());
        tvValue3.setText(engineBean.getRES_GRAD());
        tvValue4.setText(engineBean.getRES_TYPE());
        tvValue5.setText(engineBean.getRES_PART());
        tvValue6.setText(engineBean.getSTART_DATE());
        tvValue7.setText(engineBean.getCOMP_DATE());
        tvValue8.setText(engineBean.getENG_STAT());
        tvValue9.setText(engineBean.getSTAT());
        tvValue10.setText(engineBean.getDAM_SIZE_HIG());
        tvValue11.setText(engineBean.getDES_FL_STAND());
        tvValue12.setText(engineBean.getDES_FL_STAG());
        tvValue13.setText(engineBean.getDEAD_LEV());
        tvValue14.setText(engineBean.getDEAD_CAP());
        tvValue15.setText(engineBean.getIFCHECK());
        MultimediaView.LocalAttachment localAttachment = new MultimediaView.LocalAttachment();
        localAttachment.fileName = engineBean.getGUID()+"-1"+".png";
        localAttachment.filePath = "static/images/yhd/";
        localAttachment.localFile = new File(localAttachment.filePath + localAttachment.fileName);
        localAttachment.type = IMAGE;
        ArrayList<MultimediaView.LocalAttachment> localAttachments = new ArrayList<>();
        localAttachments.add(localAttachment);
        read_only_mv.setData(localAttachments);
        if("1".equals(engineBean.getIFCHECK())) {
            aeDescribeAudio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            aeDescribeAudio.setEditText(engineBean.getCHECKOPIN());
            mvMultimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
            ll_commit.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    TestFormActivity.this);
            customDialog.setDialogMessage(null, null,
                    null);
            customDialog.setMessage("当前新增事故内容未保存，确定退出？");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mvMultimedia.onActivityResult(requestCode, requestCode, data);
    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }

    @Override
    public void dialogClick() {
        report();
    }

    @Override
    public void dialogCancel() {

    }
    private void report(){
        String url = GlobleConstants.strCJIP +"/cjapi/cj/bis/hidd/rectAcce/addObjHiddRectAcce";
        HashMap<String,String> params = new HashMap<>();
        params.put("guid",engineBean.getGUID());//隐患GUID
        params.put("note",aeDescribeAudio.getEditText().toString());  //备注
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  mvMultimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.url = GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                info.bisTableName = "BIS_HIDD_RECT_ACCE";
                info.bisGuid = engineBean.getGUID();
                info.localStatus = "1";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "0";
                }else {
                    info.medType = "1";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachMentInfoEntities.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity, attachMentInfoEntities,new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                ToastUtils.show("提交成功");
                finish();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });

    }
}
