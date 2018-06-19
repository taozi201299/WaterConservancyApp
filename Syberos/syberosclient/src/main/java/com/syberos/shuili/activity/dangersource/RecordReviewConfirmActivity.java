package com.syberos.shuili.activity.dangersource;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.dangersource.ObjHaz;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 危险源审核 行政版  提交到重大危险源备案核销信息
 */
public class RecordReviewConfirmActivity extends BaseActivity {

    @BindView(R.id.ce_unit)
    TextView ce_unit;

    @BindView(R.id.ce_code)
    TextView ce_code;
    @BindView(R.id.tv_accident_liability_label)
    TextView tv_accident_liability_label;


    @BindView(R.id.ae_describe_audio)
    AudioEditView ae_describe_audio;

    private ObjHaz information = null;

    @OnClick(R.id.tv_passed)
    void onConfirmClicked() {


        ToastUtils.show("TODO: 处理备案确认后续工作");
        commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_record_review_confirm;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setActionBarRightVisible(View.INVISIBLE);
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        information = (ObjHaz)bundle.getSerializable(
                RecordReviewListActivity.SEND_BUNDLE_KEY);
        int titleType = bundle.getInt("title");
        String title = "";

        if(titleType == 0){
            title = "备案审核";
            tv_accident_liability_label.setText("是否备案");
        }else {
          title = "核销审核";
            tv_accident_liability_label.setText("是否核销");
        }
        showTitle(title);

        if (null != information) {
            ce_code.setText(information.getHazCode());
            ce_unit.setText(information.getGuid());
        }

        ae_describe_audio.setLabelText("备注");
        ae_describe_audio.setEditText("划拨维修经费5万元，对堤坝进行修整，确保水库安全" +
                "运行，对损坏的堤坝进行修复，加固。");
    }
    private  void commit(){
        String url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/haz/maj/bisHazMajRegWrit/";
        HashMap<String,String> params= new HashMap<>();
        params.put("hazGuid",information.getGuid()); // 危险源GUID
        params.put("regOrgGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId()); // 报备单位GUID
        params.put("regTime", CommonUtils.getCurrentDate()); //备案日期
        params.put("regCode",""); // 备案编号
        params.put("writeOrgGuid",""); // 核销单位GUID
        params.put("writeCode",""); //核销号
        params.put("writeOffTime",""); // 核销时间
        params.put("regStat","");
        params.put("writStat","");
        params.put("note","移动端测试"); // 备注
        params.put("collTime",""); // 采集时间
        params.put("updTime",""); // 更新时间
        params.put("recPers",""); // 记录人员
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        SyberosManagerImpl.getInstance().submit(localCacheEntity,attachMentInfoEntities, new RequestCallback<String>() {
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
