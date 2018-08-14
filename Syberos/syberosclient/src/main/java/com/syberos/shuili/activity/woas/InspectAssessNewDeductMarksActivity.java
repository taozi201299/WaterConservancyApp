package com.syberos.shuili.activity.woas;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.base.TranslucentActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.woas.DeductMarksInfo;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.service.dataprovider.sync.synchronizer.SynchronizerFactory;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.MultimediaView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 水利稽查考核扣分
 */
public class InspectAssessNewDeductMarksActivity extends BaseActivity implements BaseActivity.IDialogInterface{

    private final String Title = "水利稽查考核";
    private DeductMarksInfo info = null;

    @BindView(R.id.tv_action_bar_title)
    TextView tv_action_bar_title;

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

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        showTitle(Title);
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        info = (DeductMarksInfo) bundle.getSerializable(
                InspectAssessObjectSelectActivity.SEND_BUNDLE_KEY);

        if (null != info) {
            tv_action_bar_title.setText(info.getUnit());
        }
    }

    @Override
    public void dialogClick() {
        commit();
    }

    @Override
    public void dialogCancel() {

    }
    private void submit(){
        showCommitDialog("确认提交考核结果?",0);
    }
    private void commit(){
        String url = GlobleConstants.strCJIP +"/wcsps-api/cj/bis/hidd/rectAcce/addObjHiddRectAcce";
        HashMap<String,String> params = new HashMap<>();
        params.put("woasWiunGuid","");// 被考核单位GUID
        params.put("woasGuid",""); // 工作考核GUID
        params.put("woasGropGuid",""); // 考核组GUID
        params.put("fianDeuc",""); //最终扣分
        params.put("deucNote","");  //扣分说明
        params.put("woasType","");// 考核类型
        params.put("recPers", SyberosManagerImpl.getInstance().getCurrentUserId()); // 记录人员
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
}
