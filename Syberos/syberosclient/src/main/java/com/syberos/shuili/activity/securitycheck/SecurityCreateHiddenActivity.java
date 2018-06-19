package com.syberos.shuili.activity.securitycheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.securitycheck.BisSinsScheGroup;
import com.syberos.shuili.entity.securitycheck.RelSinsGroupWiun;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by jidan on 18-4-6.
 * 安全检查新建隐患
 */

public class SecurityCreateHiddenActivity extends BaseActivity implements View.OnClickListener{
    private OptionsPickerView levelPicker = null;
    private BisSinsScheGroup bisSinsScheGroup;
    RelSinsGroupWiun relSinsGroupWiun;
    DicInfo hiddenGrade;
    @BindView(R.id.investigation_add_layout)
    LinearLayout ll_investigation_add_layout;
    @BindView(R.id.ll_enum_level)
    EnumView ll_enum_level;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;
    @BindView(R.id.tv_hidden_part)
    ClearEditText tv_hidden_part;
    @BindView(R.id.tv_hidden_name)
    ClearEditText tv_hidden_name;
    @BindView(R.id.ll_multimedia)
    MultimediaView ll_multimedia;
    @BindView(R.id.ev_des_audio)
    AudioEditView ev_des_audio;
    @BindView(R.id.tv_project_name)
    TextView tv_project_name;
    @BindView(R.id.ll_project_tend)
    RelativeLayout ll_project_tend;
    @BindView(R.id.ll_part)
    RelativeLayout ll_part;
    @BindView(R.id.ll_part_line)
    View ll_part_line;

    @Override
    public int getLayoutId() {
        return R.layout.activity_investigation_enterprise_add_layout;
    }

    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);

    }

    @Override
    public void initData() {
        showDataLoadingDialog();
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        if(relSinsGroupWiun == null) {
            relSinsGroupWiun = (RelSinsGroupWiun) bundle.getSerializable(
                    SecurityCheckProjectSelectActivity.SEND_BUNDLE_KEY);
        }
        tv_project_name.setText(relSinsGroupWiun.getEngName());
        if(bisSinsScheGroup == null) {
            bisSinsScheGroup = (BisSinsScheGroup) bundle.getSerializable("group");
        }
        if(relSinsGroupWiun == null || bisSinsScheGroup == null){
            ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-6).getMessage());
            closeDataDialog();
            return;
        }
        getHiddenDic();

    }
    private void getHiddenDic(){
        String url  = "http://192.168.1.8:8080/wcsps-supervision/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String> params = new HashMap<>();
        params.put("attTabCode","OBJ_HIDD");
        params.put("attColCode","HIDD_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenGrade  = gson.fromJson(result,DicInfo.class);
                if(hiddenGrade != null){
                    setEnumData();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                ToastUtils.show(errorInfo.getMessage());
            }
        });
    }
    private void setEnumData(){
        HashMap<Integer,String> values= new HashMap<>();
        int size = hiddenGrade.dataSource.size();
        for(int i = 0; i < size ; i ++){
            values.put(i,hiddenGrade.dataSource.get(i).getDcItemName());
        }
        ll_enum_level.setEntries(values);
    }
    @Override
    public void initView() {
        showTitle("新建隐患");
        setActionBarRightVisible(View.INVISIBLE);
        ll_project_tend.setVisibility(View.GONE);
        ll_part.setVisibility(View.GONE);
        ll_part_line.setVisibility(View.GONE);
        ev_des_audio.setLabelText("隐患描述");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                commit();
                break;
        }
    }
    // TODO: 2018/4/26 安全检查隐患接口 根据engGuid 和 安全检查方案GUID 获取当前组的隐患
    private void commit(){
        String  url = "http://192.168.1.8:8080/wcsps-supervision/v1/bis/obj/objHidd/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddName",tv_hidden_name.getText().toString()); // 隐患名称
        params.put("engGuid",relSinsGroupWiun.getGuid()); // 所属工程
        params.put("sinsScheGuid",bisSinsScheGroup.getScheGuid());
        params.put("orgGuid","");
        params.put("hiddGrad",String.valueOf(ll_enum_level.getCurrentIndex())); // 隐患级别
        params.put("hiddClas","");
        params.put("proPart",tv_hidden_part.getText().toString()); // 隐患部位
        params.put("hiddDesc",ev_des_audio.getEditText()); // 隐患描述
        params.put("note","移动端测试");
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 1;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();
        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                info.medPath = item.localFile.getPath();
                info.url = "http://192.168.1.8:8080/wcsps-supervision/v1/jck/attMedBase/";
                info.bisTableName = "OBJ_HIDD";
                info.bisGuid = "";
                info.localStatus = "0";
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ll_multimedia.onActivityResult(requestCode,requestCode,data);
    }
}
