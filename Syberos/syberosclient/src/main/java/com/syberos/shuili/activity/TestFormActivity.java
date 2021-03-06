package com.syberos.shuili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjt2325.cameralibrary.util.LogUtil;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.shuili.httputils.HttpUtils;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.test.EngineDetailBean;
import com.syberos.shuili.entity.test.EngineInfoBean;
import com.syberos.shuili.entity.test.PostBean;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.BitmapUtil;
import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.CustomScrollView;
import com.syberos.shuili.view.MultimediaView;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        setDialogInterface(this);
        ll_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        ivActionBar2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityFinish();
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
        tvValue8.setText("2".equals(engineBean.getENG_STAT()) ?"已建":"在建");
        tvValue9.setText("0".equals(engineBean.getSTAT()) ?"停用":"启用");
        tvValue10.setText(engineBean.getDAM_SIZE_HIG());
        tvValue11.setText(engineBean.getDES_FL_STAND());
        tvValue12.setText(engineBean.getDES_FL_STAG());
        tvValue13.setText(engineBean.getDEAD_LEV());
        tvValue14.setText(engineBean.getDEAD_CAP());
        tvValue15.setText("0".equals(engineBean.getIFCHECK())? "否":"是");
        MultimediaView.LocalAttachment localAttachment = new MultimediaView.LocalAttachment();
        localAttachment.fileName = engineBean.getGUID()+"-1"+".png";
        localAttachment.filePath = "static/images/yhd/";
        localAttachment.localFile = new File(localAttachment.filePath + localAttachment.fileName);
        localAttachment.type = IMAGE;
        ArrayList<MultimediaView.LocalAttachment> localAttachments = new ArrayList<>();
        localAttachments.add(localAttachment);
    //    read_only_mv.setData(localAttachments);
        if("1".equals(engineBean.getIFCHECK())) {
            aeDescribeAudio.setModel(MultimediaView.RunningMode.READ_ONLY_MODE);
            aeDescribeAudio.setEditText(engineBean.getCHECKOPIN());
            mvMultimedia.setRunningMode(MultimediaView.RunningMode.READ_ONLY_MODE);
            ll_commit.setVisibility(View.GONE);
            getImageInfo();
        }
    }
    private void getImageInfo(){
        showDataLoadingDialog();
        String url = TestActivity1.TestIP +"/desu/serv/v1/getSpillwayCheck";
        HashMap<String,String>params = new HashMap<>();
        params.put("guid",engineBean.getGUID());
        HttpUtils.getInstance().requestNet_post(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                EngineDetailBean engineDetailBean = gson.fromJson(result,EngineDetailBean.class);
                ArrayList<MultimediaView.LocalAttachment> images = new ArrayList<>();
                ArrayList<EngineDetailBean.CheckFileBean>list = (ArrayList<EngineDetailBean.CheckFileBean>) engineDetailBean.getData().getCHECKFILE();
                if(list != null){
                    for(EngineDetailBean.CheckFileBean item :list){
                        MultimediaView.LocalAttachment localAttachment = new MultimediaView.LocalAttachment();
                        localAttachment.fileName = item.getMedName();
                        localAttachment.filePath = item.getMedPath();
                        localAttachment.localFile = new File(item.getMedPath());
                        localAttachment.type = IMAGE;
                        localAttachment.bExist = true;
                        images.add(localAttachment);
                    }
                }
           mvMultimedia.setData(images);
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
        mvMultimedia.onActivityResult(requestCode, requestCode, data);
    }
    private void commit() {
        String message = "确认提交数据?";
        showCommitDialog(message,0);
    }
    @Override
    public void dialogClick() {
        showDataLoadingDialog("正在提交数据");
        ll_commit.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                report();
            }
        },300);

    }

    @Override
    public void dialogCancel() {

    }
    private void report(){
        String url = TestActivity1.TestIP + "/desu/serv/v1/updateSpillwayCheck";
        HashMap<String,Object> params = new HashMap<>();
        params.put("guid",engineBean.getGUID());
        params.put("ifSpillway","1");
        params.put("checkOpin",aeDescribeAudio.getEditText().toString());  //备注
        ArrayList<MultimediaView.LocalAttachment> list =  mvMultimedia.getBinaryFile();
        List<FileInfo> images = new ArrayList<>();
        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                FileInfo fileInfo = new FileInfo();
                fileInfo.medName = item.localFile.getPath();
                String base64 = BitmapUtil.imageToBase64(item.localFile.getPath());
                fileInfo.imageStr = base64;
                images.add(fileInfo);
            }
        }
        final Gson gson = new Gson();
        params.put("checkImages",images);
        String json = gson.toJson(params);
        final HashMap<String,String>param = new HashMap<>();
        param.put("param",json);
        HttpUtils.getInstance().requestNet_post(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                ll_commit.setEnabled(true);
                closeDataDialog();
                Gson gson1 = new Gson();
                PostBean postBean = gson1.fromJson(result,PostBean.class);
                if(postBean == null || !postBean.getMeta().isSuccess()){
                    ToastUtils.show("提交失败");
                    return;
                }else {
                    ToastUtils.show("提交成功");
                }
                activityFinish();
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ll_commit.setEnabled(true);
                closeDataDialog();
                ToastUtils.show("提交失败");
            }
        });
    }
    class FileInfo implements Serializable{
        String medName;
        String imageStr;
    }
}
