package com.syberos.shuili.activity.wins;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

/**
 * 新建稽查问题
 */
public class InspectNewProblemActivity extends BaseActivity implements BaseActivity.IDialogInterface,View.OnClickListener {

    public static final String RESULT_KEY = "InspectProblemInformation";
    private final String Title = "新建稽查问题";

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


    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect_new_problem;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        ll_problems_type.setEntries(GlobleConstants.winsProbMap);
        ll_problems_type.setCurrentDetailText(GlobleConstants.winsProbMap.get(1));
        ll_severity_level.setEntries(GlobleConstants.winsProbTypeMap);
        ll_severity_level.setCurrentDetailText(GlobleConstants.winsProbTypeMap.get(1));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mv_multimedia.setData(null);
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
    private void commit(){
        String url = GlobleConstants.strCJIP +"/wcsps-api/cj/bis/hidd/rectAcce/addObjHiddRectAcce";
        HashMap<String,String> params = new HashMap<>();
        params.put("winProjGuid","");// 稽察项目GUID
        params.put("probType",""); // 问题分类
        params.put("probCate",""); // 严重程度
        params.put("probDep",""); //对应司局
        params.put("probDesc","");  //问题详情描述
        params.put("rectSugg","");// 整改建议
        params.put("unrecReson",""); // 整改结论
        params.put("rectMeas",""); // 未整改原因
        params.put("rectLed",""); // 整改负责人
        params.put("isRect",""); // 是否整改
        params.put("trackPeop",""); //整改人
        params.put("note",""); // 备注
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.commitType = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  mv_multimedia.getBinaryFile();

        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.url = GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
                info.bisTableName = "BIS_HIDD_RECT_ACCE";
                info.bisGuid = "";
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                submit();
            break;
        }
    }
}
