package com.syberos.shuili.activity.securitycheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.securitycheck.BisSeChit;
import com.syberos.shuili.entity.securitycheck.BisSinsRec;
import com.syberos.shuili.entity.securitycheck.ObjSe;
import com.syberos.shuili.service.AttachMentInfoEntity;
import com.syberos.shuili.service.LocalCacheEntity;
import com.syberos.shuili.utils.CommonUtils;
import com.syberos.shuili.utils.Strings;
import com.syberos.shuili.utils.ToastUtils;
import com.syberos.shuili.view.AudioEditView;
import com.syberos.shuili.view.CustomDialog;
import com.syberos.shuili.view.EnumView;
import com.syberos.shuili.view.MultimediaView;
import com.syberos.shuili.view.indexListView.ClearEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;

import static com.syberos.shuili.config.GlobleConstants.strIP;

/**
 * Created by jidan on 18-4-6.
 * 安全检查新建隐患
 */

public class EnterprisesElementCheckCreateHiddenActivity extends BaseActivity implements View.OnClickListener ,BaseActivity.IDialogInterface{
    private OptionsPickerView levelPicker = null;
    /**
     *  安全元素对象信息
     */
    private ObjSe information = null;
    /**
     * 安全元素下的检查项信息
     */
    private BisSeChit bisSeChit = null;
    /**
     * 现场检查 安全检查记录对象
     */
    private BisSinsRec bisSinsRec = null;
    MvEngColl objectEngine = null;
    ObjectTend objectTend = null;
    boolean hasTend;
    String type;
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
        setDialogInterface(this);

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        hasTend = bundle.getBoolean("hasTend");
        type = bundle.getString("type");
        if("element".equals(type)) {
            if (information == null) {
                information = (ObjSe) bundle.getSerializable("element");
            }
        }else if("check".equals(type)){
            if(bisSinsRec == null){
                bisSinsRec = (BisSinsRec)bundle.getSerializable("checkItem");
            }
        }
        if(objectEngine == null){
            objectEngine = (MvEngColl)bundle.getSerializable("data");
        }
        if(hasTend) {
            if (objectTend == null) {
                objectTend = (ObjectTend) bundle.getSerializable("tend");
            }
        }
        tv_project_name.setText(objectEngine.getName());
        showDataLoadingDialog();
        getHiddenDic();

    }
    private void getHiddenDic(){
        String url  = strIP+"/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        HashMap<String,String> params = new HashMap<>();
        params.put("attTabCode","OBJ_HIDD");
        params.put("attColCode","HIDD_GRAD");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                hiddenGrade  = gson.fromJson(result,DicInfo.class);
                if(hiddenGrade != null && hiddenGrade.dataSource !=null){
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
        setFinishOnBackKeyDown(false);
        if(objectTend == null) {
            ll_project_tend.setVisibility(View.GONE);
        }
        ev_des_audio.setLabelText("隐患描述");
        setFinishOnBackKeyDown(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                commit();
                break;
        }
    }
    @Override
    public void dialogClick() {
        commitForm();

    }

    @Override
    public void dialogCancel() {

    }
    private boolean checkParam(){
        boolean bRet = false;
        if(tv_hidden_name.getText().toString() == null || tv_hidden_name.getText().toString().isEmpty()){
            ToastUtils.show("隐患名称不能为空");
            return bRet;
        }
        if(ll_enum_level.getCurrentDetailText() == null || ll_enum_level.getCurrentDetailText().isEmpty()){
            ToastUtils.show("隐患级别不能为空");
            return  bRet;
        }
        if(tv_hidden_part.getText().toString() == null || tv_hidden_part.getText().toString().isEmpty()){
            ToastUtils.show("隐患部位不能空");
            return bRet;
        }
        if(ev_des_audio.getEditText() == null || ev_des_audio.getEditText().isEmpty()){
            ToastUtils.show("隐患描述不能为空");
            return  bRet;
        }
        return true;
    }
    private void commit(){
        showCommitDialog("确认提交数据?",0);
    }
    /**
     * 多媒体附件的上传 下载
     */
    // TODO: 2018/4/26 安全检查隐患接口 根据engGuid 和 安全检查方案GUID 获取当前组的隐患
    private void commitForm(){
        if(!checkParam())return;
        String url = GlobleConstants.strCJIP + "/cjapi/cj/obj/hidd/addObjHidds";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddName",tv_hidden_name.getText().toString()); // 隐患名称
        params.put("engGuid",objectEngine.getId()); // 所属工程
        params.put("tendGuid",objectTend == null ? "":objectTend.getGuid());
        if("element".equals(type)) {
            params.put("seCheckItemGuid", information.getGuid());
        }else if("check".equals(type)){
            params.put("inspRecGuid",bisSinsRec.guid);
        }
        params.put("inspOrgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("goverRespWiunGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("hiddGrad",String.valueOf(ll_enum_level.getCurrentIndex() +1)); // 隐患级别
        params.put("hiddClas","");
        params.put("proPart",tv_hidden_part.getText().toString()); // 隐患部位
        params.put("hiddDesc",ev_des_audio.getEditText()); // 隐患描述
        params.put("note","移动端测试安全检查记录隐患");
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserInfo().getPersName());
        LocalCacheEntity localCacheEntity = new LocalCacheEntity();
        localCacheEntity.url = url;
        ArrayList<AttachMentInfoEntity> attachMentInfoEntities = new ArrayList<>();
        localCacheEntity.params = params;
        localCacheEntity.type = 0;
        localCacheEntity.seriesKey = UUID.randomUUID().toString();
        ArrayList<MultimediaView.LocalAttachment> list =  ll_multimedia.getBinaryFile();
        if(list != null){
            for(MultimediaView.LocalAttachment item :list){
                AttachMentInfoEntity info = new AttachMentInfoEntity();
                info.medName = item.localFile.getName();
                String time = CommonUtils.getCurrentDateYMD();
                time = time.replace("-","");
                info.medPath = App.roleCode + "/OBJ_HIDD/"+time+ "/"+info.medName;
                info.url = GlobleConstants.strIP + "/sjjk/v1/jck/attMedBase/";
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            final CustomDialog customDialog = new CustomDialog(
                    EnterprisesElementCheckCreateHiddenActivity.this);
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
}
