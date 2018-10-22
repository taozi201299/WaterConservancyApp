package com.syberos.shuili.activity.wins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.wins.BisWinsProj;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

/**
 * 新建稽查问题
 */
public class InspectNewProblemActivity extends BaseActivity implements BaseActivity.IDialogInterface,View.OnClickListener {

    public static final String RESULT_KEY = "InspectProblemInformation";
    private final String Title = "新建稽察问题";

    @BindView(R.id.tv_project)
    TextView tv_project;
    @BindView(R.id.ll_problems_type)
    EnumView ll_problems_type;
    @BindView(R.id.ll_severity_level)
    EnumView ll_severity_level;
    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;
    @BindView(R.id.ae_rect_audio)
    AudioEditView ae_rect_audio;
    @BindView(R.id.mv_multimedia)
    MultimediaView mv_multimedia;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;

    private BisWinsProj bisWinsProj = null;


    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_new_problem;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);
        setDialogInterface(this);

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if(bisWinsProj == null){
            bisWinsProj = (BisWinsProj) bundle.getSerializable("inspectionProjects");
        }
        if(bisWinsProj == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            activityFinish();
        }
        tv_project.setText(bisWinsProj.getProjName());

    }

    @Override
    public void initView() {
        setInitActionBar(true);
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        ll_problems_type.setEntries(GlobleConstants.winsProbMap);
        ll_problems_type.setCurrentDetailText(GlobleConstants.winsProbMap.get(1));
        ll_severity_level.setEntries(GlobleConstants.winsProbTypeMap);
        ll_severity_level.setCurrentDetailText(GlobleConstants.winsProbTypeMap.get(1));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.onActivityResult(requestCode, requestCode, data);
    }

    private int getProbType(String value){
        for(int key : GlobleConstants.winsProbMap.keySet()){
            if(GlobleConstants.winsProbMap.get(key).equals(value)){
                return key;
            }
        }
        return 1;
    }
    private int getProbCate(String value){
        for(int key : GlobleConstants.winsProbTypeMap.keySet()){
            if(GlobleConstants.winsProbTypeMap.get(key).equals(value)){
                return key;
            }
        }
        return 1;
    }
    @Override
    public void dialogClick() {
        commit();
    }

    @Override
    public void dialogCancel() {

    }
    private void submit(){
        showCommitDialog("确认提交稽查问题?",0);
    }

    private boolean checkParam(){
        boolean bRet = false;
        if(ll_problems_type.getCurrentDetailText()  == null || ll_problems_type.getCurrentDetailText().isEmpty()){
            ToastUtils.show("问题分类不能为空");
            return  bRet;
        }
        if(ll_severity_level.getCurrentDetailText() == null ||ll_severity_level.getCurrentDetailText().isEmpty()){
            ToastUtils.show("问题严重程度内容不能为空");
            return  bRet;
        }
        if(ae_describe_audio.getEditText()== null || ae_describe_audio.getEditText().isEmpty()){
            ToastUtils.show("问题描述不能为空");
            return  bRet;
        }
        if(ae_rect_audio.getEditText() == null || ae_rect_audio.getEditText().isEmpty()){
            ToastUtils.show("整改建议不能为空");
            return  bRet;
        }
        return  true;
    }
    private void commit(){
        if(!checkParam())return;
        showDataLoadingDialog();
        String url = GlobleConstants.strZRIP +"/wins/mobile/bisWinsProb/";
        HashMap<String,String> params = new HashMap<>();
        params.put("winsProjGuid",bisWinsProj.getProjGuid());// 稽察项目GUID
        params.put("probType", String.valueOf(getProbType(ll_problems_type.getCurrentDetailText()))); // 问题分类
        params.put("probCate", String.valueOf(getProbCate(ll_severity_level.getCurrentDetailText()))); // 严重程度
        params.put("probDep",""); //对应司局
        params.put("probDesc",ae_describe_audio.getEditText());  //问题详情描述
        params.put("rectSugg",ae_rect_audio.getEditText());// 整改建议
        params.put("rectConc","");
        params.put("unrecReson",""); // 整改结论
        params.put("rectMeas",""); // 未整改原因
        params.put("rectLed",""); // 整改负责人
        params.put("isRect",""); // 是否整改
        params.put("trackPeop",""); //整改人
        params.put("note","客户端测试数据"); // 备注
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 0;
        localCacheEntity.commitType = 0;
        localCacheEntity.attachType = 0; // 0 暫存 1 提交
        localCacheEntity.params = params;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  mv_multimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                time.replace("-","");
                info.medPath = "wins/BIS_WINS_PROB/"+time+ "/"+info.medName;
                info.url =  GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                File file = new File(item.localFile.getPath());
                info.localPath = item.localFile.getPath();
                info.medSize = file.length();
                info.bisTableName = "BIS_WINS_PROB";
                info.bisGuid = "";
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                submit();
            break;
        }
    }
}
