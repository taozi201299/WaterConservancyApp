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
import com.syberos.shuili.App;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.entity.basicbusiness.MvEngColl;
import com.syberos.shuili.entity.basicbusiness.ObjectEngine;
import com.syberos.shuili.entity.basicbusiness.ObjectTend;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.entity.securitycheck.BisSeChit;
import com.syberos.shuili.entity.securitycheck.BisSinsRec;
import com.syberos.shuili.entity.securitycheck.ObjSe;
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

public class EnterprisesElementCheckCreateHiddenActivity extends BaseActivity implements View.OnClickListener{
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

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getBundleExtra(Strings.DEFAULT_BUNDLE_NAME);
        hasTend = bundle.getBoolean("hasTend");
        type = bundle.getString("type");
        if("element".equals(type)) {
            if (bisSeChit == null) {
                bisSeChit = (BisSeChit) bundle.getSerializable("checkItem");
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
        String url  = "http://192.168.1.8:8080/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
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
        if(objectTend == null) {
            ll_project_tend.setVisibility(View.GONE);
        }
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

    /**
     * 多媒体附件的上传 下载
     */
    // TODO: 2018/4/26 安全检查隐患接口 根据engGuid 和 安全检查方案GUID 获取当前组的隐患
    private void commit(){
        String url = App.strCJIP + "/wcsps-api/cj/obj/hiddAndSe/addObjHidd";
        //String  url = "http://192.168.1.8:8080/sjjk/v1/bis/obj/objHidd/";
        HashMap<String,String>params = new HashMap<>();
        params.put("hiddName",tv_hidden_name.getText().toString()); // 隐患名称
        params.put("engGuid",objectEngine.getId()); // 所属工程
        params.put("tendGuid",objectTend == null ? "":objectTend.getGuid());
        if("element".equals(type)) {
            params.put("seCheckItemGuid", bisSeChit.getGuid());
        }else if("check".equals(type)){
            params.put("inspRecGuid",bisSinsRec.sinsGuid);
        }
        params.put("orgGuid",SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        params.put("hiddGrad",String.valueOf(ll_enum_level.getCurrentIndex())); // 隐患级别
        params.put("hiddClas","");
        params.put("proPart",tv_hidden_part.getText().toString()); // 隐患部位
        params.put("hiddDesc",ev_des_audio.getEditText()); // 隐患描述
        params.put("note","移动端测试安全检查记录隐患");
        params.put("recPers",SyberosManagerImpl.getInstance().getCurrentUserId());
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
                info.url = "http://192.168.1.8:8080/sjjk/v1/jck/attMedBase/";
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
