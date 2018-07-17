package com.syberos.shuili.activity.dangersource;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

import static com.syberos.shuili.utils.Strings.DEFAULT_BUNDLE_NAME;

/**
 * 新建巡视问题
 */
public class InspectionNewDangerousActivity extends BaseActivity {

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
        commitForm();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspection_new_dangerous;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(DEFAULT_BUNDLE_NAME);
        itemInfo = (ObjHaz)bundle.getSerializable("data");
    }

    @Override
    public void initView() {
        setActionBarTitle("新建问题");
        setActionBarRightVisible(View.INVISIBLE);
    }
    private void commitForm(){
       // String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/haz/bisHazPatRec/";
        String url = App.strCJIP + "/wcsps-api/cj/yuanXin/Danger/create";
        HashMap<String, String> params = new HashMap<>();
        params.put("hazGuid", itemInfo.getGuid());
        params.put("patTime", "");
        params.put("patPers", SyberosManagerImpl.getInstance().getCurrentUserInfo().getUserName());
        params.put("probFound", ae_describe_problem_audio.getEditText());
        params.put("treaMeas", ae_describe_controls_audio.getEditText());
        params.put("note", "移动端测试");
        params.put("recPers", SyberosManagerImpl.getInstance().getCurrentUserId());
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
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.bisTableName = "BIS_HAZ_PAT_REC";
                info.bisGuid = "";
                if(item.type == MultimediaView.LocalAttachmentType.IMAGE){
                    info.medType = "0";
                }else {
                    info.medType = "1";
                }
                info.seriesKey = localCacheEntity.seriesKey;
                attachMentInfoEntities.add(info);
            }
        }
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                finish();

            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,resultCode,data);
    }
}
